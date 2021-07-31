package com.example.minh_myorder.entities;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName="coffee_table")
public class Coffee {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name="coffee_id")
    private long coffeeId;

    @ColumnInfo(name="coffee_type")
    private String type;

    @ColumnInfo(name="coffee_style")
    private String style;

    @ColumnInfo(name="coffee_size")
    private String size;

    @ColumnInfo(name="coffee_quantity")
    private int quantity;

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

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Coffee() { }

    public Coffee(String type, String style, String size, int quantity) {
        this.type = type;
        this.style = style;
        this.size = size;
        this.quantity = quantity;
    }

    public Coffee(long coffeeId, String type, String style, String size, int quantity) {
        this.coffeeId = coffeeId;
        this.type = type;
        this.style = style;
        this.size = size;
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "Coffee{" +
                "coffeeId=" + coffeeId +
                ", type='" + type + '\'' +
                ", style='" + style + '\'' +
                ", size='" + size + '\'' +
                ", quantity=" + quantity +
                '}';
    }
}
