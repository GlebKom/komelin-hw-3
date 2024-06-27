package ru.komelin.komelinhw3.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;

import java.io.IOException;
import java.time.Duration;
import java.time.Instant;


public abstract class IncomingHttpLoggingInterceptor implements HandlerInterceptor {

    protected static final Logger logger = LoggerFactory.getLogger(IncomingHttpLoggingInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws IOException {
        request.setAttribute("startTime", Instant.now());
        logRequest(request);
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        Instant startTime = (Instant) request.getAttribute("startTime");
        logResponse(request, response, Duration.between(startTime, Instant.now()));
    }

    protected abstract void logRequest(HttpServletRequest request);

    protected abstract void logResponse(HttpServletRequest request, HttpServletResponse response, Duration duration);
}