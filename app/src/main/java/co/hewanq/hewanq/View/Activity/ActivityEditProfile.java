package co.hewanq.hewanq.View.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.HashMap;

import co.hewanq.hewanq.InputChecker;
import co.hewanq.hewanq.Presenter.ApiRequest;
import co.hewanq.hewanq.Presenter.Server;
import co.hewanq.hewanq.Presenter.SessionManager;
import co.hewanq.hewanq.R;

public class ActivityEditProfile extends AppCompatActivity {

    private SessionManager sessionManager;

    private HashMap<String, String> userData;

    private ApiRequest apiRequest;

    protected Button btn_selesai;
    protected EditText et_nama, et_email, et_kota, et_alamat;
    protected String data_nama, data_email, data_gender, data_kota, data_alamat;
    protected Spinner sp_gender;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        apiRequest = Server.getClient().create(ApiRequest.class);

        sessionManager = new SessionManager(ActivityEditProfile.this);
        userData = sessionManager.getSavedToken();

        // Mengisi variabel
        sp_gender = findViewById(R.id.gender);
        et_nama = findViewById(R.id.nama);
        et_email = findViewById(R.id.email);
        et_kota = findViewById(R.id.kota);
        et_alamat = findViewById(R.id.alamat);

        // Silahkan di edit menggunakan data dinamis
        loadUserData();

        // Jika tombol diklik
        btn_selesai = findViewById(R.id.btn_selesai);
        btn_selesai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Mengambil input user
                data_nama = InputChecker.checkAndGetString(et_nama);
                data_email = InputChecker.checkAndGetString(et_email);
                data_kota = InputChecker.checkAndGetString(et_kota);
                data_alamat = InputChecker.checkAndGetString(et_alamat);
                data_gender = sp_gender.getSelectedItem().toString();

                if(InputChecker.getError() == 0)
                {
                    // KIRIM DATA DISINI
                    Toast.makeText(getApplicationContext(), "Yeeaahh!!", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    public void loadUserData()
    {
        data_nama = userData.get(sessionManager.getName());
        data_email = userData.get(sessionManager.getEmail());
//        data_gender = "Laki-laki";
        data_gender = userData.get(sessionManager.getGender());
        data_kota = userData.get(sessionManager.getKota());
        data_alamat = userData.get(sessionManager.getAlamat());

        sp_gender.setSelection(InputChecker.spinnerTranslatorFromGender(data_gender));
        et_nama.setText(data_nama);
        et_email.setText(data_email);
        et_kota.setText(data_kota);
        et_alamat.setText(data_alamat);
    }

    public void updateData()
    {

    }
}
