import java.util.Random;

public class App {


	public static void main(String[] args) {

		WordReader wr = new WordReader();
		String[] words = wr.readWords(args[0]);
		String[] dict = wr.readWords(args[1]);

        //int wordLength = 1000;
        //String[] words = getRandomList(10, wordLength);
		//String[] dict = getRandomList(10, wordLength);

		AnagramFinder anagramFinder = new AnagramFinderKenny();

        int[] result = new int[words.length];
        long startTime = System.nanoTime();
        anagramFinder.findAnagrams(words, dict, result);
        long endTime = System.nanoTime();

		System.out.println();
		int total = 0;
		for (int i = 0; i < result.length; i++) {
			String line = "Il y a " + result[i] + " anagrammes du mot " + words[i];
			System.out.println(line);

			total += result[i];
		}


        double time = (endTime - startTime) / 1000000000D;
        System.out.printf("Temps d'execution: %.5f secondes \n", time);

		System.out.println("Il y a un total de " + total + " anagrammes");
	}
    static final String AB = "0123456789abcdefghijklmnopqrstuvwxyz";
    static Random r = new Random();

    private static String[] getRandomList(int arrayLength, int wordLength) {
        String[] array = new String[arrayLength];
        for (int i = 0; i < arrayLength; i++){
            array[i] = randomString(wordLength);
        }
        return array;
    }

    private static String randomString(int length){
        StringBuilder sb = new StringBuilder(length);
        for(int i = 0; i < length; i++)
            sb.append(AB.charAt(r.nextInt(AB.length())));
        return sb.toString();
    }
}
