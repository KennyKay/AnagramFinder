import java.util.stream.IntStream;

public class AnagramFinderCount extends AnagramFinder {


    public static final int ALLOWED_CHARS_LENGTH = 36;

    @Override
	public void findAnagrams(String[] words, String[] dict, int[] result) {

		int wordsLength = words.length;
		int dictionaryWordsLength = dict.length;

		int[][] charCountWord = new int[wordsLength][];
        int[][] charCountDict = new int[dictionaryWordsLength][];

		IntStream.range(0, wordsLength).parallel().forEach(i -> {
			int wordAnagramCount = 0;
			for (int j = 0; j < dictionaryWordsLength; j++){
                if(words[i].length() != dict[j].length())
                    continue;

                if(charCountWord[i] == null)
                	charCountWord[i] = getCharCount(words[i]);

                if(charCountDict[j] == null)
                    charCountDict[j] = getCharCount(dict[j]);

                if (isAnagram(charCountWord[i], charCountDict[j]))
                    wordAnagramCount++;
            }

			result[i] = wordAnagramCount;
		});
	}

    private static int[] charIndex = new int[]{
            -1,-1,-1,-1,-1,-1,-1,-1,-1,-1,
            -1,-1,-1,-1,-1,-1,-1,-1,-1,-1,
            -1,-1,-1,-1,-1,-1,-1,-1,-1,-1,
            -1,-1,-1,-1,-1,-1,-1,-1,-1,-1,
            -1,-1,-1,-1,-1,-1,-1,-1,0,1, // 0-9 starts at index 48
            2,3,4,5,6,7,8,9,-1,-1,
            -1,-1,-1,-1,-1,-1,-1,-1,-1,-1,
            -1,-1,-1,-1,-1,-1,-1,-1,-1,-1,
            -1,-1,-1,-1,-1,-1,-1,-1,-1,-1,
            -1,-1,-1,-1,-1,-1,-1,1-1,11,12, // a-z starts at index 97
            13,14,15,16,17,18,19,2-1,21,22,
            23,24,25,26,27,28,29,3-1,31,32,
            33,34,35
    };

	public static int[] getCharCount(String word){
		int[] charCount = new int[ALLOWED_CHARS_LENGTH];
		for (int i = 0; i < word.length();i++)
            charCount[charIndex[word.charAt(i)]]++;
		return charCount;
	}

	public static boolean isAnagram(int[] charOcc, int[] other) {
	    for (int i = 0; i < charOcc.length; i++)
            if(charOcc[i] != other[i])
                return false;
        return true;
    }
}
