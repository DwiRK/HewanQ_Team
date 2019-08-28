package co.hewanq.hewanq.View.Activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import co.hewanq.hewanq.Model.UserModel;
import co.hewanq.hewanq.Presenter.ApiRequest;
import co.hewanq.hewanq.Presenter.Login;
import co.hewanq.hewanq.Presenter.SessionManager;
import co.hewanq.hewanq.R;
import co.hewanq.hewanq.Presenter.Server;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ActivityLogin extends AppCompatActivity {
    ProgressDialog progressDialog;

    Button btn_register, btn_login;
    EditText txt_email, txt_password;

        private SessionManager sessionManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        progressDialog = new ProgressDialog(ActivityLogin.this);

        sessionManager = new SessionManager(ActivityLogin.this);

        btn_login = findViewById(R.id.login_btn);
        btn_register = findViewById(R.id.register_btn);
        txt_email = findViewById(R.id.email_login);
        txt_password = findViewById(R.id.pass_login);

        progressDialog.setMessage("Tunggu Sebentar ...");

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressDialog.show();

                onLogin();
            }
        });

        // Pindah ke activity register
        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ActivityLogin.this, ActivityRegister.class);
                startActivity(intent);
                finish();
            }
        });
    }

    private void onLogin()
    {
        ApiRequest apiRequest = Server.getClient().create(ApiRequest.class);

        Login login = new Login(txt_email.getText().toString(), txt_password.getText().toString());
        Call<UserModel> call = apiRequest.login(login);

        call.enqueue(new Callback<UserModel>() {
            @Override
            public void onResponse(Call<UserModel> call, Response<UserModel> response) {
                progressDialog.dismiss();

                UserModel userModelResponse = response.body();

                if(!response.isSuccessful())
                {
                    Toast.makeText(ActivityLogin.this, "Login Gagal", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    sessionManager.saveUser(userModelResponse.getId(), userModelResponse.getApiToken(),
                            userModelResponse.getName(), userModelResponse.getEmail(), userModelResponse.getJenisKelamin(),
                            userModelResponse.getKota(), userModelResponse.getAlamat(), userModelResponse.getPhoto());

//                    Toast.makeText(ActivityLogin.this, response.body().getToken(), Toast.LENGTH_SHORT).show();

                    Intent startMainActivity = new Intent(ActivityLogin.this, ActivityMain.class);
                    startActivity(startMainActivity);
                    finish();
                }
            }

            @Override
            public void onFailure(Call<UserModel> call, Throwable t) {
                Toast.makeText(ActivityLogin.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
