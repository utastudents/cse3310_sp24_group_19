import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class GameModeTest {

    @Test
    public void testGameModeEnumValues() {
        
        assertEquals("DUOS", GameMode.DUOS.name());
        
        assertEquals("SQUADS", GameMode.SQUADS.name());
    }
}
