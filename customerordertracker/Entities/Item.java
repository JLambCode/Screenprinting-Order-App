package com.example.customerordertracker.Entities;

import com.example.customerordertracker.Utilities.Converters;

import java.lang.reflect.GenericArrayType;
import java.util.Date;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.Index;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import static com.example.customerordertracker.Utilities.Constants.TABLE_ITEM;
import static com.example.customerordertracker.Utilities.Constants.TABLE_ITEM_COL_AORY;
import static com.example.customerordertracker.Utilities.Constants.TABLE_ITEM_COL_APPLICATION;
import static com.example.customerordertracker.Utilities.Constants.TABLE_ITEM_COL_COLOR;
import static com.example.customerordertracker.Utilities.Constants.TABLE_ITEM_COL_DESCRIPTION;
import static com.example.customerordertracker.Utilities.Constants.TABLE_ITEM_COL_ID;
import static com.example.customerordertracker.Utilities.Constants.TABLE_ITEM_COL_LASTUPDATED;
import static com.example.customerordertracker.Utilities.Constants.TABLE_ITEM_COL_LASTUPDATEDBY;
import static com.example.customerordertracker.Utilities.Constants.TABLE_ITEM_COL_NAME;
import static com.example.customerordertracker.Utilities.Constants.TABLE_ITEM_COL_ORDERID;
import static com.example.customerordertracker.Utilities.Constants.TABLE_ITEM_COL_QUANTITY;
import static com.example.customerordertracker.Utilities.Constants.TABLE_ITEM_COL_SIZE;
import static com.example.customerordertracker.Utilities.Constants.TABLE_ITEM_COL_VENDORID;
import static com.example.customerordertracker.Utilities.Constants.TABLE_ORDER_COL_ID;
import static com.example.customerordertracker.Utilities.Constants.TABLE_VENDOR_COL_ID;

@Entity(tableName = TABLE_ITEM,
        foreignKeys = {
            @ForeignKey(entity = Vendor.class,
                    parentColumns = TABLE_VENDOR_COL_ID,
                    childColumns = TABLE_ITEM_COL_VENDORID,
                    onUpdate = ForeignKey.CASCADE,
                    onDelete = ForeignKey.CASCADE),
            @ForeignKey(entity = Order.class,
                    parentColumns = TABLE_ORDER_COL_ID,
                    childColumns = TABLE_ITEM_COL_ORDERID,
                    onUpdate = ForeignKey.CASCADE,
                    onDelete = ForeignKey.CASCADE)},
            indices = {@Index(value = TABLE_ITEM_COL_ID),@Index(value = TABLE_ORDER_COL_ID),@Index(value = TABLE_VENDOR_COL_ID)}
)

public class Item {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name=TABLE_ITEM_COL_ID)
    public int itemID;

    @ColumnInfo(name=TABLE_ITEM_COL_NAME)
    @NonNull
    private String name;

    @ColumnInfo(name=TABLE_ITEM_COL_DESCRIPTION)
    private String description;

    @ColumnInfo(name=TABLE_ITEM_COL_QUANTITY)
    private String quantity;

    @ColumnInfo(name=TABLE_ITEM_COL_AORY)
    @NonNull
    private String adultOrYouth;

    @ColumnInfo(name=TABLE_ITEM_COL_SIZE)
    @NonNull
    private String size;

    @ColumnInfo(name=TABLE_ITEM_COL_APPLICATION)
    @NonNull
    private String application;

    @ColumnInfo(name=TABLE_ITEM_COL_COLOR)
    private String color;

    @ColumnInfo(name=TABLE_ITEM_COL_LASTUPDATED)
    @TypeConverters(Converters.class)
    private Date lastUpdated;

    @ColumnInfo(name=TABLE_ITEM_COL_LASTUPDATEDBY)
    private String lastUpdatedBy;

    @ColumnInfo(name=TABLE_ITEM_COL_ORDERID)
    @NonNull
    public int orderID;

    @ColumnInfo(name=TABLE_ITEM_COL_VENDORID)
    @NonNull
    public int vendorID;

    public Item(int itemID, String name, String description, String quantity, String adultOrYouth, String size, String application, String color, int orderID, int vendorID) {
        this.itemID = itemID;
        this.name = name;
        this.description = description;
        this.quantity = quantity;
        this.adultOrYouth = adultOrYouth;
        this.size = size;
        this.application = application;
        this.color = color;
        this.orderID = orderID;
        this.vendorID = vendorID;
    }

    @Ignore
    public Item(int itemID, String name, int orderID, int vendorID) {
        this.itemID = itemID;
        this.name = name;
        this.orderID = orderID;
        this.vendorID = vendorID;
    }

    @Override
    public String toString() {
        return "Item{" +
                "itemID=" + itemID +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", quantity='" + quantity + '\'' +
                ", adultOrYouth='" + adultOrYouth + '\'' +
                ", size='" + size + '\'' +
                ", application='" + application + '\'' +
                ", color='" + color + '\'' +
                ", lastUpdated=" + lastUpdated +
                ", lastUpdatedBy='" + lastUpdatedBy + '\'' +
                ", orderID=" + orderID +
                ", vendorID=" + vendorID +
                '}';
    }

    public int getItemID() {
        return itemID;
    }

    public void setItemID(int itemID) {
        this.itemID = itemID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getAdultOrYouth() {
        return adultOrYouth;
    }

    public void setAdultOrYouth(String adultOrYouth) {
        this.adultOrYouth = adultOrYouth;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getApplication() {
        return application;
    }

    public void setApplication(String application) {
        this.application = application;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Date getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(Date lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

    public String getLastUpdatedBy() {
        return lastUpdatedBy;
    }

    public void setLastUpdatedBy(String lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
    }

    public int getOrderID() {
        return orderID;
    }

    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }

    public int getVendorID() {
        return vendorID;
    }

    public void setVendorID(int vendorID) {
        this.vendorID = vendorID;
    }
}
