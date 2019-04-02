import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;
import java.util.StringTokenizer;


public class Client {
	@SuppressWarnings("resource")
	public static  long getSize(File f) throws Exception{//ȡ���ļ���С
		long s=0;
		if (f.exists()) {
		FileInputStream fis = null;
		fis = new FileInputStream(f);
		s= fis.available();
		} else {
		f.createNewFile();
		System.out.println("file is not exist");
		s=0;
		}
		return s;
		}
	@SuppressWarnings("unused")
	public static void main(String args[]) 
	{
		Socket dataSocket = null;
		InetAddress i = null;
	System.out.println("----------------------------------------");
	try {
		  i = InetAddress.getLocalHost();
	} catch (UnknownHostException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	}
	System.out.println("client is open, the IP is:"+i);
	System.out.println("----------------------------------------");
	try {
		Scanner scanner=new Scanner(System.in);
		Socket socket= new Socket("192.168.78.94",8888);
		InputStream input=socket.getInputStream();
		OutputStream output=socket.getOutputStream();
		DataInputStream dis= new DataInputStream(input);
		DataOutputStream dos=new DataOutputStream(output);
		long rs=0;

		while (true) {
//			System.out.print("Please enter the instruction");
//			String s1 = scanner.nextLine();
//			dos.writeUTF(s1 + "\\r\\n");
            System.out.println(dis.readUTF());
		}
		/*
		socket.close();
		input.close();
		output.close();
		dis.close();
		dos.close();
		*/
		} catch (IOException e) {
		e.printStackTrace();
		}

	}
} 
