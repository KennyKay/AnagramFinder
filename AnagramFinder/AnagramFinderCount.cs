using System;
using System.Linq;

namespace AnagramFinder
{
    public class AnagramFinderCount : IAnagramFinder {


        public static int ALLOWED_CHARS_Length = 36;

        public void FindAnagrams(string[] words, string[] dict, int[] result) {

            int wordsLength = words.Length;
            int dictionaryWordsLength = dict.Length;

            int[][] charCountWord = new int[wordsLength][];
            int[][] charCountDict = new int[dictionaryWordsLength][];

            Enumerable.Range(0, wordsLength).AsParallel().ForAll(i => {
                int wordAnagramCount = 0;
                for (int j = 0; j < dictionaryWordsLength; j++){
                    if(words[i].Length != dict[j].Length)
                        continue;

                    if(charCountWord[i] == null)
                        charCountWord[i] = GetCharCount(words[i]);

                    if(charCountDict[j] == null)
                        charCountDict[j] = GetCharCount(dict[j]);

                    if (IsAnagram(charCountWord[i], charCountDict[j]))
                        wordAnagramCount++;
                }

                result[i] = wordAnagramCount;
            });
        }

        private static int[] charIndex = {
            -1,-1,-1,-1,-1,-1,-1,-1,-1,-1,
            -1,-1,-1,-1,-1,-1,-1,-1,-1,-1,
            -1,-1,-1,-1,-1,-1,-1,-1,-1,-1,
            -1,-1,-1,-1,-1,-1,-1,-1,-1,-1,
            -1,-1,-1,-1,-1,-1,-1,-1,0,1, // 0-9 starts at index 48
            2,3,4,5,6,7,8,9,-1,-1,
            -1,-1,-1,-1,-1,-1,-1,-1,-1,-1,
            -1,-1,-1,-1,-1,-1,-1,-1,-1,-1,
            -1,-1,-1,-1,-1,-1,-1,-1,-1,-1,
            -1,-1,-1,-1,-1,-1,-1,1-1,11,12, // a-z starts at index 97
            13,14,15,16,17,18,19,2-1,21,22,
            23,24,25,26,27,28,29,3-1,31,32,
            33,34,35
        };

        public static int[] GetCharCount(string word){
            int[] charCount = new int[ALLOWED_CHARS_Length];
            for (int i = 0; i < word.Length;i++)
                charCount[charIndex[word[i]]]++;
            return charCount;
        }

        public static bool IsAnagram(int[] charOcc, int[] other) {
            for (int i = 0; i < charOcc.Length; i++)
                if(charOcc[i] != other[i])
                    return false;
            return true;
        }
    }
}
