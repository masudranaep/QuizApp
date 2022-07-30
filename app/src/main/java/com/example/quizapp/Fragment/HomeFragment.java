package com.example.quizapp.Fragment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.quizapp.Adapter.CategoryAdapter;
import com.example.quizapp.Model.CategoryModel;
import com.example.quizapp.Quiz.RewadAds;
import com.example.quizapp.Quiz.Spinner;
import com.example.quizapp.R;
import com.example.quizapp.databinding.FragmentHomeBinding;
import com.google.android.gms.ads.FullScreenContentCallback;
import com.google.android.gms.ads.OnPaidEventListener;
import com.google.android.gms.ads.OnUserEarnedRewardListener;
import com.google.android.gms.ads.ResponseInfo;
import com.google.android.gms.ads.rewarded.OnAdMetadataChangedListener;
import com.google.android.gms.ads.rewarded.RewardItem;
import com.google.android.gms.ads.rewarded.RewardedAd;
import com.google.android.gms.ads.rewarded.ServerSideVerificationOptions;
import com.google.android.gms.internal.ads.zzcfi;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;


public class HomeFragment extends Fragment {



    public HomeFragment() {
        // Required empty public constructor
    }




    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate ( savedInstanceState );

    }
    FragmentHomeBinding binding;
    FirebaseFirestore database;


    RewardedAd rewardedAd;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentHomeBinding.inflate ( inflater, container, false );





        database = FirebaseFirestore.getInstance ();
        final ArrayList<CategoryModel> category = new ArrayList<> ();
        final CategoryAdapter adapter = new CategoryAdapter ( getContext (),category );

        //category input firebase

        database.collection ( "Category" )
                .addSnapshotListener ( new EventListener<QuerySnapshot> () {
                    @Override
                    public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                        category.clear ();
                        for(DocumentSnapshot snapshot : value.getDocuments ()){
                            CategoryModel model = snapshot.toObject (CategoryModel.class );
                            model.setCategoryId ( snapshot.getId () );
                            category.add ( model );
                        }

                        adapter.notifyDataSetChanged ();
                    }
                } );

        binding.recyleView.setLayoutManager ( new GridLayoutManager ( getContext (), 2 ) );
        binding.recyleView.setAdapter ( adapter );

        binding.spinwheel.setOnClickListener ( new View.OnClickListener () {
            @Override
            public void onClick(View v) {
                startActivity ( new Intent (getContext (), Spinner.class ) );
            }
        } );

        //share app
        binding.invitefd.setOnClickListener ( new View.OnClickListener () {
            @Override
            public void onClick(View v) {
//                Intent intent = new Intent ( Intent.ACTION_SEND );
//                intent.setType ( "text/plain" );
//                String Body = "Download this App";
//                String Sub = "http://play.google.com";
//                intent.putExtra ( Intent.EXTRA_TEXT, Body );
//                intent.putExtra ( Intent.EXTRA_TEXT, Sub );
//                startActivity ( Intent.createChooser ( intent, "Share using" ) );

                startActivity ( new Intent (getContext (), RewadAds.class ) );
            }
        } );

        // Inflate the layout for this fragment
        return binding.getRoot ();






    }
}