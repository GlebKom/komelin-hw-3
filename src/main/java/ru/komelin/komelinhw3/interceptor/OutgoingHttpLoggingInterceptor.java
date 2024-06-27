package ru.komelin.komelinhw3.interceptor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.time.Duration;
import java.time.Instant;

@Component
public abstract class OutgoingHttpLoggingInterceptor implements ClientHttpRequestInterceptor {

    protected static final Logger logger = LoggerFactory.getLogger(OutgoingHttpLoggingInterceptor.class);
    @Override
    public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution) throws IOException {
        logRequest(request);
        Instant startTime = Instant.now();
        ClientHttpResponse response = execution.execute(request, body);
        logResponse(request, response, Duration.between(startTime, Instant.now()));

        return response;
    }

    protected abstract void logRequest(HttpRequest request);
    protected abstract void logResponse(HttpRequest request, ClientHttpResponse response, Duration duration);
}
