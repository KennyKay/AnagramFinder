using System;
using System.Linq;

namespace AnagramFinder
{
    public class AnagramFinderSort : IAnagramFinder {

        public void FindAnagrams(string[] words, string[] dict, int[] result) {

            int wordsLength = words.Length;
            int dictionaryWordsLength = dict.Length;

            char[][] sortedWords = new char[wordsLength][];
            char[][] sortedDict = new char[dictionaryWordsLength][];


            Enumerable.Range(0, wordsLength).AsParallel().ForAll(i => {
                int wordAnagramCount = 0;

                for (int j = 0; j < dictionaryWordsLength; j++){
                    if(words[i].Length != dict[j].Length)
                        continue;

                    if(sortedWords[i] == null){
                        char[] temp = words[i].ToCharArray();
                        Array.Sort(temp);
                        sortedWords[i] = temp;
                    }

                    if(sortedDict[j] == null){
                        char[] temp = dict[j].ToCharArray();
                        Array.Sort(temp);
                        sortedDict[j] = temp;
                    }

                    if (isAnagram(sortedWords[i], sortedDict[j]))
                        wordAnagramCount++;
                }

                result[i] = wordAnagramCount;
            });
        }

        private static bool isAnagram(char[] word, char[] anagram) {
            for(int i = word.Length - 1; i >= 0; i--)
                if(word[i] != anagram[i])
                    return false;

            return true;
        }
    }
}
