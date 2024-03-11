package com.example.redis_example.repository;

import com.example.redis_example.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ProductDAL {
    @Autowired
    @Qualifier("redisTemplate")
    private RedisTemplate template;
    public static final String HASH_KEY = "Product";
    public Product save(Product product){
        template.opsForHash().put(HASH_KEY,String.valueOf(product.getId()),product);
        return product;
    }

    public List<Product> findAll(){
        return template.opsForHash().values(HASH_KEY);
    }

    public Product findProductById(int id){
        return (Product) template.opsForHash().get(HASH_KEY,id);
    }

    public String deleteProduct (int id){
         template.opsForHash().delete(HASH_KEY,id);
         return "delete success";
    }
}
