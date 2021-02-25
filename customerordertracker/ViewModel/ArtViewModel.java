package com.example.customerordertracker.ViewModel;

import android.app.Application;

import com.example.customerordertracker.Database.Repository;
import com.example.customerordertracker.Entities.Art;

import java.util.List;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

public class ArtViewModel extends AndroidViewModel {

    public int orderId;
    private Repository mRepository;
    private LiveData<List<Art>> mAllArt;
    private LiveData<List<Art>> mRelatedArt;

    public ArtViewModel(Application application){
        super(application);
        mRepository = new Repository(application);
        mAllArt = mRepository.getAllArt();
        mRelatedArt = mRepository.getRelatedArt(orderId);
    }

    public LiveData<List<Art>> getRelatedArt(int orderId){
        return mRepository.getRelatedArt(orderId);
    }

    public LiveData<List<Art>> getAllArt() {return mAllArt;}

    public void insert(Art art) {mRepository.insertArt(art);}

    public void update(Art art) {mRepository.updateArt(art);}

    public void delete(Art art) {mRepository.deleteArt(art);}

    public int lastId() { return mAllArt.getValue().size();}

    public void deleteAllArt() {mRepository.deleteAllArt();}

}
