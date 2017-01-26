import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class WordReader {
    public String[] readWords(String path){
        List<String> words = new ArrayList<>();
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(path));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try {

            String line = br.readLine();

            while (line != null) {
                words.add(removeSpaces(line));
                line = br.readLine();
            }

            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return words.toArray(new String[words.size()]);
    }
    private String removeSpaces(String word) {
        return word.replace(" ", "");
    }

}
