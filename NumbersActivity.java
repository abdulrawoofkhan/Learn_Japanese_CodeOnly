package com.example.android.learnjapanese;

import androidx.appcompat.app.AppCompatActivity;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.widget.ListView;
import android.view.View;
import android.widget.AdapterView;
import java.util.ArrayList;

public class NumbersActivity extends AppCompatActivity {
    private MediaPlayer mMediaPlayer;
    private MediaPlayer.OnCompletionListener mCompletionListener = new MediaPlayer.OnCompletionListener() {
        @Override
        public void onCompletion(MediaPlayer mediaPlayer) {
            releaseMediaPlayer();
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_numbers);

        final ArrayList<Word> words = new ArrayList<Word>();
        words.add(new Word("One", "ichi", R.mipmap.number_one, R.raw.number_one));
        words.add(new Word("Two", "ni",R.mipmap.number_two, R.raw.number_two));
        words.add(new Word("Three", "san",R.mipmap.number_three, R.raw.number_three));
        words.add(new Word("Four", "shi",R.mipmap.number_four, R.raw.number_four));
        words.add(new Word("Five", "go",R.mipmap.number_five, R.raw.number_five));
        words.add(new Word("Six", "roku",R.mipmap.number_six, R.raw.number_six));
        words.add(new Word("Seven", "nana",R.mipmap.number_seven, R.raw.number_seven));
        words.add(new Word("Eight", "hachi",R.mipmap.number_eight, R.raw.number_eight));
        words.add(new Word("Nine", "kyū",R.mipmap.number_nine, R.raw.number_nine));
        words.add(new Word("Ten", "juu",R.mipmap.number_ten, R.raw.number_ten));


        WordAdapter adapter = new WordAdapter(this,words);

        ListView listView = (ListView) findViewById(R.id.list);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override

            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                releaseMediaPlayer();                  //prev sound must stop playing, so that 2 sounds don't play at a time
                Word word = words.get(position);

                mMediaPlayer = MediaPlayer.create(NumbersActivity.this, word.getAudioResourceId());
                mMediaPlayer.start();
                mMediaPlayer.setOnCompletionListener(mCompletionListener);

            }
        });
        }

    @Override
    protected void onStop() {               //sound must stop immediately when home button is pressed
        super.onStop();
        releaseMediaPlayer();
    }

    private void releaseMediaPlayer() {
        // If the media player is not null, then it may be currently playing a sound.
        if (mMediaPlayer != null) {
            mMediaPlayer.release();
            mMediaPlayer = null;
        }
    }
    }