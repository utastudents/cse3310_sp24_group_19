import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class GameModeTest {

    @Test
    public void testGameModeEnumValues() {
        
        assertEquals("DUOS", GameModeTest.DUOS.name());
        
        assertEquals("SQUADS", GameModeTest.SQUADS.name());
    }
}
