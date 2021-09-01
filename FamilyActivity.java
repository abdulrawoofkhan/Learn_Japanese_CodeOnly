package com.example.android.learnjapanese;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;
import android.media.MediaPlayer;
import java.util.ArrayList;
import android.view.View;
import android.widget.AdapterView;
public class FamilyActivity extends AppCompatActivity {
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
        setContentView(R.layout.activity_family);

        final ArrayList<Word> words = new ArrayList<Word>();
        words.add(new Word("Father", "Otōsan",R.mipmap.family_father, R.raw.family_father));
        words.add(new Word("Mother", "Haha",R.mipmap.family_mother, R.raw.family_mother));
        words.add(new Word("Son", "Musuko",R.mipmap.family_son, R.raw.family_son));
        words.add(new Word("Daughter", "Musume",R.mipmap.family_daughter, R.raw.family_daughter));
        words.add(new Word("Elder Sister", "Ane",R.mipmap.family_older_sister, R.raw.family_older_sister));
        words.add(new Word("Younger sister", "Imōto",R.mipmap.family_younger_sister, R.raw.family_younger_sister));
        words.add(new Word("Elder brother", "Ojisan",R.mipmap.family_older_brother, R.raw.family_older_brother));
        words.add(new Word("Younger brother", "Oba",R.mipmap.family_younger_brother, R.raw.family_younger_brother));
        words.add(new Word("Grandfather", "Sofu",R.mipmap.family_grandfather, R.raw.family_grandfather));
        words.add(new Word("Grandmother", "Sobo",R.mipmap.family_grandmother, R.raw.family_grandmother));

        WordAdapter adapter = new WordAdapter(this,words);

        ListView listView = (ListView) findViewById(R.id.list);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                releaseMediaPlayer();                  //prev sound must stop playing, so that 2 sounds don't play at a time

                Word word = words.get(position);

                mMediaPlayer = MediaPlayer.create(FamilyActivity.this, word.getAudioResourceId());

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
        if (mMediaPlayer != null) {
            mMediaPlayer.release();
            mMediaPlayer = null;
        }
    }
}