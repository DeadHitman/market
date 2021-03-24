package ru.geekbrains.homework.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import ru.geekbrains.homework.entity.Product;

@RestController
public class ProductController {

    @GetMapping(value = "/json",produces = "application/json")
    public Product returnProductJson(){
        Product product = new Product(1L,"Product-1",100);

        return product;

    }

    @GetMapping(value = "/xml",produces = "applicaton/xml")
    public String returnProductXml(){
        Product product = new Product(5L,"Product-3",1000);

        return product.toString();

    }

    @GetMapping(value = "/test", produces = MediaType.APPLICATION_JSON_VALUE)
    public String test(){
        return "test";


    }

}
