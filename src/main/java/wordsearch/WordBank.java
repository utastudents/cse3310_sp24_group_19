package wordsearch;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class WordBank {
    private List<String> wordList;
    private List<String> wordBankList;

    public WordBank() {
        this.wordList = new ArrayList<>();
        this.wordBankList = new ArrayList<>();
        readWordListFile();
        generateWordBank();
    }

    public void readWordListFile() {
        String filePath = "word_list.txt";

        try {
            FileReader fileReader = new FileReader(filePath);
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            String line;
            while ((line = bufferedReader.readLine()) != null) {
                // System.out.println(line);
                this.wordList.add(line);
            }

            bufferedReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<String> getWordList() {
        return wordList;
    }

    public List<String> generateWordBank() {
        List<String> words = new ArrayList<>();
        Random random = new Random();

        while (words.size() < 12) {
            int randomIndex = random.nextInt(this.wordList.size());
            String word = this.wordList.get(randomIndex);
            if (!words.contains(word)) {
                words.add(word);
                //System.out.println("wordbank= " + word);
            }
        }
        this.wordBankList = words;
        return words;
    }

    public List<String> getWordBank() {
        return wordBankList;
    }
}
