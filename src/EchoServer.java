import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class EchoServer {
    private int port;

    public EchoServer(int port) {
        this.port=port;
    }

    public void startServer() throws IOException {
        // TODO: lav message-køen
        // TODO: lav listen til clienthandlers

        //TODO:instantiér dispatcheren med de delte ressourcer
        ServerSocket serverSocket = new ServerSocket(port);
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        while(true) {
            Socket client = serverSocket.accept();
            //TODO:lav cl med delt ressource
            ClientHandler cl = new ClientHandler(client);
            //TODO:put cl i listen
            //cl.start();
            executorService.execute(cl);
        }


    }
}
