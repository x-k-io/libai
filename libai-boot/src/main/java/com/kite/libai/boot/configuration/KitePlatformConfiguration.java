package com.kite.libai.boot.configuration;

import com.kite.libai.boot.properties.AliYunProperties;
import com.kite.libai.boot.properties.KiteLibaiProperties;
import com.kite.libai.boot.properties.KiteJobProperties;
import com.kite.libai.boot.properties.TencentCloudProperties;
import com.kite.libai.boot.properties.WeChatPayProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableConfigurationProperties({
        AliYunProperties.class,
        WeChatPayProperties.class,
        KiteLibaiProperties.class,
        KiteJobProperties.class,
        TencentCloudProperties.class
})
public class KitePlatformConfiguration {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


}
