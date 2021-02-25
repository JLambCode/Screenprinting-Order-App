package com.example.customerordertracker.Database;

import android.app.Application;
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

import java.util.List;

import androidx.lifecycle.LiveData;

public class Repository {
    private static Repository ourInstance;

    private ArtDAO mArtDAO;
    private ClientDAO mClientDAO;
    private DepartmentDAO mDepartmentDAO;
    private EmployeeDAO mEmployeeDAO;
    private ItemDAO mItemDAO;
    private OrderDAO mOrderDAO;
    private POCDAO mPOCDAO;
    private VendorDAO mVendorDAO;

    public Art mArt;
    public Client mClient;
    public Department mDepartment;
    public Employee mEmployee;
    public Item mItem;
    public Order mOrder;
    public POC mPOC;
    public Vendor mVendor;

    public LiveData<List<Art>> mAllArt;
    public LiveData<List<Art>> mRelatedArt;
    public LiveData<List<Client>> mAllClients;
    public LiveData<List<Department>> mAllDepartments;
    public LiveData<List<Employee>> mAllEmployees;
    public LiveData<List<Employee>> mRelatedEmployees;
    public LiveData<List<Item>> mAllItems;
    public LiveData<List<Item>> mRelatedItems;
    public LiveData<List<Order>> mAllOrders;
    public LiveData<List<Order>> mRelatedOrders;
    public LiveData<List<POC>> mAllPOCs;
    public LiveData<List<POC>> mRelatedPOCs;
    public LiveData<List<Vendor>> mAllVendors;
    public LiveData<List<Order>> mSearchedOrders;
    public LiveData<List<Client>> mSearchedClients;
    private int artId;
    private int clientId;
    private int departmentId;
    private int employeeId;
    private int itemId;
    private int orderId;
    private int pocId;
    private int vendorId;
    private String username;
    private String password;
    private int admin;
    private String clientSearched;
    private String orderSearched;

    public Repository(Application application){
        Database mDb = Database.getDatabase(application);
        mArtDAO = mDb.artDAO();
        mClientDAO = mDb.clientDAO();
        mDepartmentDAO = mDb.departmentDAO();
        mEmployeeDAO = mDb.employeeDAO();
        mItemDAO = mDb.itemDAO();
        mOrderDAO = mDb.orderDAO();
        mPOCDAO = mDb.pocDAO();
        mVendorDAO = mDb.vendorDAO();

        mArt = mArtDAO.getArt(artId);
        mClient = mClientDAO.getClient(clientId);
        mDepartment = mDepartmentDAO.getDepartment(departmentId);
        mEmployee = mEmployeeDAO.getEmployee(employeeId);
        mItem = mItemDAO.getItem(itemId);
        mOrder = mOrderDAO.getOrder(orderId);
        mPOC = mPOCDAO.getPOC(pocId);
        mVendor = mVendorDAO.getVendor(vendorId);
        mAllArt = mArtDAO.getAllArt();
        mAllClients = mClientDAO.getAllClients();
        mAllDepartments = mDepartmentDAO.getAllDepartments();
        mAllEmployees = mEmployeeDAO.getAllEmployees();
        mAllItems = mItemDAO.getAllItems();
        mAllOrders = mOrderDAO.getAllOrders();
        mAllPOCs = mPOCDAO.getAllPOCs();
        mAllVendors = mVendorDAO.getAllVendors();
        mRelatedArt = mArtDAO.getRelatedArt(orderId);
        mRelatedEmployees = mEmployeeDAO.getRelatedEmployees(departmentId);
        mRelatedItems = mItemDAO.getRelatedItems(orderId);
        mRelatedOrders = mOrderDAO.getRelatedOrders(clientId);
        mRelatedPOCs = mPOCDAO.getRelatedPOCs(clientId);
        mSearchedClients = mClientDAO.getSearchedClients(clientSearched);
        mSearchedOrders = mOrderDAO.getSearchedOrders(orderSearched);

    }

    public static Repository getInstance(Application application){
        if(ourInstance == null){
            ourInstance = new Repository(application);
        }
        return ourInstance;
    }

    public Art getArt() {return mArt;}
    public Client getClient() {return mClient;}
    public Department getDepartment() {return mDepartment;}
    public Employee getEmployee() {return mEmployee;}
    public Item getItem() {return mItem;}
    public Order getOrder() {return mOrder;}
    public POC getPOC() {return mPOC;}
    public Vendor getVendor() {return mVendor;}

