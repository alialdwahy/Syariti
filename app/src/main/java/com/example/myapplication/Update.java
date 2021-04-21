package com.example.myapplication;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class Update extends AppCompatActivity implements RadioGroup.OnCheckedChangeListener {


    DB_Sqlitecar db;
final int REQUEST_CODE_GALLERY = 9000;
        EditText nacrw, nucrw, tycrw, mocrw, trcrw, clcrw, accarw, licarw, priw;
        Button buttAdd;
        Button buttCancel;
        TextView textVieRequesttype;
        TextView textVieGeartype;
        ProgressBar progressBar;
        ImageView imagz;
        RadioButton sal;
        RadioButton ren;
        RadioButton nor;
        RadioButton autm;
    FloatingActionButton add;
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_update);

            init();

            BottomNavigationView bottomNavigationView = findViewById(R.id.bottomN);
            bottomNavigationView.setSelectedItemId(R.id.updat);
            bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                    switch (item.getItemId()){
                        case R.id.addcar:
                            startActivity(new Intent(getApplicationContext(),AddCar.class));
                            overridePendingTransition(0,0);
                            finish();
                            return true;
                        case R.id.homec:
                            startActivity(new Intent(getApplicationContext(),HomeCar.class));
                            overridePendingTransition(0,0);
                            finish();
                            return true;
                        case R.id.updat:
                            return true;

                        case R.id.dele:
                            startActivity(new Intent(getApplicationContext(),DeletCar.class));
                            overridePendingTransition(0,0);
                            finish();
                            return true;
                        case R.id.bnk:

                            startActivity(new Intent(getApplicationContext(), Login .class));
                            overridePendingTransition(0, 0);
                            finish();
                            return true;
                    }
                    return false;
                }
            });

            buttAdd.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    final String Typeca;
                    final String Nameca;
                    final String Mode;
                    final String Traveleddistanc;
                    final String Carno;
                    final String Carclas;
                    final String Accident;
                    final String Licens;
                    final byte[] Imag;
                    final String priseca;
                    String geartyp = "";
                    String typereqouse = "";
                    Typeca = String.valueOf(tycrw.getText().toString());
                    Nameca = String.valueOf(nacrw.getText().toString());
                    Mode = String.valueOf(mocrw.getText().toString());
                    Traveleddistanc = String.valueOf(trcrw.getText().toString());
                    Carno = String.valueOf(nucrw.getText().toString());
                    Carclas = String.valueOf(clcrw.getText().toString());
                    Accident = String.valueOf(accarw.getText().toString());
                    Licens = String.valueOf(licarw.getText().toString());
                    priseca = String.valueOf(priw.getText().toString());
                    Imag = imageViewToByte(imagz);

                    if (sal.isChecked()) {
                        typereqouse = "بيع";
                    }
                    if (ren.isChecked()) {
                        typereqouse = "ايجار";
                    }
                    if (nor.isChecked()) {
                        geartyp = "عادي";
                    }
                    if (autm.isChecked()) {
                        geartyp = "اوتوماتيك";
                    }
                    db.updateR(Typeca, Nameca, Mode, Traveleddistanc, Carno, Carclas, Accident, Licens, priseca, Imag, geartyp, typereqouse);

                    Toast.makeText(getApplicationContext(), "تم تعديل البيانات بنجاح", Toast.LENGTH_SHORT).show();
                    nacrw.setText("");
                    nucrw.setText("");
                    tycrw.setText("");
                    mocrw.setText("");
                    trcrw.setText("");
                    clcrw.setText("");
                    accarw.setText("");
                    licarw.setText("");
                    priw.setText("");
                    sal.setChecked(Boolean.parseBoolean(""));
                    ren.setChecked(Boolean.parseBoolean(""));
                    nor.setChecked(Boolean.parseBoolean(""));
                    autm.setChecked(Boolean.parseBoolean(""));
                    imagz.setImageResource(R.mipmap.ic_launcher);


                    {
                        Toast.makeText(getApplicationContext(), " رقم السيارة غير صحيح", Toast.LENGTH_SHORT).show();
                    }
                }

            });

            add.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    ActivityCompat.requestPermissions(
                            Update.this,
                            new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                            REQUEST_CODE_GALLERY);


                }
            });
        }
    public static byte[] imageViewToByte(ImageView image) {
        Bitmap bitmap = ((BitmapDrawable) image.getDrawable()).getBitmap();
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
        byte[] byteArray = stream.toByteArray();
        return byteArray;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        if (requestCode == REQUEST_CODE_GALLERY) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(intent, REQUEST_CODE_GALLERY);
            } else {
                Toast.makeText(getApplicationContext(), "You don't have permission to access file location!", Toast.LENGTH_SHORT).show();
            }
            return;
        }

        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == REQUEST_CODE_GALLERY && resultCode == RESULT_OK && data != null) {
            Uri uri = data.getData();

            try {
                InputStream inputStream = getContentResolver().openInputStream(uri);

                Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                imagz.setImageBitmap(bitmap);

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }

        super.onActivityResult(requestCode, resultCode, data);
    }

    private void init() {

            nacrw = findViewById(R.id.typ);
            nucrw = findViewById(R.id.del);
            tycrw = findViewById(R.id.nn);
            mocrw = findViewById(R.id.noo);
            trcrw = findViewById(R.id.trwraz);
            clcrw = findViewById(R.id.trt);
            accarw = findViewById(R.id.tyr);
            licarw = findViewById(R.id.ac);
            priw = findViewById(R.id.prj);
            textVieRequesttype = findViewById(R.id.teew);
            textVieGeartype = findViewById(R.id.teViw);
            progressBar = findViewById(R.id.progress);
            buttAdd = findViewById(R.id.butocx);
            buttCancel = findViewById(R.id.bun);
            add = findViewById(R.id.floatingActionButton2);
            sal = findViewById(R.id.rtton2);
            ren = findViewById(R.id.rton4);
            nor = findViewById(R.id.radion);
            autm = findViewById(R.id.raditn3);
            imagz = findViewById(R.id.imagec);

        db = new   DB_Sqlitecar(this);

        buttCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), HomeCar.class);
                startActivity(intent);
                finish();
            }
        });


        }


    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {

    }

}

