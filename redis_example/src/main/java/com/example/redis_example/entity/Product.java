package com.example.redis_example.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.index.Indexed;


@Data
@AllArgsConstructor
@NoArgsConstructor
@RedisHash("Product")
public class Product  {
    @Id
    @Indexed
    private int id;
    private String name;
    private int qty;
    private long price;
}
