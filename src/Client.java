import java.io.*;
import java.net.*;
import javax.swing.*;

class gui
{    
    public static void main(String[] args)
    {
        JFrame frame = new JFrame("My First GUI");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 300);
        JButton button1 = new JButton("Press");
        frame.getContentPane().add(button1); // Adds Button to content pane of frame
        frame.setVisible(true);
    }
}

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
            System.out.println("Prosim zadajte vase meno");
            String username = inFromUser.readLine(); // posleme meno 
            // System.out.println(sentence);
            if (username.equals("~")) {
                clientSocket.close();
                System.exit(0);
            } else {
                byte[] sendData = new byte[1024]; // sluzi na nacitanie dat a neskor aj odosielame
                byte[] receiveData = new byte[1024]; // sluzi na prijatie sprav
                sendData = username.getBytes();
                DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, 9876);
                clientSocket.send(sendPacket);
                sendData = new byte[0];
                System.out.println("Toto je text zo send");
                System.out.println(sendData);
                DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
                clientSocket.receive(receivePacket);
                String modifiedSentence = "";
                modifiedSentence = new String(receivePacket.getData());
                System.out.println("FROM SERVER:" + modifiedSentence); //sluzi na kontrolu ci mi to prislo a ze ci ma server registruje
            }

        }
    }
}