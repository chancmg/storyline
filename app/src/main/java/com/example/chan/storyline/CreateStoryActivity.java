package com.example.chan.storyline;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.chan.storyline.Constants.AppConstants;
import com.example.chan.storyline.Database.StoryDBHelper;
import com.example.chan.storyline.Database.UserStoryLog;
import com.example.chan.storyline.Database.UserStoryLogDBHelper;

/**
 * Created by chandru on 1/4/18.
 */

public class CreateStoryActivity extends AppCompatActivity {
    EditText storyEdit, storyTitle;
    Button submit;
    TextView remainingWords;
    StoryDBHelper storyDBHelper;
    UserStoryLogDBHelper userStoryLogDBHelper;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_story);
        storyDBHelper = new StoryDBHelper(this);
        userStoryLogDBHelper = new UserStoryLogDBHelper(this);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        storyTitle = (EditText) findViewById(R.id.storyTitle_edit);
        storyEdit = (EditText) findViewById(R.id.createStory_edit);
        submit = (Button) findViewById(R.id.submit_Story);
        remainingWords = (TextView) findViewById(R.id.textRemainingWords);

        storyEdit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                remainingWords.setText("Remaining words:" + (AppConstants.MAX_STORY_LENGTH - getWordCount(editable.toString())));
            }
        });
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createStory(storyTitle.getText().toString(), storyEdit.getText().toString());
            }
        });

    }

    private void createStory(String title, String content) {

        if (getWordCount(content) > 50) {
            Toast.makeText(this, "content exceeds the maximum limit", Toast.LENGTH_SHORT).show();
        }

        if (title.trim().contentEquals(""))
            Toast.makeText(this, "title should not be empty", Toast.LENGTH_SHORT).show();

        if (!content.trim().contentEquals("") && !title.trim().contentEquals("")) {

            if (!storyDBHelper.isStoryFound(title)) {
                long id = storyDBHelper.insertStory(content, title, "ADMIN", AppConstants.TOTAL_STORY_LENGTH - getWordCount(content));
                if (id != 0) {
                    userStoryLogDBHelper.insertUserStory("ADMIN", id, AppConstants.MAX_STORY_LENGTH - getWordCount(content));
                    Toast.makeText(this, "Successfully Saved", Toast.LENGTH_SHORT).show();
                }

            } else {
                Toast.makeText(this, "Title already exists", Toast.LENGTH_SHORT).show();

            }
        } else {
            Toast.makeText(this, "please verify the content", Toast.LENGTH_SHORT).show();
        }


    }


    private int getWordCount(String story) {
        int count = 0;
        for (int i = 1; i < story.length(); i++) {
            if (story.charAt(i) == ' ' && story.charAt(i - 1) != ' ')
                count++;
        }

        return count;
    }
}
