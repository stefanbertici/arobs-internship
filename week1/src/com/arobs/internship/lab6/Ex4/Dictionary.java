package com.arobs.internship.lab6.Ex4;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Dictionary {
    Map<Word, Definition> dictionary;

    public Dictionary(Map<Word, Definition> dictionary) {
        this.dictionary = dictionary;
    }

    public void addWord(Word word, Definition definition) {
        dictionary.put(word, definition);
    }

    public Definition getDefinition(Word word) {
        return dictionary.get(word);
    }

    public List<Word> getAllWords() {
        List<Word> words = new ArrayList<>(dictionary.keySet());
        return words;
    }

    public List<Definition> getAllDefinitions() {
        List<Definition> definitions = new ArrayList<>(dictionary.values());
        return definitions;
    }
}
