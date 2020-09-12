package com.example.leaderboard.ui.activitiesAndFragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.DialogFragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.leaderboard.R;
import com.example.leaderboard.models.SubmissionForm;
import com.example.leaderboard.services.FormService;
import com.example.leaderboard.services.FormServiceBuilder;
import com.example.leaderboard.ui.adaptersAndModels.PageViewModel;

import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class DialogueFragment extends DialogFragment {

    static final String FRAGMENT_TAG = "confirmation fragment";
    static final String FORM_VALUES_KEY = "form values";
    static final String VIEW_VALUES_KEY = "view values";

    PageViewModel pageViewModel;

    public DialogueFragment() {
    }

    public static DialogueFragment newInstance(String[] formValues, int[] viewValues) {
        DialogueFragment frag = new DialogueFragment();
        Bundle args = new Bundle();
        args.putStringArray(FORM_VALUES_KEY, formValues);
        args.putIntArray(VIEW_VALUES_KEY, viewValues);
        frag.setArguments(args);
        return frag;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        pageViewModel = new ViewModelProvider(this).get(PageViewModel.class);

        if (pageViewModel.isNewlyCreated()){
            int[] values = Objects.requireNonNull(requireArguments().getIntArray(VIEW_VALUES_KEY));
            pageViewModel.setNewlyCreated(false);
            pageViewModel.setConfirmationStatus(values[0]);
            pageViewModel.setPositiveResponseStatus(values[1]);
            pageViewModel.setNegativeResponseStatus(values[2]);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_dialogue, container);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        CardView cardView = (CardView) view;
        cardView.setRadius(16);
        Objects.requireNonNull(Objects.requireNonNull(getDialog())
                .getWindow()).setBackgroundDrawableResource(R.color.colorTransparent);

        final ConstraintLayout confirmationCl = view.findViewById(R.id.confirmation_cl);
        final ConstraintLayout positiveResponseCL = view.findViewById(R.id.positive_response_cl);
        final ConstraintLayout negativeResponseCL = view.findViewById(R.id.negative_response_cl);
        observeViews(confirmationCl, positiveResponseCL, negativeResponseCL);

        ImageView cancelBtn = view.findViewById(R.id.cancel);
        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });

        Button yesBtn = view.findViewById(R.id.yes_bt);
        yesBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                makeServiceCall();
            }
        });

        TextView textView = view.findViewById(R.id.question_mark_tv);
        textView.setText("?");
        textView.setTextSize(45);

    }

    private void makeServiceCall() {
        assert getArguments() != null;
        String[] formValues = getArguments().getStringArray(FORM_VALUES_KEY);
        FormService taskFormService = FormServiceBuilder.buildService(FormService.class);
        assert formValues != null;
        Call<SubmissionForm> submitCall = taskFormService.submitForm(
                formValues[0], formValues[1], formValues[2], formValues[3]
        );

        submitCall.enqueue(new Callback<SubmissionForm>() {

            @Override
            public void onResponse(@NonNull Call<SubmissionForm> call, @NonNull Response<SubmissionForm> response) {

                pageViewModel.setConfirmationStatus(View.GONE);
                pageViewModel.setConfirmationStatus(View.GONE);
                pageViewModel.setNegativeResponseStatus(View.VISIBLE);
            }

            @Override
            public void onFailure(@NonNull Call<SubmissionForm> call, @NonNull Throwable t) {
                pageViewModel.setConfirmationStatus(View.GONE);
                pageViewModel.setConfirmationStatus(View.VISIBLE);
                pageViewModel.setNegativeResponseStatus(View.GONE);
            }
        });
    }

    private void observeViews(final ConstraintLayout confirmationCl,
                              final ConstraintLayout positiveResponseCL,
                              final ConstraintLayout negativeResponseCL){

        pageViewModel.getConfirmationStatus().observe(getViewLifecycleOwner(), new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                confirmationCl.setVisibility(integer);
            }
        });pageViewModel.getPositiveResponseStatus().observe(getViewLifecycleOwner(), new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                positiveResponseCL.setVisibility(integer);
            }
        });pageViewModel.getNegativeResponseStatus().observe(getViewLifecycleOwner(), new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                negativeResponseCL.setVisibility(integer);
            }
        });
    }
}