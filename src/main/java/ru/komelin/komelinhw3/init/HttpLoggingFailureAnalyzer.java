package ru.komelin.komelinhw3.init;

import org.springframework.boot.diagnostics.AbstractFailureAnalyzer;
import org.springframework.boot.diagnostics.FailureAnalysis;
import ru.komelin.komelinhw3.exception.HttpLoggingStartupException;

public class HttpLoggingFailureAnalyzer extends AbstractFailureAnalyzer<HttpLoggingStartupException> {
    @Override
    protected FailureAnalysis analyze(Throwable rootFailure, HttpLoggingStartupException cause) {
        return new FailureAnalysis(cause.getMessage(), "Укажите валидные значения для свойств", cause);
    }
}
