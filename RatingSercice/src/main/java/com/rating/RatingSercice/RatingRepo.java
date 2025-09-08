package com.rating.RatingSercice;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface RatingRepo extends MongoRepository<Rating, String> {
    List<Rating>findByUserId(String userId);
      List<Rating>findByHotelId(String hotelId);
}
