package com.example.quizapp.Quiz;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;

import com.example.quizapp.R;
import com.example.quizapp.databinding.ActivityRewadAdsBinding;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.OnUserEarnedRewardListener;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.google.android.gms.ads.rewarded.RewardItem;
import com.google.android.gms.ads.rewarded.RewardedAd;
import com.google.android.gms.ads.rewarded.RewardedAdLoadCallback;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;

public class RewadAds extends AppCompatActivity {
    private RewardedAd mRewardedAd;

    ActivityRewadAdsBinding binding;

    FirebaseFirestore database;
    String currenUid;
    int coins = 0;
    private AdView adView;


    @SuppressLint("MissingPermission")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate ( savedInstanceState );
        binding = ActivityRewadAdsBinding.inflate ( getLayoutInflater () );
        setContentView (binding.getRoot () );

        database = FirebaseFirestore.getInstance ();
        currenUid = FirebaseAuth.getInstance ().getUid ();
        
        
        

        LoadAd ();

        final AdRequest adRequest = new AdRequest.Builder().build();
        binding.adView.loadAd ( adRequest );


        MobileAds.initialize(this, new OnInitializationCompleteListener () {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });

        binding.video1.setOnClickListener ( new View.OnClickListener () {
            @Override
            public void onClick(View v) {
              //rewardAds

                if (mRewardedAd != null) {
                    Activity activityContext = RewadAds.this;
                    mRewardedAd.show(activityContext, new OnUserEarnedRewardListener () {
                        @Override
                        public void onUserEarnedReward(@NonNull RewardItem rewardItem) {
                            // Handle the reward.


                            int rewardAmount = rewardItem.getAmount();
                            String rewardType = rewardItem.getType();

                            //binding.vd1.setImageResource ( R.drawable.coins );
                        }
                    });
                } else {

                }

            }
        } );


        binding.video2.setOnClickListener ( new View.OnClickListener () {
            @Override
            public void onClick(View v) {
                //rewardAds

                if (mRewardedAd != null) {
                    Activity activityContext = RewadAds.this;
                    mRewardedAd.show(activityContext, new OnUserEarnedRewardListener () {
                        @Override
                        public void onUserEarnedReward(@NonNull RewardItem rewardItem) {
                            // Handle the reward.


                            int rewardAmount = rewardItem.getAmount();
                            String rewardType = rewardItem.getType();

                            //binding.vd1.setImageResource ( R.drawable.coins );
                        }
                    });
                } else {

                }

            }
        } );

        binding.video4.setOnClickListener ( new View.OnClickListener () {
            @Override
            public void onClick(View v) {
                //rewardAds

                if (mRewardedAd != null) {
                    Activity activityContext = RewadAds.this;
                    mRewardedAd.show(activityContext, new OnUserEarnedRewardListener () {
                        @Override
                        public void onUserEarnedReward(@NonNull RewardItem rewardItem) {
                            // Handle the reward.


                            int rewardAmount = rewardItem.getAmount();
                            String rewardType = rewardItem.getType();

                            //binding.vd1.setImageResource ( R.drawable.coins );
                        }
                    });
                } else {

                }

            }
        } );


        binding.video4.setOnClickListener ( new View.OnClickListener () {
            @Override
            public void onClick(View v) {
                //rewardAds

                if (mRewardedAd != null) {
                    Activity activityContext = RewadAds.this;
                    mRewardedAd.show(activityContext, new OnUserEarnedRewardListener () {
                        @Override
                        public void onUserEarnedReward(@NonNull RewardItem rewardItem) {
                            // Handle the reward.


                            int rewardAmount = rewardItem.getAmount();
                            String rewardType = rewardItem.getType();

                            //binding.vd1.setImageResource ( R.drawable.coins );
                        }
                    });
                } else {

                }

            }
        } );


    }

    //rewardAds

    void LoadAd(){
        AdRequest adRequest = new AdRequest.Builder().build();

        RewardedAd.load(this, "ca-app-pub-6814304072623948/6838048540",
                adRequest, new RewardedAdLoadCallback () {
                    @Override
                    public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                        // Handle the error.

                        mRewardedAd = null;
                    }

                    @Override
                    public void onAdLoaded(@NonNull RewardedAd rewardedAd) {
                        mRewardedAd = rewardedAd;

                    }
                });

    }
}