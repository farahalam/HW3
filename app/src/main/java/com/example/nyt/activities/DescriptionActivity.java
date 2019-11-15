package com.example.nyt.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.webkit.URLUtil;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.nyt.CatAdapter;
import com.example.nyt.CatImage;
import com.example.nyt.Database;
import com.example.nyt.R;
import com.example.nyt.model.Cat;
import com.example.nyt.model.Favourite;
import com.google.gson.Gson;

import java.io.IOException;
import java.io.InputStream;
import java.security.AccessControlContext;
import java.util.Arrays;
import java.util.List;



public class DescriptionActivity extends AppCompatActivity {
    private TextView cat_textView;
    private TextView origin_textView;
    private TextView temperament_textView;
    private TextView life_span;
    private TextView dog_friendliness;
    private TextView wiki_url;
    private TextView breed;
    private TextView altnames_textView;
    private TextView weight_imperial;
    private TextView hairless;
    private TextView hypoallergenic;

    //private TextView test;
    private Button add_to_favourites;


    //private ImageView cat_image;

    private static Context ctx;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_description);

        Intent intent = getIntent();

        String articleID = intent.getStringExtra("ArticleID"); //FIX ARTICLEID

        Cat cat = Database.getCatByID(articleID);
        final CatImage catImage = Database.getCatByImageID(articleID);

        final CatAdapter catAdapter1 = new CatAdapter();
        RequestQueue queue = Volley.newRequestQueue(this);
        String url = "https://api.thecatapi.com/v1/images/search?";
        System.out.println("Images");



        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Gson gson = new Gson();
                        CatImage[] catImages = gson.fromJson(response, CatImage[].class);
                        List<CatImage> cat_image = Arrays.asList(catImages);
                        catAdapter1.setData1(cat_image);
                        Database.saveImagesToDatabase(cat_image);
                        System.out.println("this is the onresponse");
                    }


                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
                System.out.println("Error");
            }
        });
        queue.add(stringRequest);

        cat_textView = findViewById(R.id.name);
        origin_textView = findViewById(R.id.origin);
        temperament_textView = findViewById(R.id.temperament);
        life_span = findViewById(R.id.lifeSpan);
        dog_friendliness = findViewById(R.id.dog_friendly);
        wiki_url = findViewById(R.id.wiki_url);
        breed = findViewById(R.id.idofbreed);
        add_to_favourites = findViewById(R.id.addToFavourite);
        altnames_textView = findViewById(R.id.altnames);
        weight_imperial = findViewById(R.id.weight);
        hairless = findViewById(R.id.hairless);
        hypoallergenic = findViewById(R.id.hypoallergenic);
        //cat_image = findViewById(R.id.catView);
       // test = findViewById(R.id.test);

        cat_textView.setText(cat.getName());
        origin_textView.setText("Origin: " + cat.getOrigin());
        life_span.setText("Life Span: " + cat.getLife_span() + " years");
        dog_friendliness.setText(" Dog Friendly (Out of 5): " + cat.getDog_friendly());
        wiki_url.setText("Wikipedia URL: " + cat.getWikipedia_url());
        breed.setText("ID of Breed: " + cat.getId());
        temperament_textView.setText("Temperament: " + cat.getTemperament());

        //test.setText("The URL is " + catImage.getUrl());

        //get weight if not null
        if (cat.getWeight_imperial() != null) {
            weight_imperial.setText("Weight: " + cat.getWeight_imperial());
        } else {
            weight_imperial.setText("Weight: Not Applicable");
        }
        //alternative names if it exists
        if (cat.getAlt_names() != null && cat.getAlt_names().length() > 2) {
            altnames_textView.setText("Alternative Names: " + cat.getAlt_names());
        } else {
            altnames_textView.setText("Alternative Names: " + cat.getAlt_names() + " None");
        }

        //hairless boolean
        if (cat.getHairless().equals("0")) {
            hairless.setText("Hairless: No");
        } else {
            hairless.setText("Hairless: Yes");
        }

        //hairless boolean
        if (cat.getHypoallergenic().equals("0")) {
            hairless.setText("Hypoallergenic: No");
        } else {
            hairless.setText("Hypoallergenic: Yes");
        }

//
//        String Url = " ";
//        if (catImageAtPosition.getMedia() != null && catImageAtPosition.getMedia().length > 0) {
//            Url = articleID.getMedia()[0].getMedia_metadata()[0].getUrl();
//        }
//        if (Url.isEmpty() == true) {
//            cat_image.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.catcute));
//        } else {
//            new DownloadImageTask((ImageView) findViewById(R.id.catView))
//                    .execute(Url);
//        }

        add_to_favourites.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addToFavourites();
                Toast.makeText(getApplicationContext(),"Cat Breed added to Favourites, return back home",Toast.LENGTH_LONG).show();
            }
        });

    }

    private class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {
        ImageView cat_image;

        public DownloadImageTask(ImageView cat_image) {
            this.cat_image = cat_image;
        }

        @Override
        protected Bitmap doInBackground(String... strings) {
            String urlDisplay = strings[0];
            Bitmap mIcon11 = null;
            try {
                InputStream in = new java.net.URL(urlDisplay).openStream();
                mIcon11 = BitmapFactory.decodeStream(in);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return mIcon11;
        }

        protected void onPostExecute(Bitmap results) {
            cat_image.setImageBitmap(results);
        }
    }

    public void addToFavourites() {

        String name = (String) cat_textView.getText();
        String origin = (String) origin_textView.getText();


        MainActivity.myFavourites.add(new Favourite(name, origin));
        System.out.println("Item added");


    }
}
