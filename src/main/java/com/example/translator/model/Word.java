package com.example.translator.model;

import com.google.gson.Gson;

import java.util.ArrayList;

public class Word {
    public String word;
    public String word_en;
    public String type;
    public ArrayList<String> singular;
    public ArrayList<String> plural;
    public ArrayList<Defenition> definitions;

    @Override
    public String toString() {
        Gson gson = new Gson();
        return gson.toJson(this);
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public String getWord_en() {
        return word_en;
    }

    public void setWord_en(String word_en) {
        this.word_en = word_en;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public ArrayList<String> getSingular() {
        return singular;
    }

    public void setSingular(ArrayList<String> singular) {
        this.singular = singular;
    }

    public ArrayList<String> getPlural() {
        return plural;
    }

    public void setPlural(ArrayList<String> plural) {
        this.plural = plural;
    }

    public ArrayList<Defenition> getDefinitions() {
        return definitions;
    }

    public void setDefinitions(ArrayList<Defenition> definitions) {
        this.definitions = definitions;
    }
}
