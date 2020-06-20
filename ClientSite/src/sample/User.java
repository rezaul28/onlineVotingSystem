package sample;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;

public class User {
    private String name;
    private int id;
    private String password;
    protected Socket socket ;
    protected DataInputStream din;
    protected DataOutputStream dout;
    private Result resultVariable;

    //default constructor
    User(){

    }

    //constructor
    public User(Socket socket) {
        this.socket=socket;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    //login
    public static boolean logIn(){
        return false;
    }

    //logout
    public boolean logOut(){

        return false;
    }

    //take old and new password and set
    public  boolean changePassword(String oldPassword,String newPassword) throws IOException {
        System.out.println("Here I am");
        dout=new DataOutputStream(socket.getOutputStream());
        din=new DataInputStream(socket.getInputStream());
        dout.writeUTF("changePassword");
        dout.writeUTF(Integer.toString(id));
        dout.writeUTF(oldPassword);
        dout.writeUTF(newPassword);
        dout.flush();
        String str=din.readUTF();
        if(str.equals("true"))
            return true;
        return false;
    }

    //get final result and returns with an arry
    public ArrayList<Result> finalResult(){
        Result result;
        ArrayList<Result> results=new ArrayList<Result>();
        try {
            dout=new DataOutputStream(socket.getOutputStream());
            dout.writeUTF("finalResult");
            dout.flush();
            din=new DataInputStream(socket.getInputStream());
            String size=din.readUTF();
            for(int i=0;i<Integer.parseInt(size);i++){
                String name=din.readUTF();
                String id=din.readUTF();
                String post=din.readUTF();
                String vote=din.readUTF();
                result=new Result(name,Integer.parseInt(id),post,Integer.parseInt(vote));
                results.add(result);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return results;
    }


    //returns candidate list with an array
    public ArrayList<Candidate> candidateList(){
        ArrayList<Candidate>candidates=new ArrayList<Candidate>();
        try {
            dout=new DataOutputStream(socket.getOutputStream());
            dout.writeUTF("candidateList");
            dout.flush();

            din= new DataInputStream(socket.getInputStream());
            String size=din.readUTF();
            System.out.println(size);
            Candidate candidate;
            for(int i=0;i<Integer.parseInt(size);i++){
                String name=din.readUTF();
                String id=din.readUTF();
                String post=din.readUTF();
                candidate=new Candidate(name,Integer.parseInt(id),post);
                candidates.add(candidate);
                System.out.println(name);

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return candidates;
    }

    //check if vote is running or finished or yet to start
    public int checkVoteTime(){
        int flag = 0;
        try{
            dout=new DataOutputStream(socket.getOutputStream());
            dout.writeUTF("voteTimeCheck");
            dout.flush();
            din=new DataInputStream(socket.getInputStream());
            String timestatus=din.readUTF();
            System.out.println(timestatus);
            flag=Integer.parseInt(timestatus);
        }catch(Exception e){
            e.printStackTrace();
        }
        return flag;
    }
}