package com.example.instagramcloneparse;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

public class SignUpActivity extends AppCompatActivity {

    EditText userNameText, userPasswordText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        userNameText        = findViewById(R.id.signUpActivityNameText);
        userPasswordText    = findViewById(R.id.signUpActivityPasswordText);

        ParseUser parseUser = ParseUser.getCurrentUser();

        if(parseUser != null) {

            // Intent
            Intent intent = new Intent(getApplicationContext(), FeedActivity.class);
            startActivity(intent);
        }
    }

    public void signIn(View view) {

        ParseUser.logInInBackground(userNameText.getText().toString(), userPasswordText.getText().toString(), new LogInCallback() {
            @Override
            public void done(ParseUser user, ParseException e) {

                if(e != null) {

                    Toast.makeText(getApplicationContext(), "Invalid Username/Password", Toast.LENGTH_LONG).show();
                } else {

                    Toast.makeText(getApplicationContext(),"Welcome " + user.getUsername(), Toast.LENGTH_LONG).show();

                    // Intent
                    Intent intent = new Intent(getApplicationContext(), FeedActivity.class);
                    startActivity(intent);
                }
            }
        });
    }

    public void signUp(View view) {

        ParseUser user = new ParseUser();
        user.setUsername(userNameText.getText().toString());
        user.setPassword(userPasswordText.getText().toString());

        user.signUpInBackground(new SignUpCallback() {
            @Override
            public void done(ParseException e) {

                if(e != null) {

                    Toast.makeText(getApplicationContext(), e.getLocalizedMessage(), Toast.LENGTH_LONG).show();
                } else {

                    Toast.makeText(getApplicationContext(), "User Created", Toast.LENGTH_LONG).show();

                    // Intent
                    Intent intent = new Intent(getApplicationContext(), FeedActivity.class);
                    startActivity(intent);
                }
            }
        });
    }
}
