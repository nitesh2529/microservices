package com.example.Hotel;

import java.util.ArrayList;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;
import org.springframework.data.mongodb.core.index.GeoSpatialIndexType;
import org.springframework.data.mongodb.core.index.GeoSpatialIndexed;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Document(collection = "hotels")
@Data
public class Hotel {
    @Id
    private String id;
private String imageUrl;
    private String name;
    private String address;
    private double rating;
    private String[] gallery;
    private String price;
   private ArrayList<Room> rooms;
private String[] amenities;
    @GeoSpatialIndexed(type = GeoSpatialIndexType.GEO_2DSPHERE)
    private GeoJsonPoint location;

    // Getters & setters
}

