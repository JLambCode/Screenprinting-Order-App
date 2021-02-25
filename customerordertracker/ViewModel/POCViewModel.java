package com.example.customerordertracker.ViewModel;

import android.app.Application;

import com.example.customerordertracker.Database.Repository;
import com.example.customerordertracker.Entities.POC;

import java.util.List;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

public class POCViewModel extends AndroidViewModel {

    public int clientId;
    private Repository mRepository;
    private LiveData<List<POC>> mAllPOCs;
    private LiveData<List<POC>> mRelatedPOCs;

    public POCViewModel(Application application){
        super(application);
        mRepository = new Repository(application);
        mAllPOCs = mRepository.getAllPOCs();
        mRelatedPOCs = mRepository.getRelatedPOCs(clientId);
    }

    public LiveData<List<POC>> getRelatedPOCs(int clientId){
        return mRepository.getRelatedPOCs(clientId);
    }

    public LiveData<List<POC>> getAllPOC() {return mAllPOCs;}

    public void insert(POC poc) {mRepository.insertPOC(poc);}

    public void update(POC poc) {mRepository.updatePOC(poc);}

    public void delete(POC poc) {mRepository.deletePOC(poc);}

    public int lastId() { return mAllPOCs.getValue().size();}

    public void deleteAllPOCs() {mRepository.deleteAllPOCs();}
}
