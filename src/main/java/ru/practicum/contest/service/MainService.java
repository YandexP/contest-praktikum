package ru.practicum.contest.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.practicum.contest.dto.stepOne.TeamDto;
import ru.practicum.contest.dto.stepOne.TokenDto;
import ru.practicum.contest.dto.stepThree.PasswordGuess;
import ru.practicum.contest.dto.stepThree.Task3DtoResult;
import ru.practicum.contest.dto.stepTwo.Task2DtoDecoded;
import ru.practicum.contest.dto.stepTwo.Task2DtoRequest;
import ru.practicum.contest.dto.stepTwo.Task2DtoResult;
import ru.practicum.contest.mapper.TeamMapper;
import ru.practicum.contest.mapper.TokenMapper;
import ru.practicum.contest.model.Token;
import ru.practicum.contest.repository.MainRepository;

@Service
@Slf4j
@RequiredArgsConstructor
public class MainService {

    private final MainRepository repository;

    private final HttpClient httpClient;

    private final CaesarDecoder caesarDecoder;

    private final PasswordGenerator passwordGenerator;

    public TokenDto addTeam(TeamDto teamDto) {
        log.info("Adding team: {}.", teamDto);
        Token token = httpClient.addTeam(TeamMapper.toModel(teamDto));
        log.info("Token received: {}.", token);
        repository.saveToTxt(token);
        return TokenMapper.toDto(token);
    }

    public Task2DtoResult decode(Task2DtoRequest task2DtoRequest, String token) {
        log.info("Decoding: {}.", task2DtoRequest);
        Task2DtoDecoded decoded = Task2DtoDecoded.builder()
                .decoded(caesarDecoder.decipher(task2DtoRequest.getEncoded(), task2DtoRequest.getOffset()))
                .build();

        log.info("Decoded: {}.", decoded);
        Task2DtoResult result = httpClient.sendDecoded(decoded, token);
        log.info("Result: {}.", result);
        repository.saveToTxt(result);
        return result;
    }

    public Task3DtoResult checkPassword(String token) {
        log.info("Guessing password.");
        Integer count = 1;
        log.info("Number of tries: {}", count);
        PasswordGuess passwordGuess = new PasswordGuess(Long.toHexString(Long.MAX_VALUE / 2));
        Task3DtoResult result = httpClient.checkPassword(passwordGuess, token);
        boolean bigger = result.getPrompt().equals(">pass");

        while (!result.getCompleted()) {
            String password = passwordGenerator.generatePassword(bigger);
            log.info("Checking password: {}", password);
            passwordGuess.setPassword(password);
            result = httpClient.checkPassword(passwordGuess, token);
            bigger = result.getPrompt().equals(">pass");
            log.info("Number of tries: {}", count);
            count++;
        }

        log.info("Result: {}.", result);
        passwordGenerator.reset();

        repository.saveToTxt(passwordGuess);
        repository.saveToTxt(result);

        return result;
    }
}