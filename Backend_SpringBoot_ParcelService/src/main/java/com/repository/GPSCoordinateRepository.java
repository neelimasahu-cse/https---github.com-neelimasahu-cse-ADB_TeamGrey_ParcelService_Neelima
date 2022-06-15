package com.repository;

import com.pojo.GPSCoordinate;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GPSCoordinateRepository extends MongoRepository<GPSCoordinate,String> {
}
