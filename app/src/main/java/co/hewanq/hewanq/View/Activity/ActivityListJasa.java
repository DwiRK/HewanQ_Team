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

import co.hewanq.hewanq.Model.Response.ListJasaResponse;
import co.hewanq.hewanq.Presenter.ApiRequest;
import co.hewanq.hewanq.Presenter.Server;
import co.hewanq.hewanq.View.Adapter.AdapterListJasa;
import co.hewanq.hewanq.R;
import co.hewanq.hewanq.Model.JasaModel;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ActivityListJasa extends AppCompatActivity {

    private RecyclerView recyclerViewJasa;
    private AdapterListJasa adapterListJasa;
    private List<JasaModel> jasaModel;

    ApiRequest apiRequest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_jasa);

        apiRequest = Server.getClient().create(ApiRequest.class);

        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Rekomendasi Jasa");

        setListJasa();
    }

    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.action_list_menu, menu);

        return true;
    }

    public void setListJasa()
    {
        recyclerViewJasa = findViewById(R.id.list_jasa);
        recyclerViewJasa.setHasFixedSize(true);

        recyclerViewJasa.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL,
                false));

        Call<ListJasaResponse> listJasa = apiRequest.getListJasa();

        listJasa.enqueue(new Callback<ListJasaResponse>() {
            @Override
            public void onResponse(Call<ListJasaResponse> call, Response<ListJasaResponse> response) {
                if(!response.isSuccessful())
                {
                    Log.d("Recycler", "JasaModel : " + response.message());
                }
                else
                {
                    jasaModel = response.body().getjasa();

                    adapterListJasa = new AdapterListJasa(ActivityListJasa.this, R.layout.recyclerlist_jasa,
                            jasaModel);
                    recyclerViewJasa.setAdapter(adapterListJasa);
                }
            }

            @Override
            public void onFailure(Call<ListJasaResponse> call, Throwable t) {
                Log.d("Recycler", "JasaModel : " + t.getMessage());
            }
        });
    }
}
