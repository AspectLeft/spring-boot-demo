package io.aspect.springbootdemo.res;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ErrorResource {
    private String message;
}
