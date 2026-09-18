package com.kite.libai.core.utils;

import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.List;

public class BeanUtils {

    private static final ModelMapper modelMapper = new ModelMapper();

    public static <S, D> D copy(S source, Class<D> destinationClass) {
        return modelMapper.map(source, destinationClass);
    }

    public static <S, D> List<D> copy(Iterable<S> sourceList, Class<D> destinationClass) {
        if (sourceList == null) {
            return new ArrayList<>();
        }
        List<D> result = new ArrayList<>();
        for (S source : sourceList) {
            result.add(modelMapper.map(source, destinationClass));
        }
        return result;
    }

    public static <S, T> void copy(S source, T target) {
        if (source != null && target != null) {
            modelMapper.map(source, target);
        }
    }
}
