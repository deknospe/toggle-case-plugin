package io.github.deknospe.casetoolkit;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public final class CaseConverter {

    private CaseConverter() {
    }

    public static String convert(String input, CaseStyle style) {
        return switch (style) {
            case TOGGLE -> toggle(input);
            case UPPER -> input.toUpperCase(Locale.ROOT);
            case LOWER -> input.toLowerCase(Locale.ROOT);
            case TITLE -> joinWords(input, " ", true, false);
            case CAMEL -> joinWords(input, "", true, true);
            case PASCAL -> joinWords(input, "", true, false);
            case SNAKE -> joinWords(input, "_", false, false);
            case KEBAB -> joinWords(input, "-", false, false);
        };
    }

    private static String toggle(String input) {
        StringBuilder result = new StringBuilder(input.length());

        input.codePoints().forEach(codePoint -> {
            String symbol = new String(Character.toChars(codePoint));
            if (Character.isUpperCase(codePoint) || Character.isTitleCase(codePoint)) {
                result.append(symbol.toLowerCase(Locale.ROOT));
            } else if (Character.isLowerCase(codePoint)) {
                result.append(symbol.toUpperCase(Locale.ROOT));
            } else {
                result.append(symbol);
            }
        });

        return result.toString();
    }

    private static String joinWords(
            String input,
            String delimiter,
            boolean capitalizeWords,
            boolean lowercaseFirstWord
    ) {
        List<String> words = splitIntoWords(input);
        StringBuilder result = new StringBuilder(input.length());

        for (int i = 0; i < words.size(); ++i) {
            if (i > 0) {
                result.append(delimiter);
            }

            String word = words.get(i).toLowerCase(Locale.ROOT);
            if (capitalizeWords && !(lowercaseFirstWord && i == 0)) {
                word = capitalize(word);
            }
            result.append(word);
        }

        return result.toString();
    }

    private static List<String> splitIntoWords(String input) {
        int[] codePoints = input.codePoints().toArray();
        List<String> words = new ArrayList<>();
        StringBuilder currentWord = new StringBuilder();

        for (int i = 0; i < codePoints.length; ++i) {
            int current = codePoints[i];
            if (!Character.isLetterOrDigit(current)) {
                appendWord(words, currentWord);
                continue;
            }

            if (startsNewWord(codePoints, i, currentWord)) {
                appendWord(words, currentWord);
            }
            currentWord.appendCodePoint(current);
        }

        appendWord(words, currentWord);
        return words;
    }

    private static boolean startsNewWord(int[] codePoints, int index, StringBuilder currentWord) {
        if (currentWord.isEmpty() || index == 0) {
            return false;
        }

        int previous = codePoints[index - 1];
        int current = codePoints[index];
        boolean lowerToUpper = Character.isLowerCase(previous) && Character.isUpperCase(current);
        boolean acronymToWord = Character.isUpperCase(previous)
                && Character.isUpperCase(current)
                && index + 1 < codePoints.length
                && Character.isLowerCase(codePoints[index + 1]);

        return lowerToUpper || acronymToWord;
    }

    private static void appendWord(List<String> words, StringBuilder currentWord) {
        if (!currentWord.isEmpty()) {
            words.add(currentWord.toString());
            currentWord.setLength(0);
        }
    }

    private static String capitalize(String word) {
        if (word.isEmpty()) {
            return word;
        }

        int firstCodePoint = word.codePointAt(0);
        int firstCodePointLength = Character.charCount(firstCodePoint);
        String firstSymbol = word.substring(0, firstCodePointLength).toUpperCase(Locale.ROOT);
        return firstSymbol + word.substring(firstCodePointLength);
    }
}
