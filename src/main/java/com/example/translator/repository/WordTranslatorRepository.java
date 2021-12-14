package com.example.translator.repository;

import com.example.translator.model.Defenition;
import com.example.translator.model.Word;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.google.gson.Gson;
import org.springframework.web.bind.annotation.RequestBody;

import java.awt.*;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

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

    public boolean removeDefinition(String word, String language, Defenition definition) {
        String fileName = "src/main/resources/translations/" + language + "/" + word + ".json";
        try {
            Reader reader = Files.newBufferedReader(Paths.get(fileName));
            Word wordModel = gson.fromJson(reader, Word.class);
            reader.close();
            wordModel.definitions.remove(definition);
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

    public boolean RemoveDef(String word, String language, Defenition definition) {
        String fileName = "src/main/resources/translations/" + language + "/" + word + ".json";
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode jsonNode = objectMapper.readTree(new File(fileName));
            for (JsonNode node : jsonNode) {
                ((ObjectNode) node).remove("dictType");
            }
            objectMapper.writeValue(new File(fileName), jsonNode);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public String translateSentence(String sentence, String fromLanguage, String toLanguage) {
        String fileName_from = "src/main/resources/translations/" + fromLanguage + "/cat.json";
        String fileName_to = "src/main/resources/translations/" + toLanguage + "/pisica.json";

        try {
            Reader reader = Files.newBufferedReader(Paths.get(fileName_to));
            Word wordModel = gson.fromJson(reader, Word.class);
            reader.close();
            return wordModel.toString();
        } catch (Exception e) {
            return "word not found";
        }
    }
}
