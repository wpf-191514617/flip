package com.sports.filip.entity.response;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * author:pengfei
 * date:2017/4/14
 * Email:15291967179@163.com
 */

public class RemarkResponse implements Parcelable
{


    /**
     * match_id : 31
     * country_id : 3
     * match_name : 法甲
     * match_url : http://www.iuliao.com/info/league/93/
     * remark : 
     */

    private String match_id;
    private String country_id;
    private String match_name;
    private String match_url;
    private String remark;

    public String getMatch_id()
    {
        return match_id;
    }

    public void setMatch_id(String match_id)
    {
        this.match_id = match_id;
    }

    public String getCountry_id()
    {
        return country_id;
    }

    public void setCountry_id(String country_id)
    {
        this.country_id = country_id;
    }

    public String getMatch_name()
    {
        return match_name;
    }

    public void setMatch_name(String match_name)
    {
        this.match_name = match_name;
    }

    public String getMatch_url()
    {
        return match_url;
    }

    public void setMatch_url(String match_url)
    {
        this.match_url = match_url;
    }

    public String getRemark()
    {
        return remark;
    }

    public void setRemark(String remark)
    {
        this.remark = remark;
    }


    @Override
    public int describeContents()
    {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags)
    {
        dest.writeString(this.match_id);
        dest.writeString(this.country_id);
        dest.writeString(this.match_name);
        dest.writeString(this.match_url);
        dest.writeString(this.remark);
    }

    public RemarkResponse()
    {
    }

    protected RemarkResponse(Parcel in)
    {
        this.match_id = in.readString();
        this.country_id = in.readString();
        this.match_name = in.readString();
        this.match_url = in.readString();
        this.remark = in.readString();
    }

    public static final Parcelable.Creator<RemarkResponse> CREATOR = new Parcelable.Creator<RemarkResponse>()
    {
        @Override
        public RemarkResponse createFromParcel(Parcel source)
        {
            return new RemarkResponse(source);
        }

        @Override
        public RemarkResponse[] newArray(int size)
        {
            return new RemarkResponse[size];
        }
    };
}
