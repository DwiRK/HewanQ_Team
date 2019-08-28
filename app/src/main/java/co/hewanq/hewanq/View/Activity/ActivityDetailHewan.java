package co.hewanq.hewanq.View.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import co.hewanq.hewanq.Model.Response.HewanResponse;
import co.hewanq.hewanq.Model.Response.UserResponse;
import co.hewanq.hewanq.Presenter.ApiRequest;
import co.hewanq.hewanq.Presenter.Server;
import co.hewanq.hewanq.R;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ActivityDetailHewan extends AppCompatActivity {

    private ApiRequest apiRequest;
    private HewanResponse hewanResponse;
    private int hewanId;
    private int userId;

    TextView nama, harga, nama_penjual, jenis_hewan, umur, kota, alamat,riwayat, deskripsi;
    ImageView gambar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_detail_hewan);

        apiRequest = Server.getClient().create(ApiRequest.class);

        Intent intent = getIntent();
        hewanId = Integer.valueOf(intent.getStringExtra("hewanId"));

        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        hewanResponse = new HewanResponse();

        gambar = findViewById(R.id.gambar);
        nama = findViewById(R.id.nama);
        harga = findViewById(R.id.harga);
        nama_penjual = findViewById(R.id.nama_penjual);
        jenis_hewan = findViewById(R.id.jenis_hewan);
        umur = findViewById(R.id.umur);
        kota = findViewById(R.id.kota);
        alamat = findViewById(R.id.alamat);
        riwayat = findViewById(R.id.riwayat);
        deskripsi = findViewById(R.id.deskripsi);

        displayDataHewan();
//        setDataFromUser();
    }

    public void displayDataHewan()
    {
        Call<HewanResponse> hewanCall = apiRequest.getDataHewan(hewanId);

        hewanCall.enqueue(new Callback<HewanResponse>() {
            @Override
            public void onResponse(Call<HewanResponse> call, Response<HewanResponse> response) {
                if(!response.isSuccessful())
                {
                    Toast.makeText(ActivityDetailHewan.this, response.message(), Toast.LENGTH_SHORT).show();
                }
                else
                {
                    nama.setText(response.body().getName());
                    harga.setText(String.valueOf(response.body().getPrice()));
                    jenis_hewan.setText(response.body().getJenisHewan());
                    riwayat.setText(response.body().getRiwayatKesehatan());
                    deskripsi.setText(response.body().getDeskripsiBarang());

                    userId = response.body().getIdUser();

                    setDataFromUser();

                    if(response.body().getPhoto() != null)
                    {
                        Glide.with(getApplicationContext())
                                .load(Server.url + "images/" + response.body().getPhoto())
                                .into(gambar);
                    }
                }
            }

            @Override
            public void onFailure(Call<HewanResponse> call, Throwable t) {
                Log.d("GET DETAIL HEWAN", "onFailure: " + t.getMessage());
            }
        });
    }

    public void setDataFromUser()
    {
        Call<UserResponse> getDetailUser = apiRequest.getUserDetail(userId);

        getDetailUser.enqueue(new Callback<UserResponse>() {
            @Override
            public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {
                if(!response.isSuccessful())
                {
                    Log.d("DETAILHEWAN", "onResponse: " + response.message());
                }
                else
                {
                    nama_penjual.setText(response.body().getUser().getName());
                    kota.setText(response.body().getUser().getKota());
                    alamat.setText(response.body().getUser().getAlamat());
                }
            }

            @Override
            public void onFailure(Call<UserResponse> call, Throwable t) {
                Log.d("DETAILHEWAN", "onFailure: " + t.getMessage());
            }
        });
    }
}