import java.util.List;
import java.util.Map;

public class AFApplication {

	private static final int TRIES = 10;
	private static final int TOP   = 10;


	public static void main(String[] args) {

		WordReader wr = new WordReader();
		String[] words = wr.readWords(args[0]);
		String[] dict = wr.readWords(args[1]);

		PerformanceMeter meter = new PerformanceMeter();
//				AnagramFinder anagramFinder = new AFBase(meter);
		//		AnagramFinder anagramFinder = new AFCountAndCompare(meter);
		//		AnagramFinder anagramFinder = new AnagramFinderKenny(meter);
		AnagramFinder anagramFinder = new AnagramFinderKenny(meter);

		Map<String, Integer> anagrams;

		for (int i = 0; i <= TRIES; i++) {
			anagramFinder.findAnagrams(words, dict);
			double time = meter.latest() / 1000000000D;
			System.out.printf("[%05d] %.5f seconds \n", i, time);
		}

		System.out.println();
		anagrams = anagramFinder.findAnagrams(words, dict);

		int total = 0;
		for (Map.Entry<String, Integer> anagram : anagrams.entrySet()) {
			String line = "Il y a " + anagram.getValue() + " anagrammes du mot " + anagram.getKey();
			System.out.println(line);

			total += anagram.getValue();
		}

		System.out.println("Il y a un total de " + total + " anagrammes");


		List<Long> top = meter.top(TOP);

		long sum = 0;
		for (int i = 0; i < TOP; i++) {
			sum += top.get(i);
		}

		double avgNano = (double) sum / TOP;
		double avgSec = avgNano / 1000000000D;

		System.out.println();
		System.out.printf("Moyenne des %d meilleurs essais sur %d \n",
		                  TOP, TRIES);
		System.out.printf("Temps d'execution : %f secondes \n", avgSec);
	}
}
