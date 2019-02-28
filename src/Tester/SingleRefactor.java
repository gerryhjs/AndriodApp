//package Tester;
//
//import javax.swing.*;
//import java.util.ArrayList;
//
//public class SingleRefactor {
//    String s;
//    public static void main(String[] args)
//    {
//        String filepath=selectFile();
//        String outpath=selectFolder();
//    }
//
//
//    public void removeComment()
//    {
//        s=s.replaceAll("/\\*{1,2}[\\s\\S]*?\\*/","");
//        s=s.replaceAll("//[\\s\\S]*?\\n","");
//    }
//    public void removeChinese()
//    {
//        s=s.replaceAll("^[\\u0391-\\uFFE5]+$ ","");
//    }
//    public void removeEmptyLine()
//    {
//        s=s.replaceAll("^\\s*\\n","");
//    }
//
//    public static String selectFolder()
//    {
//        JFileChooser fileChooser = new JFileChooser("C:\\");
//        fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
//        int returnVal = fileChooser.showOpenDialog(fileChooser);
//        if(returnVal == JFileChooser.APPROVE_OPTION){
//            return  fileChooser.getSelectedFile().getAbsolutePath();
//        }
//        return null;
//    }
//    public static String selectFile()
//    {
//        JFileChooser fileChooser = new JFileChooser("C:\\");
//        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
//        int returnVal = fileChooser.showOpenDialog(fileChooser);
//        if(returnVal == JFileChooser.APPROVE_OPTION){
//            return  fileChooser.getSelectedFile().getAbsolutePath();
//        }
//        return null;
//    }
//
//    public static ArrayList<String> suffix()
//    {
//        ArrayList<String> suffix=new ArrayList<>();
//        return suffix;
//    }
//
//}
