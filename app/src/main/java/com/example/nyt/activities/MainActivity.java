package com.example.nyt.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.nyt.R;
import com.example.nyt.fragments.HomeFragment;
import com.example.nyt.fragments.SearchFragment;
import com.example.nyt.fragments.FavouritesFragment;
import com.example.nyt.model.Favourite;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

// Notice the Activity implements the interface OnFragmentInteractionListener, meaning this Activity
// MUST have the method defined by the interface (see bottom), and if it does then this Activity
// can be considered an OnFragmentInteractionListener; it listens for Fragment interaction.
// This is only relevant to FavouritesFragment in this app (because ArticleRecyclerFragment has nothing
// to listen to).
public class MainActivity extends AppCompatActivity implements FavouritesFragment.OnFragmentInteractionListener {

    BottomNavigationView bottomNavigationView;
    public static ArrayList<Favourite> myFavourites;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myFavourites = new ArrayList<Favourite>();

        Fragment fragment = new HomeFragment();
        swapFragment(fragment);

        bottomNavigationView = findViewById(R.id.nav_view);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

                if (menuItem.getItemId() == R.id.navigation_home) {
                    Fragment fragment = new HomeFragment();
                    swapFragment(fragment);
                    return true;

                } else if (menuItem.getItemId() == R.id.navigation_favourites) {
                    Fragment fragment = new FavouritesFragment();
                    swapFragment(fragment);
                    return true;
                } else if (menuItem.getItemId() == R.id.navigation_search) {
                    Fragment fragment = new SearchFragment();
                    swapFragment(fragment);
                    return true;
                }
                return false;
            }
        });

    }

    private void swapFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame_mainActivity, fragment);
        fragmentTransaction.commit();
    }

    @Override
    public void onFragmentInteraction(String string) {
        Toast.makeText(this, "Hello! I have come from the fragment. Message: " + string, Toast.LENGTH_SHORT).show();
    }

    public void showCoolMessage(String string) {
        Toast.makeText(this, "Search For a Cat!", Toast.LENGTH_SHORT).show();


    }

}