import java.util.Dictionary;
import java.util.HashMap;
import java.util.Map;

/**
 *
 */
public class AFBase extends AnagramFinder {

	public AFBase(PerformanceMeter meter) {

		super(meter);
	}


	@Override
	public Map<String, Integer> findAnagrams(String[] words, String[] dict) {

		Map<String, Integer> result = new HashMap<>();

		this.meter.start();
		for (String w : words) {

			int count = 0;

			for (String a : dict) {
				String word = w.replaceAll(" ", "");
				String anagram = a.replaceAll(" ", "");

				if (word.length() == anagram.length()
						&& isAnagram(word.toCharArray(), anagram.toCharArray())) {
					count++;
				}
				result.put(w, count);
			}
		}
		this.meter.stop();

		return result;
	}


	@Override
	protected boolean isAnagram(char[] word, char[] anagram) {

		boolean found;
		for (char w : word) {

			found = false;
			for (int j = 0; j < anagram.length && !found; j++) {
				if (w == anagram[j]) {
					anagram[j] = ' ';
					found = true;
				}
			}
			if (!found)
				return false;
		}

		for (char c : anagram)
			if (c != ' ') return false;

		return true;
	}
}
