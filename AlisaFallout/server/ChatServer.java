package AlisaFallout.server;

import java.net.*;
import java.util.Random;
import java.io.*;

public class ChatServer
{
    private Socket          socket   = null;
    private ServerSocket    server   = null;
    private DataInputStream streamIn =  null;
    private DataOutputStream streamOut =  null;

    public ChatServer(int port) {
        try {
            System.out.println("Запущен на " + port + " порту");
            server = new ServerSocket(port);  
            System.out.println("Сервер запущен: " + server);
            System.out.println("Ожидание подключения ..."); 
            socket = server.accept();
            System.out.println("Клиент соединен: " + socket);

            open();
            boolean done = false;
            while (!done) {
                try {
                    String line = streamIn.readUTF();

                    done = line.equals("close");
                    if (done) continue;

                    streamOut.writeUTF(selectRandomWordFromDictionary(line) + "?");
                    streamOut.flush();
                } catch(IOException ioe) {
                    done = true;
                }
            }
            close();
        } catch(Exception ioe) {
            System.out.println(ioe); 
        }
    }
    public void open() throws IOException {
        streamIn = new DataInputStream(new BufferedInputStream(socket.getInputStream()));
        streamOut = new DataOutputStream(new BufferedOutputStream(socket.getOutputStream()));
    }
    public void close() throws IOException {
        if (socket != null)    socket.close();
        if (streamIn != null)  streamIn.close();
        if (streamOut != null)  streamOut.close();
    }

    public String selectRandomWord (String question) {
        Random rand = new Random();
        String[] words = question.split(" ");

        return words[rand.nextInt(words.length)];
    }

    public String selectRandomWordFromDictionary(String question) {
        Random rand = new Random();
        String[] words = question.split(" ");
        String selectedWord = words[rand.nextInt(words.length)].toLowerCase();
        String retWord = "";

        System.out.println(selectedWord);

        String dictValue = DictParser.assocMap.get(selectedWord);

        if (dictValue != null && dictValue.length() > 0) {
            String[] dictValues = dictValue.split(",");
            retWord = dictValues[rand.nextInt(dictValues.length)];
        } else {
            retWord = selectedWord;
        }

        return retWord;
    }

    public static void main(String args[]) {
        DictParser.parse("rusDict.txt");

        ChatServer server = null;
        if (args.length != 1)
            System.out.println("Использование: java ChatServer port");
        else
            server = new ChatServer(Integer.parseInt(args[0]));
    }
}