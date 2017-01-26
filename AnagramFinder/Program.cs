using System;
using System.Diagnostics;
using System.Text;

namespace AnagramFinder
{
    static class Program
    {
        static void Main(string[] args)
        {
            var wr = new WordReader();
            var words = wr.readWords(args[0]);
            var dict = wr.readWords(args[1]);

            //int wordLength = 1000;
            //string[] words = GetRandomList(10, wordLength);
            //string[] dict = GetRandomList(10, wordLength);

            var anagramFinder = new AnagramFinderKenny();

            var result = new int[words.Length];
            var stopWatch = new Stopwatch();
            stopWatch.Start();
            anagramFinder.FindAnagrams(words, dict, result);
            stopWatch.Stop();

            Console.WriteLine();
            int total = 0;
            for (int i = 0; i < result.Length; i++)
            {
                var line = "Il y a " + result[i] + " anagrammes du mot " + words[i];
                Console.WriteLine(line);

                total += result[i];
            }


            var time = stopWatch.ElapsedMilliseconds / 1000;
            Console.WriteLine("Temps d'execution: {0} secondes", time);

            Console.WriteLine("Il y a un total de " + total + " anagrammes");
        }

        static readonly char[] AB = {'0','1','2','3','4','5','6','7','8','9','a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z'};
        static Random r = new Random();

        private static string[] GetRandomList(int arrayLength, int wordLength)
        {
            var array = new string[arrayLength];
            for (int i = 0; i < arrayLength; i++)
            {
                array[i] = randomstring(wordLength);
            }
            return array;
        }

        private static string randomstring(int Length)
        {
            var sb = new StringBuilder(Length);
            for (int i = 0; i < Length; i++)
                sb.Append(AB[r.Next(AB.Length)]);
            return sb.ToString();
        }
    }
}
