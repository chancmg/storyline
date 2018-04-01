package com.example.chan.storyline.Database;

/**
 * Created by chandru on 1/4/18.
 */

public class UserStoryLog {
    public static final String TABLE_NAME = "user_story_log";
    public static final String ID ="id";
    public static final String USER_NAME = "username";
    public static final String STORY_ID = "story_id";
    public static final String OWN_REMAINING_WORDS = "own_remaining_words";
    public static final String CREATE_TABLE_LOG = "CREATE TABLE "+TABLE_NAME+"("+ID+" INTEGER PRIMARY KEY AUTOINCREMENT,"
            +USER_NAME+" TEXT,"
            +STORY_ID+" INTEGER,"
            +OWN_REMAINING_WORDS+" INTEGER)";

   private String userName;
   private int storyID;
   private int ownRemainingWords;
   public UserStoryLog(String userName, int storyID, int ownRemainingWords) {
        this.userName = userName;
        this.storyID = storyID;
        this.ownRemainingWords = ownRemainingWords;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getStoryID() {
        return storyID;
    }

    public void setStoryID(int storyID) {
        this.storyID = storyID;
    }

    public int getOwnRemainingWords() {
        return ownRemainingWords;
    }

    public void setOwnRemainingWords(int ownRemainingWords) {
        this.ownRemainingWords = ownRemainingWords;
    }
}
