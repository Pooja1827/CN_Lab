import java.io.*;
import java.net.*;
class Client {
    public static void main(String[] args) {
        try {
            DatagramSocket client = new DatagramSocket();
            InetAddress serverAddress = InetAddress.getByName("127.0.0.1");
            BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
            while (true) {
                System.out.print("Enter domain name or IP address (or type 'exit' to quit): ");
                String userInput = input.readLine();
                if (userInput.equalsIgnoreCase("exit")) {
                    System.out.println("Client exited.");
                    break;
                }
                byte[] sendData = userInput.getBytes();
                DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, serverAddress, 1309);
                client.send(sendPacket);
                byte[] receiveData = new byte[1024];
                DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
                client.receive(receivePacket);
                String response = new String(receivePacket.getData()).trim();
                System.out.println("Response from server: " + response);
            }
            client.close();
        } catch (Exception e) {
            System.out.println("Client Error: " + e.getMessage());
        }
    }
}
