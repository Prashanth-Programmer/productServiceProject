package com.mario.productserviceproject;

import com.mario.productserviceproject.Models.Category;
import com.mario.productserviceproject.Models.Product;
import com.mario.productserviceproject.Repositories.ICategoryRepository;
import com.mario.productserviceproject.Repositories.IProductRepository;
import com.mario.productserviceproject.Repositories.Projection.IProductIdName;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

@SpringBootTest
class ProductServiceProjectApplicationTests {
    private IProductRepository productRepository;

    private ICategoryRepository categoryRepository;
    @Autowired

    public ProductServiceProjectApplicationTests(IProductRepository productRepository, ICategoryRepository categoryRepository){
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }
//    @Test
//    void contextLoads() {
//    }

    @Transactional
    @Test
    void testQuery(){
//        Optional<Product> product = productRepository.getProductById(2L);
//        if(product.isEmpty()){
//            System.out.println("no such product exists.");
//        }
//        System.out.println(product.get().getId());
//        System.out.println(product.get().getName());
//
//        Optional<IProductIdName> product2 = productRepository.getProductIdAndName(2L);
//        if(product.isEmpty()){
//            System.out.println("no such product exists.");
//        }
//        System.out.println(product2.get().getId());
//        System.out.println(product2.get().getName());

//        Optional<Product> p = productRepository.findById(52L);
//        if(p.isEmpty() == true){
//            System.out.println("No such product exists.");
//        }
//        else{
//            System.out.println(p.get().getId());
//            System.out.println(p.get().getName());
//
//        }

        //Eager fetching
        Optional<Product> res = productRepository.findById(52L);
        if(res.isEmpty() == true){
            System.out.println("No such product exists.");
        }
        else{
            System.out.println(res.get().getId());
            System.out.println(res.get().getName());
        }

        //Lazy fetching
        List<Category> categoryList = categoryRepository.findByNameStartingWith("l");
        for(Category c: categoryList){
            for(Product p: c.getProducts()){
                System.out.println(p.getName());
            }
        }
    }

}
