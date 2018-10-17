package id.ac.dutabangsa.favorits.activity;

import android.database.Cursor;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import id.ac.dutabangsa.favorits.R;
import id.ac.dutabangsa.favorits.adapter.MovieAdapter;
import id.ac.dutabangsa.favorits.database.MovieContract;
import id.ac.dutabangsa.favorits.entity.MovieFavorits;
import id.ac.dutabangsa.favorits.entity.MovieResult;
import id.ac.dutabangsa.favorits.rest.MovieClient;
import id.ac.dutabangsa.favorits.rest.MovieInterface;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static id.ac.dutabangsa.favorits.Utils.Utils.API_KEY;
import static id.ac.dutabangsa.favorits.database.MovieContract.CONTENT_URI;

public class MainActivity extends AppCompatActivity
        implements LoaderManager.LoaderCallbacks<Cursor> {

    @BindView(R.id.recycler_favorite)
    RecyclerView recyclerView;

    Call<MovieResult> movieResultCall;
    MovieAdapter adapter;
    ArrayList<MovieResult> movieResults;
    ArrayList<MovieFavorits> movieFavorits;
    MovieInterface movieService;

    private final int MOVIE_ID = 110;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        adapter = new MovieAdapter(getApplicationContext());
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        recyclerView.setHasFixedSize(true);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        getSupportLoaderManager().initLoader(MOVIE_ID, null, this);
    }


    @NonNull
    @Override
    public android.support.v4.content.Loader<Cursor> onCreateLoader(int id, @Nullable Bundle args) {
        movieFavorits = new ArrayList<>();
        movieResults = new ArrayList<>();
        return new CursorLoader(this, CONTENT_URI,
                null, null, null, null);
    }

    @Override
    public void onLoadFinished(@NonNull android.support.v4.content.Loader<Cursor> loader, Cursor data) {
        movieFavorits = getItem(data);
        for (MovieFavorits m : movieFavorits) {
            getFavoriteMovies(m.getId());
            adapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onLoaderReset(@NonNull android.support.v4.content.Loader<Cursor> loader) {
        movieFavorits = getItem(null);
    }

    private ArrayList<MovieFavorits> getItem(Cursor cursor) {
        ArrayList<MovieFavorits> movieFavoritsArrayList = new ArrayList<>();
        cursor.moveToFirst();
        MovieFavorits favorite;
        if (cursor.getCount() > 0) {
            do {
                favorite = new MovieFavorits(cursor.getString(cursor.getColumnIndexOrThrow(
                        MovieContract.MovieColumns.MOVIE_ID)));
                movieFavoritsArrayList.add(favorite);
                cursor.moveToNext();
            } while (!cursor.isAfterLast());
        }
        return movieFavoritsArrayList;
    }

    private void getFavoriteMovies(String id) {
        movieService = MovieClient.getClient().create(MovieInterface.class);
        movieResultCall = movieService.getMovieById(id, API_KEY);

        movieResultCall.enqueue((Callback<MovieResult>) new Callback<MovieResult>() {
            @Override
            public void onResponse(@NonNull Call<MovieResult> call, @NonNull Response<MovieResult> response) {
                movieResults.add(response.body());
                adapter.setMovieResult(movieResults);
                recyclerView.setAdapter(adapter);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(@NonNull Call<MovieResult> call, @NonNull Throwable t) {
                movieResults = null;
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (movieResults != null) {
            movieResults.clear();
            adapter.setMovieResult(movieResults);
            recyclerView.setAdapter(adapter);
        }
        getSupportLoaderManager().restartLoader(MOVIE_ID, null, this);
    }
}
