package ru.practicum.contest;

import org.junit.Test;
import ru.practicum.contest.service.CaesarDecoder;

import static org.junit.Assert.assertEquals;

public class CaesarDecoderTest {
    CaesarDecoder caesarDecoder = new CaesarDecoder();

    @Test
    public void decipherTest_Normal() {
        String original = "I AM PROGRAMMER";
        String encrypted = "L DP SURJUDPPHU";
        int offset = 3;

        String result = caesarDecoder.decipher(encrypted, offset);

        assertEquals(original, result);
    }

}
