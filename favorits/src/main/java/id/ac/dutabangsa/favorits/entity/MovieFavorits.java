package id.ac.dutabangsa.favorits.entity;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Triyono on 10/11/2018.
 */
public class MovieFavorits implements Parcelable {
    private String id;
    private String title;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public MovieFavorits(String id) {
        this.id = id;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.id);
        dest.writeString(this.title);
    }

    protected MovieFavorits(Parcel in) {
        this.id = in.readString();
        this.title = in.readString();
    }

    public static final Parcelable.Creator<MovieFavorits> CREATOR = new Parcelable.Creator<MovieFavorits>() {
        @Override
        public MovieFavorits createFromParcel(Parcel source) {
            return new MovieFavorits(source);
        }

        @Override
        public MovieFavorits[] newArray(int size) {
            return new MovieFavorits[size];
        }
    };

}
