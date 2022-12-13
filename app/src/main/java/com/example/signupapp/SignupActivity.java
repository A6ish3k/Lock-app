package com.example.signupapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SignupActivity extends AppCompatActivity implements View.OnClickListener{

    private FirebaseAuth mAuth;
    private Button Signup;
    private TextView RegNow;
    private EditText Username, Email, Password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        mAuth = FirebaseAuth.getInstance();

        Signup = (Button) findViewById(R.id.signIn);
        Signup.setOnClickListener(this);

        RegNow = (TextView) findViewById(R.id.regNow);
        RegNow.setOnClickListener(this);

        Username = (EditText) findViewById(R.id.username);
        Email = (EditText) findViewById(R.id.email);
        Password = (EditText) findViewById(R.id.password);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.regNow:
                startActivity(new Intent(this, LoginActivity.class));
                break;
            case R.id.signIn:
                SignUpNow();
                break;
        }
    }

    private void SignUpNow() {
        String enterUsername = Username.getText().toString().trim();
        String enterEmail = Email.getText().toString().trim();
        String enterPassword = Password.getText().toString().trim();

        if (enterUsername.isEmpty()) {
            Username.setError("Username is required");
            Username.requestFocus();
            return;
        }
        if (enterEmail.isEmpty()) {
            Email.setError("Email is required");
            Email.requestFocus();
            return;
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(enterEmail).matches()) {
            Email.setError("Please provide a valid Email address");
            Email.requestFocus();
            return;
        }
        if (enterPassword.isEmpty()) {
            Password.setError("Password required");
            Password.requestFocus();
            return;
        }
        if (enterPassword.length() < 6) {
            Password.setError("Min pass required");
            Password.requestFocus();
            return;
        }
        mAuth.createUserWithEmailAndPassword(enterEmail, enterPassword)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if (task.isSuccessful()) {
                            User user = new User(enterUsername, enterEmail);

                            FirebaseDatabase.getInstance().getReference("User")
                                    .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                    .setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            if (task.isSuccessful()) {
                                                Toast.makeText(SignupActivity.this, "User has been registered", Toast.LENGTH_LONG).show();
                                                finish();
                                                startActivity(new Intent(SignupActivity.this, LoginActivity.class));
                                            } else {
                                                Toast.makeText(SignupActivity.this, "Failed", Toast.LENGTH_LONG).show();

                                            }

                                        }
                                    });

                        } else {
                            Toast.makeText(SignupActivity.this, "Failed to", Toast.LENGTH_LONG).show();
                        }
                    }
                });

    }
}