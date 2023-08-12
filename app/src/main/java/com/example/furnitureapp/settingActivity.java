package com.example.furnitureapp;

import static com.example.furnitureapp.R.id.*;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.ButtonBarLayout;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class settingActivity extends AppCompatActivity {
ImageView btnBack;
FirebaseAuth mAuth;
GoogleSignInClient mGoogleSignInClient;
Button logout;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        btnBack = findViewById(R.id.btnBack);

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();
        mGoogleSignInClient = GoogleSignIn.getClient(this,gso);


        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        logout = findViewById(R.id.logout);
        mAuth=FirebaseAuth.getInstance();

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signOutFromEmailAndPassword();
                signOutFromGoogle();
            }

        });
    }
    private void signOutFromEmailAndPassword() {
        mAuth.signOut();
        Intent intent = new Intent(settingActivity.this,LoginActivity.class);
        startActivity(intent);
    }
    private void signOutFromGoogle() {
        mGoogleSignInClient.signOut();
        Intent intent = new Intent(settingActivity.this,LoginActivity.class);
        startActivity(intent);

    }


}

