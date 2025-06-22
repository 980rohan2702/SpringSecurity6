package com.loginApp.spring_security_6.controller;

import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductConroller {
    private record Product(Integer productId , String productName , double price){}

    //Mock Data
     List<Product> products = new ArrayList<>(
             List.of(
                     new Product(1,"iPhone", 999.9),
                     new Product(2,"Mac Pro",2099.9)
             )
     );

    @GetMapping
    public List<Product> getProducts(){
        return products;
    }

    @PostMapping
    public Product saveProduct(@RequestBody Product product){
        products.add(product);
        return product;
    }

}
