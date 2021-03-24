package ru.geekbrains.homework.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.geekbrains.homework.entity.Product;

@Repository
public interface ProductRepositrory extends JpaRepository<Product,Long> {

}
