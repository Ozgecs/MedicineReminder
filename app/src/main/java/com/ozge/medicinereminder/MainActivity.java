package com.ozge.medicinereminder;


import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;


public class MainActivity extends AppCompatActivity {
    ActionBar actionBar;

    //Uygulamanın açılış ekranının kaç saniye belirip kaybolacağının kodlandığı kısım.

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Thread splash = new Thread(){
            public void run()
            {
                try
                {
                    sleep(2000);
                    Intent intent = new Intent(getApplicationContext(),HomeActivity.class);
                    startActivity(intent);
                    finish();
                }catch (Exception e)
                {
                    e.printStackTrace();
                }
            }
        };
        splash.start();
    }
}