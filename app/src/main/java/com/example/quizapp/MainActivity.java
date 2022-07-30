package com.example.quizapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.quizapp.Fragment.HomeFragment;
import com.example.quizapp.Fragment.WalletFragment;
import com.example.quizapp.Quiz.AmountMethod;
import com.example.quizapp.Quiz.Policy;
import com.example.quizapp.Quiz.Profile;
import com.example.quizapp.Quiz.UserProfile;
import com.example.quizapp.databinding.ActivityMainBinding;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;
    private AdView adView;

    @SuppressLint("MissingPermission")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_main );

        binding = ActivityMainBinding.inflate ( getLayoutInflater () );
         setContentView ( binding.getRoot () );

        MobileAds.initialize(this, new OnInitializationCompleteListener () {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });

        //banner Ads

//
//      final AdRequest adRequest = new AdRequest.Builder().build();
//        binding.adView.loadAd ( adRequest );




        //setSupportActionBar ( binding.toolbar );

        FragmentTransaction transaction = getSupportFragmentManager ().beginTransaction ();
        transaction.replace ( R.id.content, new HomeFragment () );
        transaction.commit ();

        binding.bottomnavigation.setOnNavigationItemSelectedListener ( new BottomNavigationView.OnNavigationItemSelectedListener () {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                FragmentTransaction transaction = getSupportFragmentManager ().beginTransaction ();

                switch (item.getItemId ()){
                    case R.id.home:
                        transaction.replace ( R.id.content, new HomeFragment () );
                        transaction.commit ();
                        break;
                    case R.id.rank:
//                        transaction.replace ( R.id.content, new Pament ());
//
//                        transaction.commit ();

                        Intent intent = new Intent (MainActivity.this, AmountMethod.class );
                        startActivity ( intent );


                        break;
                    case R.id.wallet:
                        transaction.replace ( R.id.content, new WalletFragment () );
                        transaction.commit ();
                        break;
                    case R.id.profile:
                        Intent intent1 = new Intent (MainActivity.this, UserProfile.class );
                        startActivity ( intent1 );
                        break;


                }
                return true;
            }
        } );




    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater ().inflate ( R.menu.homemenu, menu );
        return super.onCreateOptionsMenu ( menu );

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
//        if (item.getItemId () == R.id.homewallet){
//            Toast.makeText ( this, "wallet is cliked.", Toast.LENGTH_LONG ).show ();
//        }

        switch (item.getItemId ()){
            case R.id.homewallet:
                Intent intent = new Intent (MainActivity.this, Profile.class );
                startActivity ( intent );
                return  true;
            case R.id.setting:
                Toast.makeText ( this, "setting is cliked.", Toast.LENGTH_LONG ).show ();
                return  true;
            case R.id.polecy:
                Intent intent1 = new Intent (MainActivity.this, Policy.class );
                startActivity ( intent1 );
                PrivacyPolicylink ( "https://masudep43.blogspot.com/2022/02/earning-43-privacy-policy.html" );
                return  true;

            default:
                return super.onOptionsItemSelected ( item );
        }

    }
    // PrivacyPolicylink

    private void PrivacyPolicylink(String s) {
        Uri uri = Uri.parse ( s );
        startActivity ( new Intent ( Intent.ACTION_VIEW, uri ) );
    }
}