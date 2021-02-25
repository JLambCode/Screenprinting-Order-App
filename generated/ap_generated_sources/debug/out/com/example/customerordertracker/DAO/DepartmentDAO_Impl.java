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
import com.example.customerordertracker.Entities.Department;
import java.lang.Exception;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

@SuppressWarnings({"unchecked", "deprecation"})
public final class DepartmentDAO_Impl implements DepartmentDAO {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<Department> __insertionAdapterOfDepartment;

  private final EntityDeletionOrUpdateAdapter<Department> __deletionAdapterOfDepartment;

  private final EntityDeletionOrUpdateAdapter<Department> __updateAdapterOfDepartment;

  private final SharedSQLiteStatement __preparedStmtOfDeleteAllDepartments;

  public DepartmentDAO_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfDepartment = new EntityInsertionAdapter<Department>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR REPLACE INTO `departments` (`departmentID`,`name`) VALUES (nullif(?, 0),?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, Department value) {
        stmt.bindLong(1, value.deptID);
        if (value.getName() == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.getName());
        }
      }
    };
    this.__deletionAdapterOfDepartment = new EntityDeletionOrUpdateAdapter<Department>(__db) {
      @Override
      public String createQuery() {
        return "DELETE FROM `departments` WHERE `departmentID` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, Department value) {
        stmt.bindLong(1, value.deptID);
      }
    };
    this.__updateAdapterOfDepartment = new EntityDeletionOrUpdateAdapter<Department>(__db) {
      @Override
      public String createQuery() {
        return "UPDATE OR REPLACE `departments` SET `departmentID` = ?,`name` = ? WHERE `departmentID` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, Department value) {
        stmt.bindLong(1, value.deptID);
        if (value.getName() == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.getName());
        }
        stmt.bindLong(3, value.deptID);
      }
    };
    this.__preparedStmtOfDeleteAllDepartments = new SharedSQLiteStatement(__db) {
      @Override
      public String createQuery() {
        final String _query = "DELETE FROM departments";
        return _query;
      }
    };
  }

  @Override
  public void insertDepartment(final Department department) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __insertionAdapterOfDepartment.insert(department);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void deleteDepartment(final Department department) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __deletionAdapterOfDepartment.handle(department);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void updateDepartment(final Department department) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __updateAdapterOfDepartment.handle(department);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public int deleteAllDepartments() {
    __db.assertNotSuspendingTransaction();
    final SupportSQLiteStatement _stmt = __preparedStmtOfDeleteAllDepartments.acquire();
    __db.beginTransaction();
    try {
      final int _result = _stmt.executeUpdateDelete();
      __db.setTransactionSuccessful();
      return _result;
    } finally {
      __db.endTransaction();
      __preparedStmtOfDeleteAllDepartments.release(_stmt);
    }
  }

  @Override
  public Department getDepartment(final int departmentId) {
    final String _sql = "SELECT * FROM departments WHERE departmentID = ? LIMIT 1";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, departmentId);
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfDeptID = CursorUtil.getColumnIndexOrThrow(_cursor, "departmentID");
      final int _cursorIndexOfName = CursorUtil.getColumnIndexOrThrow(_cursor, "name");
      final Department _result;
      if(_cursor.moveToFirst()) {
        final int _tmpDeptID;
        _tmpDeptID = _cursor.getInt(_cursorIndexOfDeptID);
        final String _tmpName;
        _tmpName = _cursor.getString(_cursorIndexOfName);
        _result = new Department(_tmpDeptID,_tmpName);
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
  public LiveData<List<Department>> getAllDepartments() {
    final String _sql = "SELECT * FROM departments ORDER BY departmentID";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return __db.getInvalidationTracker().createLiveData(new String[]{"departments"}, false, new Callable<List<Department>>() {
      @Override
      public List<Department> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfDeptID = CursorUtil.getColumnIndexOrThrow(_cursor, "departmentID");
          final int _cursorIndexOfName = CursorUtil.getColumnIndexOrThrow(_cursor, "name");
          final List<Department> _result = new ArrayList<Department>(_cursor.getCount());
          while(_cursor.moveToNext()) {
            final Department _item;
            final int _tmpDeptID;
            _tmpDeptID = _cursor.getInt(_cursorIndexOfDeptID);
            final String _tmpName;
            _tmpName = _cursor.getString(_cursorIndexOfName);
            _item = new Department(_tmpDeptID,_tmpName);
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
  public int getDepartmentCount() {
    final String _sql = "SELECT COUNT(*) FROM departments";
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
