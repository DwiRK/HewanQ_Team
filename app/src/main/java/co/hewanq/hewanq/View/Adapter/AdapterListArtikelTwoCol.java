package co.hewanq.hewanq.View.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import co.hewanq.hewanq.Model.ArtikelModel;
import co.hewanq.hewanq.Model.UserModel;
import co.hewanq.hewanq.Presenter.Server;
import co.hewanq.hewanq.R;
import co.hewanq.hewanq.View.Activity.ActivityDetailArtikel;

public class AdapterListArtikelTwoCol extends RecyclerView.Adapter<AdapterListArtikelTwoCol.TrendArtikelView> {

    private Context mContext;
    int rowLayout;
    private List<UserModel> trendArtikelModel;

    public AdapterListArtikelTwoCol(Context mContext, int rowLayout, List<UserModel> trendArtikelModel) {
        this.mContext = mContext;
        this.rowLayout = rowLayout;
        this.trendArtikelModel = trendArtikelModel;
    }

    @NonNull
    @Override
    public AdapterListArtikelTwoCol.TrendArtikelView onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(rowLayout, viewGroup, false);

        return new TrendArtikelView(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TrendArtikelView trendArtikelView, final int position)
    {
        UserModel artikelModel = trendArtikelModel.get(position);

        trendArtikelView.judulArtikel.setText(artikelModel.getKota());
        trendArtikelView.namaPenulis.setText(artikelModel.getName());

        if(trendArtikelModel.get(position).getPhoto() != null)
        {
            Glide.with(mContext)
                    .load(Server.url + "images/" + trendArtikelModel.get(position).getPhoto())
                    .into(trendArtikelView.fotoArtikel);
        }

        trendArtikelView.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String artikelId = String.valueOf(trendArtikelModel.get(position).getId());
                Intent intent = new Intent(mContext, ActivityDetailArtikel.class);
                intent.putExtra("artikelId", artikelId);

                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return trendArtikelModel.size();
    }

    class TrendArtikelView extends RecyclerView.ViewHolder
    {
        ImageView fotoArtikel;
        TextView judulArtikel, namaPenulis;

        public TrendArtikelView(@NonNull View itemView) {
            super(itemView);

            fotoArtikel = itemView.findViewById(R.id.recycler_foto_artikel_two_col);
            judulArtikel = itemView.findViewById(R.id.recycler_judul_artikel_two_col);
            namaPenulis = itemView.findViewById(R.id.recycler_penulis_artikel_two_col);
        }
    }
}
