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

public class HomeUser extends AppCompatActivity {
    GridView gridView;
    ArrayList<CarsI> list;
    Carsad adapter = null;
    DB_Sqlitecar db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_user);
        BottomNavigationView bottomNavigationView = findViewById(R.id.naviues);
        bottomNavigationView.setSelectedItemId(R.id.homec);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()) {
                    case R.id.home1:

                        return true;
                    case R.id.profail:
                        startActivity(new Intent(getApplicationContext(),AdminH.class));
                        overridePendingTransition(0, 0);
                        finish();
                        return true;
                    case R.id.logout:
                        startActivity(new Intent(getApplicationContext(), Login.class));
                        overridePendingTransition(0, 0);
                        finish();
                        return true;
                    default:
                        throw new IllegalStateException("Unexpected value: " + item.getItemId());
                }

                }
            });
        db = new   DB_Sqlitecar(this);
        gridView = (GridView) findViewById(R.id.gridV);
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
            String Licens = cursor.getString(7);
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
                Intent it = new Intent(HomeUser.this, Detils.class);
                it.putExtra("CarsI", carsI);
                startActivity(it);
                finish();
            }
        });

    }
}