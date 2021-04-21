package com.example.myapplication;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class      AdminH extends AppCompatActivity {
Button log;
   TextView email,phone,whats;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_h);


        whats = findViewById(R.id.textView23);
        email = findViewById(R.id.textView21);
        phone = findViewById(R.id.textView22);
        log = findViewById(R.id.button);







        log.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),HomeUser.class);
                startActivity(intent);
                finish();
            }
        });
        email.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("IntentReset")
            @Override
            public void onClick(View v) {
                Intent email = new Intent(Intent.ACTION_SEND);
                email.setData(Uri.parse("com.google.android.gm "));
                email.setType("messag/rfc822");
                email.putExtra(Intent.EXTRA_EMAIL,"aligowi358@gmail.com");
                if (email.resolveActivity(getPackageManager()) != null) {
                    startActivity(email);

                }
            }
        });
        phone.setOnClickListener(new View.OnClickListener() {
                                     @Override
                                     public void onClick(View v) {
                                         Intent intent = new Intent(Intent.ACTION_DIAL);
                                         intent.setData(Uri.parse("tel:0915334953"));
                                         if (intent.resolveActivity(getPackageManager()) != null) {
                                             startActivity(intent);

                                         }
                                     }
                                 });
        whats.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent emai = new Intent(Intent.ACTION_VIEW);
                emai.setData(Uri.parse("whatsapp://send?text=the text message goes here"));
                if (emai.resolveActivity(getPackageManager()) != null) {
                    startActivity(emai);

                }
            }
        });

    }
}