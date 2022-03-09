package com.arobs.internship.lab6.Ex4;

import java.util.HashMap;
import java.util.Map;

public class TestDictionary {
    public static void main(String[] args) {
        Map<Word, Definition> repository = new HashMap<>();
        Dictionary dictionary = new Dictionary(repository);
        TextUI ui = new TextUI(dictionary);

        ui.start();
    }
}
