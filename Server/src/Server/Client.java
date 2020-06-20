/*
Author : Rezaul Karim
2nd year student
Roll : 44
Dhaka University
 */

package Server;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;

public class Client extends Database implements Runnable{
    private int id;
    private String name;
    private String password;
    private DataInputStream din;
    private DataOutputStream dout;
    private Socket socket;
    private ObjectInputStream objectInputStream;
    private ObjectOutputStream objectOutputStream;

    //constructor
    Client(Socket socket){
        this.socket=socket;
    }

    @Override
    public void run() {
        String str=null;
        while(true){
            try{
                din=new DataInputStream(socket.getInputStream());
                dout=new DataOutputStream(socket.getOutputStream());

                str=din.readUTF(); //take a request from client
            }catch(Exception ex){
                break;
            }

            //if request is to login here it goes and check if client is admin or candidate or voter type
            if(str.equals("logIn")){
                try {
                    str = din.readUTF();
                }catch(Exception ex){
                    System.out.println();
                    break;
                }
                if(str.equals("Admin")){
                    System.out.println("Not Here");
                    try {
                        password=din.readUTF();
                        boolean flag=logInAccess(password);
                        if(flag){
                            System.out.println("OK");
                            setLogInStatus(1);
                        }
                        else setLogInStatus(0);
                        dout.writeUTF(String.valueOf(flag));
                        dout.flush();
                    } catch (IOException e) {
                        e.printStackTrace();
                        break;
                    }
                }
                else {
                    System.out.println("Here");
                    try{
                        String idString=din.readUTF();
                        id=Integer.parseInt(idString);
                        password=din.readUTF();
                        boolean flag= logInAccess(id,password,str);
                        dout.writeUTF(String.valueOf(flag));
                        dout.flush();

                        idString=String.valueOf(flag);
                        System.out.println(idString);

                    }catch(Exception ex){System.out.println("Out");break;}
                }

            }

            //changes password
            else if(str.equals("changePassword")){
                System.out.println("Fabliha");
                try {
                    String strId=din.readUTF();
                    id=Integer.parseInt(strId);
                    password=din.readUTF();
                    String newPassword=din.readUTF();
                    boolean flag=changePassword(id,password,newPassword);
                    System.out.println("flag");
                    dout.writeUTF(String.valueOf(flag));
                    dout.flush();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            //sends voter list to client
            else if(str.equals("voterList")){
                System.out.println("Voter List");
                ArrayList<Voter>ara=sendVoterList();
                try {
                    dout.writeUTF(String.valueOf(ara.size()));
                    dout.flush();
                    for(int i=0;i<ara.size();i++){
                        dout.writeUTF(ara.get(i).name);
                        dout.writeUTF(String.valueOf(ara.get(i).id));
                    }
                    dout.flush();
                    System.out.println("Voter list sent");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            //add new candidate
            else if(str.equals("addCandidate")){
                try {
                    String name=din.readUTF();
                    String password=din.readUTF();
                    String post=din.readUTF();
                    addCandidate(name,post,password);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            //set poll starting and finishing time
            else if(str.equals("setPollTime")){
                try{
                    String startString=din.readUTF();
                    String endString=din.readUTF();
                    int start=Integer.parseInt(startString);
                    int end=Integer.parseInt(endString);
                    setPollTime(start,end);
                }catch(Exception ex){
                    System.out.println("set Poll time denied");
                }
            }

            //delete a candidate
            else if(str.equals("deleteCandidate")){
                try{
                    String idString=din.readUTF();
                    int id=Integer.parseInt(idString);
                    boolean flag=deleteCandidate(id);
                    dout.writeUTF(String.valueOf(flag));
                    dout.flush();
                }catch (Exception ex){
                    System.out.println("Delete Candidate denied");
                }
            }

            //add new voter
            else if(str.equals("addVoter")){
                try{
                    String name=din.readUTF();
                    String password=din.readUTF();
                    addVoter(name,password);
                }catch (Exception ex){
                    System.out.println("Add voter request invalid");
                }
            }

            //sends candidate list as string by string
            else if(str.equals("candidateList")){
                ArrayList<Candidate> ara=sendCandidateList();
                System.out.println("Ok candidate list");
                try {
                    dout.writeUTF(String.valueOf(ara.size()));
                    dout.flush();
                    for(int i=0;i<ara.size();i++){
                        dout.writeUTF(ara.get(i).name);
                        dout.writeUTF(String.valueOf(ara.get(i).id));
                        dout.writeUTF(ara.get(i).post);
                        System.out.println();
                    }
                    dout.flush();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            //sends voter info
            else if(str.equals("voterDetails")){
                try {
                    String idString = din.readUTF();
                    int id=Integer.parseInt(idString);
                    Voter voter=sendVoterDetails(id);
                    dout.writeUTF(voter.name);
                    dout.writeUTF(String.valueOf(voter.id));
                } catch (IOException e) {
                    System.out.println("Sorry voter");
                }
            }

            //sends candidate info
            else if(str.equals("candidateDetails")){
                try {
                    String idString = din.readUTF();
                    int id = Integer.parseInt(idString);
                    Candidate candidate = sendCandidateDetails(id);
                    dout.writeUTF(candidate.name);
                    dout.writeUTF(String.valueOf(candidate.id));
                    dout.writeUTF(candidate.post);
                    dout.flush();
                    System.out.println("Sent");
                } catch (Exception e) {
                    System.out.println("Sorry Candidate Details");
                }
            }

            //check if voter is allowed to vote
            else if(str.equals("validVoter")){
                try {
                    String voterId=din.readUTF();
                    boolean flag=validVoter(Integer.parseInt(voterId));
                    dout.writeUTF(String.valueOf(flag));
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }

            //take vote
            else if(str.equals("giveVote")){
                try {
                    String voterId=din.readUTF();
                    String id1=din.readUTF();
                    String id2=din.readUTF();
                    System.out.println(Integer.parseInt(id1));
                    System.out.println(Integer.parseInt(id2));
                    countVote(Integer.parseInt(id1));
                    countVote(Integer.parseInt(id2));
                    blockVoter(Integer.parseInt(voterId));
                } catch (IOException e) {
                    System.out.println("Sorry Count vote");
                }

            }

            //edit voter by id
            else if(str.equals("editVoter")){
                try{
                    String id=din.readUTF();
                    String name=din.readUTF();
                    boolean flag=editVoter(Integer.parseInt(id),name);
                    dout.writeUTF(String.valueOf(flag));
                }catch(Exception ex){
                    System.out.println("Edit voter error");
                }
            }

            //edit candidate by id
            else if(str.equals("editCandidate")){
                try{
                    String id=din.readUTF();
                    String name=din.readUTF();
                    boolean flag=editCandidate(Integer.parseInt(id),name);
                    dout.writeUTF(String.valueOf(flag));
                    System.out.println(flag);
                }catch(Exception ex){
                    System.out.println("Edit Candidate error");
                }
            }

            //send final result string by string
            else if(str.equals("finalResult")){

                ArrayList<Result>ara=null;
                ara=getResult();
                int size=resultSize();
                System.out.println(size);
                try {
                    //dout.close();
                    dout.writeUTF(String.valueOf(size));
                    dout.flush();
                    System.out.println("Result got");
                    for(int i=0;i<size;i++){
                        String name=ara.get(i).name;
                        dout.writeUTF(name);
                        String id=String.valueOf(ara.get(i).id);
                        dout.writeUTF(id);
                        String post=ara.get(i).post;
                        dout.writeUTF(post);
                        String vote=String.valueOf(ara.get(i).vote);
                        dout.writeUTF(vote);
                        dout.flush();
                        System.out.println(name);
                        System.out.println(id);
                        System.out.println(post);
                        System.out.println(vote);
                    }

                    System.out.println("Result got");
                } catch (Exception e) {
                    System.out.println("Result sending error");
                }

            }

            //check if vote is running or finished or yet to start
            else if(str.equals("voteTimeCheck")){
                int flag=checkVoteTimeStatus();
                try {
                    dout.writeUTF(String.valueOf(flag));
                    dout.flush();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }
    }
}
