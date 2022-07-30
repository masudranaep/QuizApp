package com.example.quizapp.Quiz;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;

import com.example.quizapp.R;
import com.example.quizapp.databinding.ActivityAmountMethodBinding;
import com.google.android.gms.ads.AdRequest;

public class AmountMethod extends AppCompatActivity {

    ActivityAmountMethodBinding binding;

    @SuppressLint("MissingPermission")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_amount_method );

        binding = ActivityAmountMethodBinding.inflate ( getLayoutInflater () );
        binding.getRoot ();


        final AdRequest adRequest = new AdRequest.Builder().build();
        binding.adView.loadAd ( adRequest );

    }
}