package com.example.nyt.fragments;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.nyt.R;
import com.example.nyt.activities.MainActivity;


public class FavouritesFragment extends Fragment {



    private OnFragmentInteractionListener catListener;
    public TextView cat_textView;
    public FavouritesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View favouritesView = inflater.inflate(R.layout.fragment_favourites, container, false);
        cat_textView = favouritesView.findViewById(R.id.addToFavourite);

        for (int o = 0; o < MainActivity.myFavourites.size(); o++) {
            cat_textView.setText(cat_textView.getText() + " " + MainActivity.myFavourites.get(o) + " , "); }
        return favouritesView;

    }


    public void onButtonPressed(String string) {
        if (catListener != null) {
            catListener.onFragmentInteraction(string);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            catListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        catListener = null;
    }

    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(String string);
    }
}
