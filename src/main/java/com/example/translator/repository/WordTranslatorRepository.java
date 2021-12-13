package com.example.translator.repository;

import com.example.translator.model.Defenition;
import com.example.translator.model.Word;
import com.google.gson.Gson;

import java.awt.*;
import java.io.File;
import java.io.FileWriter;
import java.io.Reader;
import java.io.Writer;
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
//        try {
//            //constructor of file class having file as argument
//            File file = new File(fileName);
//            if (!Desktop.isDesktopSupported())
//            //check if Desktop is supported by Platform or not
//            {
//                System.out.println("not supported");
//                //return true;
//            }
//            Desktop desktop = Desktop.getDesktop();
//            if (file.exists()) //checks file exists or not
//                file.delete(); //desktop.open(file); //opens the specified file
//            return true;
////            file.delete();
//        } catch (Exception e) {
//            System.out.println("not such file");
//            e.printStackTrace();
//        }
//    return false;
//    }
    //////////////////
//        try {
//            File file = new File(fileName);
//
//            boolean flag = file.createNewFile();
//            if (flag) {
//                System.out.println("File has been created successfully at the specified location");
//            }
//            else {
//                System.out.println("File already present at the specified location");
//            }
//
//            file.delete();//  метод работает не корректно, но удаление выполняет
//            return true;
//        } catch (Exception e) {
//            return false;
//        }
//    }

    //    public boolean addDefinitionForWord(String word, String language, Defenition definition) {
//        String fileName = "src/main/resources/translations/" + language + "/" + word + ".json";
//        try {
//            Reader reader = Files.newBufferedReader(Paths.get(fileName));
//            Word wordModel = gson.fromJson(reader, Word.class);
//            reader.close();
//            wordModel.definitions.add(definition);//to_do
//            try {
//                Writer writer = new FileWriter(fileName);
//                gson.toJson(wordModel, writer);
//                writer.close();
//            } catch (Exception e) {
//                return false;
//            }
//            return true;
//        } catch (Exception e) {
//            return false;
//        }
//
//    }
    public boolean addDefinitionForWord(String word, String language, Defenition definition){
        String fileName = "src/main/resources/translations/" + language + "/" + word + ".json";
        try {
            Reader reader = Files.newBufferedReader(Paths.get(fileName));
            Word wordModel = gson.fromJson(reader, Word.class);
            reader.close();
//            assert wordModel != null;
//           wordModel.definitions.add(definition);
//            wordModel.setDefinitions(definition);
            try {
                Writer writer = new FileWriter(fileName);
                gson.toJson(wordModel, writer);
                wordModel.definitions.add(definition);
                //wordModel.setDefinitions(definition);
                writer.close();
            } catch (Exception e) {
                return false;
            }
            return true;
        } catch (Exception exception) {
            return false;
        }
    }

}
