package co.hewanq.hewanq.View.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;

import java.util.ArrayList;
import java.util.List;

import co.hewanq.hewanq.Model.ArtikelModel;
import co.hewanq.hewanq.Model.Response.ListArtikelResponse;
import co.hewanq.hewanq.Model.UserModel;
import co.hewanq.hewanq.Presenter.ApiRequest;
import co.hewanq.hewanq.Presenter.Server;
import co.hewanq.hewanq.View.Adapter.AdapterListArtikelOneCol;
import co.hewanq.hewanq.View.Adapter.AdapterListArtikelTwoCol;
import co.hewanq.hewanq.R;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ActivityListArtikel extends AppCompatActivity
{
    private GridLayoutManager gridLayoutManager;
    private RecyclerView recyclerViewArtikel;
    private AdapterListArtikelOneCol adapterListArtikelOneCol;
    private AdapterListArtikelTwoCol adapterListArtikelTwoCol;
    private List<UserModel> artikelModel;

    private ApiRequest apiRequest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_artikel);

        apiRequest = Server.getClient().create(ApiRequest.class);

        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Baca Artikel");

        setListArtikelNoGrid();
        //setListArtikelGrid();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.action_list_search, menu);

        return true;
    }

    public void setListArtikelNoGrid()
    {
        recyclerViewArtikel = findViewById(R.id.list_artikel_no_grid);
        recyclerViewArtikel.setHasFixedSize(true);
        recyclerViewArtikel.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL,
                false));

        Call<ListArtikelResponse> listArtikel = apiRequest.getListArtikel();

        listArtikel.enqueue(new Callback<ListArtikelResponse>() {
            @Override
            public void onResponse(Call<ListArtikelResponse> call, Response<ListArtikelResponse> response) {
                if(!response.isSuccessful())
                {
                    Log.d("Recycler", "ArtikelModel : " + response.message());
                }
                else
                {
                    artikelModel = response.body().getUserModel();

                    adapterListArtikelOneCol = new AdapterListArtikelOneCol(ActivityListArtikel.this,
                            R.layout.recyclerlist_artikel_one_coloumn, artikelModel);

                    recyclerViewArtikel.setAdapter(adapterListArtikelOneCol);
                }
            }

            @Override
            public void onFailure(Call<ListArtikelResponse> call, Throwable t) {
                Log.d("Recycler", "ArtikelModel : " + t.getMessage());
            }
        });
    }

//    public void setListArtikelGrid()
//    {
//        artikelModel = new ArrayList<>();
//
//        recyclerView = findViewById(R.id.list_artikel_grid);
//        recyclerView.setHasFixedSize(true);
//
//        gridLayoutManager = new GridLayoutManager(this, 2);
//        gridLayoutManager.setOrientation(GridLayoutManager.VERTICAL);
//
//        recyclerView.setLayoutManager(gridLayoutManager);
//
//        artikelModel.add(new ArtikelModel("Artikel1", "Drh. Dian Ratih",
//                R.drawable.homeframe3));
//        artikelModel.add(new ArtikelModel("Artikel1", "Drh. Dian Ratih",
//                R.drawable.homeframe3));
//        artikelModel.add(new ArtikelModel("Artikel1", "Drh. Dian Ratih",
//                R.drawable.homeframe3));
//        artikelModel.add(new ArtikelModel("Artikel1", "Drh. Dian Ratih",
//                R.drawable.homeframe3));
//
//        adapterListArtikelTwoCol = new AdapterListArtikelTwoCol(this, artikelModel);
//        recyclerView.setAdapter(adapterListArtikelTwoCol);
//    }
}
