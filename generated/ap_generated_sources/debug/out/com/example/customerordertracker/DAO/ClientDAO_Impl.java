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
import com.example.customerordertracker.Entities.Client;
import java.lang.Exception;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

@SuppressWarnings({"unchecked", "deprecation"})
public final class ClientDAO_Impl implements ClientDAO {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<Client> __insertionAdapterOfClient;

  private final EntityDeletionOrUpdateAdapter<Client> __deletionAdapterOfClient;

  private final EntityDeletionOrUpdateAdapter<Client> __updateAdapterOfClient;

  private final SharedSQLiteStatement __preparedStmtOfDeleteAllClients;

  public ClientDAO_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfClient = new EntityInsertionAdapter<Client>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR REPLACE INTO `clients` (`clientID`,`name`,`address`,`city`,`state`,`zip`,`phone`) VALUES (nullif(?, 0),?,?,?,?,?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, Client value) {
        stmt.bindLong(1, value.clientID);
        if (value.getName() == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.getName());
        }
        if (value.getAddress() == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindString(3, value.getAddress());
        }
        if (value.getCity() == null) {
          stmt.bindNull(4);
        } else {
          stmt.bindString(4, value.getCity());
        }
        if (value.getState() == null) {
          stmt.bindNull(5);
        } else {
          stmt.bindString(5, value.getState());
        }
        if (value.getZip() == null) {
          stmt.bindNull(6);
        } else {
          stmt.bindString(6, value.getZip());
        }
        if (value.getPhone() == null) {
          stmt.bindNull(7);
        } else {
          stmt.bindString(7, value.getPhone());
        }
      }
    };
    this.__deletionAdapterOfClient = new EntityDeletionOrUpdateAdapter<Client>(__db) {
      @Override
      public String createQuery() {
        return "DELETE FROM `clients` WHERE `clientID` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, Client value) {
        stmt.bindLong(1, value.clientID);
      }
    };
    this.__updateAdapterOfClient = new EntityDeletionOrUpdateAdapter<Client>(__db) {
      @Override
      public String createQuery() {
        return "UPDATE OR REPLACE `clients` SET `clientID` = ?,`name` = ?,`address` = ?,`city` = ?,`state` = ?,`zip` = ?,`phone` = ? WHERE `clientID` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, Client value) {
        stmt.bindLong(1, value.clientID);
        if (value.getName() == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.getName());
        }
        if (value.getAddress() == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindString(3, value.getAddress());
        }
        if (value.getCity() == null) {
          stmt.bindNull(4);
        } else {
          stmt.bindString(4, value.getCity());
        }
        if (value.getState() == null) {
          stmt.bindNull(5);
        } else {
          stmt.bindString(5, value.getState());
        }
        if (value.getZip() == null) {
          stmt.bindNull(6);
        } else {
          stmt.bindString(6, value.getZip());
        }
        if (value.getPhone() == null) {
          stmt.bindNull(7);
        } else {
          stmt.bindString(7, value.getPhone());
        }
        stmt.bindLong(8, value.clientID);
      }
    };
    this.__preparedStmtOfDeleteAllClients = new SharedSQLiteStatement(__db) {
      @Override
      public String createQuery() {
        final String _query = "DELETE FROM clients";
        return _query;
      }
    };
  }

  @Override
  public void insertClient(final Client client) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __insertionAdapterOfClient.insert(client);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void deleteClient(final Client client) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __deletionAdapterOfClient.handle(client);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void updateClient(final Client client) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __updateAdapterOfClient.handle(client);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public int deleteAllClients() {
    __db.assertNotSuspendingTransaction();
    final SupportSQLiteStatement _stmt = __preparedStmtOfDeleteAllClients.acquire();
    __db.beginTransaction();
    try {
      final int _result = _stmt.executeUpdateDelete();
      __db.setTransactionSuccessful();
      return _result;
    } finally {
      __db.endTransaction();
      __preparedStmtOfDeleteAllClients.release(_stmt);
    }
  }

  @Override
  public Client getClient(final int clientId) {
    final String _sql = "SELECT * FROM clients WHERE clientID = ? LIMIT 1";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, clientId);
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfClientID = CursorUtil.getColumnIndexOrThrow(_cursor, "clientID");
      final int _cursorIndexOfName = CursorUtil.getColumnIndexOrThrow(_cursor, "name");
      final int _cursorIndexOfAddress = CursorUtil.getColumnIndexOrThrow(_cursor, "address");
      final int _cursorIndexOfCity = CursorUtil.getColumnIndexOrThrow(_cursor, "city");
      final int _cursorIndexOfState = CursorUtil.getColumnIndexOrThrow(_cursor, "state");
      final int _cursorIndexOfZip = CursorUtil.getColumnIndexOrThrow(_cursor, "zip");
      final int _cursorIndexOfPhone = CursorUtil.getColumnIndexOrThrow(_cursor, "phone");
      final Client _result;
      if(_cursor.moveToFirst()) {
        final int _tmpClientID;
        _tmpClientID = _cursor.getInt(_cursorIndexOfClientID);
        final String _tmpName;
        _tmpName = _cursor.getString(_cursorIndexOfName);
        final String _tmpAddress;
        _tmpAddress = _cursor.getString(_cursorIndexOfAddress);
        final String _tmpCity;
        _tmpCity = _cursor.getString(_cursorIndexOfCity);
        final String _tmpState;
        _tmpState = _cursor.getString(_cursorIndexOfState);
        final String _tmpZip;
        _tmpZip = _cursor.getString(_cursorIndexOfZip);
        final String _tmpPhone;
        _tmpPhone = _cursor.getString(_cursorIndexOfPhone);
        _result = new Client(_tmpClientID,_tmpName,_tmpAddress,_tmpCity,_tmpState,_tmpZip,_tmpPhone);
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
  public LiveData<List<Client>> getAllClients() {
    final String _sql = "SELECT * FROM clients ORDER BY clientID";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return __db.getInvalidationTracker().createLiveData(new String[]{"clients"}, false, new Callable<List<Client>>() {
      @Override
      public List<Client> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfClientID = CursorUtil.getColumnIndexOrThrow(_cursor, "clientID");
          final int _cursorIndexOfName = CursorUtil.getColumnIndexOrThrow(_cursor, "name");
          final int _cursorIndexOfAddress = CursorUtil.getColumnIndexOrThrow(_cursor, "address");
          final int _cursorIndexOfCity = CursorUtil.getColumnIndexOrThrow(_cursor, "city");
          final int _cursorIndexOfState = CursorUtil.getColumnIndexOrThrow(_cursor, "state");
          final int _cursorIndexOfZip = CursorUtil.getColumnIndexOrThrow(_cursor, "zip");
          final int _cursorIndexOfPhone = CursorUtil.getColumnIndexOrThrow(_cursor, "phone");
          final List<Client> _result = new ArrayList<Client>(_cursor.getCount());
          while(_cursor.moveToNext()) {
            final Client _item;
            final int _tmpClientID;
            _tmpClientID = _cursor.getInt(_cursorIndexOfClientID);
            final String _tmpName;
            _tmpName = _cursor.getString(_cursorIndexOfName);
            final String _tmpAddress;
            _tmpAddress = _cursor.getString(_cursorIndexOfAddress);
            final String _tmpCity;
            _tmpCity = _cursor.getString(_cursorIndexOfCity);
            final String _tmpState;
            _tmpState = _cursor.getString(_cursorIndexOfState);
            final String _tmpZip;
            _tmpZip = _cursor.getString(_cursorIndexOfZip);
            final String _tmpPhone;
            _tmpPhone = _cursor.getString(_cursorIndexOfPhone);
            _item = new Client(_tmpClientID,_tmpName,_tmpAddress,_tmpCity,_tmpState,_tmpZip,_tmpPhone);
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
  public LiveData<List<Client>> getSearchedClients(final String clientSearch) {
    final String _sql = "SELECT * FROM clients WHERE name LIKE ''%?%''";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    if (clientSearch == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, clientSearch);
    }
    return __db.getInvalidationTracker().createLiveData(new String[]{"clients"}, false, new Callable<List<Client>>() {
      @Override
      public List<Client> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfClientID = CursorUtil.getColumnIndexOrThrow(_cursor, "clientID");
          final int _cursorIndexOfName = CursorUtil.getColumnIndexOrThrow(_cursor, "name");
          final int _cursorIndexOfAddress = CursorUtil.getColumnIndexOrThrow(_cursor, "address");
          final int _cursorIndexOfCity = CursorUtil.getColumnIndexOrThrow(_cursor, "city");
          final int _cursorIndexOfState = CursorUtil.getColumnIndexOrThrow(_cursor, "state");
          final int _cursorIndexOfZip = CursorUtil.getColumnIndexOrThrow(_cursor, "zip");
          final int _cursorIndexOfPhone = CursorUtil.getColumnIndexOrThrow(_cursor, "phone");
          final List<Client> _result = new ArrayList<Client>(_cursor.getCount());
          while(_cursor.moveToNext()) {
            final Client _item;
            final int _tmpClientID;
            _tmpClientID = _cursor.getInt(_cursorIndexOfClientID);
            final String _tmpName;
            _tmpName = _cursor.getString(_cursorIndexOfName);
            final String _tmpAddress;
            _tmpAddress = _cursor.getString(_cursorIndexOfAddress);
            final String _tmpCity;
            _tmpCity = _cursor.getString(_cursorIndexOfCity);
            final String _tmpState;
            _tmpState = _cursor.getString(_cursorIndexOfState);
            final String _tmpZip;
            _tmpZip = _cursor.getString(_cursorIndexOfZip);
            final String _tmpPhone;
            _tmpPhone = _cursor.getString(_cursorIndexOfPhone);
            _item = new Client(_tmpClientID,_tmpName,_tmpAddress,_tmpCity,_tmpState,_tmpZip,_tmpPhone);
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
