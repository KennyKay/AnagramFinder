namespace AnagramFinder
{
    public interface IAnagramFinder
    {
        void FindAnagrams(string[] words, string[] dict, int[] results);
    }
}
