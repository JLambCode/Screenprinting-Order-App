package com.example.customerordertracker.Database;


import android.content.Context;
import android.os.AsyncTask;

import com.example.customerordertracker.DAO.ArtDAO;
import com.example.customerordertracker.DAO.ClientDAO;
import com.example.customerordertracker.DAO.DepartmentDAO;
import com.example.customerordertracker.DAO.EmployeeDAO;
import com.example.customerordertracker.DAO.ItemDAO;
import com.example.customerordertracker.DAO.OrderDAO;
import com.example.customerordertracker.DAO.POCDAO;
import com.example.customerordertracker.DAO.VendorDAO;
import com.example.customerordertracker.Entities.Art;
import com.example.customerordertracker.Entities.Client;
import com.example.customerordertracker.Entities.Department;
import com.example.customerordertracker.Entities.Employee;
import com.example.customerordertracker.Entities.Item;
import com.example.customerordertracker.Entities.Order;
import com.example.customerordertracker.Entities.POC;
import com.example.customerordertracker.Entities.Vendor;
import com.example.customerordertracker.Utilities.Converters;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import androidx.annotation.NonNull;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;
import androidx.sqlite.db.SupportSQLiteDatabase;

import static com.example.customerordertracker.Utilities.Constants.DATABASE_NAME;
import static com.example.customerordertracker.Utilities.Constants.DATABASE_VERSION;

@androidx.room.Database(entities = {Art.class, Client.class, Department.class, Employee.class, Item.class, Order.class, POC.class, Vendor.class}, version = DATABASE_VERSION, exportSchema = false)
@TypeConverters(Converters.class)
public abstract class Database extends RoomDatabase {

    public abstract ArtDAO artDAO();
    public abstract ClientDAO clientDAO();
    public abstract DepartmentDAO departmentDAO();
    public abstract EmployeeDAO employeeDAO();
    public abstract ItemDAO itemDAO();
    public abstract OrderDAO orderDAO();
    public abstract POCDAO pocDAO();
    public abstract VendorDAO vendorDAO();

    private static volatile Database INSTANCE;
    private static final Object LOCK = new Object();
    private static final int NUMBER_OF_THREADS = 4;

    static final ExecutorService dbWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    static Database getDatabase(Context context){
        if(INSTANCE == null){
            synchronized (Database.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(), Database.class, DATABASE_NAME)
                            .fallbackToDestructiveMigration()
                            .allowMainThreadQueries()
                            .addCallback(mRoomDatabaseCallback)
                            .build();

                    //Code below maintains business structure per current business requirements
                    //DO NOT DELETE
                    if(INSTANCE.vendorDAO().getVendorCount() < 1) {
                        Vendor sanmar = new Vendor(1, "Sanmar");
                        Vendor broderbrothers = new Vendor(2, "Broder Brothers");
                        Vendor ottocap = new Vendor(3, "Ottocap");
                        INSTANCE.vendorDAO().insertVendor(sanmar);
                        INSTANCE.vendorDAO().insertVendor(broderbrothers);
                        INSTANCE.vendorDAO().insertVendor(ottocap);
                    }
                    if(INSTANCE.departmentDAO().getDepartmentCount() < 1) {
                        Department management = new Department(1, "Management");
                        Department sales = new Department(2, "Sales");
                        Department art = new Department(3, "Art");
                        Department print = new Department(4, "Printing");
                        Department embroidery = new Department(5, "Embroidery");
                        Department shipping = new Department(6, "Shipping");
                        INSTANCE.departmentDAO().insertDepartment(management);
                        INSTANCE.departmentDAO().insertDepartment(sales);
                        INSTANCE.departmentDAO().insertDepartment(art);
                        INSTANCE.departmentDAO().insertDepartment(print);
                        INSTANCE.departmentDAO().insertDepartment(embroidery);
                        INSTANCE.departmentDAO().insertDepartment(shipping);
                    }
                    if(INSTANCE.employeeDAO().getEmployeeCount() < 1) {
                        Employee admin = new Employee(1, "Admin", "Admin", "Admin@NthPlanet.com", "555-555-5555", 1, 1, "Admin", "Pass");
                        INSTANCE.employeeDAO().insertEmployee(admin);
                    }
                    //Code above maintains business structure per current business requirements
                    //DO NOT DELETE

                }
            }
        }
        return INSTANCE;
    }

    private static RoomDatabase.Callback mRoomDatabaseCallback = new RoomDatabase.Callback() {
        //@Override
        //public void onCreate(@NonNull SupportSQLiteDatabase db){
        //    super.onCreate(db);
        //    new PopulateDbAsync(INSTANCE).execute();
        //}
        @Override
        public void onOpen(@NonNull SupportSQLiteDatabase db){
            super.onOpen(db);
            new PopulateDbAsync(INSTANCE).execute();
        }
    };

    private static class PopulateDbAsync extends AsyncTask<Void, Void, Void> {
        private final ArtDAO mArtDAO;
        private final ClientDAO mClientDAO;
        private final DepartmentDAO mDepartmentDAO;
        private final EmployeeDAO mEmployeeDAO;
        private final ItemDAO mItemDAO;
        private final OrderDAO mOrderDAO;
        private final POCDAO mPOCDAO;
        private final VendorDAO mVendorDAO;

        PopulateDbAsync(Database db){
            mArtDAO = db.artDAO();
            mClientDAO = db.clientDAO();
            mDepartmentDAO = db.departmentDAO();
            mEmployeeDAO = db.employeeDAO();
            mItemDAO = db.itemDAO();
            mOrderDAO = db.orderDAO();
            mPOCDAO = db.pocDAO();
            mVendorDAO = db.vendorDAO();
        }

        @Override
        protected Void doInBackground(final Void... params) { return null; }
    }

}
