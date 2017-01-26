
/**
 *
 */
public class AnagramFinderBase extends AnagramFinder {

	@Override
	public void findAnagrams(String[] words, String[] dict, int[] result) {

        for (int i = 0; i < words.length; i++) {
            String w = words[i];

            for (String a : dict) {

				if (w.length() == a.length() && isAnagram(w.toCharArray(), a.toCharArray()))
					result[i]++;
            }
        }
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
