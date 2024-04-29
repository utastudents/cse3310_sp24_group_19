import org.junit.Test;
import static org.junit.Assert.assertNotNull;

public class UUIDGeneratorTest {

    @Test
    public void testGenerateUUID() {
       
        String uuid = UUIDGenerator.generateUUID();
      
        assertNotNull(uuid);
       
    }
}
