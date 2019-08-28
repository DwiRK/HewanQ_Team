package co.hewanq.hewanq.Model;

public class ArtikelModel
{
    private String judulArtikel,namaPenulis;
    private int fotoArtikel;

    public ArtikelModel(String judulArtikel, String namaPenulis, int fotoArtikel) {
        this.judulArtikel = judulArtikel;
        this.namaPenulis = namaPenulis;
        this.fotoArtikel = fotoArtikel;
    }

    public String getJudulArtikel() {
        return judulArtikel;
    }

    public String getNamaPenulis() {
        return namaPenulis;
    }

    public int getFotoArtikel() {
        return fotoArtikel;
    }
}
