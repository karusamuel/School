package com.nahashon.second.activity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.support.constraint.ConstraintLayout;
import android.support.design.internal.NavigationMenu;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import com.github.barteksc.pdfviewer.PDFView;
import com.github.barteksc.pdfviewer.listener.OnLoadCompleteListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.nahashon.second.R;
import com.wang.avi.AVLoadingIndicatorView;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import javax.net.ssl.HttpsURLConnection;

import io.github.yavski.fabspeeddial.FabSpeedDial;

public class NoteViewer extends AppCompatActivity {
    DatabaseReference mDatabase;
    FirebaseDatabase mD;
    TextView dateTextView, notesTextView;
    FabSpeedDial speedDial;
    PDFView myView;
    AVLoadingIndicatorView view;
    ConstraintLayout layout;


    TextToSpeech textToSpeech;
    String content;
    int result;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note);

        myView = findViewById(R.id.PDFView);
        view = findViewById(R.id.pdfAv);
        layout=findViewById(R.id.xx);


        dateTextView = findViewById(R.id.date_text_view);
//        notesTextView = findViewById(R.id.notes_text_view);
        speedDial = findViewById(R.id.note_fab);

        //Getting the intent sent
        Intent noteValueIntent = getIntent();
        final String level = noteValueIntent.getStringExtra("Level");
        final String subject = noteValueIntent.getStringExtra("Subject");
        final String mTopic = noteValueIntent.getStringExtra("Topic");

        //set the action bar with topic name


        mD = FirebaseDatabase.getInstance();

        mDatabase=mD.getReference().child(subject).child(level+subject).child(mTopic);
        mDatabase.keepSynced(true);
        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                new LoadPdf().execute(dataSnapshot.getValue().toString());

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


        //Text to speech for the notes
        textToSpeech  = new TextToSpeech(NoteViewer.this, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if (status == TextToSpeech.SUCCESS){
                    result = textToSpeech.setLanguage(Locale.UK);
                }else {
                    Toast.makeText(NoteViewer.this, "Feature not supported on your device", Toast.LENGTH_LONG).show();
                }
            }
        });


        //Setting the date today.
        String date = DateFormat.getDateTimeInstance().format(new Date());
        dateTextView.setText(date);

        /*//to use a seeek bar to change the text size of the text in Textview;
        //use seekbar like this;
        SeekBar seekbar = findViewById();
        seekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean b) {
                //call the textView and set the textSizre to this;
                //dateTextView.setTextSize(progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });*/

        speedDial.setMenuListener(new FabSpeedDial.MenuListener() {
            @Override
            public boolean onPrepareMenu(NavigationMenu navigationMenu) {
                return true;
            }

            @Override
            public boolean onMenuItemSelected(MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.ask_teacher:
                        startActivity(new Intent(getApplicationContext(), AskTeacher.class));
                        return true;

                    case R.id.assignment:
                        Toast.makeText(getApplication(), menuItem.getTitle(), Toast.LENGTH_LONG).show();
                        return true;
                }

                return true;
            }

            @Override
            public void onMenuClosed() {

            }
        });


    }

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.speech_menu, menu);

        return true;


    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.speak:
                if (result == TextToSpeech.LANG_MISSING_DATA||result== TextToSpeech.LANG_NOT_SUPPORTED){
                    Toast.makeText(NoteViewer.this, "Feature not Supported", Toast.LENGTH_LONG).show();
                }else {
                    textToSpeech.speak(content, TextToSpeech.QUEUE_FLUSH, null);
                }

                break;
            case R.id.stop_reading:
                if (textToSpeech!=null){
                    textToSpeech.stop();
                }

                break;
            case R.id.dictionary:
                Intent intent = new Intent(NoteViewer.this, WordSearch.class);
                startActivity(intent);

                break;
            case R.id.kamusi:
                Intent kIntent = new Intent(NoteViewer.this, WordSearch.class);
                startActivity(kIntent);

                break;


        }
        return super.onOptionsItemSelected(item);


    }
    public  void display(InputStream is){
        //Toast.makeText(getApplicationContext(), "Finished loading", Toast.LENGTH_LONG).show();
        myView.fromStream(is).load();
        view.setVisibility(View.GONE);
        myView.setVisibility(View.VISIBLE);
        layout.setVisibility(View.GONE);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (textToSpeech!= null){
            textToSpeech.stop();
            textToSpeech.shutdown();
        }
    }
    class LoadPdf extends AsyncTask<String,Void,InputStream>{


        @Override
        protected InputStream doInBackground(String... strings) {


            return download(strings[0]);
        }
        public void onPostExecute(InputStream inputStream){
            display(inputStream);

        }
        private InputStream download(String url) {
            InputStream is = null;
            try{
            URL url1 = new URL(url);
            HttpsURLConnection conn = (HttpsURLConnection)url1.openConnection();
            conn.setDoInput(true);
            conn.setConnectTimeout(15000);
            conn.setReadTimeout(15000);
            conn.connect();
            is=conn.getInputStream();

        }catch(MalformedURLException e){

        }catch (IOException e){


            }

            return  is;
        }

    }

}
