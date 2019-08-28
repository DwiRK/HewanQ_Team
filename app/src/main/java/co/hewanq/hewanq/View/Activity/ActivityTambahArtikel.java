package co.hewanq.hewanq.View.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import co.hewanq.hewanq.R;

public class ActivityTambahArtikel extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah_artikel);

        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Tambah Artikel");
    }
}
