package com.example.nyt.model;

public class Favourite {

    private String name;
    private String origin;

    public Favourite(String name, String origin) {
        this.name = name;
        this.origin = origin;

    }

    public String getName() {
        return name;
    }

    public String getOrigin() {
        return origin;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    @Override
    public String toString() {
        return "Cat Breed: " + name + "\n" + "\n";
    }
}
