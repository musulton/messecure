package com.musulton.droid.messecure;

import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import android.net.Uri;
import android.database.Cursor;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class Readsms extends AppCompatActivity {

    String decodeData;
    private String prikey;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_readsms);
        final ListView list = findViewById(R.id.list);

        setActionBarTitle("Baca Pesan Messecure");

        SharedPreferences result = getSharedPreferences("savekey", Context.MODE_PRIVATE);
        prikey = result.getString("prikey", "Data not found");

        // TODO Auto-generated method stub
        List<String> msgs = getSMS(prikey);
        if (msgs.isEmpty()) {
            msgs.add("\n" + "Tidak ada pesan yang dapat dipecahkan dengan kunci yang digunakan" + "\n");
        }
        ArrayAdapter<String> smsAdapter = new ArrayAdapter<String>(getBaseContext(),
                android.R.layout.simple_list_item_1, msgs);
        list.setAdapter(smsAdapter);
    }

    private void setActionBarTitle(String title){
        Intent bacapesan = getIntent();
        String judul = bacapesan.getStringExtra("title");
        getSupportActionBar().setTitle(judul);
    }

    public List<String> getSMS(String prikey) {
        List<String> list = new ArrayList<String>();
        Intent tipepesan = getIntent();
        Uri uri = Uri.parse("content://sms/" + tipepesan.getStringExtra("tipepesan"));
        Cursor c = null;
        try{
            c = getApplicationContext().getContentResolver().query(uri, null, null ,null,null);
            for (boolean hasData = c.moveToFirst(); hasData; hasData = c.moveToNext()) {

                String noHP = c.getString(c.getColumnIndex("address"));
                Uri contactUri = Uri.withAppendedPath( ContactsContract.PhoneLookup.CONTENT_FILTER_URI, Uri.encode(noHP));
                Cursor cur = getContentResolver().query(contactUri, null,null, null, null);
                ContentResolver contect_resolver = getContentResolver();

                int size = cur.getCount();
                if (size > 0 && cur != null) {
                    for (int i = 0; i < size; i++) {
                        cur.moveToPosition(i);

                        String id1 = cur.getString(cur .getColumnIndexOrThrow(ContactsContract.Contacts._ID));

                        Cursor phoneCur = contect_resolver .query(contactUri,null,ContactsContract.CommonDataKinds.Phone.CONTACT_ID
                                + " = ?",new String[]{id1}, null);
                        if (phoneCur.moveToFirst()) {
                            String namaKontak = phoneCur.getString(phoneCur
                                    .getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
                            phoneCur.close();
                            noHP = namaKontak;
                        }
                    }
                    cur.close();
                }

                final String msg = c.getString(c.getColumnIndexOrThrow("body"));
                BigInteger bi = new BigInteger(msg, 16);
                byte[] dek = bi.toByteArray();
                decodeData = RSA.decryptByPrivateKey(dek, prikey);
                if(!decodeData.equalsIgnoreCase("ERROR")){
                    list.add("\n" + noHP + "\n" + decodeData + "\n");
                }
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        c.close();
        return list;
    }
}