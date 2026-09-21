package com.kite.libai.boot.jackson;

import com.kite.libai.common.jackson.KiteObjectMapperFactory;
import org.springframework.http.MediaType;

public class MappingApiJackson2HttpMessageConverter extends AbstractReadWriteJackson2HttpMessageConverter {

    public MappingApiJackson2HttpMessageConverter() {
        super(KiteObjectMapperFactory.getApiReadObjectMapper(), KiteObjectMapperFactory.getApiWriteObjectMapper(), MediaType.APPLICATION_JSON,
                new MediaType("application", "*+json"));
    }
}