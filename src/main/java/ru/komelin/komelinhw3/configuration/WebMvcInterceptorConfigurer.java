package ru.komelin.komelinhw3.configuration;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import ru.komelin.komelinhw3.interceptor.IncomingHttpLoggingInterceptor;

@RequiredArgsConstructor
@Component
@ConditionalOnProperty(prefix = "http.logging", name = "enabled", havingValue = "true", matchIfMissing = true)
public class WebMvcInterceptorConfigurer implements WebMvcConfigurer {
    private final IncomingHttpLoggingInterceptor incomingHttpLoggingInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(incomingHttpLoggingInterceptor);
    }
}
