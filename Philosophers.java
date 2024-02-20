/**
 * Philosopher.java
 *
 * This class represents each philosopher thread.
 * Philosophers alternate between eating and thinking.
 *
 */


public class Philosophers implements Runnable
{
  DiningServer server;
  int  phiNum;
  public Philosophers (DiningServer server, int phiNum){
    this.server= server;
    this.phiNum= phiNum;
  }
  public void eating(int phinum){
    long start = System.currentTimeMillis();
    int time = (int) (5 * Math.random() );
    try{
      Thread.sleep(time*1000);
      System.out.println("Philosopher "+phiNum+ " eat ms= "+(System.currentTimeMillis()-start));
    }
    catch (InterruptedException e){

    }  
  }
  @Override
  public void run (){
    while(true){
      server.takeForks(phiNum);
      eating(phiNum);
      server.returnForks(phiNum);

    }
  }

}
