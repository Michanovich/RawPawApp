package com.viktor.listtest;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.viktor.listtest.DBModels.Dog;
import com.viktor.listtest.Formulas.Formulas;

public class EditDogProfile extends AppCompatActivity {
    private EditText editDogName;
    private EditText editDogIdealWeight;
    private Dog dog;
    public Spinner newActivityLevel;
    public Spinner newDogAge;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_dog_profile);

        editDogName = findViewById(R.id.editNewDogName);
        editDogIdealWeight = findViewById(R.id.editDogsIdealWeight);

        newActivityLevel = findViewById(R.id.newActivitySpinner);
        String[] dog_activitylevel = new String[]{"Low", "Medium", "High"};
        ArrayAdapter<String> adapter1 = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, dog_activitylevel);
        newActivityLevel.setAdapter(adapter1);

        newDogAge = findViewById(R.id.newDogAgeSpinner);
        String[] dog_age = new String[]{"2 - 4 months old", "4 - 6 months old", "6 - 8 months old", "8 - 18 months old", "Adult", "Senior"};
        ArrayAdapter<String> adapter2 = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, dog_age);
        newDogAge.setAdapter(adapter2);

        dog = new Gson().fromJson(getIntent().getStringExtra("dog"), Dog.class);

        editDogName.setText(dog.getDog_name());
        editDogIdealWeight.setText(Double.toString(dog.getDog_idealweight()));
        newActivityLevel.setSelection(((ArrayAdapter) newActivityLevel.getAdapter()).getPosition(dog.getDog_activitylevel()));
        newDogAge.setSelection(((ArrayAdapter) newDogAge.getAdapter()).getPosition(dog.getDog_age()));

        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
    }

    private AlertDialog AskOption() {
        AlertDialog myDialogBox = new AlertDialog.Builder(this)
                .setTitle("Save")
                .setMessage("Do you want to save changes?")
                .setIcon(R.drawable.saveicon)

                .setPositiveButton("Save", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        String newName = editDogName.getText().toString();
                        Double newWeight = Double.valueOf(editDogIdealWeight.getText().toString());

                        dog.setDog_name(newName);
                        dog.setDog_idealweight(newWeight);
                        dog.setDog_activitylevel(newActivityLevel.getSelectedItem().toString());
                        dog.setDog_age(newDogAge.getSelectedItem().toString());
                        dog.setDog_foodmenu(Math.round(Formulas.calculateFormula(editDogIdealWeight.getText().toString(), newActivityLevel.getSelectedItem().toString(),
                                newDogAge.getSelectedItem().toString(), dog.getDog_massunit())));

                        Dog.updateDog(getApplicationContext(), dog);
                        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent);

                        Toast.makeText(getApplicationContext(), "Changes saved successfully", Toast.LENGTH_SHORT).show();
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

    public void saveEditedDogProfile(View view) {
        AlertDialog dialog = AskOption();
        dialog.show();
    }
}
