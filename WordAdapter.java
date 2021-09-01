package com.example.android.learnjapanese;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;


import android.app.Activity;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;


public class WordAdapter extends ArrayAdapter<Word> {
    private int mColorResourceId;
    private static final String LOG_TAG = WordAdapter.class.getSimpleName();


    public WordAdapter(Activity context, ArrayList<Word> words) {
        super(context, 0, words);
    }
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View list_item_view = convertView;
        if (list_item_view==null){
            list_item_view= LayoutInflater.from(getContext()).inflate(R.layout.list_item, parent, false);
        }
        Word currentWord = getItem(position);

        TextView miwak = (TextView) list_item_view.findViewById(R.id.jap_text_view) ;
        miwak.setText(currentWord.getJapTranslation());

        TextView eng = (TextView) list_item_view.findViewById(R.id.default_text_view) ;
        eng.setText(currentWord.getDefaultTranslation());

        ImageView img = (ImageView) list_item_view.findViewById(R.id.imageView);
        if (currentWord.hasImage()){
            img.setImageResource(currentWord.getImageResourceId());
            img.setVisibility(View.VISIBLE);
        }
        else{
            // Otherwise hide the ImageView (set visibility to GONE)
            img.setVisibility(View.GONE);
        }

        return list_item_view;
    }
}
