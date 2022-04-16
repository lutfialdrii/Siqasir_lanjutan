package com.example.siqasir_lanjutan.activity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.siqasir_lanjutan.R;

public class LoginActivity extends AppCompatActivity {

    ImageButton login;
    EditText username, password;

    protected void onCreate(Bundle saveInstanceState) {
        super.onCreate(saveInstanceState);

        setContentView(R.layout.login_relative);

        login = (ImageButton) findViewById(R.id.btnLogin);
        username = (EditText) findViewById(R.id.etUsername);
        password = (EditText) findViewById(R.id.etPassword);

        login.setOnClickListener(view -> {
            if (password.getText().toString().equalsIgnoreCase("1234")) {
                login_sukses();
            } else {
                Toast.makeText(getApplicationContext(), "salah Password", Toast.LENGTH_SHORT).show();
            }
        });
    }
    @Override
    public boolean onCreateOptionsMenu (Menu menu) {
        getMenuInflater().inflate(R.menu.main,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        if (item.getItemId()==R.id.action_settings){
            Intent nintent = new Intent(Settings.ACTION_LOCALE_SETTINGS);
            startActivity(nintent);
        }
        return super.onOptionsItemSelected(item);
    }

    private void login_sukses() {
        String usernameLogin = username.getText().toString();
        Intent intent = new Intent(this, MainActivity.class);
        //mengirim data ke tampilan utama
        intent.putExtra("USERNAME", usernameLogin);
        startActivity(intent);
    }
}

