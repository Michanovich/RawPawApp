package com.viktor.listtest;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.viktor.listtest.DBModels.Cat;
import com.viktor.listtest.Formulas.Cat_Formulas;

public class CreateCatProfile extends AppCompatActivity {

        public EditText catsName;
        public EditText idealWeight;
        public Spinner activitylevel;
        public Spinner massSpinner;

        private AdView mAdView;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_create_cat_profile);

            catsName = findViewById(R.id.et_catsName);
            idealWeight = findViewById(R.id.et_cat_idealWeight);

            this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

            activitylevel = findViewById(R.id.spinner_cat_activitylevel);
            String[] cat_activitylevel = new String[]{"2% = Low", "2.5% = Moderate", "3% = Active", "3.5% = Active", "4% = 10-12 months old or highly active", "4,5% = 10-12 months old or highly active",
                    "5% = 8-10 months old", "5.5% = 8-10 months old", "6% = 6-8 months old", "6.5% = 6-8 months old", "7% = 5-6 months old", "7.5% = 5-6 months old",
                    "8% = 3-4 months old", "8.5% = 3-4 months old", "9% = 4-12 weeks old", "9.5% = 4-12 weeks old", "10% = 4-12 weeks old"};
            ArrayAdapter<String> adapter1 = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, cat_activitylevel);
            activitylevel.setAdapter(adapter1);

            massSpinner = findViewById(R.id.massSpinner);
            String[] cat_massunit = new String[]{"Kilograms", "Pounds"};
            ArrayAdapter<String> adapter3 = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, cat_massunit);
            massSpinner.setAdapter(adapter3);

            MobileAds.initialize(this, "ca-app-pub-9102373556091600~1368769916");
            mAdView = findViewById(R.id.adView);
            AdRequest adRequest = new AdRequest.Builder().build();
            mAdView.loadAd(adRequest);
        }

        public void saveCatProfile(View view) {
            String name = catsName.getText().toString();

            if (name.equals("") || idealWeight.getText().toString().equals("") || (name.equals("")) && (idealWeight.getText().toString().equals(""))) {
                Toast.makeText(getApplicationContext(), "Please input name and ideal weight", Toast.LENGTH_SHORT).show();
            } else {
                double cat_foodmenu = Cat_Formulas.calculateFormula(idealWeight.getText().toString(), activitylevel.getSelectedItem().toString(),
                        massSpinner.getSelectedItem().toString());
                Cat.insertIntoCat(getApplicationContext(), catsName.getText().toString(), Double.valueOf(idealWeight.getText().toString()), activitylevel.getSelectedItem().toString(),
                         cat_foodmenu, massSpinner.getSelectedItem().toString());

                Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);

                Toast.makeText(getApplicationContext(), "Data saved successfully", Toast.LENGTH_SHORT).show();
            }
        }
}
