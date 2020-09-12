package com.example.leaderboard.ui.adaptersAndModels;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.leaderboard.R;
import com.example.leaderboard.models.LearningLeaders;
import com.example.leaderboard.models.SkillIQLeaders;
import com.example.leaderboard.services.LeadersService;
import com.example.leaderboard.services.LeadersServiceBuilder;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A placeholder fragment containing a simple view.
 */
public class PlaceholderFragment extends Fragment {

    private static final String ARG_SECTION_NUMBER = "section_number";
    private PageViewModel pageViewModel;

    public static PlaceholderFragment newInstance(int index) {
        PlaceholderFragment fragment = new PlaceholderFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(ARG_SECTION_NUMBER, index);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        pageViewModel = new ViewModelProvider(this).get(PageViewModel.class);
        int index = 1;
        if (getArguments() != null) {
            index = getArguments().getInt(ARG_SECTION_NUMBER);
        }
        pageViewModel.setIndex(index);
    }

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_main, container, false);
        final RecyclerView recyclerView = root.findViewById(R.id.list_items);
        final LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);

        pageViewModel.getIndex().observe(getViewLifecycleOwner(), new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                makeServiceCall(recyclerView, integer);
            }
        });

        return root;
    }

    private void makeServiceCall(final RecyclerView recyclerView, Integer integer) {
        LeadersService taskLeadersService = LeadersServiceBuilder.buildService(LeadersService.class);

        if (integer == 1) {

            Call<List<LearningLeaders>> call = taskLeadersService.getHours();

            call.enqueue(new Callback<List<LearningLeaders>>() {
                @Override
                public void onResponse(@NonNull Call<List<LearningLeaders>> call, @NonNull Response<List<LearningLeaders>> response) {
                    assert response.body() != null;

                    final LearningRecyclerAdapter learningAdapter = new LearningRecyclerAdapter(getContext(),
                            response.body());
                    recyclerView.setAdapter(learningAdapter);
                }

                @Override
                public void onFailure(@NonNull Call<List<LearningLeaders>> call, @NonNull Throwable t) {

                }
            });


        } else {
            Call<List<SkillIQLeaders>> call = taskLeadersService.getSkilliq();

            call.enqueue(new Callback<List<SkillIQLeaders>>() {
                @Override
                public void onResponse(@NonNull Call<List<SkillIQLeaders>> call,
                                       @NonNull Response<List<SkillIQLeaders>> response) {
                    assert response.body() != null;

                    final SkillRecyclerAdapter skillIQAdapter = new SkillRecyclerAdapter(getContext(),
                            response.body());
                    recyclerView.setAdapter(skillIQAdapter);
                }

                @Override
                public void onFailure(@NonNull Call<List<SkillIQLeaders>> call, @NonNull Throwable t) {

                }
            });

        }
    }

}