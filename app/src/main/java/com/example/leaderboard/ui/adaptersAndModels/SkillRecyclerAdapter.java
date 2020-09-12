package com.example.leaderboard.ui.adaptersAndModels;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.leaderboard.R;
import com.example.leaderboard.models.SkillIQLeaders;
import com.squareup.picasso.Picasso;

import java.util.List;
import java.util.Locale;

public class SkillRecyclerAdapter extends RecyclerView.Adapter<SkillRecyclerAdapter.ViewHolder> {
    private final LayoutInflater mLayoutInflater;
    private final List<SkillIQLeaders> mSkillIQLeaders;

    public SkillRecyclerAdapter(Context context, List<SkillIQLeaders> skillIQLeaders) {
        mLayoutInflater = LayoutInflater.from(context);
        this.mSkillIQLeaders = skillIQLeaders;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = mLayoutInflater.inflate(R.layout.list_item_skill, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.mNameTV.setText(mSkillIQLeaders.get(position).getName());
        holder.mScoreTV.setText(String.format(Locale.ENGLISH, "%d Skill IQ Score, %s",
                mSkillIQLeaders.get(position).getScore(), mSkillIQLeaders.get(position).getCountry()));

        Picasso.with(holder.mBadgeIV.getContext())
                .load(mSkillIQLeaders.get(position).getBadgeUrl())
                .into(holder.mBadgeIV);
        //holder.mBadgeIV.setImageResource(R.drawable.ic_baseline_assignment_24);
    }

    @Override
    public int getItemCount() {
        return mSkillIQLeaders.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public final ImageView mBadgeIV;
        public final TextView mNameTV;
        public final TextView mScoreTV;

        public ViewHolder(final View itemView) {
            super(itemView);
            mBadgeIV = itemView.findViewById(R.id.badge_image_view);
            mNameTV = itemView.findViewById(R.id.name_text_view);
            mScoreTV = itemView.findViewById(R.id.score_text_view);
        }
    }
}

