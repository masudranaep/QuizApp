package com.example.quizapp.Quiz;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.quizapp.MainActivity;
import com.example.quizapp.R;
import com.example.quizapp.databinding.ActivityQuizResultBinding;
import com.google.android.gms.ads.AdRequest;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;

public class QuizResult extends AppCompatActivity {


    ActivityQuizResultBinding binding;
    FirebaseFirestore database;
    int POINT = 10;

    @SuppressLint("MissingPermission")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_quiz_result );
        binding =ActivityQuizResultBinding.inflate ( getLayoutInflater () );
        setContentView ( binding.getRoot () );


        final AdRequest adRequest = new AdRequest.Builder().build();
        binding.adView.loadAd ( adRequest );

     //coins add

        int correctAnswers = getIntent().getIntExtra("correct", 0);
        int totalQuestions = getIntent().getIntExtra("total", 0);

        long points = correctAnswers * POINT;

        binding.score.setText(String.format("%d/%d", correctAnswers, totalQuestions));
        binding.earncoins.setText(String.valueOf(points));


        //tatal coins add wallet

        FirebaseFirestore database = FirebaseFirestore.getInstance();

        database.collection("user")
                .document(FirebaseAuth.getInstance().getUid())
                .update("coins", FieldValue.increment(points));

        binding.restartbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent (QuizResult.this, MainActivity.class));
                finishAffinity();
            }
        });

    }
}