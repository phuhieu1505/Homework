package com.example.redis_example.service;

import com.example.redis_example.entity.Product;
import com.example.redis_example.repository.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@EnableCaching
public class ProductService {
    @Autowired
    private ProductRepo productRepo;
    @Cacheable("products")
    public List<Product> getAllProducts() {
        doLongRunningTask();

        List<Product> allProducts = new ArrayList<>();
        productRepo.findAll().forEach(allProducts::add);
        return allProducts;
    }

    @Cacheable("product")
    public Product getProductById(int id) {
        Optional<Product> productOptional = productRepo.findById(id);
        return productOptional.orElse(null);
    }
    public Product addProduct(Product product) {
        return productRepo.save(product);
    }


    public Product updateProduct(int id, Product product) {
        Optional<Product> productOptional = productRepo.findById(id);
        if(productOptional.isPresent()){
            Product newProduct = productOptional.get();
            newProduct.setId(product.getId());
            newProduct.setName(product.getName());
            newProduct.setQty(product.getQty());
            newProduct.setPrice(product.getPrice());

            productRepo.deleteById(id);
            return productRepo.save(newProduct);
        }
        return null;
    }

    public void deleteProduct(int id) {
         productRepo.deleteById(id);
    }

    private void doLongRunningTask(){
        try{
            Thread.sleep(3000);
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
