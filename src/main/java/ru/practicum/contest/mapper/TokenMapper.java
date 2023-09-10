package ru.practicum.contest.mapper;

import ru.practicum.contest.dto.stepOne.TokenDto;
import ru.practicum.contest.model.Token;

public class TokenMapper {
    public static TokenDto toDto(Token token) {
        return new TokenDto(token.getName(), token.getToken(), token.getNextTaskUrl());
    }

    public static Token toModel(TokenDto tokenDto) {
        return new Token(tokenDto.getName(), tokenDto.getToken(), tokenDto.getNextTaskUrl());
    }
}
