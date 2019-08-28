package co.hewanq.hewanq.View.Activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import co.hewanq.hewanq.Model.UserModel;
import co.hewanq.hewanq.Presenter.ApiRequest;
import co.hewanq.hewanq.Presenter.Server;
import co.hewanq.hewanq.R;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ActivityRegister extends AppCompatActivity {

    TextView goToLoginText;

    EditText namaUser, emailUser, passwordUser, passwordConfirm;

    Button btnRegister;

    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        progressDialog = new ProgressDialog(ActivityRegister.this);

        btnRegister = findViewById(R.id.register_btn);

        namaUser = findViewById(R.id.nama_register);
        emailUser = findViewById(R.id.email_register);
        passwordUser = findViewById(R.id.pass_register);
        passwordConfirm = findViewById(R.id.pass_register_confirm);

        goToLoginText = findViewById(R.id.register_to_login);

        progressDialog.setMessage("Tunggu Sebentar ...");

        goToLoginText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ActivityRegister.this, ActivityLogin.class);
                finish();
                startActivity(intent);
            }
        });

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressDialog.show();

                onRegister(namaUser, emailUser, passwordUser, passwordConfirm);

                namaUser.setText("");
                emailUser.setText("");
                passwordUser.setText("");
                passwordConfirm.setText("");
            }
        });
    }

    public void onRegister(EditText namaUser, EditText emailUser, EditText passwordUser,
                           EditText passwordConfirm)
    {
        ApiRequest apiRequest = Server.getClient().create(ApiRequest.class);

        String nama = namaUser.getText().toString();
        String email = emailUser.getText().toString();
        String password = passwordUser.getText().toString();
        String passwordConf = passwordConfirm.getText().toString();

        if(!password.equals(passwordConf))
        {
            progressDialog.dismiss();

            Toast.makeText(this, "Password tidak sama", Toast.LENGTH_SHORT).show();
        }
        else
        {
            Call<UserModel> register = apiRequest.register(nama, email, password);

            register.enqueue(new Callback<UserModel>() {
                @Override
                public void onResponse(Call<UserModel> call, Response<UserModel> response) {
                    progressDialog.dismiss();

//                    Log.d("REGISTER","Response body : " + response.body().toString());

                    if(!response.isSuccessful())
                    {
                        Log.d("GAGAL", "onResponse: " + response.message());

                        Toast.makeText(ActivityRegister.this, "Gagal mendaftar",
                                Toast.LENGTH_SHORT).show();
                    }
                    else
                    {
                        Toast.makeText(ActivityRegister.this, "Berhasil mendaftar",
                                Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<UserModel> call, Throwable t) {
                    Log.d("REGISTER", "Failure : " + t);
                }
            });
        }
    }
}
