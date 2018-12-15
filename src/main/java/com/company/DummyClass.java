package com.company;

import static java.lang.Math.abs;
import com.company.FieldAnnotations.*;


public class DummyClass {
    @HackerAttack
    private String title;

    @HackerAttack
    private int ttl;

    @HackerAttack
    private int key;

    private int someField;

    public DummyClass(String title, int ttl, int key, int someField) {
        this.title = title;
        this.ttl = ttl;
        this.key = key;
        this.someField = someField;
    }

    public int getTtl() {
        return ttl;
    }

    public int getKey() {
        return key;
    }

    public int getSomeField() {
        return someField;
    }

    public void setTtl(int ttl) {
        this.ttl = ttl;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
