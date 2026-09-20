package com.kite.libai.boot.configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kite.libai.boot.jackson.KiteJavaTimeModule;
import com.kite.libai.boot.jackson.KiteLongToStringModule;
import com.kite.libai.common.utils.DateUtils;
import com.kite.libai.common.utils.JsonUtils;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;

import java.time.ZoneId;
import java.util.Locale;
import java.util.TimeZone;

@Configuration
public class JacksonConfiguration implements Jackson2ObjectMapperBuilderCustomizer {

    @Override
    public void customize(Jackson2ObjectMapperBuilder builder) {
        builder.locale(Locale.CHINA);
        builder.timeZone(TimeZone.getTimeZone(ZoneId.systemDefault()));
        builder.simpleDateFormat(DateUtils.PATTERN_DATETIME);
        // 处理日期和Long类型
        builder.findModulesViaServiceLoader(true);
        builder.modulesToInstall(new KiteJavaTimeModule(), new KiteLongToStringModule());
        builder.configure(JsonUtils.getInstance());
    }

    @Bean
    public MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter(ObjectMapper objectMapper) {
        objectMapper.registerModule(new KiteJavaTimeModule());
        objectMapper.registerModule(new KiteLongToStringModule());
        return new MappingJackson2HttpMessageConverter(objectMapper);
    }
}
