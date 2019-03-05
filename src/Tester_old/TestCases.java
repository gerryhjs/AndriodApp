//package Tester;
//
//import compare_core.CodeCompare;
//import compare_core.StringCompare;
//import file_core.CodeFile;
//import file_core.FolderScanner;
//import graphViz.GraphVizTest;
//import org.junit.Test;
//
//import javax.swing.*;
//import java.io.File;
//import java.io.IOException;
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.HashMap;
//import java.util.Set;
//import java.util.regex.Matcher;
//import java.util.regex.Pattern;
//
///**
// * Created by Saika on 2019/1/12.
// */
//public class TestCases {
//    @Test
//    public void test() throws IOException {
//        ArrayList<String> Edges=new ArrayList<>();
//        ArrayList<String> Edges2=new ArrayList<>();
//        FolderScanner fs=new FolderScanner();
//        fs.find(selectFolder(),1);
//        for (CodeFile Scanner:fs.getCodeFiles())
//            Scanner.setIndex(0);
//        for (CodeFile Scanner:fs.getCodeFiles()) {
//            String nowPackage=Scanner.getPackageName();
//            //System.out.println("["+nowPackage+"]"+Scanner.getFileName());
//            String code=Scanner.getCode();
//            for (CodeFile Scanner2:fs.getCodeFiles()) {
//                String target=Scanner2.getName();
//                if (Scanner.getName().equals(Scanner2.getName()))
//                    target=Scanner2.getName()+" ";
//                int times=appearNumber(code,target);
//
//                String type="";
////                        if ((code.indexOf("new " + Scanner2.getName() + "(") > 0)) {
////                                Scanner.create(Scanner2);
////                                type="CREATE";
////                            times*=2;
////
////                        } else if ((code.indexOf(" "+Scanner2.getName() + "" + ".") > 0)
////                                ||(!Scanner2.getFileName().equals(Scanner.getFileName()))) {
////                            type="use";
////                                Scanner.use(Scanner2);
////
////                        }
////                    else if ((code.indexOf(" "+Scanner2.getName() + " ") > 0)&&(!Scanner2.getFileName().equals(Scanner.getFileName())))
////                    {
////                            type="relate";
////                            Scanner.relate(Scanner2);
////
////                    }
//                if (Scanner.getName().equals(Scanner2.getName()))
//                {
//                    if (!type.equals("CREATE")) times=0;
//                }
//                if (times>0) {
//                    Edges.add("\"" + Scanner.getFileName() + "\"" + "->" +
//                            "\"" + Scanner2.getFileName() + "\"" + " " +
//                            "[label=\"" + times + "\"]");
//                    Edges2.add("\"" + Scanner.getFileName() + "\"" + "->" +
//                            "\"" + Scanner2.getFileName() + "\"" + " " +
//                            "[label=\"" + type + "\"]");
//                    }
//                }
//            //System.out.println();
//            }
//
//        GraphVizTest gvt=new GraphVizTest();
//        gvt.draw(Edges,"distance");
////        GraphVizTest gvt2=new GraphVizTest();
////        gvt2.draw(Edges2,"relation");
//        //gvt.draw(Edges2,"relation");
////            for (CodeFile Scanner:fs.getCodeFiles())
////            {
////                String nowPackage=Scanner.getPackageName();
////           //     //System.out.println("["+nowPackage+"]"+Scanner.getFileName()+" -R:"+Scanner.getRelateIndex()+" -U:"+Scanner.getUseIndex()+" -C:"+Scanner.getCreateIndex());
////            }
//    }
//
//    public static String selectFolder()
//    {
//        JFileChooser fileChooser = new JFileChooser("C:\\Users\\Saika\\Desktop\\!Java\\");
//        fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
//        int returnVal = fileChooser.showOpenDialog(fileChooser);
//        if(returnVal == JFileChooser.APPROVE_OPTION){
//            return  fileChooser.getSelectedFile().getAbsolutePath();
//        }
//        return null;
//    }
//
//    public static int appearNumber(String srcText, String findText) {
//        int count = 0;
//        Pattern p = Pattern.compile(findText);
//        Matcher m = p.matcher(srcText);
//        while (m.find()) {
//            count++;
//        }
//        return count;
//    }
//    @Test
//    public void test0()
//    {
//        String test=" public static int appearNumber(String srcText, String findText) {\n" +
//                "        int count = 0;\n" +
//                "        Pattern p = Pattern.compile(findText);\n" +
//                "        Matcher m = p.matcher(srcText);\n" +
//                "        while (m.find()) {\n" +
//                "            count++;\n" +
//                "        }\n" +
//                "        return count;\n" +
//                "    }";
//        String regex="[^a-zA-Z0-9.()]";
//        String[] result=test.split(regex);
//        //System.out.println(Arrays.toString(result));
//    }
//    @Test
//    public void test3()
//    {
//        String s1="bacdfe";
//        String s2="abcde";
//        StringCompare sc=new StringCompare(s1,s2);
//        //System.out.println(sc.work(1));
//    }
//
//    @Test
//    public void test4()
//    {
//        CodeFile cf1=new CodeFile(new File("D:\\S1.java"));
//        CodeFile cf2=new CodeFile(new File("D:\\User.java"));
//        ////System.out.println(Arrays.toString(cf1.getCodes()));
//        ////System.out.println(Arrays.toString(cf2.getCodes()));
//        double result=new CodeCompare(cf1,cf2).work();
//        //System.out.println("Similar:"+result);
//    }
//    @Test
//    public void test5()
//    {
//        HashMap<Integer,Double> test=new HashMap<>();
//        test.put(1,2d);
//        //System.out.println(test.get(1));
//        //System.out.println(test.get(2));
//        test.put(1,3d);
//        //System.out.println(test.get(1));
//        test.put(2,3d);
//        Set<Integer> s=test.keySet();
//        //System.out.println(Arrays.toString(s.toArray()));
//    }
//}
