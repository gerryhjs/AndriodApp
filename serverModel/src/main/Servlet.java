package main;



import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;


@WebServlet("/Servlet")
public class Servlet extends HttpServlet {


	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {

		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		String input = request.getParameter("input");
		String reply;
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		String key=input.replace("你", "").replace("我", "你").replace("么", "")
				.replace("吗", "").replace("呢", "").replace("？", "").replace("?", "");
		int asc=0;
		for (int i=0;i<key.length();i++)
		{
			asc+= key.indexOf(i);
		}
		if (asc%2==0)
			reply="我"+ key +"。";
		else
			reply="我不"+ key +"。";
		out.write(reply);
		out.close();
	}






}
