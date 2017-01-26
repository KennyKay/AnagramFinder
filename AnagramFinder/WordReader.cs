using System;
using System.Collections;
using System.Collections.Generic;
using System.IO;

namespace AnagramFinder
{
    public class WordReader {
        public string[] readWords(string path){
            var words = new List<string>();
            using (var sr = new StreamReader(path))
            {
                var line = sr.ReadLine();

                while (line != null)
                {
                    words.Add(removeSpaces(line));
                    line = sr.ReadLine();
                }
            }

               
            return words.ToArray();
        }

        private string removeSpaces(string word)
        {
            return word.Replace(" ", "");
        }
    }
}
