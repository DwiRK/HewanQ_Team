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

import co.hewanq.hewanq.Model.HewanModel;
import co.hewanq.hewanq.Model.Response.ListHewanResponse;
import co.hewanq.hewanq.Presenter.ApiRequest;
import co.hewanq.hewanq.Presenter.Server;
import co.hewanq.hewanq.Presenter.SessionManager;
import co.hewanq.hewanq.R;
import co.hewanq.hewanq.View.Adapter.AdapterListHewan;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ActivityListJualHewan extends AppCompatActivity {
    ImageView add_btn, no_pet;
    Intent intent;

    private GridLayoutManager gridLayoutManager;
    private RecyclerView recyclerViewHewan;
    private AdapterListHewan adapterListHewan;
    private List<HewanModel> hewanModel;

    private HashMap<String, String> userData;

    private ApiRequest apiRequest;

    private SessionManager sessionManager;

    private int userId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_jual_hewan);

        apiRequest = Server.getClient().create(ApiRequest.class);

        sessionManager = new SessionManager(ActivityListJualHewan.this);
        userData = sessionManager.getSavedToken();

        userId = Integer.valueOf(userData.get(sessionManager.getUserId()));

        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Hewan Saya");
        actionBar.setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.blue_theme)));

        add_btn = findViewById(R.id.add_btn);
        no_pet = findViewById(R.id.no_pet);

        setListHewan();

        add_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(getApplicationContext(), ActivityTambahHewan.class);
                intent.putExtra("id", userData.get(sessionManager.getUserId()));
                startActivity(intent);
            }
        });
    }

    public void setListHewan()
    {
        recyclerViewHewan = findViewById(R.id.list_recycler_hewan_user);
        recyclerViewHewan.setHasFixedSize(true);

        gridLayoutManager = new GridLayoutManager(this, 2);
        gridLayoutManager.setOrientation(GridLayoutManager.VERTICAL);

        recyclerViewHewan.setLayoutManager(gridLayoutManager);

        Call<ListHewanResponse> listHewan = apiRequest.getListHewanUser(userId);

        listHewan.enqueue(new Callback<ListHewanResponse>() {
            @Override
            public void onResponse(Call<ListHewanResponse> call, Response<ListHewanResponse> response) {
                if(!response.isSuccessful())
                {
                    Log.d("Recycler", "HewanModel : " + response.message());
                    no_pet.setVisibility(View.VISIBLE);
                }
                else
                {
                    hewanModel = response.body().getPet();

                    adapterListHewan = new AdapterListHewan(ActivityListJualHewan.this, R.layout.recyclerlist_hewan,
                            hewanModel);
                    recyclerViewHewan.setAdapter(adapterListHewan);

                    no_pet.setVisibility(View.GONE);
                }
            }

            @Override
            public void onFailure(Call<ListHewanResponse> call, Throwable t) {
                Log.d("Recycler", "HewanModel : " + t.getMessage());
            }
        });
    }
}
