package ru.practicum.contest.service;

import ru.practicum.contest.dto.TokenDto;
import ru.practicum.contest.model.Token;

public interface MainService {
    TokenDto addToken(TokenDto tokenDto);
}
