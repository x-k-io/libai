package com.kite.libai.boot.configuration;

import com.kite.libai.boot.jackson.KiteJavaTimeModule;
import com.kite.libai.boot.jackson.KiteLongToStringModule;
import com.kite.libai.core.utils.DateUtils;
import com.kite.libai.core.utils.JsonUtils;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

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
        builder.modules(new KiteJavaTimeModule(), new KiteLongToStringModule());
        builder.configure(JsonUtils.getInstance());
    }
}
