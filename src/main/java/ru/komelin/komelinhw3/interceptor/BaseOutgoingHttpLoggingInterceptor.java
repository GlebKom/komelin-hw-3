package ru.komelin.komelinhw3.interceptor;

import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpResponse;

import java.io.IOException;
import java.time.Duration;

public class BaseOutgoingHttpLoggingInterceptor extends OutgoingHttpLoggingInterceptor{
    @Override
    protected void logRequest(HttpRequest request) {
        logger.info("Outgoing request: METHOD={} URI={}", request.getMethod(), request.getURI().getPath());
    }

    @Override
    protected void logResponse(HttpRequest request, ClientHttpResponse response, Duration duration) {
        String statusCode = "undefined";
        try {
            statusCode = response.getStatusCode().toString();
        } catch (IOException e) {
            logger.warn("Ошибка при получении кода ответа.");
        }

        logger.info("Incoming response: METHOD={} URI={} STATUS={} DURATION={}ms", request.getMethod(),
                request.getURI(), statusCode, duration.toMillis());
    }
}
