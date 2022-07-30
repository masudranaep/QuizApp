package com.example.quizapp.Login;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.quizapp.MainActivity;
import com.example.quizapp.R;
import com.example.quizapp.databinding.ActivityLogingBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import java.util.concurrent.Executor;

public class Loging extends AppCompatActivity {


    ActivityLogingBinding binding;

    FirebaseAuth auth;
    ProgressDialog dialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_loging );

        binding = ActivityLogingBinding.inflate ( getLayoutInflater () );
        setContentView ( binding.getRoot () );

        auth = FirebaseAuth.getInstance ();

        dialog = new ProgressDialog ( this );
        dialog.setMessage ( "Logging in!" );

        if(auth.getCurrentUser () != null){
           startActivity ( new Intent (Loging.this, MainActivity.class ) );
            finish ();
        }

        binding.submitbuttonLog.setOnClickListener ( new View.OnClickListener () {
            @Override
            public void onClick(View v) {
                 String email, pass;
                 email = binding.EmailAddress.getText ().toString ();
                 pass = binding.TextPassword.getText ().toString ();

                 dialog.show ();
                 auth.signInWithEmailAndPassword ( email, pass ).addOnCompleteListener ( new OnCompleteListener<AuthResult> () {
                     @Override
                     public void onComplete(@NonNull Task<AuthResult> task) {

                         dialog.dismiss ();
                         if(task.isSuccessful ()){
                              startActivity ( new Intent (Loging.this, MainActivity.class ) );
                              finish ();


                         }else {

                             Toast.makeText ( Loging.this, task.getException ().getLocalizedMessage (), Toast.LENGTH_LONG ).show ();
                         }
                     }
                 } );
            }
        } );

        binding.alreadyaccount.setOnClickListener ( new View.OnClickListener () {
            @Override
            public void onClick(View v) {
                startActivity ( new Intent ( Loging.this, Signup.class) );
            }
        } );

    }


    public void forgotpassword(View view) {
    }
}