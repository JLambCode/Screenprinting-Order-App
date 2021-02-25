package com.example.customerordertracker.DAO;

import com.example.customerordertracker.Entities.Client;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import static com.example.customerordertracker.Utilities.Constants.TABLE_CLIENT;
import static com.example.customerordertracker.Utilities.Constants.TABLE_CLIENT_COL_ID;
import static com.example.customerordertracker.Utilities.Constants.TABLE_CLIENT_COL_NAME;

@Dao
public interface ClientDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertClient(Client client);

    @Update(onConflict = OnConflictStrategy.REPLACE)
    void updateClient(Client client);

    @Delete
    void deleteClient(Client client);

    @Query("DELETE FROM " + TABLE_CLIENT)
    int deleteAllClients();

    @Query("SELECT * FROM " + TABLE_CLIENT + " WHERE " + TABLE_CLIENT_COL_ID + " = :clientId LIMIT 1")
    Client getClient(int clientId);

    @Query("SELECT * FROM " + TABLE_CLIENT + " ORDER BY " + TABLE_CLIENT_COL_ID)
    LiveData<List<Client>> getAllClients();

    @Query("SELECT * FROM " + TABLE_CLIENT + " WHERE " + TABLE_CLIENT_COL_NAME + " LIKE ''%:clientSearch%''")
    LiveData<List<Client>> getSearchedClients(String clientSearch);
}
