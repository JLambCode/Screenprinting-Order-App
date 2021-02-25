package com.example.customerordertracker.Entities;

import com.example.customerordertracker.Utilities.Converters;

import java.util.Date;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.Index;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import static com.example.customerordertracker.Utilities.Constants.TABLE_ART;
import static com.example.customerordertracker.Utilities.Constants.TABLE_ART_COL_ARTLOC;
import static com.example.customerordertracker.Utilities.Constants.TABLE_ART_COL_ID;
import static com.example.customerordertracker.Utilities.Constants.TABLE_ART_COL_LASTUPDATED;
import static com.example.customerordertracker.Utilities.Constants.TABLE_ART_COL_LASTUPDATEDBY;
import static com.example.customerordertracker.Utilities.Constants.TABLE_ART_COL_NAME;
import static com.example.customerordertracker.Utilities.Constants.TABLE_ART_COL_NUMBEROFCOLORS;
import static com.example.customerordertracker.Utilities.Constants.TABLE_ART_COL_ORDERID;
import static com.example.customerordertracker.Utilities.Constants.TABLE_ART_COL_PLACEMENT;
import static com.example.customerordertracker.Utilities.Constants.TABLE_ORDER_COL_ID;

@Entity(tableName = TABLE_ART,
        foreignKeys = {@ForeignKey(
                entity = Order.class,
                parentColumns = TABLE_ORDER_COL_ID,
                childColumns = TABLE_ART_COL_ORDERID,
                onUpdate = ForeignKey.CASCADE,
                onDelete = ForeignKey.CASCADE)},
        indices = {@Index(value = TABLE_ART_COL_ID),@Index(value = TABLE_ART_COL_ORDERID)}
)

public class Art {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name=TABLE_ART_COL_ID)
    public int artID;

    @ColumnInfo(name=TABLE_ART_COL_NAME)
    @NonNull
    private String name;

    @ColumnInfo(name=TABLE_ART_COL_ARTLOC)
    private String artLoc;

    @ColumnInfo(name=TABLE_ART_COL_NUMBEROFCOLORS)
    private String numberOfColors;

    @ColumnInfo(name=TABLE_ART_COL_PLACEMENT)
    private String placement;

    @ColumnInfo(name=TABLE_ART_COL_LASTUPDATED)
    @TypeConverters(Converters.class)
    private Date lastUpdated;

    @ColumnInfo(name=TABLE_ART_COL_LASTUPDATEDBY)
    private String lastUpdatedBy;

    @ColumnInfo(name=TABLE_ART_COL_ORDERID)
    @NonNull
    public int orderID;

    public Art(int artID, String name, String artLoc, String numberOfColors, String placement, int orderID) {
        this.artID = artID;
        this.name = name;
        this.artLoc = artLoc;
        this.numberOfColors = numberOfColors;
        this.placement = placement;
        this.orderID = orderID;
    }
    @Ignore
    public Art(int artID, String name, int orderID) {
        this.artID = artID;
        this.name = name;
        this.orderID = orderID;
    }

    @Override
    public String toString() {
        return "Art{" +
                "ArtID=" + artID +
                ", Name='" + name + '\'' +
                ", ArtLoc='" + artLoc + '\'' +
                ", NumberOfColors=" + numberOfColors +
                ", Placement='" + placement + '\'' +
                ", OrderID=" + orderID +
                '}';
    }

    public int getArtID() {
        return artID;
    }

    public void setArtID(int artID) {
        this.artID = artID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getArtLoc() {
        return artLoc;
    }

    public void setArtLoc(String artLoc) {
        this.artLoc = artLoc;
    }

    public String getNumberOfColors() {
        return numberOfColors;
    }

    public void setNumberOfColors(String numberOfColors) {
        this.numberOfColors = numberOfColors;
    }

    public String getPlacement() {
        return placement;
    }

    public void setPlacement(String placement) {
        this.placement = placement;
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
}
