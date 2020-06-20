/*
Author : Rezaul Karim
2nd year student
Roll : 44
Dhaka University
 */

package Server;

import java.io.Serializable;

public class Voter implements Serializable {
    public String name;
    public String password;
    public int voteStatus;
    public int id;

    //constructor
    Voter(int id,String name,String password,int voteStatus){
        this.name=name;
        this.id=id;
        this.password=password;
        this.voteStatus = voteStatus;
    }
}
