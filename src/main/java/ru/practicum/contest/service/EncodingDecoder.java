package ru.practicum.contest.service;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.SortedMap;

public class EncodingDecoder {
    public void printOutAnswers(String input) {
        SortedMap<String, Charset> charsets = Charset.availableCharsets();
        for (Charset charset : charsets.values()) {
            String decodedString = decode(input, charset);
            if (!containsInvalidCharacters(decodedString)) {
                System.out.println(charset + ":" + decodedString);
            }
        }
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
