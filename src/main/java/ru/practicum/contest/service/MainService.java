package ru.practicum.contest.service;

import ru.practicum.contest.dto.Task2DtoRequest;
import ru.practicum.contest.dto.Task2DtoResult;
import ru.practicum.contest.dto.TeamDto;
import ru.practicum.contest.dto.TokenDto;

public interface MainService {
    TokenDto addTeam(TeamDto tokenDto);

    Task2DtoResult sendForDecode(Task2DtoRequest task2DtoRequest);
}