    public LiveData<List<Art>> getAllArt() {return mAllArt;}
    public LiveData<List<Client>> getAllClients() {return mAllClients;}
    public LiveData<List<Department>> getAllDepartments() {return mAllDepartments;}
    public LiveData<List<Employee>> getAllEmployees() {return mAllEmployees;}
    public LiveData<List<Item>> getAllItems() {return mAllItems;}
    public LiveData<List<Order>> getAllOrders() {return mAllOrders;}
    public LiveData<List<POC>> getAllPOCs() {return mAllPOCs;}
    public LiveData<List<Vendor>> getAllVendors() {return mAllVendors;}

    public LiveData<List<Art>> getRelatedArt(int orderId) {return mRelatedArt;}
    public LiveData<List<Employee>> getRelatedEmployees(int departmentId) {return mRelatedEmployees;}
    public LiveData<List<Item>> getRelatedItems(int orderId) {return mRelatedItems;}
    public LiveData<List<Order>> getRelatedOrders(int clientId) {return mRelatedOrders;}
    public LiveData<List<POC>> getRelatedPOCs(int clientId) {return mRelatedPOCs;}
    public LiveData<List<Client>> getSearchedClients(String clientSearched) {return mSearchedClients; }
    public LiveData<List<Order>> getSearchedOrders(String orderSearched) {return mSearchedOrders;}

    public void insertArt(Art art) {new insertAsyncTaskArt(mArtDAO).execute(art);}

    private static class insertAsyncTaskArt extends AsyncTask<Art, Void, Void>{
        private ArtDAO mAsyncArtDao;
        insertAsyncTaskArt(ArtDAO dao) { mAsyncArtDao = dao;}
        @Override
        protected Void doInBackground(final Art... params){
            mAsyncArtDao.insertArt(params[0]);
            return null;
        }
    }

    public void insertClient(Client client) {new insertAsyncTaskClient(mClientDAO).execute(client);}

    private static class insertAsyncTaskClient extends AsyncTask<Client, Void, Void>{
        private ClientDAO mAsyncClientDao;
        insertAsyncTaskClient(ClientDAO dao) { mAsyncClientDao = dao;}
        @Override
        protected Void doInBackground(final Client... params){
            mAsyncClientDao.insertClient(params[0]);
            return null;
        }
    }

    public void insertDepartment(Department department) {new insertAsyncTaskDepartment(mDepartmentDAO).execute(department);}

    private static class insertAsyncTaskDepartment extends AsyncTask<Department, Void, Void>{
        private DepartmentDAO mAsyncDepartmentDao;
        insertAsyncTaskDepartment(DepartmentDAO dao) { mAsyncDepartmentDao = dao;}
        @Override
        protected Void doInBackground(final Department... params){
            mAsyncDepartmentDao.insertDepartment(params[0]);
            return null;
        }
    }

    public void insertEmployee(Employee employee) {new insertAsyncTaskEmployee(mEmployeeDAO).execute(employee);}

    private static class insertAsyncTaskEmployee extends AsyncTask<Employee, Void, Void>{
        private EmployeeDAO mAsyncEmployeeDao;
        insertAsyncTaskEmployee(EmployeeDAO dao) { mAsyncEmployeeDao = dao;}
        @Override
        protected Void doInBackground(final Employee... params){
            mAsyncEmployeeDao.insertEmployee(params[0]);
            return null;
        }
    }

    public void insertItem(Item item) {new insertAsyncTaskItem(mItemDAO).execute(item);}

    private static class insertAsyncTaskItem extends AsyncTask<Item, Void, Void>{
        private ItemDAO mAsyncItemDao;
        insertAsyncTaskItem(ItemDAO dao) { mAsyncItemDao = dao;}
        @Override
        protected Void doInBackground(final Item... params){
            mAsyncItemDao.insertItem(params[0]);
            return null;
        }
    }

    public void insertOrder(Order order) {new insertAsyncTaskOrder(mOrderDAO).execute(order);}

    private static class insertAsyncTaskOrder extends AsyncTask<Order, Void, Void>{
        private OrderDAO mAsyncOrderDao;
        insertAsyncTaskOrder(OrderDAO dao) { mAsyncOrderDao = dao;}
        @Override
        protected Void doInBackground(final Order... params){
            mAsyncOrderDao.insertOrder(params[0]);
            return null;
        }
    }

