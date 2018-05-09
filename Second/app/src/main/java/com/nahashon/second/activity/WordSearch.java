package com.nahashon.second.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;

import com.nahashon.second.R;

public class WordSearch extends AppCompatActivity {
    WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_word_search);

        webView  = findViewById(R.id.web_view);
        WebSettings webSettings = webView.getSettings();
        //webSettings.setJavaScriptEnabled(true);
        webView.loadUrl("https://www.pu.ac.ke");

    }
}
