package com.arobs.internship.lab6.Ex4;

public class Word {
    private String name;

    public Word(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Word word = (Word) o;

        return name.equals(word.name);
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }

    @Override
    public String toString() {
        return "Word{" +
                "name='" + name + '\'' +
                '}';
    }
}
