import java.io.*; 
import java.util.*; 
import java.net.*;
import java.security.cert.CertPathChecker; 
public class Clintchatapp
{
    public static void main(String args[])
    {
    try
    {
        
    

    InetAddress ip = InetAddress.getByName("localhost");
    Socket s=new Socket(ip,5056);
    System.out.println("clint is connected !");
    DataInputStream dis=new DataInputStream(s.getInputStream());
    DataOutputStream dos=new DataOutputStream(s.getOutputStream());
    Thread t1=new Sendmessage(s,dos);
    Thread t2=new Recevemessage(s,dis);
    t1.start();
    t2.start();
    }
 catch (Exception e)
{
    //TODO: handle exception
}
    }

}

class Sendmessage extends Thread
{
     Socket s;
     DataOutputStream dos;
     Sendmessage(Socket s,DataOutputStream dos)
     {
         this.s=s;
         this.dos=dos;
     }
     public void run()
     {
         try
         {
             Scanner sc=new  Scanner(System.in);
             String strg1="",strg2="";
             while(strg2.equals("over")==false)
             {
                 strg1=sc.nextLine();
                 strg2=sc.nextLine();
                 dos.writeUTF(strg1);
                 dos.writeUTF(strg2);
                 if(strg2=="over")
                 {
                    this.dos.close();
                    this.s.close();
                    break;
                 }
             }
             
         }
         catch (Exception e)
         {
             //TODO: handle exception
         }
     }

}

class Recevemessage extends Thread
{
     Socket s;
     DataInputStream dis;
     Recevemessage(Socket s,DataInputStream dis)
     {
         this.s=s;
         this.dis=dis;
     }
     public void run()
     {
         try
         {
            
             String strg1="",strg2="";
             while(true)
             {
                strg1=dis.readUTF();
                strg2=dis.readUTF();
                System.out.println(strg1);
                System.out.println(strg2);

             }
             
         }
         catch (Exception e)
         {
             //TODO: handle exception
         }
     }

}