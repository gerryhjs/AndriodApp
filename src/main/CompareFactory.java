package main;

import Saika_Output.ExStreamer;
import calc_map.Diagram;
import calc_map.Vertex;
import file_core.CodeFile;
import file_core.FolderScanner;
import graphViz.GraphVizTest;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Saika on 2019/1/12.
 */
public class CompareFactory {
    private DecimalFormat df = new DecimalFormat("#.00");

    private double check=0.6;
    private double LOW_DOWN=1;
    private boolean createDiagram=true;
    private boolean byLines=false;
    private boolean bySize=false;

    public void setCreateDiagram(boolean createDiagram) {
        this.createDiagram = createDiagram;
    }
    public void setComment(boolean comment) {
        this.comment = comment;
    }

    private boolean comment=true;
    private ArrayList<String> suffixList=new ArrayList<>();

    public void setSuffixList(ArrayList<String> suffixList)
    {
        this.suffixList=suffixList;
    }
    public void setCheck(double check) {
        this.check = check;
    }

    public void setLOW_DOWN(double LOW_DOWN) {
        this.LOW_DOWN = LOW_DOWN;
    }

    public void initSetting()
    {
        check=0.6;
        LOW_DOWN=1;
        createDiagram=true;
        byLines=false;
        bySize=false;
        comment=true;
    }
    @Test
    public void test()
    {
        createDiagram=false;
        //bySize=true;
        System.out.println(test_compare2());
    }

    public String test_compare()
    {

        return (compare("/media/hjs/KINGSTON/check/",0,1));
    }

    public String test_compare2(){
        int projectSize = 2;
        String[] paths = new String[projectSize];
//        for (int i = 0; i < projectSize; i++)
//            paths[i] = selectFolder();
        paths[0]="/media/hjs/KINGSTON/check/jsp-server";
        paths[1]="/media/hjs/KINGSTON/check/jsp-lab";
        double result = 0;
        try {
            result = compare(paths[0], paths[1], 1d);
        } catch (IOException ignored) {

        }
        System.out.println();
        System.out.println("Result->" + df.format(result * 100) + "%");
        return (df.format(result * 100) + "%");
    }


    public double compare(String path0, String path1,double weight_edge) throws IOException //1-1
    {
        int projectSize = 2;
        String[] paths = new String[projectSize];
        paths[0] = path0;
        paths[1] = path1;
        Diagram[] projects = new Diagram[projectSize];
        for (int i = 0; i < projectSize; i++) {
            if (createDiagram)
                check_draw(paths[i]);
            projects[i] = check(paths[i]);
        }
        return compareDiagram(projects[0],projects[1],weight_edge);
    }
    public double compare2(String path1,String path2,double weight_edge)//1-N
    {
        double similar = 0;
        String[] paths=new File(path2).list();
        if (paths==null) return 0;
        for(String Scanner2:paths) {
            try {
                if (createDiagram)
                    check_draw(Scanner2);
                similar = Math.max(compare(path1, Scanner2, weight_edge),similar);
            } catch (IOException ignored) {

            }
        }
        return similar;
    }


    public String compare(String path,double threshold,double weight_edge)//N
    {
        return compare(path,path,threshold,weight_edge);
    }

