package com.example.minh_myorder.models;

import android.os.Parcel;
import android.os.Parcelable;

public class CoffeeItem implements Parcelable {

    private long coffeeId;
    private String type;
    private String style;
    private String size;
    private String quantity;

    public CoffeeItem(long coffeeId ,String type, String style, String size, String quantity) {
        this.coffeeId = coffeeId;
        this.type = type;
        this.style = style;
        this.size = size;
        this.quantity = quantity;
    }

    protected CoffeeItem(Parcel in) {
        coffeeId = in.readLong();
        type = in.readString();
        style = in.readString();
        size = in.readString();
        quantity = in.readString();
    }

    public static final Creator<CoffeeItem> CREATOR = new Creator<CoffeeItem>() {
        @Override
        public CoffeeItem createFromParcel(Parcel in) {
            return new CoffeeItem(in);
        }

        @Override
        public CoffeeItem[] newArray(int size) {
            return new CoffeeItem[size];
        }
    };

    public long getCoffeeId() {
        return coffeeId;
    }

    public void setCoffeeId(long coffeeId) {
        this.coffeeId = coffeeId;
    }

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
        dest.writeLong(coffeeId);
        dest.writeString(type);
        dest.writeString(style);
        dest.writeString(size);
        dest.writeString(quantity);
    }
}
