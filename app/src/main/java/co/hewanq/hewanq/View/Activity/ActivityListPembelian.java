package co.hewanq.hewanq.View.Activity;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import co.hewanq.hewanq.R;
import co.hewanq.hewanq.View.Fragment.FragmentMainPembelian;
import co.hewanq.hewanq.View.Fragment.FragmentPembelianBatal;
import co.hewanq.hewanq.View.Fragment.FragmentPembelianBelumDibayar;
import co.hewanq.hewanq.View.Fragment.FragmentPembelianDikemas;
import co.hewanq.hewanq.View.Fragment.FragmentPembelianDikirim;
import co.hewanq.hewanq.View.Fragment.FragmentPembelianSukses;
import co.hewanq.hewanq.View.Fragment.FragmentPenjualanBatal;
import co.hewanq.hewanq.View.Fragment.FragmentPenjualanDikirim;
import co.hewanq.hewanq.View.Fragment.FragmentPenjualanPerluDikirim;
import co.hewanq.hewanq.View.Fragment.FragmentPenjualanSukses;

public class ActivityListPembelian extends AppCompatActivity {
    private TabLayout tabLayout;
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_transaksi);

        // membuat action bar
        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Pembelian Anda");

        // mengisi attribut
        tabLayout = findViewById(R.id.tab_layout_transaksi);
        viewPager = findViewById(R.id.view_pager_transaksi);
        FragmentMainPembelian adapter = new FragmentMainPembelian(getSupportFragmentManager());

        // menambah fragment
        adapter.addFragment(new FragmentPembelianBelumDibayar(), "Belum Dibayar");
        adapter.addFragment(new FragmentPembelianDikemas(), "Dikemas");
        adapter.addFragment(new FragmentPembelianDikirim(), "Dikirim");
        adapter.addFragment(new FragmentPembelianSukses(), "Sukses");
        adapter.addFragment(new FragmentPembelianBatal(), "Batal");

        // adapter setup
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
    }
}
