package com.example.quizapp.Fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.quizapp.Model.User;
import com.example.quizapp.Model.Withdraw;
import com.example.quizapp.Quiz.Spinner;
import com.example.quizapp.databinding.FragmentWalletBinding;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;


public class WalletFragment extends Fragment {


    public WalletFragment() {
        // Required empty public constructor
    }




    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate ( savedInstanceState );

    }

    FragmentWalletBinding binding;
    FirebaseFirestore database;
    private User user;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment

        binding = FragmentWalletBinding.inflate ( inflater, container, false );
        database = FirebaseFirestore.getInstance ();

        database.collection ( "user" )
                .document ( FirebaseAuth.getInstance ().getUid () )
                .get ().addOnSuccessListener ( new OnSuccessListener<DocumentSnapshot> () {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {

                 user = documentSnapshot.toObject ( User.class );
                binding.currentcoins.setText ( String.valueOf ( user.getCoins () ) );

            }
        } );
        //

        //

        binding.sendbutton.setOnClickListener ( new View.OnClickListener () {
            @Override
            public void onClick(View v) {


                if(user.getCoins () > 50000){
                    String uid = FirebaseAuth.getInstance ().getUid ();
                    String bikash = binding.bikashorpaypal.getText ().toString ();
                    Withdraw requst = new Withdraw (uid, bikash, user.getName ());
                    database.collection ( "withdraw" )
                            .document ()
                            .set ( requst ).addOnSuccessListener ( new OnSuccessListener<Void> () {
                        @Override
                        public void onSuccess(Void unused) {
                            Toast.makeText ( getContext (), "Requst sent successfully.", Toast.LENGTH_LONG ).show ();
                        }
                    } );

                }else {
                    Toast.makeText ( getContext (), "You need more coins withdraw!", Toast.LENGTH_LONG ).show ();
                }
            }
        } );

        binding.spinwheel.setOnClickListener ( new View.OnClickListener () {
            @Override
            public void onClick(View v) {
                startActivity ( new Intent (getContext (), Spinner.class ) );
            }
        } );





        return binding.getRoot ();
    }
}