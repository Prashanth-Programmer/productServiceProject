package com.mario.productserviceproject.Models;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Product extends BaseModel{
    private String name;
    private Double price;
    @ManyToOne(fetch = FetchType.EAGER)
    private Category category;
    private String description;
    private String imageURL;

}
