package com.example.furnitureapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {

    EditText inEmail, inPassword;
    Button login;
    FirebaseAuth fAuth;
    ProgressBar progressBar;
    TextView registertext;
    ImageView btnBack,showPwd;
    Button btnGoogle;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        inEmail = findViewById(R.id.inEmail);
        inPassword = findViewById(R.id.inPassword);

        progressBar = findViewById(R.id.progressBar);
        login = findViewById(R.id.login);
        registertext = findViewById(R.id.registertext);
        fAuth = FirebaseAuth.getInstance();
        btnBack = findViewById(R.id.btnBack);
        btnGoogle=findViewById(R.id.btnGoogle);
        showPwd = findViewById(R.id.showPwd);
        showPwd.setImageResource(R.drawable.hideeye);

btnGoogle.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        signIn();
    }
});

        showPwd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (inPassword.getTransformationMethod().equals(HideReturnsTransformationMethod.getInstance())) {
                    ;
                    //if pwd is visible then hide it
                    inPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    //change icon
                    showPwd.setImageResource(R.drawable.hideeye);
                } else {

                    inPassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    showPwd.setImageResource(R.drawable.eye);
                }
            }

        });


        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        registertext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(LoginActivity.this, "Register has been clicked", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
            }
        });
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              performLogin();
            }
        });




    }

    private void signIn() {
        Intent intent=new Intent(LoginActivity.this, GoogleSignInActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 1000){
            Task< GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                task.getResult(ApiException.class);
                navigateToSecondActivity();
            } catch (ApiException e) {
                Toast.makeText(LoginActivity.this, "Something went wrong", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void navigateToSecondActivity() {
        finish();
        Intent intent = new Intent(LoginActivity.this,GoogleSignInActivity.class);
        startActivity(intent);
    }

    private void performLogin() {


        String email = inEmail.getText().toString();
        String password = inPassword.getText().toString();


        if (TextUtils.isEmpty(email)) {
            inEmail.setError(" Email is Required");
            return;

        }
        if (TextUtils.isEmpty(password)) {

            inPassword.setError("Password is Required");
            return;
        }
        if (password.length() < 6) {

            inPassword.setError("Password must be more then and equals to six character");
            return;
        }
        progressBar.setVisibility(View.VISIBLE);

        fAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {

                    Toast.makeText(LoginActivity.this, "Login Successful", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(LoginActivity.this,MainActivity.class));
                }
                else {
                    Toast.makeText(LoginActivity.this, "Error" + task.getException().getMessage(),Toast.LENGTH_SHORT).show();
                    progressBar.setVisibility(View.GONE);
                }
            }
        });
    }
}
