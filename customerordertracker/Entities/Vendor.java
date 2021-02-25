package com.example.customerordertracker.Entities;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import static com.example.customerordertracker.Utilities.Constants.TABLE_VENDOR;
import static com.example.customerordertracker.Utilities.Constants.TABLE_VENDOR_COL_ID;
import static com.example.customerordertracker.Utilities.Constants.TABLE_VENDOR_COL_NAME;

@Entity(tableName = TABLE_VENDOR,
        indices = {@Index(value = TABLE_VENDOR_COL_ID)}
)

public class Vendor {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name=TABLE_VENDOR_COL_ID)
    public int vendorID;

    @ColumnInfo(name=TABLE_VENDOR_COL_NAME)
    @NonNull
    private String vendorName;

    public Vendor(int vendorID, String vendorName) {
        this.vendorID = vendorID;
        this.vendorName = vendorName;
    }

    @Override
    public String toString() {
        return "Vendor{" +
                "VendorID=" + vendorID +
                ", VendorName='" + vendorName + '\'' +
                '}';
    }

    public int getVendorID() {
        return vendorID;
    }

    public void setVendorID(int vendorID) {
        this.vendorID = vendorID;
    }

    public String getVendorName() {
        return vendorName;
    }

    public void setVendorName(String vendorName) {
        this.vendorName = vendorName;
    }
}
