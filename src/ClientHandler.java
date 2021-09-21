import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Map;
import java.util.Scanner;

public class ClientHandler implements Runnable{
    Socket client;
    PrintWriter pw;
    Scanner sc;
    Map<String,String> dkUk;

    public ClientHandler(Socket client) throws IOException {
        this.client=client;
        this.pw = new PrintWriter(client.getOutputStream(),true);
        this.sc = new Scanner(client.getInputStream());
    }
    public ClientHandler(Socket client, Map<String, String> dkUk) throws IOException {
        this.client=client;
        this.pw = new PrintWriter(client.getOutputStream(),true);
        this.sc = new Scanner(client.getInputStream());
        this.dkUk = dkUk;
    }

    public void protocol() throws IOException {
        String msg="";
        String dataString="";
        while(!msg.equals("CLOSE#")) {
            msg = sc.nextLine();
            //TODO: split strengen på #
            //TODO: switche på første del og process anden del (data)
            String[] msgArr = msg.split("#");
            String action = msgArr[0];
            if (msgArr.length > 1) {
                dataString = msgArr[1];
            }
            switch (action) {
                case "UPPER":pw.println(dataString.toUpperCase());break;
                case "LOWER":pw.println(dataString.toLowerCase());break;
                case "REVERSE":pw.println(doReverse(dataString));break;
                case "TRANSLATE":pw.println(doTranslate(dataString));break;
                default:msg="CLOSE#";
            }
        }
        client.close();


        pw.println(msg + " hej fra server");
    }

    private String doTranslate(String dataString) {
        String retVal="Not found";
        if(dkUk.containsKey(dataString)){
            retVal=dkUk.get(dataString);
            return retVal;
        }
        return retVal;
    }

    private String doReverse(String dataString) {
        StringBuilder sb = new StringBuilder();
        char[] charData = dataString.toCharArray();
        for(int i=charData.length-1;i>=0;i--){
            sb.append(charData[i]);
        }
        return sb.toString();
    }

    @Override
    public void run() {
        try {
            while (true) {
                this.protocol();
            }
        } catch (IOException ie) {
            ie.printStackTrace();
        }
    }
}
