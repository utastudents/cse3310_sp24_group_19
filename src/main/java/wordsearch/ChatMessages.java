package wordsearch;

public class ChatMessages {
    private String lobbyUUID;
    private String playerUUID;
    private String message;

    public ChatMessages(String lobbyUUID, String playerUUID, String message) {
        this.lobbyUUID = lobbyUUID;
        this.playerUUID = playerUUID;
        this.message = message;
    }

    public String getPlayerUUID() {
        return playerUUID;
    }

    public String getMessage() {
        return message;
    }
}