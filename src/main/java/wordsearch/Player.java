package wordsearch;

import org.java_websocket.WebSocket;

public class Player {
    private String uuid;
    private String name;
    private String nick;
    private WebSocket connection;
    private int score;
    private PlayerColors.Color color;
    private GameMode gameMode;

    public Player(String uuid, WebSocket connection) {
        this.uuid = uuid;
        this.connection = connection;
    }

    public String getUUID() {
        return uuid;
    }

    public String getNick() {
        return nick;
    }

    public GameMode getPlayerGameMode() {
        return gameMode;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public void setGameMode(GameMode gameMode) {
        this.gameMode = gameMode;
    }

    public PlayerColors.Color getColor() {
        return color;
    }

    public void setPlayerColor(PlayerColors.Color playerColor) {
        this.color = playerColor;
    }
}
