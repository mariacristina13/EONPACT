package griffith;

import java.io.*;
import java.net.*;
import java.util.*;

public class GameServer {
    private static final int PORT = 5000;
    private static List<ClientHandler> clients = new ArrayList<>();

    public static void main(String[] args) {//Start the server and accepts the client connections

        System.out.println("Server started...");
        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            
            while (true) {

                Socket socket = serverSocket.accept();//to wait for client
                System.out.println("Player connected!");
                ClientHandler client = new ClientHandler(socket);
                clients.add(client);//store the clients
                new Thread(client).start();//to handle the client in new thread

            }
        }
        catch (IOException e) {

            e.printStackTrace();

        }
    }

    static class ClientHandler implements Runnable {
        private Socket socket;
        private BufferedReader in;
        private PrintWriter out;

        public ClientHandler(Socket socket) {//constructor

            this.socket = socket;

        }

        public void run() {//handles the communication with a single client

            try {

                in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                out = new PrintWriter(socket.getOutputStream(), true);
                String message;
                while ((message = in.readLine()) != null) {

                    broadcast(message);

                }
            }
            catch (IOException e) {

                System.out.println("Player disconnected");

            }
        }

        private void broadcast(String message) {//send the message to all connected clients

            for (ClientHandler client : clients) {

                client.out.println(message);

            }
        }
    }
}