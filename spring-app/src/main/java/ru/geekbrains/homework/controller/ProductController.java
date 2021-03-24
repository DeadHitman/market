package ru.geekbrains.homework.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.homework.dao.ProductDao;
import ru.geekbrains.homework.entity.Product;
import ru.geekbrains.homework.repo.ProductRepositrory;

import java.util.List;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductRepositrory productRepositrory;


    @GetMapping(value = "/getProductBy/{id}")
    public Product findById(@PathVariable long id) {
        return productRepositrory.findById(id).get();
    }

    @GetMapping(value = "/getProductList", produces = {"application/json"})
    public List<Product> findAll() {
        return (List<Product>) productRepositrory.findAll();
    }

    @PostMapping(value = "/createProduct", produces = {"application/json"})
    @ResponseStatus(HttpStatus.CREATED)
    public void createProduct(@RequestBody Product product) {
        productRepositrory.save(product);
    }

    @GetMapping(value = "/deleteProductBy/{id}")
    public void deleteProductById(@PathVariable long id) {
        productRepositrory.deleteById(id);
    }

}
