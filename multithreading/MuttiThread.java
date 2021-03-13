class Multithread extends Thread
{
    private int ar[]=new int[10];
    int x;
    Multithread(int z)
    {
        this.x=z;
    }
    public void run()
    {
        System.out.println("Rais "+x);
    }
}

public class MuttiThread 
{
    public static void main(String args[])
    {
        System.out.println("Siddhartha Rai");
        Thread t1=new Multithread(5);
        t1.start();
    }
}
