package com.example.translator.controllers;

import com.example.translator.model.Word;
import com.example.translator.repository.TranslatorRepository;
import org.springframework.web.bind.annotation.*;

@RestController
public class TranslatorController {
    private TranslatorRepository translatorRepository = new TranslatorRepository();

    public TranslatorController() {
    }

    @GetMapping(
            path = {"translate/word/{language}/{word}"}
    )
    public String translateWord(@PathVariable String language, @PathVariable String word) {
        return this.translatorRepository.translateWord(language, word);
    }

    @PutMapping(
            path = {"translate/word/{language}/{word}"}
    )
    public Word addWord(@RequestBody Word word, @PathVariable String language) {
        return word;
    }
}