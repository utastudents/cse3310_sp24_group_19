import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class LobbyTest {

    private Lobby lobby;
    private Player player1;
    private Player player2;
    private ChatMessages chatMessage;

   
    public void setUp() {

        lobby = new Lobby("lobbyUUID");

        player1 = new Player("uuid1", null);
        player2 = new Player("uuid2", null);

        lobby.addPlayer(player1);
        lobby.addPlayer(player2);

        chatMessage = new ChatMessages("lobbyUUID", "uuid1", "Hello, world!");
    }

    @Test
    public void testAddPlayer() {
       
        assertEquals(2, lobby.getPlayerCount());
        lobby.addPlayer(player1);
        assertEquals(2, lobby.getPlayerCount());
    }

    @Test
    public void testAddChatMessage() {

        lobby.addChatMessage(chatMessage);
        assertEquals(1, lobby.getChatMessages().size());
    }

    @Test
    public void testGetPlayerByUUID() {

        assertEquals(player1, lobby.getPlayerByUUID("uuid1"));

        assertNull(lobby.getPlayerByUUID("nonExistentUUID"));
    }

    @Test
    public void testDisplayChatMessage() {
      
        String expectedChatLog = "Player1: Hello, world!";
        assertEquals(expectedChatLog, lobby.displayChatMessage(chatMessage));
    }

    @Test
    public void testCheckGameModeFull() {
        player1.setGameMode(GameMode.DUOS);
        lobby.addPlayer(player1);
      
        assertEquals(1, lobby.getPlayerCount());
    }
}
