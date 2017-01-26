import java.util.Arrays;
import java.util.stream.IntStream;

public class AnagramFinderKenny extends AnagramFinder {



    @Override
    public void findAnagrams(String[] words, String[] dict, int[] result) {

        int wordsLength = words.length;
        int dictionaryWordsLength = dict.length;

        int[][] sortedWords = new int[wordsLength][];
        int[][] sortedDict = new int[dictionaryWordsLength][];


        IntStream.range(0, wordsLength).parallel().forEach(i -> {
            int wordAnagramCount = 0;

            for (int j = 0; j < dictionaryWordsLength; j++){
                if(words[i].length() != dict[j].length())
                    continue;

                if(sortedWords[i] == null){
                    if(words[i].length() < 36){
                        int[] temp = toIntArray(words[i]);
                        Arrays.sort(temp);
                        sortedWords[i] = temp;
                    }
                    else
                        sortedWords[i] = AnagramFinderCount.getCharCount(words[i]);
                }

                if(sortedDict[j] == null){
                    if(dict[j].length() < 36){
                        int[] temp = toIntArray(dict[j]);
                        Arrays.sort(temp);
                        sortedDict[j] = temp;
                    }
                    else
                        sortedDict[j] = AnagramFinderCount.getCharCount(dict[j]);
                }

                if (AnagramFinderCount.isAnagram(sortedWords[i], sortedDict[j]))
                    wordAnagramCount++;
            }

            result[i] = wordAnagramCount;
        });
    }

    private int[] toIntArray(String word) {
        int[] array = new int[word.length()];
        for (int i = 0; i < word.length(); i++)
            array[i] = Character.getNumericValue(word.charAt(i));
        return array;
    }
}
