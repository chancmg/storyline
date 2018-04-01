package com.example.chan.storyline.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by chandru on 1/4/18.
 */

public class StoryDBHelper extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "story_db";

    public StoryDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(StoryDB.CREATE_TABLE_STORY);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + StoryDB.STORY_TABLE_NAME);

        // Create tables again
        onCreate(db);
    }

    public long insertStory(String story, String storyTitle, String author, int remain) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(StoryDB.STORY_CONTENT, story);
        values.put(StoryDB.STORY_TITLE, storyTitle);
        values.put(StoryDB.REMAINING_WORDS, remain);
        values.put(StoryDB.CREATED_USER, author);
        // insert row
        long id = db.insert(StoryDB.STORY_TABLE_NAME, null, values);
        db.close();
        return id;
    }

    public StoryDB getStory(long id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(StoryDB.STORY_TABLE_NAME,
                new String[]{StoryDB.STORY_ID, StoryDB.STORY_TITLE, StoryDB.STORY_CONTENT, StoryDB.CREATED_USER, StoryDB.REMAINING_WORDS},
                StoryDB.STORY_ID + "=?",
                new String[]{String.valueOf(id)}, null, null, null, null);
        if (cursor != null){
            cursor.moveToFirst();
        }


        StoryDB storyDB = new StoryDB(
                cursor.getInt(cursor.getColumnIndex(StoryDB.STORY_ID)),
                cursor.getString(cursor.getColumnIndex(StoryDB.STORY_TITLE)),
                cursor.getString(cursor.getColumnIndex(StoryDB.STORY_CONTENT)),
                cursor.getString(cursor.getColumnIndex(StoryDB.CREATED_USER)),
                cursor.getInt(cursor.getColumnIndex(StoryDB.REMAINING_WORDS)));

        // close the db connection
        cursor.close();

        return storyDB;
    }

    public List<StoryDB> getAllStories() {
        List<StoryDB> storyDBList = new ArrayList<>();
        String selectQuery = "SELECT  * FROM " + StoryDB.STORY_TABLE_NAME + " ORDER BY " +
                StoryDB.STORY_ID;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {

                StoryDB storyDB = new StoryDB(
                        cursor.getInt(cursor.getColumnIndex(StoryDB.STORY_ID)),
                        cursor.getString(cursor.getColumnIndex(StoryDB.STORY_TITLE)),
                        cursor.getString(cursor.getColumnIndex(StoryDB.STORY_CONTENT)),
                        cursor.getString(cursor.getColumnIndex(StoryDB.CREATED_USER)),
                        cursor.getInt(cursor.getColumnIndex(StoryDB.REMAINING_WORDS)));

                storyDBList.add(storyDB);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();

        return storyDBList;
    }

    public int getStoriesCount() {
        String countQuery = "SELECT  * FROM " + StoryDB.STORY_TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);

        int count = cursor.getCount();
        cursor.close();
        // return count
        return count;
    }

    public boolean isStoryFound(String title) {
        String query = "SELECT  * FROM " + StoryDB.STORY_TABLE_NAME + " WHERE " + StoryDB.STORY_TITLE + "=" + title;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        int count = cursor.getCount();
        cursor.close();
        if (count > 0)
            return true;

        return false;
    }
    public int getStoryID(String title) {
        String query = "SELECT  "+StoryDB.STORY_ID+ " FROM " + StoryDB.STORY_TABLE_NAME + " WHERE " + StoryDB.STORY_TITLE + "=" + title;
        SQLiteDatabase db = this.getReadableDatabase();
        System.out.println("get story ID");
        if(db==null)
        {
            System.out.println("DB NOT FOUND");
        }
        else
        {
            System.out.println(db.getPath());
        }
        Cursor cursor = db.rawQuery(query, null);

        if (cursor != null)
            cursor.moveToFirst();

        int id =  cursor.getInt(cursor.getColumnIndex(StoryDB.STORY_ID));

        cursor.close();

        return id;

    }
}
