package co.hewanq.hewanq.View.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import co.hewanq.hewanq.R;

public class ActivityDetailPembayaran extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_pembayaran);

        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Detail Pembayaran");

        Button lanjutkan_btn = findViewById(R.id.lanjutkan_btn);
        lanjutkan_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ActivityBayarSekarang.class);
                startActivity(intent);
            }
        });
    }
}
