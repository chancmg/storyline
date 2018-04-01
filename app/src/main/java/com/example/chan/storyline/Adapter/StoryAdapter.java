package com.example.chan.storyline.Adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.chan.storyline.R;
import com.example.chan.storyline.vo.Story;

import java.util.List;

/**
 * Created by chandru on 1/4/18.
 */

public class StoryAdapter extends RecyclerView.Adapter<StoryAdapter.MyViewHolder> {

    List<Story> storyList;

    public StoryAdapter(List<Story> storyList) {
        this.storyList = storyList;
    }

    @Override
    public StoryAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.storyitem, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(StoryAdapter.MyViewHolder holder, int position) {
        Story story = storyList.get(position);

        holder.storyTitle.setText(story.getStoryTitle());
        holder.createdUser.setText(story.getCreatedUser());
        holder.noOFWords.setText(story.getNoOfWords());
    }

    @Override
    public int getItemCount() {
        return storyList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView storyTitle;
        public TextView createdUser;
        public TextView noOFWords;

        public MyViewHolder(View itemView) {
            super(itemView);
            storyTitle = (TextView) itemView.findViewById(R.id.storyName);
            createdUser = (TextView) itemView.findViewById(R.id.createdUser);
            noOFWords = (TextView) itemView.findViewById(R.id.noOfWords);

        }
    }
}
