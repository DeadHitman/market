package ru.geekbrains.homework;


import ru.geekbrains.homework.dao.CustomerDao;
import ru.geekbrains.homework.dao.ProductDao;

public class Application {


    ProductDao dao = new ProductDao();

    public static void main(String[] args) {
//        ProductDao productDao = new ProductDao();
        CustomerDao customerDao = new CustomerDao();
//        System.out.println(productDao.findAll());
        System.out.println(customerDao.findAll());
//        System.out.println(service.getProductListByCustomerId(2L));
//        System.out.println(service.getCustomerListByProductId(1L));
    }

}

