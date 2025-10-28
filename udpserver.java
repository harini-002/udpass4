import java.net.*;

public class UDPServer {
    public static void main(String[] args) throws Exception {
        DatagramSocket serverSocket = new DatagramSocket(9876);
        byte[] receiveData = new byte[1024];
        byte[] sendData;

        System.out.println("Server is running and waiting for data...");

        while (true) {
            DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
            serverSocket.receive(receivePacket);

            String clientData = new String(receivePacket.getData(), 0, receivePacket.getLength());
            System.out.println("Received from client: " + clientData);

            // Split and sum the numbers
            String[] numbers = clientData.trim().split(" ");
            int sum = 0;
            for (String num : numbers) {
                sum += Integer.parseInt(num);
            }

            String result = "Sum = " + sum;
            sendData = result.getBytes();

            InetAddress clientIP = receivePacket.getAddress();
            int clientPort = receivePacket.getPort();

            DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, clientIP, clientPort);
            serverSocket.send(sendPacket);

            System.out.println("Result sent to client.");

            // Optional: break after one response (or keep running for multiple clients)
            // break;
        }
    }
}
