package wordsearch;

import java.util.Random;
import java.util.ArrayList;
import java.util.List;

public class GridGenerator {
    private int GRID_SIZE = 20;
    private char EMPTY_CELL = '-';
    private char[][] grid = new char[GRID_SIZE][GRID_SIZE];
    private List<String> wordLocations;

    public GridGenerator(WordBank wordBank) {
        this.wordLocations = new ArrayList<>();
        initializeGrid();
        fillWords(wordBank); // add parameter that gets wordbank from game and feels it in
        fillRemaining();
        printGrid();

    }

    public void initializeGrid() {
        for (int i = 0; i < GRID_SIZE; i++) {
            for (int j = 0; j < GRID_SIZE; j++) {
                grid[i][j] = EMPTY_CELL;
            }
        }
    }

    public void fillWords(WordBank wordBank) {
        Random random = new Random();
        List<String> wordBankList = wordBank.getWordBank();
        int count = 0;
        for (String word : wordBankList) {
            count++;
            boolean placed = false;
            while (!placed) {
                int direction = random.nextInt(8); // Random direction
                int row = random.nextInt(GRID_SIZE);
                int col = random.nextInt(GRID_SIZE);
                if (canPlaceWord(word, row, col, direction)) {
                    placeWord(word, row, col, direction);
                    placed = true;
                    // System.out.println("Word: " + word + " starts at (" + row + ", " + col + ")
                    // and ends at ("
                    // + (row + (word.length() - 1) * getDeltaRow(direction)) + ", "
                    // + (col + (word.length() - 1) * getDeltaCol(direction)) + ")");
                    String wordLocation = count + "=" + row + "=" + col + "="
                            + (row + (word.length() - 1) * getDeltaRow(direction)) + "="
                            + (col + (word.length() - 1) * getDeltaCol(direction));
                    this.wordLocations.add(wordLocation);
                }
            }
        }
    }

    public List<String> getWordLocations() {
        return this.wordLocations;
    }

    public int getDeltaRow(int direction) {
        switch (direction) {
            case 0:
            case 4:
            case 5:
                return -1;
            case 1:
            case 6:
            case 7:
                return 1;
            default:
                return 0;
        }
    }

    public int getDeltaCol(int direction) {
        switch (direction) {
            case 2:
            case 4:
            case 6:
                return -1;
            case 3:
            case 5:
            case 7:
                return 1;
            default:
                return 0;
        }
    }

    public boolean canPlaceWord(String word, int row, int col, int direction) {
        int len = word.length();
        int dr = 0, dc = 0;
        switch (direction) {
            case 0:
                dr = -1;
                dc = 0; // Up
                break;
            case 1:
                dr = 1;
                dc = 0; // Down
                break;
            case 2:
                dr = 0;
                dc = -1; // Left
                break;
            case 3:
                dr = 0;
                dc = 1; // Right
                break;
            case 4:
                dr = -1;
                dc = -1; // Up-Left
                break;
            case 5:
                dr = -1;
                dc = 1; // Up-Right
                break;
            case 6:
                dr = 1;
                dc = -1; // Down-Left
                break;
            case 7:
                dr = 1;
                dc = 1; // Down-Right
                break;
        }
        for (int i = 0; i < len; i++) {
            int r = row + i * dr;
            int c = col + i * dc;
            if (r < 0 || r >= GRID_SIZE || c < 0 || c >= GRID_SIZE
                    || (grid[r][c] != EMPTY_CELL && grid[r][c] != word.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    public void placeWord(String word, int row, int col, int direction) {
        int len = word.length();
        int dr = 0, dc = 0;
        switch (direction) {
            case 0:
                dr = -1;
                dc = 0; // Up
                break;
            case 1:
                dr = 1;
                dc = 0; // Down
                break;
            case 2:
                dr = 0;
                dc = -1; // Left
                break;
            case 3:
                dr = 0;
                dc = 1; // Right
                break;
            case 4:
                dr = -1;
                dc = -1; // Up-Left
                break;
            case 5:
                dr = -1;
                dc = 1; // Up-Right
                break;
            case 6:
                dr = 1;
                dc = -1; // Down-Left
                break;
            case 7:
                dr = 1;
                dc = 1; // Down-Right
                break;
        }
        for (int i = 0; i < len; i++) {
            grid[row + i * dr][col + i * dc] = word.charAt(i);
        }
    }

    public void fillRemaining() {
        Random random = new Random();
        for (int i = 0; i < GRID_SIZE; i++) {
            for (int j = 0; j < GRID_SIZE; j++) {
                if (grid[i][j] == EMPTY_CELL) {
                    grid[i][j] = (char) (random.nextInt(26) + 'a');
                }
            }
        }
    }

    public void printGrid() {
        // for (int i = 0; i < GRID_SIZE; i++) {
        // for (int j = 0; j < GRID_SIZE; j++) {
        // System.out.print(grid[i][j] + " ");
        // }
        // System.out.println();
        // }
    }

    public String getGridCharacters() {
        String gridString = "";
        for (int i = 0; i < GRID_SIZE; i++) {
            for (int j = 0; j < GRID_SIZE; j++) {
                if (i == GRID_SIZE - 1 && j == GRID_SIZE - 1) {
                    gridString += grid[i][j];
                } else {
                    gridString += grid[i][j] + ",";
                }
            }
        }
        // System.out.println(gridString);
        return gridString;
    }
}
