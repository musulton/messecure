package com.musulton.droid.messecure;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Kode extends AppCompatActivity {

    private EditText mViewUser, mViewPassword, mViewRepassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kode);

        /* Menginisialisasi variable dengan Form User, Form Password, dan Form Repassword
        dari Layout RegisterActivity */
        mViewPassword =findViewById(R.id.et_passwordSignup);
        mViewRepassword =findViewById(R.id.et_passwordSignup2);

        setActionBarTitle("Set Kode");

        /* Menjalankan Method razia() jika merasakan tombol SignUp di keyboard disentuh */
        mViewRepassword.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE || actionId == EditorInfo.IME_NULL) {
                    razia();
                    return true;
                }
                return false;
            }
        });
        /* Menjalankan Method razia() jika merasakan tombol SignUp disentuh */
        findViewById(R.id.button_signupSignup).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                razia();
            }
        });
    }

    /** Men-check inputan User dan Password dan Memberikan akses ke MainActivity */
    private void razia(){
        /* Mereset semua Error dan fokus menjadi default */

        mViewPassword.setError(null);
        mViewRepassword.setError(null);
        View fokus = null;
        boolean cancel = false;

        /* Mengambil text dari Form User, Password, Repassword dengan variable baru bertipe String*/
        String repassword = mViewRepassword.getText().toString();
        String password = mViewPassword.getText().toString();


        /* Jika form password kosong dan MEMENUHI kriteria di Method cekPassword maka,
         * Reaksinya sama dengan percabangan User di atas. Bedanya untuk Password dan Repassword*/
        if (TextUtils.isEmpty(password)){
            //Jika form password kosong
            mViewPassword.setError("Kode akses baru masih kosong");
            fokus = mViewPassword;
            cancel = true;
        }else if (!cekPassword(password,repassword)){
            //Jika password tidak sama antara Confirm Password dengan Password
            mViewRepassword.setError("Konfirmasi kode akses tidak cocok");
            fokus = mViewRepassword;
            cancel = true;
        }

        /** Jika cancel true, variable fokus mendapatkan fokus. Jika false, maka
         *  Kembali ke LoginActivity dan Set User dan Password untuk data yang terdaftar */
        if (cancel){
            //Memindahkan Fokus ke View yang ada Errornya
            fokus.requestFocus();
        }else{
            //Mengubah data yang sebelumnya sudah ter registrasi menjadi baru

            Preferences.setRegisteredPass(getBaseContext(),password);
            Toast.makeText(this, "Kode akses berhasil diganti", Toast.LENGTH_LONG).show();
            finish();
        }
    }

    /** True jika parameter password sama dengan parameter repassword */
    private boolean cekPassword(String password, String repassword){
        return password.equals(repassword);
    }

    private void setActionBarTitle(String title){
        getSupportActionBar().setTitle(title);
    }

}
