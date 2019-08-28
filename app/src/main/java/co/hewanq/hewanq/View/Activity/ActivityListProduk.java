package co.hewanq.hewanq.View.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;

import java.util.List;

import co.hewanq.hewanq.Model.HewanModel;
import co.hewanq.hewanq.Model.ProdukModel;
import co.hewanq.hewanq.Model.Response.ListHewanResponse;
import co.hewanq.hewanq.Model.Response.ListProdukResponse;
import co.hewanq.hewanq.Presenter.ApiRequest;
import co.hewanq.hewanq.Presenter.Server;
import co.hewanq.hewanq.R;
import co.hewanq.hewanq.View.Adapter.AdapterListHewan;
import co.hewanq.hewanq.View.Adapter.AdapterListProduk;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ActivityListProduk extends AppCompatActivity {

    private GridLayoutManager gridLayoutManager;
    private RecyclerView recyclerViewProduk;
    private AdapterListProduk adapterListProduk;
    private List<ProdukModel> produkModel;

    ApiRequest apiRequest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_produk);

        apiRequest = Server.getClient().create(ApiRequest.class);

        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("List Produk");

        setListProduk();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.action_list_menu, menu);

        return true;
    }

    public void setListProduk()
    {
        recyclerViewProduk = findViewById(R.id.list_produk);
        recyclerViewProduk.setHasFixedSize(true);

        gridLayoutManager = new GridLayoutManager(this, 2);
        gridLayoutManager.setOrientation(GridLayoutManager.VERTICAL);

        recyclerViewProduk.setLayoutManager(gridLayoutManager);

        Call<ListProdukResponse> listProduk = apiRequest.getListProduk();

        listProduk.enqueue(new Callback<ListProdukResponse>() {
            @Override
            public void onResponse(Call<ListProdukResponse> call, Response<ListProdukResponse> response) {
                if(!response.isSuccessful())
                {
                    Log.d("Recycler", "ProdukModel : " + response.message());
                }
                else
                {
                    produkModel = response.body().getItem();

                    adapterListProduk = new AdapterListProduk(ActivityListProduk.this, R.layout.recyclerlist_produk,
                            produkModel);
                    recyclerViewProduk.setAdapter(adapterListProduk);
                }
            }

            @Override
            public void onFailure(Call<ListProdukResponse> call, Throwable t) {
                Log.d("Recycler", "ProdukModel : " + t.getMessage());
            }
        });
    }
}
