package com.rating.RatingSercice;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rating")
public class RatingController {
     @Autowired 
   private RatingRepo ratingRepo;

    @GetMapping
    public List<Rating> user(){
        return ratingRepo.findAll();
    }
    @PostMapping
    public ResponseEntity<Rating> Save(@RequestBody Rating rating){
        String random=UUID.randomUUID().toString();
      rating.setRatingId(random);
        Rating rating1=ratingRepo.save(rating);
        return ResponseEntity.status(HttpStatus.CREATED).body(rating1);
    }
     @GetMapping("/ratingId/{ratingId}")
    public Optional<Rating> findRating(@PathVariable String ratingId){
        return ratingRepo.findById(ratingId);
    }
     @GetMapping("/userId/{userId}")
    public List<Rating> findUser(@PathVariable String userId){
        return ratingRepo.findByUserId(userId);
    }
     @GetMapping("/hotelId/{hotelId}")
    public List<Rating> findhotel(@PathVariable String hotelId){
        return ratingRepo.findByHotelId(hotelId);
    }
}
