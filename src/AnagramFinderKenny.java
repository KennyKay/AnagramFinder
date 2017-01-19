import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class AnagramFinderKenny extends AnagramFinder {


    public AnagramFinderKenny(PerformanceMeter meter) {
        super(meter);
    }

    @Override
    public Map<String, Integer> findAnagrams(String[] words, String[] dict) {
        Map<String, Integer> result = new HashMap<>();
        this.meter.start();
        int wordsLength = words.length;
        int dictionaryWordsLength = dict.length;

        char[][] sortedWords = new char[wordsLength][];
        char[][] sortedDict = new char[dictionaryWordsLength][];

        int wordAnagramCount;
        for (int i = 0; i < wordsLength; i++) {
            wordAnagramCount = 0;
            for (int j = 0; j < dictionaryWordsLength; j++){
                if(words[i].length() != dict[j].length())
                    continue;

                if(sortedWords[i] == null){
                    sortedWords[i] = words[i].toCharArray();
                    Arrays.sort(sortedWords[i]);
                }

                if(sortedDict[j] == null){
                    sortedDict[j] = dict[j].toCharArray();
                    Arrays.sort(sortedDict[j]);
                }

                if (isAnagram(sortedWords[i], sortedDict[j]))
                    wordAnagramCount++;
            }

            result.put(words[i], wordAnagramCount);
        }

        this.meter.stop();
        return result;
    }



    private boolean isAnagram(char[] word, char[] anagram) {
        for(int i = word.length - 1; i >= 0; i--)
            if(word[i] != anagram[i])
                return false;

        return true;
    }
}
