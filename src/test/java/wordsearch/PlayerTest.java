import org.java_websocket.WebSocket;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class PlayerTest {

    private PlayerTest player;
    private WebSocket connection;

  
    public void setUp() {
        player = new PlayerTest("uuid123", connection);
    }

    @Test
    public void testGetUUID() {
        assertEquals("uuid123", player.getUUID());
    }

    @Test
    public void testGetNick() {
        assertNull(player.getNick());

        player.setNick("testNick");
        assertEquals("testNick", player.getNick());
    }

    @Test
    public void testGetPlayerGameMode() {
        assertNull(player.getPlayerGameMode());

        player.setGameMode(GameModeTest.DUOS);
        assertEquals(GameModeTest.DUOS, player.getPlayerGameMode());
    }
}
