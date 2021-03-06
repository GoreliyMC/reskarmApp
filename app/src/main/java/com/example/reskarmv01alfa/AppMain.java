package com.example.reskarmv01alfa;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.badge.BadgeUtils;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;


public class AppMain extends AppCompatActivity {
    public String url = "http://reskarmApp.na4u.ru/get.php";

    private Switch light;
    private Switch signaling;
    private Button butExit;
    private Button profileBut;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app_main);

        butExit = (Button) findViewById(R.id.butExit);
        butExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AppMain.this, MainActivity.class);
                startActivity(intent);
            }
        });

        light = (Switch) findViewById(R.id.switchLight);


        light.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                boolean checkLight = light.isChecked();
                if (checkLight) {
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
        });
    }
}
