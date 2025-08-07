import java.io.*;
import java.net.*;
class Server {
    public static void main(String[] args) {
        try {
            DatagramSocket server = new DatagramSocket(1309);
            System.out.println("DNS Server is running...");
            String[] ip = { "165.165.80.80", "165.165.79.1" };
            String[] name = { "www.aptitudeguru.com", "www.downloadcyclone.blogspot.com" };
            while (true) {
                byte[] receiveData = new byte[1024];
                byte[] sendData = new byte[1024];
                DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
                server.receive(receivePacket);
                String query = new String(receivePacket.getData()).trim();
                InetAddress clientAddress = receivePacket.getAddress();
                int clientPort = receivePacket.getPort();
                System.out.println("Received: " + query);
                String response = "Not found";
                for (int i = 0; i < ip.length; i++) {
                    if (query.equalsIgnoreCase(ip[i])) {
                        response = name[i];
                        break;
                    } else if (query.equalsIgnoreCase(name[i])) {
                        response = ip[i];
                        break;
                    }
                }

                sendData = response.getBytes();
                DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, clientAddress, clientPort);
                server.send(sendPacket);
                System.out.println("Replied with: " + response);
            }
        } catch (Exception e) {
            System.out.println("Server Error: " + e.getMessage());
        }
    }
}
