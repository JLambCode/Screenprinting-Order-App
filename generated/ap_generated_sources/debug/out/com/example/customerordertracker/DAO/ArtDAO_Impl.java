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
import com.example.customerordertracker.Entities.Art;
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
public final class ArtDAO_Impl implements ArtDAO {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<Art> __insertionAdapterOfArt;

  private final EntityDeletionOrUpdateAdapter<Art> __deletionAdapterOfArt;

  private final EntityDeletionOrUpdateAdapter<Art> __updateAdapterOfArt;

  private final SharedSQLiteStatement __preparedStmtOfDeleteAllArt;

  public ArtDAO_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfArt = new EntityInsertionAdapter<Art>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR REPLACE INTO `art` (`artID`,`name`,`art_loc`,`number_of_colors`,`placement`,`last_updated`,`last_updated_by`,`orderID`) VALUES (nullif(?, 0),?,?,?,?,?,?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, Art value) {
        stmt.bindLong(1, value.artID);
        if (value.getName() == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.getName());
        }
        if (value.getArtLoc() == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindString(3, value.getArtLoc());
        }
        if (value.getNumberOfColors() == null) {
          stmt.bindNull(4);
        } else {
          stmt.bindString(4, value.getNumberOfColors());
        }
        if (value.getPlacement() == null) {
          stmt.bindNull(5);
        } else {
          stmt.bindString(5, value.getPlacement());
        }
        final Long _tmp;
        _tmp = Converters.toTimestamp(value.getLastUpdated());
        if (_tmp == null) {
          stmt.bindNull(6);
        } else {
          stmt.bindLong(6, _tmp);
        }
        if (value.getLastUpdatedBy() == null) {
          stmt.bindNull(7);
        } else {
          stmt.bindString(7, value.getLastUpdatedBy());
        }
        stmt.bindLong(8, value.orderID);
      }
    };
    this.__deletionAdapterOfArt = new EntityDeletionOrUpdateAdapter<Art>(__db) {
      @Override
      public String createQuery() {
        return "DELETE FROM `art` WHERE `artID` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, Art value) {
        stmt.bindLong(1, value.artID);
      }
    };
    this.__updateAdapterOfArt = new EntityDeletionOrUpdateAdapter<Art>(__db) {
      @Override
      public String createQuery() {
        return "UPDATE OR REPLACE `art` SET `artID` = ?,`name` = ?,`art_loc` = ?,`number_of_colors` = ?,`placement` = ?,`last_updated` = ?,`last_updated_by` = ?,`orderID` = ? WHERE `artID` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, Art value) {
        stmt.bindLong(1, value.artID);
        if (value.getName() == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.getName());
        }
        if (value.getArtLoc() == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindString(3, value.getArtLoc());
        }
        if (value.getNumberOfColors() == null) {
          stmt.bindNull(4);
        } else {
          stmt.bindString(4, value.getNumberOfColors());
        }
        if (value.getPlacement() == null) {
          stmt.bindNull(5);
        } else {
          stmt.bindString(5, value.getPlacement());
        }
        final Long _tmp;
        _tmp = Converters.toTimestamp(value.getLastUpdated());
        if (_tmp == null) {
          stmt.bindNull(6);
        } else {
          stmt.bindLong(6, _tmp);
        }
        if (value.getLastUpdatedBy() == null) {
          stmt.bindNull(7);
        } else {
          stmt.bindString(7, value.getLastUpdatedBy());
        }
        stmt.bindLong(8, value.orderID);
        stmt.bindLong(9, value.artID);
      }
    };
    this.__preparedStmtOfDeleteAllArt = new SharedSQLiteStatement(__db) {
      @Override
      public String createQuery() {
        final String _query = "DELETE FROM art";
        return _query;
      }
    };
  }

  @Override
  public void insertArt(final Art art) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __insertionAdapterOfArt.insert(art);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void deleteArt(final Art art) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __deletionAdapterOfArt.handle(art);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void updateArt(final Art art) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __updateAdapterOfArt.handle(art);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public int deleteAllArt() {
    __db.assertNotSuspendingTransaction();
    final SupportSQLiteStatement _stmt = __preparedStmtOfDeleteAllArt.acquire();
    __db.beginTransaction();
    try {
      final int _result = _stmt.executeUpdateDelete();
      __db.setTransactionSuccessful();
      return _result;
    } finally {
      __db.endTransaction();
      __preparedStmtOfDeleteAllArt.release(_stmt);
    }
  }

  @Override
  public Art getArt(final int artId) {
    final String _sql = "SELECT * FROM art WHERE artID = ? LIMIT 1";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, artId);
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfArtID = CursorUtil.getColumnIndexOrThrow(_cursor, "artID");
      final int _cursorIndexOfName = CursorUtil.getColumnIndexOrThrow(_cursor, "name");
      final int _cursorIndexOfArtLoc = CursorUtil.getColumnIndexOrThrow(_cursor, "art_loc");
      final int _cursorIndexOfNumberOfColors = CursorUtil.getColumnIndexOrThrow(_cursor, "number_of_colors");
      final int _cursorIndexOfPlacement = CursorUtil.getColumnIndexOrThrow(_cursor, "placement");
      final int _cursorIndexOfLastUpdated = CursorUtil.getColumnIndexOrThrow(_cursor, "last_updated");
      final int _cursorIndexOfLastUpdatedBy = CursorUtil.getColumnIndexOrThrow(_cursor, "last_updated_by");
      final int _cursorIndexOfOrderID = CursorUtil.getColumnIndexOrThrow(_cursor, "orderID");
      final Art _result;
      if(_cursor.moveToFirst()) {
        final int _tmpArtID;
        _tmpArtID = _cursor.getInt(_cursorIndexOfArtID);
        final String _tmpName;
        _tmpName = _cursor.getString(_cursorIndexOfName);
        final String _tmpArtLoc;
        _tmpArtLoc = _cursor.getString(_cursorIndexOfArtLoc);
        final String _tmpNumberOfColors;
        _tmpNumberOfColors = _cursor.getString(_cursorIndexOfNumberOfColors);
        final String _tmpPlacement;
        _tmpPlacement = _cursor.getString(_cursorIndexOfPlacement);
        final int _tmpOrderID;
        _tmpOrderID = _cursor.getInt(_cursorIndexOfOrderID);
        _result = new Art(_tmpArtID,_tmpName,_tmpArtLoc,_tmpNumberOfColors,_tmpPlacement,_tmpOrderID);
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
  public LiveData<List<Art>> getRelatedArt(final int orderId) {
    final String _sql = "SELECT * FROM art WHERE orderID = ? ORDER BY artID";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, orderId);
    return __db.getInvalidationTracker().createLiveData(new String[]{"art"}, false, new Callable<List<Art>>() {
      @Override
      public List<Art> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfArtID = CursorUtil.getColumnIndexOrThrow(_cursor, "artID");
          final int _cursorIndexOfName = CursorUtil.getColumnIndexOrThrow(_cursor, "name");
          final int _cursorIndexOfArtLoc = CursorUtil.getColumnIndexOrThrow(_cursor, "art_loc");
          final int _cursorIndexOfNumberOfColors = CursorUtil.getColumnIndexOrThrow(_cursor, "number_of_colors");
          final int _cursorIndexOfPlacement = CursorUtil.getColumnIndexOrThrow(_cursor, "placement");
          final int _cursorIndexOfLastUpdated = CursorUtil.getColumnIndexOrThrow(_cursor, "last_updated");
          final int _cursorIndexOfLastUpdatedBy = CursorUtil.getColumnIndexOrThrow(_cursor, "last_updated_by");
          final int _cursorIndexOfOrderID = CursorUtil.getColumnIndexOrThrow(_cursor, "orderID");
          final List<Art> _result = new ArrayList<Art>(_cursor.getCount());
          while(_cursor.moveToNext()) {
            final Art _item;
            final int _tmpArtID;
            _tmpArtID = _cursor.getInt(_cursorIndexOfArtID);
            final String _tmpName;
            _tmpName = _cursor.getString(_cursorIndexOfName);
            final String _tmpArtLoc;
            _tmpArtLoc = _cursor.getString(_cursorIndexOfArtLoc);
            final String _tmpNumberOfColors;
            _tmpNumberOfColors = _cursor.getString(_cursorIndexOfNumberOfColors);
            final String _tmpPlacement;
            _tmpPlacement = _cursor.getString(_cursorIndexOfPlacement);
            final int _tmpOrderID;
            _tmpOrderID = _cursor.getInt(_cursorIndexOfOrderID);
            _item = new Art(_tmpArtID,_tmpName,_tmpArtLoc,_tmpNumberOfColors,_tmpPlacement,_tmpOrderID);
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
  public LiveData<List<Art>> getAllArt() {
    final String _sql = "SELECT * FROM art ORDER BY artID";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return __db.getInvalidationTracker().createLiveData(new String[]{"art"}, false, new Callable<List<Art>>() {
      @Override
      public List<Art> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfArtID = CursorUtil.getColumnIndexOrThrow(_cursor, "artID");
          final int _cursorIndexOfName = CursorUtil.getColumnIndexOrThrow(_cursor, "name");
          final int _cursorIndexOfArtLoc = CursorUtil.getColumnIndexOrThrow(_cursor, "art_loc");
          final int _cursorIndexOfNumberOfColors = CursorUtil.getColumnIndexOrThrow(_cursor, "number_of_colors");
          final int _cursorIndexOfPlacement = CursorUtil.getColumnIndexOrThrow(_cursor, "placement");
          final int _cursorIndexOfLastUpdated = CursorUtil.getColumnIndexOrThrow(_cursor, "last_updated");
          final int _cursorIndexOfLastUpdatedBy = CursorUtil.getColumnIndexOrThrow(_cursor, "last_updated_by");
          final int _cursorIndexOfOrderID = CursorUtil.getColumnIndexOrThrow(_cursor, "orderID");
          final List<Art> _result = new ArrayList<Art>(_cursor.getCount());
          while(_cursor.moveToNext()) {
            final Art _item;
            final int _tmpArtID;
            _tmpArtID = _cursor.getInt(_cursorIndexOfArtID);
            final String _tmpName;
            _tmpName = _cursor.getString(_cursorIndexOfName);
            final String _tmpArtLoc;
            _tmpArtLoc = _cursor.getString(_cursorIndexOfArtLoc);
            final String _tmpNumberOfColors;
            _tmpNumberOfColors = _cursor.getString(_cursorIndexOfNumberOfColors);
            final String _tmpPlacement;
            _tmpPlacement = _cursor.getString(_cursorIndexOfPlacement);
            final int _tmpOrderID;
            _tmpOrderID = _cursor.getInt(_cursorIndexOfOrderID);
            _item = new Art(_tmpArtID,_tmpName,_tmpArtLoc,_tmpNumberOfColors,_tmpPlacement,_tmpOrderID);
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
