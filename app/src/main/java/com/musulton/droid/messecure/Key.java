package com.musulton.droid.messecure;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Map;

public class Key extends AppCompatActivity implements View.OnClickListener{

    private EditText pkEdit, skEdit;
    private Button keygenButton, setkeyButton, defkeyButton, viewkeyButton;
    private static String publickey, privatekey;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_key);

        pkEdit = (EditText) findViewById(R.id.pkEdit);
        skEdit = (EditText) findViewById(R.id.skEdit);
        keygenButton = (Button) findViewById(R.id.keygenButton);
        setkeyButton = (Button) findViewById(R.id.setkeyButton);
        defkeyButton = (Button) findViewById(R.id.defkeyButton);
        viewkeyButton = (Button) findViewById(R.id.viewkeyButton);

        keygenButton.setOnClickListener(this);
        setkeyButton.setOnClickListener(this);
        defkeyButton.setOnClickListener(this);
        viewkeyButton.setOnClickListener(this);

        setActionBarTitle("Set Kunci");
    }

    @Override
    public void onClick(View v){
        if(v == keygenButton){
            try{
                Map<String, Object> keyMap = RSA.initKey();
                publickey = RSA.getPublicKey(keyMap);
                privatekey = RSA.getPrivateKey(keyMap);
                pkEdit.setText(publickey);
                skEdit.setText(privatekey);
            }catch(Exception e){
                e.printStackTrace();
            }
        }else if(v == defkeyButton){
            publickey = "MFwwDQYJKoZIhvcNAQEBBQADSwAwSAJBAL+R16UJI/mDSJedmH57QuQKwd+fmarcEcxCFr2GzHuMqEQg6PwCFBQK/OuqlYP0q53soyeXqjPr3ABcQL4JnakCAwEAAQ==";
            privatekey = "MIIBVAIBADANBgkqhkiG9w0BAQEFAASCAT4wggE6AgEAAkEAv5HXpQkj+YNIl52YfntC5ArB35+ZqtwRzEIWvYbMe4yoRCDo/AIUFAr866qVg/SrneyjJ5eqM+vcAFxAvgmdqQIDAQABAkAwqdOl0NSapYbjPN1oA9fSJglpzRk0FgNPZ9pu+rcnCvrPYkWGSbVj8JHjJF+WoDQOOkZJ7qsjzqmm3vKoSiIhAiEA+KSGnIFEzcQoUrPi0/6s04UduzUzKgyEwRmepxToR6MCIQDFPPzCsOEEKiZ7KwNgqNYC7mSMmlvPtVZp6qsAROuKQwIhAIxyiuL+lfnQ8qH8oIT1F3SHfcrQey0mUxqsORTR138XAiAn012yLibxQVWNzyyaatzzsJEq9swND6+IKXMn1cK9owIgJogntEzzGsNzh25s6NdMJYaD6/wCCJNHbJUbBeDf4XA=";
            pkEdit.setText(publickey);
            skEdit.setText(privatekey);
        }else if(v == setkeyButton){
            sharedPreferences = getSharedPreferences("savekey", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();

            if(pkEdit != null && skEdit != null){
                String kuncipublik = pkEdit.getText().toString();
                String kuncirahasia = skEdit.getText().toString();
                editor.putString("pubkey", kuncipublik);
                editor.apply();
                editor.putString("prikey", kuncirahasia);
                editor.apply();
                Toast.makeText(this, "Kunci siap dipakai", Toast.LENGTH_LONG).show();
                finish();
            }else{
                Toast.makeText(this, "Kunci masih kosong", Toast.LENGTH_LONG).show();
                return;
            }

        }else if(v == viewkeyButton){
            SharedPreferences result = getSharedPreferences("savekey", Context.MODE_PRIVATE);
            String pkey = result.getString("pubkey", "Kunci masih kosong") ;
            String skey = result.getString("prikey", "Kunci masih kosong") ;
            if(pkey != null && skey != null){
                pkEdit.setText(pkey);
                skEdit.setText(skey);
            }else{
                Toast.makeText(this, "Kunci masih kosong", Toast.LENGTH_LONG).show();
                return;
            }
        }
    }

    private void setActionBarTitle(String title){
        getSupportActionBar().setTitle(title);
    }
}
