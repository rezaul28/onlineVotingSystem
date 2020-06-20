/*
Author : Rezaul Karim
2nd year student
Roll : 44
Dhaka University
 */


package Server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket=null;
        Socket socket = null;

        serverSocket=new ServerSocket(6000);//diving port number
        Database.readFileFromFile();//read all data from al files

        //set writer class as a thread
        Writer write=new Writer();
        Thread thread=new Thread(write);
        thread.start();

        //set timeSvecker as a Thread
        TimeChecker timeChecker=new TimeChecker();
        Thread time=new Thread(timeChecker);
        time.start();

        //set Client Class as a thread and set an infinite loop to accept clients
        while(true){
            socket=serverSocket.accept();//connection request confirmed
            Client a=new Client(socket);
            Thread x=new Thread(a);
            x.start();
        }
    }

}
