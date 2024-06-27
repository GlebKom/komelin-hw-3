package ru.komelin.komelinhw3.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.time.Duration;
import java.util.Spliterator;
import java.util.Spliterators;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class ExtendedIncomingIncomingHttpLoggingInterceptor extends IncomingHttpLoggingInterceptor {
    protected void logRequest(HttpServletRequest request) {
        String headers = StreamSupport
                .stream(Spliterators.spliteratorUnknownSize(request.getHeaderNames().asIterator(),
                        Spliterator.ORDERED), false)
                .collect(Collectors.joining(", "));

        logger.info("Incoming request: METHOD={} URI={} HEADERS={}", request.getMethod(), request.getRequestURI(),
                headers);
    }

    protected void logResponse(HttpServletRequest request, HttpServletResponse response, Duration duration) {
        String headers = String.join(", ", response.getHeaderNames());
        logger.info("Outgoing response: METHOD={} URI={} STATUS={} DURATION={}ms headers={}", request.getMethod(),
                request.getRequestURI(), response.getStatus(), duration.toMillis(), headers);
    }
}
