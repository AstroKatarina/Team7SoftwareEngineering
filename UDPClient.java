import java.net.*;
import java.io.*;
import java.util.Scanner;

public class UDPClient {
        public static void main(String[] args) throws IOException{
            Scanner sc = new Scanner(System.in);
           
            DatagramSocket socket = new DatagramSocket();

            // Define the server address and port (e.g., localhost and 12345)
            InetAddress serverAddress = InetAddress.getLocalHost();
            int serverPort = 7500;
            byte buf[] = null;

            //Temporary int
            int code = 0;

            while(true) {
                System.out.print("Player Code: ");
                System.out.print(code);
                String input = sc.nextLine();

                // convert the String input into the byte array.
                buf = input.getBytes();

                // Create a UDP packet with the message and send it to the server
                DatagramPacket sendPacket = new DatagramPacket(buf, buf.length, serverAddress, serverPort);
                socket.send(sendPacket);

                System.out.println("Sent message to server: " + input);

                if(input.equals("bye")){
                break;
                }
            }

            socket.close();
            sc.close();

        }
}

