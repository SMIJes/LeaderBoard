package com.example.leaderboard.ui.adaptersAndModels;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class PageViewModel extends ViewModel {





    private boolean newlyCreated = true;
    private MutableLiveData<Integer> mIndex = new MutableLiveData<>();
    private MutableLiveData<Integer> confirmationStatus = new MutableLiveData<>();
    private MutableLiveData<Integer> positiveResponseStatus = new MutableLiveData<>();
    private MutableLiveData<Integer> negativeResponseStatus = new MutableLiveData<>();

    //Getters
    public boolean isNewlyCreated() {
        return newlyCreated;
    }
    public MutableLiveData<Integer> getIndex() {
        return mIndex;
    }
    public MutableLiveData<Integer> getConfirmationStatus() {
        return confirmationStatus;
    }
    public MutableLiveData<Integer> getPositiveResponseStatus() {
        return positiveResponseStatus;
    }
    public MutableLiveData<Integer> getNegativeResponseStatus() {
        return negativeResponseStatus;
    }

    //Setters
    public void setNewlyCreated(boolean newlyCreated) {
        this.newlyCreated = newlyCreated;
    }
    public void setIndex(int index) {
        mIndex.setValue(index);
    }
    public void setConfirmationStatus(int status) {
        confirmationStatus.setValue(status);
    }
    public void setPositiveResponseStatus(int status) {
        positiveResponseStatus.setValue(status);
    }
    public void setNegativeResponseStatus(int status) {
        negativeResponseStatus.setValue(status);
    }
}
