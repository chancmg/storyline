package com.example.chan.storyline.Database;

import com.example.chan.storyline.vo.Story;

/**
 * Created by chandru on 1/4/18.
 */

public class StoryDB {

    public static final String STORY_TABLE_NAME = "storyline_story";
    public static final String STORY_ID="story_id";
    public static final String STORY_TITLE="story_title";
    public static final String STORY_CONTENT = "story_content";
    public static final String CREATED_USER="author_name";
    public static final String REMAINING_WORDS="remaining_words";
    public static final String CREATE_TABLE_STORY = "CREATE TABLE "+STORY_TABLE_NAME+"("+STORY_ID+" INTEGER PRIMARY KEY AUTOINCREMENT,"
            +STORY_TITLE+" TEXT,"
            +STORY_CONTENT+" TEXT,"
            +CREATED_USER+" TEXT,"
            +REMAINING_WORDS+" INTEGER)";
    private int id;
    private String storyTitle;
    private String storyContent;
    private String createUser;
    private int remainingWords;



    public StoryDB(){}

    public StoryDB(int id, String storyTitle, String storyContent, String createUser, int remainingWords) {
        this.id = id;
        this.storyTitle = storyTitle;
        this.storyContent = storyContent;
        this.createUser = createUser;
        this.remainingWords = remainingWords;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStoryTitle() {
        return storyTitle;
    }

    public void setStoryTitle(String storyTitle) {
        this.storyTitle = storyTitle;
    }

    public String getStoryContent() {
        return storyContent;
    }

    public void setStoryContent(String storyContent) {
        this.storyContent = storyContent;
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public int getRemainingWords() {
        return remainingWords;
    }

    public void setRemainingWords(int remainingWords) {
        this.remainingWords = remainingWords;
    }
}
