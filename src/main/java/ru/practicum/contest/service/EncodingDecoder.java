package ru.practicum.contest.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import ru.practicum.contest.dto.stepFour.Congratulation;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

@Component
@Slf4j
public class EncodingDecoder {
    public List<Congratulation> getDecoded(String input) {
        List<Charset> charsets = new ArrayList<>(Charset.availableCharsets().values());
        List<Congratulation> result = new ArrayList<>();
        for (int i = 0; i < charsets.size(); i++) {
            try {
                byte[] bytes = input.getBytes(charsets.get(i));
                for (int j = 1; j < charsets.size(); j++) {
                    String decodedString = new String(bytes, charsets.get(j));
                    if (decodedString.toLowerCase().contains("поздравляем")) {
                        result.add(new Congratulation(decodedString));

                    }
                }
            } catch (UnsupportedOperationException e) {
                log.info(charsets.get(i).displayName() + " unsupported charset");
            }
        }
        return result;
    }

    private String decode(String s, Charset set) {
        try {
            byte[] bytes = s.getBytes(set);
            return new String(bytes, StandardCharsets.UTF_8);
        } catch (UnsupportedOperationException e) {
            return "Unsupported set";
        }
    }

    private boolean containsInvalidCharacters(String s) {
        return s.contains("�") || s.contains("??") || s.contains("\u001B") || s.contains("!!")
                || s.contains("@") || s.contains("\u000F") || s.contains("*");
    }
}