    public String compare(String path1,String path2,double threshold,double weight_edge)//N-N
    {
        if (!path1.endsWith("File.separator")) path1 += File.separator;
        if (!path2.endsWith("File.separator")) path2 += File.separator;
        String[] paths1=new File(path1).list();
        String[] paths2=new File(path2).list();
        StringBuilder result= new StringBuilder();
        if (paths1==null) return null;
        if (paths2==null) return null;
        for(String Scanner1:paths1)
        {
            if (createDiagram) {
                try {
                    check_draw(Scanner1);
                } catch (IOException ignored) {
                    continue;
                }
            }

            for(String Scanner2:paths2) {
                double similar = 0;
                if (!Scanner1.equals(Scanner2)) {
                    try {
                        similar = compare(path1+Scanner1, path2+Scanner2, weight_edge);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    if (similar > threshold)
                        result.append(Scanner1).append("-").append(Scanner2).append(":").append(similar).append(";<br/>");
                }
            }
        }
        return result.toString();
    }

    public double compareDiagram(Diagram m1, Diagram m2,double weight_edge)
    {
        //思路：匹配点 比较边权值  或者比较边后匹配点？
        // 得出结论 单向匹配z
        Set<Vertex> v1=m1.getVertexList();
        Set<Vertex> v2=m2.getVertexList();
        //匹配点
        Map<Vertex,Vertex> likely=new HashMap<>();
        double sumSimilar=0;
        int index=0;
        double weightIndex=0;
        String[][] values=new String[v1.size()+1][4];
        for (Vertex Scanner:v1)
        {
            index++;


            System.out.println("Looking For->"+Scanner.info() +"====="+index+"/"+v1.size());
            Vertex result=null;
            double max_similar=0;

            for (Vertex Scanner2:v2) {
                double this_similar = Scanner.similar(Scanner2);
                if (this_similar > max_similar) {
                    if (this_similar>check)
                    result = Scanner2;
                    max_similar = this_similar;
                    if (max_similar == 1) break;
                }
            }
            if (result!=null) {
                System.out.println("Result:" + result.info() + "-" + df
                        .format(max_similar * 100) + "%");
            }
            else
            {
                System.out.println("Result:NULL");
            }
            likely.put(Scanner, result);
            values[index - 1][0] = Scanner.info();
            if (result != null) {
                values[index - 1][1] = result.info();
            }
            else
            {
                values[index - 1][1] ="NULL";
            }

            values[index - 1][2] = df.format(max_similar * 100) + "%";

            if (byLines) weightIndex+=Scanner.getCf().getLines();
            if (bySize) weightIndex+=Scanner.getCf().getSize();
            weightIndex++;

            if (byLines) sumSimilar+=Scanner.getCf().getLines()*max_similar;
            if (bySize) sumSimilar+=Scanner.getCf().getSize()*max_similar;
            sumSimilar += max_similar;
                //sumSimilar+=Math.pow(max_similar,2);
        }
        if (weightIndex<=0) weightIndex=1;
        double similar1=sumSimilar/weightIndex;
        double sumSimilar2=0;

        index=0;
        weightIndex=0;
        double max_similar2;
        for (Vertex Scanner:v1)
        {
            index++;
            Vertex like=likely.get(Scanner);
            try {
                System.out.println(Scanner.info() + "=" + like.info());
            }catch (Exception ignored) {

            }
            if (like!=null)
            {
                Set relates=Scanner.getTo();
                double sum=0;
                for (Object Scanner2:relates)
                {
                    try {
                        double similar;
                        Vertex v = (Vertex) Scanner2;
                        double distance1 = m1.getDistance(Scanner,v,LOW_DOWN);
                        Vertex like2 = likely.get(v);
                        double distance2=m2.getDistance(like,like2,LOW_DOWN);
                        System.out.println(Scanner.info() +"_"+distance1+"_"+distance2);
                        if (distance2==-1) similar=0;
                        else
                        {
                            double diff=Math.abs(distance1-distance2);
                            double sumDis=distance1+distance2;
                            similar=1-(diff)/(sumDis);

//                            if (diff<=sumDis*0.5+1) similar=0.6;
//                            if (diff<=sumDis*0.25+1) similar=0.7;
//                            if (diff<=sumDis*0.1+1) similar=0.8;
//                            if (diff<=sumDis*0.03+1) similar=0.9;
//                            if (diff<=sumDis*0.01+1) similar=1;

                            System.out.println("S="+similar);

                        }
                        sum+=similar;//*(Scanner.similar(like));//+((Vertex)Scanner2).similar(like2)

                    }catch (Exception e)
                    {
                        System.out.println(e);
                    }
                }
//                double edge_MaxS=1d;
                if ((Scanner.getTo().size()>0)||(like.getTo().size()>0))
                {
                    if (relates.size()>0) max_similar2=sum/relates.size();
                    else max_similar2=0;
//                    if (max_similar>edge_MaxS)
//                        max_similar=edge_MaxS;
                    System.out.println(max_similar2);
                    values[index - 1][3] = df.format(max_similar2 * 100) + "%";

                    if (byLines)  weightIndex+=Scanner.getCf().getLines();
                    if (bySize)  weightIndex+=Scanner.getCf().getSize();
                    weightIndex++;

                    if (byLines) sumSimilar2+=Scanner.getCf().getLines()*max_similar2;
                    if (bySize) sumSimilar2+=Scanner.getCf().getSize()*max_similar2;
                    sumSimilar2+=max_similar2;
                }
                else
                {
                    values[index - 1][3] = "NaN";

                }

            }
//            else
//            {
//                //nan++;
//            }
        }

       // System.out.println(attrs.size());
    //    System.out.println(sumSimilar2+"_"+weightIndex);

        double similar2;
        if (weightIndex<=0) weightIndex=1;
         //double sizes=v1.size();
            similar2=sumSimilar2/(weightIndex);
//        else
//            similar2=0;

        if (similar2>1) similar2=1;

        System.out.println("SS:"+similar1+"-|-"+similar2);
        double result=(similar1+similar2*weight_edge)/(1+weight_edge);
//        values[v1.size()][2]= String.valueOf(similar1);
//        values[v1.size()][3]= String.valueOf(similar2);
//        values[v1.size()][4]= String.valueOf(result);

        ArrayList<String> attrs=new ArrayList<>();
        System.out.println(similar1+"_"+similar2);
        attrs.add("origin");
        attrs.add("similar_target");
        attrs.add("Vertex_Similar");
        attrs.add("Edge_Similar");
      //  attrs.add("Similar");

        new ExStreamer("S:"+result+"["+m1.getName()+"-"+m2.getName()+"].xls").excelOut(v1.size(),attrs,values);

        return result;
    }
    private Diagram check(String path) throws IOException {
        String[] temp=path.split("/");
        String name=temp[temp.length-1];
        Diagram m=new Diagram(name);
        FolderScanner fs=new FolderScanner();

        if (suffixList.size()==0) suffixList.add("java");

        fs.setSuffixList(suffixList);
        fs.setJavaDictionary("/home/hjs/code_compare/src/dictionary");
        if (!comment) fs.disableComment();

        fs.find(path,1);
        for (CodeFile Scanner:fs.getCodeFiles()) {
            Vertex v=new Vertex(Scanner);
            m.addVertex(v);
            Scanner.setIndex(0);
        }
        for (CodeFile Scanner:fs.getCodeFiles()) {
            String nowPackage=Scanner.getPackageName();
            System.out.println("["+nowPackage+"]"+Scanner.getFileName());
            String code=Scanner.getCode();
            for (CodeFile Scanner2:fs.getCodeFiles()) {
                int times= scanFolder(Scanner, code, Scanner2);;
                if (times>0) {
                    double weight=0.25d/(Math.sqrt(times))+1;//距离权重 1-1.25
                    m.getVertex(Scanner.getFileName()).Relate(m.getVertex(Scanner2.getFileName()),weight);
               //     System.out.println("MS"+m.getVertex(Scanner.getFileName()).getTo().size());
                }
            }
            System.out.println();
        }
        return m;

    }

    private int scanFolder(CodeFile Scanner, String code, CodeFile Scanner2) {
        String target="[^a-zA-Z0-9]"+Scanner2.getName()+"[^a-zA-Z0-9]";
        if (Scanner.getName().equals(Scanner2.getName()))
            target=Scanner2.getName();
        int times=appearNumber(code,target);
        if (Scanner.getName().equals(Scanner2.getName()))
        {
            times=0;
        }
        return times;
    }


    private void check_draw(String path) throws IOException {

        ArrayList<String> Edges=new ArrayList<>();
      //  ArrayList<String> Edges2=new ArrayList<>();
        FolderScanner fs=new FolderScanner();
        fs.find(path,1);
        for (CodeFile Scanner:fs.getCodeFiles()) {
            Scanner.setIndex(0);
        }
        for (CodeFile Scanner:fs.getCodeFiles()) {
            String nowPackage=Scanner.getPackageName();
            System.out.println("["+nowPackage+"]"+Scanner.getFileName());
            String code=Scanner.getCode();
            for (CodeFile Scanner2:fs.getCodeFiles()) {
                int times=scanFolder(Scanner, code, Scanner2);
                if (times>0) {
                    double weight=0.25d/(Math.sqrt(times))+1;//权重 1-1.25
                    Edges.add("\"" + Scanner.getFileName() + "\"" + "->" +
                            "\"" + Scanner2.getFileName() + "\"" + " " +
                            "[label=\"" + weight + "\"]");
//                    Edges2.add("\"" + Scanner.getFileName() + "\"" + "->" +
//                            "\"" + Scanner2.getFileName() + "\"" + " " +
//                            "[label=\"" + type + "\"]");
                }
            }
            System.out.println();
        }



        String[] temp=path.split("/");
        String name=temp[temp.length-1];
        GraphVizTest gvt=new GraphVizTest();
        gvt.draw(Edges,"Diagram_"+name);
//        GraphVizTest gvt2=new GraphVizTest();
//        gvt2.draw(Edges2,"relation_"+i);

    }


    //    String type="";
//                if ((code.indexOf("new " + Scanner2.getName() + "[^a-zA-Z0-9]") > 0)) {
//                    Scanner.create(Scanner2);
//                    type="CREATE";
//                   //times*=5;
//                }
//                else if ((code.indexOf("[^a-zA-Z0-9]"+Scanner2.getName()  + ".") > 0)
//                        ||(!Scanner2.getFileName().equals(Scanner.getFileName()))) {
//                    type="use";
//                    Scanner.use(Scanner2);
//                    //times*=2;
//                }
//                else if ((times > 0))
//                {
//                    type="relate";
//                    Scanner.relate(Scanner2);
//                }



//    private static String selectFolder()
//    {
//        JFileChooser fileChooser = new JFileChooser("");
//        fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
//        int returnVal = fileChooser.showOpenDialog(fileChooser);
//        if(returnVal == JFileChooser.APPROVE_OPTION){
//            return  fileChooser.getSelectedFile().getAbsolutePath();
//        }
//        return null;
//    }

    public static int appearNumber(String srcText, String findText) {
        int count = 0;
        Pattern p = Pattern.compile(findText);
        Matcher m = p.matcher(srcText);
        while (m.find()) {
            count++;
        }
        return count;
    }


    public void setByLines(boolean byLines) {
        this.byLines = byLines;
    }

    public void setBySize(boolean bySize) {
        this.bySize = bySize;
    }

    public void setSpace(boolean space) {
    }
}
