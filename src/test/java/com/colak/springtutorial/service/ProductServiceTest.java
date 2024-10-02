package com.colak.springtutorial.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class ProductServiceTest {

    @Autowired
    private ProductService productService;

    @Test
    public void testGetDistinctProducts() {
        // Arrange
        String category = "Electronics";
        double minPrice = 500;
        double maxPrice = 1500;
        String availability = "In Stock";

        List<Product> products = productService.getDistinctProducts(category, minPrice, maxPrice, availability);

        Product laptop = new Product();
        laptop.setProductId(1);
        laptop.setProductName("Laptop");
        laptop.setPrice(1000);
        laptop.setAvailability("In Stock");

        Product smartPhone = new Product();
        smartPhone.setProductId(2);
        smartPhone.setProductName("Smartphone");
        smartPhone.setPrice(700);
        smartPhone.setAvailability("In Stock");

        assertThat(products).containsOnly(laptop, smartPhone);

    }
}