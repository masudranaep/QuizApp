package com.example.quizapp.Quiz;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.quizapp.Login.Signup;
import com.example.quizapp.MainActivity;
import com.example.quizapp.Model.User;
import com.example.quizapp.R;
import com.example.quizapp.databinding.ActivitySignupBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

public class UserProfile extends AppCompatActivity {

    ActivitySignupBinding binding;
    FirebaseAuth auth;

    FirebaseFirestore database;
    ProgressDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_user_profile );

        binding = ActivitySignupBinding.inflate ( getLayoutInflater () );
        setContentView ( binding.getRoot () );

        auth = FirebaseAuth.getInstance ();
        database = FirebaseFirestore.getInstance ();

        dialog = new ProgressDialog( this );
        dialog.setMessage ( "We're creating account!" );

        binding.submitBytton.setOnClickListener ( new View.OnClickListener () {
            @Override
            public void onClick(View v) {
                String email, pass, name, referCode;

                // email & pass

                email = binding.EmailAddress.getText ().toString ();
                pass = binding.TextPassword.getText ().toString ();
                name = binding.editName.getText ().toString ();
                referCode = binding.ReferCode.getText ().toString ();

                final User user = new User (name, email, pass, referCode);

                dialog.show();

                auth.createUserWithEmailAndPassword ( email, pass ).addOnCompleteListener ( new OnCompleteListener<AuthResult> () {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {


                        if(task.isSuccessful ()){
                            String uid = task.getResult ().getUser ().getUid ();
                            database.collection ( "user" )
                                    .document (uid)
                                    .set ( user ).addOnCompleteListener ( new OnCompleteListener<Void> () {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if(task.isSuccessful ()){
                                        dialog.dismiss ();
                                        startActivity ( new Intent ( UserProfile.this, MainActivity.class ) );
                                        finish ();
                                    }else {
                                        Toast.makeText ( UserProfile.this, task.getException ().getLocalizedMessage (),Toast.LENGTH_LONG ).show ();

                                    }
                                }
                            } );
                            Toast.makeText ( UserProfile.this, "Success", Toast.LENGTH_LONG ).show ();

                        }else {
                            dialog.dismiss ();
                            Toast.makeText ( UserProfile.this, task.getException ().getLocalizedMessage (),Toast.LENGTH_LONG ).show ();
                        }
                    }
                } );

            }
        } );
    }
    }
