package com.kite.libai.common.utils;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.TreeNode;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionLikeType;
import com.fasterxml.jackson.databind.type.MapType;
import com.kite.libai.common.function.CheckedConsumer;
import com.kite.libai.common.jackson.KiteObjectMapperFactory;
import lombok.experimental.UtilityClass;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * Jackson Json工具类
 * <p>注意：
 * 1. 开启宽松JSON解析（单引号、未转义控制字符），适合内部系统调用；对外公网接口建议关闭宽松特性
 * 2. isValidJson 仅校验JSON语法合法性，不校验业务对象字段结构
 * 3. 全局单例ObjectMapper，线程安全
 * </p>
 */
@UtilityClass
public class JsonUtils {

    private static final ObjectMapper objectMapper = KiteObjectMapperFactory.getObjectMapper();

    /**
     * 将对象序列化成json字符串
     *
     * @param object javaBean
     * @return jsonString json字符串
     */
    public static String toJson(Object object) {
        if (object == null) {
            return null;
        }
        try {
            return objectMapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            throw Exceptions.unchecked(e);
        }
    }

    /**
     * 将对象序列化成 json byte 数组
     *
     * @param object javaBean
     * @return json bytes
     */
    public static byte[] toJsonAsBytes(Object object) {
        if (object == null) {
            return null;
        }
        try {
            return objectMapper.writeValueAsBytes(object);
        } catch (JsonProcessingException e) {
            throw Exceptions.unchecked(e);
        }
    }

    /**
     * 将json字符串转成 JsonNode
     *
     * @param jsonString jsonString
     * @return JsonNode
     */
    public static JsonNode readTree(String jsonString) {
        Objects.requireNonNull(jsonString, "jsonString is null");
        try {
            return objectMapper.readTree(jsonString);
        } catch (IOException e) {
            throw Exceptions.unchecked(e);
        }
    }

    /**
     * 将InputStream转成 JsonNode
     *
     * @param in InputStream
     * @return JsonNode
     */
    public static JsonNode readTree(InputStream in) {
        Objects.requireNonNull(in, "InputStream in is null");
        try {
            return objectMapper.readTree(in);
        } catch (IOException e) {
            throw Exceptions.unchecked(e);
        }
    }

    /**
     * 将byte[]转成 JsonNode
     *
     * @param content bytes
     * @return JsonNode
     */
    public static JsonNode readTree(byte[] content) {
        Objects.requireNonNull(content, "byte[] content is null");
        try {
            return objectMapper.readTree(content);
        } catch (IOException e) {
            throw Exceptions.unchecked(e);
        }
    }

    /**
     * 将JsonParser转成 JsonNode
     *
     * @param jsonParser JsonParser
     * @return JsonNode
     */
    public static JsonNode readTree(JsonParser jsonParser) {
        Objects.requireNonNull(jsonParser, "jsonParser is null");
        try {
            return objectMapper.readTree(jsonParser);
        } catch (IOException e) {
            throw Exceptions.unchecked(e);
        }
    }

    /**
     * 将json byte 数组反序列化成对象
     *
     * @param content   json bytes
     * @param valueType class
     * @param <T>       T 泛型标记
     * @return Bean
     */
    public static <T> T readValue(byte[] content, Class<T> valueType) {
        if (content == null) {
            return null;
        }
        try {
            return objectMapper.readValue(content, valueType);
        } catch (IOException e) {
            throw Exceptions.unchecked(e);
        }
    }

    /**
     * 将json字符串反序列化成对象
     *
     * @param jsonString jsonString
     * @param valueType  class
     * @param <T>        T 泛型标记
     * @return Bean
     */
    public static <T> T readValue(String jsonString, Class<T> valueType) {
        if (StringUtils.isBlank(jsonString)) {
            return null;
        }
        try {
            return objectMapper.readValue(jsonString, valueType);
        } catch (IOException e) {
            throw Exceptions.unchecked(e);
        }
    }

    /**
     * 将InputStream反序列化成对象
     *
     * @param in        InputStream
     * @param valueType class
     * @param <T>       T 泛型标记
     * @return Bean
     */
    public static <T> T readValue(InputStream in, Class<T> valueType) {
        if (in == null) {
            return null;
        }
        try {
            return objectMapper.readValue(in, valueType);
        } catch (IOException e) {
            throw Exceptions.unchecked(e);
        }
    }

    /**
     * 将json byte 数组反序列化成对象
     *
     * @param content       bytes
     * @param typeReference 泛型类型
     * @param <T>           T 泛型标记
     * @return Bean
     */
    public static <T> T readValue(byte[] content, TypeReference<T> typeReference) {
        if (content == null) {
            return null;
        }
        try {
            return objectMapper.readValue(content, typeReference);
        } catch (IOException e) {
            throw Exceptions.unchecked(e);
        }
    }

