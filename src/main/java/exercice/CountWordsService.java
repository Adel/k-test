package exercice;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

public class CountWordsService {

    private final Map<String, Integer> words = new HashMap<>();

    //FIXME what about multiple spaces?
    protected void processLine(String line) {
        //remove non Alphabetical chars and put all to lowercase
        String alphabetic = line.replaceAll("[^A-Za-z0-9\\-\\s+]", "").toLowerCase();
        for (String word : alphabetic.split(" ")) {
            words.put(word, words.getOrDefault(word, 0) + 1);
        }
    }

    public static Stream<String> readFile(String filename) {
        try {
            return Files.lines(Paths.get(filename));
        } catch (IOException e) {
            throw new RuntimeException("error reading filename " + filename, e);
        }
    }


    public void countWords(String inputFileName, String outputFileName) {
        try (Stream<String> stream = readFile(inputFileName);
             PrintWriter printWriter = getPrintWriter(outputFileName)) {
            stream.forEach(this::processLine);
            final Comparator<Map.Entry<String, Integer>> byValue =
                    Comparator.comparing(Map.Entry::getValue, Comparator.reverseOrder());
            final Comparator<Map.Entry<String, Integer>> byKey =
                    Comparator.comparing(Map.Entry::getKey);
            words.entrySet()
                    .stream()
                    .sorted(byValue.thenComparing(byKey))
                    .map(e -> new StringBuilder(e.getKey()).append("\t").append(e.getValue()).toString())
                    .forEach(printWriter::println);
            if (printWriter.checkError()) {
                throw new RuntimeException("unknown error, cannot write to file");
            }
        }
    }

    public static PrintWriter getPrintWriter(String filename) {
        try {
            return new PrintWriter(Files.newBufferedWriter(Paths.get(filename)));
        } catch (IOException e) {
            throw new RuntimeException("cannot write to file " + filename, e);
        }
    }


    public Integer getWordCount(String word) {
        return words.getOrDefault(word, 0);
    }

    public static void main(String[] args) {
        String input = "input.txt";
        String output = "output.txt";
        if (args.length == 1) {
            input = args[0];
        }
        CountWordsService service = new CountWordsService();
        service.countWords(input, output);
    }
}
