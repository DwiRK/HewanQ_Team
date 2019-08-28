package co.hewanq.hewanq.View.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import co.hewanq.hewanq.R;

public class ActivityDetailArtikel extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_detail_artikel);

        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        ImageView gambar = findViewById(R.id.gambar);
        TextView judul = findViewById(R.id.judul);
        TextView penulis = findViewById(R.id.penulis);
        TextView isi = findViewById(R.id.isi);

        // ### SETTING STATIS

        judul.setText("Inilah Kebiasaan Kucing Anda");
        penulis.setText("Drh. Dian Ratih");
        isi.setText("\"Hal ini yang biasa dilakukan kucing peliharaan Anda 1. Mengeong Suara kucing ini sangat khas saat dia ingin bermain, lapar, dan juga suka minum susu");

        /* ### SETTING DINAMIS ###

        // Utk mengambil ID
        Intent intent = getIntent();
        int id = Integer.valueOf(intent.getStringExtra("id"));

        Glide.with(this).load(AdapterList.recyclerListArtikel.get(id).getFotoArtikel()).into(gambar);

        judul.setText(AdapterList.recyclerListArtikel.get(id).getPenyediaArtikel());
        penulis.setText("Rp. " + String.valueOf(AdapterList.recyclerListArtikel.get(id).getpenulisArtikel())
                .replaceAll("([0-9])\\.0+([^0-9]|$)", "$1$2"));
        isi.setText(AdapterList.recyclerListArtikel.get(id).getisiArtikel());
        */
    }
}