    /**
     * 将json字符串反序列化成对象
     *
     * @param jsonString    jsonString
     * @param typeReference 泛型类型
     * @param <T>           T 泛型标记
     * @return Bean
     */
    public static <T> T readValue(String jsonString, TypeReference<T> typeReference) {
        if (StringUtils.isBlank(jsonString)) {
            return null;
        }
        try {
            return objectMapper.readValue(jsonString, typeReference);
        } catch (IOException e) {
            throw Exceptions.unchecked(e);
        }
    }

    /**
     * 将InputStream反序列化成对象
     *
     * @param in            InputStream
     * @param typeReference 泛型类型
     * @param <T>           T 泛型标记
     * @return Bean
     */
    public static <T> T readValue(InputStream in, TypeReference<T> typeReference) {
        if (in == null) {
            return null;
        }
        try {
            return objectMapper.readValue(in, typeReference);
        } catch (IOException e) {
            throw Exceptions.unchecked(e);
        }
    }

    /**
     * 将json byte 数组反序列化成对象
     *
     * @param content  bytes
     * @param javaType JavaType
     * @param <T>      T 泛型标记
     * @return Bean
     */
    public static <T> T readValue(byte[] content, JavaType javaType) {
        if (content == null) {
            return null;
        }
        try {
            return objectMapper.readValue(content, javaType);
        } catch (IOException e) {
            throw Exceptions.unchecked(e);
        }
    }

    /**
     * 将json字符串反序列化成对象
     *
     * @param jsonString jsonString
     * @param javaType   JavaType
     * @param <T>        T 泛型标记
     * @return Bean
     */
    public static <T> T readValue(String jsonString, JavaType javaType) {
        if (StringUtils.isBlank(jsonString)) {
            return null;
        }
        try {
            return objectMapper.readValue(jsonString, javaType);
        } catch (IOException e) {
            throw Exceptions.unchecked(e);
        }
    }

    /**
     * 将InputStream反序列化成对象
     *
     * @param in       InputStream
     * @param javaType JavaType
     * @param <T>      T 泛型标记
     * @return Bean
     */
    public static <T> T readValue(InputStream in, JavaType javaType) {
        if (in == null) {
            return null;
        }
        try {
            return objectMapper.readValue(in, javaType);
        } catch (IOException e) {
            throw Exceptions.unchecked(e);
        }
    }

    /**
     * 封装 map type，keyClass String
     *
     * @param valueClass value 类型
     * @return MapType
     */
    public static MapType getMapType(Class<?> valueClass) {
        return getMapType(String.class, valueClass);
    }

    /**
     * 封装 map type
     *
     * @param keyClass   key 类型
     * @param valueClass value 类型
     * @return MapType
     */
    public static MapType getMapType(Class<?> keyClass, Class<?> valueClass) {
        return objectMapper.getTypeFactory().constructMapType(Map.class, keyClass, valueClass);
    }

    /**
     * 封装 list type
     *
     * @param elementClass 集合值类型
     * @return CollectionLikeType
     */
    public static CollectionLikeType getListType(Class<?> elementClass) {
        return objectMapper.getTypeFactory().constructCollectionLikeType(List.class, elementClass);
    }

    /**
     * 封装参数化类型
     * 例如： Map.class, String.class, String.class 对应 Map<String, String>
     *
     * @param parametrized     泛型参数化
     * @param parameterClasses 泛型参数类型
     * @return JavaType
     */
    public static JavaType getParametricType(Class<?> parametrized, Class<?>... parameterClasses) {
        return objectMapper.getTypeFactory().constructParametricType(parametrized, parameterClasses);
    }

    /**
     * 读取集合
     *
     * @param content      bytes
     * @param elementClass elementClass
     * @param <T>          泛型
     * @return 集合
     */
    public static <T> List<T> readList(byte[] content, Class<T> elementClass) {
        if (content == null) {
            return Collections.emptyList();
        }
        try {
            return objectMapper.readValue(content, getListType(elementClass));
        } catch (IOException e) {
            throw Exceptions.unchecked(e);
        }
    }

    /**
     * 读取集合
     *
     * @param content      InputStream
     * @param elementClass elementClass
     * @param <T>          泛型
     * @return 集合
     */
    public static <T> List<T> readList(InputStream content, Class<T> elementClass) {
        if (content == null) {
            return Collections.emptyList();
        }
        try {
            return objectMapper.readValue(content, getListType(elementClass));
        } catch (IOException e) {
            throw Exceptions.unchecked(e);
        }
    }

