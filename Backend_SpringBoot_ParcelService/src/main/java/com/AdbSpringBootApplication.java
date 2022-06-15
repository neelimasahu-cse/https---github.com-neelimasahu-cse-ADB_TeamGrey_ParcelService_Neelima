package com;

import com.google.gson.Gson;
import com.pojo.GPSCoordinate;
import com.pojo.Order;
import com.repository.GPSCoordinateRepository;
import com.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.boot.autoconfigure.batch.BatchProperties;

import org.springframework.boot.autoconfigure.batch.JobLauncherApplicationRunner;
import org.springframework.boot.json.GsonJsonParser;
import org.springframework.context.annotation.Bean;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@SpringBootApplication
public class AdbSpringBootApplication implements CommandLineRunner {

    public static void main(String[] args) {

        SpringApplication.run(AdbSpringBootApplication.class, args);

    }
    @Autowired
    private GPSCoordinateRepository gpsCoordinateRepository;
    @Autowired
    private OrderRepository orderRepository;

    //create an Order
    private void createOrder(){
        String jsonObject = "{\n" +
                "  \"receiverName\": \"Neelima\",\n" +
                "  \"receiverHouseNumber\": 13,\n" +
                "  \"receiverStreetName\": \"Im Mellsig\",\n" +
                "  \"currentPLZ\": 60433,\n" +
                "  \"destinationPlz\": 60314,\n" +
                "  \"destinationCity\": \"Frankfurt am Main\",\n" +
                "  \"currentCity\": \"Berlin\",\n" +
                "  \"currentLatitude\": 50,\n" +
                "  \"destinationLongitude\": 8,\n" +
                "  \"_class\": \"com.pojo.Order\"\n" +
                "}";
        Order order = new Gson().fromJson(jsonObject,Order.class);
        orderRepository.insert(order);
    }

    @Override
    public void run(String... args) throws Exception {
        //
        createOrder();

        String line="";
        ArrayList<GPSCoordinate> coordinates= new ArrayList<>();
        BufferedReader reader = null;

        try {
            reader = new BufferedReader(new FileReader("src/main/resources/coordinates2.csv"));
            line = reader.readLine();
            while((line=reader.readLine())!=null){
                String[] eachCoordinate = line.split(",");
                GPSCoordinate coordinate = new GPSCoordinate(eachCoordinate[0],eachCoordinate[1]);
                coordinates.add(coordinate);
            }
        gpsCoordinateRepository.insert(coordinates);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        catch (IOException e) {
            e.printStackTrace();
        };

    }
}
