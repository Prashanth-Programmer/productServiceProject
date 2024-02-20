package com.mario.productserviceproject.Repositories;

import com.mario.productserviceproject.Models.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ICategoryRepository extends JpaRepository <Category, Long> {
    public Optional<Category> findByName(String name);
    public Category save(Category category);
}
