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


public class AddCar extends AppCompatActivity  implements RadioGroup.OnCheckedChangeListener {
    DB_Sqlitecar db;

    EditText nacr, nucr, tyc, mocr, trcr, clcr, accar, licar, edtPrice;
    Button buttonAdd;
    Button buttonCancel;
    TextView textViewRequesttype;
    TextView textViewGeartype;
    ProgressBar progressBar;
    RadioButton sa;
    RadioButton re;
    RadioButton no;
    RadioButton aut;
    ImageView imag;
    RadioGroup radioGroup;
    RadioGroup radioGroup1;
    FloatingActionButton add;
    final int REQUEST_CODE_GALLERY = 9000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_car);

        init();

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setSelectedItemId(R.id.addcar);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()) {
                    case R.id.addcar:

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
                    case R.id.homec:
                        startActivity(new Intent(getApplicationContext(), HomeCar.class));
                        overridePendingTransition(0, 0);
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


        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final String Typecar;
                final String Namecar;
                final String Model;
                final String Traveleddistance;
                final String Carnum;
                final String Carclass;
                final String Accidents;
                final String License;
                final byte [] Image;
                final String prisecar;
                String geartype = "";
                String typereqouset = "";
                Typecar = String.valueOf(tyc.getText().toString());
                Namecar = String.valueOf(nacr.getText().toString());
                Model = String.valueOf(mocr.getText().toString());
                Traveleddistance = String.valueOf(trcr.getText().toString());
                Carnum = String.valueOf(nucr.getText().toString());
                Carclass = String.valueOf(clcr.getText().toString());
                Accidents = String.valueOf(accar.getText().toString());
                License = String.valueOf(licar.getText().toString());
                prisecar = String.valueOf(edtPrice.getText().toString());
                 Image=imageViewToByte(imag);

                if (sa.isChecked()) {
                    typereqouset = "بيع";
                }
                if (re.isChecked()) {
                    typereqouset = "ايجار";
                }
                if (no.isChecked()) {
                    geartype = "عادي";
                }
                if (aut.isChecked()) {
                    geartype = "اوتوماتيك";
                }
                boolean result = db.insertData1(Typecar, Namecar, Model, Traveleddistance, Carnum, Carclass, Accidents, License, prisecar,Image,geartype,typereqouset);
                if (result == true) {
                    Toast.makeText(getApplicationContext(), "تم اضافة السيارة بنجاح", Toast.LENGTH_SHORT).show();
                    nacr.setText("");
                    nucr.setText("");
                    tyc.setText("");
                    mocr.setText("");
                    trcr.setText("");
                    clcr.setText("");
                    accar.setText("");
                    licar.setText("");
                    edtPrice.setText("");
                    sa.setChecked(Boolean.parseBoolean(""));
                    re.setChecked(Boolean.parseBoolean(""));
                    no.setChecked(Boolean.parseBoolean(""));
                    aut.setChecked(Boolean.parseBoolean(""));
                    imag.setImageResource(R.mipmap.ic_launcher);

                }else {
                        Toast.makeText(AddCar.this, "يوجد خطأ في البيانات", Toast.LENGTH_SHORT).show();

                    }
            }
        });


        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ActivityCompat.requestPermissions(
                        AddCar.this,
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
                Toast.makeText(getApplicationContext(), "لا تملك صلاحية الوصول لموقع الملف", Toast.LENGTH_SHORT).show();
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
                imag.setImageBitmap(bitmap);

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }

        super.onActivityResult(requestCode, resultCode, data);
    }

 
    private void init() {
        radioGroup = findViewById(R.id.chiroup2);
        radioGroup1 = findViewById(R.id.chipGroup1);
        nacr = findViewById(R.id.typc);
        nucr = findViewById(R.id.dele);
        tyc = findViewById(R.id.nnx);
        mocr = findViewById(R.id.nomo);
        trcr = findViewById(R.id.trfw);
        clcr = findViewById(R.id.tyor);
        accar = findViewById(R.id.acc);
        licar = findViewById(R.id.trty);
        edtPrice = findViewById(R.id.prnj);
        textViewRequesttype = findViewById(R.id.texiew);
        textViewGeartype = findViewById(R.id.tetViw);
        progressBar = findViewById(R.id.progress);
        buttonAdd = findViewById(R.id.butto);
        buttonCancel = findViewById(R.id.buton);
        add = findViewById(R.id.floatingActionButton);
        sa = findViewById(R.id.radtton2);
        re = findViewById(R.id.ratton4);
        no = findViewById(R.id.raditton);
        aut = findViewById(R.id.raditton3);
        imag = findViewById(R.id.imagec);
        db = new   DB_Sqlitecar(this);

        buttonCancel.setOnClickListener(new View.OnClickListener() {
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








