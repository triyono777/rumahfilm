package id.ac.dutabangsa.rumahfilm.fragment;


import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import id.ac.dutabangsa.rumahfilm.R;
import id.ac.dutabangsa.rumahfilm.adapter.MovieAdapter;
import id.ac.dutabangsa.rumahfilm.entity.Movie;
import id.ac.dutabangsa.rumahfilm.entity.MovieResult;
import id.ac.dutabangsa.rumahfilm.rest.MovieClient;
import id.ac.dutabangsa.rumahfilm.rest.MovieInterface;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static id.ac.dutabangsa.rumahfilm.utils.Utils.API_KEY;

/**
 * A simple {@link Fragment} subclass.
 */
public class UpcomingFragment extends Fragment {


    @BindView(R.id.recycler_movie_upcoming)
    RecyclerView recyclerView;

    @BindView(R.id.progress_bar_upcoming)
    ProgressBar progressBar;

    List<MovieResult> movieList;
    MovieAdapter movieAdapter;

    MovieInterface movieService;
    Call<Movie> movieCall;


    public UpcomingFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_upcoming, container, false);
        ButterKnife.bind(this, rootView);
        initView();
        getMovies();

        return rootView;
    }

    void initView() {

        movieAdapter = new MovieAdapter(getActivity());
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setHasFixedSize(true);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        return super.onOptionsItemSelected(item);
    }

    private void getMovies() {

        showProgressBar();
        movieService = MovieClient.getClient().create(MovieInterface.class);
        movieCall = movieService.getUpcomingMovie(API_KEY);

        movieList = new ArrayList<>();

        movieCall.enqueue(new Callback<Movie>() {
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onResponse(@NonNull Call<Movie> call, @NonNull Response<Movie> response) {
                if (response.body() != null) {
                    movieList = Objects.requireNonNull(response.body()).getResults();
                }
                movieAdapter.setMovieResult(movieList);
                recyclerView.setAdapter(movieAdapter);
                hideProgressBar();
            }

            @Override
            public void onFailure(@NonNull Call<Movie> call, @NonNull Throwable t) {
                Toast.makeText(getActivity(), "Ada kesalahan, Error"
                        , Toast.LENGTH_SHORT).show();
                hideProgressBar();
            }
        });
    }

    void showProgressBar() {
        progressBar.setVisibility(View.VISIBLE);
    }

    void hideProgressBar() {
        progressBar.setVisibility(View.GONE);
    }
}