package ru.practicum.contest.repository;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;

@Component
@Slf4j
public class MainRepository {

    public <T> void saveToTxt(T result) {
        StringBuilder sb = new StringBuilder("src/main/resources/");
        sb.append(result.getClass().getSimpleName());
        sb.append(".txt");

        File file = new File(sb.toString());

        try (OutputStreamWriter writer = new OutputStreamWriter
                (new FileOutputStream(file), StandardCharsets.UTF_8)) {
            writer.write(result.toString());
        } catch (IOException e) {
            log.error("Can't save to file. {} ", e.getMessage());
        }
    }
}
