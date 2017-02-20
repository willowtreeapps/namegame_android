package com.willowtreeapps.namegame.network.api.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Metadata implements Parcelable {

    private final int skip;
    private final int limit;
    private final int total;

    public Metadata(int skip, int limit, int total) {
        this.skip = skip;
        this.limit = limit;
        this.total = total;
    }

    private Metadata(Parcel in) {
        this.skip = in.readInt();
        this.limit = in.readInt();
        this.total = in.readInt();
    }

    public int getSkip() {
        return skip;
    }

    public int getLimit() {
        return limit;
    }

    public int getTotal() {
        return total;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.skip);
        dest.writeInt(this.limit);
        dest.writeInt(this.total);
    }

    public static final Creator<Metadata> CREATOR = new Creator<Metadata>() {
        @Override
        public Metadata createFromParcel(Parcel source) {
            return new Metadata(source);
        }

        @Override
        public Metadata[] newArray(int size) {
            return new Metadata[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }
}
