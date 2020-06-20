/*
Author : Rezaul Karim
2nd year student
Roll : 44
Dhaka University
 */

package Server;

public class Writer extends Database implements Runnable {

    @Override
    public void run() {
        while(true) {
            writeInCandidateFile(); //write candidate info into file
            writeInVoterFile();//write voter info into file
            try {
                Thread.sleep(60000); // make this thread sleep 1 minute
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
