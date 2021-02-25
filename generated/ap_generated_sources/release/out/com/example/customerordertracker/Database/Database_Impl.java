package com.example.customerordertracker.Database;

import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.room.RoomOpenHelper;
import androidx.room.RoomOpenHelper.Delegate;
import androidx.room.RoomOpenHelper.ValidationResult;
import androidx.room.util.DBUtil;
import androidx.room.util.TableInfo;
import androidx.room.util.TableInfo.Column;
import androidx.room.util.TableInfo.ForeignKey;
import androidx.room.util.TableInfo.Index;
import androidx.sqlite.db.SupportSQLiteDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper;
import androidx.sqlite.db.SupportSQLiteOpenHelper.Callback;
import androidx.sqlite.db.SupportSQLiteOpenHelper.Configuration;
import com.example.customerordertracker.DAO.ArtDAO;
import com.example.customerordertracker.DAO.ArtDAO_Impl;
import com.example.customerordertracker.DAO.ClientDAO;
import com.example.customerordertracker.DAO.ClientDAO_Impl;
import com.example.customerordertracker.DAO.DepartmentDAO;
import com.example.customerordertracker.DAO.DepartmentDAO_Impl;
import com.example.customerordertracker.DAO.EmployeeDAO;
import com.example.customerordertracker.DAO.EmployeeDAO_Impl;
import com.example.customerordertracker.DAO.ItemDAO;
import com.example.customerordertracker.DAO.ItemDAO_Impl;
import com.example.customerordertracker.DAO.OrderDAO;
import com.example.customerordertracker.DAO.OrderDAO_Impl;
import com.example.customerordertracker.DAO.POCDAO;
import com.example.customerordertracker.DAO.POCDAO_Impl;
import com.example.customerordertracker.DAO.VendorDAO;
import com.example.customerordertracker.DAO.VendorDAO_Impl;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

@SuppressWarnings({"unchecked", "deprecation"})
public final class Database_Impl extends Database {
  private volatile ArtDAO _artDAO;

  private volatile ClientDAO _clientDAO;

  private volatile DepartmentDAO _departmentDAO;

  private volatile EmployeeDAO _employeeDAO;

  private volatile ItemDAO _itemDAO;

  private volatile OrderDAO _orderDAO;

  private volatile POCDAO _pOCDAO;

  private volatile VendorDAO _vendorDAO;

