package com.example.customerordertracker.Entities;


import com.example.customerordertracker.Utilities.Converters;

import java.util.Date;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.Index;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import static com.example.customerordertracker.Utilities.Constants.TABLE_CLIENT;
import static com.example.customerordertracker.Utilities.Constants.TABLE_CLIENT_COL_ADDRESS;
import static com.example.customerordertracker.Utilities.Constants.TABLE_CLIENT_COL_CITY;
import static com.example.customerordertracker.Utilities.Constants.TABLE_CLIENT_COL_ID;
import static com.example.customerordertracker.Utilities.Constants.TABLE_CLIENT_COL_LASTUPDATED;
import static com.example.customerordertracker.Utilities.Constants.TABLE_CLIENT_COL_LASTUPDATEDBY;
import static com.example.customerordertracker.Utilities.Constants.TABLE_CLIENT_COL_NAME;
import static com.example.customerordertracker.Utilities.Constants.TABLE_CLIENT_COL_PHONE;
import static com.example.customerordertracker.Utilities.Constants.TABLE_CLIENT_COL_STATE;
import static com.example.customerordertracker.Utilities.Constants.TABLE_CLIENT_COL_ZIP;

@Entity(tableName = TABLE_CLIENT,
        indices = {@Index(value = TABLE_CLIENT_COL_ID)}
)

public class Client {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name=TABLE_CLIENT_COL_ID)
    public int clientID;

    @ColumnInfo(name=TABLE_CLIENT_COL_NAME)
    @NonNull
    private String name;

    @ColumnInfo(name=TABLE_CLIENT_COL_ADDRESS)
    @NonNull
    private String address;

    @ColumnInfo(name=TABLE_CLIENT_COL_CITY)
    @NonNull
    private String city;

    @ColumnInfo(name=TABLE_CLIENT_COL_STATE)
    @NonNull
    private String state;

    @ColumnInfo(name=TABLE_CLIENT_COL_ZIP)
    @NonNull
    private String zip;

    @ColumnInfo(name=TABLE_CLIENT_COL_PHONE)
    @NonNull
    private String phone;

    public Client(int clientID, String name, String address, String city, String state, String zip, String phone) {
        this.clientID = clientID;
        this.name = name;
        this.address = address;
        this.city = city;
        this.state = state;
        this.zip = zip;
        this.phone = phone;
    }

    @Ignore
    public Client(int clientID, String name) {
        this.clientID = clientID;
        this.name = name;
    }

    @Override
    public String toString() {
        return "Client{" +
                "ClientID=" + clientID +
                ", Name='" + name + '\'' +
                ", Address='" + address + '\'' +
                ", City='" + city + '\'' +
                ", State='" + state + '\'' +
                ", Zip='" + zip + '\'' +
                '}';
    }

    public int getClientID() {
        return clientID;
    }

    public void setClientID(int clientID) {
        this.clientID = clientID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
