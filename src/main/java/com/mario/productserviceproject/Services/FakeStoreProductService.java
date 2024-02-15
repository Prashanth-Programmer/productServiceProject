package com.mario.productserviceproject.Services;

import com.mario.productserviceproject.DTOs.FakeStoreProductDTO;
import com.mario.productserviceproject.Models.Category;
import com.mario.productserviceproject.Models.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpMessageConverterExtractor;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.RestTemplate;
import  com.mario.productserviceproject.CustomExceptions.*;

import java.util.ArrayList;
import java.util.List;

@Service
public class FakeStoreProductService implements IProductService{
    RestTemplate restTemplate;

    @Autowired
    public FakeStoreProductService(RestTemplate restTemplate){
        this.restTemplate = restTemplate;
    }
    @Override
    public Product getProductDetails(long id) throws ProductDoesNotExistException{
//        FakeStoreProductDTO fakeStoreProductDTO = restTemplate.getForObject(
//                "https://fakestoreapi.com/products/" + id,
//                FakeStoreProductDTO.class
//        );
        //return ConvertFakeStoreProductDTO(fakeStoreProductDTO);
        throw new ProductDoesNotExistException("Product with id "+id+" does not exist.");
    }

    @Override
    public List<Product> getProducts() {
        FakeStoreProductDTO[] fakeStoreProductDTOS = restTemplate.getForObject(
          "https://fakestoreapi.com/products",
          FakeStoreProductDTO[].class
        );
        List<Product> products = new ArrayList<>();
        for(FakeStoreProductDTO dto : fakeStoreProductDTOS){
            products.add(ConvertFakeStoreProductDTO(dto));
        }
        return products;
    }

    private Product ConvertFakeStoreProductDTO(FakeStoreProductDTO fakeStoreProductDTO){
        Product product = new Product();
        product.setId(fakeStoreProductDTO.getId());
        product.setName(fakeStoreProductDTO.getTitle());
        product.setDescription(fakeStoreProductDTO.getDescription());
        product.setPrice(fakeStoreProductDTO.getPrice());
        product.setCategory(new Category());
        product.getCategory().setName(fakeStoreProductDTO.getCategory());
        return  product;
    }

    public Product createProduct(Product product){
        FakeStoreProductDTO dto = new FakeStoreProductDTO();
        dto.setTitle(product.getName());
        dto.setDescription(product.getDescription());
        dto.setPrice(product.getPrice());
        dto.setCategory(product.getCategory().getName());
        dto.setImage(product.getImageURL());
        FakeStoreProductDTO response = restTemplate.postForObject("https://fakestoreapi.com/products",
                dto, FakeStoreProductDTO.class);
        return ConvertFakeStoreProductDTO(response);
    }

    @Override
    public Product replaceProduct(long id, Product product) {
        FakeStoreProductDTO dto = new FakeStoreProductDTO();
        dto.setTitle(product.getName());
        dto.setDescription(product.getDescription());
        dto.setPrice(product.getPrice());
        dto.setCategory(product.getCategory().getName());
        dto.setImage(product.getImageURL());
        RequestCallback requestCallback = restTemplate.httpEntityCallback(dto, FakeStoreProductDTO.class);
        HttpMessageConverterExtractor<FakeStoreProductDTO> responseExtractor =
                new HttpMessageConverterExtractor<>(FakeStoreProductDTO.class, restTemplate.getMessageConverters());
        FakeStoreProductDTO response = restTemplate.execute("https://fakestoreapi.com/products/"+id, HttpMethod.PUT, requestCallback, responseExtractor);
        return ConvertFakeStoreProductDTO(response);
    }

    @Override
    public Product updateProduct(long id, Product product) {
        FakeStoreProductDTO dto = new FakeStoreProductDTO();
        dto.setTitle(product.getName());
        FakeStoreProductDTO response = restTemplate.patchForObject("https://fakestoreapi.com/products/"+id, dto,FakeStoreProductDTO.class);
        return ConvertFakeStoreProductDTO(response);
    }

    @Override
    public void delete(long id) {
        restTemplate.delete("https://fakestoreapi.com/products/"+id);
    }
}