  @Override
  protected SupportSQLiteOpenHelper createOpenHelper(DatabaseConfiguration configuration) {
    final SupportSQLiteOpenHelper.Callback _openCallback = new RoomOpenHelper(configuration, new RoomOpenHelper.Delegate(15) {
      @Override
      public void createAllTables(SupportSQLiteDatabase _db) {
        _db.execSQL("CREATE TABLE IF NOT EXISTS `art` (`artID` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `name` TEXT NOT NULL, `art_loc` TEXT, `number_of_colors` TEXT, `placement` TEXT, `last_updated` INTEGER, `last_updated_by` TEXT, `orderID` INTEGER NOT NULL, FOREIGN KEY(`orderID`) REFERENCES `orders`(`orderID`) ON UPDATE CASCADE ON DELETE CASCADE )");
        _db.execSQL("CREATE INDEX IF NOT EXISTS `index_art_artID` ON `art` (`artID`)");
        _db.execSQL("CREATE INDEX IF NOT EXISTS `index_art_orderID` ON `art` (`orderID`)");
        _db.execSQL("CREATE TABLE IF NOT EXISTS `clients` (`clientID` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `name` TEXT NOT NULL, `address` TEXT NOT NULL, `city` TEXT NOT NULL, `state` TEXT NOT NULL, `zip` TEXT NOT NULL, `phone` TEXT NOT NULL)");
        _db.execSQL("CREATE INDEX IF NOT EXISTS `index_clients_clientID` ON `clients` (`clientID`)");
        _db.execSQL("CREATE TABLE IF NOT EXISTS `departments` (`departmentID` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `name` TEXT NOT NULL)");
        _db.execSQL("CREATE INDEX IF NOT EXISTS `index_departments_departmentID` ON `departments` (`departmentID`)");
        _db.execSQL("CREATE TABLE IF NOT EXISTS `employees` (`employeeID` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `first_name` TEXT NOT NULL, `last_name` TEXT NOT NULL, `email` TEXT NOT NULL, `phone` TEXT NOT NULL, `departmentID` INTEGER NOT NULL, `admin` INTEGER NOT NULL, `username` TEXT NOT NULL, `password` TEXT NOT NULL, FOREIGN KEY(`departmentID`) REFERENCES `departments`(`departmentID`) ON UPDATE CASCADE ON DELETE CASCADE )");
        _db.execSQL("CREATE INDEX IF NOT EXISTS `index_employees_departmentID` ON `employees` (`departmentID`)");
        _db.execSQL("CREATE TABLE IF NOT EXISTS `items` (`itemID` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `name` TEXT NOT NULL, `description` TEXT, `quantity` TEXT, `adult_or_youth` TEXT NOT NULL, `size` TEXT NOT NULL, `application` TEXT NOT NULL, `color` TEXT, `last_updated` INTEGER, `last_updated_by` TEXT, `orderID` INTEGER NOT NULL, `vendorID` INTEGER NOT NULL, FOREIGN KEY(`vendorID`) REFERENCES `vendors`(`vendorID`) ON UPDATE CASCADE ON DELETE CASCADE , FOREIGN KEY(`orderID`) REFERENCES `orders`(`orderID`) ON UPDATE CASCADE ON DELETE CASCADE )");
        _db.execSQL("CREATE INDEX IF NOT EXISTS `index_items_itemID` ON `items` (`itemID`)");
        _db.execSQL("CREATE INDEX IF NOT EXISTS `index_items_orderID` ON `items` (`orderID`)");
        _db.execSQL("CREATE INDEX IF NOT EXISTS `index_items_vendorID` ON `items` (`vendorID`)");
        _db.execSQL("CREATE TABLE IF NOT EXISTS `orders` (`orderID` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `name` TEXT, `date_ordered` INTEGER, `date_due` INTEGER, `delivery_type` TEXT NOT NULL, `reference_art_location` TEXT, `description` TEXT, `address` TEXT, `city` TEXT, `state` TEXT, `zip` TEXT, `clientID` INTEGER NOT NULL, FOREIGN KEY(`clientID`) REFERENCES `clients`(`clientID`) ON UPDATE CASCADE ON DELETE CASCADE )");
        _db.execSQL("CREATE INDEX IF NOT EXISTS `index_orders_orderID` ON `orders` (`orderID`)");
        _db.execSQL("CREATE INDEX IF NOT EXISTS `index_orders_clientID` ON `orders` (`clientID`)");
        _db.execSQL("CREATE TABLE IF NOT EXISTS `point_of_contacts` (`pocID` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `first_name` TEXT NOT NULL, `last_name` TEXT NOT NULL, `phone` TEXT, `email` TEXT, `clientID` INTEGER NOT NULL, FOREIGN KEY(`clientID`) REFERENCES `clients`(`clientID`) ON UPDATE CASCADE ON DELETE CASCADE )");
        _db.execSQL("CREATE INDEX IF NOT EXISTS `index_point_of_contacts_pocID` ON `point_of_contacts` (`pocID`)");
        _db.execSQL("CREATE INDEX IF NOT EXISTS `index_point_of_contacts_clientID` ON `point_of_contacts` (`clientID`)");
        _db.execSQL("CREATE TABLE IF NOT EXISTS `vendors` (`vendorID` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `name` TEXT NOT NULL)");
        _db.execSQL("CREATE INDEX IF NOT EXISTS `index_vendors_vendorID` ON `vendors` (`vendorID`)");
        _db.execSQL("CREATE TABLE IF NOT EXISTS room_master_table (id INTEGER PRIMARY KEY,identity_hash TEXT)");
        _db.execSQL("INSERT OR REPLACE INTO room_master_table (id,identity_hash) VALUES(42, '160a05314b3f0076d418f2ba8a5f82a1')");
      }

      @Override
      public void dropAllTables(SupportSQLiteDatabase _db) {
        _db.execSQL("DROP TABLE IF EXISTS `art`");
        _db.execSQL("DROP TABLE IF EXISTS `clients`");
        _db.execSQL("DROP TABLE IF EXISTS `departments`");
        _db.execSQL("DROP TABLE IF EXISTS `employees`");
        _db.execSQL("DROP TABLE IF EXISTS `items`");
        _db.execSQL("DROP TABLE IF EXISTS `orders`");
        _db.execSQL("DROP TABLE IF EXISTS `point_of_contacts`");
        _db.execSQL("DROP TABLE IF EXISTS `vendors`");
        if (mCallbacks != null) {
          for (int _i = 0, _size = mCallbacks.size(); _i < _size; _i++) {
            mCallbacks.get(_i).onDestructiveMigration(_db);
          }
        }
      }

      @Override
      protected void onCreate(SupportSQLiteDatabase _db) {
        if (mCallbacks != null) {
          for (int _i = 0, _size = mCallbacks.size(); _i < _size; _i++) {
            mCallbacks.get(_i).onCreate(_db);
          }
        }
      }

      @Override
      public void onOpen(SupportSQLiteDatabase _db) {
        mDatabase = _db;
        _db.execSQL("PRAGMA foreign_keys = ON");
        internalInitInvalidationTracker(_db);
        if (mCallbacks != null) {
          for (int _i = 0, _size = mCallbacks.size(); _i < _size; _i++) {
            mCallbacks.get(_i).onOpen(_db);
          }
        }
      }

      @Override
      public void onPreMigrate(SupportSQLiteDatabase _db) {
        DBUtil.dropFtsSyncTriggers(_db);
      }

      @Override
      public void onPostMigrate(SupportSQLiteDatabase _db) {
      }

      @Override
      protected RoomOpenHelper.ValidationResult onValidateSchema(SupportSQLiteDatabase _db) {
        final HashMap<String, TableInfo.Column> _columnsArt = new HashMap<String, TableInfo.Column>(8);
        _columnsArt.put("artID", new TableInfo.Column("artID", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsArt.put("name", new TableInfo.Column("name", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsArt.put("art_loc", new TableInfo.Column("art_loc", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsArt.put("number_of_colors", new TableInfo.Column("number_of_colors", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsArt.put("placement", new TableInfo.Column("placement", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsArt.put("last_updated", new TableInfo.Column("last_updated", "INTEGER", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsArt.put("last_updated_by", new TableInfo.Column("last_updated_by", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsArt.put("orderID", new TableInfo.Column("orderID", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysArt = new HashSet<TableInfo.ForeignKey>(1);
        _foreignKeysArt.add(new TableInfo.ForeignKey("orders", "CASCADE", "CASCADE",Arrays.asList("orderID"), Arrays.asList("orderID")));
        final HashSet<TableInfo.Index> _indicesArt = new HashSet<TableInfo.Index>(2);
        _indicesArt.add(new TableInfo.Index("index_art_artID", false, Arrays.asList("artID")));
        _indicesArt.add(new TableInfo.Index("index_art_orderID", false, Arrays.asList("orderID")));
        final TableInfo _infoArt = new TableInfo("art", _columnsArt, _foreignKeysArt, _indicesArt);
        final TableInfo _existingArt = TableInfo.read(_db, "art");
        if (! _infoArt.equals(_existingArt)) {
          return new RoomOpenHelper.ValidationResult(false, "art(com.example.customerordertracker.Entities.Art).\n"
                  + " Expected:\n" + _infoArt + "\n"
                  + " Found:\n" + _existingArt);
        }
        final HashMap<String, TableInfo.Column> _columnsClients = new HashMap<String, TableInfo.Column>(7);
        _columnsClients.put("clientID", new TableInfo.Column("clientID", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsClients.put("name", new TableInfo.Column("name", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsClients.put("address", new TableInfo.Column("address", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsClients.put("city", new TableInfo.Column("city", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsClients.put("state", new TableInfo.Column("state", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsClients.put("zip", new TableInfo.Column("zip", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsClients.put("phone", new TableInfo.Column("phone", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysClients = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesClients = new HashSet<TableInfo.Index>(1);
        _indicesClients.add(new TableInfo.Index("index_clients_clientID", false, Arrays.asList("clientID")));
        final TableInfo _infoClients = new TableInfo("clients", _columnsClients, _foreignKeysClients, _indicesClients);
        final TableInfo _existingClients = TableInfo.read(_db, "clients");
        if (! _infoClients.equals(_existingClients)) {
          return new RoomOpenHelper.ValidationResult(false, "clients(com.example.customerordertracker.Entities.Client).\n"
                  + " Expected:\n" + _infoClients + "\n"
                  + " Found:\n" + _existingClients);
        }
        final HashMap<String, TableInfo.Column> _columnsDepartments = new HashMap<String, TableInfo.Column>(2);
        _columnsDepartments.put("departmentID", new TableInfo.Column("departmentID", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsDepartments.put("name", new TableInfo.Column("name", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysDepartments = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesDepartments = new HashSet<TableInfo.Index>(1);
        _indicesDepartments.add(new TableInfo.Index("index_departments_departmentID", false, Arrays.asList("departmentID")));
        final TableInfo _infoDepartments = new TableInfo("departments", _columnsDepartments, _foreignKeysDepartments, _indicesDepartments);
        final TableInfo _existingDepartments = TableInfo.read(_db, "departments");
        if (! _infoDepartments.equals(_existingDepartments)) {
          return new RoomOpenHelper.ValidationResult(false, "departments(com.example.customerordertracker.Entities.Department).\n"
                  + " Expected:\n" + _infoDepartments + "\n"
                  + " Found:\n" + _existingDepartments);
        }
        final HashMap<String, TableInfo.Column> _columnsEmployees = new HashMap<String, TableInfo.Column>(9);
        _columnsEmployees.put("employeeID", new TableInfo.Column("employeeID", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsEmployees.put("first_name", new TableInfo.Column("first_name", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsEmployees.put("last_name", new TableInfo.Column("last_name", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsEmployees.put("email", new TableInfo.Column("email", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsEmployees.put("phone", new TableInfo.Column("phone", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsEmployees.put("departmentID", new TableInfo.Column("departmentID", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsEmployees.put("admin", new TableInfo.Column("admin", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsEmployees.put("username", new TableInfo.Column("username", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsEmployees.put("password", new TableInfo.Column("password", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysEmployees = new HashSet<TableInfo.ForeignKey>(1);
        _foreignKeysEmployees.add(new TableInfo.ForeignKey("departments", "CASCADE", "CASCADE",Arrays.asList("departmentID"), Arrays.asList("departmentID")));
        final HashSet<TableInfo.Index> _indicesEmployees = new HashSet<TableInfo.Index>(1);
        _indicesEmployees.add(new TableInfo.Index("index_employees_departmentID", false, Arrays.asList("departmentID")));
        final TableInfo _infoEmployees = new TableInfo("employees", _columnsEmployees, _foreignKeysEmployees, _indicesEmployees);
        final TableInfo _existingEmployees = TableInfo.read(_db, "employees");
        if (! _infoEmployees.equals(_existingEmployees)) {
          return new RoomOpenHelper.ValidationResult(false, "employees(com.example.customerordertracker.Entities.Employee).\n"
                  + " Expected:\n" + _infoEmployees + "\n"
                  + " Found:\n" + _existingEmployees);
        }
        final HashMap<String, TableInfo.Column> _columnsItems = new HashMap<String, TableInfo.Column>(12);
        _columnsItems.put("itemID", new TableInfo.Column("itemID", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsItems.put("name", new TableInfo.Column("name", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsItems.put("description", new TableInfo.Column("description", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsItems.put("quantity", new TableInfo.Column("quantity", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsItems.put("adult_or_youth", new TableInfo.Column("adult_or_youth", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsItems.put("size", new TableInfo.Column("size", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsItems.put("application", new TableInfo.Column("application", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsItems.put("color", new TableInfo.Column("color", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsItems.put("last_updated", new TableInfo.Column("last_updated", "INTEGER", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsItems.put("last_updated_by", new TableInfo.Column("last_updated_by", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsItems.put("orderID", new TableInfo.Column("orderID", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsItems.put("vendorID", new TableInfo.Column("vendorID", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysItems = new HashSet<TableInfo.ForeignKey>(2);
        _foreignKeysItems.add(new TableInfo.ForeignKey("vendors", "CASCADE", "CASCADE",Arrays.asList("vendorID"), Arrays.asList("vendorID")));
        _foreignKeysItems.add(new TableInfo.ForeignKey("orders", "CASCADE", "CASCADE",Arrays.asList("orderID"), Arrays.asList("orderID")));
        final HashSet<TableInfo.Index> _indicesItems = new HashSet<TableInfo.Index>(3);
        _indicesItems.add(new TableInfo.Index("index_items_itemID", false, Arrays.asList("itemID")));
        _indicesItems.add(new TableInfo.Index("index_items_orderID", false, Arrays.asList("orderID")));
        _indicesItems.add(new TableInfo.Index("index_items_vendorID", false, Arrays.asList("vendorID")));
        final TableInfo _infoItems = new TableInfo("items", _columnsItems, _foreignKeysItems, _indicesItems);
        final TableInfo _existingItems = TableInfo.read(_db, "items");
        if (! _infoItems.equals(_existingItems)) {
          return new RoomOpenHelper.ValidationResult(false, "items(com.example.customerordertracker.Entities.Item).\n"
                  + " Expected:\n" + _infoItems + "\n"
                  + " Found:\n" + _existingItems);
        }
        final HashMap<String, TableInfo.Column> _columnsOrders = new HashMap<String, TableInfo.Column>(12);
        _columnsOrders.put("orderID", new TableInfo.Column("orderID", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsOrders.put("name", new TableInfo.Column("name", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsOrders.put("date_ordered", new TableInfo.Column("date_ordered", "INTEGER", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsOrders.put("date_due", new TableInfo.Column("date_due", "INTEGER", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsOrders.put("delivery_type", new TableInfo.Column("delivery_type", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsOrders.put("reference_art_location", new TableInfo.Column("reference_art_location", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsOrders.put("description", new TableInfo.Column("description", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsOrders.put("address", new TableInfo.Column("address", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsOrders.put("city", new TableInfo.Column("city", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsOrders.put("state", new TableInfo.Column("state", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsOrders.put("zip", new TableInfo.Column("zip", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsOrders.put("clientID", new TableInfo.Column("clientID", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysOrders = new HashSet<TableInfo.ForeignKey>(1);
        _foreignKeysOrders.add(new TableInfo.ForeignKey("clients", "CASCADE", "CASCADE",Arrays.asList("clientID"), Arrays.asList("clientID")));
        final HashSet<TableInfo.Index> _indicesOrders = new HashSet<TableInfo.Index>(2);
        _indicesOrders.add(new TableInfo.Index("index_orders_orderID", false, Arrays.asList("orderID")));
        _indicesOrders.add(new TableInfo.Index("index_orders_clientID", false, Arrays.asList("clientID")));
        final TableInfo _infoOrders = new TableInfo("orders", _columnsOrders, _foreignKeysOrders, _indicesOrders);
        final TableInfo _existingOrders = TableInfo.read(_db, "orders");
        if (! _infoOrders.equals(_existingOrders)) {
          return new RoomOpenHelper.ValidationResult(false, "orders(com.example.customerordertracker.Entities.Order).\n"
                  + " Expected:\n" + _infoOrders + "\n"
                  + " Found:\n" + _existingOrders);
        }
        final HashMap<String, TableInfo.Column> _columnsPointOfContacts = new HashMap<String, TableInfo.Column>(6);
        _columnsPointOfContacts.put("pocID", new TableInfo.Column("pocID", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsPointOfContacts.put("first_name", new TableInfo.Column("first_name", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsPointOfContacts.put("last_name", new TableInfo.Column("last_name", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsPointOfContacts.put("phone", new TableInfo.Column("phone", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsPointOfContacts.put("email", new TableInfo.Column("email", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsPointOfContacts.put("clientID", new TableInfo.Column("clientID", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysPointOfContacts = new HashSet<TableInfo.ForeignKey>(1);
        _foreignKeysPointOfContacts.add(new TableInfo.ForeignKey("clients", "CASCADE", "CASCADE",Arrays.asList("clientID"), Arrays.asList("clientID")));
        final HashSet<TableInfo.Index> _indicesPointOfContacts = new HashSet<TableInfo.Index>(2);
        _indicesPointOfContacts.add(new TableInfo.Index("index_point_of_contacts_pocID", false, Arrays.asList("pocID")));
        _indicesPointOfContacts.add(new TableInfo.Index("index_point_of_contacts_clientID", false, Arrays.asList("clientID")));
        final TableInfo _infoPointOfContacts = new TableInfo("point_of_contacts", _columnsPointOfContacts, _foreignKeysPointOfContacts, _indicesPointOfContacts);
        final TableInfo _existingPointOfContacts = TableInfo.read(_db, "point_of_contacts");
        if (! _infoPointOfContacts.equals(_existingPointOfContacts)) {
          return new RoomOpenHelper.ValidationResult(false, "point_of_contacts(com.example.customerordertracker.Entities.POC).\n"
                  + " Expected:\n" + _infoPointOfContacts + "\n"
                  + " Found:\n" + _existingPointOfContacts);
        }
        final HashMap<String, TableInfo.Column> _columnsVendors = new HashMap<String, TableInfo.Column>(2);
        _columnsVendors.put("vendorID", new TableInfo.Column("vendorID", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsVendors.put("name", new TableInfo.Column("name", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysVendors = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesVendors = new HashSet<TableInfo.Index>(1);
        _indicesVendors.add(new TableInfo.Index("index_vendors_vendorID", false, Arrays.asList("vendorID")));
        final TableInfo _infoVendors = new TableInfo("vendors", _columnsVendors, _foreignKeysVendors, _indicesVendors);
        final TableInfo _existingVendors = TableInfo.read(_db, "vendors");
        if (! _infoVendors.equals(_existingVendors)) {
          return new RoomOpenHelper.ValidationResult(false, "vendors(com.example.customerordertracker.Entities.Vendor).\n"
                  + " Expected:\n" + _infoVendors + "\n"
                  + " Found:\n" + _existingVendors);
        }
        return new RoomOpenHelper.ValidationResult(true, null);
      }
    }, "160a05314b3f0076d418f2ba8a5f82a1", "5805438a59c3573b3d1af6c5e8ea2f9c");
    final SupportSQLiteOpenHelper.Configuration _sqliteConfig = SupportSQLiteOpenHelper.Configuration.builder(configuration.context)
        .name(configuration.name)
        .callback(_openCallback)
        .build();
    final SupportSQLiteOpenHelper _helper = configuration.sqliteOpenHelperFactory.create(_sqliteConfig);
    return _helper;
  }

  @Override
  protected InvalidationTracker createInvalidationTracker() {
    final HashMap<String, String> _shadowTablesMap = new HashMap<String, String>(0);
    HashMap<String, Set<String>> _viewTables = new HashMap<String, Set<String>>(0);
    return new InvalidationTracker(this, _shadowTablesMap, _viewTables, "art","clients","departments","employees","items","orders","point_of_contacts","vendors");
  }

  @Override
  public void clearAllTables() {
    super.assertNotMainThread();
    final SupportSQLiteDatabase _db = super.getOpenHelper().getWritableDatabase();
    boolean _supportsDeferForeignKeys = android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP;
    try {
      if (!_supportsDeferForeignKeys) {
        _db.execSQL("PRAGMA foreign_keys = FALSE");
      }
      super.beginTransaction();
      if (_supportsDeferForeignKeys) {
        _db.execSQL("PRAGMA defer_foreign_keys = TRUE");
      }
      _db.execSQL("DELETE FROM `art`");
      _db.execSQL("DELETE FROM `clients`");
      _db.execSQL("DELETE FROM `departments`");
      _db.execSQL("DELETE FROM `employees`");
      _db.execSQL("DELETE FROM `items`");
      _db.execSQL("DELETE FROM `orders`");
      _db.execSQL("DELETE FROM `point_of_contacts`");
      _db.execSQL("DELETE FROM `vendors`");
      super.setTransactionSuccessful();
    } finally {
      super.endTransaction();
      if (!_supportsDeferForeignKeys) {
        _db.execSQL("PRAGMA foreign_keys = TRUE");
      }
      _db.query("PRAGMA wal_checkpoint(FULL)").close();
      if (!_db.inTransaction()) {
        _db.execSQL("VACUUM");
      }
    }
  }

  @Override
  public ArtDAO artDAO() {
    if (_artDAO != null) {
      return _artDAO;
    } else {
      synchronized(this) {
        if(_artDAO == null) {
          _artDAO = new ArtDAO_Impl(this);
        }
        return _artDAO;
      }
    }
  }

  @Override
  public ClientDAO clientDAO() {
    if (_clientDAO != null) {
      return _clientDAO;
    } else {
      synchronized(this) {
        if(_clientDAO == null) {
          _clientDAO = new ClientDAO_Impl(this);
        }
        return _clientDAO;
      }
    }
  }

  @Override
  public DepartmentDAO departmentDAO() {
    if (_departmentDAO != null) {
      return _departmentDAO;
    } else {
      synchronized(this) {
        if(_departmentDAO == null) {
          _departmentDAO = new DepartmentDAO_Impl(this);
        }
        return _departmentDAO;
      }
    }
  }

  @Override
  public EmployeeDAO employeeDAO() {
    if (_employeeDAO != null) {
      return _employeeDAO;
    } else {
      synchronized(this) {
        if(_employeeDAO == null) {
          _employeeDAO = new EmployeeDAO_Impl(this);
        }
        return _employeeDAO;
      }
    }
  }

  @Override
  public ItemDAO itemDAO() {
    if (_itemDAO != null) {
      return _itemDAO;
    } else {
      synchronized(this) {
        if(_itemDAO == null) {
          _itemDAO = new ItemDAO_Impl(this);
        }
        return _itemDAO;
      }
    }
  }

  @Override
  public OrderDAO orderDAO() {
    if (_orderDAO != null) {
      return _orderDAO;
    } else {
      synchronized(this) {
        if(_orderDAO == null) {
          _orderDAO = new OrderDAO_Impl(this);
        }
        return _orderDAO;
      }
    }
  }

  @Override
  public POCDAO pocDAO() {
    if (_pOCDAO != null) {
      return _pOCDAO;
    } else {
      synchronized(this) {
        if(_pOCDAO == null) {
          _pOCDAO = new POCDAO_Impl(this);
        }
        return _pOCDAO;
      }
    }
  }

  @Override
  public VendorDAO vendorDAO() {
    if (_vendorDAO != null) {
      return _vendorDAO;
    } else {
      synchronized(this) {
        if(_vendorDAO == null) {
          _vendorDAO = new VendorDAO_Impl(this);
        }
        return _vendorDAO;
      }
    }
  }
}
