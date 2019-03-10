package com.example.mymall;

import android.content.Intent;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Splash extends AppCompatActivity {
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        mAuth = FirebaseAuth.getInstance();
        SystemClock.sleep(3000);

    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if (currentUser == null){
            Intent registerIntent = new Intent(Splash.this, RegisterActivity.class);
            startActivity(registerIntent);
            finish();
        }else{
            Intent mainIntent = new Intent(Splash.this, MainActivity.class);
            startActivity(mainIntent);
            finish();
        }
    }
}
