package com.example.translator.model;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.function.IntFunction;

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

    public String getDict() {
        return dict;
    }

    public void setDict(String dict) {
        this.dict = dict;
    }

    public String getDictType() {
        return dictType;
    }

    public void setDictType(String dictType) {
        this.dictType = dictType;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public ArrayList<String> getText() {
        return text;
    }

    public void setText(ArrayList<String> text) {
        this.text = text;
    }

//    @Override
//    public <T> T[] toArray(IntFunction<T[]> generator) {
//        return super.toArray(generator);
//    }
}
