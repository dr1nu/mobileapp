package com.example.sportbook;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.Toast;

public class registerActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener{
    EditText username,password,confirmPass;
    Button register, cancel;
    DatabaseHelper databaseHelper;
    Spinner sport,localityItems;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        databaseHelper= new DatabaseHelper(this);
        username= (EditText) findViewById(R.id.username);
        password= (EditText)findViewById(R.id.password);
        confirmPass=(EditText)findViewById(R.id.confirmPass);
        register= (Button)findViewById(R.id.register);
        cancel= (Button) findViewById(R.id.cancel);

        //SETTING SPINNERS AND THEIR VALUES

        sport = findViewById(R.id.sport);
        final String[] sportItems = new String[]{"Choose a Sport","Football", "Swimming", "Athletics", "Basketball"};
        localityItems = findViewById((R.id.localities));
        String [] locItems= getResources().getStringArray(R.array.localityItems);

        SpinnerAdapter adapter = new ArrayAdapter<String>(this, R.layout.spinner_item, sportItems);

        sport.setAdapter(adapter);
        sport.setOnItemSelectedListener(this);
        SpinnerAdapter locAdapter = new ArrayAdapter<String>(this, R.layout.spinner_item,locItems);
        localityItems.setAdapter(locAdapter);


        //REGISTERING THE USER AND SOME DATA VALIDATION

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String usernameValue= username.getText().toString();
                String passwordValue= password.getText().toString();
                String confirmPassValue= confirmPass.getText().toString();
                String sportValue= sport.getSelectedItem().toString();
                String localityValue= localityItems.getSelectedItem().toString();

                if(usernameValue.length()>1) {
                    //check password and confirm password match
                    if(passwordValue.compareTo(confirmPassValue)==0){
                        if(sportValue != sportItems[0]){
                            //storing values into the database
                            ContentValues contentValues = new ContentValues();

                            contentValues.put("username",usernameValue);
                            contentValues.put("password",passwordValue);
                            contentValues.put("sport",sportValue);
                            contentValues.put("locality",localityValue);

                            databaseHelper.insertUser(contentValues);
                            Toast.makeText(registerActivity.this, "User registered Succesfully", Toast.LENGTH_SHORT).show();
                            //end activity
                            finish();

                        }
                        else{
                            Toast.makeText(registerActivity.this, "Please choose a sport", Toast.LENGTH_SHORT).show();
                        }

                    }
                    else  Toast.makeText(registerActivity.this, "Passwords do not match", Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(registerActivity.this, "Enter Username", Toast.LENGTH_SHORT).show();
                }
            }
        });
//cancel button ends activity and goes back to login.
        cancel.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }



    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }




}

