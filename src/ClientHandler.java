import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class ClientHandler {
    Socket client;
    PrintWriter pw;
    Scanner sc;

    public ClientHandler(Socket client) throws IOException {
        this.client=client;
        this.pw = new PrintWriter(client.getOutputStream(),true);
        this.sc = new Scanner(client.getInputStream());
    }

    public void protocol() {
        String msg = sc.nextLine();
        pw.println(msg + " hej fra server");
    }
}
