package com.example.myapplication;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

public class HomeCar extends AppCompatActivity {
    GridView gridView;
    ArrayList<CarsI> list;
    Carsad adapter = null;
    DB_Sqlitecar db;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_car);


        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavig);
        bottomNavigationView.setSelectedItemId(R.id.homec);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()) {
                    case R.id.homec:

                        return true;
                    case R.id.addcar:
                        startActivity(new Intent(getApplicationContext(), AddCar.class));
                        overridePendingTransition(0, 0);
                        finish();
                        return true;
                    case R.id.updat:
                        startActivity(new Intent(getApplicationContext(), Update.class));
                        overridePendingTransition(0, 0);
                        finish();
                        return true;
                    case R.id.dele:
                        startActivity(new Intent(getApplicationContext(), DeletCar.class));
                        overridePendingTransition(0, 0);
                        finish();
                        return true;
                    case R.id.bnk:
                        startActivity(new Intent(getApplicationContext(), Login.class));
                        overridePendingTransition(0, 0);
                        finish();
                        return true;
                }
                return false;
            }
        });
        db = new   DB_Sqlitecar(this);
        gridView = (GridView) findViewById(R.id.gridView);
        list = new ArrayList<>();
        adapter = new Carsad(this, R.layout.carsi, list);
        gridView.setAdapter(adapter);

        Cursor cursor = db.getData("SELECT * FROM cars1 ");
        list.clear();
        while (cursor.moveToNext()) {
            String model = cursor.getString(3);
            String name = cursor.getString(1);
            String price = cursor.getString(11);
            byte[] image = cursor.getBlob(10);
            String type = cursor.getString(2);
            String Traveleddistanc = cursor.getString(4);
            String Accident = cursor.getString(6);
            String Licens = cursor.getString(5);
            String geartyp = cursor.getString(8);
            String typereqouse = cursor.getString(9);
            String Carno = cursor.getString(0);
            list.add(new CarsI(name, price, image, model,type ,Traveleddistanc, Carno,  Accident,  Licens, geartyp, typereqouse ));
        }
        adapter.notifyDataSetChanged();



        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override

            public void onItemClick(AdapterView<?> parent, View view, int position, long carnum) {

              CarsI carsI = list.get(position);
                    Intent i = new Intent(HomeCar.this, Detils.class);
                    i.putExtra("CarsI", carsI);
                    startActivity(i);
                }
        });

    }

    }