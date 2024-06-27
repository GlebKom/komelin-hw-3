package ru.komelin.komelinhw3.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.time.Duration;

public class BaseIncomingIncomingHttpLoggingInterceptor extends IncomingHttpLoggingInterceptor {
    protected void logRequest(HttpServletRequest request) {
        logger.info("Incoming request: METHOD={} URI={}", request.getMethod(), request.getRequestURI());
    }

    protected void logResponse(HttpServletRequest request, HttpServletResponse response, Duration duration) {
        logger.info("Outgoing response: METHOD={} URI={} STATUS={} DURATION={}ms", request.getMethod(),
                request.getRequestURI(), response.getStatus(), duration.toMillis());
    }
}
