package com.example.plantteacher;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

public class SplashActivity extends AppCompatActivity {
    Handler handler = new Handler();
    public static PremiumViewModel premiumViewModel;
    //앱을 실행할 때 가장 먼저 실행이 되는 화면, 1초 후 login화면으로 전환
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        premiumViewModel = new ViewModelProvider(this).get(PremiumViewModel.class);
        premiumViewModel.setIsPremium(0);
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(getApplicationContext(),MainActivity.class);

                Bundle bundle = MainActivity.mbundle;
                bundle.putString("move","move");
                startActivity(intent);
                finish();
            }
        },1000);
    }
}
