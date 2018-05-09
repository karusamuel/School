package com.nahashon.second.adapter;

public class Question {

    private String Sender;
    private String Time;
    private String Question;
    private  String Answer="pending";

    public String getAnswer() {
        return Answer;
    }

    public void setAnswer(String answer) {
        Answer = answer;
    }

    public Question(String sender, String time, String question) {
        Sender = sender;
        Time = time;
        Question = question;
    }
    public Question(){

    }

    public String getSender() {
        return Sender;
    }

    public void setSender(String sender) {
        Sender = sender;
    }

    public String getTime() {
        return Time;
    }

    public void setTime(String time) {
        Time = time;
    }

    public String getQuestion() {
        return Question;
    }

    public void setQuestion(String question) {
        Question = question;
    }
}
