import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class GameServerTest {

    private GameServerTest gameServer;
    private PlayerTest player1;
    private PlayerTest player2;

 
    public void setUp() {
        gameServer = new GameServerTest();
        player1 = new Player("uuid1", null);
        player2 = new Player("uuid2", null);
    }

  
    public void testAddPlayer() {
        gameServer.addPlayer(player1);
        assertEquals(1, gameServer.getPlayerList().size());
        assertEquals(player1, gameServer.getPlayerList().get(0));
    }


    public void testAddLobby() {
        Lobby lobby = new Lobby("lobbyUUID");
        gameServer.addLobby(lobby);
        assertEquals(1, gameServer.getLobbies().size());
        assertEquals(lobby, gameServer.getLobbies().get(0));
    }

  
    public void testGetPlayerByUUID() {
        gameServer.addPlayer(player1);
        gameServer.addPlayer(player2);
        assertEquals(player1, gameServer.getPlayerByUUID("uuid1"));
        assertEquals(player2, gameServer.getPlayerByUUID("uuid2"));
        assertNull(gameServer.getPlayerByUUID("nonExistentUUID"));
    }


    public void testIsNickUnique() {
        gameServer.addPlayer(player1);
        assertTrue(gameServer.isNickUnique("uniqueNick"));
        player1.setNick("uniqueNick");
        assertFalse(gameServer.isNickUnique("uniqueNick"));
    }


    public void testFindLobby() {
        assertNotNull(gameServer.findLobby());
        for (int i = 0; i < 4; i++) {
            Player player = new Player("uuid" + i, null);
            gameServer.addPlayer(player);
        }
        assertNotNull(gameServer.findLobby());
    }


    public void testGetLobbyByUUID() {
        Lobby lobby = new Lobby("lobbyUUID");
        gameServer.addLobby(lobby);
        assertEquals(lobby, gameServer.getLobbyByUUID("lobbyUUID"));
        assertNull(gameServer.getLobbyByUUID("nonExistentUUID"));
    }
}
