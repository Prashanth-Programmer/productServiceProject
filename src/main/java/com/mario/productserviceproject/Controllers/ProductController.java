package com.mario.productserviceproject.Controllers;

import com.mario.productserviceproject.CustomExceptions.ProductDoesNotExistException;
import com.mario.productserviceproject.Models.Product;
import com.mario.productserviceproject.Services.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/Products")
public class ProductController {
    IProductService productService;

    @Autowired
    public ProductController(IProductService productService){
        this.productService = productService;
    }
    @GetMapping()
    public List<Product> getProducts(){
        return  productService.getProducts();
    }

    @GetMapping("/{id}")
    public Product getProductDetails(@PathVariable("id") long id) throws ProductDoesNotExistException{
            Product product = productService.getProductDetails(id);
            return product;
    }

    @PostMapping()
    public Product createProduct(@RequestBody Product product){
        return productService.createProduct(product);
    }

    @PatchMapping("/{id}")
    public Product updateProduct(@PathVariable("id") long id, @RequestBody Product product){
        return productService.updateProduct(id, product);
    }

    @PutMapping("/{id}")
    public Product replaceProduct(@PathVariable("id") long id, @RequestBody Product product){
        return productService.replaceProduct(id, product);
    }

    @DeleteMapping ("/{id}")
    public void deleteProduct(@PathVariable("id") long id){
        productService.delete(id);
    }
}
