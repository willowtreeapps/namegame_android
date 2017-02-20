package com.willowtreeapps.namegame.network.api.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

public class Person implements Parcelable {

    private final String id;
    private final String type;
    private final String slug;
    private final String jobTitle;
    private final String firstName;
    private final String lastName;
    private final Headshot headshot;
    private final List<String> socialLinks;

    public Person(String id,
                  String type,
                  String slug,
                  String jobTitle,
                  String firstName,
                  String lastName,
                  Headshot headshot,
                  List<String> socialLinks) {
        this.id = id;
        this.type = type;
        this.slug = slug;
        this.jobTitle = jobTitle;
        this.firstName = firstName;
        this.lastName = lastName;
        this.headshot = headshot;
        this.socialLinks = socialLinks;
    }

    private Person(Parcel in) {
        this.id = in.readString();
        this.type = in.readString();
        this.slug = in.readString();
        this.jobTitle = in.readString();
        this.firstName = in.readString();
        this.lastName = in.readString();
        this.headshot = in.readParcelable(Headshot.class.getClassLoader());
        this.socialLinks = in.createStringArrayList();
    }

    public String getId() {
        return id;
    }

    public String getType() {
        return type;
    }

    public String getSlug() {
        return slug;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public Headshot getHeadshot() {
        return headshot;
    }

    public List<String> getSocialLinks() {
        return socialLinks;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.id);
        dest.writeString(this.type);
        dest.writeString(this.slug);
        dest.writeString(this.jobTitle);
        dest.writeString(this.firstName);
        dest.writeString(this.lastName);
        dest.writeParcelable(this.headshot, flags);
        dest.writeStringList(this.socialLinks);
    }

    public static final Creator<Person> CREATOR = new Creator<Person>() {
        @Override
        public Person createFromParcel(Parcel source) {
            return new Person(source);
        }

        @Override
        public Person[] newArray(int size) {
            return new Person[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }
}