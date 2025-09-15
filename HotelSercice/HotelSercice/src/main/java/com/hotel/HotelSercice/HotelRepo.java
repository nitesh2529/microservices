package com.hotel.HotelSercice;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface HotelRepo extends MongoRepository<Hotel, String>{
    
}
