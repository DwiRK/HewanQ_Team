package co.hewanq.hewanq.Presenter;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.HashMap;

public class SessionManager
{
    private SharedPreferences sharedPreferences;
    private static SharedPreferences.Editor sharedEditor;

    private static final String sharedName = "userToken";
    private static final String userId = "id";
    private static final String name = "name";
    private static final String email = "email";
    private static final String gender = "gender";
    private static final String kota = "kota";
    private static final String alamat = "alamat";
    private static final String photo = "photo";
    private static final String token = "token";
    private static final String loginStatus = "false";

    private Context context;

    public SessionManager(Context context)
    {
        this.context = context;
        sharedPreferences = this.context.getSharedPreferences(sharedName, Context.MODE_PRIVATE);
        sharedEditor = sharedPreferences.edit();
    }

    public void saveUser(int userId, String token, String name, String email, String gender, String
                         kota, String alamat, String photo)
    {
        sharedEditor.putBoolean(loginStatus, true);
        sharedEditor.putString(this.userId, String.valueOf(userId));
        sharedEditor.putString(this.name, name);
        sharedEditor.putString(this.email, email);
        sharedEditor.putString(this.gender, gender);
        sharedEditor.putString(this.kota, kota);
        sharedEditor.putString(this.alamat, alamat);
        sharedEditor.putString(this.photo, photo);
        sharedEditor.putString(this.token, token);

        sharedEditor.commit();
    }

    public HashMap getSavedToken()
    {
        HashMap<String, String> hashMap = new HashMap<>();

        hashMap.put(this.userId, sharedPreferences.getString(this.userId, null));
        hashMap.put(this.name, sharedPreferences.getString(this.name, null));
        hashMap.put(this.email, sharedPreferences.getString(this.email, null));
        hashMap.put(this.gender, sharedPreferences.getString(this.gender, null));
        hashMap.put(this.kota, sharedPreferences.getString(this.kota, null));
        hashMap.put(this.alamat, sharedPreferences.getString(this.alamat, null));
        hashMap.put(this.photo, sharedPreferences.getString(this.photo, null));
        hashMap.put(this.token, sharedPreferences.getString(this.token, null));

        return hashMap;
    }

    public boolean checkLogin()
    {
        boolean login = sharedPreferences.getBoolean(loginStatus, false);

        if(!login)
        {
            return false;
        }

        return true;
    }

    public void clearSession()
    {
        sharedEditor.clear();
        sharedEditor.commit();
    }

    public String getUserId()
    {
        return userId;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getGender() {
        return gender;
    }

    public String getKota() {
        return kota;
    }

    public String getAlamat() {
        return alamat;
    }

    public String getPhoto() {
        return photo;
    }
}
