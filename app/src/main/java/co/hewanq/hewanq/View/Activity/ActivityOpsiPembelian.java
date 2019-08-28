package co.hewanq.hewanq.View.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import co.hewanq.hewanq.R;

public class ActivityOpsiPembelian extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_opsi_pembelian);

        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Opsi Pembelian");

        Button lanjutkan_btn = findViewById(R.id.lanjutkan_btn);
        lanjutkan_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ActivityDetailPembayaran.class);
                startActivity(intent);
            }
        });
    }
}
