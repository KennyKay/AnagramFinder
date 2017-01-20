import java.util.*;

/**
 *
 */
public class AFBase extends AnagramFinder {

	public AFBase(PerformanceMeter meter) {

		super(meter);
	}


	@Override
	public int[] findAnagrams(String[] words, String[] dict) {

        int[] result = new int[words.length];

		this.meter.start();
        for (int i = 0; i < words.length; i++) {
            String w = words[i];

            int count = 0;

            for (String a : dict) {
                String word = w.replaceAll(" ", "");
                String anagram = a.replaceAll(" ", "");

                if (word.length() == anagram.length()
                        && isAnagram(word.toCharArray(), anagram.toCharArray())) {
                    count++;
                }
                result[i] = count;
            }
        }
		this.meter.stop();

		return result;
	}


	private boolean isAnagram(char[] word, char[] anagram) {

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
