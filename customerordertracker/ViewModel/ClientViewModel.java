package com.example.customerordertracker.ViewModel;

import android.app.Application;

import com.example.customerordertracker.Database.Repository;
import com.example.customerordertracker.Entities.Art;
import com.example.customerordertracker.Entities.Client;

import java.util.List;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

public class ClientViewModel extends AndroidViewModel {

    private Repository mRepository;
    private LiveData<List<Client>> mAllClients;

    public ClientViewModel(Application application){
        super(application);
        mRepository = new Repository(application);
        mAllClients = mRepository.getAllClients();
    }

    public LiveData<List<Client>> getSearchedClient(String clientSearched) {
        return mRepository.getSearchedClients(clientSearched);
    }

    public LiveData<List<Client>> getAllClients() {return mAllClients;}

    public void insert(Client client) {mRepository.insertClient(client);}

    public void update(Client client) {mRepository.updateClient(client);}

    public void delete(Client client) {mRepository.deleteClient(client);}

    public int lastId() { return mAllClients.getValue().size();}

    public void deleteAllClients() {mRepository.deleteAllClients();}

}
