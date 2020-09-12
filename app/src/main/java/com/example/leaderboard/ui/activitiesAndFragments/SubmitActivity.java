package com.example.leaderboard.ui.activitiesAndFragments;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import com.example.leaderboard.R;

public class SubmitActivity extends AppCompatActivity {
    EditText firstNameEt = null;
    EditText lastNameEt = null;
    EditText emailAddressEt = null;
    EditText githubAddressEt = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_submit);

        firstNameEt = findViewById(R.id.first_name_et);
        lastNameEt = findViewById(R.id.last_name_et);
        emailAddressEt = findViewById(R.id.email_et);
        githubAddressEt = findViewById(R.id.github_et);

        Button back_button = findViewById(R.id.back_button);
        back_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        Button submit_button = findViewById(R.id.submit_button);
        submit_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fm = getSupportFragmentManager();
                DialogueFragment dialogueFragment = DialogueFragment
                        .newInstance(getFormValues(), new int[]{View.VISIBLE,View.GONE, View.GONE});
                dialogueFragment.show(fm, DialogueFragment.FRAGMENT_TAG);
            }
        });
    }

    private String[] getFormValues() {
        String firsName = firstNameEt.getText().toString().trim();
        String lastName = lastNameEt.getText().toString().trim();
        String emailAddress = emailAddressEt.getText().toString().trim();
        String githubAddress = githubAddressEt.getText().toString().trim();
        return new String[]{firsName, lastName, emailAddress, githubAddress};
    }
}