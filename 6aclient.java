import java.io.*;
import java.net.*;
class Client1 {
    public static void main(String[] args) {
        try {
            Socket socket = new Socket("127.0.0.1", 3636);
            System.out.println("Connected to server.");
            BufferedReader userInput = new BufferedReader(new InputStreamReader(System.in));
            BufferedReader serverInput = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            DataOutputStream serverOutput = new DataOutputStream(socket.getOutputStream());
            String ip;
            while (true) {
                System.out.print("Enter the Logical Address (IP) or type 'exit' to quit: ");
                ip = userInput.readLine();
                serverOutput.writeBytes(ip + "\n");
                if (ip.equalsIgnoreCase("exit")) {
                    System.out.println("Client exited.");
                    break;
                }
                String response = serverInput.readLine();
                System.out.println("The Physical Address is: " + response);
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
