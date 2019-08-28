package co.hewanq.hewanq.Model.Response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import co.hewanq.hewanq.Model.UserModel;

public class UserResponse {

    @SerializedName("user")
    @Expose
    private UserModel user;

    public UserModel getUser() {
        return user;
    }

    public void setUser(UserModel user) {
        this.user = user;
    }
}
