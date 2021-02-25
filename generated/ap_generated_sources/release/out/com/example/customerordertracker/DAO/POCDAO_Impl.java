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
import com.example.customerordertracker.Entities.POC;
import java.lang.Exception;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

@SuppressWarnings({"unchecked", "deprecation"})
public final class POCDAO_Impl implements POCDAO {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<POC> __insertionAdapterOfPOC;

  private final EntityDeletionOrUpdateAdapter<POC> __deletionAdapterOfPOC;

  private final EntityDeletionOrUpdateAdapter<POC> __updateAdapterOfPOC;

  private final SharedSQLiteStatement __preparedStmtOfDeleteAllPOCs;

  public POCDAO_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfPOC = new EntityInsertionAdapter<POC>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR REPLACE INTO `point_of_contacts` (`pocID`,`first_name`,`last_name`,`phone`,`email`,`clientID`) VALUES (nullif(?, 0),?,?,?,?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, POC value) {
        stmt.bindLong(1, value.pocid);
        if (value.getFName() == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.getFName());
        }
        if (value.getLName() == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindString(3, value.getLName());
        }
        if (value.getPhone() == null) {
          stmt.bindNull(4);
        } else {
          stmt.bindString(4, value.getPhone());
        }
        if (value.getEmail() == null) {
          stmt.bindNull(5);
        } else {
          stmt.bindString(5, value.getEmail());
        }
        stmt.bindLong(6, value.clientID);
      }
    };
    this.__deletionAdapterOfPOC = new EntityDeletionOrUpdateAdapter<POC>(__db) {
      @Override
      public String createQuery() {
        return "DELETE FROM `point_of_contacts` WHERE `pocID` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, POC value) {
        stmt.bindLong(1, value.pocid);
      }
    };
    this.__updateAdapterOfPOC = new EntityDeletionOrUpdateAdapter<POC>(__db) {
      @Override
      public String createQuery() {
        return "UPDATE OR REPLACE `point_of_contacts` SET `pocID` = ?,`first_name` = ?,`last_name` = ?,`phone` = ?,`email` = ?,`clientID` = ? WHERE `pocID` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, POC value) {
        stmt.bindLong(1, value.pocid);
        if (value.getFName() == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.getFName());
        }
        if (value.getLName() == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindString(3, value.getLName());
        }
        if (value.getPhone() == null) {
          stmt.bindNull(4);
        } else {
          stmt.bindString(4, value.getPhone());
        }
        if (value.getEmail() == null) {
          stmt.bindNull(5);
        } else {
          stmt.bindString(5, value.getEmail());
        }
        stmt.bindLong(6, value.clientID);
        stmt.bindLong(7, value.pocid);
      }
    };
    this.__preparedStmtOfDeleteAllPOCs = new SharedSQLiteStatement(__db) {
      @Override
      public String createQuery() {
        final String _query = "DELETE FROM point_of_contacts";
        return _query;
      }
    };
  }

  @Override
  public void insertPOC(final POC poc) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __insertionAdapterOfPOC.insert(poc);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void deletePOC(final POC poc) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __deletionAdapterOfPOC.handle(poc);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void updatePOC(final POC poc) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __updateAdapterOfPOC.handle(poc);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public int deleteAllPOCs() {
    __db.assertNotSuspendingTransaction();
    final SupportSQLiteStatement _stmt = __preparedStmtOfDeleteAllPOCs.acquire();
    __db.beginTransaction();
    try {
      final int _result = _stmt.executeUpdateDelete();
      __db.setTransactionSuccessful();
      return _result;
    } finally {
      __db.endTransaction();
      __preparedStmtOfDeleteAllPOCs.release(_stmt);
    }
  }

  @Override
  public POC getPOC(final int POCId) {
    final String _sql = "SELECT * FROM point_of_contacts WHERE pocID = ? LIMIT 1";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, POCId);
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfPocid = CursorUtil.getColumnIndexOrThrow(_cursor, "pocID");
      final int _cursorIndexOfFName = CursorUtil.getColumnIndexOrThrow(_cursor, "first_name");
      final int _cursorIndexOfLName = CursorUtil.getColumnIndexOrThrow(_cursor, "last_name");
      final int _cursorIndexOfPhone = CursorUtil.getColumnIndexOrThrow(_cursor, "phone");
      final int _cursorIndexOfEmail = CursorUtil.getColumnIndexOrThrow(_cursor, "email");
      final int _cursorIndexOfClientID = CursorUtil.getColumnIndexOrThrow(_cursor, "clientID");
      final POC _result;
      if(_cursor.moveToFirst()) {
        final int _tmpPocid;
        _tmpPocid = _cursor.getInt(_cursorIndexOfPocid);
        final String _tmpFName;
        _tmpFName = _cursor.getString(_cursorIndexOfFName);
        final String _tmpLName;
        _tmpLName = _cursor.getString(_cursorIndexOfLName);
        final String _tmpPhone;
        _tmpPhone = _cursor.getString(_cursorIndexOfPhone);
        final String _tmpEmail;
        _tmpEmail = _cursor.getString(_cursorIndexOfEmail);
        final int _tmpClientID;
        _tmpClientID = _cursor.getInt(_cursorIndexOfClientID);
        _result = new POC(_tmpPocid,_tmpFName,_tmpLName,_tmpPhone,_tmpEmail,_tmpClientID);
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
  public LiveData<List<POC>> getRelatedPOCs(final int clientId) {
    final String _sql = "SELECT * FROM point_of_contacts WHERE clientID = ? ORDER BY pocID";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, clientId);
    return __db.getInvalidationTracker().createLiveData(new String[]{"point_of_contacts"}, false, new Callable<List<POC>>() {
      @Override
      public List<POC> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfPocid = CursorUtil.getColumnIndexOrThrow(_cursor, "pocID");
          final int _cursorIndexOfFName = CursorUtil.getColumnIndexOrThrow(_cursor, "first_name");
          final int _cursorIndexOfLName = CursorUtil.getColumnIndexOrThrow(_cursor, "last_name");
          final int _cursorIndexOfPhone = CursorUtil.getColumnIndexOrThrow(_cursor, "phone");
          final int _cursorIndexOfEmail = CursorUtil.getColumnIndexOrThrow(_cursor, "email");
          final int _cursorIndexOfClientID = CursorUtil.getColumnIndexOrThrow(_cursor, "clientID");
          final List<POC> _result = new ArrayList<POC>(_cursor.getCount());
          while(_cursor.moveToNext()) {
            final POC _item;
            final int _tmpPocid;
            _tmpPocid = _cursor.getInt(_cursorIndexOfPocid);
            final String _tmpFName;
            _tmpFName = _cursor.getString(_cursorIndexOfFName);
            final String _tmpLName;
            _tmpLName = _cursor.getString(_cursorIndexOfLName);
            final String _tmpPhone;
            _tmpPhone = _cursor.getString(_cursorIndexOfPhone);
            final String _tmpEmail;
            _tmpEmail = _cursor.getString(_cursorIndexOfEmail);
            final int _tmpClientID;
            _tmpClientID = _cursor.getInt(_cursorIndexOfClientID);
            _item = new POC(_tmpPocid,_tmpFName,_tmpLName,_tmpPhone,_tmpEmail,_tmpClientID);
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
  public LiveData<List<POC>> getAllPOCs() {
    final String _sql = "SELECT * FROM point_of_contacts ORDER BY pocID";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return __db.getInvalidationTracker().createLiveData(new String[]{"point_of_contacts"}, false, new Callable<List<POC>>() {
      @Override
      public List<POC> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfPocid = CursorUtil.getColumnIndexOrThrow(_cursor, "pocID");
          final int _cursorIndexOfFName = CursorUtil.getColumnIndexOrThrow(_cursor, "first_name");
          final int _cursorIndexOfLName = CursorUtil.getColumnIndexOrThrow(_cursor, "last_name");
          final int _cursorIndexOfPhone = CursorUtil.getColumnIndexOrThrow(_cursor, "phone");
          final int _cursorIndexOfEmail = CursorUtil.getColumnIndexOrThrow(_cursor, "email");
          final int _cursorIndexOfClientID = CursorUtil.getColumnIndexOrThrow(_cursor, "clientID");
          final List<POC> _result = new ArrayList<POC>(_cursor.getCount());
          while(_cursor.moveToNext()) {
            final POC _item;
            final int _tmpPocid;
            _tmpPocid = _cursor.getInt(_cursorIndexOfPocid);
            final String _tmpFName;
            _tmpFName = _cursor.getString(_cursorIndexOfFName);
            final String _tmpLName;
            _tmpLName = _cursor.getString(_cursorIndexOfLName);
            final String _tmpPhone;
            _tmpPhone = _cursor.getString(_cursorIndexOfPhone);
            final String _tmpEmail;
            _tmpEmail = _cursor.getString(_cursorIndexOfEmail);
            final int _tmpClientID;
            _tmpClientID = _cursor.getInt(_cursorIndexOfClientID);
            _item = new POC(_tmpPocid,_tmpFName,_tmpLName,_tmpPhone,_tmpEmail,_tmpClientID);
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
