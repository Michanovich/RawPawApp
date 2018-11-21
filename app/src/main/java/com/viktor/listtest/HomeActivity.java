package com.viktor.listtest;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;

import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

public class HomeActivity extends AppCompatActivity implements View.OnClickListener {
    private CardView dogCard, catCard, petProfileCard, aboutCard;
    private AdView mAdView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        dogCard = findViewById(R.id.dogCardId);
//        catCard = findViewById(R.id.catCardId);
        petProfileCard = findViewById(R.id.petProfileCardId);
        aboutCard = findViewById(R.id.aboutCardId);

        dogCard.setOnClickListener(this);
//        catCard.setOnClickListener(this);
        petProfileCard.setOnClickListener(this);
        aboutCard.setOnClickListener(this);

        MobileAds.initialize(this, "ca-app-pub-9102373556091600~1368769916");
        mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
    }

    @Override
    public void onClick(View v) {
        Intent i;

        switch (v.getId()) {
            case R.id.dogCardId:
                i = new Intent(this, CreateDogProfile.class);
                startActivity(i);
                break;
//            case R.id.catCardId:
//                i = new Intent(this, CreateCatProfile.class);
//                startActivity(i);
//                break;
            case R.id.petProfileCardId:
                i = new Intent(this, MainActivity.class);
                startActivity(i);
                break;
            case R.id.aboutCardId:
                i = new Intent(this, AboutInfo.class);
                startActivity(i);
                break;
            default:
                break;
        }
    }
}
