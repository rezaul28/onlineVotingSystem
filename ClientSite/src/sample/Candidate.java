package sample;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.util.ArrayList;

public class Candidate extends User {
    DataInputStream din;
    DataOutputStream dout;
    ObjectInputStream objectInputStream;
    private java.lang.Object Object;
    String post;
    private int ballotNo;
    ArrayList<Candidate> candidateArrayList = new ArrayList<Candidate>();


    //constructor
    Candidate(Socket socket){
        super(socket);
    }

    //constructor
    Candidate(String name,int ballotNo, String post){
        this.ballotNo = ballotNo;
        setName(name);
        this.post=post;
    }

    //constructor
    Candidate(String name,String id,String post){
        setName(name);
        setId(Integer.parseInt(id));
        this.post=post;
    }

    public String getPost() {
        return post;
    }

    public void setPost(String post) {
        this.post = post;
    }

    public int getBallotNo() {
        return ballotNo;
    }

    public void setBallotNo(int ballotNo) {
        this.ballotNo = ballotNo;
    }

    //take candidate id and password and permit to login
    public boolean login(int id,String password){
        String str = null;
        setId(id);
        try {
            dout = new DataOutputStream(socket.getOutputStream());
            dout.writeUTF("logIn");
            dout.writeUTF("Candidate");
            dout.writeUTF(Integer.toString(id));
            dout.writeUTF(password);
            dout.flush();
            din = new DataInputStream(socket.getInputStream());
            str = din.readUTF();
        }catch(Exception ex){
            System.out.println("Candidate log in denied");
        }
        if(str.equals("true"))
            return true;
        else
            return false;
    }

    //returns a candidate type object with his information
    public  Candidate viewDetails(){
        Candidate candidate = null;
        try {
            dout=new DataOutputStream(socket.getOutputStream());
            dout.writeUTF("candidateDetails");
            dout.writeUTF(String.valueOf(getId()));
            dout.flush();
            din=new DataInputStream(socket.getInputStream());
            String name=din.readUTF();
            String id=din.readUTF();
            String post=din.readUTF();
            System.out.println(name);
            System.out.println(id);
            System.out.println(post);

            candidate=new Candidate(name,id,post);

        } catch (Exception e) {
            System.out.println("Candidate details error");
        }
        return candidate;
    }

}