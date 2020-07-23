package io.aspect.springbootdemo.res;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class FieldResource {
    private String resource;
    private String field;
    private String code;
    private String message;
}
