import java.net.*;
import java.util.*;

public class PingClient {
    public static void main(String[] args) throws Exception{
        int port = 2014;
        DatagramSocket uplink = new DatagramSocket(port);
        byte[] transferData = new byte[1024];
        Scanner keyboard = new Scanner(System.in);
        System.out.println("Ping server? Y/N: ");
        String answer = keyboard.next().toLowerCase();
        char proceed = answer.charAt(0);
        do{

        }while()

    }
}
