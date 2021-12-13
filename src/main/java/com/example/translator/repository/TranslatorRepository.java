package com.example.translator.repository;

import com.example.translator.model.Word;
import com.google.gson.Gson;

import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;

public class TranslatorRepository {
    public TranslatorRepository() {
    }

    public String translateWord(String language, String word) {
        String fileName = "src/main/resources/translations/" + language +"/"+ word + ".json";

        try {
            Gson gson = new Gson();
            Reader reader = Files.newBufferedReader(Paths.get(fileName));
            Word wordModel = (Word)gson.fromJson(reader, Word.class);
            reader.close();
            return wordModel.toString();
        } catch (Exception var6) {
            return "word not found";
        }
    }
    public  Word addWord(String language,Word word){
        String fileName = "src/main/resources/translations/" + language +"/"+ word + ".json";
        return word;
    }
}

