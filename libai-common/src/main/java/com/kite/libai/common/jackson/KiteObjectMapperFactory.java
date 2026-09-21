package com.kite.libai.common.jackson;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.json.JsonReadFeature;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.util.Locale;

public class KiteObjectMapperFactory {

    private static final ObjectMapper GLOBAL_OBJECT_MAPPER = new JacksonObjectMapper();
    private static final ObjectMapper API_READ_OBJECT_MAPPER = new JacksonObjectMapper();
    private static final ObjectMapper API_WRITE_OBJECT_MAPPER = createApiWriteObjectMapper();
    private static final ObjectMapper VALID_JSON_OBJECT_MAPPER = createValidJsonObjectMapper();

    public static ObjectMapper getObjectMapper() {
        return GLOBAL_OBJECT_MAPPER;
    }

    public static ObjectMapper getApiReadObjectMapper() {
        return API_READ_OBJECT_MAPPER;
    }

    public static ObjectMapper getApiWriteObjectMapper() {
        return API_WRITE_OBJECT_MAPPER;
    }

    public static ObjectMapper getValidJsonObjectMapper() {
        return VALID_JSON_OBJECT_MAPPER;
    }

    private static ObjectMapper createApiWriteObjectMapper() {
        ObjectMapper objectMapper = new JacksonObjectMapper();
        objectMapper.setSerializerFactory(
                objectMapper.getSerializerFactory().withSerializerModifier(new KiteBeanSerializerModifier()));
        return objectMapper;
    }

    private static ObjectMapper createValidJsonObjectMapper() {
        ObjectMapper objectMapper = new JacksonObjectMapper();
        objectMapper.enable(DeserializationFeature.FAIL_ON_TRAILING_TOKENS);
        objectMapper.enable(DeserializationFeature.FAIL_ON_READING_DUP_TREE_KEY);
        return objectMapper;
    }


    private static class JacksonObjectMapper extends ObjectMapper {
        private static final KiteJavaTimeModule KITE_JAVA_TIME_MODULE = new KiteJavaTimeModule();
        private static final KiteLongToStringModule KITE_LONG_TO_STRING_MODULE = new KiteLongToStringModule();

        private static final Locale CHINA = Locale.CHINA;

        JacksonObjectMapper() {
            super(jsonFactory());
            super.setLocale(CHINA);
            // 忽略未知字段
            super.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            // 空Bean不抛异常
            super.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
            // Java8时间模块
            registerModule(KITE_JAVA_TIME_MODULE);
            // Long 转 String
            registerModule(KITE_LONG_TO_STRING_MODULE);
            super.findAndRegisterModules();
        }

        private static JsonFactory jsonFactory() {
            return JsonFactory.builder()
                    // 可解析反斜杠引用的所有字符
                    .configure(JsonReadFeature.ALLOW_BACKSLASH_ESCAPING_ANY_CHARACTER, true)
                    // 允许JSON字符串包含未转义控制字符（内部系统兼容）
                    .configure(JsonReadFeature.ALLOW_UNESCAPED_CONTROL_CHARS, true)
                    .build();
        }
    }
}
