package com.EZALLC.securityapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.widget.EditText;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Signup extends AppCompatActivity {

    private EditText emailEditText;
    private EditText passwordEditText;
    private EditText confirmPasswordEditText;
    private FirebaseAuth mAuth;
    private static final String TAG = "SignupActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        mAuth = FirebaseAuth.getInstance();

        emailEditText = findViewById(R.id.signup_email_edit);
        passwordEditText = findViewById(R.id.signup_password_edit);
        confirmPasswordEditText = findViewById(R.id.confirm_password_edit);
    }

    private boolean checkEmptyFields() {
        // Method for validating edit text fields on signin/up page
        boolean valid = true;

        String email = emailEditText.getText().toString();
        if (TextUtils.isEmpty(email)) {
            emailEditText.setError("Required.");
            valid = false;
        } else {
            emailEditText.setError(null);
        }

        String password = passwordEditText.getText().toString();
        if (TextUtils.isEmpty(password)) {
            passwordEditText.setError("Required.");
            valid = false;
        } else {
            passwordEditText.setError(null);
        }

        String confirmPassword = confirmPasswordEditText.getText().toString();
        if (TextUtils.isEmpty(confirmPassword)) {
            confirmPasswordEditText.setError("Required.");
            valid = false;
        } else {
            confirmPasswordEditText.setError(null);
        }

        return valid;
    }

    private boolean checkMatchingPasswords() {
        boolean valid = true;
        String password = passwordEditText.getText().toString();
        String confirmPassword = confirmPasswordEditText.getText().toString();

        if (!confirmPassword.equals(password)) {
            confirmPasswordEditText.setError("Passwords did not match.");
            passwordEditText.setError("Passwords did not match.");
            valid = false;
        } else {
            confirmPasswordEditText.setError(null);
            passwordEditText.setError(null);
        }

        return valid;
    }

    public void onSignup(View view) {
        // Method for creating user account and updating ui to homebase when successful
        if (!checkEmptyFields()) {
            return;
        }
        if (!checkMatchingPasswords()) {
            return;
        }

        String email = emailEditText.getText().toString();
        String password = confirmPasswordEditText.getText().toString();

        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "createUserWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            Toast.makeText(Signup.this, "Account created successfully.",
                                    Toast.LENGTH_SHORT).show();

                            Intent intent = new Intent(Signup.this, MainActivity.class);
                            startActivity(intent);
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "createUserWithEmail:failure", task.getException());
                            Toast.makeText(Signup.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    public void onLoginReturn(View view) {
        Intent intent = new Intent(Signup.this, Login.class);
        startActivity(intent);
    }
}