package com.example.plantteacher;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class PremiumViewModel extends ViewModel {
    private MutableLiveData<Integer> isPremium = new MutableLiveData<>();

    public void setIsPremium(int val) {
        this.isPremium.setValue(val);
    }

    public int getIsPremium()
    {
        return this.isPremium.getValue();
    }
}
