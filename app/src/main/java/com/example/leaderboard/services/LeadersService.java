package com.example.leaderboard.services;

import com.example.leaderboard.models.LearningLeaders;
import com.example.leaderboard.models.SkillIQLeaders;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface LeadersService {
    @GET("hours")
    Call<List<LearningLeaders>> getHours();

    @GET("skilliq")
    Call<List<SkillIQLeaders>> getSkilliq();
}
