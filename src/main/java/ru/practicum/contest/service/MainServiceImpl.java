package ru.practicum.contest.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.practicum.contest.dto.stepOne.TeamDto;
import ru.practicum.contest.dto.stepOne.TokenDto;
import ru.practicum.contest.dto.stepTwo.CaesarDecoder;
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
public class MainServiceImpl implements MainService {

    private final MainRepository repository;

    private final HttpClient httpClient;

    private final CaesarDecoder caesarDecoder;

    @Override
    public TokenDto addTeam(TeamDto teamDto) {
        log.info("Adding team: {}.", teamDto);
        Token token = httpClient.addTeam(TeamMapper.toModel(teamDto));
        log.info("Token received: {}.", token);
        repository.saveToken(token);
        return TokenMapper.toDto(token);
    }

    @Override
    public Task2DtoResult sendForDecode(Task2DtoRequest task2DtoRequest, String token) {
        log.info("Decoding: {}.", task2DtoRequest);
        Task2DtoDecoded decoded = Task2DtoDecoded.builder()
                .decoded(caesarDecoder.decipher(task2DtoRequest.getEncoded(), task2DtoRequest.getOffset()))
                .build();

        log.info("Decoded: {}.", decoded);
        Task2DtoResult result = httpClient.sendDecoded(decoded, token);
        log.info("Result: {}.", result);
        repository.saveDecoded(result);
        return result;
    }
}