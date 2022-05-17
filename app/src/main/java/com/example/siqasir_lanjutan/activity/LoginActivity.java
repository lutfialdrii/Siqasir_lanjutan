package com.example.siqasir_lanjutan.activity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.siqasir_lanjutan.R;
import com.example.siqasir_lanjutan.model.User;
import com.example.siqasir_lanjutan.response.ResponseLogin;
import com.example.siqasir_lanjutan.rest.ApiClient;
import com.example.siqasir_lanjutan.rest.ApiInterface;
import com.example.siqasir_lanjutan.utils.SessionManager;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

//    menggunakan BindView pada library Butterknife untuk menggantikan findViewById
    @BindView(R.id.btnLogin)
    ImageButton login;

    @BindView(R.id.etUsername)
    EditText etusername;

    @BindView(R.id.etPassword)
    EditText etpassword;

    @BindView(R.id.tvCreateAccount)
    TextView register_user;

    ApiInterface apiservice;
    SessionManager sessionManager;

    private static final String TAG = "LoginActivity";

    protected void onCreate(Bundle saveInstanceState) {
        super.onCreate(saveInstanceState);

        setContentView(R.layout.login_relative);
        ButterKnife.bind(this);

        apiservice = ApiClient.getClient().create(ApiInterface.class);
        sessionManager = new SessionManager(this);


        login.setOnClickListener(view -> {
            loginUser();
        });
        register_user.setOnClickListener(view -> {
            Intent i = new Intent(this,RegisterActivity.class);
            startActivity(i);
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

    private void loginUser() {
        String username = etusername.getText().toString();
        String password = etpassword.getText().toString();

        apiservice.login(username,password).enqueue(new Callback<ResponseLogin>() {
            @Override
            public void onResponse(Call<ResponseLogin> call, Response<ResponseLogin> response) {
                if (response.isSuccessful()) {
                    User userLoggedIn = response.body().getUser();
                    sessionManager.createLoginSession(userLoggedIn);
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NO_HISTORY);
                    startActivity(intent);
                    finish();
                }
                else if (!response.isSuccessful()) {
                    Toast.makeText(LoginActivity.this,"User tidak ditemukan",Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseLogin> call, Throwable t) {
                Log.e(TAG, "onFailure:"+t.getLocalizedMessage());
                Toast.makeText(LoginActivity.this,"Koneksi gagal",Toast.LENGTH_SHORT).show();

            }
        } );
    }
}