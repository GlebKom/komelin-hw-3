package ru.komelin.komelinhw3.interceptor;

import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpResponse;

import java.io.IOException;
import java.time.Duration;

public class ExtendedOutgoingHttpLoggerInterceptor extends OutgoingHttpLoggingInterceptor{
    @Override
    protected void logRequest(HttpRequest request) {
        logger.info("Outgoing request: METHOD={} URI={} HEADERS={}", request.getMethod(), request.getURI().getPath(),
                request.getHeaders());
    }

    @Override
    protected void logResponse(HttpRequest request, ClientHttpResponse response, Duration duration) {
        String statusCode = "undefined";
        try {
            statusCode = response.getStatusCode().toString();
        } catch (IOException e) {
            logger.warn("Ошибка при получении кода ответа.");
        }

        logger.info("Incoming response: METHOD={} URI={} HEADERS = {} STATUS={} DURATION={}ms", request.getMethod(),
                request.getURI(), response.getHeaders(), statusCode, duration.toMillis());
    }
}
