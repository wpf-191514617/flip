package com.sports.filip.entity.response;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * author:pengfei
 * date:2017/4/14
 * Email:15291967179@163.com
 */

public class CountryResponse implements Parcelable
{

    /**
     * country_id : 1
     * country_name : 意大利
     * cont_id : 1
     */

    private String country_id;
    private String country_name;
    private String cont_id;

    public String getCountry_id()
    {
        return country_id;
    }

    public void setCountry_id(String country_id)
    {
        this.country_id = country_id;
    }

    public String getCountry_name()
    {
        return country_name;
    }

    public void setCountry_name(String country_name)
    {
        this.country_name = country_name;
    }

    public String getCont_id()
    {
        return cont_id;
    }

    public void setCont_id(String cont_id)
    {
        this.cont_id = cont_id;
    }


    @Override
    public int describeContents()
    {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags)
    {
        dest.writeString(this.country_id);
        dest.writeString(this.country_name);
        dest.writeString(this.cont_id);
    }


    protected CountryResponse(Parcel in)
    {
        this.country_id = in.readString();
        this.country_name = in.readString();
        this.cont_id = in.readString();
    }

    public static final Creator<CountryResponse> CREATOR = new Creator<CountryResponse>()
    {
        @Override
        public CountryResponse createFromParcel(Parcel source)
        {
            return new CountryResponse(source);
        }

        @Override
        public CountryResponse[] newArray(int size)
        {
            return new CountryResponse[size];
        }
    };
}
