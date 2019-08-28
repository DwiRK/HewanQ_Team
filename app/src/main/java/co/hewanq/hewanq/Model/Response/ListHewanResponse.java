package co.hewanq.hewanq.Model.Response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

import co.hewanq.hewanq.Model.HewanModel;

public class ListHewanResponse {
    @SerializedName("pet")
    @Expose
    private List<HewanModel> pet = null;

    public List<HewanModel> getPet() {
        return pet;
    }

    public void setPet(List<HewanModel> pet) {
        this.pet = pet;
    }
}
