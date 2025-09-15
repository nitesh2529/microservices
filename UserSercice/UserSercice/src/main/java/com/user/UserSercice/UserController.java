package com.user.UserSercice;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    RestTemplate restTemplate;
   @Autowired 
   private UserRepo userRepo;
    @Autowired
    Hotel_service hotel_service;
private org.slf4j.Logger logger = LoggerFactory.getLogger(UserController.class);
    @GetMapping
    public List<User> user(){
        return userRepo.findAll();
    }
    @PostMapping
    public ResponseEntity< User> Save(@RequestBody User user){
        String random=UUID.randomUUID().toString();
        user.setUserId(random);
        User user1=userRepo.save(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(user1);
    }
     @CircuitBreaker(name="ratingHotelBreaker",fallbackMethod="ratingHotelfallback")
    // int retryCount=0;
    //  @Retry(name = "ratingHotelService", fallbackMethod = "ratingHotelfallback")
     @GetMapping("/{userId}")
    public ResponseEntity<Optional<User>> findUser(@PathVariable String userId){
        // here we use Service name in place of localhost:8080 
         
        //  logger.info("Retry count: {}", retryCount);
     Rating[] object =  restTemplate.getForObject("http://RATINGSERCICE/rating/userId/"+userId, Rating[].class);
     List <Rating>listRatingof=Arrays.stream(object ).toList();
     List<Rating>listRating= listRatingof.stream().map(rating->{
            //  Hotel object2 =  restTemplate.getForObject("http://HOTELSERCICE/hotel/"+rating.getHotelId(),Hotel.class);
            //here we using feignclient instead of restTemplate
                         Hotel object2 =  hotel_service.getHotel(rating.getHotelId());

rating.setHotel(object2 );
return rating;
     }).collect(Collectors.toList());
    //  logger.info("{}",object);
    //  System.out.println(object);
    Optional<User> findUser=userRepo.findById(userId);
    findUser.get().setRating(listRatingof);
        return ResponseEntity.ok( findUser);
    }
    //creating fallback method for circuite breaker
    public ResponseEntity<User>ratingHotelfallback(String userId,Exception ex){
        logger.info("Fallback is executed because server is down",ex.getMessage());
    User user=  User.builder()
      .email("dummy@gmail.com")
      .name("Dummy")
      .about("this user is create dummy becouse server is down")
      .userId("123")
      .build();
        return new ResponseEntity<>(user,HttpStatus.OK);
    }
}
