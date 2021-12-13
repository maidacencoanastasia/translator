package com.example.translator.model;

import com.google.gson.Gson;

import java.util.ArrayList;

public class Word {
    String word;
    String word_en;
    String type;
    ArrayList<String> singular;
    ArrayList<String> plural;
    ArrayList<Defenition> definitions;

    public Word() {
    }

    public String toString() {
        Gson gson = new Gson();
        return gson.toJson(this);
    }
}
