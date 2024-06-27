package ru.komelin.komelinhw3;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatusCode;
import org.springframework.test.web.client.ExpectedCount;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.web.client.RestTemplate;

import static org.springframework.test.web.client.match.MockRestRequestMatchers.method;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withStatus;


@SpringBootTest(classes = KomelinHw3Application.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class IncomingHttpLoggingInterceptorTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void testIncomingRequestLogs(){
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set("User-Agent", "");
        httpHeaders.set("Authorization", "");
        httpHeaders.set("Accept", "");
        restTemplate.getForObject("http://localhost:" + port + "/",String.class, httpHeaders);
    }
}
