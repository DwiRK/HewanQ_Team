package co.hewanq.hewanq.Presenter;

import co.hewanq.hewanq.Model.Response.DokterResponse;
import co.hewanq.hewanq.Model.Response.HewanResponse;
import co.hewanq.hewanq.Model.Response.JasaResponse;
import co.hewanq.hewanq.Model.Response.ListArtikelResponse;
import co.hewanq.hewanq.Model.Response.ListDokterResponse;
import co.hewanq.hewanq.Model.Response.ListHewanResponse;
import co.hewanq.hewanq.Model.Response.ListJasaResponse;
import co.hewanq.hewanq.Model.Response.ListJualBeliResponse;
import co.hewanq.hewanq.Model.Response.ListProdukResponse;
import co.hewanq.hewanq.Model.Response.MessageResponse;
import co.hewanq.hewanq.Model.Response.ProdukResponse;
import co.hewanq.hewanq.Model.Response.UserResponse;
import co.hewanq.hewanq.Model.UserModel;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;

public interface ApiRequest
{
    @POST("api/auth/login")
    Call<UserModel> login(@Body Login login);

    @FormUrlEncoded
    @POST("api/auth/register")
    Call<UserModel> register(@Field("name") String nama, @Field("email") String email,
                             @Field("password") String password);

    @POST("api/auth/update/{id}")
    Call<UserModel> login(@Path("id") int id);

    @GET("api/detailUser/{id}")
    Call<UserResponse> getUserDetail(@Path("id") int id);

    @GET("api/dokters")
    Call<ListDokterResponse> getListDokter();

    @GET("api/detailDokter/{id}")
    Call<DokterResponse> getDataDokter(@Path("id") int id);

    @GET("api/barangs")
    Call<ListJualBeliResponse> getListJualBeli();

    @GET("api/pet")
    Call<ListHewanResponse> getListHewan();

    @GET("api/pet/{id}")
    Call<ListHewanResponse> getListHewanUser(@Path("id") int id);

    @Multipart
    @POST("api/buatHewan/{id}")
    Call<MessageResponse> tambahHewan(
            @Path("id") int id,
            @Part("name") RequestBody name,
            @Part("deskripsi_barang") RequestBody deskripsi_barang,
            @Part("kota") RequestBody kota,
            @Part("alamat") RequestBody alamat,
            @Part MultipartBody.Part photo,
            @Part("stock") RequestBody stock,
            @Part("price") RequestBody price,
            @Part("jenis_hewan") RequestBody jenis_hewan,
            @Part("gender") RequestBody gender,
            @Part("tgl_lahir") RequestBody tgl_lahir,
            @Part("riwayat_kesehatan") RequestBody riwayat_kesehatan,
            @Part("jenis") RequestBody jenis
    );

    @GET("api/editHewan/{id}")
    Call<HewanResponse> getDataHewan(@Path("id") int id);

    @GET("api/item")
    Call<ListProdukResponse> getListProduk();

    @GET("api/item/{id}")
    Call<ListProdukResponse> getListProdukUser(@Path("id") int id);

    @Multipart
    @POST("api/buatItem/{id}")
    Call<MessageResponse> tambahProduk(
            @Path("id") int id,
            @Part("name") RequestBody name,
            @Part("deskripsi_barang") RequestBody deskripsi_barang,
            @Part("kota") RequestBody kota,
            @Part("alamat") RequestBody alamat,
            @Part MultipartBody.Part photo,
            @Part("stock") RequestBody stock,
            @Part("price") RequestBody price,
            @Part("size") RequestBody size,
            @Part("ukuran") RequestBody ukuran,
            @Part("jenis") RequestBody jenis
    );

    @GET("api/editItem/{id}")
    Call<ProdukResponse> getDataProduk(@Path("id") int id);

    @GET("api/users")
    Call<ListArtikelResponse> getListArtikel();

    @GET("api/service")
    Call<ListJasaResponse> getListJasa();

    @GET("api/service/{id}")
    Call<ListJasaResponse> getListJasaUser(@Path("id") int id);

    @Multipart
    @POST("api/buatService/{id}")
    Call<MessageResponse> tambahJasa(
            @Path("id") int id,
            @Part("name") RequestBody name,
            @Part("Hewan_dilayani") RequestBody Hewan_dilayani,
            @Part("kota") RequestBody kota,
            @Part("alamat") RequestBody alamat,
            @Part("hari_buka") RequestBody hari_buka,
            @Part("jam_buka") RequestBody jam_buka,
            @Part MultipartBody.Part photo,
            @Part("deskripsi") RequestBody jenis_hewan,
            @Part("harga") RequestBody harga
    );

    @GET("api/editService/{id}")
    Call<JasaResponse> getDataJasa(@Path("id") int id);
}
