package wordsearch;

import java.util.ArrayList;
import java.util.List;

public class GameServer {
    private List<Player> players;
    private List<Lobby> lobbies;

    public GameServer() {
        this.players = new ArrayList<>();
        this.lobbies = new ArrayList<>();
    }

    public void addPlayer(Player player) {
        players.add(player);
        System.out.println("player has joined");
    }

    public void addLobby(Lobby lobby) {
        lobbies.add(lobby);
        System.out.println("lobby has been created");
    }

    public List<Player> getPlayerList() {
        return players;
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

    public boolean isNickUnique(String nick) {
        for (Player user : this.players) {
            if (user.getNick() == null) {
            } else {
                if (user.getNick().equals(nick)) {
                    return false;
                }
            }
        }
        return true;
    }

    public Lobby findLobby() {
        if (this.lobbies.isEmpty()) {
            System.out.println("lobby empty");
            String uuid = UUIDGenerator.generateUUID();
            Lobby lobby = new Lobby(uuid);
            this.addLobby(lobby);
            return lobby;
        } else {
            System.out.println("lobbies=>" + this.lobbies);
            for (Lobby lobby : this.lobbies) {
                System.out.println("looping throgh lobbies");
                int playerCount = lobby.getPlayerCount();
                if (playerCount < 4) {
                    return lobby;
                }
            }
            // create lobby if all are full
            String uuid = UUIDGenerator.generateUUID();
            Lobby lobby = new Lobby(uuid);
            this.addLobby(lobby);
            return lobby;
        }
    }

    public Lobby getLobbyByUUID(String uuid) {
        for (Lobby lobby : this.lobbies) {
            String lobbyUUID = lobby.getLobbyUUID();
            if (lobbyUUID.equals(uuid)) {
                return lobby;
            }
        }
        return null;
    }

    public String getLeaderBoard() {
        String lb = LeaderBoard.generateLeaderBoard(players);
        return lb;
    }
}
