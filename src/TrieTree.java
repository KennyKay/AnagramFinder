import java.util.SortedMap;
import java.util.TreeMap;

/**
 *
 */
public abstract class TrieTree<Whole, Part> {

	protected class TrieNode {

		public final SortedMap<Part, TrieNode> children = new TreeMap<>();

		public final Part content;

		public boolean isLeaf = false;


		TrieNode(Part content) {

			this.content = content;
		}
	}


	public abstract void add(Whole content);

	public abstract boolean isPrefix(Whole prefix);

	public abstract boolean contains(Whole content);

}

