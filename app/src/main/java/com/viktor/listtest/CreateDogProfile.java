package com.viktor.listtest;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.viktor.listtest.DBModels.Dog;
import com.viktor.listtest.Formulas.Formulas;

public class CreateDogProfile extends AppCompatActivity {

    public EditText dogsName;
    public EditText idealWeight;
    public Spinner activitylevel;
    public Spinner age;
    public Spinner massSpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_dog_profile);

        dogsName = findViewById(R.id.et_dogsName);
        idealWeight = findViewById(R.id.et_idealWeight);

        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

        activitylevel = findViewById(R.id.spinner_activitylevel);
        String[] dog_activitylevel = new String[]{"Low", "Medium", "High"};
        ArrayAdapter<String> adapter1 = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, dog_activitylevel);
        activitylevel.setAdapter(adapter1);

        age = findViewById(R.id.spinner_age);
        String[] dog_age = new String[]{"2 - 4 months old", "4 - 6 months old", "6 - 8 months old", "8 - 18 months old", "Adult", "Senior"};
        ArrayAdapter<String> adapter2 = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, dog_age);
        age.setAdapter(adapter2);

        massSpinner = findViewById(R.id.massSpinner);
        String[] dog_massunit = new String[]{"Kilograms", "Pounds"};
        ArrayAdapter<String> adapter3 = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, dog_massunit);
        massSpinner.setAdapter(adapter3);
    }

    public void saveDogProfile(View view) {
        String name = dogsName.getText().toString();

        if (name.equals("") || idealWeight.getText().toString().equals("") || (name.equals("")) && (idealWeight.getText().toString().equals(""))) {
            Toast.makeText(getApplicationContext(), "Please input name and ideal weight", Toast.LENGTH_SHORT).show();
        } else {
            double dog_foodmenu = Formulas.calculateFormula(idealWeight.getText().toString(), activitylevel.getSelectedItem().toString(), age.getSelectedItem().toString(),
                    massSpinner.getSelectedItem().toString());
            Dog.insertIntoDog(getApplicationContext(), dogsName.getText().toString(), Double.valueOf(idealWeight.getText().toString()), activitylevel.getSelectedItem().toString(),
                    age.getSelectedItem().toString(), dog_foodmenu, massSpinner.getSelectedItem().toString());

            Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);

            Toast.makeText(getApplicationContext(), "Data saved successfully", Toast.LENGTH_SHORT).show();
        }
    }

//    public void deleteDogProfile(View view) {
//    }
//
//    public void editDogProfile(View view) {
//    }
//
//    public void saveEditedDogProfile(View view) {
//    }
}
