package wordsearch;

import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;
import java.util.Collections;

public class Lobby {
    private String lobbyUUID;
    private List<Player> players;
    private List<Game> games;
    private List<Player> gameModeQueue;
    private List<ChatMessages> chatMessages;

    public Lobby(String lobbyUUID) {
        this.lobbyUUID = lobbyUUID;
        this.players = new ArrayList<>();
        this.games = new ArrayList<>();
        this.gameModeQueue = new ArrayList<>();
        this.chatMessages = new ArrayList<>();
    }

    public void addPlayer(Player player) {
        if (!players.contains(player)) {
            players.add(player);
        } else {
        }
        System.out.println("player has joined lobby");
    }

    public void addGame(Game game) {
        games.add(game);
        System.out.println("game has been created in this lobby");
    }

    public void addChatMessage(ChatMessages message) {
        chatMessages.add(message);
        System.out.println("message has been sent");
    }

    // gonna have to make seperate 1 for squads
    public void addPlayerGameModeQueue(Player player) {
        this.gameModeQueue.add(player);
        System.out.println("player has joined queue");
        System.out.println("=>    " + this.gameModeQueue);
    }

    public String returnGameModeQueue() {
        String queueList = "";
        for (Player users : this.gameModeQueue) {
            queueList += users + ",";
        }
        return queueList;
    }

    public List<Player> getPlayerList() {
        return players;
    }

    public List<Game> getGameList() {
        return games;
    }

    public int getPlayerCount() {
        int playerCount = this.players.size();
        return playerCount;
    }

    public String getLobbyUUID() {
        return lobbyUUID;
    }

    public Player getPlayerByUUID(String uuid) {
        for (Player user : this.players) {
            String playerUUID = user.getUUID();
            if (playerUUID.equals(uuid)) {
                return user;
            }
        }
        return null;
    }

    public Game getGameByPlayer(String uuid) {
        for (Game game : this.games) {
            System.out.println(this.games);
            List<Player> playerList = game.getPlayerList();
            for (Player user : playerList) {
                String playerUUID = user.getUUID();
                if (playerUUID.equals(uuid)) {
                    return game;
                }
            }
        }
        return null;
    }

    public Game getGameByUUID(String uuid) {
        for (Game game : this.games) {
            String gameUUID = game.getGameUUID();
            if (gameUUID.equals(uuid)) {
                return game;
            }
        }
        return null;
    }

    public String displayChatMessage(ChatMessages message) {
        Player player = getPlayerByUUID(message.getPlayerUUID());
        String playerNick = player.getNick();
        String rawMessage = message.getMessage();
        String chatLog = playerNick + ": " + rawMessage;
        return chatLog;
    }

    public boolean checkGameModeFull() {
        
        // loop through playerlist, get their gamemode
        int duosCount = 0;
        int squdsCount = 0;
        System.out.println(this.gameModeQueue);
        // if lobby gamemode has 2 players in it

        // THIS IS THE ISSUE LMFAO
        int max = 2;
        if (this.gameModeQueue.size() == max) {
            System.out.println("GAME BOUT TO START");
            Player player_1 = this.gameModeQueue.get(0);
            Player player_2 = this.gameModeQueue.get(1);
            Game game = new Game(UUIDGenerator.generateUUID(), this.getLobbyUUID());
            this.addGame(game);
            game.addPlayer(player_1);
            game.addPlayer(player_2);

            // REMOVE PLAYERS FROM GAMEMODE SO NEW CAN BE CREATED
            this.gameModeQueue.remove(player_1);
            this.gameModeQueue.remove(player_2);

            // setting colors
            List<Player> gamePlayerList = game.getPlayerList();
            PlayerColors.Color[] allColors = PlayerColors.getAllColors();
            List<PlayerColors.Color> colorList = Arrays.asList(allColors);
            Collections.shuffle(colorList);
            PlayerColors.Color[] shuffledColors = colorList.toArray(new PlayerColors.Color[0]);

            for (int i = 0; i < gamePlayerList.size(); i++) {
                Player player = gamePlayerList.get(i);
                PlayerColors.Color color = shuffledColors[i];
                player.setPlayerColor(color);
                // System.out.println(player.getNick() + "=" + color);
            }
            return true;
        }
        return false;
    }
}
