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
import co.hewanq.hewanq.Model.JasaModel;
import co.hewanq.hewanq.View.Activity.ActivityDetailJasa;

public class AdapterListJasa extends RecyclerView.Adapter<AdapterListJasa.ListJasa> {

    private Context mContext;
    int rowLayout;
    private List<JasaModel> listJasaModel;

    public AdapterListJasa(Context mContext, int rowLayout, List<JasaModel> listJasaModel) {
        this.mContext = mContext;
        this.rowLayout = rowLayout;
        this.listJasaModel = listJasaModel;
    }

    @NonNull
    @Override
    public AdapterListJasa.ListJasa onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(rowLayout, viewGroup, false);

        return new ListJasa(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ListJasa listJasa, final int position) {
        JasaModel jasaModel = this.listJasaModel.get(position);

        listJasa.namaJasa.setText(jasaModel.getName());
        listJasa.lokasiJasa.setText(jasaModel.getKota());
        listJasa.hargaJasa.setText("Rp. " + Integer.toString(jasaModel.getHarga()));
        listJasa.spesialisJasa.setText(jasaModel.getHewanDilayani());

        if(listJasaModel.get(position).getPhoto() != null)
        {
            Glide.with(mContext)
                    .load(Server.url + "images/" + listJasaModel.get(position).getPhoto())
                    .into(listJasa.fotoJasa);
        }

        listJasa.btnDetailJasa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String jasaId = String.valueOf(listJasaModel.get(position).getId());

                Intent intent = new Intent(mContext, ActivityDetailJasa.class);
                intent.putExtra("jasaId", jasaId);

                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return listJasaModel.size();
    }

    class ListJasa extends RecyclerView.ViewHolder
    {
        ImageView fotoJasa;
        TextView namaJasa, lokasiJasa, hargaJasa, spesialisJasa;

        Button btnDetailJasa;

        public ListJasa(@NonNull View itemView) {
            super(itemView);

            fotoJasa = itemView.findViewById(R.id.recycler_foto_jasa);
            namaJasa = itemView.findViewById(R.id.recycler_nama_jasa);
            lokasiJasa = itemView.findViewById(R.id.recycler_lokasi_jasa);
            hargaJasa = itemView.findViewById(R.id.recycler_harga_jasa);
            spesialisJasa = itemView.findViewById(R.id.recycler_spesialis_jasa);

            btnDetailJasa = itemView.findViewById(R.id.recycler_btn_detail_jasa);
        }
    }
}
