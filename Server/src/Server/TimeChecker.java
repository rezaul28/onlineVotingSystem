/*
Author : Rezaul Karim
2nd year student
Roll : 44
Dhaka University
 */

package Server;

public class TimeChecker extends Database implements Runnable {
    @Override
    public void run() {
        while (true){

            //checkVoteTimeStatus(); //call this method from Database class and check voting time
            try {
                Thread.sleep(100); //make sleep this thread for 10 millisecond
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
