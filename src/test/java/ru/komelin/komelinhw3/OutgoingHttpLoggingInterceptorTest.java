package ru.komelin.komelinhw3;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.method;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withStatus;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
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

@SpringBootTest(classes = KomelinHw3Application.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class OutgoingHttpLoggingInterceptorTest {

    @LocalServerPort
    private int port;


    @Autowired
    private RestTemplate restTemplate;

    private MockRestServiceServer mockServer;

    @BeforeEach
    public void init() {
        mockServer = MockRestServiceServer.createServer(restTemplate);
        mockServer.expect(ExpectedCount.once(),
                        requestTo("http://localhost:" + port + "/"))
                .andExpect(method(HttpMethod.GET))
                .andRespond(withStatus(HttpStatusCode.valueOf(200)));
    }
    @Test
    public void testOutgoingRequestLogs(){
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set("User-Agent", "");
        httpHeaders.set("Authorization", "");
        httpHeaders.set("Accept", "");
        restTemplate.getForObject("http://localhost:" + port + "/",String.class, httpHeaders);
        mockServer.verify();
    }
}
