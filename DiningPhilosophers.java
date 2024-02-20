/**
 * DiningPhilosophers.java
 *
 * This program starts the dining philosophers problem.
 *
 */


public class DiningPhilosophers
{  
   public static void main(String args[])
   { int a =5;
     DiningServer server = new DiningServerImpl();
     Philosophers [] philoNum = new Philosophers [a];    
     for (int i = 0;i <= 4;i++)
     {
      philoNum[i]= new Philosophers (server, i);
     }
     for (int i =0;i<=4;i++)
     {
        Thread t =new Thread(philoNum[i]); 
        t.start();
     }
   }
}
