package id.ac.dutabangsa.rumahfilm.rest;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static id.ac.dutabangsa.rumahfilm.utils.Utils.BASE_URL;

/**
 * Created by Triyono on 9/9/2018.
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
