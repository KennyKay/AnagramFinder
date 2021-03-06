import java.util.*;
import java.util.stream.IntStream;

public class AnagramFinderSort extends AnagramFinder {



    @Override
    public void findAnagrams(String[] words, String[] dict, int[] result) {

        int wordsLength = words.length;
        int dictionaryWordsLength = dict.length;

        char[][] sortedWords = new char[wordsLength][];
        char[][] sortedDict = new char[dictionaryWordsLength][];


        IntStream.range(0, wordsLength).parallel().forEach(i -> {
            int wordAnagramCount = 0;

            for (int j = 0; j < dictionaryWordsLength; j++){
                if(words[i].length() != dict[j].length())
                    continue;

                if(sortedWords[i] == null){
                    char[] temp = words[i].toCharArray();
                    Arrays.sort(temp);
                    sortedWords[i] = temp;
                }

                if(sortedDict[j] == null){
                    char[] temp = dict[j].toCharArray();
                    Arrays.sort(temp);
                    sortedDict[j] = temp;
                }

                if (isAnagram(sortedWords[i], sortedDict[j]))
                    wordAnagramCount++;
            }

            result[i] = wordAnagramCount;
        });
    }

    private static boolean isAnagram(char[] word, char[] anagram) {
        for(int i = word.length - 1; i >= 0; i--)
            if(word[i] != anagram[i])
                return false;

        return true;
    }
}
