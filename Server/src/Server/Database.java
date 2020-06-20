/*
Author : Rezaul Karim
2nd year student
Roll : 44
Dhaka University
 */

package Server;

import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.*;

public class Database {
    private static int startTime;
    private static int endTime;
    private int currentTime;
    private static String name;
    private static int id;
    private static int voteStatus;
    private static String password;
    private static int voteTimeStatus=0;
    private int logInStatus=0;

    private static ArrayList<Voter> voterList=new ArrayList<Voter>();
    private static ArrayList<Candidate> candidateList=new ArrayList<Candidate>();
    private static ArrayList<Result> resultList=new ArrayList<Result>();
    private static ArrayList<Admin> adminList=new ArrayList<Admin>();

    //set user's login status
    void setLogInStatus(int x){
        logInStatus=x;
    }

    //check vote time
    public int  checkVoteTimeStatus(){
        Calendar calendar=Calendar.getInstance();
        Date date=calendar.getTime();
        this.currentTime=date.getHours();
        if(startTime<=currentTime && endTime>currentTime)
            voteTimeStatus = 1;
        else if(endTime<currentTime)
            voteTimeStatus=2;
        else voteTimeStatus=0;
        System.out.println(startTime);
        System.out.println(endTime);
        System.out.println(currentTime);
        return voteTimeStatus;
    }

    //read all data from all files
    public static void readFileFromFile(){
        File file = null;
        Scanner input = null;
        try{
            file=new File("voterDetails.txt");
            input=new Scanner(file);
        }catch(Exception ex){

        }
        while(input.hasNextLine()){
            String idString =input.nextLine();
            name=input.nextLine();
            password=input.nextLine();
            id=Integer.parseInt(idString);
            String vote=input.nextLine();
            voteStatus=Integer.parseInt(vote);
            Voter voter=new Voter(id,name,password,voteStatus);
            voterList.add(voter);
        }
        input.close();


        try{
            file=new File("adminDetails.txt");
            input=new Scanner(file);
        }catch(Exception ex){
            System.out.println("");
        }
        while(input.hasNextLine()){
            password=input.nextLine();
            Admin admin=new Admin(password);
            adminList.add(admin);
        }
        input.close();
        try{
            file=new File("candidateDetails.txt");
            input=new Scanner(file);
        }catch(Exception ex){
            System.out.println("");
        }
        while(input.hasNextLine()){
            String idString =input.nextLine();
            name=input.nextLine();
            password=input.nextLine();
            id=Integer.parseInt(idString);
            String voteString=input.nextLine();
            String post=input.nextLine();
            int vote=Integer.parseInt(voteString);
            Candidate candidate=new Candidate(id,name,post,vote,password);
            candidateList.add(candidate);
        }
        input.close();
    }

    //write voters' data to file
    public void writeInVoterFile(){
        Iterator<Voter>it=voterList.iterator();
        FileWriter file;

        Voter voter;
        try {
            file=new FileWriter("voterDetails.txt");
            PrintWriter pw = new PrintWriter(file);

            it=voterList.iterator();
            for(int i=0;i<voterList.size();i++){
                voter=it.next();
                id=voter.id;
                name=voter.name;
                password=voter.password;
                voteStatus=voter.voteStatus;
                pw.println(id);
                pw.println(name);
                pw.println(password);
                pw.println(voteStatus);

            }
            pw.close();
        }catch(Exception ex){
            System.out.println("Not written");
        }
    }

    //write Candidates' data to file
    public  void writeInCandidateFile(){
        Iterator<Candidate>it=candidateList.iterator();
        FileWriter file;
        Candidate candidate;
        try{
            file=new FileWriter("candidateDetails.txt");
            PrintWriter pw = new PrintWriter(file);
            it=candidateList.iterator();
            for(int i=0;i<candidateList.size();i++){
                candidate=it.next();
                id=candidate.id;
                name=candidate.name;
                String post=candidate.post;
                password=candidate.password;
                int vote=candidate.vote;
                pw.println(id);
                pw.println(name);
                pw.println(password);
                pw.println(vote);
                pw.println(post);
            }
            pw.close();
        }catch(Exception ex){
            System.out.println("Candidate is not added");
        }
    }

