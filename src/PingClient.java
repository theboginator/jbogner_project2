/*
PingClient application v1.0
(c) 2019 by Jacob Bogner
This application will send a series of pings over a specified port number and print out the response, if any,
from any PingServer application running on the same port.
 */
import java.net.*;
import java.util.*;

public class PingClient {
    public static void main(String[] args) throws Exception{
        int port = 2014; //Declare a port
        DatagramSocket uplink = new DatagramSocket();
        InetAddress IPAddress = InetAddress.getByName("localhost");
        byte[] transferData;
        byte[] receiveData = new byte[1024];
        String transferString = "hello_from_client";
        Scanner keyboard = new Scanner(System.in);
        System.out.println("Ping server? Y/N: ");
        String answer = keyboard.next().toLowerCase();
        char proceed = answer.charAt(0);
        while(proceed == 'y'){
            transferData = transferString.getBytes();

            // create packet to send
            DatagramPacket sendPacket = new DatagramPacket(transferData, transferData.length, IPAddress, port);
            DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);

            for(int attempts = 0; attempts <10; attempts ++){
                long start = System.currentTimeMillis();

                uplink.send(sendPacket);

                uplink.setSoTimeout(1000);

                try {
                    uplink.receive(receivePacket);

                    long finish = System.currentTimeMillis();
                    long rtt = finish - start;

                    System.out.println("Round trip time (RTT): " + rtt);

                    String incoming = new String(receivePacket.getData());
                    System.out.println("MSG From server: " + incoming);

                } catch (SocketTimeoutException ex) {
                    System.out.println("Request timed out.");
                }
            }

            System.out.println("Ping server again? Y/N: ");
            answer = keyboard.next().toLowerCase();
            proceed = answer.charAt(0);
        }
        uplink.close();


    }
}
