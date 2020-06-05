package com.example.sportbook;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TimePicker;

import java.util.Calendar;

public class booking extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    EditText date,startTime,endTime;
    DatePickerDialog picker;
    TimePickerDialog tPicker;
    Button book;
    DatabaseHelper databaseHelper;
    Spinner sport,localityItems;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking);
        databaseHelper= new DatabaseHelper(this);
        Toolbar toolbar= findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        book= findViewById(R.id.book);



        //SETTING SPINNERS AND THEIR VALUES
        sport = findViewById(R.id.sport);
        String[] sportItems = getResources().getStringArray(R.array.sportItems);
        localityItems = findViewById((R.id.localities));
        String [] locItems= getResources().getStringArray(R.array.localityItems);

        SpinnerAdapter adapter = new ArrayAdapter<String>(this, R.layout.spinner_item, sportItems);
        sport.setAdapter(adapter);
        sport.setOnItemSelectedListener(this);
        SpinnerAdapter locAdapter = new ArrayAdapter<String>(this, R.layout.spinner_item,locItems);
        localityItems.setAdapter(locAdapter);

        // method to get calendar widget so that user can select the date
        date= findViewById(R.id.date);
        date.setInputType(InputType.TYPE_NULL);
        date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar cldr = Calendar.getInstance();
                int day = cldr.get(Calendar.DAY_OF_MONTH);
                int month = cldr.get(Calendar.MONTH);
                int year = cldr.get(Calendar.YEAR);
                // date picker dialog
                picker = new DatePickerDialog(booking.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                                date.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
                            }
                        }, year, month, day);

                picker.show();
            }

        });
// method to get a widget so that user can select the time
        startTime= findViewById(R.id.startTime);
        startTime.setInputType(InputType.TYPE_NULL);
        startTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar cldr = Calendar.getInstance();
                int hour = cldr.get(Calendar.HOUR_OF_DAY);
                int minutes = cldr.get(Calendar.MINUTE);
                // time picker dialog
                tPicker = new TimePickerDialog(booking.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int sHour, int sMinute) {
                        startTime.setText(sHour + ":" + sMinute);
                    }
                }, hour, minutes, true);
                tPicker.show();
            }
        });

        endTime= findViewById(R.id.endTime);
        endTime.setInputType(InputType.TYPE_NULL);
        endTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar cldr = Calendar.getInstance();
                int hour = cldr.get(Calendar.HOUR_OF_DAY);
                int minutes = cldr.get(Calendar.MINUTE);
                // time picker dialog
                tPicker = new TimePickerDialog(booking.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int sHour, int sMinute) {
                        endTime.setText(sHour + ":" + sMinute);
                    }
                }, hour, minutes, true);
                tPicker.show();
            }
        });

    }




    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
// menu in toolbar
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.booking_menu,menu);
        return true;
    }
    public void selectMenuOption(MenuItem item){
        Intent intent = new Intent(booking.this,viewBookings_Activity.class);
        startActivity(intent);
    }
}
