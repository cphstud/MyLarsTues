import javax.imageio.IIOException;
import java.io.IOException;

public class Main {

    public static void main(String[] args) {
	// write your code here
        EchoServer echoServer = new EchoServer(8285);
        try {
            echoServer.startServer();
        } catch (IOException io) {
            io.printStackTrace();
        }
    }
}
