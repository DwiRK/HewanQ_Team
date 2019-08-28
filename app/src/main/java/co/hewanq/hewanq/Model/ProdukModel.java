package co.hewanq.hewanq.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ProdukModel
{
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("id_user")
    @Expose
    private Integer idUser;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("deskripsi_barang")
    @Expose
    private String deskripsiBarang;
    @SerializedName("kota")
    @Expose
    private String kota;
    @SerializedName("alamat")
    @Expose
    private String alamat;
    @SerializedName("photo")
    @Expose
    private String photo;
    @SerializedName("stock")
    @Expose
    private Integer stock;
    @SerializedName("price")
    @Expose
    private Integer price;
    @SerializedName("size")
    @Expose
    private float size;
    @SerializedName("ukuran")
    @Expose
    private String ukuran;
    @SerializedName("jenis_hewan")
    @Expose
    private String jenisHewan;
    @SerializedName("gender")
    @Expose
    private String gender;
    @SerializedName("tgl_lahir")
    @Expose
    private String tglLahir;
    @SerializedName("riwayat_kesehatan")
    @Expose
    private String riwayatKesehatan;
    @SerializedName("jenis")
    @Expose
    private String jenis;

    public Integer getId() {
        return id;
    }

    public Integer getIdUser() {
        return idUser;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDeskripsiBarang() {
        return deskripsiBarang;
    }

    public void setDeskripsiBarang(String deskripsiBarang) {
        this.deskripsiBarang = deskripsiBarang;
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

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public float getSize() {
        return size;
    }

    public void setSize(float size) {
        this.size = size;
    }

    public String getUkuran() {
        return ukuran;
    }

    public void setUkuran(String ukuran) {
        this.ukuran = ukuran;
    }

    public String getJenisHewan() {
        return jenisHewan;
    }

    public void setJenisHewan(String jenisHewan) {
        this.jenisHewan = jenisHewan;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getTglLahir() {
        return tglLahir;
    }

    public void setTglLahir(String tglLahir) {
        this.tglLahir = tglLahir;
    }

    public String getRiwayatKesehatan() {
        return riwayatKesehatan;
    }

    public void setRiwayatKesehatan(String riwayatKesehatan) {
        this.riwayatKesehatan = riwayatKesehatan;
    }

    public String getJenis() {
        return jenis;
    }

    public void setJenis(String jenis) {
        this.jenis = jenis;
    }
}
