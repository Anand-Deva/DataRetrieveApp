package com.example.dataretrieveapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import io.realm.Realm;
import io.realm.mongodb.App;
import io.realm.mongodb.User;
import io.realm.mongodb.Credentials;

public class MainActivity extends AppCompatActivity {
    EditText emailEditText, passwordEditText;
    Button loginButton;
    boolean isEmailEmpty, isPasswordEmpty;
    User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Realm.init(this);
        App app = RealmSingleton.getInstance().getRealm();

        emailEditText = findViewById(R.id.editText_Email);
        passwordEditText = findViewById(R.id.editText_Password);
        loginButton = findViewById(R.id.loginButton);

        loginButton.setOnClickListener(v -> {
            String emailText = emailEditText.getText().toString();
            String passwordText = passwordEditText.getText().toString();

            if(TextUtils.isEmpty(emailText)) {
                emailEditText.setError(getResources().getString(R.string.logIn_Email_error));
                isEmailEmpty = true;
                return;
            }else{
                isEmailEmpty = false;
            }

            if(TextUtils.isEmpty(passwordText)) {
                passwordEditText.setError(getResources().getString(R.string.logIn_password_error));
                isPasswordEmpty = true;
                return;
            }else{
                isPasswordEmpty = false;
            }

            if(isEmailEmpty == false && isPasswordEmpty == false) {

                Credentials emailPasswordCredentials = Credentials.emailPassword(emailText, passwordText);

                app.loginAsync(emailPasswordCredentials, it -> {
                    if (it.isSuccess()) {
                        Log.v("MongoDB Auth", "Success");

                        //user.set(app.currentUser());

                        Intent intent = new Intent(MainActivity.this, inventoryActivity.class);
                        startActivity(intent);
                    } else {
                        Log.e("MongoDB Auth", it.getError().toString());
                        Toast.makeText(getApplicationContext(),"Invalid username or password",Toast.LENGTH_LONG).show();
                    }
                });
            }
        });
    }

}