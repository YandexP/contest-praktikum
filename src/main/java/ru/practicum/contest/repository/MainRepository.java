package ru.practicum.contest.repository;

import ru.practicum.contest.dto.stepTwo.Task2DtoResult;
import ru.practicum.contest.model.Token;

public interface MainRepository {
    void saveToken(Token token);

    void saveDecoded(Task2DtoResult task2DtoResult);
}
