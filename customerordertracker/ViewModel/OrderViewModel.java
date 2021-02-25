package com.example.customerordertracker.ViewModel;

import android.app.Application;

import com.example.customerordertracker.Database.Repository;
import com.example.customerordertracker.Entities.Order;

import java.util.List;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

public class OrderViewModel extends AndroidViewModel {

    public int clientId;
    private Repository mRepository;
    private LiveData<List<Order>> mAllOrders;
    private LiveData<List<Order>> mRelatedOrders;

    public OrderViewModel(Application application){
        super(application);
        mRepository = new Repository(application);
        mAllOrders = mRepository.getAllOrders();
        mRelatedOrders = mRepository.getRelatedOrders(clientId);
    }

    public LiveData<List<Order>> getRelatedOrders(int clientId){
        return mRepository.getRelatedOrders(clientId);
    }

    public LiveData<List<Order>> getSearchedOrders(String orderSearched){
        return mRepository.getSearchedOrders(orderSearched);
    }

    public LiveData<List<Order>> getAllOrders() {return mAllOrders;}

    public void insert(Order order) {mRepository.insertOrder(order);}

    public void update(Order order) {mRepository.updateOrder(order);}

    public void delete(Order order) {mRepository.deleteOrder(order);}

    public int lastId() { return mAllOrders.getValue().size();}

    public void deleteAllOrders() {mRepository.deleteAllOrders();}
}
