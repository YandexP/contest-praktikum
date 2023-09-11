package ru.practicum.contest.service;

import org.springframework.stereotype.Component;

@Component
public class PasswordGenerator {
    private Long max = Long.MAX_VALUE;
    private Long min = 0L;
    private Long result = max / 2;

    public String generatePassword(Boolean bigger) {
        if (bigger) {
            min = result;
            result = (min + max) / 2;
        } else {
            max = result;
            result = result / 2;
        }
        return Long.toHexString(result);
    }

    public void reset() {
        max = Long.MAX_VALUE;
        min = 0L;
        result = max / 2;
    }
}
