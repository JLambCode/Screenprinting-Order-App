package com.example.customerordertracker.ViewModel;

import android.app.Application;

import com.example.customerordertracker.Database.Repository;
import com.example.customerordertracker.Entities.Item;

import java.util.List;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

public class ItemViewModel extends AndroidViewModel {

    public int orderId;
    public int vendorId;
    private Repository mRepository;
    private LiveData<List<Item>> mAllItems;
    private LiveData<List<Item>> mRelatedItems;

    public ItemViewModel(Application application){
        super(application);
        mRepository = new Repository(application);
        mAllItems = mRepository.getAllItems();
        mRelatedItems = mRepository.getRelatedItems(orderId);
    }

    public LiveData<List<Item>> getRelatedItems(int orderId){
        return mRepository.getRelatedItems(orderId);
    }

    public LiveData<List<Item>> getAllItems() {return mAllItems;}

    public void insert(Item item) {mRepository.insertItem(item);}

    public void update(Item item) {mRepository.updateItem(item);}

    public void delete(Item item) {mRepository.deleteItem(item);}

    public int lastId() { return mAllItems.getValue().size();}

    public void deleteAllItems() {mRepository.deleteAllItems();}
}
