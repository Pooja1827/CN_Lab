import java.io.*;
import java.net.*;
class Server1 {
    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(3636);
            System.out.println("Server started. Waiting for client...");
            Socket socket = serverSocket.accept();
            System.out.println("Client connected.");
            BufferedReader din = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            DataOutputStream dout = new DataOutputStream(socket.getOutputStream());
            String[] ipList = { "165.165.80.80", "165.165.79.1" };
            String[] macList = { "6A:08:AA:C2", "8A:BC:E3:FA" };
            String input;
            while ((input = din.readLine()) != null) {
                System.out.println("Received IP: " + input);
                if (input.equalsIgnoreCase("exit")) {
                    System.out.println("Client exited.");
                    break;
                }
                boolean found = false;
                for (int i = 0; i < ipList.length; i++) {
                    if (input.equals(ipList[i])) {
                        dout.writeBytes(macList[i] + '\n');
                        found = true;
                        break;
                    }
                }
                if (!found) {
                    dout.writeBytes("MAC not found\n");
                }
            }
            din.close();
            dout.close();
            socket.close();
            serverSocket.close();
            System.out.println("Server closed.");
        } catch (Exception e) {
            System.out.println("Server Error: " + e.getMessage());
        }
    }
}
