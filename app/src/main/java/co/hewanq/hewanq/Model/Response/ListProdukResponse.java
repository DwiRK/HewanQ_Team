package co.hewanq.hewanq.Model.Response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

import co.hewanq.hewanq.Model.ProdukModel;

public class ListProdukResponse
{
    @SerializedName("item")
    @Expose
    private List<ProdukModel> item = null;

    public List<ProdukModel> getItem() {
        return item;
    }

    public void setItem(List<ProdukModel> item) {
        this.item = item;
    }

}
