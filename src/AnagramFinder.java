import java.util.Dictionary;
import java.util.Map;

/**
 * The {@link AnagramFinder} class contains methods to find anagram of a
 * list of words in a dictionary.
 * <p>
 * This class is abstract to allow us to implement the strategy pattern.
 *
 * @author Hugo Lapointe Di Giacomo
 * @since 1.8
 */
public abstract class AnagramFinder {

	protected final PerformanceMeter meter;


	/**
	 * Returns the {@link PerformanceMeter} use to calculate the execution
	 * time of the anagram finder algorithm.
	 *
	 * @return The {@link PerformanceMeter}.
	 */
	public final PerformanceMeter meter() {

		return this.meter;
	}


	public AnagramFinder(PerformanceMeter meter) {

		this.meter = meter;
	}


	/**
	 * Returns a {@link Dictionary} of the {@code words} and the number of
	 * anagram found in the {@code dict} for each word.
	 *
	 * @param words The list of words to check.
	 * @param dict  The list of words to find anagram.
	 *
	 * @return A {@link Map} of the {@code words} and the number of anagram
	 * found in the {@code dict} for each word.
	 */
	public abstract Map<String, Integer> findAnagrams(
			String[] words,
			String[] dict
	);


	/**
	 * Returns whether the {@code anagram} is an anagram of the word {@code
	 * word}.
	 *
	 * @param word    The word to check.
	 * @param anagram The anagram to confirm.
	 *
	 * @return {@code true} if the anagram is confirmed, {@code false},
	 * otherwise.
	 */
	protected abstract boolean isAnagram(
			char[] word,
			char[] anagram
	);
}