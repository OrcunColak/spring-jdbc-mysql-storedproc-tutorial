package com.colak.springtutorial.service;

import lombok.Data;

@Data
public class Product {
    private int productId;
    private String productName;
    private double price;
    private String availability;

}

