package io.aspect.springbootdemo.util;

import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

import java.beans.PropertyDescriptor;
import java.util.stream.Stream;

public class CustomBeanUtils {
    public static String[] getNullPropertyNames(final Object object) {
        BeanWrapper beanWrapper = new BeanWrapperImpl(object);
        return Stream.of(beanWrapper.getPropertyDescriptors()).map(PropertyDescriptor::getName).filter(name ->
                beanWrapper.getPropertyValue(name) == null).toArray(String[]::new);
    }

}
