package ru.geekbrains.homework;

//import org.springframework.boot.SpringApplication;
//import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.SpringApplication;

@ComponentScan
@EnableAutoConfiguration
public class SpringAppApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringAppApplication.class, args);

//        ApplicationContext context = new AnnotationConfigApplicationContext(ProductRepository.class, Cart.class);
//        ProductRepository productList = context.getBean(ProductRepository.class);
//        System.out.println(productList.getProductList());
//        System.out.println(productList.getProductListById(2));
//        Cart cart = context.getBean(Cart.class);
//        cart.addProductById(1);
//        cart.addProductById(2);
//        cart.addProductById(3);
//        System.out.println(cart.getCartList());
//        cart.deleteCartListById(2);
//        System.out.println(cart.getCartList());
    }

}
