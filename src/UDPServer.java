// Java program to illustrate Server side
// Implementation using DatagramSocket
import java.net.*;
import java.io.*;

// For receiving information from the traffic generator
public class UDPServer implements Runnable{
    
    public void startServer() throws IOException {

        InetAddress serverAddress = InetAddress.getByName("127.0.0.1");
        DatagramSocket serverSocket = new DatagramSocket(7501, serverAddress);
        try {
            // Create a UDP socket to listen on a specific port (e.g., 12345)
            byte[] receiveData = new byte[1024];

            System.out.println("UDP Server is running...");

            while (true) {
                DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
                
                // Wait for a UDP packet to arrive
                serverSocket.receive(receivePacket);

                String message = new String(receivePacket.getData(), 0, receivePacket.getLength());
                System.out.println("Received message from client: " + message);

                // Message Processing
                // Splits the int:int message into two integers for processing
                String[] gamePlayers = message.split(":");

                String hitPlayer = gamePlayers[0];
                String scorePlayer = gamePlayers[1];

                // Convert String to int using Integer.parseInt()
                int number1 = Integer.parseInt(hitPlayer);

                // Convert String to int using Integer.parseInt()
                int number2 = Integer.parseInt(scorePlayer);

                Model.addScore(number1, number2);

                // Players[0] will be equipment ID of player transmitting (Get Points)

                // Players[1] will be equipment ID of player that got hit
                
                // Clear the receive buffer for the next message
                receiveData = new byte[1024];
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (serverSocket != null && !serverSocket.isClosed()) {
                serverSocket.close();
            }
        }
    }

    @Override
    public void run() {
        System.out.println("Thread about to start server\n");
        try {
            startServer();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } // Start the server when this thread starts running
    }
}
