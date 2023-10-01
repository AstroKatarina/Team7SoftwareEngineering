import java.net.*;
import java.io.*;
import java.util.Scanner;

public class UDPClient {
        public static void sendData(int Ecode) throws IOException{
           
            DatagramSocket socket = new DatagramSocket();

            // Define the server address and port (e.g., localhost and 12345)
            InetAddress serverAddress = InetAddress.getByName("10.35.11.109");
            int serverPort = 7500;
            
            // To recieve int:int information later
            // int receivePort = 7501;
           
            // Initializing packet to null
            byte buf[] = null;

            // Call method to get equipment code
            int code = Ecode;

            // Convert int code to string to be sent over UDP
            String stringCode = Integer.toString(code);

            // while(true) {

                // Convert the String input into the byte array.
                buf = stringCode.getBytes();

                // Create a UDP packet with the message and send it to the server
                DatagramPacket sendPacket = new DatagramPacket(buf, buf.length, serverAddress, serverPort);
                socket.send(sendPacket);
                
                // Error checking
                System.out.println("Sent message to server: " + code);

            // Close the socket    
            socket.close();
            }
        }
