package com.istiaq66.momentous_click.Login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.istiaq66.momentous_click.MainActivity;
import com.istiaq66.momentous_click.R;
import com.istiaq66.momentous_click.Splash;

import java.util.HashMap;
import java.util.Map;

public class Login_Activity extends AppCompatActivity {

    Button login;
    EditText ed1, ed2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        login = findViewById(R.id.login_bt);
        ed1 = findViewById(R.id.username);
        ed2 = findViewById(R.id.pass);

        //---------Hide Actionbar----------//
        getSupportActionBar().hide();

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Login();

            }
        });
    }

    public void Login() {


        String username = ed1.getText().toString().trim();
        String password = ed2.getText().toString().trim();

        if (ed1.getText().toString().isEmpty()) {

            ed1.setError("Enter a username");
            return;
        }

        if (ed2.getText().toString().isEmpty()) {

            ed2.setError("Enter a password");
            return;
        }
        else  {

            Intent intent = new Intent(Login_Activity.this, MainActivity.class);
            startActivity(intent);
        }
    }
}