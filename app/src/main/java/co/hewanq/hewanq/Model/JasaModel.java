package co.hewanq.hewanq.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class JasaModel
{
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("user_id")
    @Expose
    private Integer userId;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("Hewan_dilayani")
    @Expose
    private String hewanDilayani;
    @SerializedName("kota")
    @Expose
    private String kota;
    @SerializedName("alamat")
    @Expose
    private String alamat;
    @SerializedName("hari_buka")
    @Expose
    private String hariBuka;
    @SerializedName("jam_buka")
    @Expose
    private String jamBuka;
    @SerializedName("photo")
    @Expose
    private String photo;
    @SerializedName("deskripsi")
    @Expose
    private String deskripsi;
    @SerializedName("harga")
    @Expose
    private Integer harga;

    public Integer getId() {
        return id;
    }

    public Integer getUserId() {
        return userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHewanDilayani() {
        return hewanDilayani;
    }

    public void setHewanDilayani(String hewanDilayani) {
        this.hewanDilayani = hewanDilayani;
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

    public String getHariBuka() {
        return hariBuka;
    }

    public void setHariBuka(String hariBuka) {
        this.hariBuka = hariBuka;
    }

    public String getJamBuka() {
        return jamBuka;
    }

    public void setJamBuka(String jamBuka) {
        this.jamBuka = jamBuka;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getDeskripsi() {
        return deskripsi;
    }

    public void setDeskripsi(String deskripsi) {
        this.deskripsi = deskripsi;
    }

    public Integer getHarga() {
        return harga;
    }

    public void setHarga(Integer harga) {
        this.harga = harga;
    }
}
