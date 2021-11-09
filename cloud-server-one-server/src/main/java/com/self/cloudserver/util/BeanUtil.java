package com.self.cloudserver.util;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.util.CollectionUtils;

import java.beans.PropertyDescriptor;
import java.util.*;
import java.util.stream.Collectors;

public class BeanUtil {

    public static <T> T copyNullProperties(Object source, Class<T> tClass) {
        try{
            if(source == null){
                return null;
            }

            T instance = tClass.newInstance();
            BeanUtils.copyProperties(source, instance, getNullField(source));
            return instance;
        }catch (Exception e){
            return null;
        }
    }

    private static String[] getNullField(Object target) {
        BeanWrapper beanWrapper = new BeanWrapperImpl(target);
        PropertyDescriptor[] propertyDescriptors = beanWrapper.getPropertyDescriptors();
        Set<String> notNullFieldSet = new HashSet<>();
        if (propertyDescriptors.length > 0) {
            for (PropertyDescriptor p : propertyDescriptors) {
                String name = p.getName();
                Object value = beanWrapper.getPropertyValue(name);
                if (Objects.isNull(value) || StringUtils.isBlank(value.toString())) {
                    notNullFieldSet.add(name);
                }
            }
        }

        String[] notNullField = new String[notNullFieldSet.size()];
        return notNullFieldSet.toArray(notNullField);
    }

    public static <E, T> List<E> copyList(List<T> srcList, Class<E> tClass) {
        if (CollectionUtils.isEmpty(srcList)) {
            return Collections.emptyList();
        }

        return srcList.stream().map(source -> copyNullProperties(source, tClass)).collect(Collectors.toList());
    }

}
