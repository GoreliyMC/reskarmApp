package com.example.reskarmv01alfa;

import android.os.Bundle;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;


public class AppMain extends AppCompatActivity {
    public String url = "http://reskarmApp.na4u.ru/get.php";

    private Switch light;
    private Switch signaling;
    private TextView tv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app_main);

        light = (Switch) findViewById(R.id.switchLight);
        tv = (TextView) findViewById(R.id.tv);

        light.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
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

    }
}
