package ru.practicum.contest.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.practicum.contest.dto.*;
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

    @Override
    public TokenDto addTeam(TeamDto teamDto) {
        log.info("Adding team: {}.", teamDto);
        Token token = httpClient.addTeam(TeamMapper.toModel(teamDto));
        log.info("Token received: {}.", token);
        repository.saveToken(token);
        return TokenMapper.toDto(token);
    }

    @Override
    public Task2DtoResult sendForDecode(Task2DtoRequest task2DtoRequest) {
        log.info("Sending: {}.", task2DtoRequest);
        Task2DtoDecoded decoded = decode((task2DtoRequest));
        log.info("Decoded: {}.", decoded);
        Task2DtoResult result = httpClient.sendDecoded(decoded);
        log.info("Result: {}.", result);
        repository.saveDecoded(result);
        return result;
    }

    private Task2DtoDecoded decode(Task2DtoRequest task2DtoRequest) {
        StringBuilder strBox = new StringBuilder();
        char tmp;
        for (int i = 0; i < task2DtoRequest.getEncoded().length(); i++) {
            tmp = task2DtoRequest.getEncoded().charAt(i);
            if (Character.isLetter(task2DtoRequest.getEncoded().charAt(i))) {
                tmp += task2DtoRequest.getOffset() % 26;
                if (tmp > 'z')
                    tmp = (char)(tmp % 'z' + 'a');
                else if (tmp < 'a')
                    tmp = (char)(tmp + 25);
            }
            strBox.append(tmp);
        }
        return Task2DtoDecoded.builder()
                .decoded(strBox.toString().toUpperCase())
                .build();
    }
}