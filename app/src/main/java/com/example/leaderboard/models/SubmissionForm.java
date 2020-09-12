package com.example.leaderboard.models;

public class SubmissionForm {
    String firstName;
    String lastName;
    String emailAddress;
    String githubLink;

    public SubmissionForm(String firstName, String lastName, String emailAddress, String githubLink) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.emailAddress = emailAddress;
        this.githubLink = githubLink;
    }

}
