package co.hewanq.hewanq.View.Activity;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import co.hewanq.hewanq.R;
import co.hewanq.hewanq.View.Fragment.FragmentMainPenjualan;
import co.hewanq.hewanq.View.Fragment.FragmentPenjualanBatal;
import co.hewanq.hewanq.View.Fragment.FragmentPenjualanDikirim;
import co.hewanq.hewanq.View.Fragment.FragmentPenjualanPerluDikirim;
import co.hewanq.hewanq.View.Fragment.FragmentPenjualanSukses;

public class ActivityListPenjualan extends AppCompatActivity {
    private TabLayout tabLayout;
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_transaksi);

        // membuat action bar
        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Penjualan Anda");

        // mengisi attribut
        tabLayout = findViewById(R.id.tab_layout_transaksi);
        viewPager = findViewById(R.id.view_pager_transaksi);
        FragmentMainPenjualan adapter = new FragmentMainPenjualan(getSupportFragmentManager());

        // menambah fragment
        adapter.addFragment(new FragmentPenjualanPerluDikirim(), "Perlu Dikirim");
        adapter.addFragment(new FragmentPenjualanDikirim(), "Dikirim");
        adapter.addFragment(new FragmentPenjualanSukses(), "Sukses");
        adapter.addFragment(new FragmentPenjualanBatal(), "Batal");

        // adapter setup
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
    }
}
