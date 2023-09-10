package ru.practicum.contest.repository;

import ru.practicum.contest.model.Token;

public interface MainRepository {
    void saveResponse(Token token);
}
