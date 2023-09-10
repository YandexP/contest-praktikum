package ru.practicum.contest.service;

import ru.practicum.contest.dto.stepOne.TeamDto;
import ru.practicum.contest.dto.stepOne.TokenDto;
import ru.practicum.contest.dto.stepTwo.Task2DtoRequest;
import ru.practicum.contest.dto.stepTwo.Task2DtoResult;

public interface MainService {
    TokenDto addTeam(TeamDto tokenDto);

    Task2DtoResult sendForDecode(Task2DtoRequest task2DtoRequest, String token);
}
