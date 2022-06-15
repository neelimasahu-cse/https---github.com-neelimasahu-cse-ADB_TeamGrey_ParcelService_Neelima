package com.pojo;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "gpsCoordinates")
//@Document(collection="testCoordinates")
public class GPSCoordinate {
    @Id
    private String id;
    private String ln;
    private String lng;

    public GPSCoordinate(String ln, String lng) {

        this.ln = ln;
        this.lng = lng;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLn() {
        return ln;
    }

    public void setLn(String ln) {
        this.ln = ln;
    }

    public String getLng() {
        return lng;
    }

    public void setLng(String lng) {
        this.lng = lng;
    }
}
