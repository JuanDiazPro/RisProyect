package com.example.RisProyect.utils;

public class Message {
    private String text;
    private com.example.practica_completa.utils.TypesResponse type;
    private Object result;

    public Message(String text, com.example.practica_completa.utils.TypesResponse type) {
        this.text = text;
        this.type = type;
    }

    public Message(Object result,String text, com.example.practica_completa.utils.TypesResponse type) {
        this.text = text;
        this.type = type;
        this.result = result;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public com.example.practica_completa.utils.TypesResponse getType() {
        return type;
    }

    public void setType(com.example.practica_completa.utils.TypesResponse type) {
        this.type = type;
    }

    public Object getResult() {
        return result;
    }

    public void setResult(Object result) {
        this.result = result;
    }
}