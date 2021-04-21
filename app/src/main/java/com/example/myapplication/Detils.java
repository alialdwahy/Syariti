package com.example.myapplication;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class Detils extends AppCompatActivity {
    DB_Sqlitecar db;
    Button sal;
    Button rent;
    ImageView imageView1;
    TextView txtName1, txtPrice1, txtmodel1, txttype1,  tatlin, txtger, txter, txtxc, txtyui;
    ArrayList<CarsI> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detils);


        txtmodel1 = (TextView) findViewById(R.id.textView5);
        txtName1 = (TextView) findViewById(R.id.textView);
        txtPrice1 = (TextView) findViewById(R.id.textView7);
        imageView1 = (ImageView) findViewById(R.id.imageView2);
        txttype1 = (TextView) findViewById(R.id.textView6);
        tatlin = (TextView) findViewById(R.id.accdent);
        txtger = (TextView) findViewById(R.id.textView19);
        txter = (TextView) findViewById(R.id.linc);
        txtxc = (TextView) findViewById(R.id.textView9);
        txtyui = (TextView) findViewById(R.id.textView20);
        sal = findViewById(R.id.sal);
        rent = findViewById(R.id.ren);
        db = new   DB_Sqlitecar(this);

        CarsI carsI = (CarsI) getIntent().getExtras().getSerializable("CarsI");

        txtmodel1.setText(carsI.getModel());
        txtName1.setText(carsI.getName());
        txtPrice1.setText(carsI.getPrice());
        txttype1.setText(carsI.getType());
        tatlin.setText(carsI.getLicens());
        txtger.setText(carsI.getgeartype());
        txter.setText(carsI.getAccident());
        txtxc.setText(carsI.getTraveleddistanc());
        txtyui.setText(carsI.gettypereqouse());
        byte[] Image = carsI.getImage();
        Bitmap bitmap = BitmapFactory.decodeByteArray(Image, 0, Image.length);
        imageView1.setImageBitmap(bitmap);

        sal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Detils.this, MainActivity.class);
                startActivity(i);
            }
        });

        rent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), HomeUser.class);
                startActivity(intent);
                finish();
            }
        });





    }

    private static void setAdapter(Carsad adapter) {
    }

}


