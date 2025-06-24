import java.io.*;
import java.net.*;

public class FileTransferServer {
    public static void main(String[] args) {
        int port = 12345;
        String filePath = "sample.txt"; // Path to the file to be sent

        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("Server is running and waiting for a connection...");

            Socket socket = serverSocket.accept();
            System.out.println("Client connected: " + socket.getInetAddress());

            // Send file
            File file = new File(filePath);
            byte[] buffer = new byte[4096];
            FileInputStream fis = new FileInputStream(file);
            OutputStream os = socket.getOutputStream();

            int bytesRead;
            while ((bytesRead = fis.read(buffer)) > 0) {
                os.write(buffer, 0, bytesRead);
            }

            os.flush();
            System.out.println("File sent successfully!");

            fis.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
