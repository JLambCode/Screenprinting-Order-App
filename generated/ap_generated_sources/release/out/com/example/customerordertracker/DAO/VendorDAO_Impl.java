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
import com.example.customerordertracker.Entities.Vendor;
import java.lang.Exception;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

@SuppressWarnings({"unchecked", "deprecation"})
public final class VendorDAO_Impl implements VendorDAO {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<Vendor> __insertionAdapterOfVendor;

  private final EntityDeletionOrUpdateAdapter<Vendor> __deletionAdapterOfVendor;

  private final EntityDeletionOrUpdateAdapter<Vendor> __updateAdapterOfVendor;

  private final SharedSQLiteStatement __preparedStmtOfDeleteAllVendors;

  public VendorDAO_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfVendor = new EntityInsertionAdapter<Vendor>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR REPLACE INTO `vendors` (`vendorID`,`name`) VALUES (nullif(?, 0),?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, Vendor value) {
        stmt.bindLong(1, value.vendorID);
        if (value.getVendorName() == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.getVendorName());
        }
      }
    };
    this.__deletionAdapterOfVendor = new EntityDeletionOrUpdateAdapter<Vendor>(__db) {
      @Override
      public String createQuery() {
        return "DELETE FROM `vendors` WHERE `vendorID` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, Vendor value) {
        stmt.bindLong(1, value.vendorID);
      }
    };
    this.__updateAdapterOfVendor = new EntityDeletionOrUpdateAdapter<Vendor>(__db) {
      @Override
      public String createQuery() {
        return "UPDATE OR REPLACE `vendors` SET `vendorID` = ?,`name` = ? WHERE `vendorID` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, Vendor value) {
        stmt.bindLong(1, value.vendorID);
        if (value.getVendorName() == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.getVendorName());
        }
        stmt.bindLong(3, value.vendorID);
      }
    };
    this.__preparedStmtOfDeleteAllVendors = new SharedSQLiteStatement(__db) {
      @Override
      public String createQuery() {
        final String _query = "DELETE FROM vendors";
        return _query;
      }
    };
  }

  @Override
  public void insertVendor(final Vendor vendor) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __insertionAdapterOfVendor.insert(vendor);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void deleteVendor(final Vendor vendor) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __deletionAdapterOfVendor.handle(vendor);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void updateVendor(final Vendor vendor) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __updateAdapterOfVendor.handle(vendor);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public int deleteAllVendors() {
    __db.assertNotSuspendingTransaction();
    final SupportSQLiteStatement _stmt = __preparedStmtOfDeleteAllVendors.acquire();
    __db.beginTransaction();
    try {
      final int _result = _stmt.executeUpdateDelete();
      __db.setTransactionSuccessful();
      return _result;
    } finally {
      __db.endTransaction();
      __preparedStmtOfDeleteAllVendors.release(_stmt);
    }
  }

  @Override
  public Vendor getVendor(final int vendorId) {
    final String _sql = "SELECT * FROM vendors WHERE vendorID = ? LIMIT 1";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, vendorId);
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfVendorID = CursorUtil.getColumnIndexOrThrow(_cursor, "vendorID");
      final int _cursorIndexOfVendorName = CursorUtil.getColumnIndexOrThrow(_cursor, "name");
      final Vendor _result;
      if(_cursor.moveToFirst()) {
        final int _tmpVendorID;
        _tmpVendorID = _cursor.getInt(_cursorIndexOfVendorID);
        final String _tmpVendorName;
        _tmpVendorName = _cursor.getString(_cursorIndexOfVendorName);
        _result = new Vendor(_tmpVendorID,_tmpVendorName);
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
  public LiveData<List<Vendor>> getAllVendors() {
    final String _sql = "SELECT * FROM vendors ORDER BY vendorID";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return __db.getInvalidationTracker().createLiveData(new String[]{"vendors"}, false, new Callable<List<Vendor>>() {
      @Override
      public List<Vendor> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfVendorID = CursorUtil.getColumnIndexOrThrow(_cursor, "vendorID");
          final int _cursorIndexOfVendorName = CursorUtil.getColumnIndexOrThrow(_cursor, "name");
          final List<Vendor> _result = new ArrayList<Vendor>(_cursor.getCount());
          while(_cursor.moveToNext()) {
            final Vendor _item;
            final int _tmpVendorID;
            _tmpVendorID = _cursor.getInt(_cursorIndexOfVendorID);
            final String _tmpVendorName;
            _tmpVendorName = _cursor.getString(_cursorIndexOfVendorName);
            _item = new Vendor(_tmpVendorID,_tmpVendorName);
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
  public int getVendorCount() {
    final String _sql = "SELECT COUNT(*) FROM vendors";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _result;
      if(_cursor.moveToFirst()) {
        _result = _cursor.getInt(0);
      } else {
        _result = 0;
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }
}
