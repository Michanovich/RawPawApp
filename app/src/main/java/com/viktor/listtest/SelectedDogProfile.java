package com.viktor.listtest;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.viktor.listtest.AboutFood.EdibleBones;
import com.viktor.listtest.AboutFood.LiverOtherOrgans;
import com.viktor.listtest.AboutFood.MuscleMeat;
import com.viktor.listtest.AboutFood.VegeSNF;
import com.viktor.listtest.DBModels.Dog;

public class SelectedDogProfile extends AppCompatActivity implements View.OnClickListener {
    private TextView dogProfileName;
    private TextView dogIdealWeight;
    private TextView dogActivityLevel;
    private TextView dogAge;
    private TextView dogFoodMenu;
    private TextView dogWeeklyFoodMenu;
    private TextView dogMonthlyFoodMenu;
    private TextView meatGrams;
    private TextView meatGrams2;
    private TextView meatGramsWeek;
    private TextView meatGramsMonth;
    private TextView meatGrams2Week;
    private TextView meatGrams2Month;
    private TextView boneGrams;
    private TextView boneGrams2;
    private TextView boneGramsWeek;
    private TextView boneGramsMonth;
    private TextView boneGrams2Week;
    private TextView boneGrams2Month;
    private TextView otherOrgansGrams;
    private TextView otherOrganGrams2;
    private TextView otherOrgansGramsWeek;
    private TextView otherOrgansGramsMonth;
    private TextView otherOrganGrams2Week;
    private TextView otherOrganGrams2Month;
    private TextView fruitGrams;
    private TextView fruitGramsWeek;
    private TextView fruitGramsMonth;
    private TextView liverGrams;
    private TextView liverGramsWeek;
    private TextView liverGramsMonth;
    private TextView liverGrams2;
    private TextView liverGramsWeek2;
    private TextView liverGramsMonth2;
    private TextView vegetableGrams;
    private TextView vegetableGramsWeek;
    private TextView vegetableGramsMonth;
    private TextView seedsnutsGrams;
    private TextView seedsnutsGramsWeek;
    private TextView seedsnutsGramsMonth;
    private Dog dog;
    private CardView meatCardId;
    private CardView meatCardId2;
    private CardView boneCardId;
    private CardView boneCardId2;
    private CardView liverCardId;
    private CardView liverCardId2;
    private CardView otherOrgansCardId;
    private CardView otherOrgansCardId2;
    private CardView vegetablesCardId;
    private CardView fruitCardId;
    private CardView seedsnutsCardId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selected_dog_profile);

        dogProfileName = findViewById(R.id.dogProfileName);
        dogIdealWeight = findViewById(R.id.dogIdealWeight);
        dogActivityLevel = findViewById(R.id.dogActivityLevel);
        dogAge = findViewById(R.id.dogAge);
        dogFoodMenu = findViewById(R.id.tv_foodmenu);
        dogWeeklyFoodMenu = findViewById(R.id.tv_weeklyfoodmenu);
        dogMonthlyFoodMenu = findViewById(R.id.tv_monthlyfoodmenu);
        meatGrams = findViewById(R.id.tv_meatGrams);
        boneGrams = findViewById(R.id.tv_boneGrams);
        otherOrgansGrams = findViewById(R.id.tv_otherOgrnasGrams);
        liverGrams = findViewById(R.id.tv_liverGrams);
        liverGrams2 = findViewById(R.id.tv_liverGrams2);
        meatGrams2 = findViewById(R.id.tv_meatGrams2);
        boneGrams2 = findViewById(R.id.tv_boneGrams2);
        otherOrganGrams2 = findViewById(R.id.tv_otherOrgansGrams2);
        fruitGrams = findViewById(R.id.tv_fruitGrams);
        meatGramsWeek = findViewById(R.id.tv_meatGramsWeek);
        meatGramsMonth = findViewById(R.id.tv_meatGramsMonth);
        meatGrams2Week = findViewById(R.id.tv_meatGrams2Week);
        meatGrams2Month = findViewById(R.id.tv_meatGrams2Month);
        boneGramsWeek = findViewById(R.id.tv_boneGramsWeek);
        boneGramsMonth = findViewById(R.id.tv_boneGramsMonth);
        boneGrams2Week = findViewById(R.id.tv_boneGrams2Week);
        boneGrams2Month = findViewById(R.id.tv_boneGrams2Month);
        otherOrgansGramsWeek = findViewById(R.id.tv_otherOrgansGramsWeek);
        otherOrgansGramsMonth = findViewById(R.id.tv_otherOrgansGramsMonth);
        otherOrganGrams2Week = findViewById(R.id.tv_otherOrgansGrams2Week);
        otherOrganGrams2Month = findViewById(R.id.tv_otherOrgansGrams2Month);
        liverGramsWeek = findViewById(R.id.tv_liverGramsWeek);
        liverGramsWeek2 = findViewById(R.id.tv_liverGrams2Week);
        liverGramsMonth = findViewById(R.id.tv_liverGramsMonth);
        liverGramsMonth2 = findViewById(R.id.tv_liverGrams2Month);
        fruitGramsWeek = findViewById(R.id.tv_fruitGramsWeek);
        fruitGramsMonth = findViewById(R.id.tv_fruitGramsMonth);
        vegetableGrams = findViewById(R.id.tv_vegetablesGrams);
        vegetableGramsWeek = findViewById(R.id.tv_vegetablesGramsWeek);
        vegetableGramsMonth = findViewById(R.id.tv_vegetablesGramsMonth);
        seedsnutsGrams = findViewById(R.id.tv_seedsnutsGrams);
        seedsnutsGramsWeek = findViewById(R.id.tv_seedsnutsGramsWeek);
        seedsnutsGramsMonth = findViewById(R.id.tv_seedsnutsGramsMonth);

        dog = new Gson().fromJson(getIntent().getStringExtra("dog"), Dog.class);

        String greater = "";
        String lower = "";
        Double weekly = 0.0;
        Double monthly = 0.0;
        Double weeklyMeat1 = 0.0;
        Double weeklyMeat2 = 0.0;
        Double weeklyOther = 0.0;
        Double monthlyMeat1 = 0.0;
        Double monthlyMeat2 = 0.0;
        Double monthlyOther = 0.0;
        Double weeklyVege = 0.0;
        Double weeklyFSN = 0.0;
        Double monthlyVege = 0.0;
        Double monthlyFNS = 0.0;

        if (dog.getDog_massunit().equals("Kilograms")) {
            double number = dog.getDog_foodmenu();

            greater = " kg";
            lower = " g";
            weekly = (number / 1000) * 7;
            monthly = (number / 1000) * 30;
            weeklyMeat1 = ((number * 0.8) * 7) / 1000;
            weeklyMeat2 = ((number * 0.7) * 7) / 1000;
            weeklyOther = ((number * 0.05) * 7) / 1000;
            monthlyMeat1 = ((number * 0.8) * 30) / 1000;
            monthlyMeat2 = ((number * 0.7) * 30) / 1000;
            monthlyOther = ((number * 0.05) * 30) / 1000;
            weeklyVege = ((number * 0.06) * 7) / 1000;
            monthlyVege = ((number * 0.06) * 30) / 1000;
            weeklyFSN = ((number * 0.02) * 7) / 1000;
            monthlyFNS = ((number + 0.02) * 30) / 1000;
        } else if (dog.getDog_massunit().equals("Pounds")) {
            double number = dog.getDog_foodmenu();

            greater = " lbs";
            lower = " oz";
            weekly = (number / 16) * 7;
            monthly = (number / 16) * 30;
            weeklyMeat1 = ((number * 0.8) * 7) / 16;
            weeklyMeat2 = ((number * 0.7) * 7) / 16;
            weeklyOther = ((number * 0.05) * 7) / 16;
            monthlyMeat1 = ((number * 0.8) * 30) / 16;
            monthlyMeat2 = ((number * 0.7) * 30) / 16;
            monthlyOther = ((number * 0.05) * 30) / 16;
            weeklyVege = ((number * 0.06) * 7) / 16;
            monthlyVege = ((number * 0.06) * 30) / 16;
            weeklyFSN = ((number * 0.02) * 7) / 16;
            monthlyFNS = ((number + 0.02) * 30) / 16;
        }

        dogProfileName.setText(dog.getDog_name());
        dogIdealWeight.setText(Double.toString(dog.getDog_idealweight()) + greater);
        dogActivityLevel.setText(dog.getDog_activitylevel());
        dogAge.setText(dog.getDog_age());
        dogFoodMenu.setText(String.format("%.2f", dog.getDog_foodmenu()) + lower + " daily");
        dogWeeklyFoodMenu.setText(String.format("%.2f", weekly) + greater + " weekly");
        dogMonthlyFoodMenu.setText(String.format("%.2f", monthly) + greater + " monthly");
        meatGrams.setText("D: " + methodOne()[0] + lower);
        boneGrams.setText("D: " + methodOne()[5] + lower);
        otherOrgansGrams.setText("D: " + methodOne()[1] + lower);
        liverGrams.setText("D: " + methodOne()[1] + lower);
        liverGrams2.setText("D: " + methodOne()[1] + lower);
        meatGrams2.setText("D: " + methodOne()[2] + lower);
        boneGrams2.setText("D: " + methodOne()[5] + lower);
        otherOrganGrams2.setText("D: " + methodOne()[1] + lower);
        fruitGrams.setText("D: " + methodOne()[4] + lower);
        meatGramsWeek.setText("W: " + String.format("%.2f", weeklyMeat1) + greater);
        meatGramsMonth.setText("M: " + String.format("%.2f", monthlyMeat1) + greater);
        meatGrams2Week.setText("W: " + String.format("%.2f", weeklyMeat2) + greater);
        meatGrams2Month.setText("M: " + String.format("%.2f", monthlyMeat2) + greater);
        boneGramsWeek.setText("W: " + String.format("%.2f", weeklyOther) + greater);
        boneGramsMonth.setText("M: " + String.format("%.2f", monthlyOther) + greater);
        boneGrams2Week.setText("W: " + String.format("%.2f", weeklyOther) + greater);
        boneGrams2Month.setText("M: " + String.format("%.2f", monthlyOther) + greater);
        otherOrgansGramsWeek.setText("W: " + String.format("%.2f", weeklyOther) + greater);
        otherOrgansGramsMonth.setText("M: " + String.format("%.2f", monthlyOther) + greater);
        otherOrganGrams2Week.setText("W: " + String.format("%.2f", weeklyOther) + greater);
        otherOrganGrams2Month.setText("M: " + String.format("%.2f", monthlyOther) + greater);
        liverGramsWeek.setText("W: " + String.format("%.2f", weeklyOther) + greater);
        liverGramsWeek2.setText("W: " + String.format("%.2f", weeklyOther) + greater);
        liverGramsMonth.setText("M: " + String.format("%.2f", monthlyOther) + greater);
        liverGramsMonth2.setText("M: " + String.format("%.2f", monthlyOther) + greater);
        fruitGramsWeek.setText("W: " + String.format("%.2f", weeklyOther) + greater);
        fruitGramsMonth.setText("M: " + String.format("%.2f", monthlyOther) + greater);
        vegetableGrams.setText("D: " + methodOne()[3] + lower);
        vegetableGramsWeek.setText("W: " + String.format("%.2f", weeklyVege) + greater);
        vegetableGramsMonth.setText("M: " + String.format("%.2f", monthlyVege) + greater);
        seedsnutsGrams.setText("D: " + methodOne()[4] + lower);
        seedsnutsGramsWeek.setText("W: " + String.format("%.2f", weeklyFSN) + greater);
        seedsnutsGramsMonth.setText("M: " + String.format("%.2f", monthlyFNS) + greater);

        meatCardId = findViewById(R.id.meatCardId);
        meatCardId2 = findViewById(R.id.meatCardId2);
        boneCardId = findViewById(R.id.boneCardId);
        boneCardId2 = findViewById(R.id.boneCardId2);
        liverCardId = findViewById(R.id.liverCardId);
        liverCardId2 = findViewById(R.id.liverCardId2);
        otherOrgansCardId = findViewById(R.id.otherOrgansCardId);
        otherOrgansCardId2 = findViewById(R.id.otherOrgansCardId2);
        vegetablesCardId = findViewById(R.id.vegetablesCardId);
        fruitCardId = findViewById(R.id.fruitCardId);
        seedsnutsCardId = findViewById(R.id.seedsnutsCardId);

        meatCardId.setOnClickListener(this);
        meatCardId2.setOnClickListener(this);
        boneCardId.setOnClickListener(this);
        boneCardId2.setOnClickListener(this);
        liverCardId.setOnClickListener(this);
        liverCardId2.setOnClickListener(this);
        otherOrgansCardId.setOnClickListener(this);
        otherOrgansCardId2.setOnClickListener(this);
        vegetablesCardId.setOnClickListener(this);
        fruitCardId.setOnClickListener(this);
        seedsnutsCardId.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent i;

        switch (v.getId()) {
            case R.id.meatCardId:
                i = new Intent(this, MuscleMeat.class);
                startActivity(i);
                break;
            case R.id.meatCardId2:
                i = new Intent(this, MuscleMeat.class);
                startActivity(i);
                break;
            case R.id.boneCardId:
                i = new Intent(this, EdibleBones.class);
                startActivity(i);
                break;
            case R.id.boneCardId2:
                i = new Intent(this, EdibleBones.class);
                startActivity(i);
                break;
            case R.id.liverCardId:
                i = new Intent(this, LiverOtherOrgans.class);
                startActivity(i);
                break;
            case R.id.liverCardId2:
                i = new Intent(this, LiverOtherOrgans.class);
                startActivity(i);
                break;
            case R.id.otherOrgansCardId:
                i = new Intent(this, LiverOtherOrgans.class);
                startActivity(i);
                break;
            case R.id.otherOrgansCardId2:
                i = new Intent(this, LiverOtherOrgans.class);
                startActivity(i);
                break;
            case R.id.vegetablesCardId:
                i = new Intent(this, VegeSNF.class);
                startActivity(i);
                break;
            case R.id.fruitCardId:
                i = new Intent(this, VegeSNF.class);
                startActivity(i);
                break;
            case R.id.seedsnutsCardId:
                i = new Intent(this, VegeSNF.class);
                startActivity(i);
                break;
            default:
                break;
        }
    }

    private AlertDialog AskOption() {
        AlertDialog myDialogBox = new AlertDialog.Builder(this)
                .setTitle("Delete")
                .setMessage("Do you want to delete " + dog.getDog_name() + "?")
                .setIcon(R.drawable.delete)

                .setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Dog.deleteDog(getApplicationContext(), dog.getDog_id());

                        Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent);

                        Toast.makeText(getApplicationContext(), "Profile " + dog.getDog_name() + " deleted successfully", Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                    }
                })

                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .create();
        return myDialogBox;
    }

    public void deleteDogProfile(View view) {
        AlertDialog dialog = AskOption();
        dialog.show();
    }

    public void editDogProfile(View view) {
        Dog thisdog = dog;

        Intent intent = new Intent(getApplicationContext(), EditDogProfile.class);
        Bundle bundle = new Bundle();
        bundle.putString("dog", new Gson().toJson(thisdog));
        intent.putExtras(bundle);
        startActivity(intent);
    }

    public String[] methodOne() {
        double number = dog.getDog_foodmenu();

        String firstGrams = String.format("%.2f", number * 0.8);
        String secondGrams = String.format("%.2f", number * 0.05);
        String thirdGrams = String.format("%.2f", number * 0.7);
        String forthGrams = String.format("%.2f", number * 0.06);
        String fifthGrams = String.format("%.2f", number * 0.02);
        String sixthGrams = String.format("%.2f", number * 0.1);

        return new String[]{firstGrams, secondGrams, thirdGrams, forthGrams, fifthGrams, sixthGrams};
    }
}
