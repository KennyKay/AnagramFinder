import java.util.HashMap;
import java.util.Map;

/**
 *
 */
public class AFCountAndCompare extends AnagramFinder {

	private int[] left  = new int[256];
	private int[] right = new int[256];


	public AFCountAndCompare(PerformanceMeter meter) {

		super(meter);
	}


	@Override
	public Map<String, Integer> findAnagrams(String[] words, String[] dict) {

		Map<String, Integer> result = new HashMap<>();

		this.meter.start();
		for (String w : words) {

			int count = 0;

			for (String a : dict) {
				char[] word = w.replaceAll("\\s+", "").toCharArray();
				char[] anagram = a.replaceAll("\\s+", "").toCharArray();

				if (isAnagram(word, anagram)) count++;
				result.put(w, count);
			}
		}
		this.meter.stop();

		return result;
	}


	@Override
	protected boolean isAnagram(char[] word, char[] anagram) {

		reset();

		for (char w : word) this.left[((int) w) <= 256 ? ((int) w) : 0]++;
		for (char a : anagram) this.right[((int) a) <= 256 ? ((int) a) : 0]++;

		for (int i = 0; i < 256; i++)
			if (this.left[i] != this.right[i])
				return false;

		return true;
	}


	private void reset() {

		for (int i = 0; i < 256; i++)
			left[i] = right[i] = 0;
	}
}