    public void insertPOC(POC poc) {new insertAsyncTaskPOC(mPOCDAO).execute(poc);}

    private static class insertAsyncTaskPOC extends AsyncTask<POC, Void, Void>{
        private POCDAO mAsyncPOCDao;
        insertAsyncTaskPOC(POCDAO dao) { mAsyncPOCDao = dao;}
        @Override
        protected Void doInBackground(final POC... params){
            mAsyncPOCDao.insertPOC(params[0]);
            return null;
        }
    }

    public void insertVendor(Vendor vendor) {new insertAsyncTaskVendor(mVendorDAO).execute(vendor);}

    private static class insertAsyncTaskVendor extends AsyncTask<Vendor, Void, Void>{
        private VendorDAO mAsyncVendorDao;
        insertAsyncTaskVendor(VendorDAO dao) { mAsyncVendorDao = dao;}
        @Override
        protected Void doInBackground(final Vendor... params){
            mAsyncVendorDao.insertVendor(params[0]);
            return null;
        }
    }

    public void updateArt(Art art) {
        new updateAsyncTaskArt(mArtDAO).execute(art);
    }

    private static class updateAsyncTaskArt extends AsyncTask<Art, Void, Void> {
        private ArtDAO mAsyncTaskDao;
        updateAsyncTaskArt(ArtDAO dao) { mAsyncTaskDao = dao; }
        @Override
        protected Void doInBackground(final Art... params) {
            mAsyncTaskDao.updateArt(params[0]);
            return null;
        }
    }

    public void updateClient(Client client) {new updateAsyncTaskClient(mClientDAO).execute(client);}

    private static class updateAsyncTaskClient extends AsyncTask<Client, Void, Void>{
        private ClientDAO mAsyncClientDao;
        updateAsyncTaskClient(ClientDAO dao) { mAsyncClientDao = dao;}
        @Override
        protected Void doInBackground(final Client... params){
            mAsyncClientDao.updateClient(params[0]);
            return null;
        }
    }

    public void updateDepartment(Department department) {new updateAsyncTaskDepartment(mDepartmentDAO).execute(department);}

    private static class updateAsyncTaskDepartment extends AsyncTask<Department, Void, Void>{
        private DepartmentDAO mAsyncDepartmentDao;
        updateAsyncTaskDepartment(DepartmentDAO dao) { mAsyncDepartmentDao = dao;}
        @Override
        protected Void doInBackground(final Department... params){
            mAsyncDepartmentDao.updateDepartment(params[0]);
            return null;
        }
    }

    public void updateEmployee(Employee employee) {new updateAsyncTaskEmployee(mEmployeeDAO).execute(employee);}

    private static class updateAsyncTaskEmployee extends AsyncTask<Employee, Void, Void>{
        private EmployeeDAO mAsyncEmployeeDao;
        updateAsyncTaskEmployee(EmployeeDAO dao) { mAsyncEmployeeDao = dao;}
        @Override
        protected Void doInBackground(final Employee... params){
            mAsyncEmployeeDao.updateEmployee(params[0]);
            return null;
        }
    }

    public void updateItem(Item item) {new updateAsyncTaskItem(mItemDAO).execute(item);}

    private static class updateAsyncTaskItem extends AsyncTask<Item, Void, Void>{
        private ItemDAO mAsyncItemDao;
        updateAsyncTaskItem(ItemDAO dao) { mAsyncItemDao = dao;}
        @Override
        protected Void doInBackground(final Item... params){
            mAsyncItemDao.updateItem(params[0]);
            return null;
        }
    }

    public void updateOrder(Order order) {new updateAsyncTaskOrder(mOrderDAO).execute(order);}

    private static class updateAsyncTaskOrder extends AsyncTask<Order, Void, Void>{
        private OrderDAO mAsyncOrderDao;
        updateAsyncTaskOrder(OrderDAO dao) { mAsyncOrderDao = dao;}
        @Override
        protected Void doInBackground(final Order... params){
            mAsyncOrderDao.updateOrder(params[0]);
            return null;
        }
    }

    public void updatePOC(POC poc) {new updateAsyncTaskPOC(mPOCDAO).execute(poc);}

    private static class updateAsyncTaskPOC extends AsyncTask<POC, Void, Void>{
        private POCDAO mAsyncPOCDao;
        updateAsyncTaskPOC(POCDAO dao) { mAsyncPOCDao = dao;}
        @Override
        protected Void doInBackground(final POC... params){
            mAsyncPOCDao.updatePOC(params[0]);
            return null;
        }
    }

