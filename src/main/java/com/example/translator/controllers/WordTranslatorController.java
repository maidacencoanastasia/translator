package com.example.translator.controllers;

import com.example.translator.model.Defenition;
import com.example.translator.model.Word;
import com.example.translator.repository.WordTranslatorRepository;
import org.springframework.web.bind.annotation.*;


@RestController
public class WordTranslatorController {

    private WordTranslatorRepository wordTranslatorRepository = new WordTranslatorRepository();

    @GetMapping(path = "translate/word/{language}/{word}")
    public String translateWord(@PathVariable String word, @PathVariable String language) {
        return wordTranslatorRepository.translateWord(word, language);
    }

    @PostMapping(path = "translate/word/{language}")
    public boolean addWord(@RequestBody Word word, @PathVariable String language) {
        return wordTranslatorRepository.addWord(word, language);
    }

    @DeleteMapping(path = "translate/word/{language}/{word}")
    public boolean deleteWord(@PathVariable String word, @PathVariable String language) {
        return wordTranslatorRepository.deleteWord(word, language);
    }

    @PostMapping(path = "translate/word/{language}/{word}")
    public boolean addDefinitionForWord(@PathVariable String word, @PathVariable String language, @RequestBody Defenition definition) {
        return wordTranslatorRepository.addDefinitionForWord(word, language, definition);
    }

    //"dictType" = dictionary: "definitions"
    @DeleteMapping(path = "translate/word/{language}/{word}/{dictionary}")
    public boolean removeDefinition(@PathVariable String word, @PathVariable String language, @PathVariable String dictionary, @RequestBody Defenition definition) {
        return wordTranslatorRepository.removeDefinition(word, language, definition);
    }

    @GetMapping(path = "translate/sentences/{fromLanguage}/{toLanguage}/{sentence}")
    String translateSentence(@PathVariable String sentence, @PathVariable String fromLanguage, @PathVariable String toLanguage) {
        return wordTranslatorRepository.translateSentence(fromLanguage, toLanguage, sentence);
    }
}