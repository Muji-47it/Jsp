import java.io.*;
import java.net.Socket;

public class Client {
    public static void main(String[] args) {
        try {
            Socket socket = new Socket("localhost", 12345); // Connect to the server running on localhost and port 12345

            // Input stream to read from the server
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream());
            // Output stream to send data to the server
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

            String message = "Hello, Ali! How are you ?";
            out.println(message); // Send the message to the server

            String response = in.readLine(); // Receive the server's response
            System.out.println("Server: " + response);

            socket.close(); // Close the socket
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
