package ru.geekbrains.homework.repo;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import ru.geekbrains.homework.entity.Product;

import java.util.ArrayList;
import java.util.List;

@Component
@Qualifier("productRepository")
public class ProductRepository {

    private List<Product> productList;

    public ProductRepository() {
        productList = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            productList.add(new Product(i));
        }
    }

    @Override
    public String toString() {
        return "ProductRepository{" +
                "productList=" + productList +
                '}';
    }

    public List<Product> getProductList() {
        return productList;
    }

    public Product getProductListById(int id) {
        Product product = null;
        for (int i = 0; i < productList.size(); i++) {
            if(id == productList.get(i).getId()){
                product = productList.get(i);
            }
        }
        return product;
    }

    public void deleteProductListById(int id) {
        for (int i = 0; i < productList.size(); i++) {
            if(id == productList.get(i).getId()){
                productList.remove(i);
            }
        }
    }
}

