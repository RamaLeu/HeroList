package com.example.heroapi;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class NewEntryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_entry);

        Intent intent = getIntent();
        Hero hero = (Hero) intent.getSerializableExtra(Adapter.ENTRY);
        Toast.makeText(this, hero.getName(), Toast.LENGTH_SHORT).show();


        CheckBox checkboxLithuania = findViewById(R.id.Unknown);
        CheckBox checkboxLatvia = findViewById(R.id.hidden);
        CheckBox checkboxCustomValue =  findViewById(R.id.jeff);

        RadioGroup groupStrength = findViewById(R.id.strength);
        RadioButton customButton = findViewById(R.id.custom_value_rb);

        Spinner spinnerOccupation = findViewById(R.id.occupation);
        ArrayList <String> updateList = new ArrayList<String>();
        updateList.add(hero.getWork());
        updateList.add(getResources().getString(R.string.new_entry_last_update_1));
        updateList.add(getResources().getString(R.string.new_entry_last_update_2));
        updateList.add(getResources().getString(R.string.new_entry_last_update_3));

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(
                this,
                android.R.layout.simple_spinner_item,
                updateList
        );

        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerOccupation.setAdapter(dataAdapter);

        EditText fullNameET = findViewById(R.id.fullName);

        Button btnDisplay = findViewById(R.id.display_selected_btn);

        checkboxCustomValue.setText(hero.getName());
        customButton.setText(String.valueOf(hero.getStrength()));
        fullNameET.setText(hero.getFullName());

        btnDisplay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = "";
                if (checkboxLithuania.isChecked()){
                    name += checkboxLithuania.getText().toString()+ ", ";
                }
                if (checkboxLatvia.isChecked()){
                    name += checkboxLatvia.getText().toString()+ ", ";
                }
                if (checkboxCustomValue.isChecked()){
                    name += checkboxCustomValue.getText().toString()+ ", ";
                }

                int selectedId = groupStrength.getCheckedRadioButtonId();

                RadioButton selectedBtn = findViewById(selectedId);
                String work = String.valueOf(fullNameET.getText());

                String fullName = String.valueOf(spinnerOccupation.getSelectedItem());

                String strength = String.valueOf(selectedBtn.getText());

                Hero hero = new Hero(name, strength, work, fullName);

                Toast.makeText(
                        NewEntryActivity.this,

                        "Name: "+hero.getName()+"\n"+
                           "Strength: " + hero.getStrength()+ " \n"+
                                "Occupation: "+hero.getWork()+" \n"+
                                "Full name: "+hero.getFullName()+" \n",
                        Toast.LENGTH_SHORT
                ).show();



            }
        });



    }
}