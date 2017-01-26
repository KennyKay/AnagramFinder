using System;

namespace AnagramFinder
{
    public class AnagramFinderBase : IAnagramFinder {

        public void FindAnagrams(string[] words, string[] dict, int[] result) {

            for (int i = 0; i < words.Length; i++) {
                string w = words[i];

                foreach (string a in dict) {

                    if (w.Length == a.Length && isAnagram(w.ToCharArray(), a.ToCharArray()))
                        result[i]++;
                }
            }
        }


        private bool isAnagram(char[] word, char[] anagram) {

            bool found;
            foreach (char w in word) {

                found = false;
                for (int j = 0; j < anagram.Length && !found; j++) {
                    if (w == anagram[j]) {
                        anagram[j] = ' ';
                        found = true;
                    }
                }
                if (!found)
                    return false;
            }

            foreach (char c in anagram)
                if (c != ' ') return false;

            return true;
        }
    }
}