    public void updateVendor(Vendor vendor) {new updateAsyncTaskVendor(mVendorDAO).execute(vendor);}

    private static class updateAsyncTaskVendor extends AsyncTask<Vendor, Void, Void>{
        private VendorDAO mAsyncVendorDao;
        updateAsyncTaskVendor(VendorDAO dao) { mAsyncVendorDao = dao;}
        @Override
        protected Void doInBackground(final Vendor... params){
            mAsyncVendorDao.updateVendor(params[0]);
            return null;
        }
    }

    public void deleteArt(Art art) {
        new deleteAsyncTaskArt(mArtDAO).execute(art);
    }

    private static class deleteAsyncTaskArt extends AsyncTask<Art, Void, Void> {
        private ArtDAO mAsyncTaskDao;
        deleteAsyncTaskArt(ArtDAO dao) { mAsyncTaskDao = dao; }
        @Override
        protected Void doInBackground(final Art... params) {
            mAsyncTaskDao.deleteArt(params[0]);
            return null;
        }
    }

    public void deleteClient(Client client) {new deleteAsyncTaskClient(mClientDAO).execute(client);}

    private static class deleteAsyncTaskClient extends AsyncTask<Client, Void, Void>{
        private ClientDAO mAsyncClientDao;
        deleteAsyncTaskClient(ClientDAO dao) { mAsyncClientDao = dao;}
        @Override
        protected Void doInBackground(final Client... params){
            mAsyncClientDao.deleteClient(params[0]);
            return null;
        }
    }

    public void deleteDepartment(Department department) {new deleteAsyncTaskDepartment(mDepartmentDAO).execute(department);}

    private static class deleteAsyncTaskDepartment extends AsyncTask<Department, Void, Void>{
        private DepartmentDAO mAsyncDepartmentDao;
        deleteAsyncTaskDepartment(DepartmentDAO dao) { mAsyncDepartmentDao = dao;}
        @Override
        protected Void doInBackground(final Department... params){
            mAsyncDepartmentDao.deleteDepartment(params[0]);
            return null;
        }
    }

    public void deleteEmployee(Employee employee) {new deleteAsyncTaskEmployee(mEmployeeDAO).execute(employee);}

    private static class deleteAsyncTaskEmployee extends AsyncTask<Employee, Void, Void>{
        private EmployeeDAO mAsyncEmployeeDao;
        deleteAsyncTaskEmployee(EmployeeDAO dao) { mAsyncEmployeeDao = dao;}
        @Override
        protected Void doInBackground(final Employee... params){
            mAsyncEmployeeDao.deleteEmployee(params[0]);
            return null;
        }
    }

    public void deleteItem(Item item) {new deleteAsyncTaskItem(mItemDAO).execute(item);}

    private static class deleteAsyncTaskItem extends AsyncTask<Item, Void, Void>{
        private ItemDAO mAsyncItemDao;
        deleteAsyncTaskItem(ItemDAO dao) { mAsyncItemDao = dao;}
        @Override
        protected Void doInBackground(final Item... params){
            mAsyncItemDao.deleteItem(params[0]);
            return null;
        }
    }

    public void deleteOrder(Order order) {new deleteAsyncTaskOrder(mOrderDAO).execute(order);}

    private static class deleteAsyncTaskOrder extends AsyncTask<Order, Void, Void>{
        private OrderDAO mAsyncOrderDao;
        deleteAsyncTaskOrder(OrderDAO dao) { mAsyncOrderDao = dao;}
        @Override
        protected Void doInBackground(final Order... params){
            mAsyncOrderDao.deleteOrder(params[0]);
            return null;
        }
    }

    public void deletePOC(POC poc) {new deleteAsyncTaskPOC(mPOCDAO).execute(poc);}

    private static class deleteAsyncTaskPOC extends AsyncTask<POC, Void, Void>{
        private POCDAO mAsyncPOCDao;
        deleteAsyncTaskPOC(POCDAO dao) { mAsyncPOCDao = dao;}
        @Override
        protected Void doInBackground(final POC... params){
            mAsyncPOCDao.deletePOC(params[0]);
            return null;
        }
    }

    public void deleteVendor(Vendor vendor) {new deleteAsyncTaskVendor(mVendorDAO).execute(vendor);}

