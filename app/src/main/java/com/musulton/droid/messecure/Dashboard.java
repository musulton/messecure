package com.musulton.droid.messecure;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.TextView;

public class Dashboard extends AppCompatActivity implements View.OnClickListener {

    private CardView keyCard, sendCard, inboxCard, sentboxCard, instructionsCard, aboutCard, kodeCard, logoutCard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        // Mendefinisikan tombol card
        keyCard = (CardView) findViewById(R.id.key_card);
        sendCard = (CardView) findViewById(R.id.send_card);
        inboxCard = (CardView) findViewById(R.id.inbox_card);
        sentboxCard = (CardView) findViewById(R.id.sentbox_card);
        instructionsCard = (CardView) findViewById(R.id.instructions_card);
        aboutCard = (CardView) findViewById(R.id.about_card);
        kodeCard = (CardView) findViewById(R.id.kode_card);
        logoutCard = (CardView) findViewById(R.id.logout_card);
        // Click listener agar dapat diklik dan ditujukan pada card
        keyCard.setOnClickListener(this);
        sendCard.setOnClickListener(this);
        inboxCard.setOnClickListener(this);
        sentboxCard.setOnClickListener(this);
        instructionsCard.setOnClickListener(this);
        aboutCard.setOnClickListener(this);
        kodeCard.setOnClickListener(this);
        logoutCard.setOnClickListener(this);

        setActionBarTitle("Dashboard");
    }

    public static  boolean hasPermissions(Context context, String... permissions) {
        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && context != null && permissions != null) {
            for (String permission : permissions) {
                if (ActivityCompat.checkSelfPermission(context, permission) != PackageManager.PERMISSION_GRANTED) {
                    return false;
                }
            }
        }
        return true;
    }

    @Override
    public void onClick(View v){
        Intent i;

        switch (v.getId()){
            case R.id.key_card :
                i = new Intent(Dashboard.this, Key.class);
                startActivity(i);
                break;
             case R.id.send_card :
                i = new Intent(Dashboard.this, Sendsms.class);
                startActivity(i);
                break;
            case R.id.inbox_card :
                i = new Intent (Dashboard.this, Readsms.class);
                i.putExtra("tipepesan", "inbox");
                i.putExtra("title", "Pesan Masuk");
                startActivity(i);
                break;
            case R.id.sentbox_card :
                i = new Intent (Dashboard.this, Readsms.class);
                i.putExtra("tipepesan", "sent");
                i.putExtra("title", "Pesan Terkirim");
                startActivity(i);
                break;
            case R.id.instructions_card :
                i = new Intent(Dashboard.this, Instructions.class);
                startActivity(i);
                break;
            case R.id.about_card :
                i = new Intent (Dashboard.this, About.class);
                startActivity(i);
                break;
            case R.id.kode_card :
                i = new Intent (Dashboard.this, Kode.class);
                startActivity(i);
                break;
            case R.id.logout_card :
                Preferences.clearLoggedInUser(getBaseContext());
                finish();
                startActivity(new Intent(getBaseContext(),Login.class));
                break;
            default:
                break;
        }
    }

    private void setActionBarTitle(String title){
        getSupportActionBar().setTitle(title);
    }
}
