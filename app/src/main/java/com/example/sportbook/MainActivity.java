package com.example.sportbook;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {
    EditText username, password;
    Button login;
    DatabaseHelper databaseHelper;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        databaseHelper= new DatabaseHelper(this);
        textView = findViewById(R.id.registerinfo);
        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        login = findViewById(R.id.login);
// creating a span to customize substring register now to be clickable and start a new activity
        String text = "Don't have an account? Register Now";
        SpannableString ss = new SpannableString(text);

        ClickableSpan clickableSpan = new ClickableSpan() {
            @Override
            public void onClick(View widget) {
                Intent intent = new Intent(widget.getContext(), registerActivity.class);
                startActivity(intent);
            }
// setting color to the span
            @Override
            public void updateDrawState(@NonNull TextPaint ds) {
                super.updateDrawState(ds);
                ds.setColor(getResources().getColor(R.color.register));
            }
        };
        ss.setSpan(clickableSpan, 23, 35, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        textView.setText(ss);
        textView.setMovementMethod(LinkMovementMethod.getInstance());



        login.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                String usernameValue = username.getText().toString();
                String passwordValue = password.getText().toString();

                //getting admin page
                if(usernameValue.equals("admin")&& passwordValue.equals("admin")){
                  Intent intent= new Intent(MainActivity.this, adminActivity.class);
                  startActivity(intent);
                  Toast.makeText(MainActivity.this, "Login Successful", Toast.LENGTH_SHORT).show();
                }
                //checking login credentials
                if (databaseHelper.isLoginValid(usernameValue,passwordValue)){
                    Intent intent= new Intent(MainActivity.this, booking.class);
                    startActivity(intent);
                    Toast.makeText(MainActivity.this, "Login Successful", Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(MainActivity.this, "Incorrect Details", Toast.LENGTH_SHORT).show();
                }
            }
        });


    }
}


