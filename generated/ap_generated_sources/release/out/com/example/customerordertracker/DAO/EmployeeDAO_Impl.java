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
import com.example.customerordertracker.Entities.Employee;
import java.lang.Exception;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

@SuppressWarnings({"unchecked", "deprecation"})
public final class EmployeeDAO_Impl implements EmployeeDAO {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<Employee> __insertionAdapterOfEmployee;

  private final EntityDeletionOrUpdateAdapter<Employee> __deletionAdapterOfEmployee;

  private final EntityDeletionOrUpdateAdapter<Employee> __updateAdapterOfEmployee;

  private final SharedSQLiteStatement __preparedStmtOfDeleteAllEmployees;

  public EmployeeDAO_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfEmployee = new EntityInsertionAdapter<Employee>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR REPLACE INTO `employees` (`employeeID`,`first_name`,`last_name`,`email`,`phone`,`departmentID`,`admin`,`username`,`password`) VALUES (nullif(?, 0),?,?,?,?,?,?,?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, Employee value) {
        stmt.bindLong(1, value.empID);
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
        if (value.getEmail() == null) {
          stmt.bindNull(4);
        } else {
          stmt.bindString(4, value.getEmail());
        }
        if (value.getPhone() == null) {
          stmt.bindNull(5);
        } else {
          stmt.bindString(5, value.getPhone());
        }
        stmt.bindLong(6, value.departmentId);
        stmt.bindLong(7, value.admin);
        if (value.username == null) {
          stmt.bindNull(8);
        } else {
          stmt.bindString(8, value.username);
        }
        if (value.password == null) {
          stmt.bindNull(9);
        } else {
          stmt.bindString(9, value.password);
        }
      }
    };
    this.__deletionAdapterOfEmployee = new EntityDeletionOrUpdateAdapter<Employee>(__db) {
      @Override
      public String createQuery() {
        return "DELETE FROM `employees` WHERE `employeeID` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, Employee value) {
        stmt.bindLong(1, value.empID);
      }
    };
    this.__updateAdapterOfEmployee = new EntityDeletionOrUpdateAdapter<Employee>(__db) {
      @Override
      public String createQuery() {
        return "UPDATE OR REPLACE `employees` SET `employeeID` = ?,`first_name` = ?,`last_name` = ?,`email` = ?,`phone` = ?,`departmentID` = ?,`admin` = ?,`username` = ?,`password` = ? WHERE `employeeID` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, Employee value) {
        stmt.bindLong(1, value.empID);
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
        if (value.getEmail() == null) {
          stmt.bindNull(4);
        } else {
          stmt.bindString(4, value.getEmail());
        }
        if (value.getPhone() == null) {
          stmt.bindNull(5);
        } else {
          stmt.bindString(5, value.getPhone());
        }
        stmt.bindLong(6, value.departmentId);
        stmt.bindLong(7, value.admin);
        if (value.username == null) {
          stmt.bindNull(8);
        } else {
          stmt.bindString(8, value.username);
        }
        if (value.password == null) {
          stmt.bindNull(9);
        } else {
          stmt.bindString(9, value.password);
        }
        stmt.bindLong(10, value.empID);
      }
    };
    this.__preparedStmtOfDeleteAllEmployees = new SharedSQLiteStatement(__db) {
      @Override
      public String createQuery() {
        final String _query = "DELETE FROM employees";
        return _query;
      }
    };
  }

  @Override
  public void insertEmployee(final Employee employee) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __insertionAdapterOfEmployee.insert(employee);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void deleteEmployee(final Employee employee) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __deletionAdapterOfEmployee.handle(employee);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void updateEmployee(final Employee employee) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __updateAdapterOfEmployee.handle(employee);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public int deleteAllEmployees() {
    __db.assertNotSuspendingTransaction();
    final SupportSQLiteStatement _stmt = __preparedStmtOfDeleteAllEmployees.acquire();
    __db.beginTransaction();
    try {
      final int _result = _stmt.executeUpdateDelete();
      __db.setTransactionSuccessful();
      return _result;
    } finally {
      __db.endTransaction();
      __preparedStmtOfDeleteAllEmployees.release(_stmt);
    }
  }

  @Override
  public Employee getEmployee(final int employeeId) {
    final String _sql = "SELECT * FROM employees WHERE employeeID = ? LIMIT 1";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, employeeId);
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfEmpID = CursorUtil.getColumnIndexOrThrow(_cursor, "employeeID");
      final int _cursorIndexOfFName = CursorUtil.getColumnIndexOrThrow(_cursor, "first_name");
      final int _cursorIndexOfLName = CursorUtil.getColumnIndexOrThrow(_cursor, "last_name");
      final int _cursorIndexOfEmail = CursorUtil.getColumnIndexOrThrow(_cursor, "email");
      final int _cursorIndexOfPhone = CursorUtil.getColumnIndexOrThrow(_cursor, "phone");
      final int _cursorIndexOfDepartmentId = CursorUtil.getColumnIndexOrThrow(_cursor, "departmentID");
      final int _cursorIndexOfAdmin = CursorUtil.getColumnIndexOrThrow(_cursor, "admin");
      final int _cursorIndexOfUsername = CursorUtil.getColumnIndexOrThrow(_cursor, "username");
      final int _cursorIndexOfPassword = CursorUtil.getColumnIndexOrThrow(_cursor, "password");
      final Employee _result;
      if(_cursor.moveToFirst()) {
        final int _tmpEmpID;
        _tmpEmpID = _cursor.getInt(_cursorIndexOfEmpID);
        final String _tmpFName;
        _tmpFName = _cursor.getString(_cursorIndexOfFName);
        final String _tmpLName;
        _tmpLName = _cursor.getString(_cursorIndexOfLName);
        final String _tmpEmail;
        _tmpEmail = _cursor.getString(_cursorIndexOfEmail);
        final String _tmpPhone;
        _tmpPhone = _cursor.getString(_cursorIndexOfPhone);
        final int _tmpDepartmentId;
        _tmpDepartmentId = _cursor.getInt(_cursorIndexOfDepartmentId);
        final int _tmpAdmin;
        _tmpAdmin = _cursor.getInt(_cursorIndexOfAdmin);
        final String _tmpUsername;
        _tmpUsername = _cursor.getString(_cursorIndexOfUsername);
        final String _tmpPassword;
        _tmpPassword = _cursor.getString(_cursorIndexOfPassword);
        _result = new Employee(_tmpEmpID,_tmpFName,_tmpLName,_tmpEmail,_tmpPhone,_tmpDepartmentId,_tmpAdmin,_tmpUsername,_tmpPassword);
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
  public LiveData<List<Employee>> getRelatedEmployees(final int departmentId) {
    final String _sql = "SELECT * FROM employees WHERE departmentID = ? ORDER BY employeeID";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, departmentId);
    return __db.getInvalidationTracker().createLiveData(new String[]{"employees"}, false, new Callable<List<Employee>>() {
      @Override
      public List<Employee> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfEmpID = CursorUtil.getColumnIndexOrThrow(_cursor, "employeeID");
          final int _cursorIndexOfFName = CursorUtil.getColumnIndexOrThrow(_cursor, "first_name");
          final int _cursorIndexOfLName = CursorUtil.getColumnIndexOrThrow(_cursor, "last_name");
          final int _cursorIndexOfEmail = CursorUtil.getColumnIndexOrThrow(_cursor, "email");
          final int _cursorIndexOfPhone = CursorUtil.getColumnIndexOrThrow(_cursor, "phone");
          final int _cursorIndexOfDepartmentId = CursorUtil.getColumnIndexOrThrow(_cursor, "departmentID");
          final int _cursorIndexOfAdmin = CursorUtil.getColumnIndexOrThrow(_cursor, "admin");
          final int _cursorIndexOfUsername = CursorUtil.getColumnIndexOrThrow(_cursor, "username");
          final int _cursorIndexOfPassword = CursorUtil.getColumnIndexOrThrow(_cursor, "password");
          final List<Employee> _result = new ArrayList<Employee>(_cursor.getCount());
          while(_cursor.moveToNext()) {
            final Employee _item;
            final int _tmpEmpID;
            _tmpEmpID = _cursor.getInt(_cursorIndexOfEmpID);
            final String _tmpFName;
            _tmpFName = _cursor.getString(_cursorIndexOfFName);
            final String _tmpLName;
            _tmpLName = _cursor.getString(_cursorIndexOfLName);
            final String _tmpEmail;
            _tmpEmail = _cursor.getString(_cursorIndexOfEmail);
            final String _tmpPhone;
            _tmpPhone = _cursor.getString(_cursorIndexOfPhone);
            final int _tmpDepartmentId;
            _tmpDepartmentId = _cursor.getInt(_cursorIndexOfDepartmentId);
            final int _tmpAdmin;
            _tmpAdmin = _cursor.getInt(_cursorIndexOfAdmin);
            final String _tmpUsername;
            _tmpUsername = _cursor.getString(_cursorIndexOfUsername);
            final String _tmpPassword;
            _tmpPassword = _cursor.getString(_cursorIndexOfPassword);
            _item = new Employee(_tmpEmpID,_tmpFName,_tmpLName,_tmpEmail,_tmpPhone,_tmpDepartmentId,_tmpAdmin,_tmpUsername,_tmpPassword);
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
  public LiveData<List<Employee>> getAllEmployees() {
    final String _sql = "SELECT * FROM employees ORDER BY employeeID";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return __db.getInvalidationTracker().createLiveData(new String[]{"employees"}, false, new Callable<List<Employee>>() {
      @Override
      public List<Employee> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfEmpID = CursorUtil.getColumnIndexOrThrow(_cursor, "employeeID");
          final int _cursorIndexOfFName = CursorUtil.getColumnIndexOrThrow(_cursor, "first_name");
          final int _cursorIndexOfLName = CursorUtil.getColumnIndexOrThrow(_cursor, "last_name");
          final int _cursorIndexOfEmail = CursorUtil.getColumnIndexOrThrow(_cursor, "email");
          final int _cursorIndexOfPhone = CursorUtil.getColumnIndexOrThrow(_cursor, "phone");
          final int _cursorIndexOfDepartmentId = CursorUtil.getColumnIndexOrThrow(_cursor, "departmentID");
          final int _cursorIndexOfAdmin = CursorUtil.getColumnIndexOrThrow(_cursor, "admin");
          final int _cursorIndexOfUsername = CursorUtil.getColumnIndexOrThrow(_cursor, "username");
          final int _cursorIndexOfPassword = CursorUtil.getColumnIndexOrThrow(_cursor, "password");
          final List<Employee> _result = new ArrayList<Employee>(_cursor.getCount());
          while(_cursor.moveToNext()) {
            final Employee _item;
            final int _tmpEmpID;
            _tmpEmpID = _cursor.getInt(_cursorIndexOfEmpID);
            final String _tmpFName;
            _tmpFName = _cursor.getString(_cursorIndexOfFName);
            final String _tmpLName;
            _tmpLName = _cursor.getString(_cursorIndexOfLName);
            final String _tmpEmail;
            _tmpEmail = _cursor.getString(_cursorIndexOfEmail);
            final String _tmpPhone;
            _tmpPhone = _cursor.getString(_cursorIndexOfPhone);
            final int _tmpDepartmentId;
            _tmpDepartmentId = _cursor.getInt(_cursorIndexOfDepartmentId);
            final int _tmpAdmin;
            _tmpAdmin = _cursor.getInt(_cursorIndexOfAdmin);
            final String _tmpUsername;
            _tmpUsername = _cursor.getString(_cursorIndexOfUsername);
            final String _tmpPassword;
            _tmpPassword = _cursor.getString(_cursorIndexOfPassword);
            _item = new Employee(_tmpEmpID,_tmpFName,_tmpLName,_tmpEmail,_tmpPhone,_tmpDepartmentId,_tmpAdmin,_tmpUsername,_tmpPassword);
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
  public int getEmployeeCount() {
    final String _sql = "SELECT COUNT(*) FROM employees";
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
