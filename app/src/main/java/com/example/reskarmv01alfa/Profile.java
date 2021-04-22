package com.example.reskarmv01alfa;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.badge.BadgeUtils;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.util.prefs.PreferenceChangeEvent;


public class Profile extends AppCompatActivity {
    public String url = "http://reskarmApp.na4u.ru/change.php";

    private Button passChange;
    private TextView viewLogin;
    private EditText newPass;
    private Button buttBack;


    SharedPreferences sLogin;
    final String SAVE_TEXT = ".";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        buttBack = findViewById(R.id.butBack);
        buttBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Profile.this, AppMain.class);
                startActivity(intent);
            }
        });

        viewLogin = (TextView) findViewById(R.id.tvShowLogin);
        sLogin = getSharedPreferences("SaveData", Context.MODE_PRIVATE);

        String getLogin = sLogin.getString("Login", "Error");
        viewLogin.setText(getLogin);


        newPass = (EditText) findViewById(R.id.etChangePass);
        passChange = (Button) findViewById(R.id.butPassChange);


        passChange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            Document doc = Jsoup.connect(url)
                                    .data("Change", "true")
                                    .data("login", viewLogin.getText().toString())
                                    .data("password", newPass.getText().toString())
                                    .post();
                        } catch (Exception e) {
                        }
                    }
                }).start();
            }
        });





       /* light.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                boolean checkLight = light.isChecked();
                if (checkLight) {
                    tv.setText("TVlight");
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            final StringBuilder builder = new StringBuilder();
                            try {
                                Document doc = Jsoup.connect(url+"?Light=On").get();

                            } catch (Exception e) {
                                builder.append("Error ").append(e.getMessage());
                            }

                        }
                    }).start();
                } else {
                    tv.setText("VTlight");
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            final StringBuilder builder = new StringBuilder();
                            try {
                                Document doc = Jsoup.connect(url+"?Light=Off").get();
                            } catch (Exception e) {
                                builder.append("Error ").append(e.getMessage());
                            }
                        }
                    }).start();
                }
            }
        });



        signaling = (Switch) findViewById(R.id.switchSignaling);

        signaling.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                boolean checkSignaling = signaling.isChecked();
                if (checkSignaling) {
                    tv.setText("TVsig");
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            final StringBuilder builder = new StringBuilder();
                            try {
                                Document doc = Jsoup.connect(url+"?Signaling=On").get();
                            } catch (Exception e) {
                                builder.append("Error ").append(e.getMessage());
                            }

                        }
                    }).start();
                } else {
                    tv.setText("VTsig");
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            final StringBuilder builder = new StringBuilder();
                            try {
                                Document doc = Jsoup.connect(url+"?Signaling=Off")
                                        .data("Signaling", "Off").get();
                            } catch (Exception e) {
                                builder.append("Error ").append(e.getMessage());
                            }

                        }
                    }).start();
                }
            }
        });

        profileBut = (Button) findViewById(R.id.butProfile);
        profileBut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AppMain.this, Profile.class);
                startActivity(intent);
            }
        });*/
    }
}
