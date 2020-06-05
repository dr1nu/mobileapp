package com.example.sportbook;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class bookingResults extends AppCompatActivity implements View.OnClickListener {
    DatabaseHelper databaseHelper;

    TextView venue,locality,sport;
    //initlaising buttons
    Button book;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking_results);
        databaseHelper= new DatabaseHelper(this);
        venue= findViewById(R.id.venueName);
        locality=findViewById(R.id.locality);
        sport=findViewById(R.id.sport);
        book=findViewById(R.id.book);

        book.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

    }

    public void getData(String loc){
        Cursor cursor= databaseHelper.getVenuebyLoc(loc);
    }
}
