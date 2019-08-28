package co.hewanq.hewanq.View.Activity;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.FrameLayout;

import co.hewanq.hewanq.Presenter.SessionManager;
import co.hewanq.hewanq.View.Fragment.FragmentMainAkun;
import co.hewanq.hewanq.View.Fragment.FragmentMainBeranda;
import co.hewanq.hewanq.View.Fragment.FragmentMainKeranjang;
import co.hewanq.hewanq.View.Fragment.FragmentMainObrolan;
import co.hewanq.hewanq.R;

public class ActivityMain extends AppCompatActivity {

    private SessionManager sessionManager;

    FrameLayout mainFrameLayout;

    private FragmentMainBeranda fragmentMainBeranda;
    private FragmentMainObrolan fragmentMainObrolan;
    private FragmentMainKeranjang fragmentMainKeranjang;
    private FragmentMainAkun fragmentMainAkun;

    // --------------------------- Variable for Bottom Navigation Goes Here ------------------------
    private BottomNavigationView bottomNavigationView;
    // ---------------------------------------------------------------------------------------------

    // ------------------------------------- Main Goes Here ----------------------------------------
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sessionManager = new SessionManager(ActivityMain.this);

        bottomNavigationView = findViewById(R.id.bottom_navbar);
        mainFrameLayout = findViewById(R.id.main_frame);

        fragmentMainBeranda = new FragmentMainBeranda();
        fragmentMainObrolan = new FragmentMainObrolan();
        fragmentMainKeranjang = new FragmentMainKeranjang();
        fragmentMainAkun = new FragmentMainAkun();

        setMainFragment(fragmentMainBeranda);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId())
                {
                    case R.id.beranda :
                        setMainFragment(fragmentMainBeranda);
                        return true;
                    case R.id.obrolan :
                        setMainFragment(fragmentMainObrolan);
                        return true;
                    case R.id.keranjang :
                        setMainFragment(fragmentMainKeranjang);
                        return true;
                    case R.id.akun :
                        setMainFragment(fragmentMainAkun);
                        return true;
                    default:
                        return false;
                }
            }
        });
   }

   private void setMainFragment(Fragment fragment)
   {
       FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
       fragmentTransaction.replace(R.id.main_frame, fragment);
       fragmentTransaction.commit();
   }

    @Override
    public void onBackPressed() {
        if(sessionManager.checkLogin())
        {
            finish();
            System.exit(0);
        }
        else
        {
            finish();
            System.exit(0);
        }
    }
}