    /**
     * 读取集合
     *
     * @param content      json字符串
     * @param elementClass elementClass
     * @param <T>          泛型
     * @return 集合
     */
    public static <T> List<T> readList(String content, Class<T> elementClass) {
        if (StringUtils.isBlank(content)) {
            return Collections.emptyList();
        }
        try {
            return objectMapper.readValue(content, getListType(elementClass));
        } catch (IOException e) {
            throw Exceptions.unchecked(e);
        }
    }

    /**
     * 读取Map
     *
     * @param content bytes
     * @return Map<String, Object>
     */
    public static Map<String, Object> readMap(byte[] content) {
        return readMap(content, Object.class);
    }

    /**
     * 读取Map
     *
     * @param content InputStream
     * @return Map<String, Object>
     */
    public static Map<String, Object> readMap(InputStream content) {
        return readMap(content, Object.class);
    }

    /**
     * 读取Map
     *
     * @param content json字符串
     * @return Map<String, Object>
     */
    public static Map<String, Object> readMap(String content) {
        return readMap(content, Object.class);
    }

    /**
     * 读取Map
     *
     * @param content    bytes
     * @param valueClass 值类型
     * @param <V>        泛型
     * @return Map<String, V>
     */
    public static <V> Map<String, V> readMap(byte[] content, Class<V> valueClass) {
        return readMap(content, String.class, valueClass);
    }

    /**
     * 读取Map
     *
     * @param content    InputStream
     * @param valueClass 值类型
     * @param <V>        泛型
     * @return Map<String, V>
     */
    public static <V> Map<String, V> readMap(InputStream content, Class<V> valueClass) {
        return readMap(content, String.class, valueClass);
    }

    /**
     * 读取Map
     *
     * @param content    json字符串
     * @param valueClass 值类型
     * @param <V>        泛型
     * @return Map<String, V>
     */
    public static <V> Map<String, V> readMap(String content, Class<V> valueClass) {
        return readMap(content, String.class, valueClass);
    }

    /**
     * 读取Map
     *
     * @param content    bytes
     * @param keyClass   key类型
     * @param valueClass 值类型
     * @param <K>        泛型
     * @param <V>        泛型
     * @return Map<K, V>
     */
    public static <K, V> Map<K, V> readMap(byte[] content, Class<K> keyClass, Class<V> valueClass) {
        if (content == null) {
            return Collections.emptyMap();
        }
        try {
            return objectMapper.readValue(content, getMapType(keyClass, valueClass));
        } catch (IOException e) {
            throw Exceptions.unchecked(e);
        }
    }

    /**
     * 读取Map
     *
     * @param content    InputStream
     * @param keyClass   key类型
     * @param valueClass 值类型
     * @param <K>        泛型
     * @param <V>        泛型
     * @return Map<K, V>
     */
    public static <K, V> Map<K, V> readMap(InputStream content, Class<K> keyClass, Class<V> valueClass) {
        if (content == null) {
            return Collections.emptyMap();
        }
        try {
            return objectMapper.readValue(content, getMapType(keyClass, valueClass));
        } catch (IOException e) {
            throw Exceptions.unchecked(e);
        }
    }

    /**
     * 读取Map
     *
     * @param content    json字符串
     * @param keyClass   key类型
     * @param valueClass 值类型
     * @param <K>        泛型
     * @param <V>        泛型
     * @return Map<K, V>
     */
    public static <K, V> Map<K, V> readMap(String content, Class<K> keyClass, Class<V> valueClass) {
        if (StringUtils.isBlank(content)) {
            return Collections.emptyMap();
        }
        try {
            return objectMapper.readValue(content, getMapType(keyClass, valueClass));
        } catch (IOException e) {
            throw Exceptions.unchecked(e);
        }
    }

    /**
     * jackson 的类型转换
     *
     * @param fromValue   来源对象
     * @param toValueType 转换的类型
     * @param <T>         泛型标记
     * @return 转换结果
     */
    public static <T> T convertValue(Object fromValue, Class<T> toValueType) {
        return objectMapper.convertValue(fromValue, toValueType);
    }

    /**
     * jackson 的类型转换
     *
     * @param fromValue   来源对象
     * @param toValueType 转换的类型
     * @param <T>         泛型标记
     * @return 转换结果
     */
    public static <T> T convertValue(Object fromValue, JavaType toValueType) {
        return objectMapper.convertValue(fromValue, toValueType);
    }

