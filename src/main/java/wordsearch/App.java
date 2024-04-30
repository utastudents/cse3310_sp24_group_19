package wordsearch;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetSocketAddress;
import java.net.UnknownHostException;
import java.nio.ByteBuffer;
import java.util.Collections;
import java.util.Random;

import org.java_websocket.WebSocket;
import org.java_websocket.drafts.Draft;
import org.java_websocket.drafts.Draft_6455;
import org.java_websocket.handshake.ClientHandshake;
import org.java_websocket.server.WebSocketServer;
import java.util.Timer;
import java.util.TimerTask;
import java.util.UUID;
import java.util.Vector;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class App extends WebSocketServer {
    GameServer gameServer = new GameServer();

    public App(int port) {
        super(new InetSocketAddress(port));
    }

    public App(InetSocketAddress address) {
        super(address);
    }

    public App(int port, Draft_6455 draft) {
        super(new InetSocketAddress(port), Collections.<Draft>singletonList(draft));
    }

    @Override
    public void onOpen(WebSocket conn, ClientHandshake handshake) {
        System.out.println(conn.getRemoteSocketAddress().getAddress().getHostAddress() + " connected");

        String uuid = UUIDGenerator.generateUUID();
        Player player = new Player(uuid, conn);
        gameServer.addPlayer(player);
        broadcast(uuid);

        // WordBank wordBank = new WordBank();
        // List<String> wordBankList = wordBank.getWordBank();
        // for (String words : wordBankList) {
        // System.out.println("words= " + words);
        // }
    }

    public void updatePlayersInLobby(String lobbyUUID) {
        String onlinePlayerList = "";
        Lobby lobby = gameServer.getLobbyByUUID(lobbyUUID);
        System.out.println("=>" + lobby.getPlayerList());
        for (Player players : lobby.getPlayerList()) {
            System.out.println(players.getNick());
            onlinePlayerList += players.getNick() + "\n";
        }
        broadcast("PlayersOnline: " + onlinePlayerList);
    }

    @Override
    public void onClose(WebSocket conn, int code, String reason, boolean remote) {
        System.out.println(conn + " has closed");
    }

    @Override
    public void onMessage(WebSocket conn, String message) {
        System.out.println(conn + " = " + message);

        // nickname handling
        if (message.contains("nickname")) {
            String msgArray = message.replace("nickname", "");
            String[] nickNameArray = msgArray.split(",");
            Player player = gameServer.getPlayerByUUID(nickNameArray[0]);
            if (gameServer.isNickUnique(nickNameArray[1]) == false) {
                broadcast("Nickname:" + nickNameArray[0] + "," + "false");
            } else {
                player.setNick(nickNameArray[1]);
                broadcast("Nickname:" + nickNameArray[0] + "," + "true" + "," + nickNameArray[1]);
            }
        }

        // lobby handling
        if (message.contains("lobby")) {
            String uuid = message.replace("lobby", "");
            Player player = gameServer.getPlayerByUUID(uuid);
            Lobby lobby = gameServer.findLobby();
            lobby.addPlayer(player);
            System.out.println(player + " has been added to lobby:" + lobby.getLobbyUUID());
            broadcast("Lobby:" + lobby.getLobbyUUID() + "," + uuid);
            updatePlayersInLobby(lobby.getLobbyUUID());
        }

        // chat handling
        // FIX WE NEED TO LOOP THROUGH LIST EVERYTHING SAID use /n and just showcase
        // through chat
        // (ACTUALY NVM THIS *day later)
        if (message.contains("chat")) {
            String msgArray = message.replace("chat", "");
            String[] chatArray = msgArray.split(",");
            ChatMessages userMessage = new ChatMessages(chatArray[0], chatArray[1], chatArray[2]);
            Lobby lobby = gameServer.getLobbyByUUID(chatArray[0]);
            lobby.addChatMessage(userMessage);
            broadcast("Chat:" + lobby.getLobbyUUID() + "," + lobby.displayChatMessage(userMessage));
        }

        // gamemode handling
        if (message.contains("mode")) {
            String msgArray = message.replace("mode", "");
            String[] modeArray = msgArray.split(",");
            Player player = gameServer.getPlayerByUUID(modeArray[0]);
            Lobby lobby = gameServer.getLobbyByUUID(modeArray[1]);

            if (modeArray[2].equals("duos")) {
                player.setGameMode(GameMode.DUOS);
                lobby.addPlayerGameModeQueue(player);
            } else {
                player.setGameMode(GameMode.SQUADS);
            }

            // probably gonna have to make a queue system
            if (lobby.checkGameModeFull() == true) {
                System.out.println("game will start very soon...");
                Game game = lobby.getGameByPlayer(modeArray[0]);
                game.setGameState(GameState.PREPARE);
                broadcast("Game:" + "duos" + "," + game.getGameUUID() + "," + lobby.getLobbyUUID() + ","
                        + game.getPlayerList().get(0).getUUID() + "," + game.getPlayerList().get(1).getUUID());
            } else {
                System.out.println("game not starting");
            }
        }

        // start game handling
        if (message.contains("start")) {
            String msgArray = message.replace("start", "");
            String[] startArray = msgArray.split(",");
            System.out.println(startArray[2]);
            Lobby lobby = gameServer.getLobbyByUUID(startArray[2]);
            Game game = lobby.getGameByUUID(startArray[1]);

            if (game.getGameState() == GameState.PREPARE) {
                game.startGame();
                List<Player> playerList = game.getPlayerList();
                WordBank wordBank = game.getGameWordBank();
                List<String> wordBankList = wordBank.getWordBank();
                GridGenerator wordGrid = game.getWordGrid();
                String gridCharacters = wordGrid.getGridCharacters();

                for (Player players : playerList) {
                    System.out.println("COLORS=>" + players.getNick() + "=" + players.getColor().getHexCode());
                }
                // System.out.println("COLORS=>" + game.)

                // System.out.println("debug=> " + gridCharacters);
                // showing error for grid being null, but still running? JAVA HUH?
                // 99% its because 2 or more instances are running here so the server will
                // pass/skip the error if something else comes through
                // this might be a problem, but we will move along anyways LOL

                broadcast("Grid:" + playerList.get(0).getUUID() + "," +
                        playerList.get(1).getUUID() + "," + gridCharacters);
                String wordLocations = "";
                List<String> locations = wordGrid.getWordLocations();

                broadcast("GamePlayers:" + startArray[1] + "," + playerList.get(0).getNick() + ","
                        + playerList.get(1).getNick() + "," + playerList.get(0).getColor().getHexCode() + ","
                        + playerList.get(1).getColor().getHexCode());

                int index = 0;
                for (String word : locations) {
                    index++;
                    if (index == 12) {
                        wordLocations += word;
                    } else {
                        wordLocations += word + ",";
                    }

                }
                broadcast("Locations:" + startArray[1] + "," + wordLocations);
                // System.out.println(wordLocations);

                int count = 0;
                String wordBanks = "";
                for (String word : wordBankList) {
                    count++;
                    if (count == 12) {
                        wordBanks += word;
                    } else {
                        wordBanks += word + ",";
                    }
                    // System.out.println(count + ". bank=> " + word);

                }
                broadcast("WordBank:" + startArray[1] + "," + wordBanks);
            }
        }
    }

    @Override
    public void onMessage(WebSocket conn, ByteBuffer message) {
        System.out.println(conn + ": " + message);
    }

    @Override
    public void onError(WebSocket conn, Exception ex) {
        ex.printStackTrace();
        if (conn != null) {
            // some errors like port binding failed may not be assignable to a specific
            // websocket
        }
    }

    @Override
    public void onStart() {
        System.out.println("Server started!");

        setConnectionLostTimeout(0);
    }

    public static void main(String[] args) {
        // Set up the http server
        int port = 9080;
        HttpServer H = new HttpServer(port, "./html");
        H.start();
        System.out.println("http Server started on port:" + port);

        // create and start the websocket server
        port = 9880;
        App A = new App(port);
        A.start();
        System.out.println("websocket Server started on port: " + port);
    }
}