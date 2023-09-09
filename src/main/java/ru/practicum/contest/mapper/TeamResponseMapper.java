package ru.practicum.contest.mapper;

import org.mapstruct.Mapper;
import ru.practicum.contest.dto.TokenDto;
import ru.practicum.contest.model.Token;

@Mapper
public class TeamResponseMapper {
    public TokenDto toDto(Token token) {
        if (token == null) {
            return null;
        }

        return new TokenDto(token.getName(), token.getToken(), token.getNextTaskUrl());
    }

    public Token toModel(TokenDto tokenDto) {
        if (tokenDto == null) {
            return null;
        }

        return new Token(tokenDto.getName(), tokenDto.getToken(), tokenDto.getNextTaskUrl());
    }
}
