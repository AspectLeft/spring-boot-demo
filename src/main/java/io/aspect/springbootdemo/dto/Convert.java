package io.aspect.springbootdemo.dto;

public interface Convert<S, T> {
    T convert(S s);

    T convert(S s, T t);
}
