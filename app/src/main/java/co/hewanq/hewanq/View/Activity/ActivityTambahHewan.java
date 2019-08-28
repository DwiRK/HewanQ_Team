package co.hewanq.hewanq.View.Activity;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import java.io.File;
import java.util.HashMap;

import co.hewanq.hewanq.InputChecker;
import co.hewanq.hewanq.Model.Response.MessageResponse;
import co.hewanq.hewanq.Presenter.ApiRequest;
import co.hewanq.hewanq.Presenter.RealPathUtil;
import co.hewanq.hewanq.Presenter.Server;
import co.hewanq.hewanq.Presenter.SessionManager;
import co.hewanq.hewanq.R;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ActivityTambahHewan extends AppCompatActivity {
    private ApiRequest apiRequest;

    private static final int PICK_IMAGE = 100;
    private static final int PERMISSION_STORAGE = 2;

    private String selectImagePath;
    private RealPathUtil realPathUtil;

    protected String name, jenis_hewan, harga, kota, alamat, gender, deskripsi, riwayat_kesehatan,
            tanggal_lahir;
    protected EditText nameE, jenis_hewanE, hargaE, kotaE, alamatE, genderE, deskripsiE, riwayat_kesehatanE;
    ImageView foto_hewan;

    protected Spinner spJenisHewan, spJenisKelamin;

    private int userId;

    private SessionManager sessionManager;
    private HashMap<String, String> userData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah_hewan);

        apiRequest = Server.getClient().create(ApiRequest.class);

        sessionManager = new SessionManager(ActivityTambahHewan.this);
        userData = sessionManager.getSavedToken();

        Intent intent = getIntent();
        userId = Integer.valueOf(intent.getStringExtra("id"));

        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        Button tambah_btn = findViewById(R.id.tambah_btn);
        Button tambah_image = findViewById(R.id.tambah_image_hewan);

        spJenisHewan = (Spinner) findViewById(R.id.jenis_hewan);
        spJenisKelamin = (Spinner) findViewById(R.id.jenis_kelamin_hewan);

        foto_hewan = findViewById(R.id.foto_hewan);

        nameE = findViewById(R.id.nama_hewan);
        kotaE = findViewById(R.id.kota_hewan);
        alamatE = findViewById(R.id.alamat_hewan);
        hargaE = findViewById(R.id.harga);
        deskripsiE = findViewById(R.id.deskripsi);;
        riwayat_kesehatanE =findViewById(R.id.riwayat_kesehatan_hewan);

        tambah_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tambahHewan();
            }
        });

        tambah_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ContextCompat.checkSelfPermission(ActivityTambahHewan.this,
                        android.Manifest.permission.READ_EXTERNAL_STORAGE)
                        != PackageManager.PERMISSION_GRANTED
                        && ContextCompat.checkSelfPermission(ActivityTambahHewan.this,
                        android.Manifest.permission.WRITE_EXTERNAL_STORAGE)
                        != PackageManager.PERMISSION_GRANTED) {

                    ActivityCompat.requestPermissions(ActivityTambahHewan.this,
                            new String[]{android.Manifest.permission.READ_EXTERNAL_STORAGE, android.Manifest.permission.WRITE_EXTERNAL_STORAGE},
                            PERMISSION_STORAGE);

                }else{
                    openImageDirectory();
                }
            }
        });
    }

    private void openImageDirectory() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Image"), PICK_IMAGE);
    }

    private void tambahHewan()
    {
        File file = new File(selectImagePath);

        RequestBody requestFile = RequestBody.create(MediaType.parse("image"), file);

        MultipartBody.Part imageBody = MultipartBody.Part.createFormData("photo", file.getName(),
                requestFile);

        // Mengambil input user
        jenis_hewan = spJenisHewan.getSelectedItem().toString();
        gender = spJenisKelamin.getSelectedItem().toString();
        name = InputChecker.checkAndGetString((EditText) findViewById(R.id.nama_hewan));
        kota = userData.get(sessionManager.getKota());
        alamat = userData.get(sessionManager.getAlamat());
        harga = InputChecker.checkAndGetString((EditText) findViewById(R.id.harga));
        deskripsi = InputChecker.checkAndGetString((EditText) findViewById(R.id.deskripsi));;
        riwayat_kesehatan = InputChecker.checkAndGetString((EditText) findViewById(R.id.riwayat_kesehatan_hewan));
//        tanggal_lahir = InputChecker.checkAndGetString((EditText) findViewById(R.id.tgl_lahir));

        String jenis = "pet";
        String stock = "1";

        // Pengecekan apakah input kosong
        if(InputChecker.getError() == 0)
        {
            RequestBody namePart = RequestBody.create(MultipartBody.FORM, name);
            RequestBody deskripsiPart = RequestBody.create(MultipartBody.FORM, deskripsi);
            RequestBody kotaPart = RequestBody.create(MultipartBody.FORM, kota);
            RequestBody alamatPart = RequestBody.create(MultipartBody.FORM, alamat);
            RequestBody stockPart = RequestBody.create(MultipartBody.FORM, stock);
            RequestBody pricePart = RequestBody.create(MultipartBody.FORM, harga);
            RequestBody jenisHewanPart = RequestBody.create(MultipartBody.FORM, jenis_hewan);
            RequestBody genderPart = RequestBody.create(MultipartBody.FORM, gender);
//            RequestBody tglLahirPart = RequestBody.create(MultipartBody.FORM, tanggal_lahir);
            RequestBody riwayatKesehatanPart = RequestBody.create(MultipartBody.FORM, riwayat_kesehatan);
            RequestBody jenisPart = RequestBody.create(MultipartBody.FORM, jenis);

            Call<MessageResponse> messageResponseCall = apiRequest.tambahHewan(
                    userId, namePart, deskripsiPart, kotaPart, alamatPart, imageBody, stockPart, pricePart,
                    jenisHewanPart, genderPart, null, riwayatKesehatanPart, jenisPart);

            messageResponseCall.enqueue(new Callback<MessageResponse>() {
                @Override
                public void onResponse(Call<MessageResponse> call, Response<MessageResponse> response) {
                    if(!response.isSuccessful())
                    {
                        Log.d("BUAT HEWAN", "onResponse: " + response.message());
                    }
                    else
                    {
                        Toast.makeText(ActivityTambahHewan.this, "Berhasil ditambahkan",
                                Toast.LENGTH_SHORT).show();

//                        nameE.setText("");
//                        jenis_hewanE.setText("");
//                        hargaE.setText("");
//                        kotaE.setText("");
//                        alamatE.setText("");
//                        genderE.setText("");
//                        deskripsiE.setText("");
//                        riwayat_kesehatanE.setText("");
//                        foto_hewan.setVisibility(View.GONE);
                    }
                }

                @Override
                public void onFailure(Call<MessageResponse> call, Throwable t) {
                    Log.d("Gagal", "tambah hewan : " + t.getMessage());
                }
            });
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE && resultCode == Activity.RESULT_OK && data != null) {
            Uri selectImageUri = data.getData();

            realPathUtil = new RealPathUtil();

            selectImagePath = realPathUtil.getRealPath(ActivityTambahHewan.this, selectImageUri);

//            foto_hewan.setVisibility(View.VISIBLE);

            decodeImage(selectImagePath);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case PERMISSION_STORAGE: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    openImageDirectory();
                }

                return;
            }
        }
    }

    private void decodeImage(String selectImagePath) {
        int targetW = foto_hewan.getWidth();
        int targetH = foto_hewan.getHeight();

        final BitmapFactory.Options bmOptions = new BitmapFactory.Options();
        bmOptions.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(selectImagePath, bmOptions);

        int photoW = bmOptions.outWidth;
        int photoH = bmOptions.outHeight;
        int scaleFactor = Math.min(photoW / targetW, photoH / targetH);

        bmOptions.inJustDecodeBounds = false;
        bmOptions.inSampleSize = scaleFactor;
        Bitmap bitmap = BitmapFactory.decodeFile(selectImagePath, bmOptions);

        if (bitmap != null) {
            foto_hewan.setImageBitmap(bitmap);
        }
    }
}
