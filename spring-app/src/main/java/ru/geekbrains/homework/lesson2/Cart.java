package ru.geekbrains.homework.lesson2;

import org.springframework.beans.factory.annotation.Autowired;
import ru.geekbrains.homework.lesson2.entity.Product;

import java.util.ArrayList;
import java.util.List;

public class Cart {

    @Autowired
    private ProductRepository productRepository;

    private List<Product> cartList;

    public Cart() {
        cartList = new ArrayList<>();
    }

    public List<Product> getCartList() {
        return cartList;
    }

    public Product getCartListById(int id) {
        Product product = null;
        for (int i = 0; i < cartList.size(); i++) {
            if(id == cartList.get(i).getId()){
                product = cartList.get(i);
            }
        }
        return product;
    }

    public void deleteCartListById(int id) {
        for (int i = 0; i < cartList.size(); i++) {
            if(id == cartList.get(i).getId()){
                cartList.remove(i);
            }
        }
    }

    public List<Product> addProductById(int id){
        cartList.add(productRepository.getProductListById(id));
        return cartList;
    }

    @Override
    public String toString() {
        return "Cart{" +
                "productList=" + cartList +
                '}';
    }
}
