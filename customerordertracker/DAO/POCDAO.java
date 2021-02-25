package com.example.customerordertracker.DAO;

import com.example.customerordertracker.Entities.POC;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import static com.example.customerordertracker.Utilities.Constants.TABLE_POC;
import static com.example.customerordertracker.Utilities.Constants.TABLE_POC_COL_CLIENTID;
import static com.example.customerordertracker.Utilities.Constants.TABLE_POC_COL_ID;

@Dao
public interface POCDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertPOC(POC poc);

    @Update(onConflict = OnConflictStrategy.REPLACE)
    void updatePOC(POC poc);

    @Delete
    void deletePOC(POC poc);

    @Query("DELETE FROM " + TABLE_POC)
    int deleteAllPOCs();

    @Query("SELECT * FROM " + TABLE_POC + " WHERE " + TABLE_POC_COL_ID + " = :POCId LIMIT 1")
    POC getPOC(int POCId);

    @Query("SELECT * FROM " + TABLE_POC + " WHERE " + TABLE_POC_COL_CLIENTID + " = :clientId ORDER BY " + TABLE_POC_COL_ID)
    LiveData<List<POC>> getRelatedPOCs(int clientId);

    @Query("SELECT * FROM " + TABLE_POC + " ORDER BY " + TABLE_POC_COL_ID)
    LiveData<List<POC>> getAllPOCs();
}
