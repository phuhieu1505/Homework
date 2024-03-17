package com.example.redis_example.controller;

import com.example.redis_example.entity.Product;
import com.example.redis_example.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {
    @Autowired
    private ProductService productService;

    @GetMapping("/all")
    public List<Product> getAllProducts(){
        return productService.getAllProducts();
    }
    @GetMapping("/{id}")
    public Product getProductById(@PathVariable("id") int id){
        return productService.getProductById(id);
    }

    @PostMapping("/add")
    public Product addProduct(@RequestBody Product product){
        return productService.addProduct(product);
    }

    @PutMapping("/{id}")
    public Product updateProductById(@PathVariable("id") int id,@RequestBody Product product){
        return productService.updateProduct(id,product);
    }

    @DeleteMapping("/{id}")
    public String deleteProductById(@PathVariable("id")int id){
         productService.deleteProduct(id);
         return "Delete success";
    }
}
