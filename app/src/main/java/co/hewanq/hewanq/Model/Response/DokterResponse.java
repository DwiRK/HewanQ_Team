package co.hewanq.hewanq.Model.Response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import co.hewanq.hewanq.Model.DokterModel;

public class DokterResponse
{
    @SerializedName("dokter")
    @Expose
    private DokterModel dokter;

    public DokterModel getDokter() {
        return dokter;
    }

    public void setDokter(DokterModel dokter) {
        this.dokter = dokter;
    }
}
