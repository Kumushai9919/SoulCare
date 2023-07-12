package com.kumush.soulcare.View.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.kumush.soulcare.View.Fragments.FragmentActivity;
import com.kumush.soulcare.R;

public class SplashActivity extends AppCompatActivity {

    private FirebaseAuth firebaseAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        firebaseAuth = FirebaseAuth.getInstance();


        //start main screen after 2seconds
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                checkUser();

            }
        }, 2000);
    }

    private void checkUser() {

        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();

        FirebaseUser currentUser = firebaseAuth.getCurrentUser();
        if (currentUser != null) {
            // User is logged in

            startActivity(new Intent(SplashActivity.this, FragmentActivity.class));
            finish();

        } else {
            // User is not logged in
            startActivity(new Intent(SplashActivity.this, LoginActivity.class));
            finish();
        }
    }
}
