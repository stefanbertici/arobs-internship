package com.arobs.internship.repository;

import java.util.HashMap;
import java.util.Map;

public class InMemoryPeopleRepository {
    private Map<String, Integer> storage;

    public InMemoryPeopleRepository() {
        storage = new HashMap<>();
    }

    public void add(String name) {
        if (storage.containsKey(name)) {
            storage.put(name, storage.get(name) + 1);
        } else {
            storage.put(name, 1);
        }
    }

    public Map<String, Integer> getAll() {
        return storage;
    }
}
