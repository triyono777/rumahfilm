package id.ac.dutabangsa.rumahfilm.rest;

import id.ac.dutabangsa.rumahfilm.entity.MovieResult;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by Triyono on 9/9/2018.
 */
public interface MovieInterface {
    @GET("movie/now_playing")
    Call<id.ac.dutabangsa.rumahfilm.entity.Movie> getNowPlayingMovie(@Query("api_key") String apiKey);

    @GET("movie/upcoming")
    Call<id.ac.dutabangsa.rumahfilm.entity.Movie> getUpcomingMovie(@Query("api_key") String apiKey);

    @GET("movie/{id}")
    Call<MovieResult> getMovieById(@Path("id") String id, @Query("api_key") String apiKey);

    @GET("search/movie/")
    Call<id.ac.dutabangsa.rumahfilm.entity.Movie> getMovieBySearch(@Query("query") String q, @Query("api_key") String apiKey);

}
