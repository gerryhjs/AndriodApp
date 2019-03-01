package Servlet;


import main.Core;
import org.junit.Test;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;

@WebServlet("/PostRequestServlet")
public class PostRequestServlet extends HttpServlet {
	private DecimalFormat df = new DecimalFormat("#.00");

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {

		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		String input = request.getParameter("input");
		System.out.println("input=" + input);
		int mode=0;
		String path1="";
		String path2="";
		double threshold=0.6;
		double weight=1;
		try {
			mode = Integer.valueOf(getValue(input, "mode"));
			path1 = getValue(input, "file1");
			path2 = getValue(input, "file2");
			threshold = Double.parseDouble(getValue(input, "threshold"));
			weight = Double.parseDouble(getValue(input, "weight"));
		}catch (Exception e) {
			//System.out.println(e.toString());
		}
		/*对照表
		$("#code").val(
            "//以下为必填参数 \n" +
            "mode:{mode};//匹配模式\n " +
            "file1:{file1};//目标位置\n " +
            "file2:{file2};//除组匹配模式必填 样本位置\n " +
            "threshold:0.6;//除快速匹配模式必填 默认值0.6 筛选阈值,如果列出所有结果请填0 \n" +
            "weight:1;\n //结构权重 默认值1 仅用点匹配请输入point_only,仅用边匹配请输入edge_only\n" +
            "codeSuffix:java//多个参数用逗号隔开\n"+
            "//以下为开发参数 \n" +
            "makeDiagram:True//是否自动生成代码结构的有向图文件 默认true\n"+
            "checkThreshold:0.6; //点近似度匹配关联点的阈值,默认0.6 \n" +
            "lowIndex:1; //点距离累计的衰减因子,默认1 \n" +
            "byLines:False; //是否按照文件行数赋予代码文件不同的近似度权值\n" +
            "bySize:False; //是否按照文件大小赋予代码文件不同的近似度权值,互斥byLines\n" +
            "removeComment:True;//是否筛筛选用注释\n" +
            "removeSpace:True;//是否禁用筛选空字段\n" +
            "//自定义匹配参数");
    }

		 */
		Core Core=new Core();
		try {
			String value=getValue(input,"codeSuffix");
			if (!value.equals(""))
				Core.setSuffixList(new ArrayList<>(Arrays.asList(value.split(","))));

		}
		catch (Exception ignored) {}
		try
		{
			String value=getValue(input,"makeDiagram");
			if (!value.equals(""))
				Core.setCreateDiagram(Boolean.parseBoolean(value));
		}
		catch (Exception ignored) {}
		try
		{
			String value=getValue(input,"checkThreshold");
			if (!value.equals(""))
				Core.setCheck(Double.parseDouble(value));
		}
		catch (Exception ignored) {}
		try
		{
			String value=getValue(input,"lowIndex");
			if (!value.equals(""))
				Core.setLOW_DOWN(Double.parseDouble(value));
		}
		catch (Exception ignored) {}
		try
		{
			String value=getValue(input,"byLines");
			if (!value.equals(""))
				Core.setByLines(Boolean.parseBoolean(value));
		}
		catch (Exception ignored) {}
		try
		{
			String value=getValue(input,"bySize");
			if (!value.equals(""))
				Core.setBySize(Boolean.parseBoolean(value));
		}
		catch (Exception ignored) {}
		try
		{
			String value=getValue(input,"removeComment");
			if (!value.equals(""))
				Core.setComment(Boolean.parseBoolean(value));
		}
		catch (Exception ignored) {}
//		try
//		{
//			String value=getValue(input,"removeSpace");
//			if (!value.equals(""))
//				Core.setSpace(Boolean.parseBoolean(value));
//		}
//		catch (Exception ignored) {}
		String reply="";
		System.out.println(mode+"_"+path1+"_"+path2+"_"+threshold+"_"+weight);


		if (mode==1)
		{
			reply= df.format(Core.compare(path1, path2, weight) * 100) + "%";
		}
		if (mode==2)
		{
			reply= String.valueOf(Core.compare(path1,threshold,weight));
		}
		if (mode==3)
		{
			reply= String.valueOf(Core.compare2(path1,path2,weight));
		}
		if (mode==4)
		{
			reply=String.valueOf(Core.compare(path1,path2,threshold,weight));
		}
		if (mode==0)
		{
			reply=String.valueOf(Core.test_compare());
		}
		//deal

		Core.initSetting();

		System.out.println("output=" + reply);

		out.write(reply);
		out.close();
	}


	@Test
	public void test()
	{
		String test="input=mode:{mode};file1:{file1};file2:{file2};threshold:0.6;weight:1;\n" +
				"smartCompare:True;sizeWeight:False;reduceIndex:Default";
		System.out.println(getValue(test,"threshold"));
		System.out.println(getValue(test,"score"));
	}
	private String getValue(String data, String attr)
	{
		try {
			int index1 = data.indexOf(attr);
			if (index1==-1) return "";
			String newData = data.substring(index1 + attr.length()+1);
			int index2 = newData.indexOf(";");
			if (index2==-1) return "";
			return newData.substring(0, index2);
		}
		catch (Exception e)
		{
			return "";
		}
	}
}
