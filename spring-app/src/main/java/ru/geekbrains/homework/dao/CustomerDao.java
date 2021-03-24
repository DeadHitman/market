package ru.geekbrains.homework.dao;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import ru.geekbrains.homework.entity.Customer;
import ru.geekbrains.homework.config.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.NoResultException;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

@Component
@Qualifier("CustomerDao")
public class CustomerDao {

    @Autowired
    EntityManager em =new EntityManager();

    public void createCustomer(String name){
        Customer customer = new Customer(name);
        em.getEm().getTransaction().begin();
        em.getEm().persist(customer);
        em.getEm().getTransaction().commit();
    }

    public Customer findById(long id) throws NoResultException {
        Customer customer = null;
        Query query = em.getEm().createQuery("select p from Customer p where p.id =:id");
        query.setParameter("id",id);

        try {
            customer = (Customer) query.getSingleResult();
        } catch (NoResultException e) {
            System.out.println("Customer no exsist : " + customer);
            customer = new Customer();
        }
        return customer;
    }

    public List<Customer> findAll() throws NoResultException {
        List <Customer> customer = null;
        Query query = em.getEm().createQuery("select p from Customer p");
        try {
            customer = query.getResultList();

        }catch (NoResultException e){
            customer = new ArrayList();
        }
        return customer;
    }

    public void deleteById(Long id){
        Customer customer = em.getEm().find(Customer.class, id);
        em.getEm().getTransaction().begin();
        em.getEm().remove(customer);
        em.getEm().getTransaction().commit();
    }

    public Customer saveOrUpdate(Customer customer){
        Customer anotherCustomer;
        anotherCustomer = findById(customer.getId());
        if (!anotherCustomer.equals(customer)){
            createCustomer(customer.getName());
            return customer;
        }
        else {
            em.getEm().getTransaction().begin();
            Query query = em.getEm().createQuery("update Customer p set p.name = :name where p.id =:id");
            query.setParameter("id",customer.getId());
            query.setParameter("name",customer.getName());
            query.executeUpdate();
            em.getEm().getTransaction().commit();
        }
        return customer;
    }
}
