package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;

public class Login extends AppCompatActivity {

    DB_Sqlitecar db;
    TextInputEditText textInputEditTextUsername, textInputEditTextPassword;
    Button buttonLogin;
    TextView textViewsText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        textInputEditTextUsername = findViewById(R.id.username);
        textInputEditTextPassword = findViewById(R.id.password);
        buttonLogin = findViewById(R.id.buttonLogin);
        textViewsText = findViewById(R.id.sText);
        db = new DB_Sqlitecar(this);


        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                String username = textInputEditTextUsername.getText().toString();
                String passward = textInputEditTextPassword.getText().toString();

                if (username.equals("admin") || passward.equals("1234")) {
                    textInputEditTextUsername.setText("");
                    textInputEditTextPassword.setText("");
                    Intent intent = new Intent(getApplicationContext(), HomeCar.class);
                    startActivity(intent);
                    finish();
                }
                if (username.equals("") || passward.equals(""))
                    Toast.makeText(Login.this, "الرجاءاملاء الحقول ", Toast.LENGTH_SHORT).show();

                else {
                    boolean checkuserpass = db.checkusernamepassward(username, passward);
                    if (checkuserpass == true) {
                        Toast.makeText(Login.this, "تم تسجيل الدخول بنجاح", Toast.LENGTH_SHORT).show();
                        textInputEditTextUsername.setText("");
                        textInputEditTextPassword.setText("");
                        Intent i = new Intent(getApplicationContext(), HomeUser.class);
                        startActivity(i);
                        finish();
                    }else {
                        Toast.makeText(Login.this, "خطاء في اسم المسخدم او كلمة المرور", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });


        textViewsText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent inten = new Intent(getApplicationContext(), SingUp.class);
                startActivity(inten);
                finish();
            }
        });


    }
}






