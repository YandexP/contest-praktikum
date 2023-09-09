package ru.practicum.contest.repository;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import ru.practicum.contest.model.Token;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;

@Component
@Slf4j
public class TokenRepository implements MainRepository {
    @Override
    public void saveResponse(Token token) {
        File file = new File("src/main/resources/token.txt");

        try (OutputStreamWriter writer = new OutputStreamWriter
                (new FileOutputStream(file), StandardCharsets.UTF_8)) {
            writer.write(token.toString());
        } catch (IOException e) {
            log.error("Can't save to file. {} ", e.getMessage());
        }
    }
}
