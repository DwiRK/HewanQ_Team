package co.hewanq.hewanq.View.Fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.support.v7.widget.Toolbar;

import co.hewanq.hewanq.R;
import co.hewanq.hewanq.View.Adapter.AdapterObrolanLayoutView;

public class FragmentMainObrolan extends Fragment
{
    private View view;
    private Toolbar toolbar;
    private TextView textView;

    private TabLayout tabLayout;
    private ViewPager viewPager;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_main_obrolan, container, false);

        toolbar = getActivity().findViewById(R.id.toolbar_top);
        toolbar.setVisibility(View.VISIBLE);

        textView = getActivity().findViewById(R.id.toolbar_title);
        textView.setText("Obrolan");

        // Inisialisasi Tabbed Layout
        tabLayout = view.findViewById(R.id.obrolan_tablayout);
        viewPager = view.findViewById(R.id.obrolan_view_pager);

        // Tabbed Layout Proses
        AdapterObrolanLayoutView adapterObrolanLayoutView = new AdapterObrolanLayoutView(getChildFragmentManager());

        adapterObrolanLayoutView.addFragment(new FragmentObrolanChat(), "Chat");
        adapterObrolanLayoutView.addFragment(new FragmentObrolanKonsultasi(), "Konsultasi");

        viewPager.setAdapter(adapterObrolanLayoutView);
        tabLayout.setupWithViewPager(viewPager);

        return view;
    }
}
