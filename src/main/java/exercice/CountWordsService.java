package exercice;

import java.util.HashMap;
import java.util.Map;

public class CountWordsService {

    private final Map<String, Integer> words = new HashMap<>();

    //TODO keep hyphen and underscore?
    protected void processLine(String line) {
        //remove non Alphabetical chars and put all to lowercase
        String alphabetic = line.replaceAll("[^A-Za-z0-9 ]", "").toLowerCase();
        for (String word : alphabetic.split(" ")) {
            words.put(word, words.getOrDefault(word, 0) + 1);
        }
    }






    public Integer getWordCount(String word) {
        return words.getOrDefault(word, 0);
    }
}
