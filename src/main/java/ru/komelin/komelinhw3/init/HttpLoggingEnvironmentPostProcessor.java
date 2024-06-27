package ru.komelin.komelinhw3.init;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.env.EnvironmentPostProcessor;
import org.springframework.core.env.ConfigurableEnvironment;
import ru.komelin.komelinhw3.exception.HttpLoggingStartupException;

import java.util.List;

@Slf4j
public class HttpLoggingEnvironmentPostProcessor implements EnvironmentPostProcessor {

    @Override
    public void postProcessEnvironment(ConfigurableEnvironment environment, SpringApplication application) {
        log.info("Вызов HttpLoggingEnvironmentPostProcessor");

        List<String> booleanPropertyNames = List.of("http.logging.enabled", "http.logging.extended");
        checkBooleanPropertiesForAccuracy(booleanPropertyNames, environment);
    }

    private void checkBooleanPropertiesForAccuracy(List<String> propertyNames, ConfigurableEnvironment environment) {
        for (String name : propertyNames) {
            if (!isPropertyBoolean(name, environment)) {
                throw new HttpLoggingStartupException(String.format("Ошибка при проверке свойства '%s'", name));
            }
        }
    }

    private boolean isPropertyBoolean(String propertyName, ConfigurableEnvironment environment) {
        String httpLoggingEnabledProperty = environment.getProperty(propertyName);
        return Boolean.TRUE.toString().equals(httpLoggingEnabledProperty) ||
                Boolean.FALSE.toString().equals(httpLoggingEnabledProperty);
    }
}

