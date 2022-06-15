package com.controller;

import com.pojo.GPSCoordinate;
import com.pojo.Order;
import com.repository.GPSCoordinateRepository;
import com.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
public class GPSCoordinateController {

    @Autowired
    private GPSCoordinateRepository gpsCoordinateRepository;

    //now create  method to fetch the coordinates from database
    @GetMapping("/listCoordinates")
    public List<GPSCoordinate> listCoordinates(){
        return gpsCoordinateRepository.findAll();
    }

}
