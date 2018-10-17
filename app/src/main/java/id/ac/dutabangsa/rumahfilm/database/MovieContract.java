package id.ac.dutabangsa.rumahfilm.database;

import android.database.Cursor;
import android.net.Uri;
import android.provider.BaseColumns;

import static id.ac.dutabangsa.rumahfilm.database.MovieContract.MovieColumns.TABEL_MOVIE;

/**
 * Created by Triyono on 9/9/2018.
 */
public class MovieContract {
    public static final String AUTHORITY = "id.ac.dutabangsa.rumahfilm";
    public static final Uri CONTENT_URI = new Uri.Builder().scheme("content")
            .authority(AUTHORITY)
            .appendPath(TABEL_MOVIE)
            .build();

    public static String getColumnString(Cursor cursor, String columnName) {
        return cursor.getString(cursor.getColumnIndex(columnName));
    }

    public static int getColumnInt(Cursor cursor, String columnName) {
        return cursor.getInt(cursor.getColumnIndex(columnName));
    }

    public static long getColumnLong(Cursor cursor, String columnName) {
        return cursor.getLong(cursor.getColumnIndex(columnName));
    }

    public static final class MovieColumns implements BaseColumns {

        public static final String TABEL_MOVIE = "movie";
        public static String MOVIE_ID = "movie_id";
        public static String MOVIE_TITLE = "title";
    }
}
