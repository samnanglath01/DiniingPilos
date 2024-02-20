/**
 * DiningServer.java
 *
 * This class contains the methods called by the  philosophers.
 *
 */

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class DiningServerImpl implements DiningServer{
   public enum State{thinking, hungry, eating};
   State[] state = new State[5];
   Condition[] self= new Condition[5]; 
   Lock lock = new ReentrantLock();
   public DiningServerImpl() {
    for (int i = 0; i < 5; i  ++) {
        self[i] = lock.newCondition();
    }
         for (int i =0;i<5;i++){
            state[i]=State.thinking;
            System.out.println("Philosopher "+i +" is thinking.");
         }
   }
   public void takeForks(int pnum) {
    lock.lock();
    state[pnum]=State.hungry;
    System.out.println("Philosopher "+ pnum +" is holding fork.");
    test(pnum);
    if(state[pnum]!=State.eating){
        try {
            self[pnum].await();
        } catch (InterruptedException e) {
            System.out.println("cannot do it, since philosopher "+pnum+" is waiting for forks.");
        }
    }
    lock.unlock();
   }
   public void returnForks(int pnum){
    lock.lock();
    state[pnum]=State.thinking;
    System.out.println("philosopher "+pnum+" is done eating and return forks also start to think again");
    test((pnum+4)%5);
    test((pnum+1)%5);
    lock.unlock();
   }
   public void test(int pnum){
    if ((state[(pnum+4)%5]!=State.eating)&&(state[pnum]==State.hungry)&&(state[(pnum+1)%5]!=State.eating)){
        state[pnum]=State.eating;
        System.out.println("Philosopher " + pnum + " is eating");
        self[pnum].signal();
    }
   } 
}
