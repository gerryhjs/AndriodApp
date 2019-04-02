import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class ServerThread extends Thread{
	private Socket s;
	public static ArrayList<User> UserList;
	public String ServerPath="C:/!Server/Files";
	public ServerThread(Socket s)
	{
		this.s = s;
	}

	
public static String getTime(File f)
{
			Process ls_proc;
			try {
				ls_proc = Runtime.getRuntime().exec(   "cmd.exe /c dir " + f.getAbsolutePath() + " /tc");
				  InputStream is = ls_proc.getInputStream();  
	                BufferedReader br = new BufferedReader(new InputStreamReader(is));  
	                String str;  
	                int j = 0;  
	                while ((str = br.readLine()) != null) {  
	                  j++;  
	                  if (j == 6) { 
	                    return str.substring(0, 17);
	                  }
	              }
			}
			 catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return null;
}
/*
public boolean upload(File f)
{
	return true;
}
public boolean download(File f)
{
	return true;
}
*/
@SuppressWarnings("resource")
public static  long getSize(File f) throws Exception{//ȡ���ļ���С
long s=0;
if (f.exists()) {
FileInputStream fis = null;
fis = new FileInputStream(f);
s= fis.available();
} else {
f.createNewFile();
System.out.println("file does not exist");
s=0;
}
return s;
}
public static String printFile(File f)
{
	String ms="";
	if(f!=null){
        if(f.isDirectory())
        {
            File[] fileArray=f.listFiles();
                for (int i = 0; i < fileArray.length; i++) 
                {
                	try {
						ms=ms+ fileArray[i] +" Create time:"+getTime(fileArray[i])+" File size"+getSize(fileArray[i]) + "\r\n";
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
                }
        }
	}
	return ms;
}
	public static void ioIn(ArrayList<User> UserList) throws UnsupportedEncodingException, FileNotFoundException
	{
		File file=new File("user.txt");
		InputStreamReader read;

			read = new InputStreamReader(new  FileInputStream(file),"GBK");
			BufferedReader reader=new BufferedReader(read);
		try{
			int UserAmount = Integer.valueOf(reader.readLine());
			UserList =new ArrayList<User>();
			for (int i=1;i<=UserAmount;i++)
			{
				String ID=reader.readLine();
				String PassWord=reader.readLine();
				String Identify=reader.readLine();
				User it=new User(ID,PassWord,Identify);
				UserList.add(it);
			}
			reader.close();
		} catch(IOException e)
		{
			e.printStackTrace();
		}
	}
	
	public static void ioOut(ArrayList<User> UserList)
	{
		File file=new File("user.txt");
		try {
			Writer writer=new OutputStreamWriter(new FileOutputStream(file,false),"GBK");
			int UserAmount=0;
			for (@SuppressWarnings("unused") User scanner:UserList) UserAmount++;
			writer.write(String.valueOf(UserAmount)+"\r\n");
			for (User scanner:UserList)
			{
				writer.write(scanner.ID+"\r\n");
				writer.write(scanner.PassWord+"\r\n");
				writer.write(scanner.Identify+"\r\n");
			}
			writer.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void run() {
		long rs=(long) 0;
		System.out.println("----------------------------------------");
		System.out.println("Service-Terminal");
		System.out.println("----------------------------------------");
		
		UserList=new ArrayList<User>();
		User exUser=new User("Example","123456","Normal");
		User exUser2=new User("Example2","admin","Admin");
		UserList.add(exUser);
		UserList.add(exUser2);
		//ioOut(UserList);
		/*
				try {
					ioIn(UserList);
				} catch (UnsupportedEncodingException | FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
	
		*/
		/*
		for (User scanner:UserList)
		{
			System.out.println(scanner.ID);
		}
		*/
	    	InputStream input = null;
			try {
				input = s.getInputStream();
			} catch (IOException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
			OutputStream output=null;
			try {
				output = s.getOutputStream();
			} catch (IOException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}

			assert input != null;
			DataInputStream dis = new DataInputStream(input);
			DataOutputStream dos = new DataOutputStream(output);
			System.out.println("Connected client");

                try {
                    for (int i = 0; i <= 10; i++)
                        dos.writeUTF(String.valueOf(i));

                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    //e.printStackTrace();
                }
    }
}