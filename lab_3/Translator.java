package lab3;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class Translator {
    private final Map<String, String> dictionary;

    public Translator(String fileName) throws InvalidFileFormatException, FileReadException {
        List<String> lines;
        try {
            lines = Files.readAllLines(Paths.get(fileName).toAbsolutePath());
        } catch (IOException e) {
            throw new FileReadException(e);
        }
        Map<String, String> dict = new HashMap<>();
        for (int i = 0; i < lines.size(); ++i) {
            String line = lines.get(i).trim().toLowerCase();
            if (!line.matches("^([a-z'\\s\\p{Pd}]+)+\\|([а-я'\\s\\p{Pd}]+)+\\Z"))
                throw new InvalidFileFormatException((i + 1), line);

            String[] values = line.split("\\|");
            dict.put(values[0].trim().toLowerCase(), values[1].trim().toLowerCase());
        }
        dictionary = dict;
    }

    public String translate(String text) {
        text = text.trim().toLowerCase();
        StringBuilder res = new StringBuilder();

        String[] words = text.split("\\s");

        int i = 0;
        String word = "";
        while (i < words.length) {
            String translation = words[i];
            for (int j = words.length; j > i; --j) {
                word = String.join(" ", Arrays.copyOfRange(words, i, j));
                if (dictionary.containsKey(word)) {
                    translation = dictionary.get(word);
                    i = --j;
                    break;
                }
            }
            res.append(translation).append(" ");
            ++i;
        }
        return res.toString().trim();
    }

    public static class InvalidFileFormatException extends Exception {
        public InvalidFileFormatException(int lineN, String line) {
            super("Wrong file format in line " + lineN + " --> "+ line);
        }
    }

    public static class FileReadException extends IOException {
        public FileReadException(IOException e) {
            super(e.toString());
        }
    }
}
