package com.example.Hotel;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Room {
     private String type;
     private String imgUrI;
    private String description;
    private String price;
}
