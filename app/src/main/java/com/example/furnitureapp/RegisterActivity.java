package com.example.furnitureapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class RegisterActivity extends AppCompatActivity {
    public static final String TAG = "TAG";

    Button btnRegister;

    ImageView btnBack;
    TextView logintext;

    EditText inputEmail, inputPassword, inputFullName, inputPhoneNumber;


    FirebaseAuth fAuth;
    FirebaseUser fUser;
    ProgressBar progressBar;
    String userID;
    FirebaseFirestore fStore;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        inputEmail = findViewById(R.id.inputEmail);
        inputPassword = findViewById(R.id.inputPassword);
        inputFullName = findViewById(R.id.inputFullName);
        inputPhoneNumber = findViewById(R.id.inputPhoneNumber);
        progressBar = findViewById(R.id.progressBar);
        btnRegister = findViewById(R.id.btnRegister);
        logintext = findViewById(R.id.logintext);
        fAuth = FirebaseAuth.getInstance();
        btnBack = findViewById(R.id.btnBack);
        fStore = FirebaseFirestore.getInstance();



        ImageView showHidePassword = findViewById(R.id.show_password);
        showHidePassword.setImageResource(R.drawable.hideeye);



        showHidePassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (inputPassword.getTransformationMethod().equals(HideReturnsTransformationMethod.getInstance())) {
                    ;
                    //if pwd is visible then hide it
                    inputPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    //change icon
                    showHidePassword.setImageResource(R.drawable.hideeye);
                } else {

                    inputPassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    showHidePassword.setImageResource(R.drawable.eye);
                }
            }

        });

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });


        logintext.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Toast.makeText(RegisterActivity.this, "Already account? Login has been clicked", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(intent);

            }
        });

        btnRegister.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                PerformAuth();

            }
        });


    }


    private void PerformAuth() {
        String email = inputEmail.getText().toString();
        String password = inputPassword.getText().toString();
        String fullName = inputFullName.getText().toString();
        String phoneNumber = inputPhoneNumber.getText().toString();

        if (TextUtils.isEmpty(email)) {
            inputEmail.setError(" Email is Required");
            return;

        }
        if (TextUtils.isEmpty(password)) {

            inputPassword.setError("Password is Required");
            return;
        }
        if (password.length() < 6) {

            inputPassword.setError("Password must be more then and equals to six character");
            return;
        }
        progressBar.setVisibility(View.VISIBLE);

fAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
    @Override
    public void onComplete(@NonNull Task<AuthResult> task) {
        if(task.isSuccessful()){
             fUser=fAuth.getCurrentUser();
             fUser.sendEmailVerification().addOnSuccessListener(new OnSuccessListener<Void>() {
                 @Override
                 public void onSuccess(Void unused) {
                     Toast.makeText(RegisterActivity.this, "Registration SuccessFul", Toast.LENGTH_SHORT).show();
                 }
             }).addOnFailureListener(new OnFailureListener() {
                 @Override
                 public void onFailure(@NonNull Exception e) {
                     Log.e(TAG,"On Failure:Email Not Sent"+ e.getMessage());
                 }
             });


            Toast.makeText(RegisterActivity.this, "User Created", Toast.LENGTH_SHORT).show();
            userID = fAuth.getCurrentUser().getUid();
            DocumentReference documentReference = fStore.collection("user").document(userID);
            Map<String,Object> user= new HashMap<>();
            user.put("fName", fullName);
            user.put("email",email);
            user.put("phoneNumber",phoneNumber);
            documentReference.set(user).addOnSuccessListener(new OnSuccessListener<Void>() {
                @Override
                public void onSuccess(Void unused) {
                    Log.e(TAG,"onSuccess: user profile is created for " + userID);
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Log.e(TAG,"onFailure: " + e.toString());
                }
            });
            startActivity(new Intent(RegisterActivity.this,LoginActivity.class));

        }
        else{
            Toast.makeText(RegisterActivity.this, "Error" + task.getException().getMessage(),
                    Toast.LENGTH_SHORT).show();
            progressBar.setVisibility(View.GONE);
                    ;
        }

    }
});

}

}











