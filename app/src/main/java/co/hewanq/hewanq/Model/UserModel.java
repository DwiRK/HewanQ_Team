package co.hewanq.hewanq.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UserModel
{
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("role_id")
    @Expose
    private Integer roleId;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("jenis_kelamin")
    @Expose
    private String jenisKelamin;
    @SerializedName("phone")
    @Expose
    private String phone;
    @SerializedName("photo")
    @Expose
    private String photo;
    @SerializedName("kota")
    @Expose
    private String kota;
    @SerializedName("alamat")
    @Expose
    private String alamat;
    @SerializedName("hewan_dilayani")
    @Expose
    private String hewanDilayani;
    @SerializedName("fasilitas")
    @Expose
    private String fasilitas;
    @SerializedName("favorite_pet")
    @Expose
    private String favoritePet;
    @SerializedName("deskripsi")
    @Expose
    private String deskripsi;
    @SerializedName("api_token")
    @Expose
    private String apiToken;

    public Integer getId() {
        return id;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public String getApiToken() {
        return apiToken;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getJenisKelamin() {
        return jenisKelamin;
    }

    public void setJenisKelamin(String jenisKelamin) {
        this.jenisKelamin = jenisKelamin;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getKota() {
        return kota;
    }

    public void setKota(String kota) {
        this.kota = kota;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getHewanDilayani() {
        return hewanDilayani;
    }

    public void setHewanDilayani(String hewanDilayani) {
        this.hewanDilayani = hewanDilayani;
    }

    public String getFasilitas() {
        return fasilitas;
    }

    public void setFasilitas(String fasilitas) {
        this.fasilitas = fasilitas;
    }

    public String getFavoritePet() {
        return favoritePet;
    }

    public void setFavoritePet(String favoritePet) {
        this.favoritePet = favoritePet;
    }

    public String getDeskripsi() {
        return deskripsi;
    }

    public void setDeskripsi(String deskripsi) {
        this.deskripsi = deskripsi;
    }
}
