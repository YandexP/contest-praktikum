package ru.practicum.contest.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.practicum.contest.dto.TokenDto;
import ru.practicum.contest.service.MainService;

@RestController("/")
@Slf4j
@RequiredArgsConstructor
public class ContestController {

    private final MainService mainService;

    @PostMapping
    public TokenDto addTeam() {

    }
}
