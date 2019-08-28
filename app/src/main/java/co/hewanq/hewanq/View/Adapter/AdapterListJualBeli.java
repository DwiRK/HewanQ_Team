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
import co.hewanq.hewanq.Model.JualBeliModel;
import co.hewanq.hewanq.View.Activity.ActivityDetailHewan;
import co.hewanq.hewanq.View.Activity.ActivityDetailProduk;

public class AdapterListJualBeli extends RecyclerView.Adapter<AdapterListJualBeli.ListJualBeli> {

    private Context mContext;
    int rowLayout;
    private List<JualBeliModel> listJualBeliModel;

    public AdapterListJualBeli(Context mContext, int rowLayout, List<JualBeliModel> listJualBeliModel) {
        this.mContext = mContext;
        this.rowLayout = rowLayout;
        this.listJualBeliModel = listJualBeliModel;
    }

    @NonNull
    @Override
    public AdapterListJualBeli.ListJualBeli onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(rowLayout, viewGroup, false);

        return new ListJualBeli(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ListJualBeli listJualBeli, final int position) {
        final JualBeliModel jualBeliModel = this.listJualBeliModel.get(position);

        listJualBeli.namaItem.setText(jualBeliModel.getName());
        listJualBeli.lokasiItem.setText(jualBeliModel.getKota());
        listJualBeli.hargaItem.setText("Rp. " + Integer.toString(jualBeliModel.getPrice()));

        if(listJualBeliModel.get(position).getPhoto() != null)
        {
            Glide.with(mContext)
                    .load(Server.url + "images/" + listJualBeliModel.get(position).getPhoto())
                    .into(listJualBeli.fotoItem);
        }

        listJualBeli.btnDetailBarang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String barangId = String.valueOf(listJualBeliModel.get(position).getId());

                Intent intentHewan = new Intent(mContext, ActivityDetailHewan.class);
                Intent intentBarang = new Intent(mContext, ActivityDetailProduk.class);

                intentHewan.putExtra("hewanId", barangId);
                intentBarang.putExtra("produkId", barangId);

                if(jualBeliModel.getJenis().equals("pet"))
                {
                    mContext.startActivity(intentHewan);
                }
                else
                {
                    mContext.startActivity(intentBarang);
                }

            }
        });
    }

    @Override
    public int getItemCount() {
        return listJualBeliModel.size();
    }

    class ListJualBeli extends RecyclerView.ViewHolder
    {
        ImageView fotoItem;
        TextView namaItem, lokasiItem, hargaItem;
        Button btnDetailBarang;

        public ListJualBeli(@NonNull View itemView) {
            super(itemView);

            fotoItem = itemView.findViewById(R.id.recycler_foto_jual_beli);
            namaItem = itemView.findViewById(R.id.recycler_nama_jual_beli);
            lokasiItem = itemView.findViewById(R.id.recycler_lokasi_jual_beli);
            hargaItem = itemView.findViewById(R.id.recycler_harga_jual_beli);

            btnDetailBarang = itemView.findViewById(R.id.recycler_btn_jual_beli);
        }
    }
}
