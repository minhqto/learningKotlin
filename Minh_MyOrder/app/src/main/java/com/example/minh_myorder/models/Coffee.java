package com.example.minh_myorder.models;

import android.os.Parcel;
import android.os.Parcelable;

public class Coffee implements Parcelable {

    private String type;
    private String style;
    private String size;
    private String quantity;

    public Coffee(String type, String style, String size, String quantity) {
        this.type = type;
        this.style = style;
        this.size = size;
        this.quantity = quantity;
    }

    protected Coffee(Parcel in) {
        type = in.readString();
        style = in.readString();
        size = in.readString();
        quantity = in.readString();
    }

    public static final Creator<Coffee> CREATOR = new Creator<Coffee>() {
        @Override
        public Coffee createFromParcel(Parcel in) {
            return new Coffee(in);
        }

        @Override
        public Coffee[] newArray(int size) {
            return new Coffee[size];
        }
    };

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getStyle() {
        return style;
    }

    public void setStyle(String style) {
        this.style = style;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(type);
        dest.writeString(style);
        dest.writeString(size);
        dest.writeString(quantity);
    }
}
