package ru.practicum.contest.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.practicum.contest.dto.stepFour.Congratulation;
import ru.practicum.contest.dto.stepFour.Task4DtoResult;
import ru.practicum.contest.dto.stepOne.TeamDto;
import ru.practicum.contest.dto.stepOne.TokenDto;
import ru.practicum.contest.dto.stepTwo.Task2DtoDecoded;
import ru.practicum.contest.dto.stepTwo.Task2DtoRequest;
import ru.practicum.contest.dto.stepTwo.Task2DtoResult;
import ru.practicum.contest.mapper.TeamMapper;
import ru.practicum.contest.mapper.TokenMapper;
import ru.practicum.contest.model.Token;
import ru.practicum.contest.repository.MainRepository;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class MainService {

    private final MainRepository repository;

    private final HttpClient httpClient;

    private final CaesarDecoder caesarDecoder;

    private final EncodingDecoder encodingDecoder;

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

    public Task4DtoResult encodingDecode(Congratulation congratulation, String token) {
        log.info("Decoding: {}.", congratulation.getCongratulation());
        List<Congratulation> decoded = encodingDecoder.getDecoded(congratulation.getCongratulation());

        Task4DtoResult task4DtoResult = httpClient.sendDecoded(decoded.get(0), token);
        boolean completed = task4DtoResult.getCompleted();
        int i = 1;
        while (!completed && i < decoded.size()) {
            log.info("Sending: {}", decoded.get(i));
            Task4DtoResult result = httpClient.sendDecoded(decoded.get(i), token);
            log.info("Response: {}", result);
            completed = result.getCompleted();
            i++;
        }
        repository.saveToTxt(task4DtoResult);
        return task4DtoResult;
    }

    public List<Congratulation> testEncode(Congratulation congratulation, String token) {
        return encodingDecoder.getDecoded(congratulation.getCongratulation());
    }
}