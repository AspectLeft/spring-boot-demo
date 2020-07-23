package io.aspect.springbootdemo.res;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class InvalidErrorResource {
    private String message;
    private List<FieldResource> errors;
}
