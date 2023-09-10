package ru.practicum.contest.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.practicum.contest.dto.stepOne.TeamDto;
import ru.practicum.contest.dto.stepOne.TokenDto;
import ru.practicum.contest.dto.stepTwo.Task2DtoDecoded;
import ru.practicum.contest.dto.stepTwo.Task2DtoRequest;
import ru.practicum.contest.dto.stepTwo.Task2DtoResult;
import ru.practicum.contest.mapper.TeamMapper;
import ru.practicum.contest.mapper.TokenMapper;
import ru.practicum.contest.model.Token;
import ru.practicum.contest.repository.MainRepository;

@Service
@Slf4j
@RequiredArgsConstructor
public class MainServiceImpl implements MainService {

    private final MainRepository repository;

    private final HttpClient httpClient;

    @Override
    public TokenDto addTeam(TeamDto teamDto) {
        log.info("Adding team: {}.", teamDto);
        Token token = httpClient.addTeam(TeamMapper.toModel(teamDto));
        log.info("Token received: {}.", token);
        repository.saveToken(token);
        return TokenMapper.toDto(token);
    }

    @Override
    public Task2DtoResult sendForDecode(Task2DtoRequest task2DtoRequest, String token) {
        log.info("Decoding: {}.", task2DtoRequest);
        Task2DtoDecoded decoded = decode((task2DtoRequest));
        log.info("Decoded: {}.", decoded);
        Task2DtoResult result = httpClient.sendDecoded(decoded, token);
        log.info("Result: {}.", result);
        repository.saveDecoded(result);
        return result;
    }

    private Task2DtoDecoded decode(Task2DtoRequest task2DtoRequest) {
        int k = Integer.parseInt("-" + task2DtoRequest.getOffset());
        String string = "";
        for (int i = 0; i < task2DtoRequest.getEncoded().length(); i++) {
            char c = task2DtoRequest.getEncoded().charAt(i);
            if (c >= 'a' && c <= 'z')// Если символ в строке строчный
            {
                c += k % 26;// мобильный ключ% 26 бит
                if (c < 'a')
                    c += 26;// слева налево
                if (c > 'z')
                    c -= 26;// направо
            } else if (c >= 'A' && c <= 'Z')// Если символ в строке в верхнем регистре
            {
                c += k % 26;// мобильный ключ% 26 бит
                if (c < 'A')
                    c += 26;// слева налево
                if (c > 'Z')
                    c -= 26;// направо
            }
            string += c;// Объединяем расшифрованные символы в строку
        }
        return Task2DtoDecoded.builder()
                .decoded(string)
                .build();
    }
}