package AlisaFallout.client;

import java.net.*;
import java.io.*;

public class ChatClient
{
    private Socket socket               = null;
    private DataOutputStream streamOut  = null;
    private InputStreamReader inReader  = null;
    private BufferedReader reader       = null;
    private DataInputStream streamIn    = null;

    public ChatClient(String serverName, int serverPort) {
        System.out.println("Установление соединения...");
        try {
            socket = new Socket(serverName, serverPort);
            System.out.println("Соединен: " + socket);
            start();
        } catch(UnknownHostException uhe) {
            System.out.println("Host unknown: " + uhe.getMessage());
        } catch(IOException ioe) {
            System.out.println("Неизвестное исключение: " + ioe.getMessage());
        }
        String line = "";
        while (!line.equals("close")) {
            try {
                line = reader.readLine();
                if (!line.equals("close")) {
                    streamOut.writeUTF(line);
                    streamOut.flush();
                }
                System.out.println(streamIn.readUTF());
            } catch(Exception ioe) {
                System.out.println("Ошибка отправки: " + ioe.getMessage());
            }
        }
        stop();
    }
    public void start() throws IOException {
        streamOut = new DataOutputStream(new BufferedOutputStream(socket.getOutputStream()));
        streamIn = new DataInputStream(new BufferedInputStream(socket.getInputStream()));;

        inReader = new InputStreamReader(System.in);
        reader = new BufferedReader(inReader);
    }
    public void stop() {
        try {
            if (reader != null)  reader.close();
            if (streamOut != null)  streamOut.close();
            if (socket != null)  socket.close();
            if (inReader != null)  inReader.close();
        } catch(IOException ioe) {
            System.out.println("Ошибка закрытия ...");
        }
    }
    public static void main(String args[]) {
        ChatClient client = null;
        if (args.length != 2)
            System.out.println("Использование: java ChatClient host port");
        else
            client = new ChatClient(args[0], Integer.parseInt(args[1]));
    }
}