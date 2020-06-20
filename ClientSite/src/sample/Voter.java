package sample;

import java.io.*;
import java.net.Socket;

public class Voter extends User {
    DataInputStream din;
    DataOutputStream dout;

    //constructor
    Voter(String name,int id){
        setId(id);
        setName(name);
    }

    //constructor
    Voter(String name,int id,String pass){
        setId(id);
        setName(name);
        setPassword(pass);
    }

    //constructor
    Voter(Socket socket){
        super(socket);
    }

    //checks if the voter is allowed to vote
    public boolean validVoter(){
        try {
            dout=new DataOutputStream(socket.getOutputStream());
            dout.writeUTF("validVoter");
            dout.writeUTF(String.valueOf(getId()));
            dout.flush();
            din=new DataInputStream(socket.getInputStream());
            String flag=din.readUTF();
            if(flag.equals("true"))
                return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    //take vote from voter
    public void giveVote(int presidentId,int vicePresedent){
        System.out.println("Vote dite asche hehe haha");
        try {
            dout=new DataOutputStream(socket.getOutputStream());
            dout.writeUTF("giveVote");
            dout.writeUTF(String.valueOf(getId()));
            dout.writeUTF(String.valueOf(presidentId));
            dout.writeUTF(String.valueOf(vicePresedent));
            dout.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //take id and password from voter and permit to login
    public boolean login(int id,String password ){
        setId(id);
        String returnType = null;
        try {
            dout=new DataOutputStream(socket.getOutputStream());
            dout.writeUTF("logIn");
            dout.writeUTF("Voter");
            dout.writeUTF(Integer.toString(id));
            dout.writeUTF(password);
            dout.flush();
            din=new DataInputStream(socket.getInputStream());
            returnType=din.readUTF();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if(returnType.equals("true")){
            System.out.println("Here in");
            return true;
        }
        else return false;
    }

    //showes voterDetails. returns voter type object
    public  Voter showDetails() {
        System.out.println("Details is here");
        Voter object=null;
        try {
            dout=new DataOutputStream(socket.getOutputStream());
            dout.writeUTF("voterDetails");
            dout.writeUTF(String.valueOf(getId()));
            dout.flush();
            System.out.println(object);
            din=new DataInputStream(socket.getInputStream());
            String name=din.readUTF();
            object= new Voter(name,getId());
            System.out.println(object);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(object);
        return object;
    }
}