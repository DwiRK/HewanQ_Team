package co.hewanq.hewanq.Model.Response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

import co.hewanq.hewanq.Model.JasaModel;

public class ListJasaResponse
{
    @SerializedName("service")
    @Expose
    private List<JasaModel> jasaModel = null;

    public List<JasaModel> getjasa() {
        return jasaModel;
    }

    public void setService(List<JasaModel> jasaModel) {
        this.jasaModel = jasaModel;
    }
}
