package com.viktor.listtest;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.viktor.listtest.DBModels.Dog;

public class MainActivity extends AppCompatActivity {
    private ListView dogNameList;
    private TextView id_dogName;
    private DBHelper dbHelper;
    private DBReadAdapterDog dbReadAdapterDog;
    private Dog dog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initializeDogNameList();
    }

    /*------------------------- LIST VIEW INIT -------------------------*/
    private void initializeDogNameList() {
        dogNameList = findViewById(R.id.dogNameList);

        dbReadAdapterDog = new DBReadAdapterDog(this, Dog.getAllDogNames(getApplicationContext()));

        dogNameList.setAdapter(dbReadAdapterDog);
        dogNameList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l){
                Dog selected_dog = (Dog) adapterView.getItemAtPosition(i);
                Intent intent = new Intent(getApplicationContext(), SelectedDogProfile.class);
                Bundle bundle = new Bundle();
                bundle.putString("dog", new Gson().toJson(selected_dog));
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });

        dogNameList.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                final Dog selected_dog = (Dog) parent.getItemAtPosition(position);

                AlertDialog.Builder dialog = new AlertDialog.Builder(MainActivity.this);
                dialog.setCancelable(false);
                dialog.setTitle("Delete " + selected_dog.getDog_name());
                dialog.setMessage("Are you sure you want to delete this profile?" );
                dialog.setIcon(R.drawable.delete);
                dialog.setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        Dog.deleteDog(getApplicationContext(), selected_dog.getDog_id());

                        Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent);

                        Toast.makeText(getApplicationContext(), "Profile " + selected_dog.getDog_name() + " deleted successfully", Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                    }
                })
                        .setNegativeButton("Cancel ", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        });

                final AlertDialog alert = dialog.create();
                alert.show();

                return true;
            }
        });
    }
}
