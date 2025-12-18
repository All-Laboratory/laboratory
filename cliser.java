//Program: Client.java
import java.io.*;
import java.net.*;
public class Client {
    public static void main(String[] args) throws IOException {
        String serverAddress = "127.0.0.1"; // Server's IP address (localhost)
        int port = 5000; // Server's port number
        Socket socket = new Socket(serverAddress, port); // Connect to the server
        System.out.println("Connected to server: " + serverAddress + ":" + port);
        // Get input and output streams for communication
        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        PrintWriter out = new PrintWriter(socket.getOutputStream(), true); // true for auto-flush
        BufferedReader consoleIn = new BufferedReader(new InputStreamReader(System.in)); // Read from console
        String userInput;
        while (true) {
            System.out.print("Enter message to send (type 'bye' to exit): ");
            userInput = consoleIn.readLine();
            out.println(userInput); // Send message to server
            String serverResponse = in.readLine(); // Read server's response
            System.out.println("Server says: " + serverResponse);

            if (userInput.equalsIgnoreCase("bye")) {
                break;
            }
        }

        // Close connections
        consoleIn.close();
        in.close();
        out.close();
        socket.close();
        System.out.println("Client closed.");
    }
}

//Program: Server.java
import java.io.*;
import java.net.*;
public class Server {
    public static void main(String[] args) throws IOException {
        int port = 5000; // Port number to listen on
        ServerSocket serverSocket = new ServerSocket(port); // Create a server socket
        System.out.println("Server listening on port " + port);
        // Wait for a client to connect
        Socket clientSocket = serverSocket.accept(); 
        System.out.println("Client connected: " + clientSocket.getInetAddress());
        // Get input and output streams for communication
        BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true); // true for auto-flush
        String clientMessage;
        while ((clientMessage = in.readLine()) != null) {
            System.out.println("Client says: " + clientMessage);
            out.println("Server received: " + clientMessage); // Send a response back
            if (clientMessage.equalsIgnoreCase("bye")) {
                break;
            }
        }

        // Close connections
        in.close();
        out.close();
        clientSocket.close();
        serverSocket.close();
        System.out.println("Server closed.");
    }
}
