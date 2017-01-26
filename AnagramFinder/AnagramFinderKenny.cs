using System;
using System.Linq;

namespace AnagramFinder
{
    public class AnagramFinderKenny : IAnagramFinder {
        
        public void FindAnagrams(string[] words, string[] dict, int[] result) {

            int wordsLength = words.Length;
            int dictionaryWordsLength = dict.Length;

            var sortedWords = new int[wordsLength][];
            var sortedDict = new int[dictionaryWordsLength][];

            Enumerable.Range(0, wordsLength).AsParallel().ForAll(i =>
            {
                int wordAnagramCount = 0;

                for (int j = 0; j < dictionaryWordsLength; j++){
                    if(words[i].Length != dict[j].Length)
                        continue;

                    if(sortedWords[i] == null){
                        if(words[i].Length < 36){
                            var temp = toIntArray(words[i]);
                            Array.Sort(temp);
                            sortedWords[i] = temp;
                        }
                        else
                            sortedWords[i] = AnagramFinderCount.GetCharCount(words[i]);
                    }

                    if(sortedDict[j] == null)
                    {
                        if(dict[j].Length < 36){
                            var temp = toIntArray(dict[j]);
                            Array.Sort(temp);
                            sortedDict[j] = temp;
                        }
                        else
                            sortedDict[j] = AnagramFinderCount.GetCharCount(dict[j]);
                    }

                    if (AnagramFinderCount.IsAnagram(sortedWords[i], sortedDict[j]))
                        wordAnagramCount++;
                }

                result[i] = wordAnagramCount;
            });
        }

        private int[] toIntArray(string word) {
            int[] array = new int[word.Length];
            for (int i = 0; i < word.Length; i++)
                array[i] = word[i];
            return array;
        }
    }
}
