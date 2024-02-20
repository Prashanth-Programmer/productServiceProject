package com.mario.productserviceproject.Repositories;


import com.mario.productserviceproject.Models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IProductRepository extends JpaRepository<Product, Long> {
    public Optional<Product> findById(Long id);
    public List<Product> findAll();
    public Product save(Product product);
    public void deleteById(Long id);
}
