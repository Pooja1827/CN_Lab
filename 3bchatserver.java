import java.io.*;
import java.net.*;
import java.util.*;

public class ChatServer {
    private static Set<PrintWriter> clientWriters = new HashSet<>();

    public static void main(String[] args) {
        int port = 12345;

        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("Chat Server started on port " + port);

            while (true) {
                Socket clientSocket = serverSocket.accept();  // Accept new client
                System.out.println("New client connected: " + clientSocket.getInetAddress());
                new ClientHandler(clientSocket).start();  // Start new thread for client
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Inner class to handle client connections
    static class ClientHandler extends Thread {
        private Socket socket;
        private PrintWriter out;
        private BufferedReader in;

        public ClientHandler(Socket socket) {
            this.socket = socket;
        }

        public void run() {
            try {
                in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                out = new PrintWriter(socket.getOutputStream(), true);

                synchronized (clientWriters) {
                    clientWriters.add(out);
                }

                String message;
                while ((message = in.readLine()) != null) {
                    System.out.println("Message received: " + message);

                    // Broadcast message to all clients
                    synchronized (clientWriters) {
                        for (PrintWriter writer : clientWriters) {
                            writer.println(message);
                        }
                    }
                }

            } catch (IOException e) {
                System.out.println("Client disconnected.");
            } finally {
                try {
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                synchronized (clientWriters) {
                    clientWriters.remove(out);
                }
            }
        }
    }
}
