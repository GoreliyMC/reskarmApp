package com.example.reskarmv01alfa;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;


public class Registration extends AppCompatActivity {
    private EditText login;
    private EditText password;
    private Button regButt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        regButt = (Button) findViewById(R.id.butReg);
        login = (EditText) findViewById(R.id.etRegLog);
        password = (EditText) findViewById(R.id.etRegPass);

        final String url = "http://reskarmapp.na4u.ru/registration.php";
        regButt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final StringBuilder builder = new StringBuilder();
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            Document doc = Jsoup.connect(url)
                                    .data("Registration", "true")
                                    .data("login", login.getText().toString())
                                    .data("password", password.getText().toString())
                                    .post();

                                Intent intent = new Intent(Registration.this, MainActivity.class);
                                startActivity(intent);

                            } catch (IOException ex) {
                            ex.printStackTrace();
                        }
                    }
                }).start();

            }
        });
    }
}
