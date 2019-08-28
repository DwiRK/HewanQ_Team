package co.hewanq.hewanq.View.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import co.hewanq.hewanq.Model.Response.DokterResponse;
import co.hewanq.hewanq.Presenter.ApiRequest;
import co.hewanq.hewanq.Presenter.Server;
import co.hewanq.hewanq.R;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ActivityDetailDokter extends AppCompatActivity {

    TextView nama, hewan, tempat_praktik, kota, alamat;
    ImageView gambar;

    private int dokterId;

    private ApiRequest apiRequest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_detail_dokter);

        apiRequest = Server.getClient().create(ApiRequest.class);

        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        Intent intent = getIntent();
        dokterId = Integer.valueOf(intent.getStringExtra("dokterId"));

        gambar = findViewById(R.id.gambar);
        nama = findViewById(R.id.nama);
        hewan = findViewById(R.id.hewannya);
        tempat_praktik = findViewById(R.id.tempat_praktik);
        kota = findViewById(R.id.kota);
        alamat = findViewById(R.id.alamat);

        // ### SETTING STATIS

//        nama.setText("Drh. Andre Wicaksono");
//        tempat_praktik.setText("Surabaya Pet Shop");
//        hewan.setText("Kucing, Burung");
//        kota.setText("Surabaya");
//        alamat.setText("Jln. Mulyosari no 95");

        displayDataDokter();
    }

    public void displayDataDokter()
    {
        Call<DokterResponse> dokterCall = apiRequest.getDataDokter(dokterId);

        dokterCall.enqueue(new Callback<DokterResponse>() {
            @Override
            public void onResponse(Call<DokterResponse> call, Response<DokterResponse> response) {
                if(!response.isSuccessful())
                {
                    Toast.makeText(ActivityDetailDokter.this, response.message(), Toast.LENGTH_SHORT).show();
                }
                else
                {
                    nama.setText(response.body().getDokter().getName());
                    kota.setText(response.body().getDokter().getKota());
                    alamat.setText(response.body().getDokter().getAlamat());
                    hewan.setText(response.body().getDokter().getHewanDilayani());
                    tempat_praktik.setText(response.body().getDokter().getAlamat());

                    if(response.body().getDokter().getPhoto() != null)
                    {
                        Glide.with(getApplicationContext())
                                .load(Server.url + "images/" + response.body().getDokter().getPhoto())
                                .into(gambar);
                    }
                }
            }

            @Override
            public void onFailure(Call<DokterResponse> call, Throwable t) {
                Log.d("GET DETAIL DOKTER", "onFailure: " + t.getMessage());
            }
        });
    }
}