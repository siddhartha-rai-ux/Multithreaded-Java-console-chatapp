import java.io.*;
import java.net.*;
import java.util.*;
public class Socketclint
{
public static void main(String args[])
{
try
{
    InetAddress ip = InetAddress.getByName("localhost"); 
    Socket s=new Socket(ip,5056);
    System.out.println("Connectiondone!");
    Scanner sc = new Scanner(System.in); 
    DataInputStream dis=new DataInputStream(s.getInputStream());
    DataOutputStream dos=new DataOutputStream(s.getOutputStream());
    String strg="";
    while(strg.equals("over")==false)
    {
        strg=sc.nextLine();
      //  System.out.println(strg);
    dos.writeUTF(strg);
    strg=dis.readUTF();
    System.out.println(strg);

    }
    dos.writeUTF(strg);
    s.close();
    dis.close();
    dos.close();




    
} 
catch (Exception e)
{
    //TODO: handle exception
}

}

}