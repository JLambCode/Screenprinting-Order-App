package com.example.customerordertracker.Entities;

import com.example.customerordertracker.Utilities.Converters;

import java.util.Date;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.Index;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import static com.example.customerordertracker.Utilities.Constants.TABLE_CLIENT_COL_ID;
import static com.example.customerordertracker.Utilities.Constants.TABLE_ORDER;
import static com.example.customerordertracker.Utilities.Constants.TABLE_ORDER_COL_ADDRESS;
import static com.example.customerordertracker.Utilities.Constants.TABLE_ORDER_COL_CITY;
import static com.example.customerordertracker.Utilities.Constants.TABLE_ORDER_COL_CLIENTID;
import static com.example.customerordertracker.Utilities.Constants.TABLE_ORDER_COL_DATEDUE;
import static com.example.customerordertracker.Utilities.Constants.TABLE_ORDER_COL_DATEORDERED;
import static com.example.customerordertracker.Utilities.Constants.TABLE_ORDER_COL_DELIVERYTYPE;
import static com.example.customerordertracker.Utilities.Constants.TABLE_ORDER_COL_DESCRIPTION;
import static com.example.customerordertracker.Utilities.Constants.TABLE_ORDER_COL_ID;
import static com.example.customerordertracker.Utilities.Constants.TABLE_ORDER_COL_NAME;
import static com.example.customerordertracker.Utilities.Constants.TABLE_ORDER_COL_REFARTLOC;
import static com.example.customerordertracker.Utilities.Constants.TABLE_ORDER_COL_STATE;
import static com.example.customerordertracker.Utilities.Constants.TABLE_ORDER_COL_ZIP;

@Entity(tableName = TABLE_ORDER,
        foreignKeys = {
                @ForeignKey(entity = Client.class,
                    parentColumns = TABLE_CLIENT_COL_ID,
                    childColumns = TABLE_ORDER_COL_CLIENTID,
                    onUpdate = ForeignKey.CASCADE,
                    onDelete = ForeignKey.CASCADE)},
        indices = {@Index(value = TABLE_ORDER_COL_ID),@Index(value = TABLE_CLIENT_COL_ID)}
)

public class Order {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name=TABLE_ORDER_COL_ID)
    public int orderID;

    @ColumnInfo(name=TABLE_ORDER_COL_NAME)
    public String name;

    @ColumnInfo(name=TABLE_ORDER_COL_DATEORDERED)
    @TypeConverters(Converters.class)
    private Date dateOrdered;

    @ColumnInfo(name=TABLE_ORDER_COL_DATEDUE)
    @TypeConverters(Converters.class)
    private Date dateDue;

    @ColumnInfo(name=TABLE_ORDER_COL_DELIVERYTYPE)
    @NonNull
    private String deliveryType;

    @ColumnInfo(name=TABLE_ORDER_COL_REFARTLOC)
    private String refArtLoc;

    @ColumnInfo(name=TABLE_ORDER_COL_DESCRIPTION)
    private String description;

    @ColumnInfo(name=TABLE_ORDER_COL_ADDRESS)
    private String address;

    @ColumnInfo(name=TABLE_ORDER_COL_CITY)
    private String city;

    @ColumnInfo(name=TABLE_ORDER_COL_STATE)
    private String state;

    @ColumnInfo(name=TABLE_ORDER_COL_ZIP)
    private String zip;

    @ColumnInfo(name=TABLE_ORDER_COL_CLIENTID)
    @NonNull
    public int clientID;

    public Order(int orderID, String name, Date dateOrdered, Date dateDue, String deliveryType, String refArtLoc, String description, String address, String city, String state, String zip, int clientID) {
        this.orderID = orderID;
        this.name = name;
        this.dateOrdered = dateOrdered;
        this.dateDue = dateDue;
        this.deliveryType = deliveryType;
        this.refArtLoc = refArtLoc;
        this.description = description;
        this.clientID = clientID;
        this.address = address;
        this.city = city;
        this.state = state;
        this.zip = zip;
    }
    @Ignore
    public Order(int orderID, @NonNull String name, @NonNull Date dateDue, @NonNull String deliveryType, String refArtLoc, String description, String address, String city, String state, String zip, int clientID) {
        this.orderID = orderID;
        this.name = name;
        this.dateDue = dateDue;
        this.deliveryType = deliveryType;
        this.refArtLoc = refArtLoc;
        this.description = description;
        this.address = address;
        this.city = city;
        this.state = state;
        this.zip = zip;
        this.clientID = clientID;
    }

    @Ignore
    public Order(int orderID, String name) {
        this.orderID = orderID;
        this.name = name;
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderID=" + orderID +
                ", name='" + name + '\'' +
                ", dateOrdered=" + dateOrdered +
                ", dateDue=" + dateDue +
                ", deliveryType='" + deliveryType + '\'' +
                ", description='" + description + '\'' +
                ", address='" + address + '\'' +
                ", city='" + city + '\'' +
                ", state='" + state + '\'' +
                ", zip='" + zip +
                '}' + "\n\n";
    }

    public int getOrderID() {
        return orderID;
    }

    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDateOrdered() {
        return dateOrdered;
    }

    public void setDateOrdered(Date dateOrdered) {
        this.dateOrdered = dateOrdered;
    }

    public Date getDateDue() {
        return dateDue;
    }

    public void setDateDue(Date dateDue) {
        this.dateDue = dateDue;
    }

    public String getDeliveryType() {
        return deliveryType;
    }

    public void setDeliveryType(String deliveryType) {
        this.deliveryType = deliveryType;
    }

    public String getRefArtLoc() {
        return refArtLoc;
    }

    public void setRefArtLoc(String refArtLoc) {
        this.refArtLoc = refArtLoc;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public int getClientID() {
        return clientID;
    }

    public void setClientID(int clientID) {
        this.clientID = clientID;
    }
}
