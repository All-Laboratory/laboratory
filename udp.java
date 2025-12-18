//UDPClient.java
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;
public class UDPClient {
    public static void main(String[] args) {
        final String SERVER_IP = "localhost"; // Server IP address
        final int SERVER_PORT = 9876; // Server port

        try {
            // Create a DatagramSocket (client will use an available ephemeral port)
            DatagramSocket clientSocket = new DatagramSocket();
            InetAddress serverAddress = InetAddress.getByName(SERVER_IP);
            Scanner scanner = new Scanner(System.in);
            byte[] sendData; // Buffer for outgoing data
            byte[] receiveData = new byte[1024]; // Buffer for incoming data

            while (true) {
                System.out.print("Enter message to send (or 'quit' to exit): ");
                String message = scanner.nextLine();

                if (message.equalsIgnoreCase("quit")) {
                    break;
                }
                sendData = message.getBytes();
                // Create a DatagramPacket to send data to the server
                DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, serverAddress, SERVER_PORT);

                // Send the packet
                clientSocket.send(sendPacket);

                // Create a DatagramPacket to receive the response
                DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
                // Receive the response
                clientSocket.receive(receivePacket);
                // Convert received data to a String
                String receivedResponse = new String(receivePacket.getData(), 0, receivePacket.getLength());
                System.out.println("Received from server: " + receivedResponse);
            }
            clientSocket.close();
            scanner.close();
        } catch (IOException e) {
            e.printStackTrace();
        }    }}

//UDPServer.java
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
public class UDPServer {
    public static void main(String[] args) {
        final int PORT = 9876; // Server port
        try {
            // Create a DatagramSocket bound to the specified port
            DatagramSocket serverSocket = new DatagramSocket(PORT);
            System.out.println("UDP Server started on port " + PORT);
            byte[] receiveData = new byte[1024]; // Buffer for incoming data
            byte[] sendData; // Buffer for outgoing data
            while (true) {
                // Create a DatagramPacket to receive data
                DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);

                // Receive the packet
                serverSocket.receive(receivePacket);
                // Extract client's IP address and port
                InetAddress clientAddress = receivePacket.getAddress();
                int clientPort = receivePacket.getPort();
                // Convert received data to a String
                String receivedMessage = new String(receivePacket.getData(), 0, receivePacket.getLength());
                System.out.println("Received from client " + clientAddress.getHostAddress() + ":" + clientPort + ": " + receivedMessage);

                // Prepare a response (e.g., echo the message back)
                String responseMessage = "Server received: " + receivedMessage;
                sendData = responseMessage.getBytes();

                // Create a DatagramPacket to send the response to the client
                DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, clientAddress, clientPort);

                // Send the response
                serverSocket.send(sendPacket);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
