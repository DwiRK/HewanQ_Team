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

import co.hewanq.hewanq.Model.HewanModel;
import co.hewanq.hewanq.Presenter.Server;
import co.hewanq.hewanq.R;
import co.hewanq.hewanq.View.Activity.ActivityDetailHewan;

public class AdapterListHewan extends RecyclerView.Adapter<AdapterListHewan.TrendHewanView> {

    Context mContext;
    int rowLayout;
    private List<HewanModel> trendHewanModel;

    public AdapterListHewan(Context mContext, int rowLayout, List<HewanModel> trendHewanModel) {
        this.mContext = mContext;
        this.rowLayout = rowLayout;
        this.trendHewanModel = trendHewanModel;
    }

    @NonNull
    @Override
    public AdapterListHewan.TrendHewanView onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(rowLayout, viewGroup, false);

        return new TrendHewanView(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final TrendHewanView trendHewanView, final int position) {
        HewanModel hewanModel = trendHewanModel.get(position);

        trendHewanView.textViewNama.setText(hewanModel.getName());
        trendHewanView.textViewLokasi.setText(hewanModel.getKota());
        trendHewanView.textViewHarga.setText("Rp. " + Integer.toString(hewanModel.getPrice()));

        if(trendHewanModel.get(position).getPhoto() != null)
        {
            Glide.with(mContext)
                    .load(Server.url + "images/" + trendHewanModel.get(position).getPhoto())
                    .into(trendHewanView.fotoHewan);
        }

        trendHewanView.btnDetailHewan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String hewanId = String.valueOf(trendHewanModel.get(position).getId());
                Intent intent = new Intent(mContext, ActivityDetailHewan.class);
                intent.putExtra("hewanId", hewanId);

                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return trendHewanModel.size();
    }

    class TrendHewanView extends RecyclerView.ViewHolder
    {
        ImageView fotoHewan;
        TextView textViewNama, textViewLokasi, textViewHarga;
        Button btnDetailHewan;

        public TrendHewanView(@NonNull View itemView) {
            super(itemView);

            fotoHewan = itemView.findViewById(R.id.recycler_foto_hewan);
            textViewNama = itemView.findViewById(R.id.recycler_nama_hewan);
            textViewLokasi = itemView.findViewById(R.id.recycler_lokasi_hewan);
            textViewHarga = itemView.findViewById(R.id.recycler_harga_hewan);
            btnDetailHewan = itemView.findViewById(R.id.recycler_btn_hewan);

        }
    }
}
