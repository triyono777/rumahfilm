package id.ac.dutabangsa.rumahfilm.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import id.ac.dutabangsa.rumahfilm.R;
import id.ac.dutabangsa.rumahfilm.activity.MovieDetailActivity;
import id.ac.dutabangsa.rumahfilm.entity.MovieResult;
import id.ac.dutabangsa.rumahfilm.utils.DateFormat;
import id.ac.dutabangsa.rumahfilm.utils.Utils;

/**
 * Created by Triyono on 9/9/2018.
 */
public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieViewHolder> {

    private List<MovieResult> movieResultList = new ArrayList<>();
    private Context context;

    public MovieAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MovieViewHolder(
                LayoutInflater.from(parent.getContext()).inflate(R.layout.item_movie_list,
                        parent, false)
        );
    }

    public void setMovieResult(List<MovieResult> movieResult) {
        this.movieResultList = movieResult;
    }

    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder holder, int position) {
        holder.bindView(movieResultList.get(position));
    }

    @Override
    public int getItemCount() {
        return movieResultList.size();
    }

    //View Holder
    class MovieViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.img_item_photo)
        ImageView item_poster;
        @BindView(R.id.btn_set_share)
        Button item_share;
        @BindView(R.id.tv_item_title)
        TextView item_title;
        @BindView(R.id.btn_set_detail)
        Button item_detail;
        @BindView(R.id.tv_item_date)
        TextView item_date;
        @BindView(R.id.tv_item_overview)
        TextView item_overview;

        MovieViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }


        void bindView(final MovieResult movieResult) {
            item_title.setText(movieResult.getTitle());
            item_overview.setText(movieResult.getOverview());
            item_date.setText(DateFormat.getDateDay(movieResult.getReleaseDate()));
            final Animation animScale = AnimationUtils.loadAnimation(context, R.anim.anim_scale);

            Picasso.get()
                    .load(Utils.BASE_POSTER_URL + movieResult.getPosterPath())
                    .placeholder(R.drawable.ic_image)
                    .error(R.drawable.ic_image)
                    .into(item_poster);

            item_detail.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(item_detail.getContext(), MovieDetailActivity.class);
                    intent.putExtra(Utils.MOVIE_DETAIL, movieResult);
                    v.startAnimation(animScale);
                    item_detail.getContext().startActivity(intent);
                }
            });
            item_share.setOnClickListener(new View.OnClickListener() {
                @SuppressLint("StringFormatInvalid")
                @Override
                public void onClick(View v) {
                    Intent sendIntent = new Intent();
                    sendIntent.setAction(Intent.ACTION_SEND);
                    sendIntent.putExtra(Intent.EXTRA_TEXT,
                            context.getString(R.string.share, movieResult.getTitle()));
                    sendIntent.setType("text/plain");
                    v.startAnimation(animScale);
                    itemView.getContext().startActivity(sendIntent);
                }
            });

        }
    }
}
