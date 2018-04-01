package com.example.chan.storyline.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by chandru on 1/4/18.
 */

public class UserStoryLogDBHelper extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "user_story_log";

    public UserStoryLogDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(UserStoryLog.CREATE_TABLE_LOG);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + UserStoryLog.TABLE_NAME);

        // Create tables again
        onCreate(db);
    }

    public long insertUserStory(String username, long storyID, int remain) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(UserStoryLog.USER_NAME, username);
        values.put(UserStoryLog.STORY_ID, storyID);
        values.put(UserStoryLog.OWN_REMAINING_WORDS, remain);
        // insert row
        long id = db.insert(UserStoryLog.TABLE_NAME, null, values);
        db.close();
        return id;
    }

    public int getRemainingWords(String username,int id)
    {
        String query = "SELECT "+UserStoryLog.OWN_REMAINING_WORDS+" FROM"+UserStoryLog.TABLE_NAME+" WHERE "+UserStoryLog.USER_NAME+"="+username+" AND "+UserStoryLog.STORY_ID+"="+id;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        if (cursor != null)
            cursor.moveToFirst();
        int remain =  cursor.getInt(cursor.getColumnIndex(UserStoryLog.OWN_REMAINING_WORDS));

        return remain;
    }


}
