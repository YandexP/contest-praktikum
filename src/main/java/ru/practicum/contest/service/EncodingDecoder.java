package ru.practicum.contest.service;

import ru.practicum.contest.dto.stepFour.Congratulation;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.SortedMap;

public class EncodingDecoder {
    public List<Congratulation> getDecoded(String input) {
        SortedMap<String, Charset> charsets = Charset.availableCharsets();
        List<Congratulation> result = new ArrayList<>();
        for (Charset charset : charsets.values()) {
            String decodedString = decode(input, charset);
            if (!containsInvalidCharacters(decodedString)) {
                result.add(new Congratulation(decodedString));
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
