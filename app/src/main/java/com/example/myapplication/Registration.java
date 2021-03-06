package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.time.LocalDate;

public class Registration extends AppCompatActivity {

    EditText editText_namalengkap, editText_email;
    TextView clickAble_TextViewLogin;
    TextInputEditText editText_password, editText_confirmpassword;
    Button btn_daftar;
    private FirebaseAuth mAuth;
    ProgressDialog progressDialog;
    /*DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReferenceFromUrl("https://gelth-7099c-default-rtdb.asia-southeast1.firebasedatabase.app/");
*/
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registration);
        findById();
        mAuth = FirebaseAuth.getInstance();
        progressDialog = new ProgressDialog(Registration.this);
        progressDialog.setTitle("Loading");
        progressDialog.setMessage("Silahkan Tunggu!");
        progressDialog.setCancelable(false);
        onClick();
        getSupportActionBar().hide();
        clickAble_TextViewLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Registration.this, Login.class);
                startActivity(i);
            }
        });
    }

    private void findById(){
        editText_namalengkap = findViewById(R.id.editText_namalengkap);
        editText_email = findViewById(R.id.editText_email);
        clickAble_TextViewLogin = findViewById(R.id.clickAble_TextViewLogin);
        editText_password = findViewById(R.id.editText_password);
        editText_confirmpassword= findViewById(R.id.editText_confirmpassword);
        btn_daftar = findViewById(R.id.btn_daftar);
    }

    private void onClick(){
        btn_daftar.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View view) {/*
                final String fullname = editText_namalengkap.getText().toString();
                final String username = editText_namapengguna.getText().toString();
                final String email = editText_email.getText().toString();
                final String password = editText_password.getText().toString();
                final String confirm_password = editText_confirmpassword.getText().toString();*/

                if(editText_namalengkap.getText().length() > 0 && editText_email.getText().length() > 0 && editText_password.getText().length() > 0 && editText_confirmpassword.getText().length() > 0 ){
                    if(editText_password.getText().toString().equals(editText_confirmpassword.getText().toString())){
                        //tanggalLahir = LocalDate.of(numberPicker_tahunlahir.getValue(), numberPicker_bulanlahir.getValue(), numberPicker_tanggallahir.getValue());
                        //strTglLahir = tanggalLahir.toString();
                        register(editText_email.getText().toString(), editText_namalengkap.getText().toString(), editText_password.getText().toString());
             /*           databaseReference.child("users").child(email).child("username").setValue(username);
                        databaseReference.child("users").child(email).child("fullname").setValue(fullname);
                        databaseReference.child("password").child(email).child("password").setValue(password);*/
                    }else {
                        Toast.makeText(getApplicationContext(), "Silahkan masukan password yang sama!!!", Toast.LENGTH_SHORT).show();
                    }
                }else{
                    Toast.makeText(getApplicationContext(), "Silahkan isi semua data!!!", Toast.LENGTH_SHORT).show();
                }
            }
        });


    }

    private void register(String eMail, String namaLengkap, String password){
        progressDialog.show();
        mAuth.createUserWithEmailAndPassword(eMail,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful() && task.getResult() !=null){
                    FirebaseUser firebaseUser = task.getResult().getUser();
                    if(firebaseUser != null) {
                        UserProfileChangeRequest request = new UserProfileChangeRequest.Builder()
                                .setDisplayName(namaLengkap)
                                .build();
                        firebaseUser.updateProfile(request).addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                reload();
                            }
                        });
                    }else {
                        Toast.makeText(getApplicationContext(), "Registrasi Gagal", Toast.LENGTH_SHORT).show();
                    }
                }else {
                    Toast.makeText(getApplicationContext(), task.getException().getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void reload(){
        Intent i = new Intent(Registration.this, MainActivity.class);
        startActivity(i);
        finish();
    }

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser != null){
            reload();
        }
    }
}