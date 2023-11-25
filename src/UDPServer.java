// Java program to illustrate Server side
// Implementation using DatagramSocket
import java.net.*;
import java.io.*;

// For receiving information from the traffic generator
public class UDPServer {
    public static void main(String[] args) throws IOException {
        try {
            // Create a UDP socket to listen on a specific port (e.g., 12345)
            DatagramSocket serverSocket = new DatagramSocket(7501);
            byte[] receiveData = new byte[1024];

            System.out.println("UDP Server is running...");

            while (true) {
                DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
                
                // Wait for a UDP packet to arrive
                socket.receive(receivePacket);

                String message = new String(receivePacket.getData(), 0, receivePacket.getLength());
                System.out.println("Received message from client: " + message);

                // Message Processing
                // Splits the int:int message into two integers for processing
                public Players[] = message.split(":");

                // Players[0] will be equipment ID of player transmitting (Get Points)
                
                // Players[1] will be equipment ID of player that got hit
                
                // Clear the receive buffer for the next message
                receiveData = new byte[1024];
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (socket != null && !socket.isClosed()) {
                socket.close();
            }
        }
    }
}
