package com.example.siqasir_lanjutan.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.siqasir_lanjutan.R;

import java.util.Locale;

public class LoginActivity extends AppCompatActivity {

    Button login;
    EditText username, password;

    protected void onCreate (Bundle saveInstanceState){
        super.onCreate(saveInstanceState);

        setContentView(R.layout.login_relative);

        login = (Button) findViewById(R.id.btnLogin);
        username = (EditText) findViewById(R.id.etUsername);
        password = (EditText) findViewById(R.id.etPassword);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (password.getText().toString().toUpperCase().equals("1234")){

                } else {
                    Toast.makeText(getApplicationContext(),"Password Salah!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    private void login_sukses(){
        String usernameLogin = username.getText().toString();
        Intent intent = new Intent(this,MainActivity.class);
        //mengirim data ke tampilan utama
        intent.putExtra("USERNAME",usernameLogin);
        startActivity(intent);
    }
}
