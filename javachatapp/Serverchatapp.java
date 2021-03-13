import java.io.*; 
import java.util.*; 
import java.net.*;
import java.security.cert.CertPathChecker; 
public class Serverchatapp
{
    static HashMap<String ,Multithread> mp=new HashMap<>();
    public static void main(String args[])
    {
        int i=0;
        String name;
        try
        {
            ServerSocket ss=new ServerSocket(5056);
            System.out.println("server is wating");
            while(true)
            {
                Socket s=ss.accept();
                System.out.println("connection done!");
                DataInputStream dis=new DataInputStream(s.getInputStream());
                DataOutputStream dos=new DataOutputStream(s.getOutputStream());
                name="clint"+i;
                i++;
                System.out.println(name);
                dos.writeUTF(name);
                dos.writeUTF("this is your name");
                Multithread mt= new Multithread(dis,dos,s,name,true); 
                mp.put(name,mt);
                Thread t=mt;
                t.start();
              

            }
            
        } catch (Exception e)
        {
            //TODO: handle exception
        }

    }
}
class Multithread extends Thread
{
    DataInputStream dis;
    DataOutputStream dos;
    Socket s;
    String name;
    boolean log;
    Multithread(DataInputStream dis,DataOutputStream dos,Socket s,String name,boolean log)
    {
        this.dis=dis;
        this.dos=dos;
        this.s=s;
        this.name=name;
        this.log=log;
    }
    public void run()
    {
        String s1,s2;
        DataOutputStream doss;
        try
        {
            while(true)
            {
                s1=dis.readUTF();
                s2=dis.readUTF();
                if(s2=="over")
                {
                    this.log=false;
                    this.s.close();
                    this.dis.close();
                    this.dos.close();
                    break;
                }
                if(Serverchatapp.mp.containsKey(s1))
                {
                    if(Serverchatapp.mp.get(s1).log)
                    {
                    doss=Serverchatapp.mp.get(s1).dos;
                    doss.writeUTF(this.name);
                    doss.writeUTF(s2);
                    }
                }

            }
        }
        catch (Exception e)
        {
            //TODO: handle exception
        }
    }
}