    /**
     * jackson 的类型转换
     *
     * @param fromValue      来源对象
     * @param toValueTypeRef 泛型类型
     * @param <T>            泛型标记
     * @return 转换结果
     */
    public static <T> T convertValue(Object fromValue, TypeReference<T> toValueTypeRef) {
        return objectMapper.convertValue(fromValue, toValueTypeRef);
    }

    /**
     * 将json反序列化成对象
     *
     * @param jsonString jsonString
     * @param type       type
     * @param <T>        T 泛型标记
     * @return Bean
     */
    public static <T> T parse(String jsonString, Type type) {
        if (StringUtils.isBlank(jsonString)) {
            return null;
        }
        try {
            return objectMapper.readValue(jsonString, objectMapper.getTypeFactory().constructType(type));
        } catch (IOException e) {
            throw Exceptions.unchecked(e);
        }
    }

    /**
     * 将json字符串列表反序列化成对象列表
     * <p>注意：任意一条json解析失败会直接抛出异常，整体失败</p>
     *
     * @param jsonStrings json字符串列表
     * @param type        type
     * @param <T>         T 泛型标记
     * @return 对象列表
     */
    public static <T> List<T> parse(List<String> jsonStrings, Type type) {
        List<T> list = new ArrayList<>();
        if (CollectionUtils.isEmpty(jsonStrings)) {
            return list;
        }
        try {
            for (String jsonString : jsonStrings) {
                if (StringUtils.isBlank(jsonString)) {
                    continue;
                }
                T t = objectMapper.readValue(jsonString, objectMapper.getTypeFactory().constructType(type));
                list.add(t);
            }
            return list;
        } catch (IOException e) {
            throw Exceptions.unchecked(e);
        }
    }

    /**
     * tree 转对象
     *
     * @param treeNode  TreeNode
     * @param valueType valueType
     * @param <T>       泛型标记
     * @return 转换结果
     */
    public static <T> T treeToValue(TreeNode treeNode, Class<T> valueType) {
        try {
            return objectMapper.treeToValue(treeNode, valueType);
        } catch (JsonProcessingException e) {
            throw Exceptions.unchecked(e);
        }
    }

    /**
     * 对象转 tree
     *
     * @param fromValue fromValue
     * @param <T>       泛型标记
     * @return JsonNode
     */
    public static <T extends JsonNode> T valueToTree(Object fromValue) {
        return objectMapper.valueToTree(fromValue);
    }

    /**
     * 判断是否可以序列化
     * <p>仅粗略判断存在序列化器，不保证运行时一定序列化成功（循环引用等运行时才会报错）</p>
     *
     * @param value 对象
     * @return 是否可以序列化
     */
    public static boolean canSerialize(Object value) {
        if (value == null) {
            return true;
        }
        return objectMapper.canSerialize(value.getClass());
    }

    /**
     * 判断是否可以反序列化
     * <p>仅粗略判断存在反序列化器，不保证运行时一定解析成功</p>
     *
     * @param type JavaType
     * @return 是否可以反序列化
     */
    public static boolean canDeserialize(JavaType type) {
        return objectMapper.canDeserialize(type);
    }

    /**
     * 检验 json 语法格式
     *
     * @param jsonString json 字符串
     * @return 是否合法
     */
    public static boolean isValidJson(String jsonString) {
        return isValidJson(mapper -> mapper.readTree(jsonString));
    }

    /**
     * 检验 json 语法格式
     *
     * @param content json byte array
     * @return 是否合法
     */
    public static boolean isValidJson(byte[] content) {
        return isValidJson(mapper -> mapper.readTree(content));
    }

    /**
     * 检验 json 语法格式
     *
     * @param input json input stream
     * @return 是否合法
     */
    public static boolean isValidJson(InputStream input) {
        return isValidJson(mapper -> mapper.readTree(input));
    }

    /**
     * 检验 json 语法格式
     *
     * @param jsonParser json parser
     * @return 是否合法
     */
    public static boolean isValidJson(JsonParser jsonParser) {
        return isValidJson(mapper -> mapper.readTree(jsonParser));
    }

    /**
     * 检验 json 语法格式
     *
     * @param consumer ObjectMapper consumer
     * @return 是否合法
     */
    public static boolean isValidJson(CheckedConsumer<ObjectMapper> consumer) {
        ObjectMapper mapper = KiteObjectMapperFactory.getValidJsonObjectMapper();
        mapper.enable(DeserializationFeature.FAIL_ON_TRAILING_TOKENS);
        mapper.enable(DeserializationFeature.FAIL_ON_READING_DUP_TREE_KEY);
        try {
            consumer.accept(mapper);
            return true;
        } catch (Throwable e) {
            return false;
        }
    }

}