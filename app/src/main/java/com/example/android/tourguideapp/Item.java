package com.example.android.tourguideapp;

import android.os.Parcel;
import android.os.Parcelable;

public class Item implements Parcelable {

    private String mName;
    private String mDescrition;
    private String mInforamtion;
    private String mImageResourceId;

    @Override
    public String toString() {
        return "Item{" +
                "mName='" + mName + '\'' +
                ", mDescrition='" + mDescrition + '\'' +
                ", mInforamtion='" + mInforamtion + '\'' +
                ", mImageResourceId='" + mImageResourceId + '\'' +
                '}';
    }


    public Item(String name, String descrtion, String information) {
        mName = name;
        mDescrition = descrtion;
        mInforamtion = information;
    }

    public Item(String name, String descrtion, String imageResourceId,
                String information) {
        mName = name;
        mDescrition = descrtion;
        mImageResourceId = imageResourceId;
        mInforamtion = information;
    }

    public String getName() {
        return mName;
    }

    public String getDescrtion() {
        return mDescrition;
    }

    public String getImageResourceId() {
        return mImageResourceId;
    }

    public String getInformation() {
        return mInforamtion;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.mName);
        dest.writeString(this.mDescrition);
        dest.writeString(this.mInforamtion);
        dest.writeString(this.mImageResourceId);
    }

    protected Item(Parcel in) {
        this.mName = in.readString();
        this.mDescrition = in.readString();
        this.mInforamtion = in.readString();
        this.mImageResourceId = in.readString();
    }

    public static final Parcelable.Creator<Item> CREATOR = new Parcelable.Creator<Item>() {
        @Override
        public Item createFromParcel(Parcel source) {
            return new Item(source);
        }

        @Override
        public Item[] newArray(int size) {
            return new Item[size];
        }
    };
}