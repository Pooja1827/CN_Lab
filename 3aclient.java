import java.io.*;
import java.net.*;

public class EchoClient {
    public static void main(String[] args) {
        String host = "localhost";  // or IP address
        int port = 12345;

        try (Socket socket = new Socket(host, port);
             BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             PrintWriter output = new PrintWriter(socket.getOutputStream(), true);
             BufferedReader userInput = new BufferedReader(new InputStreamReader(System.in))) {

            System.out.println("Connected to Echo Server. Type messages to send. Type 'exit' to quit.");

            String userMessage;
            while (true) {
                System.out.print("You: ");
                userMessage = userInput.readLine();

                if ("exit".equalsIgnoreCase(userMessage)) {
                    break;
                }

                output.println(userMessage);  // Send to server
                String serverResponse = input.readLine();  // Read echo
                System.out.println(serverResponse);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
