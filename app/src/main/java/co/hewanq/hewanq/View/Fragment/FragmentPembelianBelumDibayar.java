package co.hewanq.hewanq.View.Fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import co.hewanq.hewanq.R;

public class FragmentPembelianBelumDibayar extends Fragment {
    View view;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_list_transaksi, container, false);
        LinearLayout no_order = view.findViewById(R.id.no_order);
        RecyclerView recyclerView = view.findViewById(R.id.recycler_view);

        int jumlah_data;
        jumlah_data = 0;

        if(jumlah_data != 0){
            no_order.setVisibility(View.GONE);
            recyclerView.setVisibility(View.VISIBLE);
        }

        return view;
    }
}
