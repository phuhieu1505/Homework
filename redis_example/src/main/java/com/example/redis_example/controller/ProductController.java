package com.example.redis_example.controller;

import com.example.redis_example.entity.Product;
import com.example.redis_example.repository.ProductDAL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {
    @Autowired
    private ProductDAL productDAL;

    @PostMapping("/save")
    public Product save(@RequestBody Product product){
        return productDAL.save(product);
    }

    @GetMapping("/getall")
    public List<Product> getAll(){
        return productDAL.findAll();
    }

    @GetMapping("/{id}")
    public Product getProductById(@PathVariable int id){
        return productDAL.findProductById(id);
    }


    @DeleteMapping("/{id}")
    public String deleteProduct(@PathVariable int id){
        return productDAL.deleteProduct(id);
    }

}
