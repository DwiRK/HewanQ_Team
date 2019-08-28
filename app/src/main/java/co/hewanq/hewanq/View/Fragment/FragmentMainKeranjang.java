package co.hewanq.hewanq.View.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import co.hewanq.hewanq.R;
import co.hewanq.hewanq.View.Activity.ActivityOpsiPembelian;

public class FragmentMainKeranjang extends Fragment
{
    View view;
    private Toolbar toolbar;
    private TextView textView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_main_keranjang, container, false);

        toolbar = getActivity().findViewById(R.id.toolbar_top);
        toolbar.setVisibility(View.VISIBLE);

        textView = getActivity().findViewById(R.id.toolbar_title);
        textView.setText("Keranjang");

        Button lanjutkan_btn = view.findViewById(R.id.lanjutkan_btn);
        lanjutkan_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ActivityOpsiPembelian.class);
                startActivity(intent);
            }
        });
        return view;
    }
}
