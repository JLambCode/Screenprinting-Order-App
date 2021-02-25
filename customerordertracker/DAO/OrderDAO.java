package com.example.customerordertracker.DAO;

import com.example.customerordertracker.Entities.Order;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import static com.example.customerordertracker.Utilities.Constants.TABLE_ORDER;
import static com.example.customerordertracker.Utilities.Constants.TABLE_ORDER_COL_CLIENTID;
import static com.example.customerordertracker.Utilities.Constants.TABLE_ORDER_COL_DESCRIPTION;
import static com.example.customerordertracker.Utilities.Constants.TABLE_ORDER_COL_ID;
import static com.example.customerordertracker.Utilities.Constants.TABLE_ORDER_COL_NAME;

@Dao
public interface OrderDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertOrder(Order order);

    @Update(onConflict = OnConflictStrategy.REPLACE)
    void updateOrder(Order order);

    @Delete
    void deleteOrder(Order order);

    @Query("DELETE FROM " + TABLE_ORDER)
    int deleteAllOrders();

    @Query("SELECT * FROM " + TABLE_ORDER + " WHERE " + TABLE_ORDER_COL_ID + " = :orderId LIMIT 1")
    Order getOrder(int orderId);

    @Query("SELECT * FROM " + TABLE_ORDER + " WHERE " + TABLE_ORDER_COL_CLIENTID + " = :clientId ORDER BY " + TABLE_ORDER_COL_ID)
    LiveData<List<Order>> getRelatedOrders(int clientId);

    @Query("SELECT * FROM " + TABLE_ORDER + " ORDER BY " + TABLE_ORDER_COL_ID)
    LiveData<List<Order>> getAllOrders();

    @Query("SELECT * FROM " + TABLE_ORDER + " WHERE " + TABLE_ORDER_COL_NAME + " LIKE ''%:orderSearch%'' OR " + TABLE_ORDER_COL_DESCRIPTION + " LIKE ''%:orderSearch%''")
    LiveData<List<Order>> getSearchedOrders(String orderSearch);
}
