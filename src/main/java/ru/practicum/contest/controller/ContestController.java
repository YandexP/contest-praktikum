package ru.practicum.contest.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.practicum.contest.dto.TeamDto;
import ru.practicum.contest.dto.TokenDto;
import ru.practicum.contest.service.MainService;

@RestController("/")
@Slf4j
@RequiredArgsConstructor
public class ContestController {

    private final MainService mainService;

    @PostMapping
    public TokenDto addTeam(@RequestBody TeamDto teamDto) {
        log.info("Adding team: {}.", teamDto);
        TokenDto tokenDto = mainService.addTeam(teamDto);
        log.info("Token received: {}.", tokenDto);
        return tokenDto;
    }
}
