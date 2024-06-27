package ru.komelin.komelinhw3.configuration;

import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.web.servlet.WebMvcAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import ru.komelin.komelinhw3.interceptor.BaseIncomingIncomingHttpLoggingInterceptor;
import ru.komelin.komelinhw3.interceptor.BaseOutgoingHttpLoggingInterceptor;
import ru.komelin.komelinhw3.interceptor.ExtendedIncomingIncomingHttpLoggingInterceptor;
import ru.komelin.komelinhw3.interceptor.ExtendedOutgoingHttpLoggerInterceptor;
import ru.komelin.komelinhw3.interceptor.IncomingHttpLoggingInterceptor;
import ru.komelin.komelinhw3.interceptor.OutgoingHttpLoggingInterceptor;

@Configuration
@ComponentScan(basePackages = "ru.komelin.komelinhw3")
@ConditionalOnProperty(prefix = "http.logging", name = "enabled", havingValue = "true", matchIfMissing = true)
@AutoConfigureAfter(WebMvcAutoConfiguration.class)
public class HttpLoggingAutoConfiguration{

    @Bean
    @ConditionalOnProperty(prefix = "http.logging", name = "extended", havingValue = "true")
    public IncomingHttpLoggingInterceptor extendedIncomingHttpLoggingInterceptor() {
        return new ExtendedIncomingIncomingHttpLoggingInterceptor();
    }

    @Bean
    @ConditionalOnProperty(prefix = "http.logging", name = "extended", havingValue = "false")
    public IncomingHttpLoggingInterceptor baseIncomingHttpLoggingInterceptor() {
        return new BaseIncomingIncomingHttpLoggingInterceptor();
    }

    @Bean
    @ConditionalOnProperty(prefix = "http.logging", name = "extended", havingValue = "true")
    public OutgoingHttpLoggingInterceptor extendedOutgoingHttpLoggingInterceptor() {
        return new ExtendedOutgoingHttpLoggerInterceptor();
    }

    @Bean
    @ConditionalOnProperty(prefix = "http.logging", name = "extended", havingValue = "false")
    public OutgoingHttpLoggingInterceptor baseOutgoingHttpLoggingInterceptor() {
        return new BaseOutgoingHttpLoggingInterceptor();
    }

    @Bean
    public RestTemplate restTemplate(OutgoingHttpLoggingInterceptor outgoingHttpLoggingInterceptor) {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.getInterceptors().add(outgoingHttpLoggingInterceptor);
        return restTemplate;
    }
}
