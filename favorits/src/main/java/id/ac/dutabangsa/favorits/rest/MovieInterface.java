package id.ac.dutabangsa.favorits.rest;

import id.ac.dutabangsa.favorits.entity.MovieResult;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by Triyono on 10/11/2018.
 */
public interface MovieInterface {
    @GET("movie/{id}")
    Call<MovieResult> getMovieById(@Path("id") String id, @Query("api_key") String apiKey);
}
