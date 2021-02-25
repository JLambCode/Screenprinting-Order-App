package com.example.customerordertracker.DAO;

import com.example.customerordertracker.Entities.Art;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import static com.example.customerordertracker.Utilities.Constants.TABLE_ART;
import static com.example.customerordertracker.Utilities.Constants.TABLE_ART_COL_ID;
import static com.example.customerordertracker.Utilities.Constants.TABLE_ART_COL_ORDERID;

@Dao
public interface ArtDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertArt(Art art);

    @Update(onConflict = OnConflictStrategy.REPLACE)
    void updateArt(Art art);

    @Delete
    void deleteArt(Art art);

    @Query("DELETE FROM " + TABLE_ART)
    int deleteAllArt();

    @Query("SELECT * FROM " + TABLE_ART + " WHERE " + TABLE_ART_COL_ID + " = :artId LIMIT 1")
    Art getArt(int artId);

    @Query("SELECT * FROM " + TABLE_ART + " WHERE " + TABLE_ART_COL_ORDERID + " = :orderId ORDER BY " + TABLE_ART_COL_ID)
    LiveData<List<Art>> getRelatedArt(int orderId);

    @Query("SELECT * FROM " + TABLE_ART + " ORDER BY " + TABLE_ART_COL_ID)
    LiveData<List<Art>> getAllArt();

}
