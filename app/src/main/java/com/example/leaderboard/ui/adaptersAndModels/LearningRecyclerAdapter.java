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
import com.example.leaderboard.models.LearningLeaders;
import com.squareup.picasso.Picasso;

import java.util.List;
import java.util.Locale;

public class LearningRecyclerAdapter extends RecyclerView.Adapter<LearningRecyclerAdapter.ViewHolder> {

    private final LayoutInflater mLayoutInflater;
    private final List<LearningLeaders> mLearningLeaders;

    public LearningRecyclerAdapter(Context context, List<LearningLeaders> learningLeaders) {
        mLayoutInflater = LayoutInflater.from(context);
        this.mLearningLeaders = learningLeaders;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = mLayoutInflater.inflate(R.layout.list_item_learning, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        holder.mNameTV.setText(mLearningLeaders.get(position).getName());
        holder.mScoreTV.setText(String.format(Locale.ENGLISH, "%d learning hours, %s",
                mLearningLeaders.get(position).getHours(), mLearningLeaders.get(position).getCountry()));
        Picasso.with(holder.mBadgeIV.getContext())
                .load(mLearningLeaders.get(position).getBadgeUrl())
                .into(holder.mBadgeIV);
    }

    @Override
    public int getItemCount() {
        return mLearningLeaders.size();
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
