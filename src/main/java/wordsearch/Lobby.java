package wordsearch;

import java.util.ArrayList;
import java.util.List;

public class Lobby {
    private String lobbyUUID;
    private List<Player> players;
    private List<ChatMessages> chatMessages;

    public Lobby(String lobbyUUID) {
        this.lobbyUUID = lobbyUUID;
        this.players = new ArrayList<>();
        this.chatMessages = new ArrayList<>();
    }

    public void addPlayer(Player player) {
        if (!players.contains(player)) {
            players.add(player);
        } else {
        }
        System.out.println("player has joined lobby");
    }

    public void addChatMessage(ChatMessages message) {
        chatMessages.add(message);
        System.out.println("message has been sent");
    }

    public List<Player> getPlayerList() {
        return players;
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

    public String displayChatMessage(ChatMessages message) {
        Player player = getPlayerByUUID(message.getPlayerUUID());
        String playerNick = player.getNick();
        String rawMessage = message.getMessage();
        String chatLog = playerNick + ": " + rawMessage;
        return chatLog;
    }

    public void checkGameModeFull() {
        // loop through playerlist, get their gamemode
        int duosCount = 0;
        int squdsCount = 0;
        for (Player users : this.players) {
            if (users.getPlayerGameMode() == GameMode.DUOS) {
                duosCount++;
                if (duosCount == 2) {
                    System.out.println("GAME BOUT TO START");
                }
            }
        }
    }
}
