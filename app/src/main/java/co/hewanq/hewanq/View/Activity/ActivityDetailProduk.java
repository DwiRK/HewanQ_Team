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
import co.hewanq.hewanq.Model.Response.ProdukResponse;
import co.hewanq.hewanq.Model.Response.UserResponse;
import co.hewanq.hewanq.Presenter.ApiRequest;
import co.hewanq.hewanq.Presenter.Server;
import co.hewanq.hewanq.R;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ActivityDetailProduk extends AppCompatActivity {

    private ApiRequest apiRequest;
    private ProdukResponse produkResponse;
    private int produkId;

    private int userId;

    TextView nama, harga, nama_penjual, kota, alamat, deskripsi, stok;
    ImageView gambar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_detail_produk);

        apiRequest = Server.getClient().create(ApiRequest.class);

        Intent intent = getIntent();
        produkId = Integer.valueOf(intent.getStringExtra("produkId"));

        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        produkResponse = new ProdukResponse();

        gambar = findViewById(R.id.gambar);
        nama = findViewById(R.id.nama);
        harga = findViewById(R.id.harga);
        nama_penjual = findViewById(R.id.nama_penjual);
        stok = findViewById(R.id.stok);
        kota = findViewById(R.id.kota);
        alamat = findViewById(R.id.alamat);
        deskripsi = findViewById(R.id.deskripsi);

        displayDataProduk();
    }

    public void displayDataProduk()
    {
        Call<ProdukResponse> produkCall = apiRequest.getDataProduk(produkId);

        produkCall.enqueue(new Callback<ProdukResponse>() {
            @Override
            public void onResponse(Call<ProdukResponse> call, Response<ProdukResponse> response) {
                if(!response.isSuccessful())
                {
                    Toast.makeText(ActivityDetailProduk.this, response.message(), Toast.LENGTH_SHORT).show();
                }
                else
                {
                    nama.setText(response.body().getName());
                    harga.setText(String.valueOf(response.body().getPrice()));
                    stok.setText(String.valueOf(response.body().getStock()));
                    kota.setText(response.body().getKota());
                    alamat.setText(response.body().getAlamat());
                    nama_penjual.setText(response.body().getRiwayatKesehatan());
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
            public void onFailure(Call<ProdukResponse> call, Throwable t) {
                Log.d("GET DETAIL Produk", "onFailure: " + t.getMessage());
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