package co.hewanq.hewanq.View.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;

import java.util.ArrayList;
import java.util.List;

import co.hewanq.hewanq.Model.Response.ListJualBeliResponse;
import co.hewanq.hewanq.Presenter.ApiRequest;
import co.hewanq.hewanq.Presenter.Server;
import co.hewanq.hewanq.View.Adapter.AdapterListJualBeli;
import co.hewanq.hewanq.R;
import co.hewanq.hewanq.Model.JualBeliModel;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ActivityListJualBeli extends AppCompatActivity {

    private GridLayoutManager gridLayoutManager;
    private RecyclerView recyclerViewJualBeli;
    private AdapterListJualBeli adapterListJualBeli;
    private List<JualBeliModel> jualBeliModel;

    private ApiRequest apiRequest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_jual_beli);

        apiRequest = Server.getClient().create(ApiRequest.class);

        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Jual Beli");

        setListJualBeli();
    }

    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.action_list_menu, menu);

        return true;
    }

    public void setListJualBeli()
    {
//        jualBeliModel = new ArrayList<>();
//
//        recyclerView = findViewById(R.id.list_jual_beli);
//        recyclerView.setHasFixedSize(true);
//
//        gridLayoutManager = new GridLayoutManager(this, 2);
//        gridLayoutManager.setOrientation(GridLayoutManager.VERTICAL);
//
//        recyclerView.setLayoutManager(gridLayoutManager);
//
//        jualBeliModel.add(new JualBeliModel("Jual Beli 1", "Surabaya", 20000.,
//                R.drawable.homeframe3));
//        jualBeliModel.add(new JualBeliModel("Jual Beli 1", "Surabaya", 20000.,
//                R.drawable.homeframe3));
//        jualBeliModel.add(new JualBeliModel("Jual Beli 1", "Surabaya", 20000.,
//                R.drawable.homeframe3));
//        jualBeliModel.add(new JualBeliModel("Jual Beli 1", "Surabaya", 20000.,
//                R.drawable.homeframe3));
//        jualBeliModel.add(new JualBeliModel("Jual Beli 1", "Surabaya", 20000.,
//                R.drawable.homeframe3));
//
//        adapterListJualBeli = new AdapterListJualBeli(this, jualBeliModel);
//        recyclerView.setAdapter(adapterListJualBeli);

        recyclerViewJualBeli = findViewById(R.id.list_jual_beli);
        recyclerViewJualBeli.setHasFixedSize(true);

        gridLayoutManager = new GridLayoutManager(this, 2);
        gridLayoutManager.setOrientation(GridLayoutManager.VERTICAL);

        recyclerViewJualBeli.setLayoutManager(gridLayoutManager);

        Call<ListJualBeliResponse> listHewan = apiRequest.getListJualBeli();

        listHewan.enqueue(new Callback<ListJualBeliResponse>() {
            @Override
            public void onResponse(Call<ListJualBeliResponse> call, Response<ListJualBeliResponse> response) {
                if(!response.isSuccessful())
                {
                    Log.d("Recycler", "JualBeliModel : " + response.message());
                }
                else
                {
                    jualBeliModel = response.body().getJualBeli();

                    adapterListJualBeli = new AdapterListJualBeli(ActivityListJualBeli.this,
                            R.layout.recyclerlist_jual_beli, jualBeliModel);
                    recyclerViewJualBeli.setAdapter(adapterListJualBeli);
                }
            }

            @Override
            public void onFailure(Call<ListJualBeliResponse> call, Throwable t) {
                Log.d("Recycler", "JualBeliModel : " + t.getMessage());
            }
        });
    }
}
