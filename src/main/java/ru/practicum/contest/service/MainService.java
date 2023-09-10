package ru.practicum.contest.service;

import ru.practicum.contest.dto.TeamDto;
import ru.practicum.contest.dto.TokenDto;

public interface MainService {
    TokenDto addTeam(TeamDto tokenDto);
}
