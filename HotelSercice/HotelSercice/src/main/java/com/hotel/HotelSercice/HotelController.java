package com.hotel.HotelSercice;

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
@RequestMapping("/hotel")
public class HotelController {
      @Autowired 
   private HotelRepo hotelRepo;

    @GetMapping
    public List<Hotel> hotel(){
        return hotelRepo.findAll();
    }
    @PostMapping
    public ResponseEntity< Hotel> Save(@RequestBody Hotel hotel){
        String random=UUID.randomUUID().toString();
        hotel.setId(random);
        Hotel hotel1=hotelRepo.save(hotel);
        return ResponseEntity.status(HttpStatus.CREATED).body(hotel1);
    }
     @GetMapping("/{id}")
    public Optional<Hotel> findUser(@PathVariable String id){
        return hotelRepo.findById(id);
    }
}
