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

import co.hewanq.hewanq.Model.DokterModel;
import co.hewanq.hewanq.Presenter.Server;
import co.hewanq.hewanq.R;
import co.hewanq.hewanq.View.Activity.ActivityDetailDokter;

public class AdapterListDokter extends RecyclerView.Adapter<AdapterListDokter.TrendDokterView> {

    private Context mContext;
    int rowLayout;
    private List<DokterModel> trendDokterModel;

    public AdapterListDokter(Context mContext, int rowLayout, List<DokterModel> trendDokter) {
        this.mContext = mContext;
        this.rowLayout =rowLayout;
        this.trendDokterModel = trendDokter;
    }

    @NonNull
    @Override
    public AdapterListDokter.TrendDokterView onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(rowLayout, viewGroup, false);

        return new TrendDokterView(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TrendDokterView trendDokterView, final int position) {
        DokterModel dokterModel = trendDokterModel.get(position);

        trendDokterView.textViewNama.setText(dokterModel.getName());
        trendDokterView.textViewLokasi.setText(dokterModel.getKota());

        if(dokterModel.getHewanDilayani() != null)
        {
            trendDokterView.textViewSpesialis.setText(dokterModel.getHewanDilayani());
        }
        else
        {
            trendDokterView.textViewSpesialis.setText("belum ada data");
        }

        if(dokterModel.getFasilitas() != null)
        {
            trendDokterView.textViewKlinik.setText(dokterModel.getFasilitas());
        }
        else
        {
            trendDokterView.textViewKlinik.setText("Belum ada data");
        }

        if(trendDokterModel.get(position).getPhoto() != null)
        {
            Glide.with(mContext)
                    .load(Server.url + "images/" + trendDokterModel.get(position).getPhoto())
                    .into(trendDokterView.fotoDokter);
        }

        trendDokterView.btnDetailDokter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String dokterId = String.valueOf(trendDokterModel.get(position).getId());
                Intent intent = new Intent(mContext, ActivityDetailDokter.class);
                intent.putExtra("dokterId", dokterId);

                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return trendDokterModel.size();
    }

    class TrendDokterView extends RecyclerView.ViewHolder
    {
        ImageView fotoDokter;
        TextView textViewNama, textViewLokasi, textViewSpesialis, textViewKlinik;

        Button btnDetailDokter;

        public TrendDokterView(@NonNull View itemView) {
            super(itemView);

            fotoDokter = itemView.findViewById(R.id.recycler_foto_dokter);
            textViewNama = itemView.findViewById(R.id.recycler_nama_dokter);
            textViewLokasi = itemView.findViewById(R.id.recycler_lokasi_dokter);
            textViewSpesialis = itemView.findViewById(R.id.recycler_spesialis_dokter);
            textViewKlinik = itemView.findViewById(R.id.recycler_klinik_dokter);

            btnDetailDokter = itemView.findViewById(R.id.recycler_btn_konsul_dokter);
        }
    }
}
