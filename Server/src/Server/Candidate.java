/*
Author : Rezaul Karim
2nd year student
Roll : 44
Dhaka University
 */

package Server;

import java.io.Serializable;

public class Candidate implements Serializable {
    public String name;
    public String password;
    public int id;
    public int vote;
    public String post;

    //constructor
    Candidate(int id, String name, String post,int vote,String password){
        this.id=id;
        this.name=name;
        this.post=post;
        this.vote=vote;
        this.password=password;
    }
}
