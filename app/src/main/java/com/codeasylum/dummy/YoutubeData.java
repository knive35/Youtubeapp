package com.codeasylum.dummy;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Kamesh on 21-05-2017.
 */

public class YoutubeData implements Parcelable{
    String  header_image_url;
    String full_title;
    String videoId;
    String name; //channel name

    public String getVideoId() {
        return videoId;
    }

    protected YoutubeData(Parcel in) {
        header_image_url = in.readString();
        full_title = in.readString();
        videoId = in.readString();
        name = in.readString();
    }

    public static final Creator<YoutubeData> CREATOR = new Creator<YoutubeData>() {
        @Override
        public YoutubeData createFromParcel(Parcel in) {
            return new YoutubeData(in);
        }

        @Override
        public YoutubeData[] newArray(int size) {
            return new YoutubeData[size];
        }
    };

    public YoutubeData() {

    }

    public String getHeader_image_url() {

        return header_image_url;
    }

    public void setHeader_image_url(String header_image_url) {
        this.header_image_url = header_image_url;
    }

    public String getFull_title()
    {
        return full_title;
    }

    public void setFull_title(String full_title)
    {
        this.full_title = full_title;
    }

    public String getPath() {

        return videoId;
    }

    public void setVideoId(String path)
    {
        this.videoId = path;
    }

    public String getName() {

        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(header_image_url);
        dest.writeString(full_title);
        dest.writeString(videoId);
        dest.writeString(name);
    }
}
