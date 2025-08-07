import java.io.*;
import java.net.*;
class Server {
    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(3000);
            System.out.println("Server started. Waiting for client...");
            Socket clientSocket = serverSocket.accept();
            System.out.println("Client connected.");
            BufferedReader reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            DataOutputStream writer = new DataOutputStream(clientSocket.getOutputStream());
            String[] macList = { "6A:08:AA:C2", "8A:BC:E3:FA" };
            String[] ipList = { "165.165.80.80", "165.165.79.1" };
            String input;
            while ((input = reader.readLine()) != null) {
                System.out.println("Received MAC: " + input);
                if (input.equalsIgnoreCase("exit")) {
                    System.out.println("Client exited.");
                    break;
                }
                boolean found = false;
                for (int i = 0; i < macList.length; i++) {
                    if (input.equalsIgnoreCase(macList[i])) {
                        writer.writeBytes(ipList[i] + "\n");
                        found = true;
                        break;
                    }
                }

                if (!found) {
                    writer.writeBytes("IP not found\n");
                }
            }
            reader.close();
            writer.close();
            clientSocket.close();
            serverSocket.close();
            System.out.println("Server closed.");
        } catch (Exception e) {
            System.out.println("Server Error: " + e.getMessage());
        }
    }
}
