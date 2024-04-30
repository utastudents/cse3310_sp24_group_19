import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class LeaderboardTest {

    @Test
    public void testConstructorAndGetters() {
       
        Player player = new Player("John");


        LeaderBoard leaderBoard = new LeaderBoard(player, 1000, 1);

        assertEquals("John", leaderBoard.getPlayer().getName());
        assertEquals(1000, leaderBoard.getPlayerScore());
        assertEquals(1, leaderBoard.getRank());
    }
}
