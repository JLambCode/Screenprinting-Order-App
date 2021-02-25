package com.example.customerordertracker.Entities;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import static com.example.customerordertracker.Utilities.Constants.TABLE_CLIENT_COL_ID;
import static com.example.customerordertracker.Utilities.Constants.TABLE_ORDER_COL_ID;
import static com.example.customerordertracker.Utilities.Constants.TABLE_POC;
import static com.example.customerordertracker.Utilities.Constants.TABLE_POC_COL_CLIENTID;
import static com.example.customerordertracker.Utilities.Constants.TABLE_POC_COL_EMAIL;
import static com.example.customerordertracker.Utilities.Constants.TABLE_POC_COL_FNAME;
import static com.example.customerordertracker.Utilities.Constants.TABLE_POC_COL_ID;
import static com.example.customerordertracker.Utilities.Constants.TABLE_POC_COL_LNAME;
import static com.example.customerordertracker.Utilities.Constants.TABLE_POC_COL_PHONE;

@Entity(tableName = TABLE_POC,
        foreignKeys = {@ForeignKey(
                entity = Client.class,
                parentColumns = TABLE_CLIENT_COL_ID,
                childColumns = TABLE_POC_COL_CLIENTID,
                onUpdate = ForeignKey.CASCADE,
                onDelete = ForeignKey.CASCADE)},
        indices = {@Index(value = TABLE_POC_COL_ID),@Index(value = TABLE_CLIENT_COL_ID)}
)

public class POC {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name=TABLE_POC_COL_ID)
    public int pocid;

    @ColumnInfo(name=TABLE_POC_COL_FNAME)
    @NonNull
    private String fName;

    @ColumnInfo(name=TABLE_POC_COL_LNAME)
    @NonNull
    private String lName;

    @ColumnInfo(name=TABLE_POC_COL_PHONE)
    private String phone;

    @ColumnInfo(name=TABLE_POC_COL_EMAIL)
    private String email;

    @ColumnInfo(name=TABLE_POC_COL_CLIENTID)
    @NonNull
    public int clientID;

    public POC(int pocid, String fName, String lName, String phone, String email, int clientID) {
        this.pocid = pocid;
        this.fName = fName;
        this.lName = lName;
        this.phone = phone;
        this.email = email;
        this.clientID = clientID;
    }

    @Override
    public String toString() {
        return "POC{" +
                "pocid=" + pocid +
                ", fName='" + fName + '\'' +
                ", lName='" + lName + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", clientID=" + clientID +
                '}';
    }

    public int getPocid() {
        return pocid;
    }

    public void setPocid(int pocid) {
        this.pocid = pocid;
    }

    public String getFName() {
        return fName;
    }

    public void setFName(String fName) {
        this.fName = fName;
    }

    public String getLName() {
        return lName;
    }

    public void setLName(String lName) {
        this.lName = lName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getClientID() {
        return clientID;
    }

    public void setClientID(int clientID) {
        this.clientID = clientID;
    }
}
