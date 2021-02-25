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
import com.example.customerordertracker.Entities.Order;
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
public final class OrderDAO_Impl implements OrderDAO {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<Order> __insertionAdapterOfOrder;

  private final EntityDeletionOrUpdateAdapter<Order> __deletionAdapterOfOrder;

  private final EntityDeletionOrUpdateAdapter<Order> __updateAdapterOfOrder;

  private final SharedSQLiteStatement __preparedStmtOfDeleteAllOrders;

  public OrderDAO_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfOrder = new EntityInsertionAdapter<Order>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR REPLACE INTO `orders` (`orderID`,`name`,`date_ordered`,`date_due`,`delivery_type`,`reference_art_location`,`description`,`address`,`city`,`state`,`zip`,`clientID`) VALUES (nullif(?, 0),?,?,?,?,?,?,?,?,?,?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, Order value) {
        stmt.bindLong(1, value.orderID);
        if (value.name == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.name);
        }
        final Long _tmp;
        _tmp = Converters.toTimestamp(value.getDateOrdered());
        if (_tmp == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindLong(3, _tmp);
        }
        final Long _tmp_1;
        _tmp_1 = Converters.toTimestamp(value.getDateDue());
        if (_tmp_1 == null) {
          stmt.bindNull(4);
        } else {
          stmt.bindLong(4, _tmp_1);
        }
        if (value.getDeliveryType() == null) {
          stmt.bindNull(5);
        } else {
          stmt.bindString(5, value.getDeliveryType());
        }
        if (value.getRefArtLoc() == null) {
          stmt.bindNull(6);
        } else {
          stmt.bindString(6, value.getRefArtLoc());
        }
        if (value.getDescription() == null) {
          stmt.bindNull(7);
        } else {
          stmt.bindString(7, value.getDescription());
        }
        if (value.getAddress() == null) {
          stmt.bindNull(8);
        } else {
          stmt.bindString(8, value.getAddress());
        }
        if (value.getCity() == null) {
          stmt.bindNull(9);
        } else {
          stmt.bindString(9, value.getCity());
        }
        if (value.getState() == null) {
          stmt.bindNull(10);
        } else {
          stmt.bindString(10, value.getState());
        }
        if (value.getZip() == null) {
          stmt.bindNull(11);
        } else {
          stmt.bindString(11, value.getZip());
        }
        stmt.bindLong(12, value.clientID);
      }
    };
    this.__deletionAdapterOfOrder = new EntityDeletionOrUpdateAdapter<Order>(__db) {
      @Override
      public String createQuery() {
        return "DELETE FROM `orders` WHERE `orderID` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, Order value) {
        stmt.bindLong(1, value.orderID);
      }
    };
    this.__updateAdapterOfOrder = new EntityDeletionOrUpdateAdapter<Order>(__db) {
      @Override
      public String createQuery() {
        return "UPDATE OR REPLACE `orders` SET `orderID` = ?,`name` = ?,`date_ordered` = ?,`date_due` = ?,`delivery_type` = ?,`reference_art_location` = ?,`description` = ?,`address` = ?,`city` = ?,`state` = ?,`zip` = ?,`clientID` = ? WHERE `orderID` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, Order value) {
        stmt.bindLong(1, value.orderID);
        if (value.name == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.name);
        }
        final Long _tmp;
        _tmp = Converters.toTimestamp(value.getDateOrdered());
        if (_tmp == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindLong(3, _tmp);
        }
        final Long _tmp_1;
        _tmp_1 = Converters.toTimestamp(value.getDateDue());
        if (_tmp_1 == null) {
          stmt.bindNull(4);
        } else {
          stmt.bindLong(4, _tmp_1);
        }
        if (value.getDeliveryType() == null) {
          stmt.bindNull(5);
        } else {
          stmt.bindString(5, value.getDeliveryType());
        }
        if (value.getRefArtLoc() == null) {
          stmt.bindNull(6);
        } else {
          stmt.bindString(6, value.getRefArtLoc());
        }
        if (value.getDescription() == null) {
          stmt.bindNull(7);
        } else {
          stmt.bindString(7, value.getDescription());
        }
        if (value.getAddress() == null) {
          stmt.bindNull(8);
        } else {
          stmt.bindString(8, value.getAddress());
        }
        if (value.getCity() == null) {
          stmt.bindNull(9);
        } else {
          stmt.bindString(9, value.getCity());
        }
        if (value.getState() == null) {
          stmt.bindNull(10);
        } else {
          stmt.bindString(10, value.getState());
        }
        if (value.getZip() == null) {
          stmt.bindNull(11);
        } else {
          stmt.bindString(11, value.getZip());
        }
        stmt.bindLong(12, value.clientID);
        stmt.bindLong(13, value.orderID);
      }
    };
    this.__preparedStmtOfDeleteAllOrders = new SharedSQLiteStatement(__db) {
      @Override
      public String createQuery() {
        final String _query = "DELETE FROM orders";
        return _query;
      }
    };
  }

  @Override
  public void insertOrder(final Order order) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __insertionAdapterOfOrder.insert(order);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void deleteOrder(final Order order) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __deletionAdapterOfOrder.handle(order);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void updateOrder(final Order order) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __updateAdapterOfOrder.handle(order);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public int deleteAllOrders() {
    __db.assertNotSuspendingTransaction();
    final SupportSQLiteStatement _stmt = __preparedStmtOfDeleteAllOrders.acquire();
    __db.beginTransaction();
    try {
      final int _result = _stmt.executeUpdateDelete();
      __db.setTransactionSuccessful();
      return _result;
    } finally {
      __db.endTransaction();
      __preparedStmtOfDeleteAllOrders.release(_stmt);
    }
  }

  @Override
  public Order getOrder(final int orderId) {
    final String _sql = "SELECT * FROM orders WHERE orderID = ? LIMIT 1";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, orderId);
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfOrderID = CursorUtil.getColumnIndexOrThrow(_cursor, "orderID");
      final int _cursorIndexOfName = CursorUtil.getColumnIndexOrThrow(_cursor, "name");
      final int _cursorIndexOfDateOrdered = CursorUtil.getColumnIndexOrThrow(_cursor, "date_ordered");
      final int _cursorIndexOfDateDue = CursorUtil.getColumnIndexOrThrow(_cursor, "date_due");
      final int _cursorIndexOfDeliveryType = CursorUtil.getColumnIndexOrThrow(_cursor, "delivery_type");
      final int _cursorIndexOfRefArtLoc = CursorUtil.getColumnIndexOrThrow(_cursor, "reference_art_location");
      final int _cursorIndexOfDescription = CursorUtil.getColumnIndexOrThrow(_cursor, "description");
      final int _cursorIndexOfAddress = CursorUtil.getColumnIndexOrThrow(_cursor, "address");
      final int _cursorIndexOfCity = CursorUtil.getColumnIndexOrThrow(_cursor, "city");
      final int _cursorIndexOfState = CursorUtil.getColumnIndexOrThrow(_cursor, "state");
      final int _cursorIndexOfZip = CursorUtil.getColumnIndexOrThrow(_cursor, "zip");
      final int _cursorIndexOfClientID = CursorUtil.getColumnIndexOrThrow(_cursor, "clientID");
      final Order _result;
      if(_cursor.moveToFirst()) {
        final int _tmpOrderID;
        _tmpOrderID = _cursor.getInt(_cursorIndexOfOrderID);
        final String _tmpName;
        _tmpName = _cursor.getString(_cursorIndexOfName);
        final Date _tmpDateOrdered;
        final Long _tmp;
        if (_cursor.isNull(_cursorIndexOfDateOrdered)) {
          _tmp = null;
        } else {
          _tmp = _cursor.getLong(_cursorIndexOfDateOrdered);
        }
        _tmpDateOrdered = Converters.toDate(_tmp);
        final Date _tmpDateDue;
        final Long _tmp_1;
        if (_cursor.isNull(_cursorIndexOfDateDue)) {
          _tmp_1 = null;
        } else {
          _tmp_1 = _cursor.getLong(_cursorIndexOfDateDue);
        }
        _tmpDateDue = Converters.toDate(_tmp_1);
        final String _tmpDeliveryType;
        _tmpDeliveryType = _cursor.getString(_cursorIndexOfDeliveryType);
        final String _tmpRefArtLoc;
        _tmpRefArtLoc = _cursor.getString(_cursorIndexOfRefArtLoc);
        final String _tmpDescription;
        _tmpDescription = _cursor.getString(_cursorIndexOfDescription);
        final String _tmpAddress;
        _tmpAddress = _cursor.getString(_cursorIndexOfAddress);
        final String _tmpCity;
        _tmpCity = _cursor.getString(_cursorIndexOfCity);
        final String _tmpState;
        _tmpState = _cursor.getString(_cursorIndexOfState);
        final String _tmpZip;
        _tmpZip = _cursor.getString(_cursorIndexOfZip);
        final int _tmpClientID;
        _tmpClientID = _cursor.getInt(_cursorIndexOfClientID);
        _result = new Order(_tmpOrderID,_tmpName,_tmpDateOrdered,_tmpDateDue,_tmpDeliveryType,_tmpRefArtLoc,_tmpDescription,_tmpAddress,_tmpCity,_tmpState,_tmpZip,_tmpClientID);
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
  public LiveData<List<Order>> getRelatedOrders(final int clientId) {
    final String _sql = "SELECT * FROM orders WHERE clientID = ? ORDER BY orderID";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, clientId);
    return __db.getInvalidationTracker().createLiveData(new String[]{"orders"}, false, new Callable<List<Order>>() {
      @Override
      public List<Order> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfOrderID = CursorUtil.getColumnIndexOrThrow(_cursor, "orderID");
          final int _cursorIndexOfName = CursorUtil.getColumnIndexOrThrow(_cursor, "name");
          final int _cursorIndexOfDateOrdered = CursorUtil.getColumnIndexOrThrow(_cursor, "date_ordered");
          final int _cursorIndexOfDateDue = CursorUtil.getColumnIndexOrThrow(_cursor, "date_due");
          final int _cursorIndexOfDeliveryType = CursorUtil.getColumnIndexOrThrow(_cursor, "delivery_type");
          final int _cursorIndexOfRefArtLoc = CursorUtil.getColumnIndexOrThrow(_cursor, "reference_art_location");
          final int _cursorIndexOfDescription = CursorUtil.getColumnIndexOrThrow(_cursor, "description");
          final int _cursorIndexOfAddress = CursorUtil.getColumnIndexOrThrow(_cursor, "address");
          final int _cursorIndexOfCity = CursorUtil.getColumnIndexOrThrow(_cursor, "city");
          final int _cursorIndexOfState = CursorUtil.getColumnIndexOrThrow(_cursor, "state");
          final int _cursorIndexOfZip = CursorUtil.getColumnIndexOrThrow(_cursor, "zip");
          final int _cursorIndexOfClientID = CursorUtil.getColumnIndexOrThrow(_cursor, "clientID");
          final List<Order> _result = new ArrayList<Order>(_cursor.getCount());
          while(_cursor.moveToNext()) {
            final Order _item;
            final int _tmpOrderID;
            _tmpOrderID = _cursor.getInt(_cursorIndexOfOrderID);
            final String _tmpName;
            _tmpName = _cursor.getString(_cursorIndexOfName);
            final Date _tmpDateOrdered;
            final Long _tmp;
            if (_cursor.isNull(_cursorIndexOfDateOrdered)) {
              _tmp = null;
            } else {
              _tmp = _cursor.getLong(_cursorIndexOfDateOrdered);
            }
            _tmpDateOrdered = Converters.toDate(_tmp);
            final Date _tmpDateDue;
            final Long _tmp_1;
            if (_cursor.isNull(_cursorIndexOfDateDue)) {
              _tmp_1 = null;
            } else {
              _tmp_1 = _cursor.getLong(_cursorIndexOfDateDue);
            }
            _tmpDateDue = Converters.toDate(_tmp_1);
            final String _tmpDeliveryType;
            _tmpDeliveryType = _cursor.getString(_cursorIndexOfDeliveryType);
            final String _tmpRefArtLoc;
            _tmpRefArtLoc = _cursor.getString(_cursorIndexOfRefArtLoc);
            final String _tmpDescription;
            _tmpDescription = _cursor.getString(_cursorIndexOfDescription);
            final String _tmpAddress;
            _tmpAddress = _cursor.getString(_cursorIndexOfAddress);
            final String _tmpCity;
            _tmpCity = _cursor.getString(_cursorIndexOfCity);
            final String _tmpState;
            _tmpState = _cursor.getString(_cursorIndexOfState);
            final String _tmpZip;
            _tmpZip = _cursor.getString(_cursorIndexOfZip);
            final int _tmpClientID;
            _tmpClientID = _cursor.getInt(_cursorIndexOfClientID);
            _item = new Order(_tmpOrderID,_tmpName,_tmpDateOrdered,_tmpDateDue,_tmpDeliveryType,_tmpRefArtLoc,_tmpDescription,_tmpAddress,_tmpCity,_tmpState,_tmpZip,_tmpClientID);
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
  public LiveData<List<Order>> getAllOrders() {
    final String _sql = "SELECT * FROM orders ORDER BY orderID";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return __db.getInvalidationTracker().createLiveData(new String[]{"orders"}, false, new Callable<List<Order>>() {
      @Override
      public List<Order> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfOrderID = CursorUtil.getColumnIndexOrThrow(_cursor, "orderID");
          final int _cursorIndexOfName = CursorUtil.getColumnIndexOrThrow(_cursor, "name");
          final int _cursorIndexOfDateOrdered = CursorUtil.getColumnIndexOrThrow(_cursor, "date_ordered");
          final int _cursorIndexOfDateDue = CursorUtil.getColumnIndexOrThrow(_cursor, "date_due");
          final int _cursorIndexOfDeliveryType = CursorUtil.getColumnIndexOrThrow(_cursor, "delivery_type");
          final int _cursorIndexOfRefArtLoc = CursorUtil.getColumnIndexOrThrow(_cursor, "reference_art_location");
          final int _cursorIndexOfDescription = CursorUtil.getColumnIndexOrThrow(_cursor, "description");
          final int _cursorIndexOfAddress = CursorUtil.getColumnIndexOrThrow(_cursor, "address");
          final int _cursorIndexOfCity = CursorUtil.getColumnIndexOrThrow(_cursor, "city");
          final int _cursorIndexOfState = CursorUtil.getColumnIndexOrThrow(_cursor, "state");
          final int _cursorIndexOfZip = CursorUtil.getColumnIndexOrThrow(_cursor, "zip");
          final int _cursorIndexOfClientID = CursorUtil.getColumnIndexOrThrow(_cursor, "clientID");
          final List<Order> _result = new ArrayList<Order>(_cursor.getCount());
          while(_cursor.moveToNext()) {
            final Order _item;
            final int _tmpOrderID;
            _tmpOrderID = _cursor.getInt(_cursorIndexOfOrderID);
            final String _tmpName;
            _tmpName = _cursor.getString(_cursorIndexOfName);
            final Date _tmpDateOrdered;
            final Long _tmp;
            if (_cursor.isNull(_cursorIndexOfDateOrdered)) {
              _tmp = null;
            } else {
              _tmp = _cursor.getLong(_cursorIndexOfDateOrdered);
            }
            _tmpDateOrdered = Converters.toDate(_tmp);
            final Date _tmpDateDue;
            final Long _tmp_1;
            if (_cursor.isNull(_cursorIndexOfDateDue)) {
              _tmp_1 = null;
            } else {
              _tmp_1 = _cursor.getLong(_cursorIndexOfDateDue);
            }
            _tmpDateDue = Converters.toDate(_tmp_1);
            final String _tmpDeliveryType;
            _tmpDeliveryType = _cursor.getString(_cursorIndexOfDeliveryType);
            final String _tmpRefArtLoc;
            _tmpRefArtLoc = _cursor.getString(_cursorIndexOfRefArtLoc);
            final String _tmpDescription;
            _tmpDescription = _cursor.getString(_cursorIndexOfDescription);
            final String _tmpAddress;
            _tmpAddress = _cursor.getString(_cursorIndexOfAddress);
            final String _tmpCity;
            _tmpCity = _cursor.getString(_cursorIndexOfCity);
            final String _tmpState;
            _tmpState = _cursor.getString(_cursorIndexOfState);
            final String _tmpZip;
            _tmpZip = _cursor.getString(_cursorIndexOfZip);
            final int _tmpClientID;
            _tmpClientID = _cursor.getInt(_cursorIndexOfClientID);
            _item = new Order(_tmpOrderID,_tmpName,_tmpDateOrdered,_tmpDateDue,_tmpDeliveryType,_tmpRefArtLoc,_tmpDescription,_tmpAddress,_tmpCity,_tmpState,_tmpZip,_tmpClientID);
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
  public LiveData<List<Order>> getSearchedOrders(final String orderSearch) {
    final String _sql = "SELECT * FROM orders WHERE name LIKE ''%?%'' OR description LIKE ''%?%''";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 2);
    int _argIndex = 1;
    if (orderSearch == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, orderSearch);
    }
    _argIndex = 2;
    if (orderSearch == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, orderSearch);
    }
    return __db.getInvalidationTracker().createLiveData(new String[]{"orders"}, false, new Callable<List<Order>>() {
      @Override
      public List<Order> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfOrderID = CursorUtil.getColumnIndexOrThrow(_cursor, "orderID");
          final int _cursorIndexOfName = CursorUtil.getColumnIndexOrThrow(_cursor, "name");
          final int _cursorIndexOfDateOrdered = CursorUtil.getColumnIndexOrThrow(_cursor, "date_ordered");
          final int _cursorIndexOfDateDue = CursorUtil.getColumnIndexOrThrow(_cursor, "date_due");
          final int _cursorIndexOfDeliveryType = CursorUtil.getColumnIndexOrThrow(_cursor, "delivery_type");
          final int _cursorIndexOfRefArtLoc = CursorUtil.getColumnIndexOrThrow(_cursor, "reference_art_location");
          final int _cursorIndexOfDescription = CursorUtil.getColumnIndexOrThrow(_cursor, "description");
          final int _cursorIndexOfAddress = CursorUtil.getColumnIndexOrThrow(_cursor, "address");
          final int _cursorIndexOfCity = CursorUtil.getColumnIndexOrThrow(_cursor, "city");
          final int _cursorIndexOfState = CursorUtil.getColumnIndexOrThrow(_cursor, "state");
          final int _cursorIndexOfZip = CursorUtil.getColumnIndexOrThrow(_cursor, "zip");
          final int _cursorIndexOfClientID = CursorUtil.getColumnIndexOrThrow(_cursor, "clientID");
          final List<Order> _result = new ArrayList<Order>(_cursor.getCount());
          while(_cursor.moveToNext()) {
            final Order _item;
            final int _tmpOrderID;
            _tmpOrderID = _cursor.getInt(_cursorIndexOfOrderID);
            final String _tmpName;
            _tmpName = _cursor.getString(_cursorIndexOfName);
            final Date _tmpDateOrdered;
            final Long _tmp;
            if (_cursor.isNull(_cursorIndexOfDateOrdered)) {
              _tmp = null;
            } else {
              _tmp = _cursor.getLong(_cursorIndexOfDateOrdered);
            }
            _tmpDateOrdered = Converters.toDate(_tmp);
            final Date _tmpDateDue;
            final Long _tmp_1;
            if (_cursor.isNull(_cursorIndexOfDateDue)) {
              _tmp_1 = null;
            } else {
              _tmp_1 = _cursor.getLong(_cursorIndexOfDateDue);
            }
            _tmpDateDue = Converters.toDate(_tmp_1);
            final String _tmpDeliveryType;
            _tmpDeliveryType = _cursor.getString(_cursorIndexOfDeliveryType);
            final String _tmpRefArtLoc;
            _tmpRefArtLoc = _cursor.getString(_cursorIndexOfRefArtLoc);
            final String _tmpDescription;
            _tmpDescription = _cursor.getString(_cursorIndexOfDescription);
            final String _tmpAddress;
            _tmpAddress = _cursor.getString(_cursorIndexOfAddress);
            final String _tmpCity;
            _tmpCity = _cursor.getString(_cursorIndexOfCity);
            final String _tmpState;
            _tmpState = _cursor.getString(_cursorIndexOfState);
            final String _tmpZip;
            _tmpZip = _cursor.getString(_cursorIndexOfZip);
            final int _tmpClientID;
            _tmpClientID = _cursor.getInt(_cursorIndexOfClientID);
            _item = new Order(_tmpOrderID,_tmpName,_tmpDateOrdered,_tmpDateDue,_tmpDeliveryType,_tmpRefArtLoc,_tmpDescription,_tmpAddress,_tmpCity,_tmpState,_tmpZip,_tmpClientID);
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
