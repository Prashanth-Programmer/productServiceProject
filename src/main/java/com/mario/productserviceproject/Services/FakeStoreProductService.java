package com.mario.productserviceproject.Services;

import com.mario.productserviceproject.DTOs.FakeStoreProductDTO;
import com.mario.productserviceproject.Models.Category;
import com.mario.productserviceproject.Models.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class FakeStoreProductService implements IProductService{
    RestTemplate restTemplate;

    @Autowired
    public FakeStoreProductService(RestTemplate restTemplate){
        this.restTemplate = restTemplate;
    }
    @Override
    public Product getProductDetails(long id) {
        FakeStoreProductDTO fakeStoreProductDTO = restTemplate.getForObject(
                "https://fakestoreapi.com/products/" + id,
                FakeStoreProductDTO.class
        );
        return ConvertFakeStoreProductDTO(fakeStoreProductDTO);
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
}
