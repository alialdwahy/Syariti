package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;


public class  SingUp extends AppCompatActivity implements RadioGroup.OnCheckedChangeListener {
    DB_Sqlitecar db;
    EditText us,fn,ln,em,ps,na,addr,ph;
    Button buttonSingUp;
    TextView textViewLogin;
    ProgressBar progressBar;
    RadioButton male;
    RadioButton fmali;
RadioGroup radioGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sing_up);
        radioGroup = findViewById(R.id.Gr);
        us = findViewById(R.id.usern);
       fn = findViewById(R.id.fna);
        ln = findViewById(R.id.lna);
        em = findViewById(R.id.ema);
        ps = findViewById(R.id.pas);
        na = findViewById(R.id.nagi);
        addr = findViewById(R.id.add);
        ph = findViewById(R.id.pho);
        textViewLogin = findViewById(R.id.loginText);
        progressBar = findViewById(R.id.progress);
        buttonSingUp = findViewById(R.id.buttonSignUp);
        male = findViewById(R.id.mal);
        fmali = findViewById(R.id.fmal);
        db = new   DB_Sqlitecar(this);


                    buttonSingUp.setOnClickListener(new View.OnClickListener() {
                        public void onClick(View v) {

                            final String username, passward, email, nagionalnum, firstname, lastname, address, phone;
                            String gender ="";
                            username = String.valueOf(us.getText().toString());
                            firstname = String.valueOf(fn.getText().toString());
                            lastname = String.valueOf(ln.getText().toString());
                            email = String.valueOf(em.getText().toString());
                            nagionalnum = String.valueOf(na.getText().toString());
                            passward = String.valueOf(ps.getText().toString());
                            address = String.valueOf(addr.getText().toString());
                            phone = String.valueOf(ph.getText().toString());

                            if (male.isChecked()){
                                gender="ذكر";
                            }
                            if (fmali.isChecked()){
                                gender="انثي";
                            }
                           

                            boolean result =    db.insertData(username,email,nagionalnum,passward,firstname,lastname, address, phone,gender);
                            if (result == true) {
                                Toast.makeText(SingUp.this, "تم التسجيل بنجاح", Toast.LENGTH_SHORT).show();


                            us.setText("");
                            fn.setText("");
                            ln.setText("");
                            em.setText("");
                            ps.setText("");
                            na.setText("");
                            addr.setText("");
                            ph.setText("");
                                male.setChecked(Boolean.parseBoolean(""));
                                fmali.setChecked(Boolean.parseBoolean(""));

                        }

                            else {
                                Toast.makeText(SingUp.this, "يوجد خطأ في البيانات", Toast.LENGTH_SHORT).show();

                            }
                        }

        });
        textViewLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Login.class);
                startActivity(intent);
                finish();
            }
        });
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {

    }
}
