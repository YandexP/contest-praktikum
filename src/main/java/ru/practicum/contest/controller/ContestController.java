package ru.practicum.contest.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;
import ru.practicum.contest.dto.stepTwo.Task2DtoRequest;
import ru.practicum.contest.dto.stepTwo.Task2DtoResult;
import ru.practicum.contest.dto.stepOne.TeamDto;
import ru.practicum.contest.dto.stepOne.TokenDto;
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

    @PostMapping("decode")
    public Task2DtoResult sendDecoded(@RequestBody Task2DtoRequest task2DtoRequest,
                                      @RequestHeader("AUTH_TOKEN") String token) {
        log.info("Decoding: {}. Token: {}", task2DtoRequest, token);
        Task2DtoResult task2DtoResponse = mainService.sendForDecode(task2DtoRequest, token);
        log.info("Decoded: {}.", task2DtoResponse);
        return task2DtoResponse;
    }
}
