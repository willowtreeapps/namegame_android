package com.willowtreeapps.namegame.network.api;

import android.os.Parcel;
import android.os.Parcelable;

public class Person implements Parcelable {

    private final String name;

    public Person(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public String getUrl() {
        return "http://willowtreeapps.com/wp-content/uploads/2014/12/"+name.replaceAll(" ","_")+".jpg";
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.name);
    }

    protected Person(Parcel in) {
        this.name = in.readString();
    }

    public static final Creator<Person> CREATOR = new Creator<Person>() {
        public Person createFromParcel(Parcel source) {
            return new Person(source);
        }

        public Person[] newArray(int size) {
            return new Person[size];
        }
    };
}
