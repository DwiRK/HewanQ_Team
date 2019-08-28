package co.hewanq.hewanq.Presenter;

import java.net.CookieHandler;
import java.net.CookieManager;
import java.util.concurrent.TimeUnit;

import okhttp3.JavaNetCookieJar;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Server {
    public static final String url = "http://128.199.74.127/";
    private static Retrofit retrofit;

    public static Retrofit getClient()
    {
        if(retrofit == null)
        {
            HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
            logging.setLevel(HttpLoggingInterceptor.Level.BODY);

            CookieHandler cookieHandler = new CookieManager();

            okhttp3.OkHttpClient okHttpClient = new OkHttpClient.Builder()
                    .addNetworkInterceptor(logging)
                    .cookieJar(new JavaNetCookieJar(cookieHandler))
                    .readTimeout(5, TimeUnit.MINUTES)
                    .connectTimeout(5, TimeUnit.MINUTES)
                    .writeTimeout(5,TimeUnit.MINUTES)
                    .build();

            retrofit = new Retrofit.Builder().baseUrl(url)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

//            return  retrofit;
        }

        return retrofit;
    }
}
