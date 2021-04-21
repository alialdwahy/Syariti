package com.example.myapplication;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.textfield.TextInputEditText;

public class DeletCar extends AppCompatActivity {
    DB_Sqlitecar db;
    TextInputEditText Textcarno;
    Button but;
    Button bt2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delet_car);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setSelectedItemId(R.id.dele);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()) {
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

                        return true;
                    case R.id.homec:
                        startActivity(new Intent(getApplicationContext(),HomeCar.class));
                        overridePendingTransition(0,0);
                        finish();
                        return true;
                    case R.id.bnk:
                        startActivity(new Intent(getApplicationContext(),Login.class));
                        overridePendingTransition(0, 0);
                        finish();
                        return true;

                }
                return false;
            }
        });
        db = new   DB_Sqlitecar(this);

        but = findViewById(R.id.but);
        bt2 = findViewById(R.id.bt2);
      Textcarno = findViewById(R.id.textInputEditText);

        but.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
            @Override
            public void onClick(View v) {
                final String Carnu;
               Carnu = Textcarno.getText().toString();
                Integer result = db.deleteR(Carnu);
                if (result > 0) {
                    Toast.makeText(com.example.myapplication.DeletCar.this, "تم حذف السيارة ", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(com.example.myapplication.DeletCar.this, "رقم السيارة غير صحيح", Toast.LENGTH_SHORT).show();
                }

            }
        });
        bt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), HomeCar.class);
                startActivity(intent);
                finish();
            }
        });
    }
}




