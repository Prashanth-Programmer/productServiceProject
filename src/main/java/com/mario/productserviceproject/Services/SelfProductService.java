package com.mario.productserviceproject.Services;

import com.mario.productserviceproject.CustomExceptions.ProductDoesNotExistException;
import com.mario.productserviceproject.Models.Category;
import com.mario.productserviceproject.Models.Product;
import com.mario.productserviceproject.Repositories.ICategoryRepository;
import com.mario.productserviceproject.Repositories.IProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service("selfProductService")
public class SelfProductService implements IProductService{
    IProductRepository productRepository;
    ICategoryRepository categoryRepository;
    @Autowired
    public SelfProductService(IProductRepository productRepository, ICategoryRepository categoryRepository){
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }
    @Override
    public Product getProductDetails(long id) throws ProductDoesNotExistException {
        Optional<Product> result = productRepository.findById(id);
        if(result.isEmpty() == true){
            throw new ProductDoesNotExistException("Product with id "+id+" does not exist.");
        }
        return result.get();
    }

    @Override
    public List<Product> getProducts() {
        List<Product> products = productRepository.findAll();
        return products;
    }

    @Override
    public Product createProduct(Product product) {
        Category category  = product.getCategory();
        Optional<Category> categoryResult = categoryRepository.findByName(category.getName());
        if(categoryResult.isEmpty() == true){
            category = categoryRepository.save(category);
            product.setCategory(category);
        }
        else{
            product.setCategory(categoryResult.get());
        }
        return productRepository.save(product);
    }

    @Override
    public Product replaceProduct(long id, Product product) throws ProductDoesNotExistException{
        Optional<Product> dbProduct = productRepository.findById(id);
        if(dbProduct.isEmpty() == true)
        {
            throw new ProductDoesNotExistException("Product with id "+id+" does not exist.");
        }
        product.setId(id);
        Category category  = product.getCategory();
        Optional<Category> categoryResult = categoryRepository.findByName(category.getName());
        if(categoryResult.isEmpty() == true){
            category = categoryRepository.save(category);
            product.setCategory(category);
        }
        else{
            product.setCategory(categoryResult.get());
        }
        return productRepository.save(product);
    }

    @Override
    public Product updateProduct(long id, Product product) throws ProductDoesNotExistException {
        Optional<Product> productResult = productRepository.findById(id);
        if(productResult.isEmpty() == true){
            throw new ProductDoesNotExistException("Product with id "+id+" does not exist.");
        }
        Product savedProduct = productResult.get();
        if(product.getName() != null){
            savedProduct.setName(product.getName());
        }
        if(product.getPrice() != null){
            savedProduct.setPrice(product.getPrice());
        }
        if(product.getDescription() != null){
            savedProduct.setDescription(product.getDescription());
        }
        if(product.getImageURL() != null){
            savedProduct.setImageURL(product.getImageURL());
        }
        if(product.getCategory() !=  null) {
            Optional<Category> categoryResult = categoryRepository.findByName(product.getCategory().getName());
            if(categoryResult.isEmpty() == true){
                Category category = categoryRepository.save(product.getCategory());
                savedProduct.setCategory(category);
            }
            else{
                savedProduct.setCategory(categoryResult.get());
            }
        }
        return productRepository.save(savedProduct);
    }

    @Override
    public void delete(long id) {
        productRepository.deleteById(id);
    }
}
