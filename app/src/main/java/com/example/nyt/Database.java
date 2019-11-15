package com.example.nyt; // <============= CHANGE ME

import com.example.nyt.model.Cat;

import java.util.HashMap;
import java.util.List;

public class Database {
    public static HashMap<String, Cat> cats = new HashMap<>();
    public static HashMap<String , CatImage> catpics = new HashMap<>();

    public static Cat getCatByID(String CatId) {
        return cats.get(CatId);
    }
    public static CatImage getCatByImageID(String imageId) { return catpics.get(imageId); }

    public static List<Cat> getAllArticles() {
        return (List) cats.values();
    }
    public static List<CatImage> getAllimages() { return  (List) catpics.values(); }


    public static void saveArticlesToFakeDatabase(List<Cat> catsToSave) {
        for(int i = 0; i < catsToSave.size(); i++) {
            Cat cat = catsToSave.get(i);
            cats.put(cat.getId(), cat);
        }
    }

    public static void saveImagesToDatabase(List<CatImage> imagesToSave) {
        for (int i = 0; i < imagesToSave.size(); i++) {
            CatImage catImage = imagesToSave.get(i);
            catpics.put(catImage.getId(), catImage);
        }
    }



}
