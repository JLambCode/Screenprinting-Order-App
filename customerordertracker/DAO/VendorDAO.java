package com.example.customerordertracker.DAO;

import com.example.customerordertracker.Entities.Vendor;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import static com.example.customerordertracker.Utilities.Constants.TABLE_VENDOR;
import static com.example.customerordertracker.Utilities.Constants.TABLE_VENDOR_COL_ID;

@Dao
public interface VendorDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertVendor(Vendor vendor);

    @Update(onConflict = OnConflictStrategy.REPLACE)
    void updateVendor(Vendor vendor);

    @Delete
    void deleteVendor(Vendor vendor);

    @Query("DELETE FROM " + TABLE_VENDOR)
    int deleteAllVendors();

    @Query("SELECT * FROM " + TABLE_VENDOR + " WHERE " + TABLE_VENDOR_COL_ID + " = :vendorId LIMIT 1")
    Vendor getVendor(int vendorId);

    @Query("SELECT * FROM " + TABLE_VENDOR + " ORDER BY " + TABLE_VENDOR_COL_ID)
    LiveData<List<Vendor>> getAllVendors();

    @Query("SELECT COUNT(*) FROM " + TABLE_VENDOR)
    int getVendorCount();

}
