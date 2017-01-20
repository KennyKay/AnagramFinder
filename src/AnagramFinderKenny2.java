import java.util.HashMap;
import java.util.Map;
import java.util.stream.IntStream;

public class AnagramFinderKenny2 extends AnagramFinder {

	public AnagramFinderKenny2(PerformanceMeter meter) {

		super(meter);
	}


	@Override
	public Map<String, Integer> findAnagrams(String[] words, String[] dict) {

		Map<String, Integer> result = new HashMap<>();
		this.meter.start();

		int wordsLength = words.length;
		int dictionaryWordsLength = dict.length;

		Map<Character, Integer>[] sortedWords = new Map[wordsLength];
		Map<Character, Integer>[] sortedDict = new Map[dictionaryWordsLength];

		IntStream.range(0, wordsLength).parallel().forEach(i -> {
			int wordAnagramCount = 0;
			for (int j = 0; j < dictionaryWordsLength; j++){
                if(words[i].length() != dict[j].length())
                    continue;

                if(sortedWords[i] == null)
                	sortedWords[i] = getMapCharCount(words[i]);

                if(sortedDict[j] == null)
                    sortedDict[j] = getMapCharCount(dict[j]);

                if (isAnagram(sortedWords[i], sortedDict[j]))
                    wordAnagramCount++;
            }

			result.put(words[i], wordAnagramCount);
		});
		this.meter.stop();
		return result;
	}

	private Map<Character, Integer> getMapCharCount(String word){

		char[] chars = word.toCharArray();
		Map<Character, Integer> map = new HashMap<>(chars.length);
		for (char character : chars) {
			if (!map.containsKey(character))
				map.put(character, 1);
			else
				map.put(character, map.get(character) + 1);
		}
		return map;
	}

	private boolean isAnagram(Map<Character, Integer> word, Map<Character, Integer> other) {
        for (char c : word.keySet()) {
            if (!other.containsKey(c))
                return false;
            else if(word.get(c) != other.get(c))
                return false;
        }
        return true;
    }
}
