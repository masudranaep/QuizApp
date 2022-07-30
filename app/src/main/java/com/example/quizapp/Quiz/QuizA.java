package com.example.quizapp.Quiz;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.quizapp.Model.QuiestionSystem;
import com.example.quizapp.R;
import com.example.quizapp.databinding.ActivityQuizBinding;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.FullScreenContentCallback;
import com.google.android.gms.ads.OnPaidEventListener;
import com.google.android.gms.ads.ResponseInfo;
import com.google.android.gms.ads.interstitial.InterstitialAd;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Random;

public class QuizA extends AppCompatActivity {

    ActivityQuizBinding binding;
    FirebaseFirestore database;

    ArrayList<QuiestionSystem> quiestions;

    int index = 0;
    QuiestionSystem quiestionSystem;
    CountDownTimer timer;
    int CorretAnswers = 0;

    private InterstitialAd mInterstitialAd;


    @SuppressLint("MissingPermission")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate ( savedInstanceState );
        binding = ActivityQuizBinding.inflate ( getLayoutInflater () );
        setContentView ( binding.getRoot () );





      final  AdRequest adRequest = new AdRequest.Builder().build();

      binding.adView.loadAd ( adRequest );



        quiestions = new ArrayList<>();
        database = FirebaseFirestore.getInstance();

        final String catId = getIntent().getStringExtra("catId");

        Random random = new Random();
        final int rand = random.nextInt(50);

        database.collection("categories")
                .document(catId)
                .collection("questions")
                .whereGreaterThanOrEqualTo("index", rand)
                .orderBy("index")
                .limit(50).get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                if(queryDocumentSnapshots.getDocuments().size() < 5) {
                    database.collection("Category")
                            .document(catId)
                            .collection("question")
                            .whereLessThanOrEqualTo("index", rand)
                            .orderBy("index")
                            .limit(50).get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                        @Override
                        public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                            for(DocumentSnapshot snapshot : queryDocumentSnapshots) {
                                QuiestionSystem quiestionSystem = snapshot.toObject(QuiestionSystem.class);
                                quiestions.add(quiestionSystem);
                            }
                            setNextQuestion();
                        }
                    });
                } else {
                    for(DocumentSnapshot snapshot : queryDocumentSnapshots) {
                        QuiestionSystem quiestionSystem = snapshot.toObject(QuiestionSystem.class);
                        quiestions.add(quiestionSystem);
                    }
                    setNextQuestion();
                }
            }
        });



        resetTimer();

    }

    void resetTimer() {
        timer = new CountDownTimer(30000,1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                binding.timer.setText(String.valueOf(millisUntilFinished/1000));
            }

            @Override
            public void onFinish() {

            }
        };
    }

    void showAnswer() {
        if(quiestionSystem.getAnswer().equals(binding.option1.getText().toString()))
            binding.option1.setBackground(getResources().getDrawable(R.drawable.op_right));
        else if(quiestionSystem.getAnswer().equals(binding.option2.getText().toString()))
            binding.option2.setBackground(getResources().getDrawable(R.drawable.op_right));
        else if(quiestionSystem.getAnswer().equals(binding.option3.getText().toString()))
            binding.option3.setBackground(getResources().getDrawable(R.drawable.op_right));
        else if(quiestionSystem.getAnswer().equals(binding.option4.getText().toString()))
            binding.option4.setBackground(getResources().getDrawable(R.drawable.op_right));
    }

    void setNextQuestion() {
        if(timer != null)
            timer.cancel();

        timer.start();
        if(index < quiestions.size()) {
            binding.QuestionNumber.setText(String.format("%d/%d", (index+1), quiestions.size()));
            quiestionSystem = quiestions.get(index);
            binding.Question.setText(quiestionSystem.getQuiestion ());
            binding.option1.setText(quiestionSystem.getOption1());
            binding.option2.setText(quiestionSystem.getOption2());
            binding.option3.setText(quiestionSystem.getOption3());
            binding.option4.setText(quiestionSystem.getOption4());
        }
    }

    void checkAnswer(TextView textView) {
        String selectedAnswer = textView.getText().toString();
        if(selectedAnswer.equals(quiestionSystem.getAnswer())) {
            CorretAnswers++;
            textView.setBackground(getResources().getDrawable(R.drawable.op_right));
        } else {
            showAnswer();
            textView.setBackground(getResources().getDrawable(R.drawable.op_wrong));
        }
    }

    void reset() {
        binding.option1.setBackground(getResources().getDrawable(R.drawable.optionunselete));
        binding.option2.setBackground(getResources().getDrawable(R.drawable.optionunselete));
        binding.option3.setBackground(getResources().getDrawable(R.drawable.optionunselete));
        binding.option4.setBackground(getResources().getDrawable(R.drawable.optionunselete));
    }

    //new quition

    public void Nextbutton(View view) {
        switch (view.getId()){
            case R.id.option1:
            case R.id.option2:
            case R.id.option3:
            case R.id.option4:
                if(timer!=null)
                    timer.cancel();
                TextView selected = (TextView) view;
                checkAnswer(selected);

                break;
            case R.id.Nextquiestion:
                reset();
                if(index <= quiestions.size()) {
                    index++;
                    setNextQuestion();
                } else {
                    Intent intent = new Intent(QuizA.this, QuizResult.class);
                    intent.putExtra("correct", CorretAnswers );
                    intent.putExtra("total", quiestions.size());
                    startActivity(intent);
                    //Toast.makeText(this, "Quiz Finished.", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }

}