package com.user.UserSercice;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name="HOTELSERCICE")
public interface Hotel_service {
    @GetMapping("/hotel/{hotelId}")
     Hotel getHotel(@PathVariable String hotelId);
}
