package com.example.chan.storyline;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.example.chan.storyline.Adapter.StoryAdapter;
import com.example.chan.storyline.Database.StoryDB;
import com.example.chan.storyline.Database.StoryDBHelper;
import com.example.chan.storyline.Database.UserStoryLogDBHelper;
import com.example.chan.storyline.vo.Story;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    List<Story> storyList = new ArrayList<>();
    RecyclerView recyclerView;
    FloatingActionButton fab;
    StoryAdapter storyAdapter;
    StoryDBHelper storyDBHelper;
    UserStoryLogDBHelper userStoryLogDBHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        storyDBHelper = new StoryDBHelper(this);
        userStoryLogDBHelper = new UserStoryLogDBHelper(this);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        recyclerView = (RecyclerView) findViewById(R.id.story_recycler_view);
        fab = (FloatingActionButton) findViewById(R.id.fab);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, CreateStoryActivity.class);
                startActivity(intent);
            }
        });
        storyAdapter = new StoryAdapter(storyList);
        RecyclerView.LayoutManager storyLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(storyLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(storyAdapter);

        loadStoryData();
    }

    private void loadStoryData() {
        storyList.clear();
      List<StoryDB> storiesList=  storyDBHelper.getAllStories();

      if(storiesList!=null&&!storiesList.isEmpty())
      {
          for(StoryDB story:storiesList)
          {
              storyList.add(new Story(story.getStoryTitle(),story.getCreateUser(),"Remaining:"+String.valueOf(story.getRemainingWords())+"words"));
          }


      }
      else
      {
          storyList.add(new Story("No Data Found","",""));

      }


        storyAdapter.notifyDataSetChanged();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.mainmenu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId())
        {
            case R.id.refresh:
                loadStoryData();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }
}
