package com.example.customerordertracker.Utilities;

public class Constants {

    //Database
    public static final int DATABASE_VERSION = 15;
    public static final String DATABASE_NAME = "COTDatabase.db";
    //Art Table
    public static final String TABLE_ART = "art";
    public static final String TABLE_ART_COL_ID = "artID"; //Primary Key
    public static final String TABLE_ART_COL_NAME = "name";
    public static final String TABLE_ART_COL_ARTLOC = "art_loc";
    public static final String TABLE_ART_COL_NUMBEROFCOLORS = "number_of_colors";
    public static final String TABLE_ART_COL_PLACEMENT = "placement";
    public static final String TABLE_ART_COL_LASTUPDATED = "last_updated";
    public static final String TABLE_ART_COL_LASTUPDATEDBY = "last_updated_by";
    public static final String TABLE_ART_COL_ORDERID = "orderID"; //Foreign Key
    //Client Table
    public static final String TABLE_CLIENT = "clients";
    public static final String TABLE_CLIENT_COL_ID = "clientID"; //Primary Key
    public static final String TABLE_CLIENT_COL_NAME = "name";
    public static final String TABLE_CLIENT_COL_ADDRESS = "address";
    public static final String TABLE_CLIENT_COL_CITY = "city";
    public static final String TABLE_CLIENT_COL_STATE = "state";
    public static final String TABLE_CLIENT_COL_ZIP = "zip";
    public static final String TABLE_CLIENT_COL_LASTUPDATED = "last_updated";
    public static final String TABLE_CLIENT_COL_LASTUPDATEDBY = "last_updated_by";
    public static final String TABLE_CLIENT_COL_PHONE = "phone";
    //Department Table
    public static final String TABLE_DEPARTMENT = "departments";
    public static final String TABLE_DEPARTMENT_COL_ID = "departmentID"; //Primary Key
    public static final String TABLE_DEPARTMENT_COL_NAME = "name";
    //Employee Table
    public static final String TABLE_EMPLOYEE = "employees";
    public static final String TABLE_EMPLOYEE_COL_ID = "employeeID"; //Primary Key
    public static final String TABLE_EMPLOYEE_COL_FNAME = "first_name";
    public static final String TABLE_EMPLOYEE_COL_LNAME = "last_name";
    public static final String TABLE_EMPLOYEE_COL_DEPARTMENTID = "departmentID"; //Foreign Key
    public static final String TABLE_EMPLOYEE_COL_EMAIL = "email";
    public static final String TABLE_EMPLOYEE_COL_PHONE = "phone";
    public static final String TABLE_EMPLOYEE_COL_ADMIN = "admin";
    public static final String TABLE_EMPLOYEE_COL_USERNAME = "username";
    public static final String TABLE_EMPLOYEE_COL_PASSWORD = "password";
    //Item Table
    public static final String TABLE_ITEM = "items";
    public static final String TABLE_ITEM_COL_ID = "itemID"; //Primary Key
    public static final String TABLE_ITEM_COL_NAME = "name";
    public static final String TABLE_ITEM_COL_DESCRIPTION = "description";
    public static final String TABLE_ITEM_COL_QUANTITY = "quantity";
    public static final String TABLE_ITEM_COL_AORY = "adult_or_youth";
    public static final String TABLE_ITEM_COL_SIZE = "size";
    public static final String TABLE_ITEM_COL_APPLICATION = "application";
    public static final String TABLE_ITEM_COL_COLOR = "color";
    public static final String TABLE_ITEM_COL_ORDERID = "orderID"; //Foreign Key
    public static final String TABLE_ITEM_COL_VENDORID = "vendorID"; //Foreign Key
    public static final String TABLE_ITEM_COL_LASTUPDATED = "last_updated";
    public static final String TABLE_ITEM_COL_LASTUPDATEDBY = "last_updated_by";
    //Order Table
    public static final String TABLE_ORDER = "orders";
    public static final String TABLE_ORDER_COL_ID = "orderID"; //Primary Key
    public static final String TABLE_ORDER_COL_NAME = "name";
    public static final String TABLE_ORDER_COL_DATEORDERED = "date_ordered";
    public static final String TABLE_ORDER_COL_DATEDUE = "date_due";
    public static final String TABLE_ORDER_COL_DELIVERYTYPE = "delivery_type";
    public static final String TABLE_ORDER_COL_REFARTLOC = "reference_art_location";
    public static final String TABLE_ORDER_COL_DESCRIPTION = "description";
    public static final String TABLE_ORDER_COL_CLIENTID = "clientID"; //Foreign Key
    public static final String TABLE_ORDER_COL_ADDRESS = "address";
    public static final String TABLE_ORDER_COL_CITY = "city";
    public static final String TABLE_ORDER_COL_STATE = "state";
    public static final String TABLE_ORDER_COL_ZIP = "zip";
    //Point Of Contact Table
    public static final String TABLE_POC = "point_of_contacts";
    public static final String TABLE_POC_COL_ID = "pocID"; //Primary Key
    public static final String TABLE_POC_COL_FNAME = "first_name";
    public static final String TABLE_POC_COL_LNAME = "last_name";
    public static final String TABLE_POC_COL_PHONE = "phone";
    public static final String TABLE_POC_COL_EMAIL = "email";
    public static final String TABLE_POC_COL_CLIENTID = "clientID"; //Foreign Key
    //Vendor Table
    public static final String TABLE_VENDOR = "vendors";
    public static final String TABLE_VENDOR_COL_ID = "vendorID"; //Primary Key
    public static final String TABLE_VENDOR_COL_NAME = "name";
    //Request codes
    public static final int NEW_EMPLOYEE_ACTIVITY_REQUEST_CODE = 1;
    public static final int DELETE_EMPLOYEE_ACTIVITY_REQUEST_CODE = 2;
    public static final int EDIT_EMPLOYEE_ACTIVITY_REQUEST_CODE = 3;
    public static final int NEW_DEPARTMENT_ACTIVITY_REQUEST_CODE = 4;
    public static final int EDIT_DEPARTMENT_ACTIVITY_REQUEST_CODE = 5;
    public static final int DELETE_DEPARTMENT_ACTIVITY_REQUEST_CODE = 6;
    public static final int NEW_CLIENT_ACTIVITY_REQUEST_CODE = 7;
    public static final int EDIT_CLIENT_ACTIVITY_REQUEST_CODE = 8;
    public static final int DELETE_CLIENT_ACTIVITY_REQUEST_CODE = 9;
    public static final int NEW_ORDER_ACTIVITY_REQUEST_CODE = 10;
    public static final int EDIT_ORDER_ACTIVITY_REQUEST_CODE = 11;
    public static final int DELETE_ORDER_ACTIVITY_REQUEST_CODE = 12;
    public static final int NEW_POC_ACTIVITY_REQUEST_CODE = 13;
    public static final int EDIT_POC_ACTIVITY_REQUEST_CODE = 14;
    public static final int DELETE_POC_ACTIVITY_REQUEST_CODE = 15;
    public static final int NEW_REF_ART_ACTIVITY_REQUEST_CODE = 16;
    public static final int EDIT_REF_ART_ACTIVITY_REQUEST_CODE = 17;
    public static final int DELETE_REF_ART_ACTIVITY_REQUEST_CODE = 18;
    public static final int NEW_ART_ACTIVITY_REQUEST_CODE = 19;
    public static final int EDIT_ART_ACTIVITY_REQUEST_CODE = 20;
    public static final int DELETE_ART_ACTIVITY_REQUEST_CODE = 21;
    public static final int NEW_ITEM_ACTIVITY_REQUEST_CODE = 22;
    public static final int EDIT_ITEM_ACTIVITY_REQUEST_CODE = 23;
    public static final int DELETE_ITEM_ACTIVITY_REQUEST_CODE = 24;
    public static final int EDIT_SHIPPING_ACTIVITY_REQUEST_CODE = 25;
    public static final int FIND_ART_ACTIVITY_REQUEST_CODE = 26;

}
