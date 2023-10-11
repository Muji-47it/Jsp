import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class MyServer {
    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(1234); // Create a server socket on port 1234

            while (true) {
                System.out.println("Waiting for a connection...");
                Socket clientSocket = serverSocket.accept(); // Wait for a client to connect
                System.out.println("Connected to client");

                // Input stream to receive data from the client
                BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                // Output stream to send data to the client
                PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);

                String clientMessage = in.readLine(); // Receive the client's message

                if (clientMessage != null) {
                    String name = clientMessage.substring(clientMessage.lastIndexOf(" ") + 1); // Extract the name from the message
                    String response = "Walikum Salam, " + name;
                    out.println(response); // Send the response back to the client
                }

                clientSocket.close(); // Close the client socket
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
