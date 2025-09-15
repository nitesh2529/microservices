package com.rating.RatingSercice;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document(collection="RatingService")
public class Rating {
    @Id
     private String ratingId;
    private String userId;
    private String hotelId;
    private int rating;
    private String feelback;
}
