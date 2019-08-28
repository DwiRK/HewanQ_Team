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

public class ActivityTambahProduk extends AppCompatActivity
{
    private ApiRequest apiRequest;

    private static final int PICK_IMAGE = 100;
    private static final int PERMISSION_STORAGE = 2;

    private String selectImagePath;
    private RealPathUtil realPathUtil;

    protected String txt_nama, harga, kota, alamat, deskripsi, stok;
    ImageView foto_produk;

    private int userId;

    private SessionManager sessionManager;
    private HashMap<String, String> userData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah_produk);

        apiRequest = Server.getClient().create(ApiRequest.class);

        sessionManager = new SessionManager(ActivityTambahProduk.this);
        userData = sessionManager.getSavedToken();

        Intent intent = getIntent();
        userId = Integer.valueOf(intent.getStringExtra("id"));

        foto_produk = findViewById(R.id.foto_tambah_produk);

        Button tambah_btn = findViewById(R.id.tambah_btn_produk);
        Button tambah_image = findViewById(R.id.add_foto_btn_produk);

        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        tambah_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tambahProduk();
            }
        });

        tambah_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ContextCompat.checkSelfPermission(ActivityTambahProduk.this,
                        android.Manifest.permission.READ_EXTERNAL_STORAGE)
                        != PackageManager.PERMISSION_GRANTED
                        && ContextCompat.checkSelfPermission(ActivityTambahProduk.this,
                        android.Manifest.permission.WRITE_EXTERNAL_STORAGE)
                        != PackageManager.PERMISSION_GRANTED) {

                    ActivityCompat.requestPermissions(ActivityTambahProduk.this,
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

    private void tambahProduk()
    {
        File file = new File(selectImagePath);

        RequestBody requestFile = RequestBody.create(MediaType.parse("image"), file);

        MultipartBody.Part imageBody = MultipartBody.Part.createFormData("photo", file.getName(),
                requestFile);

        // Mengambil input user
        txt_nama = InputChecker.checkAndGetString((EditText) findViewById(R.id.nama_produk));
        harga = InputChecker.checkAndGetString((EditText) findViewById(R.id.harga));
        deskripsi = InputChecker.checkAndGetString((EditText) findViewById(R.id.deskripsi));;
        stok = InputChecker.checkAndGetString((EditText) findViewById(R.id.stok));
        kota = userData.get(sessionManager.getKota());
        alamat = userData.get(sessionManager.getAlamat());
        String jenis = "item";

        // Pengecekan apakah input kosong
        if(InputChecker.getError() == 0)
        {
            RequestBody namePart = RequestBody.create(MultipartBody.FORM, txt_nama);
            RequestBody deskripsiPart = RequestBody.create(MultipartBody.FORM, deskripsi);
            RequestBody kotaPart = RequestBody.create(MultipartBody.FORM, kota);
            RequestBody alamatPart = RequestBody.create(MultipartBody.FORM, alamat);
            RequestBody stockPart = RequestBody.create(MultipartBody.FORM, stok);
            RequestBody pricePart = RequestBody.create(MultipartBody.FORM, harga);
            RequestBody sizePart = RequestBody.create(MultipartBody.FORM, "1");
            RequestBody ukuranPart = RequestBody.create(MultipartBody.FORM, "1");
            RequestBody jenisPart = RequestBody.create(MultipartBody.FORM, jenis);

            Call<MessageResponse> messageResponseCall = apiRequest.tambahProduk(
                    userId, namePart, deskripsiPart, kotaPart, alamatPart, imageBody, stockPart, pricePart,
                    sizePart, ukuranPart, jenisPart);

            messageResponseCall.enqueue(new Callback<MessageResponse>() {
                @Override
                public void onResponse(Call<MessageResponse> call, Response<MessageResponse> response) {
                    if(!response.isSuccessful())
                    {
                        Log.d("BUAT Produk", "onResponse: " + response.message());
                    }
                    else
                    {
                        Toast.makeText(ActivityTambahProduk.this, "Berhasil ditambahkan",
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

            selectImagePath = realPathUtil.getRealPath(ActivityTambahProduk.this, selectImageUri);

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
        int targetW = foto_produk.getWidth();
        int targetH = foto_produk.getHeight();

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
            foto_produk.setImageBitmap(bitmap);
        }
    }
}
