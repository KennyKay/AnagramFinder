import java.util.SortedMap;

/**
 * Represents a Vocabulary.
 */
public class Vocabulary
		extends TrieTree<String, Character> {

	//region Fields

	private final TrieNode root = new TrieNode(Character.MIN_VALUE);

	//endregion


	//region Methods


	/**
	 * Adds a {@code word} to the {@link Vocabulary}.
	 * <p>
	 * The order of this function algorithm is O(n) who n is the length of the
	 * {@code word}.
	 *
	 * @param word The {@link String} representation of the {@code word}.
	 */
	@Override
	public void add(String word) {

		SortedMap<Character, TrieNode> children = this.root.children;

		TrieNode node;

		// Initializes variables.
		final int length = word.length();
		int i = 0;

		// Iterates throw the letter of the word.
		do {
			Character letter = word.charAt(i);

			// If there already has a node for this letter, get it!
			// Otherwise, create it and add it to the children.
			if (children.containsKey(letter)) {
				node = children.get(letter);

			} else {
				node = new TrieNode(letter);
				children.put(letter, node);
			}

			children = node.children;
		} while (++i < length);

		// Mark the last letter node as a leaf.
		node.isLeaf = true;
	}


	/**
	 * Returns whether the {@code prefix} is contain in the {@link Vocabulary}.
	 *
	 * @param prefix The {@link String} representation of the {@code prefix}.
	 *
	 * @return {@code true} if presents in the {@link Vocabulary}, {@code
	 * false}, otherwise.
	 *
	 * @see #getNode(String)
	 */
	@Override
	public boolean isPrefix(String prefix) {

		return getNode(prefix) != null;
	}


	/**
	 * Returns whether the {@code word} is contain in the {@link Vocabulary}.
	 *
	 * @param word The {@link String} representation of the {@code word}.
	 *
	 * @return {@code true} if presents in the {@link Vocabulary}, {@code
	 * false}, otherwise.
	 *
	 * @see #getNode(String)
	 */
	@Override
	public boolean contains(String word) {

		final TrieNode node = getNode(word);

		return node != null && node.isLeaf;
	}

	//endregion


	//region Helpers


	/**
	 * Returns the {@link TrieTree.TrieNode} of the {@code prefix}.
	 * <p>
	 * The order of this function algorithm is O(n) who n is the length of the
	 * {@code prefix}.
	 *
	 * @param prefix The {@link String} representation of the {@code prefix}.
	 *
	 * @return The {@link TrieTree.TrieNode}.
	 */
	private TrieNode getNode(String prefix) {

		TrieNode node = this.root;

		final int length = prefix.length();
		for (int i = 0; i < length; i++) {

			final Character letter = prefix.charAt(i);

			// If this letter doesn't have a node,
			// the word doesn't exists.
			if (!node.children.containsKey(letter)) {
				return null;
			}

			node = node.children.get(letter);
		}

		return node;
	}

	//endregion
}
