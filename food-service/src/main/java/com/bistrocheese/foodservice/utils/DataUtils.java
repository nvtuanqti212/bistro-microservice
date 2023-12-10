package com.bistrocheese.foodservice.utils;

import org.springframework.beans.BeanUtils;

import java.lang.reflect.Constructor;

public class DataUtils {
    /*
     * @description: Copy properties to new object
     * */
    public static <T> T copyProperties(Object source, Class<T> classTarget) {
        try {
            Constructor<?> cons = classTarget.getConstructor();
            Object target = cons.newInstance();

            BeanUtils.copyProperties(source, target);
            return (T) target;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
