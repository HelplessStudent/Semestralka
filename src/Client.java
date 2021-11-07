import java.io.*;
import java.net.*;
class Client
{
    public static void main(String args[]) throws Exception
    {
        BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in));
        DatagramSocket clientSocket = new DatagramSocket();
        InetAddress IPAddress = InetAddress.getByName("localhost");
       
        for (;;) {
            // byte[] sendData = new byte[1024]; //sluzi na nacitanie dat a neskor aj
            // odosielame
            // byte[] receiveData = new byte[1024]; //sluzi na prijatie sprav
            String sentence = inFromUser.readLine(); // tipujem ze do tohot nahravam to co pisem do clienta
            // System.out.println(sentence);
            if (sentence.equals("~")) {
                clientSocket.close();
                System.exit(0);
            } else {
                byte[] sendData = new byte[1024]; // sluzi na nacitanie dat a neskor aj odosielame
                byte[] receiveData = new byte[1024]; // sluzi na prijatie sprav
                sendData = sentence.getBytes();
                DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, 9876);
                clientSocket.send(sendPacket);
                DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
                clientSocket.receive(receivePacket);
                String modifiedSentence = new String(receivePacket.getData());
                System.out.println("FROM SERVER:" + modifiedSentence);
            }

        }
    }
}