package com.example.redis_example.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import java.io.Serializable;

@Data
@RedisHash("Product")
public class Product implements Serializable {
    @Id
    private int id;
    private String name;
    private int qty;
    private long price;
}
