import java.io.*;
import java.net.*;

public class EchoServer {
    public static void main(String[] args) {
        int port = 12345;  // You can use any port number above 1024

        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("Echo Server is running on port " + port + "...");

            while (true) {
                Socket clientSocket = serverSocket.accept();  // Wait for client
                System.out.println("Client connected: " + clientSocket.getInetAddress());

                // Create input/output streams
                BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);

                String receivedMessage;
                while ((receivedMessage = in.readLine()) != null) {
                    System.out.println("Received: " + receivedMessage);
                    out.println("Echo: " + receivedMessage);  // Echo back
                }

                clientSocket.close();
                System.out.println("Client disconnected.");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
