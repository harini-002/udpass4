import java.net.*;
import java.util.Scanner;

public class UDPClient {
    public static void main(String[] args) throws Exception {
        DatagramSocket clientSocket = new DatagramSocket();
        InetAddress serverAddress = InetAddress.getByName("localhost");

        Scanner sc = new Scanner(System.in);
        System.out.println("Enter numbers separated by space: ");
        String input = sc.nextLine();

        byte[] sendData = input.getBytes();
        byte[] receiveData = new byte[1024];

        DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, serverAddress, 9876);
        clientSocket.send(sendPacket);

        DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
        clientSocket.receive(receivePacket);

        String result = new String(receivePacket.getData(), 0, receivePacket.getLength());
        System.out.println("From Server: " + result);

        clientSocket.close();
        sc.close();
    }
}
