package com.example.nyt.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.nyt.CatAdapter;
import com.example.nyt.Database;
import com.example.nyt.activities.MainActivity;
import com.example.nyt.R;
import com.example.nyt.model.Cat;
import com.google.gson.Gson;

import java.util.Arrays;
import java.util.List;


public class SearchFragment extends Fragment {

    private RecyclerView recyclerView;
    public EditText query;
    public Button search_button;

    public SearchFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View searchView = inflater.inflate(R.layout.fragment_search, container, false);
        recyclerView = searchView.findViewById(R.id.recycler_searchpage);
        query = searchView.findViewById(R.id.searchBar);
        search_button = searchView.findViewById(R.id.searchButton);
        LinearLayoutManager layoutManager = new LinearLayoutManager(searchView.getContext());
        recyclerView.setLayoutManager(layoutManager);
        // REFER to the comments in BookRecyclerAdapter

        final CatAdapter catAdapter = new CatAdapter();
        RequestQueue queue = Volley.newRequestQueue(getContext());
        String url = "https://api.thecatapi.com/v1/breeds";
        System.out.println("Breeds");



        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Gson gson = new Gson();
                        Cat[] cat = gson.fromJson(response, Cat[].class);
                        List<Cat> catCat = Arrays.asList(cat);
                        catAdapter.setData(catCat);
                        recyclerView.setAdapter(catAdapter);
                        Database.saveArticlesToFakeDatabase(catCat);
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

        search_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                newSearchActivity();
            }
        });
        return searchView;
    }

    @Override
    public void onResume() {
        super.onResume();
        MainActivity parent = (MainActivity) getActivity();
        parent.showCoolMessage("cool (from SearchFragment onResume)");
    }

    public void newSearchActivity() {
        final CatAdapter catAdapter = new CatAdapter();
        RequestQueue queue = Volley.newRequestQueue(getContext());
        String addQ = query.getText().toString();
        String newUrl = "https://api.thecatapi.com/v1/breeds/search?q=" + addQ;
        System.out.println(addQ);

        StringRequest stringRequest = new StringRequest(Request.Method.GET, newUrl,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Gson gson = new Gson();
                        Cat[] cat = gson.fromJson(response, Cat[].class);
                        List<Cat> catCat = Arrays.asList(cat);
                        catAdapter.setData(catCat);
                        recyclerView.setAdapter(catAdapter);
                        // We have reworked Database to act as a place to store these Articles, such that we
                        // can access them via their ID. This will allow our intents to the DetailView to keep
                        // functioning.
                        Database.saveArticlesToFakeDatabase(catCat);
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
    }


}
