package com.example.android.learnjapanese;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;



import android.media.MediaPlayer;
import android.view.View;
import android.widget.AdapterView;

public class PhrasesActivity extends AppCompatActivity {
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
        setContentView(R.layout.activity_phrases);

        final ArrayList<Word> words = new ArrayList<Word>();
        words.add(new Word("What is your name?", "O-namae wa nan desu ka?",R.raw.name));
        words.add(new Word("My name is", "Namae wa",R.raw.my_name));
        words.add(new Word("Good Morning", "Ohayou",R.raw.gm));
        words.add(new Word("How are you?", "Ogenkidesuka",R.raw.how));
        words.add(new Word("Good evening", "Konbanwa",R.raw.ge));
        words.add(new Word("Thanks", "Arigatō",R.raw.thanks));
        words.add(new Word("Sorry", "Gomen'nasai",R.raw.sorry));
        words.add(new Word("Please", "kudasai",R.raw.please));
        words.add(new Word("I am fine", "Watashi wa genkidesu",R.raw.fine));
        words.add(new Word("I am hungry", "Onaka ga akimashita",R.raw.hungry));
        words.add(new Word("Goodbye", "Sayōnara",R.raw.bye));



//      List view is used to reduce memory usage
        WordAdapter adapter = new WordAdapter(this,words);

        ListView listView = (ListView) findViewById(R.id.list);
        listView.setAdapter(adapter);


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                releaseMediaPlayer();                  //prev sound must stop playing, so that 2 sounds don't play at a time
                Word word= words.get(position);
                mMediaPlayer= MediaPlayer.create(PhrasesActivity.this, word.getAudioResourceId());
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