    private static class deleteAsyncTaskVendor extends AsyncTask<Vendor, Void, Void>{
        private VendorDAO mAsyncVendorDao;
        deleteAsyncTaskVendor(VendorDAO dao) { mAsyncVendorDao = dao;}
        @Override
        protected Void doInBackground(final Vendor... params){
            mAsyncVendorDao.deleteVendor(params[0]);
            return null;
        }
    }

    public void deleteAllArt() {
        new deleteAllAsyncTaskArt(mArtDAO).execute();
    }

    private static class deleteAllAsyncTaskArt extends AsyncTask<Art, Void, Void> {
        private ArtDAO mAsyncTaskDao;
        deleteAllAsyncTaskArt(ArtDAO dao) { mAsyncTaskDao = dao; }
        @Override
        protected Void doInBackground(final Art... params) {
            mAsyncTaskDao.deleteAllArt();
            return null;
        }
    }

    public void deleteAllClients() {new deleteAllAsyncTaskClient(mClientDAO).execute();}

    private static class deleteAllAsyncTaskClient extends AsyncTask<Client, Void, Void>{
        private ClientDAO mAsyncClientDao;
        deleteAllAsyncTaskClient(ClientDAO dao) { mAsyncClientDao = dao;}
        @Override
        protected Void doInBackground(final Client... params){
            mAsyncClientDao.deleteAllClients();
            return null;
        }
    }

    public void deleteAllDepartments() {new deleteAllAsyncTaskDepartment(mDepartmentDAO).execute();}

    private static class deleteAllAsyncTaskDepartment extends AsyncTask<Department, Void, Void>{
        private DepartmentDAO mAsyncDepartmentDao;
        deleteAllAsyncTaskDepartment(DepartmentDAO dao) { mAsyncDepartmentDao = dao;}
        @Override
        protected Void doInBackground(final Department... params){
            mAsyncDepartmentDao.deleteAllDepartments();
            return null;
        }
    }

    public void deleteAllEmployees() {new deleteAllAsyncTaskEmployee(mEmployeeDAO).execute();}

    private static class deleteAllAsyncTaskEmployee extends AsyncTask<Employee, Void, Void>{
        private EmployeeDAO mAsyncEmployeeDao;
        deleteAllAsyncTaskEmployee(EmployeeDAO dao) { mAsyncEmployeeDao = dao;}
        @Override
        protected Void doInBackground(final Employee... params){
            mAsyncEmployeeDao.deleteAllEmployees();
            return null;
        }
    }

    public void deleteAllItems() {new deleteAllAsyncTaskItem(mItemDAO).execute();}

    private static class deleteAllAsyncTaskItem extends AsyncTask<Item, Void, Void>{
        private ItemDAO mAsyncItemDao;
        deleteAllAsyncTaskItem(ItemDAO dao) { mAsyncItemDao = dao;}
        @Override
        protected Void doInBackground(final Item... params){
            mAsyncItemDao.deleteAllItems();
            return null;
        }
    }

    public void deleteAllOrders() {new deleteAllAsyncTaskOrder(mOrderDAO).execute();}

    private static class deleteAllAsyncTaskOrder extends AsyncTask<Order, Void, Void>{
        private OrderDAO mAsyncOrderDao;
        deleteAllAsyncTaskOrder(OrderDAO dao) { mAsyncOrderDao = dao;}
        @Override
        protected Void doInBackground(final Order... params){
            mAsyncOrderDao.deleteAllOrders();
            return null;
        }
    }

    public void deleteAllPOCs() {new deleteAllAsyncTaskPOC(mPOCDAO).execute();}

    private static class deleteAllAsyncTaskPOC extends AsyncTask<POC, Void, Void>{
        private POCDAO mAsyncPOCDao;
        deleteAllAsyncTaskPOC(POCDAO dao) { mAsyncPOCDao = dao;}
        @Override
        protected Void doInBackground(final POC... params){
            mAsyncPOCDao.deleteAllPOCs();
            return null;
        }
    }

    public void deleteAllVendors() {new deleteAllAsyncTaskVendor(mVendorDAO).execute();}

    private static class deleteAllAsyncTaskVendor extends AsyncTask<Vendor, Void, Void>{
        private VendorDAO mAsyncVendorDao;
        deleteAllAsyncTaskVendor(VendorDAO dao) { mAsyncVendorDao = dao;}
        @Override
        protected Void doInBackground(final Vendor... params){
            mAsyncVendorDao.deleteAllVendors();
            return null;
        }
    }

}
