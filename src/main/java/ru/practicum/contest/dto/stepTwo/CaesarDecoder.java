package ru.practicum.contest.dto.stepTwo;

public class CaesarDecoder {
    private static final int ALPHABET_SIZE = 26;

    public String decodeCaesar(String message, int offset) {
        StringBuilder result = new StringBuilder();

        for (char character : message.toCharArray()) {
            if (Character.isLetter(character)) {
                char letterA = Character.isLowerCase(character) ? 'a' : 'A';
                int originalAlphabetPosition = character - letterA;
                int newAlphabetPosition = (originalAlphabetPosition + offset) % ALPHABET_SIZE;
                char newCharacter = (char) (letterA + newAlphabetPosition);
                result.append(newCharacter);
            }
            else {
                result.append(character);
            }
        }
        return result.toString();
    }

    public String decipher(String message, int offset) {
        return decodeCaesar(message, ALPHABET_SIZE - (offset % ALPHABET_SIZE));
    }
}
