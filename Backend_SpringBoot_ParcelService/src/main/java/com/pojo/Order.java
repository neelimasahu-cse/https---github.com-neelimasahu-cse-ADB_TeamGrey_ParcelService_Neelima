package com.pojo;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "orders")
public class Order {
    @Override
    public String toString() {
        return "Order{" +
                "id='" + id + '\'' +
                ", receiverName='" + receiverName + '\'' +
                ", receiverHouseNumber=" + receiverHouseNumber +
                ", receiverStreetName='" + receiverStreetName + '\'' +
                ", currentPLZ=" + currentPLZ +
                ", destinationPlz=" + destinationPlz +
                ", destinationCity='" + destinationCity + '\'' +
                ", currentCity='" + currentCity + '\'' +
                ", currentLatitude=" + currentLatitude +
                ", destinationLongitude=" + destinationLongitude +
                '}';
    }

    public Order(String receiverName, int receiverHouseNumber, String receiverStreetName, int currentPLZ, int destinationPlz, String destinationCity, String currentCity, int currentLatitude, int destinationLongitude) {
        this.receiverName = receiverName;
        this.receiverHouseNumber = receiverHouseNumber;
        this.receiverStreetName = receiverStreetName;
        this.currentPLZ = currentPLZ;
        this.destinationPlz = destinationPlz;
        this.destinationCity = destinationCity;
        this.currentCity = currentCity;
        this.currentLatitude = currentLatitude;
        this.destinationLongitude = destinationLongitude;
    }

    @Id
    private String id;

    private String receiverName;
    private int receiverHouseNumber;
    private String receiverStreetName;
    private int currentPLZ;
    private int destinationPlz;
    private String destinationCity;
    private String currentCity;
    private int currentLatitude;
    private int destinationLongitude;

    public Order() {
    }


    public int getLatitude() {
        return currentLatitude;
    }

    public void setLatitude(int latitude) {
        this.currentLatitude = latitude;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getReceiverName() {
        return receiverName;
    }

    public void setReceiverName(String receiverName) {
        this.receiverName = receiverName;
    }

    public int getReceiverHouseNumber() {
        return receiverHouseNumber;
    }

    public void setReceiverHouseNumber(int receiverHouseNumber) {
        this.receiverHouseNumber = receiverHouseNumber;
    }

    public String getReceiverStreetName() {
        return receiverStreetName;
    }

    public void setReceiverStreetName(String receiverStreetName) {
        this.receiverStreetName = receiverStreetName;
    }

    public int getDestinationPlz() {
        return destinationPlz;
    }

    public void setDestinationPlz(int destinationPlz) {
        this.destinationPlz = destinationPlz;
    }

    public String getCity() {
        return destinationCity;
    }

    public void setCity(String city) {
        this.destinationCity = city;
    }
    public String getCurrentCity() {
        return currentCity;
    }

    public void setCurrentCity(String currentCity) {
        this.currentCity = currentCity;
    }

    public int getCurrentPLZ() {
        return currentPLZ;
    }

    public void setCurrentPLZ(int currentPLZ) {
        this.currentPLZ = currentPLZ;
    }

    public String getDestinationCity() {
        return destinationCity;
    }

    public void setDestinationCity(String destinationCity) {
        this.destinationCity = destinationCity;
    }

    public int getCurrentLatitude() {
        return currentLatitude;
    }

    public void setCurrentLatitude(int currentLatitude) {
        this.currentLatitude = currentLatitude;
    }

    public int getDestinationLongitude() {
        return destinationLongitude;
    }

    public void setDestinationLongitude(int destinationLongitude) {
        this.destinationLongitude = destinationLongitude;
    }
}
