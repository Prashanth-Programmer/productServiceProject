package com.mario.productserviceproject.Repositories;


import com.mario.productserviceproject.Models.Product;
import com.mario.productserviceproject.Repositories.Projection.IProductIdName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IProductRepository extends JpaRepository<Product, Long> {
    //JPA query methods
    //1. Declared Queries
    //2. HQL queries
    //3. Native Queries

    // Declared Queries
    public Optional<Product> findById(Long id);
    public List<Product> findAll();
    public Product save(Product product);
    public void deleteById(Long id);

    //HQL queries
    //@Query("select p from Product p where p.id = :id")
    //public Optional<Product> getProductById(@Param("id") Long id);
    // HQL queries with projections
    //@Query("select p.id as id, p.name as name from Product p where p.id = :id")
    //public Optional<IProductIdName> getProductIdAndName(@Param("id") Long id);


    //Native Queries
    @Query(value = "select * from product p where p.id = :id", nativeQuery = true)
    public Optional<Product> getProductById(@Param("id") Long id);
    // Native queries with projections
    @Query(value = "select p.id as id, p.name as name from product p where p.id = :id", nativeQuery = true)
    public Optional<IProductIdName> getProductIdAndName(@Param("id") Long id);

}
