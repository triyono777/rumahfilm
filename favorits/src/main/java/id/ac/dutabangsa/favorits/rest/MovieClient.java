package id.ac.dutabangsa.favorits.rest;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static id.ac.dutabangsa.favorits.Utils.Utils.BASE_URL;

/**
 * Created by Triyono on 10/11/2018.
 */
public class MovieClient {

    private static Retrofit retrofit = null;


    public static Retrofit getClient() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }

}
