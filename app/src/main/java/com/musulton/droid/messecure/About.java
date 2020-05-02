package com.musulton.droid.messecure;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class About extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        setActionBarTitle("Tentang Aplikasi");
    }

    private void setActionBarTitle(String title){
        getSupportActionBar().setTitle(title);
    }
}
