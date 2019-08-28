package co.hewanq.hewanq.Model.Response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

import co.hewanq.hewanq.Model.JualBeliModel;
import co.hewanq.hewanq.Model.ProdukModel;

public class ListJualBeliResponse
{
    @SerializedName("barang")
    @Expose
    private List<JualBeliModel> jualBeli = null;

    public List<JualBeliModel> getJualBeli() {
        return jualBeli;
    }

    public void setItem(List<JualBeliModel> jualBeli) {
        this.jualBeli = jualBeli;
    }

}
