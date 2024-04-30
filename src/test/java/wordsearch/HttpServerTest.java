import org.junit.Test;
import static org.junit.Assert.*;

public class HttpServerTest {

    @Test
    public void testStart() {
     
        int portNum = 8080;
        String dirName = "./html";

        HttpServer httpServer = new HttpServer(portNum, dirName);

        httpServer.start();
    }
}
