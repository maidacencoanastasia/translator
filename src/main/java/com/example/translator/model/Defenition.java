package com.example.translator.model;

import com.google.gson.Gson;

import java.util.ArrayList;

public class Defenition {
    public String dict;
    public String dictType;
    public Integer year;
    public ArrayList<String> text;

    @Override
    public String toString() {
        Gson gson = new Gson();
        return gson.toJson(this);
    }
}
