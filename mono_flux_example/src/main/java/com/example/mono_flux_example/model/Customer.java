package com.example.mono_flux_example.model;

import lombok.*;

import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import java.io.Serializable;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@RedisHash("customer")
public class Customer implements Serializable {
    @Id
    private String id;
    private String name;
    private long counterValue;
}
