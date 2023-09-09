package ru.practicum.contest.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.practicum.contest.dto.TeamDto;
import ru.practicum.contest.dto.TokenDto;
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
        repository.saveResponse(token);
        return TokenMapper.toDto(token);
    }
}