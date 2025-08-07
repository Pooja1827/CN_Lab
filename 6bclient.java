import java.io.*;
import java.net.*;
class Client {
    public static void main(String[] args) {
        try {
            Socket socket = new Socket("127.0.0.1", 3000);
            System.out.println("Connected to server.");
            BufferedReader userInput = new BufferedReader(new InputStreamReader(System.in));
            BufferedReader serverInput = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            DataOutputStream serverOutput = new DataOutputStream(socket.getOutputStream());
            String mac;
            while (true) {
                System.out.print("Enter the Physical Address (MAC) or type 'exit' to quit: ");
                mac = userInput.readLine();
                serverOutput.writeBytes(mac + "\n");
                if (mac.equalsIgnoreCase("exit")) {
                    System.out.println("Client exited.");
                    break;
                }
                String response = serverInput.readLine();
                System.out.println("The Logical Address (IP): " + response);
            }
            userInput.close();
            serverInput.close();
            serverOutput.close();
            socket.close();
        } catch (Exception e) {
            System.out.println("Client Error: " + e.getMessage());
        }
    }
}