    //permit to login voters and Candidates
    public boolean logInAccess(int id,String password, String clientType){//login confrimation
        if(clientType.equals("Voter")){ //if user is voter here he goes
            System.out.println("Voter here");
            Iterator<Voter> iterator=voterList.iterator();
            for(int i=0;i<voterList.size();i++){
                Voter voter=iterator.next();
                if(voter.id==id){
                    System.out.println("Voter Found");
                    if(voter.password.equals(password))
                        return true;
                    else return false;
                }
            }
        }
        else if(clientType.equals("Candidate")){//if user is candidate here he goes
            Iterator<Candidate> iterator=candidateList.iterator();
            for(int i=0;i<voterList.size();i++){
                Candidate candidate=iterator.next();
                if(candidate.id==id){
                    if(candidate.password.equals(password))
                        return true;
                    else return false;
                }
            }
        }
        return false;
    }

    //permit admin to login
    public boolean logInAccess(String password){
        Iterator<Admin>iterator=adminList.iterator();
        Admin admin=iterator.next();
        if(admin.password.equals(password))
            return true;
        else return false;
    }

    //returns array list containing voter type objects
    public ArrayList<Voter> sendVoterList(){
        return voterList;
    }

    //returns arrylist containing candidate type class
    public ArrayList<Candidate>sendCandidateList(){
        return candidateList;
    }

    //returns voter type object
    public Voter sendVoterDetails(int id){
        Iterator<Voter> iterator=voterList.iterator();
        for(int i=0;i<voterList.size();i++){
            Voter voter=iterator.next();
            if(voter.id==id){
                return voter;
            }
        }
        return null;
    }

    //returns candidate type object
    public Candidate sendCandidateDetails(int id){
        Iterator<Candidate> iterator=candidateList.iterator();
        for(int i=0;i<voterList.size();i++){
            Candidate candidate=iterator.next();
            if(candidate.id==id){
                return candidate;
            }
        }
        return null;
    }

    //adds new voter
    public void addVoter (String name,String password){
        int id=voterList.size()+1;
        Voter voter=new Voter(id,name,password,0);
        voterList.add(voter);
        /*FileWriter file;
        System.out.println("Voter is added");
        try {
            file=new FileWriter("voterDetails.txt");
            PrintWriter pw = new PrintWriter(file);
            Iterator<Voter>it=voterList.iterator();
            for(int i=0;i<voterList.size();i++){
                voter=it.next();
                id=voter.id;
                name=voter.name;
                password=voter.password;
                //file.write(id+"\n"+name+"\n"+password+"\n");
                pw.println(id);
                pw.println(name);
                pw.println(password);
            }
            pw.close();
        }catch(Exception ex){
            System.out.println("Not written");
        }

         */
    }


    //add new candidate
    public void addCandidate(String name,String post,String password){
        System.out.println("Candidate is here");
        int id=candidateList.size()+1;
        Candidate candidate=new Candidate(id,name,post,0,password);
        candidateList.add(candidate);
        System.out.println("Candidate");
        /*FileWriter file;
        try{
            file=new FileWriter("candidateDetails.txt");
            PrintWriter pw = new PrintWriter(file);
            Iterator<Candidate>it=candidateList.iterator();
            for(int i=0;i<candidateList.size();i++){
                candidate=it.next();
                id=candidate.id;
                name=candidate.name;
                post=candidate.post;
                password=candidate.password;
                int vote=candidate.vote;
                pw.println(id);
                pw.println(name);
                pw.println(password);
                pw.println(vote);
                pw.println(post);
            }
            pw.close();
        }catch(Exception ex){
            System.out.println("Candidate is not added");
        }

         */

    }

