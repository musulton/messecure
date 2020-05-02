package com.musulton.droid.messecure;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class Instructions extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_instructions);

        setActionBarTitle("Petunjuk Penggunaan");
    }

    private void setActionBarTitle(String title){
        getSupportActionBar().setTitle(title);
    }
}
