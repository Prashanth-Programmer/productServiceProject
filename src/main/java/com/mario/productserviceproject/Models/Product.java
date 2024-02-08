package com.mario.productserviceproject.Models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Product {
    private long id;
    private String name;
    private double price;
    private Category category;
    private String description;
    private String imageURL;

}