    //delete exiting candidate
    public boolean deleteCandidate(int id){
        Iterator<Candidate> it=candidateList.iterator();
        boolean found=false;
        for (int i=0;i<candidateList.size();i++){
            Candidate candidate=it.next();
            if(candidate.id==id){
                it.remove();
                System.out.println("Found Delete Candidate");
                found=true;
                break;
            }
        }
        if(!found)
            return false;
        /*FileWriter file;
        Candidate candidate;
        try{
            file=new FileWriter("candidateDetails.txt");
            PrintWriter pw = new PrintWriter(file);
            it=candidateList.iterator();
            for(int i=0;i<candidateList.size();i++){
                candidate=it.next();
                id=candidate.id;
                name=candidate.name;
                String post=candidate.post;
                password=candidate.password;
                int vote=candidate.vote;
                pw.println(id);
                pw.println(name);
                pw.println(password);
                pw.println(vote);
                pw.println(post);
            }
            pw.close();
        }catch(Exception ex){
            System.out.println("Candidate is not added");
        }
        */
        return true;
    }

    //change old password
    public boolean changePassword(int id,String oldPassword,String newPassword){
            System.out.println("Ok");
            Iterator<Voter> it=voterList.iterator();
            for(int i=0;i<voterList.size();i++){
                Voter voter=it.next();
                if(voter.id==id){
                    if(voter.password.equals(oldPassword)) {
                        voter.password = newPassword;
                        System.out.println("Done");
                        break;
                    }
                    else
                        return false;
                }
            }
           /* FileWriter file;

            Voter voter;
            try {
                file=new FileWriter("voterDetails.txt");
                PrintWriter pw = new PrintWriter(file);

                it=voterList.iterator();
                for(int i=0;i<voterList.size();i++){
                    voter=it.next();
                    id=voter.id;
                   //file.write(id+"\n");
                    name=voter.name;
                    //file.write(name+"\n");
                    password=voter.password;
                    //file.write(password+"\n");
                    pw.println(id);
                    pw.println(name);
                    pw.println(password);

                }
                pw.close();
            }catch(Exception ex){
                System.out.println("Not written");
                return false;
            }

            */
        return true;

    }

    //set poll starting and ending time
    public void setPollTime(int start,int end){
        this.startTime=start;
        this.endTime=end;

    }

    //returns candidate's number
    public int resultSize(){
        return candidateList.size();
    }

    //returns final result
    public  ArrayList<Result> getResult() {
        if(true){
            Candidate candidate;
            Iterator<Candidate>it=candidateList.iterator();
            for(int i=0;i<candidateList.size();i++){
                candidate=it.next();
                Result result=new Result(candidate.id,candidate.name,candidate.vote,candidate.post);
                resultList.add(result);
            }
            return resultList;

        }
        return null;
    }

    //permits voters to vote
    public boolean validVoter(int id){
        for(int i=0;i<voterList.size();i++){
            if(voterList.get(i).id==id){
                if(voterList.get(i).voteStatus==1)
                    return false;
                else return true;
            }
        }
        return false;
    }

    //make a voter as vote given
    public  void blockVoter(int id){
        for(int i=0;i<voterList.size();i++){
            if(voterList.get(i).id==id){
                voterList.get(i).voteStatus=1;
                return;
            }
        }
    }

    //count vote from voters
    public  void countVote(int id){
        Iterator<Candidate>it=candidateList.iterator();
        Candidate candidate;
        for(int i=0;i<candidateList.size();i++){
            candidate=it.next();
            if(candidate.id==id){
                candidate.vote+=1;
                return;
            }
        }
    }

    //edit voter name by id number
    public boolean editVoter(int id,String name){
        Iterator<Voter> it=voterList.iterator();
        for(int i=0;i<voterList.size();i++){
            Voter voter=it.next();
            if(voter.id==id){
                voter.name=name;
                writeInVoterFile();
                return true;
            }
        }
        return false;
    }

    //edit candidate name by id
    public boolean editCandidate(int id,String name){
        Iterator<Candidate> it=candidateList.iterator();
        for(int i=0;i<voterList.size();i++){
            Candidate candidate=it.next();
            if(candidate.id==id){
                candidate.name=name;
                writeInCandidateFile();
                return true;
            }
        }
        return false;
    }
}
