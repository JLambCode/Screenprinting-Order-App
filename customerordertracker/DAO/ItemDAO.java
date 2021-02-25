package com.example.customerordertracker.DAO;

import com.example.customerordertracker.Entities.Item;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import static com.example.customerordertracker.Utilities.Constants.TABLE_ITEM;
import static com.example.customerordertracker.Utilities.Constants.TABLE_ITEM_COL_ID;
import static com.example.customerordertracker.Utilities.Constants.TABLE_ITEM_COL_ORDERID;
import static com.example.customerordertracker.Utilities.Constants.TABLE_ITEM_COL_VENDORID;

@Dao
public interface ItemDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertItem(Item item);

    @Update(onConflict = OnConflictStrategy.REPLACE)
    void updateItem(Item item);

    @Delete
    void deleteItem(Item item);

    @Query("DELETE FROM " + TABLE_ITEM)
    int deleteAllItems();

    @Query("SELECT * FROM " + TABLE_ITEM + " WHERE " + TABLE_ITEM_COL_ID + " = :itemId LIMIT 1")
    Item getItem(int itemId);

    @Query("SELECT * FROM " + TABLE_ITEM + " WHERE " + TABLE_ITEM_COL_ORDERID + " = :orderId ORDER BY " + TABLE_ITEM_COL_ID)
    LiveData<List<Item>> getRelatedItems(int orderId);

    @Query("SELECT * FROM " + TABLE_ITEM + " ORDER BY " + TABLE_ITEM_COL_ID)
    LiveData<List<Item>> getAllItems();

}
