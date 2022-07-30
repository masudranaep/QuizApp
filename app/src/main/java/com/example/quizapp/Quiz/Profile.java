package com.example.quizapp.Quiz;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.quizapp.R;
import com.example.quizapp.databinding.ActivityProfileBinding;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;

public class Profile extends AppCompatActivity {

    String num = "+8801628702811";
    String text = "Hello";
    LinearLayout facebook, university;

    ActivityProfileBinding binding;

    private AdView adView;

    @SuppressLint("MissingPermission")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_profile );
        binding = ActivityProfileBinding.inflate ( getLayoutInflater () );
        binding.getRoot ();




        AdRequest adRequest = new AdRequest.Builder().build();
        binding.adView.loadAd ( adRequest );

            facebook = findViewById ( R.id.facebook );
            university = findViewById ( R.id.University );

            //facebook

            facebook.setOnClickListener ( new View.OnClickListener () {
                @Override
                public void onClick(View v) {
                    facebookUrl("https://www.facebook.com/mdmasudranaep" );
                }
            } );

            // Univarsity
            university.setOnClickListener ( new View.OnClickListener () {
                @Override
                public void onClick(View v) {
                    universityUrl("https://www.facebook.com/NUBDhaka" );
                }
            } );

        }
//facbook
        private void facebookUrl(String s) {
            Uri uri = Uri.parse ( s );
            startActivity ( new Intent (Intent.ACTION_VIEW, uri) );
        }
        //university
        private void universityUrl(String s) {
            Uri uri = Uri.parse ( s );
            startActivity ( new Intent (Intent.ACTION_VIEW, uri) );
        }

        public void Backpass(View view) {
        }


        public void Contact(View view) {
            boolean installed = isAppInstalled("com.whatsapp");
            if(installed){
                Intent intent = new Intent (Intent.ACTION_VIEW);
                intent.setData ( Uri.parse ("http://api.whatsapp.com/send?phone="+num+"&text"+text) );
                startActivity ( intent );
            }else {
                Toast.makeText ( Profile.this, "Whatsapp is not installed",Toast.LENGTH_LONG ).show ();
            }


        }
        private boolean isAppInstalled(String s) {
            PackageManager packageManager = getPackageManager ();
            boolean is_installed;

            try {
                packageManager.getPackageInfo ( s, PackageManager.GET_ACTIVITIES );
                is_installed = true;

            }catch (PackageManager.NameNotFoundException e){
                is_installed = false;
                e.printStackTrace ();

            }
            return is_installed;
        }



        public void Location(View view) {
        }

    }
