import java.io.*;
import java.net.*;

public class FileTransferClient {
    public static void main(String[] args) {
        String serverAddress = "localhost";
        int port = 12345;
        String savePath = "received_file.txt";

        try (Socket socket = new Socket(serverAddress, port)) {
            System.out.println("Connected to server. Downloading file...");

            InputStream is = socket.getInputStream();
            FileOutputStream fos = new FileOutputStream(savePath);
            byte[] buffer = new byte[4096];

            int bytesRead;
            while ((bytesRead = is.read(buffer)) > 0) {
                fos.write(buffer, 0, bytesRead);
            }

            System.out.println("File received and saved as: " + savePath);

            fos.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
