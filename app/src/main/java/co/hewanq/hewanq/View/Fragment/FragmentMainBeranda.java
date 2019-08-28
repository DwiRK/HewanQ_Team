package co.hewanq.hewanq.View.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import com.synnapps.carouselview.CarouselView;
import com.synnapps.carouselview.ImageListener;

import java.util.List;

import co.hewanq.hewanq.Model.DokterModel;
import co.hewanq.hewanq.Model.HewanModel;
import co.hewanq.hewanq.Model.Response.ListArtikelResponse;
import co.hewanq.hewanq.Model.Response.ListDokterResponse;
import co.hewanq.hewanq.Model.Response.ListHewanResponse;
import co.hewanq.hewanq.Model.Response.ListProdukResponse;
import co.hewanq.hewanq.Model.UserModel;
import co.hewanq.hewanq.Presenter.ApiRequest;
import co.hewanq.hewanq.Presenter.Server;
import co.hewanq.hewanq.R;
import co.hewanq.hewanq.Model.ProdukModel;
import co.hewanq.hewanq.View.Activity.ActivityListArtikel;
import co.hewanq.hewanq.View.Activity.ActivityListDokter;
import co.hewanq.hewanq.View.Activity.ActivityListHewan;
import co.hewanq.hewanq.View.Activity.ActivityListJasa;
import co.hewanq.hewanq.View.Activity.ActivityListJualBeli;
import co.hewanq.hewanq.View.Activity.ActivityListProduk;
import co.hewanq.hewanq.View.Adapter.AdapterListArtikelTwoCol;
import co.hewanq.hewanq.View.Adapter.AdapterListDokter;
import co.hewanq.hewanq.View.Adapter.AdapterListHewan;
import co.hewanq.hewanq.View.Adapter.AdapterListProduk;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FragmentMainBeranda extends Fragment
{
    View view;

    Toolbar toolbar;

    ApiRequest apiRequest;

    // ------------------------------ Variable for Carousel Goes Here ------------------------------
    private CarouselView carouselView;
    private int[] sampleImages = {R.drawable.homeframe1, R.drawable.homeframe2, R.drawable.homeframe3,
                            R.drawable.homeframe4};
    // ---------------------------------------------------------------------------------------------

    private CardView konsultasiCard, jualBeliCard, artikelCard ,jasaCard;
    private TextView listProdukBeranda, listHewanBeranda, listDokterBeranda, listArtikelBeranda;

    // ------------------------------ Variable for Recycler Goes Here ------------------------------
    RecyclerView recyclerViewHewan, recyclerViewProduk, recyclerViewDokter, recyclerViewArtikel;

    AdapterListProduk adapterListProduk;
    AdapterListHewan adapterListHewan;
    AdapterListDokter adapterListDokter;
    AdapterListArtikelTwoCol adapterListArtikelTwoCol;

    List<ProdukModel> produkModel;
    List<HewanModel> hewanModel;
    List<DokterModel> dokterModel;
    List<UserModel> artikelModel;
    // ---------------------------------------------------------------------------------------------

    public FragmentMainBeranda() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_main_beranda, container, false);

        apiRequest = Server.getClient().create(ApiRequest.class);

        toolbar = getActivity().findViewById(R.id.toolbar_top);
        toolbar.setVisibility(View.GONE);

        clickableCard();
        clickableText();

        setCarouselView();
        setTrendProduk();
        setTrendHewan();
        setTrendDokter();
        setTrendArtikel();

        return view;
    }

    public void clickableCard()
    {
        konsultasiCard = view.findViewById(R.id.kosultasi_card);
        jualBeliCard = view.findViewById(R.id.jualbeli_card);
        artikelCard = view.findViewById(R.id.artikel_card);
        jasaCard = view.findViewById(R.id.jasa_card);

        konsultasiCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent loadListKonsul = new Intent(getActivity(), ActivityListDokter.class);
                startActivity(loadListKonsul);
            }
        });

        jualBeliCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent loadListJualBeli = new Intent(getActivity(), ActivityListJualBeli.class);
                startActivity(loadListJualBeli);
            }
        });

        artikelCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent loadListArtikel = new Intent(getActivity(), ActivityListArtikel.class);
                startActivity(loadListArtikel);
            }
        });

        jasaCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent loadListJasa = new Intent(getActivity(), ActivityListJasa.class);
                startActivity(loadListJasa);
            }
        });
    }

    public void clickableText()
    {
        listProdukBeranda = view.findViewById(R.id.txt_lihat_produk_beranda);
        listHewanBeranda = view.findViewById(R.id.txt_lihat_hewan_beranda);
        listDokterBeranda = view.findViewById(R.id.txt_lihat_dokter_beranda);
        listArtikelBeranda = view.findViewById(R.id.txt_lihat_artikel_beranda);

        listProdukBeranda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent loadListProduk = new Intent(getActivity(), ActivityListProduk.class);
                startActivity(loadListProduk);
            }
        });

        listHewanBeranda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent loadListHewan = new Intent(getActivity(), ActivityListHewan.class);
                startActivity(loadListHewan);
            }
        });

        listDokterBeranda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent loadListKonsul = new Intent(getActivity(), ActivityListDokter.class);
                startActivity(loadListKonsul);
            }
        });

        listArtikelBeranda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent loadListArtikel = new Intent(getActivity(), ActivityListArtikel.class);
                startActivity(loadListArtikel);
            }
        });
    }

    // --------------------------------- Method for CarouselView ----------------------------------
    public void setCarouselView()
    {
        carouselView = view.findViewById(R.id.home_carousel);
        carouselView.setPageCount(sampleImages.length);

        ImageListener imageListener = new ImageListener() {
            @Override
            public void setImageForPosition(int position, ImageView imageView) {
                imageView.setImageResource(sampleImages[position]);
            }
        };

        carouselView.setImageListener(imageListener);
    }
    // ---------------------------------------------------------------------------------------------

    // --------------------------------- Method for RecyclerView -----------------------------------
    public void setTrendProduk()
    {
        recyclerViewProduk = view.findViewById(R.id.trend_produk);
        recyclerViewProduk.setHasFixedSize(true);
        recyclerViewProduk.setLayoutManager(new LinearLayoutManager(getActivity(),
                LinearLayoutManager.HORIZONTAL, false));

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

                    adapterListProduk = new AdapterListProduk(getActivity(), R.layout.recyclerlist_produk,
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

    public void setTrendHewan()
    {
        recyclerViewHewan = view.findViewById(R.id.trend_hewan);
        recyclerViewHewan.setHasFixedSize(true);
        recyclerViewHewan.setLayoutManager(new LinearLayoutManager(getActivity(),
                LinearLayoutManager.HORIZONTAL, false));

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

                    adapterListHewan = new AdapterListHewan(getActivity(), R.layout.recyclerlist_hewan,
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

    public void setTrendDokter()
    {
        recyclerViewDokter = view.findViewById(R.id.trend_dokter);
        recyclerViewDokter.setHasFixedSize(true);
        recyclerViewDokter.setLayoutManager(new LinearLayoutManager(getActivity(),
                LinearLayoutManager.HORIZONTAL, false));

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

                    adapterListDokter = new AdapterListDokter(getActivity(), R.layout.recyclerlist_dokter,
                            dokterModel);

                    recyclerViewDokter.setAdapter(adapterListDokter);
                }
            }

            @Override
            public void onFailure(Call<ListDokterResponse> call, Throwable t) {
                Log.d("Recycler", "DokterModel : " + t.getMessage());
            }
        });
    }

    public void setTrendArtikel()
    {
        recyclerViewArtikel = view.findViewById(R.id.trend_artikel);
        recyclerViewArtikel.setHasFixedSize(true);
        recyclerViewArtikel.setLayoutManager(new LinearLayoutManager(getActivity(),
                LinearLayoutManager.HORIZONTAL, false));

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

                    adapterListArtikelTwoCol = new AdapterListArtikelTwoCol(getActivity(),
                            R.layout.recyclerlist_artikel_two_coloumn, artikelModel);

                    recyclerViewArtikel.setAdapter(adapterListArtikelTwoCol);
                }
            }

            @Override
            public void onFailure(Call<ListArtikelResponse> call, Throwable t) {
                Log.d("Recycler", "ArtikelModel : " + t.getMessage());
            }
        });
    }
    // ---------------------------------------------------------------------------------------------
}
