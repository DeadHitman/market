package ru.geekbrains.homework.dao;

import org.hibernate.HibernateException;
import org.hibernate.cfg.Configuration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import ru.geekbrains.homework.config.EntityManager;
import ru.geekbrains.homework.entity.Product;

import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

@Component
@Qualifier("ProductDao")
public class ProductDao {

    @Autowired
    EntityManager em =new EntityManager();


    public void createProduct(String name, int price) {
        Product product = new Product(name, price);
        em.getEm().getTransaction().begin();
        em.getEm().persist(product);
        em.getEm().getTransaction().commit();
    }

    public Product findById(long id) throws NoResultException {
        Product product = null;
        Query query = em.getEm().createQuery("select p from Product p where p.id =:id");
        query.setParameter("id", id);

        try {
            product = (Product) query.getSingleResult();
        } catch (NoResultException e) {
            System.out.println("Product no exsist : " + product);
            product = new Product();
        }
        return product;
    }

    public List<Product> findAll() throws NoResultException {
        List<Product> product = null;
        Query query = em.getEm().createQuery("select p from Product p");
        try {
            product = query.getResultList();

        } catch (NoResultException e) {
            product = new ArrayList();
        }
        return product;
    }

    public void deleteById(Long id) {
        Product product = em.getEm().find(Product.class, id);
        em.getEm().getTransaction().begin();
        em.getEm().remove(product);
        em.getEm().getTransaction().commit();
    }

    public Product saveOrUpdate(Product product) {
        Product anotherProduct;
        anotherProduct = findById(product.getId());
        if (!anotherProduct.equals(product)) {
            createProduct(product.getTitle(), product.getPrice());
            return product;
        } else {
            em.getEm().getTransaction().begin();
            Query query = em.getEm().createQuery("update Product p set p.title = :title,p.price = :price  where p.id =:id");
            query.setParameter("id", product.getId());
            query.setParameter("title", product.getTitle());
            query.setParameter("price", product.getPrice());
            query.executeUpdate();
            em.getEm().getTransaction().commit();
        }
        return product;
    }

}
