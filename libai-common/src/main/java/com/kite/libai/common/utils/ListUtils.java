package com.kite.libai.common.utils;

import lombok.experimental.UtilityClass;
import org.apache.commons.collections4.CollectionUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import java.util.function.Function;
import java.util.stream.Collectors;

@UtilityClass
public class ListUtils {
    /**
     * 创建一个空ArrayList
     *
     * @param <T> 元素类型
     * @return 空的List
     */
    public static <T> List<T> of() {
        return new ArrayList<>();
    }

    /**
     * 创建一个ArrayList
     *
     * @param elements 数组
     * @param <T>      集合元素类型
     * @return List<T>
     */
    @SafeVarargs
    public static <T> List<T> of(T... elements) {
        List<T> arrayList = new ArrayList<>(elements.length);
        Collections.addAll(arrayList, elements);
        return arrayList;
    }

    /**
     * 创建一个空LinkedList
     *
     * @param <T> 元素类型
     * @return 空的List
     */
    public static <T> List<T> newLinkedList() {
        return new LinkedList<>();
    }

    /**
     * 创建一个LinkedList
     *
     * @param elements 数组
     * @param <T>      集合元素类型
     * @return List<T>
     */
    @SafeVarargs
    public static <T> List<T> newLinkedList(T... elements) {
        List<T> linkedList = new LinkedList<>();
        Collections.addAll(linkedList, elements);
        return linkedList;
    }

    /**
     * 判断list是否为空如果为null,new一个新的ArrayList
     *
     * @param list list
     * @return 非null的List
     */
    public static <T> List<T> defaultList(List<T> list) {
        return (CollectionUtils.isEmpty(list) ? new ArrayList<>() : list);
    }

    /**
     * 安全的截取list
     *
     * @param list  原 list
     * @param start 开始位置
     * @param end   结束位置
     * @param <T>   T
     * @return 新的list
     */
    public static <T> List<T> safeSubList(List<T> list, int start, int end) {
        if (start >= list.size()) {
            return new ArrayList<>();
        }
        return list.subList(Math.max(start, 0), Math.min(end, list.size()));
    }

    /**
     * 获取第一个元素,空集合返回null
     *
     * @param list list
     * @param <T>  T
     * @return T
     */
    public static <T> T getFirst(List<T> list) {
        return CollectionUtils.isEmpty(list) ? null : list.get(0);
    }

    /**
     * 获取最后一个元素,空集合返回null
     *
     * @param list list
     * @param <T>  T
     * @return T
     */
    public static <T> T getLast(List<T> list) {
        return CollectionUtils.isEmpty(list) ? null : list.get(list.size() - 1);
    }

    /**
     * 去重
     *
     * @param list list
     * @param <T>  T
     * @return List<T>
     */
    public static <T> List<T> unique(List<T> list) {
        return CollectionUtils.isEmpty(list) ? new ArrayList<>() : new ArrayList<>(new LinkedHashSet<>(list));
    }

    /**
     * 将list 转为 map
     *
     * @param list     list
     * @param function function
     * @param <K>      key
     * @param <V>      value
     * @return map
     */
    public static <K, V> Map<K, V> toMap(List<V> list, Function<V, K> function) {
        if (CollectionUtils.isEmpty(list) || function == null) {
            return new HashMap<>();
        }
        return list.stream().collect(Collectors.toMap(function, Function.identity(), (v1, v2) -> v1));
    }

    /**
     * 获取集合长度,空集合返回0
     *
     * @param list list
     * @param <T>  T
     * @return int
     */
    public static <T> int getSize(List<T> list) {
        return CollectionUtils.isEmpty(list) ? 0 : list.size();
    }

    /**
     * 随机获取元素
     *
     * @param list list
     * @param <T>  T
     * @return
     */
    public static <T> T random(List<T> list) {
        if (CollectionUtils.isEmpty(list)) {
            return null;
        }
        int n = ThreadLocalRandom.current().nextInt(list.size());
        return list.get(n);
    }

    /**
     * 随机获取count个元素不去重
     *
     * @param list  list
     * @param count count
     * @param <T>   List<T>
     * @return
     */
    public static <T> List<T> random(List<T> list, int count) {
        List<T> result = new ArrayList<>();
        if (CollectionUtils.isEmpty(list)) {
            return result;
        }
        for (int i = 0; i < count; i++) {
            int n = ThreadLocalRandom.current().nextInt(list.size());
            result.add(list.get(n));
        }
        return result;
    }
}
