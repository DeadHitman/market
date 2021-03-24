package ru.geekbrains.homework.services;

import org.springframework.stereotype.Service;
import ru.geekbrains.homework.entity.Cart;
import ru.geekbrains.homework.entity.Customer;
import ru.geekbrains.homework.entity.Product;
import ru.geekbrains.homework.config.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

@Service
public class ProductSrv {
    @Autowired
    public Customer customer;

    @Autowired
    public Product product;

    @Autowired
    public Cart cart;

    @Autowired
    EntityManager em =new EntityManager();

    public List<Product> getProductListByCustomerId(Long id) {
        List product = new ArrayList<>();
        Query query = em.getEm().createQuery("select p from Cart c, Customer cu, Product p where c.customer.id = cu.id and cu.id =:id and p.id = c.product.id", Product.class);
        query.setParameter("id", id);

        try {
            product = query.getResultList();
        } catch (NoResultException e) {
            System.out.println("Product no exsist : " + product);
            product = new ArrayList<>();
        }
        return product;
    }

    public List<Customer> getCustomerListByProductId(Long id) {
        List customer = new ArrayList<>();
        Query query = em.getEm().createQuery("select cu from Cart c, Customer cu, Product p where c.customer.id = cu.id and p.id =:id and p.id = c.product.id", Customer.class);
        query.setParameter("id", id);

        try {
            customer = query.getResultList();
        } catch (NoResultException e) {
            System.out.println("Customer no exsist : " + product);
            customer = new ArrayList<>();
        }
        return customer;
    }
}
