package co.hewanq.hewanq.View.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;

import java.util.List;

import co.hewanq.hewanq.Model.HewanModel;
import co.hewanq.hewanq.Model.Response.ListHewanResponse;
import co.hewanq.hewanq.Presenter.ApiRequest;
import co.hewanq.hewanq.Presenter.Server;
import co.hewanq.hewanq.View.Adapter.AdapterListHewan;
import co.hewanq.hewanq.R;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ActivityListHewan extends AppCompatActivity {

    private GridLayoutManager gridLayoutManager;
    private RecyclerView recyclerViewHewan;
    private AdapterListHewan adapterListHewan;
    private List<HewanModel> hewanModel;

    ApiRequest apiRequest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_hewan);

        apiRequest = Server.getClient().create(ApiRequest.class);

        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("List Hewan");

        setListHewan();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.action_list_menu, menu);

        return true;
    }

    public void setListHewan()
    {
        recyclerViewHewan = findViewById(R.id.list_hewan);
        recyclerViewHewan.setHasFixedSize(true);

        gridLayoutManager = new GridLayoutManager(this, 2);
        gridLayoutManager.setOrientation(GridLayoutManager.VERTICAL);

        recyclerViewHewan.setLayoutManager(gridLayoutManager);

        Call<ListHewanResponse> listHewan = apiRequest.getListHewan();

        listHewan.enqueue(new Callback<ListHewanResponse>() {
            @Override
            public void onResponse(Call<ListHewanResponse> call, Response<ListHewanResponse> response) {
                if(!response.isSuccessful())
                {
                    Log.d("Recycler", "HewanModel : " + response.message());
                }
                else
                {
                    hewanModel = response.body().getPet();

                    adapterListHewan = new AdapterListHewan(ActivityListHewan.this, R.layout.recyclerlist_hewan,
                            hewanModel);
                    recyclerViewHewan.setAdapter(adapterListHewan);
                }
            }

            @Override
            public void onFailure(Call<ListHewanResponse> call, Throwable t) {
                Log.d("Recycler", "HewanModel : " + t.getMessage());
            }
        });
    }
}
