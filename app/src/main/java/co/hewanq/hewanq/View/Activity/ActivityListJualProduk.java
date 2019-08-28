package co.hewanq.hewanq.View.Activity;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import java.util.HashMap;
import java.util.List;

import co.hewanq.hewanq.Model.ProdukModel;
import co.hewanq.hewanq.Model.Response.ListProdukResponse;
import co.hewanq.hewanq.Presenter.ApiRequest;
import co.hewanq.hewanq.Presenter.Server;
import co.hewanq.hewanq.Presenter.SessionManager;
import co.hewanq.hewanq.R;
import co.hewanq.hewanq.View.Adapter.AdapterListProduk;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ActivityListJualProduk extends AppCompatActivity {
    ImageView add_btn, no_produk;
    Intent intent;

    private GridLayoutManager gridLayoutManager;
    private RecyclerView recyclerViewProduk;
    private AdapterListProduk adapterListProduk;
    private List<ProdukModel> produkModel;

    ApiRequest apiRequest;

    private HashMap<String, String> userData;

    private SessionManager sessionManager;

    private int userId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_jual_produk);

        apiRequest = Server.getClient().create(ApiRequest.class);

        sessionManager = new SessionManager(ActivityListJualProduk.this);
        userData = sessionManager.getSavedToken();

        userId = Integer.valueOf(userData.get(sessionManager.getUserId()));

        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Produk Saya");
        actionBar.setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.blue_theme)));

        add_btn = findViewById(R.id.add_btn);
        no_produk = findViewById(R.id.no_produk);

        add_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(getApplicationContext(), ActivityTambahProduk.class);
                intent.putExtra("id", userData.get(sessionManager.getUserId()));
                startActivity(intent);
            }
        });

        setListProduk();
    }

    public void setListProduk()
    {
        recyclerViewProduk = findViewById(R.id.list_recycler_produk_user);
        recyclerViewProduk.setHasFixedSize(true);

        gridLayoutManager = new GridLayoutManager(this, 2);
        gridLayoutManager.setOrientation(GridLayoutManager.VERTICAL);

        recyclerViewProduk.setLayoutManager(gridLayoutManager);

        Call<ListProdukResponse> listProduk = apiRequest.getListProdukUser(userId);

        listProduk.enqueue(new Callback<ListProdukResponse>() {
            @Override
            public void onResponse(Call<ListProdukResponse> call, Response<ListProdukResponse> response) {
                if(!response.isSuccessful())
                {
                    Log.d("Recycler", "ProdukModel : " + response.message());
                    no_produk.setVisibility(View.GONE);
                }
                else
                {
                    produkModel = response.body().getItem();

                    adapterListProduk = new AdapterListProduk(ActivityListJualProduk.this, R.layout.recyclerlist_produk,
                            produkModel);
                    recyclerViewProduk.setAdapter(adapterListProduk);

                    no_produk.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onFailure(Call<ListProdukResponse> call, Throwable t) {
                Log.d("Recycler", "ProdukModel : " + t.getMessage());
            }
        });
    }
}
