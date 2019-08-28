package co.hewanq.hewanq.View.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import co.hewanq.hewanq.Presenter.Server;
import co.hewanq.hewanq.R;
import co.hewanq.hewanq.Model.ProdukModel;
import co.hewanq.hewanq.View.Activity.ActivityDetailProduk;

public class AdapterListProduk extends RecyclerView.Adapter<AdapterListProduk.TrendProdukView> {

    private Context mContext;
    int rowLayout;
    private List<ProdukModel> trendProdukModel;

    public AdapterListProduk(Context mContext, int rowLayout, List<ProdukModel> trendProdukModel) {
        this.mContext = mContext;
        this.rowLayout = rowLayout;
        this.trendProdukModel = trendProdukModel;
    }

    @NonNull
    @Override
    public AdapterListProduk.TrendProdukView onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(rowLayout, viewGroup, false);

        return new TrendProdukView(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TrendProdukView trendProdukView, final int position) {
        ProdukModel produkModel = trendProdukModel.get(position);

        trendProdukView.textViewNama.setText(produkModel.getName());
        trendProdukView.textViewLokasi.setText(produkModel.getKota());
        trendProdukView.textViewHarga.setText("Rp. " + Integer.toString(produkModel.getPrice()));

        if(trendProdukModel.get(position).getPhoto() != null)
        {
            Glide.with(mContext)
                    .load(Server.url + "images/" + trendProdukModel.get(position).getPhoto())
                    .into(trendProdukView.fotoProduk);
        }

        trendProdukView.btnDetailProduk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String produkId = String.valueOf(trendProdukModel.get(position).getId());
                Intent intent = new Intent(mContext, ActivityDetailProduk.class);
                intent.putExtra("produkId", produkId);

                mContext.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return trendProdukModel.size();
    }

    class TrendProdukView extends RecyclerView.ViewHolder
    {
        ImageView fotoProduk;
        TextView textViewNama, textViewLokasi, textViewHarga;

        Button btnDetailProduk;

        public TrendProdukView(@NonNull View itemView) {
            super(itemView);

            fotoProduk = itemView.findViewById(R.id.recycler_foto_produk);
            textViewNama = itemView.findViewById(R.id.recycler_nama_produk);
            textViewLokasi = itemView.findViewById(R.id.recycler_lokasi_produk);
            textViewHarga = itemView.findViewById(R.id.recycler_harga_produk);

            btnDetailProduk = itemView.findViewById(R.id.recycler_btn_produk);
        }
    }
}
