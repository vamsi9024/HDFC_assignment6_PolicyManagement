package com.hdfc.policy.repository;

import java.util.*;

public class Repository <T ,ID> {

    private Map<ID , T> storage = new HashMap<ID, T>();

    public Map<ID, T> getStorage() {
        return storage;
    }

    public void setStorage(Map<ID, T> storage) {
        this.storage = storage;
    }

    public void add (ID id , T object){
        storage.put(id, object);
    }

    public T get(ID id) {
        return storage.get(id);
    }

    public T remove(ID id) {
        return storage.remove(id);
    }

    public List<T> listAll() {
        return new ArrayList<>(storage.values());
    }

    public boolean contains(ID id) {
        return storage.containsKey(id);
    }

    public Map<ID, T> asMap() {
        return Collections.unmodifiableMap(storage);
    }
}
