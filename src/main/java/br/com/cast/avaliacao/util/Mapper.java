package br.com.cast.avaliacao.util;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

import java.util.HashSet;
import java.util.Set;
import java.util.function.Consumer;

public class Mapper {

    private static String[] getNullPropertyNames(final Object source) {
        final BeanWrapper src = new BeanWrapperImpl(source);
        final java.beans.PropertyDescriptor[] pds = src.getPropertyDescriptors();

        final Set<String> emptyNames = new HashSet<>();
        for(java.beans.PropertyDescriptor pd : pds) {
            Object srcValue = src.getPropertyValue(pd.getName());
            if (srcValue == null) emptyNames.add(pd.getName());
        }
        final String[] result = new String[emptyNames.size()];
        return emptyNames.toArray(result);
    }

    public static <S, T> T map(final S source, final Class<T> classTarget) {
        final T target = createInstanceOf(classTarget);
        BeanUtils.copyProperties(source, target);
        return target;
    }

    public static <S, T> T map(final S source, final Class<T> classTarget, final Consumer<T> postMap) {
        final T target = map(source, classTarget);
        postMap.accept(target);
        return target;
    }

    private static <T> T createInstanceOf(final Class<T> clazz) {
        try {
            return clazz.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}
