package co.hewanq.hewanq.View.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;

import java.util.List;

import co.hewanq.hewanq.Model.DokterModel;
import co.hewanq.hewanq.Model.Response.ListDokterResponse;
import co.hewanq.hewanq.Model.UserModel;
import co.hewanq.hewanq.Presenter.ApiRequest;
import co.hewanq.hewanq.Presenter.Server;
import co.hewanq.hewanq.View.Adapter.AdapterListDokter;
import co.hewanq.hewanq.R;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ActivityListDokter extends AppCompatActivity
{
    private GridLayoutManager gridLayoutManager;
    private RecyclerView recyclerViewDokter;
    private AdapterListDokter adapterListDokter;
    private List<DokterModel> dokterModel;

    ApiRequest apiRequest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_dokter);

        apiRequest = Server.getClient().create(ApiRequest.class);

        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Rekomendasi Dokter");

        setListDokter();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.action_list_menu, menu);

        return true;
    }

    public void setListDokter()
    {
        recyclerViewDokter = findViewById(R.id.list_dokter);
        recyclerViewDokter.setHasFixedSize(true);

        gridLayoutManager = new GridLayoutManager(this, 2);
        gridLayoutManager.setOrientation(GridLayoutManager.VERTICAL);

        recyclerViewDokter.setLayoutManager(gridLayoutManager);

        Call<ListDokterResponse> listDokter = apiRequest.getListDokter();

        listDokter.enqueue(new Callback<ListDokterResponse>() {
            @Override
            public void onResponse(Call<ListDokterResponse> call, Response<ListDokterResponse> response) {
                if(!response.isSuccessful())
                {
                    Log.d("Recycler", "DokterModel : " + response.message());
                }
                else
                {
                    dokterModel = response.body().getDokter();

                    adapterListDokter = new AdapterListDokter(ActivityListDokter.this,
                            R.layout.recyclerlist_dokter, dokterModel);

                    recyclerViewDokter.setAdapter(adapterListDokter);
                }
            }

            @Override
            public void onFailure(Call<ListDokterResponse> call, Throwable t) {
                Log.d("Recycler", "DokterModel : " + t.getMessage());
            }
        });
    }
}
