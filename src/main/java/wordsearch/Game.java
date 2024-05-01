package wordsearch;

import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;
import java.util.Collections;

public class Game {
    private String gameUUID;
    private String lobbyUUID;
    private List<Player> players;
    private GameState gameState;
    private WordBank wordBank;
    private GridGenerator wordGrid;

    public Game(String gameUUID, String lobbyUUID) {
        this.gameUUID = gameUUID;
        this.lobbyUUID = lobbyUUID;
        this.players = new ArrayList<>();
        this.wordBank = new WordBank();
        GridGenerator gridGenerator = new GridGenerator(this.wordBank);
        this.wordGrid = gridGenerator;
        this.setGameState(GameState.PREPARE);
    }

    public void addPlayer(Player player) {
        if (!players.contains(player)) {
            players.add(player);
        } else {
        }
        System.out.println("player has joined game");
    }

    public List<Player> getPlayerList() {
        return players;
    }

    public String getGameUUID() {
        return gameUUID;
    }

    public void startGame() {
        if (this.gameState == GameState.PREPARE) {
            this.setGameState(GameState.START);
            // gridGenerator.getGridCharacters();

        }
        if (this.gameState == GameState.START) {
            System.out.println("cant start game, game has already been started");
        }
    }

    public WordBank getGameWordBank() {
        return this.wordBank;
    }

    public void endGame() {
    }

    public void setGameState(GameState gameState) {
        this.gameState = gameState;
    }

    public GameState getGameState() {
        return this.gameState;
    }

    public GridGenerator getWordGrid() {
        return wordGrid;
    }

    public void setUniquePlayerColor() {
        // System.out.println("lmfaofoaof");
        // PlayerColors.Color[] allColors = PlayerColors.getAllColors();
        // List<PlayerColors.Color> colorList = Arrays.asList(allColors);
        // Collections.shuffle(colorList);
        // PlayerColors.Color[] shuffledColors = colorList.toArray(new
        // PlayerColors.Color[0]);

        // for (int i = 0; i < this.players.size(); i++) {
        // Player player = this.players.get(i);
        // PlayerColors.Color color = shuffledColors[i];
        // player.setPlayerColor(color);
        // System.out.println(player.getNick() + "=" + color);
        // }

    }

    public void addPlayerScore() {
    }
}
