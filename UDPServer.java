// Java program to illustrate Server side
// Implementation using DatagramSocket
import java.net.*;
import java.io.*;


public class UDPServer {
    public static void main(String[] args) {
        DatagramSocket socket = null;

        try {
            // Create a UDP socket to listen on a specific port (e.g., 12345)
            socket = new DatagramSocket(7500);

            byte[] receiveData = new byte[1024];

            while (true) {
                DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);

                // Wait for a UDP packet to arrive
                socket.receive(receivePacket);

                String message = new String(receivePacket.getData(), 0, receivePacket.getLength());
                System.out.println("Received message from client: " + message);

                // You can process the received message here

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