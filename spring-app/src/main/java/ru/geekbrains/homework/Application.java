package ru.geekbrains.homework;


import ru.geekbrains.homework.dao.ProductDao;
import ru.geekbrains.homework.entity.Product;

public class Application {
    public static void main(String[] args) {

        ProductDao dao = new ProductDao();
//        dao.createProduct("Табуретка",50);
//        System.out.println(dao.findById(3));
//        System.out.println(dao.findAll());
//        dao.delteById(5);
        Product product = new Product(7,"Раскладушка",300);
        System.out.println(dao.saveOrUpdate(product));

    }
}
