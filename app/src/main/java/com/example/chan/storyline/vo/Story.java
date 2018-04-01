package com.example.chan.storyline.vo;

/**
 * Created by chandru on 1/4/18.
 */

public class Story {
    String storyTitle;
    String noOfWords;
    String createdUser;

    public Story(String storyTitle,String createdUser, String noOfWords) {
        this.storyTitle = storyTitle;
        this.noOfWords = noOfWords;
        this.createdUser = createdUser;
    }

    public String getStoryTitle() {
        return storyTitle;
    }

    public void setStoryTitle(String storyTitle) {
        this.storyTitle = storyTitle;
    }

    public String getNoOfWords() {
        return noOfWords;
    }

    public void setNoOfWords(String noOfWords) {
        this.noOfWords = noOfWords;
    }

    public String getCreatedUser() {
        return createdUser;
    }

    public void setCreatedUser(String createdUser) {
        this.createdUser = createdUser;
    }
}
