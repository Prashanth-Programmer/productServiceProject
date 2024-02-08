package com.mario.productserviceproject.Controllers;

import com.mario.productserviceproject.Models.Product;
import com.mario.productserviceproject.Services.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
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
        return  new ArrayList<>();
    }

    @GetMapping("/{id}")
    public Product getProductDetails(@PathVariable("id") long id){
        return  productService.getProductDetails(id);
    }

    @PostMapping()
    public Product createProduct(@RequestBody Product product){
        return new Product();
    }

    @PatchMapping("/{id}")
    public Product updateProduct(@PathVariable("id") long id, @RequestBody Product product){
        return new Product();
    }

    @PutMapping("/{id}")
    public Product replaceProduct(@PathVariable("id") long id, @RequestBody Product product){
        return new Product();
    }

    @DeleteMapping ("/{id}")
    public void deleteProduct(@PathVariable("id") long id){
        return;
    }
}
