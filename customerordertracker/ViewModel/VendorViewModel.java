package com.example.customerordertracker.ViewModel;

import android.app.Application;

import com.example.customerordertracker.Database.Repository;
import com.example.customerordertracker.Entities.Vendor;

import java.util.List;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

public class VendorViewModel extends AndroidViewModel {

    private Repository mRepository;
    private LiveData<List<Vendor>> mAllVendors;

    public VendorViewModel(Application application){
        super(application);
        mRepository = new Repository(application);
        mAllVendors = mRepository.getAllVendors();
    }

    public LiveData<List<Vendor>> getAllVendors() {return mAllVendors;}

    public void insert(Vendor vendor) {mRepository.insertVendor(vendor);}

    public void update(Vendor vendor) {mRepository.updateVendor(vendor);}

    public void delete(Vendor vendor) {mRepository.deleteVendor(vendor);}

    public int lastId() { return mAllVendors.getValue().size();}

    public void deleteAllVendors() {mRepository.deleteAllVendors();}
}
