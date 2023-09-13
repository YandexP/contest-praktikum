package ru.practicum.contest.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.*;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.DefaultUriBuilderFactory;
import ru.practicum.contest.dto.stepFour.Congratulation;
import ru.practicum.contest.dto.stepFour.Task4DtoResult;
import ru.practicum.contest.dto.stepTwo.Task2DtoDecoded;
import ru.practicum.contest.dto.stepTwo.Task2DtoResult;
import ru.practicum.contest.model.Team;
import ru.practicum.contest.model.Token;
import ru.practicum.contest.repository.MainRepository;

import java.util.HashMap;
import java.util.Map;

@Component
@Slf4j
public class HttpClient {

    private final RestTemplate rest;

    public HttpClient(RestTemplateBuilder builder, MainRepository repository) {
        rest = builder
                .uriTemplateHandler(new DefaultUriBuilderFactory("http://ya.praktikum.fvds.ru:8080"))
                .requestFactory(HttpComponentsClientHttpRequestFactory::new)
                .build();
    }

    public Token addTeam(Team team) {
        try {
            String url = "/dev-day/register";

            Map<String, String> headersMap = new HashMap<>();
            headersMap.put("MAIN_ANSWER", String.valueOf(42));
            HttpHeaders headers = getHeaders(headersMap);

            ResponseEntity<Token> response = makeAndSendRequest(HttpMethod.POST, url, team, Token.class, headers);
            Token token = response.getBody();
            log.info("Token received: {}.", token);

            return token;
        } catch (HttpStatusCodeException e) {
            log.info("Could not send request: {}", e.getMessage());
            throw e;
        }
    }

    public Task2DtoResult sendDecoded(Task2DtoDecoded task2DtoDecoded, String token) {
        try {
            String url = "/dev-day/task/2";

            Map<String, String> headersMap = new HashMap<>();
            headersMap.put("AUTH_TOKEN", token);
            HttpHeaders headers = getHeaders(headersMap);

            ResponseEntity<Task2DtoResult> response = makeAndSendRequest(HttpMethod.POST, url,
                    task2DtoDecoded, Task2DtoResult.class, headers);
            Task2DtoResult result = response.getBody();
            log.info("Decoded received: {}.", result);

            return result;
        } catch (HttpStatusCodeException e) {
            log.info("Could not send request: {}", e.getMessage());
            throw e;
        }
    }

    public Task4DtoResult sendDecoded(Congratulation decodedResult, String token) {
        String url = "/dev-day/task/4";

        Map<String, String> headersMap = new HashMap<>();
        headersMap.put("AUTH_TOKEN", token);
        HttpHeaders headers = getHeaders(headersMap);
        ResponseEntity<Task4DtoResult> response;
        try {
            response = makeAndSendRequest(HttpMethod.POST, url,
                    decodedResult, Task4DtoResult.class, headers);
        } catch (HttpClientErrorException e) {
            return Task4DtoResult.builder().completed(false).build();
        }
        Task4DtoResult result = response.getBody();
        log.info("Response received: {}.", result);

        return result;
    }

    private <T, S> ResponseEntity<S> makeAndSendRequest(HttpMethod method, String path, @Nullable T body,
                                                        Class<S> responseType, HttpHeaders headers) {
        HttpEntity<T> requestEntity = new HttpEntity<>(body, headers);
        return rest.exchange(path, method, requestEntity, responseType);
    }

    private HttpHeaders getHeaders(Map<String, String> h) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        if (!h.isEmpty()) {
            headers.setAll(h);
        }
        return headers;
    }
}
