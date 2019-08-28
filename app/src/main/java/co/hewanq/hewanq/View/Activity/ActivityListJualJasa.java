package co.hewanq.hewanq.View.Activity;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import java.util.HashMap;
import java.util.List;

import co.hewanq.hewanq.Model.JasaModel;
import co.hewanq.hewanq.Model.Response.ListJasaResponse;
import co.hewanq.hewanq.Presenter.ApiRequest;
import co.hewanq.hewanq.Presenter.Server;
import co.hewanq.hewanq.Presenter.SessionManager;
import co.hewanq.hewanq.R;
import co.hewanq.hewanq.View.Adapter.AdapterListJasa;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ActivityListJualJasa extends AppCompatActivity {
    ImageView add_btn, no_jasa;
    Intent intent;

    private RecyclerView recyclerViewJasa;
    private AdapterListJasa adapterListJasa;
    private List<JasaModel> jasaModel;

    private ApiRequest apiRequest;

    private HashMap<String, String> userData;

    private SessionManager sessionManager;

    private int userId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_jual_jasa);

        apiRequest = Server.getClient().create(ApiRequest.class);

        sessionManager = new SessionManager(ActivityListJualJasa.this);
        userData = sessionManager.getSavedToken();

        userId = Integer.valueOf(userData.get(sessionManager.getUserId()));


        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Jasa Saya");
        actionBar.setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.blue_theme)));

        add_btn = findViewById(R.id.add_btn);
        no_jasa = findViewById(R.id.no_jasa);

        add_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(getApplicationContext(), ActivityTambahJasa.class);
                intent.putExtra("id", userData.get(sessionManager.getUserId()));
                startActivity(intent);
            }
        });

        setListJasa();
    }

    public void setListJasa()
    {
        recyclerViewJasa = findViewById(R.id.list_recycler_jasa_user);
        recyclerViewJasa.setHasFixedSize(true);

        recyclerViewJasa.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL,
                false));

        Call<ListJasaResponse> listJasa = apiRequest.getListJasaUser(userId);

        listJasa.enqueue(new Callback<ListJasaResponse>() {
            @Override
            public void onResponse(Call<ListJasaResponse> call, Response<ListJasaResponse> response) {
                if(!response.isSuccessful())
                {
                    Log.d("Recycler", "JasaModel : " + response.message());
                    no_jasa.setVisibility(View.VISIBLE);
                }
                else
                {
                    jasaModel = response.body().getjasa();

                    adapterListJasa = new AdapterListJasa(ActivityListJualJasa.this, R.layout.recyclerlist_jasa,
                            jasaModel);
                    recyclerViewJasa.setAdapter(adapterListJasa);
                    no_jasa.setVisibility(View.GONE);
                }
            }

            @Override
            public void onFailure(Call<ListJasaResponse> call, Throwable t) {
                Log.d("Recycler", "JasaModel : " + t.getMessage());
            }
        });
    }
}
