package com.example.android.learnjapanese;

public class Word {

    private String mDefaultTranslation;
    private String mjapTranslation;
    private int mImageResourceId = NO_IMAGE_PROVIDED;
    private int mAudioResourceId;

    /**
     * Constant value that represents no image was provided for this word
     */

         /** audioResourceId is the resource ID for the audio file associated with this word
            */
    private static final int NO_IMAGE_PROVIDED = -1;

    public Word(String defaultTranslation, String japTranslation, int ImageResourceId, int audioResourceId) {
        mDefaultTranslation = defaultTranslation;
        mjapTranslation = japTranslation;
        mImageResourceId = ImageResourceId;
        mAudioResourceId = audioResourceId;

    }

    public Word(String defaultTranslation, String japTranslation,int audioResourceId){
        mDefaultTranslation = defaultTranslation;
        mjapTranslation = japTranslation;
          mAudioResourceId = audioResourceId;
    }

    public String getDefaultTranslation() {
        return mDefaultTranslation;
    }

    public String getJapTranslation() {
        return mjapTranslation;
    }

    public int getImageResourceId() {
        return mImageResourceId;
    }

    public boolean hasImage() {
        return mImageResourceId != NO_IMAGE_PROVIDED;
    }

    public int getAudioResourceId(){
        return mAudioResourceId;
    }
}
