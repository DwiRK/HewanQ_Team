package co.hewanq.hewanq.Model.Response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

import co.hewanq.hewanq.Model.DokterModel;
import co.hewanq.hewanq.Model.UserModel;

public class ListDokterResponse {
    @SerializedName("dokter")
    @Expose
    private List<DokterModel> dokter = null;

    public List<DokterModel> getDokter() {
        return dokter;
    }

    public void setUser(List<DokterModel> dokter) {
        this.dokter = dokter;
    }
}
