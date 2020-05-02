package com.musulton.droid.messecure;

import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.net.Uri;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.provider.ContactsContract;
import android.provider.ContactsContract.CommonDataKinds.Phone;

import java.math.BigInteger;

public class Sendsms extends AppCompatActivity{

    private EditText pesanEdit, kontak;
    private Button sendButton;
    private String pubkey, noHp;
    String encodeData;

    private static final int CONTACT_PICKER_RESULT = 1001;

    // mengambil nomor kontak
    public void doLaunchContactPicker(View view) {
        Uri uri = ContactsContract.CommonDataKinds.Phone.CONTENT_URI;
        Intent contactPickerIntent = new Intent(Intent.ACTION_PICK, uri);
        startActivityForResult(contactPickerIntent, CONTACT_PICKER_RESULT);
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        String phone = "";
        Cursor contacts = null;
        try {
            if (resultCode == RESULT_OK) {
                switch (requestCode) {
                    case CONTACT_PICKER_RESULT:

                        // mendaatkan Uri dari kontak yg dipilih
                    Uri result = data.getData();
                        // dapatkan id kontak dari Uri (bagian terahir dari id kontak)
                    String id = result.getLastPathSegment();
                        // mengambil data kontak
                    contacts = getContentResolver().query(
                        ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                        null,
                        ContactsContract.CommonDataKinds.Phone._ID + "=?",
                        new String[] { id }, null);
                        // dapatkan indek dari nomor kontak
                    int phoneIdx = contacts.getColumnIndex(Phone.DATA);
                    if (contacts.moveToFirst()) {
                            // dapatkan nomor kontak
                        phone = contacts.getString(phoneIdx);
                        EditText phoneTxt = (EditText) findViewById(R.id.kontak);
                            // memasukan nomor telepon ke field EditText address
                        phoneTxt.setText(phone);
                    } else {
                        Toast.makeText(this, "Gagal...", Toast.LENGTH_LONG).show();
                    }
                    break;
                }
            } else {
                // gracefully handle failure
                Toast.makeText(Sendsms.this, "Kontak belum dipilih",
                    Toast.LENGTH_SHORT).show();
            }
        } catch (Exception e) {
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show();
        } finally {
            if (contacts != null) {
                contacts.close();
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sendsms);
        pesanEdit = (EditText) findViewById(R.id.pesanEdit);
        sendButton = (Button) findViewById(R.id.sendButton);
        kontak = (EditText) findViewById(R.id.kontak);

        SharedPreferences result = getSharedPreferences("savekey", Context.MODE_PRIVATE);
        pubkey = result.getString("pubkey", "Data not found");

        setActionBarTitle("Kirim Pesan");

        sendButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                noHp = kontak.getText().toString();
                String pesan = pesanEdit.getText().toString();
                if (pesan.equals("") && noHp.equals("")) {
                    Toast.makeText(getApplicationContext(), "Nomor atau pesan masih kosong", Toast.LENGTH_SHORT).show();
                    return;
                }
                byte[] userData = pesan.getBytes();
                try {
                    encodeData = RSA.encryptByPublicKey(userData, pubkey);
                    if(!encodeData.equalsIgnoreCase("ERROR")){
                        SmsManager sms = SmsManager.getDefault();
                        sms.sendTextMessage(noHp, null, encodeData, null, null);
                        Toast.makeText(Sendsms.this,"Pesan berhasil dikirim", Toast.LENGTH_SHORT).show();
                        finish();
                    }else{
                        Toast.makeText(Sendsms.this,"Pesan tidak bisa dienkripsi", Toast.LENGTH_SHORT).show();
                        return;
                    }
                } catch (Exception e) {
                    Toast.makeText(Sendsms.this,"Pesan gagal dikirim", Toast.LENGTH_SHORT).show();
                    e.printStackTrace();
                }
            }
        });
    }

    private void setActionBarTitle(String title){
        getSupportActionBar().setTitle(title);
    }
}