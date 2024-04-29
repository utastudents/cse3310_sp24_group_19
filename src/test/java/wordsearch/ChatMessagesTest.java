import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class ChatMessagesTest {

    @Test
    public void testChatMessages() {
        
        String lobbyUUID = "1234";
        String playerUUID = "5678";
        String message = "Hello John";
        
        ChatMessages chatMessage = new ChatMessages(lobbyUUID, playerUUID, message);
        
        assertEquals(lobbyUUID, chatMessage.getLobbyUUID());
        assertEquals(playerUUID, chatMessage.getPlayerUUID());
        assertEquals(message, chatMessage.getMessage());
    }
}
