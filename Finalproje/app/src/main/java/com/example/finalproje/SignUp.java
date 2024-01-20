package com.example.finalproje;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import static android.content.ContentValues.TAG;


import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class SignUp extends AppCompatActivity {

    private EditText nameText,emailText,lastnameText,passwordText;
    private Button signup;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        mAuth=FirebaseAuth.getInstance();
        nameText=findViewById(R.id.name);
        lastnameText=findViewById(R.id.lastname);
        emailText=findViewById(R.id.mail);
        passwordText=findViewById(R.id.sifre);
        signup=findViewById(R.id.kayitol);

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = nameText.getText().toString().trim();
                String email = emailText.getText().toString().trim();
                String lastname = lastnameText.getText().toString().trim();
                String password = passwordText.getText().toString().trim();

                if (name.isEmpty()){
                    nameText.setError("Lutfen bir isim giriniz.");
                    return;
                }
                if (email.isEmpty()){
                    emailText.setError("Lutfen bir mail adresi girin.");
                    return;
                }
                if (lastname.isEmpty()){
                    lastnameText.setError("Lutfen soyadınızı girin.");
                    return;
                }
                if (password.isEmpty()){
                    passwordText.setError("Lutfen bir şifre girin.");
                    return;
                }
                mAuth.createUserWithEmailAndPassword(email,password)
                        .addOnCompleteListener(SignUp.this,task -> {
                            if (task.isSuccessful()){
                                Toast.makeText(SignUp.this, "Uyelik başarıyla oluşturuldu.", Toast.LENGTH_SHORT).show();

                                FirebaseFirestore db = FirebaseFirestore.getInstance();

                                Map<String,Object> user = new HashMap<>();
                                user.put("name",name);
                                user.put("lastname",lastname);
                                db.collection("users").document(mAuth.getUid())
                                        .set(user)
                                        .addOnSuccessListener(new OnSuccessListener<Void>(){
                                            @Override
                                            public void onSuccess(Void unused) {
                                                Log.d(TAG,"BAŞARILI BİR ŞEKİLDE OLUŞTURULDU!");
                                            }
                                        })
                                        .addOnFailureListener(new OnFailureListener() {
                                            @Override
                                            public void onFailure(@NonNull Exception e) {
                                                Log.w(TAG, "oluşturulurken hata alındı");
                                            }
                                        });

                            }

                            else {
                                Exception e = task.getException();
                                if(e != null){
                                    Toast.makeText(SignUp.this, "Üyelik oluşuturulurken bir hata alındı:" +e.getMessage(), Toast.LENGTH_SHORT).show();
                                }
                            }

                        });


            }
        });

        Button login = (Button) findViewById(R.id.girisyap);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SignUp.this, Login.class);

                startActivity(intent);
            }
        });

    }
}