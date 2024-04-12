package com.example.mongodb_example.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

        import java.util.Objects;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseData {
    private boolean status;
    private Object data;
}
