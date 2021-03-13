import java.io.*; 
import java.text.*; 
import java.util.*; 
import java.net.*; 

public class Socketserver
{
  public static void main(String args[])
 {
    try
    {
    ServerSocket ss = new ServerSocket(5056);
    System.out.println("Server is wating"); 
    while(true)
    {
    Socket s=ss.accept();
    System.out.println("connection done!"); 
    DataInputStream dis=new DataInputStream(s.getInputStream()); 
    DataOutputStream dos=new DataOutputStream(s.getOutputStream());
    Thread t=new Multithread(s,dis,dos);
    t.start();

    }
    }
     catch (Exception e)
    {
        //TODO: handle exception
    }

    

 }

}
class Multithread extends Thread
{
    private Socket s;
    private DataInputStream dis;
    private DataOutputStream dos;
    Multithread(Socket s,DataInputStream dis,DataOutputStream dos)
    {
        this.s=s;
        this.dos=dos;
        this.dis=dis;
    }
    public void run()
    {

        while(true)
        {
    try{
        String s1,s2;
        s1=dis.readUTF();
        System.out.println(s1);
        if(s1.equals("over")==true)
        {
            this.s.close();
            this.dis.close();
            this.dos.close();
            break;
        }
        dos.writeUTF(s1);
        
     }
     catch (IOException e) 
     { 
        e.printStackTrace(); 
       

     }

    }
}
}

