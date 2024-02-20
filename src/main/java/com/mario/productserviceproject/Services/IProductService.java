package com.mario.productserviceproject.Services;

import com.mario.productserviceproject.CustomExceptions.ProductDoesNotExistException;
import com.mario.productserviceproject.Models.Product;

import java.util.List;

public interface IProductService {
    public Product getProductDetails(long id) throws ProductDoesNotExistException;
    public List<Product> getProducts();
    public Product createProduct(Product product);
    public Product replaceProduct(long id, Product product) throws ProductDoesNotExistException;
    public Product updateProduct(long id, Product product);
    public void delete(long id);
}
