import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

public class Server {
    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(12345); // Create a server socket on port 12345

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
                    String result = countMaxCharacter(clientMessage);
                    out.println(result); // Send the result back to the client
                }

                clientSocket.close(); // Close the client socket
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static String countMaxCharacter(String input) {
        // Create a map to store character counts
        Map<Character, Integer> charCount = new HashMap<>();
        char maxChar = '\0';
        int maxCount = 0;

        // Loop through the input string and count character occurrences
        for (char c : input.toCharArray()) {
            if (Character.isLetter(c)) {
                c = Character.toLowerCase(c); // Ignore letter case
                int count = charCount.getOrDefault(c, 0) + 1;
                charCount.put(c, count);
                if (count > maxCount) {
                    maxCount = count;
                    maxChar = c;
                }
            }
        }

        if (maxChar != '\0') {
            return maxChar + ":" + maxCount;
        } else {
            return "No valid characters in the input string";
        }
    }
}
