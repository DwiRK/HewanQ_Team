package co.hewanq.hewanq.View.Fragment;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.HashMap;

import co.hewanq.hewanq.Model.Response.UserResponse;
import co.hewanq.hewanq.Presenter.ApiRequest;
import co.hewanq.hewanq.Presenter.Server;
import co.hewanq.hewanq.Presenter.SessionManager;
import co.hewanq.hewanq.R;
import co.hewanq.hewanq.View.Activity.ActivityEditProfile;
import co.hewanq.hewanq.View.Activity.ActivityListJualHewan;
import co.hewanq.hewanq.View.Activity.ActivityListJualJasa;
import co.hewanq.hewanq.View.Activity.ActivityListJualProduk;
import co.hewanq.hewanq.View.Activity.ActivityListPembelian;
import co.hewanq.hewanq.View.Activity.ActivityListPenjualan;
import co.hewanq.hewanq.View.Activity.ActivityLogin;
import co.hewanq.hewanq.View.Activity.ActivityMain;
import co.hewanq.hewanq.View.Activity.ActivityRegister;
import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FragmentMainAkun extends Fragment
{
    CircleImageView fotoUserLogin;
//    ImageView fotoUserLogin;
    TextView namaUser, emailUser;

    ProgressDialog progressDialog;

    private SessionManager sessionManager;

    private HashMap<String, String> userData;

    private ApiRequest apiRequest;

    private View view;
    private Toolbar toolbar;
    private RelativeLayout btn_edit_profile, btn_pembelian, btn_penjualan, btn_hewan, btn_produk, btn_jasa, btn_keluar;

    public FragmentMainAkun() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_main_akun, container, false);

        progressDialog = new ProgressDialog(getActivity());

        apiRequest = Server.getClient().create(ApiRequest.class);

        // inisialisasi session manager
        sessionManager = new SessionManager(getActivity());
        userData = sessionManager.getSavedToken();

        // menghilangkan toolbar
        toolbar = getActivity().findViewById(R.id.toolbar_top);
        toolbar.setVisibility(View.GONE);

        // inisialisasi komponen layout
        LinearLayout notLogin = view.findViewById(R.id.not_login_view);
        ScrollView login = view.findViewById(R.id.login_view);

        // inisialisasi Button
        Button login_btn, register_btn;
        login_btn = view.findViewById(R.id.login_btn);
        register_btn = view.findViewById(R.id.register_btn);

        btn_edit_profile = view.findViewById(R.id.btn_edit_profile);
        btn_pembelian = view.findViewById(R.id.btn_pembelian);
        btn_penjualan = view.findViewById(R.id.btn_penjualan);
        btn_hewan = view.findViewById(R.id.btn_hewan);
        btn_produk = view.findViewById(R.id.btn_produk);
        btn_jasa = view.findViewById(R.id.btn_jasa);
        btn_keluar = view.findViewById(R.id.btn_keluar);

        // inisialisasi element user
        fotoUserLogin = view.findViewById(R.id.foto_user_login);
        namaUser = view.findViewById(R.id.nama_user);
        emailUser = view.findViewById(R.id.email_user);

        progressDialog.setMessage("Logout ...");

        // check apakah user sudah login atau  belum
        if(sessionManager.checkLogin())
        {
            notLogin.setVisibility(View.GONE);
            login.setVisibility(View.VISIBLE);

            setDataUser();
        }

        // Proses Button
        login_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ActivityLogin.class);
                startActivity(intent);
            }
        });

        register_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ActivityRegister.class);
                startActivity(intent);
            }
        });

        btn_edit_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ActivityEditProfile.class);
                startActivity(intent);
            }
        });

        btn_pembelian.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ActivityListPembelian.class);
                startActivity(intent);
            }
        });

        btn_penjualan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ActivityListPenjualan.class);
                startActivity(intent);
            }
        });

        btn_hewan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ActivityListJualHewan.class);
                startActivity(intent);
            }
        });

        btn_produk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ActivityListJualProduk.class);
                startActivity(intent);
            }
        });

        btn_jasa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ActivityListJualJasa.class);
                startActivity(intent);
            }
        });

        btn_keluar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressDialog.show();

                sessionManager.clearSession();

                Intent loadMainActivity = new Intent(getActivity(), ActivityMain.class);
                startActivity(loadMainActivity);
                getActivity().finish();
            }
        });

        return view;
    }

    public void setDataUser()
    {
        if(userData.get(sessionManager.getPhoto()) != null)
        {
            Glide.with(this)
                    .load(Server.url + "images/" + userData.get(sessionManager.getPhoto()))
                    .into(fotoUserLogin);
        }

        namaUser.setText(userData.get(sessionManager.getName()));
        emailUser.setText(userData.get(sessionManager.getEmail()));
    }
}
