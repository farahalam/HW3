package com.example.nyt;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.nyt.activities.DescriptionActivity;
import com.example.nyt.model.Cat;

import java.util.ArrayList;
import java.util.List;

// We need to give a type in angle brackets <> when we extend RecyclerView.Adapter
// It's saying that we want an adapter that adapts to CatViewHolder (our custom ViewHolder)
public class CatAdapter extends RecyclerView.Adapter<CatAdapter.CatViewHolder> {
    // class variable that holds the data that we want to adapt
    private List<Cat> catsToAdapt;
    private List<Cat> results;

    private List<CatImage> imagesToAdapt;
    private List<CatImage> resultss;

    public void setData(List<Cat> articlesToAdapt) {
        // This is basically a Setter that we use to give data to the adapter
        this.catsToAdapt = articlesToAdapt;
        results = new ArrayList<>(articlesToAdapt);
    }

    public void setData1(List<CatImage> imagesToAdapt) {
        // This is basically a Setter that we use to give data to the adapter
        this.imagesToAdapt = imagesToAdapt;
        resultss = new ArrayList<>(imagesToAdapt);
    }

    @NonNull
    @Override
    public CatViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // First create a View from the layout file. It'll probably be a ViewGroup like
        // ConstraintLayout that contains more Views inside it.
        // This view now represents your entire one item.
        View view =
                LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.cat, parent, false);

        // Then create an instance of your custom ViewHolder with the View you got from inflating
        // the layout.
        CatViewHolder catViewHolder = new CatViewHolder(view);
        return catViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull CatViewHolder holder, int position) {
        final Cat catAtPosition = catsToAdapt.get(position);

        holder.cat_textView.setText(catAtPosition.getName());
        holder.description_textView.setText(catAtPosition.getOrigin());


        holder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Context context = view.getContext();

                Intent intent = new Intent(context, DescriptionActivity.class);
                intent.putExtra("ArticleID", catAtPosition.getId()); //FIX ARTICLEID SOMEHOW
                context.startActivity(intent);
            }
        });


    }

    @Override
    public int getItemCount() {
        return catsToAdapt.size();
    }

    //Place everything that was used in cat.xml
    public static class CatViewHolder extends RecyclerView.ViewHolder {
        public View view;
        public TextView cat_textView;
        public TextView description_textView;
        public ImageView shareImageView;
        public ImageView bookmarkImageView;
        public ImageView articleImageView;
        public boolean isBookmarked = false;

        public CatViewHolder(View v) {
            super(v);
            view = v;
            cat_textView = v.findViewById(R.id.newsHeadline);
            description_textView = v.findViewById(R.id.newsDetails);
            shareImageView = v.findViewById(R.id.newsShareButton);
            bookmarkImageView = v.findViewById(R.id.newsSaveButton);

            bookmarkImageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(isBookmarked) {
                        bookmarkImageView.setImageResource(R.drawable.ic_bookmark_border_black_24dp);
                    } else {
                        bookmarkImageView.setImageResource(R.drawable.ic_bookmark_black_24dp);
                    }
                    isBookmarked = !isBookmarked;
                }
            });

        }
    }


}
