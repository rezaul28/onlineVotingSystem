package sample;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;

public class Admin extends User {
    DataOutputStream dout;
    DataInputStream din;
    private String post;
    ArrayList<Voter> voterArrayList = new ArrayList<Voter>();
    ArrayList<Candidate> candidateArrayList = new ArrayList<Candidate>();

    //constructor to save socket
    Admin(Socket socket){
        super(socket);
    }

    //add new voter by name and password
    public void addVoter(String name, String password){
        try {
            dout=new DataOutputStream(socket.getOutputStream());
            dout.writeUTF("addVoter");
            dout.writeUTF(name);
            dout.writeUTF(password);
            dout.flush();
        } catch (IOException e) {
            System.out.println("Voter addition error");
        }
    }

    //add candidate by name password and post
    public void addCandidate(String name, String password , String post ) {
        try {
            dout=new DataOutputStream(socket.getOutputStream());
            dout.writeUTF("addCandidate");
            dout.writeUTF(name);
            dout.writeUTF(password);
            dout.writeUTF(post);
            dout.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    //take an id of a candidate and delete
    public boolean deleteCandidate(String id){
        try {
            dout=new DataOutputStream(socket.getOutputStream());
            dout.writeUTF("deleteCandidate");
            dout.writeUTF(id);
            dout.flush();
            din=new DataInputStream(socket.getInputStream());
            String str=din.readUTF();
            if(str.equals("true"))
                return true;
        } catch (IOException e) {
            System.out.println("Candidate Delete error");
        }
        return false;
    }

    //return a list containing all voters
    public ArrayList<Voter> viewVoterList() {
        ArrayList<Voter>voters=new ArrayList<Voter>();
        try {
            dout=new DataOutputStream(socket.getOutputStream());
            dout.writeUTF("voterList");
            dout.flush();

            din= new DataInputStream(socket.getInputStream());
            String size=din.readUTF();
            Voter voter;
            for(int i=0;i<Integer.parseInt(size);i++){
                String name=din.readUTF();
                String id=din.readUTF();
                voter=new Voter(name,Integer.parseInt(id));
                voters.add(voter);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return voters;
    }

    //returns an arraylist containing all candidate
    /*public ArrayList<Candidate> candidateList(){
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

     */

    //take password and permit to login
    public boolean login (String password) throws IOException {
        dout=new DataOutputStream(socket.getOutputStream());
        dout.writeUTF("logIn");
        dout.writeUTF("Admin");
        dout.writeUTF(password);
        dout.flush();
        din=new DataInputStream(socket.getInputStream());
        String str=din.readUTF();
        if(str.equals("true")){
            return true;
        }
        else return false;
    }

    //set starting and finishing time of vote
    public void setPollTime(String start ,String finish ){
        try {
            dout=new DataOutputStream(socket.getOutputStream());
            dout.writeUTF("setPollTime");
            dout.writeUTF(start);
            dout.writeUTF(finish);
            dout.flush();
        } catch (IOException e) {
            System.out.println("set poll time error");
        }

    }

    //edit voter's name
    public boolean editVoter(String id, String name){
        try{
            dout=new DataOutputStream(socket.getOutputStream());
            din=new DataInputStream(socket.getInputStream());
            dout.writeUTF("editVoter");
            dout.writeUTF(id);
            dout.writeUTF(name);
            dout.flush();
            String flag=din.readUTF();
            if(flag.equals("true"))
                return true;
        }catch(Exception ex){
            System.out.println("Edit voter error");
        }
        return false;
    }

    //edit candidate name
    public boolean editCandidate(String id, String name){
        try{
            dout=new DataOutputStream(socket.getOutputStream());
            din=new DataInputStream(socket.getInputStream());
            dout.writeUTF("editCandidate");
            dout.writeUTF(id);
            dout.writeUTF(name);
            dout.flush();
            String flag=din.readUTF();
            System.out.println(flag);
            if(flag.equals("true"))
                return true;
        }catch(Exception ex){
            System.out.println("Edit Candidate error");
        }
        return false;
    }
}
