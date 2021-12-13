package com.example.translator.model;

import com.google.gson.Gson;

import java.util.ArrayList;

public class Defenition {
    String dict;
    String dictType;
    Integer year;
    ArrayList<String> text;

    public Defenition() {
    }

    public String toString() {
        Gson gson = new Gson();
        return gson.toJson(this);
    }
}
