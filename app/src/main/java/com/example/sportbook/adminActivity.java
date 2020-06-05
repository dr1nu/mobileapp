package com.example.sportbook;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;
import android.widget.Toast;

public class adminActivity extends Activity implements AdapterView.OnItemSelectedListener {
    private static final int GALLERY_REQUEST_CODE = 123;
    TextView venue;
    ImageButton imageButton;
    DatabaseHelper databaseHelper;
    Spinner sport, localityItems;
    Button add,addanother;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);
        databaseHelper= new DatabaseHelper(this);
        venue=findViewById(R.id.venueName);
        sport = findViewById(R.id.sport);
        String[] sportItems = getResources().getStringArray(R.array.sportItems);
        localityItems = findViewById((R.id.localities));
        String [] locItems= getResources().getStringArray(R.array.localityItems);
//Populating drop down menus with sport items and locality items
        SpinnerAdapter adapter = new ArrayAdapter<String>(this, R.layout.spinner_item, sportItems);
        sport.setAdapter(adapter);
        sport.setOnItemSelectedListener(this);
        SpinnerAdapter locAdapter = new ArrayAdapter<String>(this, R.layout.spinner_item,locItems);
        localityItems.setAdapter(locAdapter);

        // advanced intent to fetch picture from gallery and store it in imagebutton
        imageButton = findViewById(R.id.post_image);
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent picture_intent = new Intent();
                picture_intent.setType("image/*");
                picture_intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(picture_intent, "Select Picture"), GALLERY_REQUEST_CODE);

            }
        });

        add= findViewById(R.id.add);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //fetching values inputted by user
                String venueValue= venue.getText().toString();
                String sportValue= sport.getSelectedItem().toString();
                String localityValue= localityItems.getSelectedItem().toString();
                Bitmap imageValue= imageView2Bitmap(imageButton);
                //insert values into the database
                databaseHelper.insertVenue(venueValue,sportValue,localityValue,imageValue);
                Toast.makeText(adminActivity.this, "Added Successfully", Toast.LENGTH_SHORT).show();
                //going back to login screen
                Intent intent = new Intent(adminActivity.this,MainActivity.class);
                startActivity(intent);

            }
        });

        //same method but instead of returning to login it returns to add venue page so that another venue may be inserted

        addanother= findViewById(R.id.addanother);
        addanother.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String venueValue= venue.getText().toString();
                String sportValue= sport.getSelectedItem().toString();
                String localityValue= localityItems.getSelectedItem().toString();
                Bitmap imageValue= imageView2Bitmap(imageButton);
                databaseHelper.insertVenue(venueValue,sportValue,localityValue,imageValue);
                Intent intent = new Intent(adminActivity.this,adminActivity.class);
                startActivity(intent);
                Toast.makeText(adminActivity.this, "Added Successfully", Toast.LENGTH_SHORT).show();

            }
        });
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == GALLERY_REQUEST_CODE && resultCode == RESULT_OK && data != null) {
            Uri imageData = data.getData();
            imageButton.setImageURI(imageData);
        }
    }
// method to convert imageView to Bitmap so it can be stored in the database.
    private Bitmap imageView2Bitmap(ImageButton view){
        Bitmap bitmap = ((BitmapDrawable)view.getDrawable()).getBitmap();
        return bitmap;
    }

    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
