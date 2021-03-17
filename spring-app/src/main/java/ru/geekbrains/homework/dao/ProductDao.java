package ru.geekbrains.homework.dao;

import org.hibernate.HibernateException;
import org.hibernate.cfg.Configuration;
import ru.geekbrains.homework.entity.Product;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

public class ProductDao {

    EntityManagerFactory factory = new Configuration()
            .configure("hibernate.xml")
            .buildSessionFactory();

    EntityManager em = factory.createEntityManager();

    /**
     * Создание продукта
     *
     * @param name
     * @param price
     */
    public void createProduct(String name, int price) {
        Product product = new Product(name, price);

        em.getTransaction().begin();
        em.persist(product);
        em.getTransaction().commit();
    }

    public void createProduct(int id,String name, int price) {
        Product product = new Product(name, price);

        em.getTransaction().begin();
        em.persist(product);
        em.getTransaction().commit();
    }

    /**
     * Поиск по Id
     * @param id
     * @return
     * @throws NoResultException
     */
    public Product findById(int id) throws NoResultException {
        Product product = null;
        Query query = em.createQuery("select p from Product p where p.id =:id");
        query.setParameter("id",id);

        try {
            product = (Product) query.getSingleResult();
        } catch (NoResultException e) {
            System.out.println("Product no exsist : " + product);
            product = new Product();
        }
        return product;

    }

    /**
     * Поиск всех продуктов
     * @return
     * @throws NoResultException
     */
    public List<Product> findAll() throws NoResultException {
        List <Product> product = null;
        Query query = em.createQuery("select p from Product p");
        try {
            product = query.getResultList();

        }catch (NoResultException e){
            product = new ArrayList();
        }
        return product;

    }

    /**
     * Удаление продукта по Id
     * @param id
     */
    public void delteById(int id){
        Product product = em.find(Product.class,id);
        em.getTransaction().begin();
        em.remove(product);
        em.getTransaction().commit();
    }

    /**
     * Создание или обновление продукта
     * @param product
     * @return
     */
    public Product saveOrUpdate(Product product){
        Product anotherProduct ;
        anotherProduct = findById(product.getId());
        if (!anotherProduct.equals(product)){
            createProduct(product.getId(),product.getName(),product.getPrice());
            return product;
        }
        else {
            em.getTransaction().begin();
            Query query = em.createQuery("update Product p set p.name = :name,p.price = :price  where p.id =:id");
            query.setParameter("id",product.getId());
            query.setParameter("name",product.getName());
            query.setParameter("price",product.getPrice());
            query.executeUpdate();
            em.getTransaction().commit();
        }

        return product;

    }

}
