package com.example.nyt.model; // <============= CHANGE ME

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Cat {

    // We use a long because the number they use for ID is too big for an int
    private String id;
    private String name;
    private String temperament;
    private String life_span;
    private String wikipedia_url;
    private String origin;
    private String weight_imperial;
    private String dog_friendly;
    private String alt_names;
    private String hairless;
    private String hypoallergenic;

    public String getHypoallergenic() { return hypoallergenic; }

    public String getHairless() { return hairless; }

    public String getAlt_names() { return alt_names; }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getTemperament() {
        return temperament;
    }

    public String getLife_span() {
        return life_span;
    }

    public String getWikipedia_url() {
        return wikipedia_url;
    }

    public String getOrigin() {
        return origin;
    }

    public String getWeight_imperial() {
        return weight_imperial;
    }

    public String getDog_friendly() {
        return dog_friendly;
    }


}