package co.hewanq.hewanq.View.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import co.hewanq.hewanq.Model.Response.JasaResponse;
import co.hewanq.hewanq.Presenter.ApiRequest;
import co.hewanq.hewanq.Presenter.Server;
import co.hewanq.hewanq.R;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ActivityDetailJasa extends AppCompatActivity {

    TextView nama, hewan, nama_penyedia, harga, kota, alamat, deskripsi, jam, hari;
    ImageView gambar;
    private int jasaId;

    ApiRequest apiRequest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_detail_jasa);

        apiRequest = Server.getClient().create(ApiRequest.class);

        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        Intent intent = getIntent();
        jasaId = Integer.valueOf(intent.getStringExtra("jasaId"));

        gambar = findViewById(R.id.gambar);
        nama = findViewById(R.id.nama);
        hewan = findViewById(R.id.hewannya);
        nama_penyedia = findViewById(R.id.nama_penyedia);
        harga = findViewById(R.id.harga);
        kota = findViewById(R.id.kota);
        alamat = findViewById(R.id.alamat);
        deskripsi = findViewById(R.id.deskripsi);
        jam = findViewById(R.id.jam);
        hari = findViewById(R.id.hari);

//        nama.setText("Penitipan HewanModel");
//        nama_penyedia.setText("Surabaya Pet Shop");
//        hewan.setText("Kucing, Burung");
//        harga.setText("Rp. 565.000");
//        kota.setText("Surabaya");
//        alamat.setText("Jln. Mulyosari no 95");
//        deskripsi.setText("Jujur dan dapat dipercaya");
//        jam.setText("9 Pagi - 5 Sore");
//        hari.setText("Setiap Hari");

        displayDataJasa();
    }

    public void displayDataJasa()
    {
        Call<JasaResponse> jasaCall = apiRequest.getDataJasa(jasaId);

        jasaCall.enqueue(new Callback<JasaResponse>() {
            @Override
            public void onResponse(Call<JasaResponse> call, Response<JasaResponse> response) {
                if(!response.isSuccessful())
                {
                    Toast.makeText(ActivityDetailJasa.this, response.message(), Toast.LENGTH_SHORT).show();
                }
                else
                {
                    nama.setText(response.body().getName());
                    harga.setText(String.valueOf(response.body().getHarga()));
                    nama_penyedia.setText(response.body().getName());
                    kota.setText(response.body().getKota());
                    alamat.setText(response.body().getAlamat());
                    hewan.setText(response.body().getHewanDilayani());
                    deskripsi.setText(response.body().getDeskripsi());
                    jam.setText(response.body().getJamBuka());
                    hari.setText(response.body().getHariBuka());

                    if(response.body().getPhoto() != null)
                    {
                        Glide.with(getApplicationContext())
                                .load(Server.url + "images/" + response.body().getPhoto())
                                .into(gambar);
                    }
                }
            }

            @Override
            public void onFailure(Call<JasaResponse> call, Throwable t) {
                Log.d("GET DETAIL JASA", "onFailure: " + t.getMessage());
            }
        });
    }
}