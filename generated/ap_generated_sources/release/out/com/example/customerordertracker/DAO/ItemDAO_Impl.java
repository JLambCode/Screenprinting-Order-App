package com.example.customerordertracker.DAO;

import android.database.Cursor;
import androidx.lifecycle.LiveData;
import androidx.room.EntityDeletionOrUpdateAdapter;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.SharedSQLiteStatement;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import com.example.customerordertracker.Entities.Item;
import com.example.customerordertracker.Utilities.Converters;
import java.lang.Exception;
import java.lang.Long;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.Callable;

@SuppressWarnings({"unchecked", "deprecation"})
public final class ItemDAO_Impl implements ItemDAO {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<Item> __insertionAdapterOfItem;

  private final EntityDeletionOrUpdateAdapter<Item> __deletionAdapterOfItem;

  private final EntityDeletionOrUpdateAdapter<Item> __updateAdapterOfItem;

  private final SharedSQLiteStatement __preparedStmtOfDeleteAllItems;

  public ItemDAO_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfItem = new EntityInsertionAdapter<Item>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR REPLACE INTO `items` (`itemID`,`name`,`description`,`quantity`,`adult_or_youth`,`size`,`application`,`color`,`last_updated`,`last_updated_by`,`orderID`,`vendorID`) VALUES (nullif(?, 0),?,?,?,?,?,?,?,?,?,?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, Item value) {
        stmt.bindLong(1, value.itemID);
        if (value.getName() == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.getName());
        }
        if (value.getDescription() == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindString(3, value.getDescription());
        }
        if (value.getQuantity() == null) {
          stmt.bindNull(4);
        } else {
          stmt.bindString(4, value.getQuantity());
        }
        if (value.getAdultOrYouth() == null) {
          stmt.bindNull(5);
        } else {
          stmt.bindString(5, value.getAdultOrYouth());
        }
        if (value.getSize() == null) {
          stmt.bindNull(6);
        } else {
          stmt.bindString(6, value.getSize());
        }
        if (value.getApplication() == null) {
          stmt.bindNull(7);
        } else {
          stmt.bindString(7, value.getApplication());
        }
        if (value.getColor() == null) {
          stmt.bindNull(8);
        } else {
          stmt.bindString(8, value.getColor());
        }
        final Long _tmp;
        _tmp = Converters.toTimestamp(value.getLastUpdated());
        if (_tmp == null) {
          stmt.bindNull(9);
        } else {
          stmt.bindLong(9, _tmp);
        }
        if (value.getLastUpdatedBy() == null) {
          stmt.bindNull(10);
        } else {
          stmt.bindString(10, value.getLastUpdatedBy());
        }
        stmt.bindLong(11, value.orderID);
        stmt.bindLong(12, value.vendorID);
      }
    };
    this.__deletionAdapterOfItem = new EntityDeletionOrUpdateAdapter<Item>(__db) {
      @Override
      public String createQuery() {
        return "DELETE FROM `items` WHERE `itemID` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, Item value) {
        stmt.bindLong(1, value.itemID);
      }
    };
    this.__updateAdapterOfItem = new EntityDeletionOrUpdateAdapter<Item>(__db) {
      @Override
      public String createQuery() {
        return "UPDATE OR REPLACE `items` SET `itemID` = ?,`name` = ?,`description` = ?,`quantity` = ?,`adult_or_youth` = ?,`size` = ?,`application` = ?,`color` = ?,`last_updated` = ?,`last_updated_by` = ?,`orderID` = ?,`vendorID` = ? WHERE `itemID` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, Item value) {
        stmt.bindLong(1, value.itemID);
        if (value.getName() == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.getName());
        }
        if (value.getDescription() == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindString(3, value.getDescription());
        }
        if (value.getQuantity() == null) {
          stmt.bindNull(4);
        } else {
          stmt.bindString(4, value.getQuantity());
        }
        if (value.getAdultOrYouth() == null) {
          stmt.bindNull(5);
        } else {
          stmt.bindString(5, value.getAdultOrYouth());
        }
        if (value.getSize() == null) {
          stmt.bindNull(6);
        } else {
          stmt.bindString(6, value.getSize());
        }
        if (value.getApplication() == null) {
          stmt.bindNull(7);
        } else {
          stmt.bindString(7, value.getApplication());
        }
        if (value.getColor() == null) {
          stmt.bindNull(8);
        } else {
          stmt.bindString(8, value.getColor());
        }
        final Long _tmp;
        _tmp = Converters.toTimestamp(value.getLastUpdated());
        if (_tmp == null) {
          stmt.bindNull(9);
        } else {
          stmt.bindLong(9, _tmp);
        }
        if (value.getLastUpdatedBy() == null) {
          stmt.bindNull(10);
        } else {
          stmt.bindString(10, value.getLastUpdatedBy());
        }
        stmt.bindLong(11, value.orderID);
        stmt.bindLong(12, value.vendorID);
        stmt.bindLong(13, value.itemID);
      }
    };
    this.__preparedStmtOfDeleteAllItems = new SharedSQLiteStatement(__db) {
      @Override
      public String createQuery() {
        final String _query = "DELETE FROM items";
        return _query;
      }
    };
  }

  @Override
  public void insertItem(final Item item) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __insertionAdapterOfItem.insert(item);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void deleteItem(final Item item) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __deletionAdapterOfItem.handle(item);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void updateItem(final Item item) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __updateAdapterOfItem.handle(item);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public int deleteAllItems() {
    __db.assertNotSuspendingTransaction();
    final SupportSQLiteStatement _stmt = __preparedStmtOfDeleteAllItems.acquire();
    __db.beginTransaction();
    try {
      final int _result = _stmt.executeUpdateDelete();
      __db.setTransactionSuccessful();
      return _result;
    } finally {
      __db.endTransaction();
      __preparedStmtOfDeleteAllItems.release(_stmt);
    }
  }

  @Override
  public Item getItem(final int itemId) {
    final String _sql = "SELECT * FROM items WHERE itemID = ? LIMIT 1";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, itemId);
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfItemID = CursorUtil.getColumnIndexOrThrow(_cursor, "itemID");
      final int _cursorIndexOfName = CursorUtil.getColumnIndexOrThrow(_cursor, "name");
      final int _cursorIndexOfDescription = CursorUtil.getColumnIndexOrThrow(_cursor, "description");
      final int _cursorIndexOfQuantity = CursorUtil.getColumnIndexOrThrow(_cursor, "quantity");
      final int _cursorIndexOfAdultOrYouth = CursorUtil.getColumnIndexOrThrow(_cursor, "adult_or_youth");
      final int _cursorIndexOfSize = CursorUtil.getColumnIndexOrThrow(_cursor, "size");
      final int _cursorIndexOfApplication = CursorUtil.getColumnIndexOrThrow(_cursor, "application");
      final int _cursorIndexOfColor = CursorUtil.getColumnIndexOrThrow(_cursor, "color");
      final int _cursorIndexOfLastUpdated = CursorUtil.getColumnIndexOrThrow(_cursor, "last_updated");
      final int _cursorIndexOfLastUpdatedBy = CursorUtil.getColumnIndexOrThrow(_cursor, "last_updated_by");
      final int _cursorIndexOfOrderID = CursorUtil.getColumnIndexOrThrow(_cursor, "orderID");
      final int _cursorIndexOfVendorID = CursorUtil.getColumnIndexOrThrow(_cursor, "vendorID");
      final Item _result;
      if(_cursor.moveToFirst()) {
        final int _tmpItemID;
        _tmpItemID = _cursor.getInt(_cursorIndexOfItemID);
        final String _tmpName;
        _tmpName = _cursor.getString(_cursorIndexOfName);
        final String _tmpDescription;
        _tmpDescription = _cursor.getString(_cursorIndexOfDescription);
        final String _tmpQuantity;
        _tmpQuantity = _cursor.getString(_cursorIndexOfQuantity);
        final String _tmpAdultOrYouth;
        _tmpAdultOrYouth = _cursor.getString(_cursorIndexOfAdultOrYouth);
        final String _tmpSize;
        _tmpSize = _cursor.getString(_cursorIndexOfSize);
        final String _tmpApplication;
        _tmpApplication = _cursor.getString(_cursorIndexOfApplication);
        final String _tmpColor;
        _tmpColor = _cursor.getString(_cursorIndexOfColor);
        final int _tmpOrderID;
        _tmpOrderID = _cursor.getInt(_cursorIndexOfOrderID);
        final int _tmpVendorID;
        _tmpVendorID = _cursor.getInt(_cursorIndexOfVendorID);
        _result = new Item(_tmpItemID,_tmpName,_tmpDescription,_tmpQuantity,_tmpAdultOrYouth,_tmpSize,_tmpApplication,_tmpColor,_tmpOrderID,_tmpVendorID);
        final Date _tmpLastUpdated;
        final Long _tmp;
        if (_cursor.isNull(_cursorIndexOfLastUpdated)) {
          _tmp = null;
        } else {
          _tmp = _cursor.getLong(_cursorIndexOfLastUpdated);
        }
        _tmpLastUpdated = Converters.toDate(_tmp);
        _result.setLastUpdated(_tmpLastUpdated);
        final String _tmpLastUpdatedBy;
        _tmpLastUpdatedBy = _cursor.getString(_cursorIndexOfLastUpdatedBy);
        _result.setLastUpdatedBy(_tmpLastUpdatedBy);
      } else {
        _result = null;
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @Override
  public LiveData<List<Item>> getRelatedItems(final int orderId) {
    final String _sql = "SELECT * FROM items WHERE orderID = ? ORDER BY itemID";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, orderId);
    return __db.getInvalidationTracker().createLiveData(new String[]{"items"}, false, new Callable<List<Item>>() {
      @Override
      public List<Item> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfItemID = CursorUtil.getColumnIndexOrThrow(_cursor, "itemID");
          final int _cursorIndexOfName = CursorUtil.getColumnIndexOrThrow(_cursor, "name");
          final int _cursorIndexOfDescription = CursorUtil.getColumnIndexOrThrow(_cursor, "description");
          final int _cursorIndexOfQuantity = CursorUtil.getColumnIndexOrThrow(_cursor, "quantity");
          final int _cursorIndexOfAdultOrYouth = CursorUtil.getColumnIndexOrThrow(_cursor, "adult_or_youth");
          final int _cursorIndexOfSize = CursorUtil.getColumnIndexOrThrow(_cursor, "size");
          final int _cursorIndexOfApplication = CursorUtil.getColumnIndexOrThrow(_cursor, "application");
          final int _cursorIndexOfColor = CursorUtil.getColumnIndexOrThrow(_cursor, "color");
          final int _cursorIndexOfLastUpdated = CursorUtil.getColumnIndexOrThrow(_cursor, "last_updated");
          final int _cursorIndexOfLastUpdatedBy = CursorUtil.getColumnIndexOrThrow(_cursor, "last_updated_by");
          final int _cursorIndexOfOrderID = CursorUtil.getColumnIndexOrThrow(_cursor, "orderID");
          final int _cursorIndexOfVendorID = CursorUtil.getColumnIndexOrThrow(_cursor, "vendorID");
          final List<Item> _result = new ArrayList<Item>(_cursor.getCount());
          while(_cursor.moveToNext()) {
            final Item _item;
            final int _tmpItemID;
            _tmpItemID = _cursor.getInt(_cursorIndexOfItemID);
            final String _tmpName;
            _tmpName = _cursor.getString(_cursorIndexOfName);
            final String _tmpDescription;
            _tmpDescription = _cursor.getString(_cursorIndexOfDescription);
            final String _tmpQuantity;
            _tmpQuantity = _cursor.getString(_cursorIndexOfQuantity);
            final String _tmpAdultOrYouth;
            _tmpAdultOrYouth = _cursor.getString(_cursorIndexOfAdultOrYouth);
            final String _tmpSize;
            _tmpSize = _cursor.getString(_cursorIndexOfSize);
            final String _tmpApplication;
            _tmpApplication = _cursor.getString(_cursorIndexOfApplication);
            final String _tmpColor;
            _tmpColor = _cursor.getString(_cursorIndexOfColor);
            final int _tmpOrderID;
            _tmpOrderID = _cursor.getInt(_cursorIndexOfOrderID);
            final int _tmpVendorID;
            _tmpVendorID = _cursor.getInt(_cursorIndexOfVendorID);
            _item = new Item(_tmpItemID,_tmpName,_tmpDescription,_tmpQuantity,_tmpAdultOrYouth,_tmpSize,_tmpApplication,_tmpColor,_tmpOrderID,_tmpVendorID);
            final Date _tmpLastUpdated;
            final Long _tmp;
            if (_cursor.isNull(_cursorIndexOfLastUpdated)) {
              _tmp = null;
            } else {
              _tmp = _cursor.getLong(_cursorIndexOfLastUpdated);
            }
            _tmpLastUpdated = Converters.toDate(_tmp);
            _item.setLastUpdated(_tmpLastUpdated);
            final String _tmpLastUpdatedBy;
            _tmpLastUpdatedBy = _cursor.getString(_cursorIndexOfLastUpdatedBy);
            _item.setLastUpdatedBy(_tmpLastUpdatedBy);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  @Override
  public LiveData<List<Item>> getAllItems() {
    final String _sql = "SELECT * FROM items ORDER BY itemID";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return __db.getInvalidationTracker().createLiveData(new String[]{"items"}, false, new Callable<List<Item>>() {
      @Override
      public List<Item> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfItemID = CursorUtil.getColumnIndexOrThrow(_cursor, "itemID");
          final int _cursorIndexOfName = CursorUtil.getColumnIndexOrThrow(_cursor, "name");
          final int _cursorIndexOfDescription = CursorUtil.getColumnIndexOrThrow(_cursor, "description");
          final int _cursorIndexOfQuantity = CursorUtil.getColumnIndexOrThrow(_cursor, "quantity");
          final int _cursorIndexOfAdultOrYouth = CursorUtil.getColumnIndexOrThrow(_cursor, "adult_or_youth");
          final int _cursorIndexOfSize = CursorUtil.getColumnIndexOrThrow(_cursor, "size");
          final int _cursorIndexOfApplication = CursorUtil.getColumnIndexOrThrow(_cursor, "application");
          final int _cursorIndexOfColor = CursorUtil.getColumnIndexOrThrow(_cursor, "color");
          final int _cursorIndexOfLastUpdated = CursorUtil.getColumnIndexOrThrow(_cursor, "last_updated");
          final int _cursorIndexOfLastUpdatedBy = CursorUtil.getColumnIndexOrThrow(_cursor, "last_updated_by");
          final int _cursorIndexOfOrderID = CursorUtil.getColumnIndexOrThrow(_cursor, "orderID");
          final int _cursorIndexOfVendorID = CursorUtil.getColumnIndexOrThrow(_cursor, "vendorID");
          final List<Item> _result = new ArrayList<Item>(_cursor.getCount());
          while(_cursor.moveToNext()) {
            final Item _item;
            final int _tmpItemID;
            _tmpItemID = _cursor.getInt(_cursorIndexOfItemID);
            final String _tmpName;
            _tmpName = _cursor.getString(_cursorIndexOfName);
            final String _tmpDescription;
            _tmpDescription = _cursor.getString(_cursorIndexOfDescription);
            final String _tmpQuantity;
            _tmpQuantity = _cursor.getString(_cursorIndexOfQuantity);
            final String _tmpAdultOrYouth;
            _tmpAdultOrYouth = _cursor.getString(_cursorIndexOfAdultOrYouth);
            final String _tmpSize;
            _tmpSize = _cursor.getString(_cursorIndexOfSize);
            final String _tmpApplication;
            _tmpApplication = _cursor.getString(_cursorIndexOfApplication);
            final String _tmpColor;
            _tmpColor = _cursor.getString(_cursorIndexOfColor);
            final int _tmpOrderID;
            _tmpOrderID = _cursor.getInt(_cursorIndexOfOrderID);
            final int _tmpVendorID;
            _tmpVendorID = _cursor.getInt(_cursorIndexOfVendorID);
            _item = new Item(_tmpItemID,_tmpName,_tmpDescription,_tmpQuantity,_tmpAdultOrYouth,_tmpSize,_tmpApplication,_tmpColor,_tmpOrderID,_tmpVendorID);
            final Date _tmpLastUpdated;
            final Long _tmp;
            if (_cursor.isNull(_cursorIndexOfLastUpdated)) {
              _tmp = null;
            } else {
              _tmp = _cursor.getLong(_cursorIndexOfLastUpdated);
            }
            _tmpLastUpdated = Converters.toDate(_tmp);
            _item.setLastUpdated(_tmpLastUpdated);
            final String _tmpLastUpdatedBy;
            _tmpLastUpdatedBy = _cursor.getString(_cursorIndexOfLastUpdatedBy);
            _item.setLastUpdatedBy(_tmpLastUpdatedBy);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }
}
