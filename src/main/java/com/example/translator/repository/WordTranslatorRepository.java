package com.example.translator.repository;

import com.example.translator.model.Defenition;
import com.example.translator.model.Word;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.google.gson.Gson;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.awt.*;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;

public class WordTranslatorRepository {
    private Gson gson = new Gson();

    public String translateWord(String word, String language) {
        String fileName = "src/main/resources/translations/" + language + "/" + word + ".json";
        try {
            Reader reader = Files.newBufferedReader(Paths.get(fileName));
            Word wordModel = gson.fromJson(reader, Word.class);
            reader.close();
            return wordModel.toString();
        } catch (Exception e) {
            return "word not found";
        }
    }

    public boolean addWord(Word word, String language) {
        String fileName = "src/main/resources/translations/" + language + "/" + word.word + ".json";
        try {
            Writer writer = new FileWriter(fileName);
            gson.toJson(word, writer);
            writer.close();
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    public boolean deleteWord(String word, String language) {
        String fileName = "src/main/resources/translations/" + language + "/" + word + ".json";

        try {
            File f = new File(fileName); //file to be delete
            if (f.delete()) //returns Boolean value
            {
                System.out.println("File " + f.getName() + " is deleted");
                return true;
                //getting and printing the file name
            } else {
                System.out.println("Delete operation failed");
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean addDefinitionForWord(String word, String language, Defenition definition) {
        String fileName = "src/main/resources/translations/" + language + "/" + word + ".json";
        try {
            Reader reader = Files.newBufferedReader(Paths.get(fileName));
            Word wordModel = gson.fromJson(reader, Word.class);
            reader.close();
            wordModel.definitions.add(definition);
            try {
                Writer writer = new FileWriter(fileName);
                gson.toJson(wordModel, writer);
                writer.close();
            } catch (Exception e) {
                return false;
            }
            return true;
        } catch (Exception e) {
            return false;
        }

    }

    public boolean removeDefinition(String word, String language, String dictionary) {
        String fileName = "src/main/resources/translations/" + language + "/" + word + ".json";
        ArrayList<Defenition> defenition;
        try {
            Reader reader = Files.newBufferedReader(Paths.get(fileName));
            Word wordModel = gson.fromJson(reader, Word.class);
            reader.close();
            defenition = wordModel.getDefinitions();
            if (defenition.contains(dictionary))
            wordModel.definitions.remove(dictionary);
            try {
                Writer writer = new FileWriter(fileName);
                gson.toJson(wordModel, writer);
                writer.close();
            } catch (Exception e) {
                return false;
            }
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean RemoveDef(String word, String language, String dictionary) {
        String fileName = "src/main/resources/translations/" + language + "/" + word + ".json";
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode jsonNode = objectMapper.readTree(new File(fileName));
            for (JsonNode node : jsonNode) {
                ((ObjectNode) node).remove(dictionary);//dictionary "dictType"
            }
            objectMapper.writeValue(new File(fileName), jsonNode);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public String translateSentence(String sentence, String fromLanguage, String toLanguage) {
//        String fileName_from = "src/main/resources/translations/" + fromLanguage + "/cat.json";
//        String fileName_to = "src/main/resources/translations/" + toLanguage + "/pisica.json";
        sentence = sentence.toLowerCase();
        String[] words = sentence.split("(?<=[a-z])\\\\.\\\\s+");
        for (String el : words) {
            System.out.println("word - " + el);
            String fileName_temp = "src/main/resources/translations/" + fromLanguage + "/" + el + ".json";
            try {
                Reader reader = Files.newBufferedReader(Paths.get(fileName_temp));
                Word wordModel = gson.fromJson(reader, Word.class);
                reader.close();
                return wordModel.toString();
            } catch (Exception e) {
                return "word not found";
            }
        }
        return "Something was happen";
    }

    public ArrayList<Defenition> getDefinitionsForWord(String word, String language) {
        String fileName = "src/main/resources/translations/" + language + "/" + word + ".json";
        ArrayList<Defenition> defenitionArrayList = null;
        int an = 0;
        int[] dates = new int[10];
        int[] dates_sorted = new int[10];
        try {
            Reader reader = Files.newBufferedReader(Paths.get(fileName));
            Word wordModel = gson.fromJson(reader, Word.class);
            reader.close();
            defenitionArrayList = wordModel.getDefinitions();
            try {
                if (defenitionArrayList != null) {
                    for (Defenition el : defenitionArrayList) {
                        dates[an] = el.getYear();
                        an++;
                        Arrays.stream(dates).sorted();
                    }
                    Writer writer = new FileWriter(fileName);
                    gson.toJson(wordModel, writer);
                    writer.close();
                }
            } catch (Exception e) {
                return defenitionArrayList;

            }
            return defenitionArrayList;

        } catch (Exception e) {
            return defenitionArrayList;
        }

    }
}
