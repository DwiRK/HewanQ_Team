package co.hewanq.hewanq.Model.Response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

import co.hewanq.hewanq.Model.UserModel;

public class ListArtikelResponse {
    @SerializedName("user")
    @Expose
    private List<UserModel> user = null;

    public List<UserModel> getUserModel() {
        return user;
    }

    public void setUser(List<UserModel> user) {
        this.user = user;
    }
}
