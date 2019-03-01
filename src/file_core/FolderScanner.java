package file_core;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FolderScanner {
//    private static int depth=1;
    //private ArrayList<File> finds;
    private ArrayList<String> finds;
    private ArrayList<CodeFile> codeFiles;
    private static String pathName="";
    public ArrayList<String> getFinds()
    {
        return finds;
    }
    public ArrayList<CodeFile> codeFiles()
    {
        return codeFiles;
    }
    private boolean removeComment=true;
    private boolean removeSpace=true;
    private static ArrayList<String> suffixList;
    private static String[] dictionary;
    public FolderScanner()
    {
        finds=new ArrayList<>();
        codeFiles=new ArrayList<>();
    }
//    public FolderScanner(String pathName,int depth) throws IOException {
//        finds=new ArrayList<>();
//        codeFiles=new ArrayList<>();
//        this.pathName=pathName;
//        this.find(pathName,depth);
//    }
    public void reset()
    {
        finds=new ArrayList<>();
        codeFiles=new ArrayList<>();
    }
    public void setJavaDictionary(String path)
    {
        FileStreamer fs=new FileStreamer(new File(path));
        String s=fs.input();
        System.out.println(s);
        if (s==null) return;
        dictionary=  s.split(",");
    }
    public void find(String pathName, int depth) throws IOException{
        File dirFile = new File(pathName);
        //finds.add(dirFile);
        if (!dirFile.exists()) {
         //   System.out.println("do not exit");
            return;
        }
        if (!dirFile.isDirectory()) {
            if (dirFile.isFile()) {
                try {
                    System.out.println(dirFile.getCanonicalFile());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return;
        }

        for (int j = 0; j < depth; j++) {
            System.out.print("  ");
        }
        System.out.print("|--");
        System.out.println(dirFile.getName());
        String[] fileList = dirFile.list();
        int currentDepth=depth+1;
        assert fileList != null;
        for (int i = 0; i < fileList.length; i++) {
            String string = fileList[i];
            File file = new File(dirFile.getPath(),string);
            String name = file.getName();
            if (file.isDirectory()) {
                find(file.getCanonicalPath(),currentDepth);
            }else{
                for (int j = 0; j < currentDepth; j++) {
                    System.out.print("   ");
                }
                System.out.print("|--");
                System.out.print("["+depth+"]");
                System.out.println(name);
                if (pd(file)) {
                    for (String suffix:suffixList) {
                        if (!finds.contains(file.getName().replace(suffix, ""))) {
                            finds.add(file.getName().replace(suffix, ""));
                            CodeFile cf;
                            cf = new CodeFile(file,dictionary);
                            if (!removeComment) cf.setRemoveComment(false);
                            codeFiles.add(cf);
                        }
                    }
                }
            }
        }
        return;
    }

    public static boolean pd(File file)
    {
        for (String Scanner:suffixList)
        {
            if (file.getName().endsWith("."+Scanner)) return true;
        }
       // if (file.getName().endsWith(".c")) return true;
        //if (file.getName().endsWith(".cpp")) return true;
        return false;
    }
    public static boolean contain(String txt,String key)
    {
        Pattern pattern = Pattern.compile(key);
        Matcher matcher = pattern.matcher(txt);
        return  matcher.matches();
    }

    public ArrayList<CodeFile> getCodeFiles() {
        return codeFiles;
    }

    public void disableComment() {
        removeComment=false;
    }
    public void disableSpace() {
        removeSpace=false;
    }

    public void setSuffixList(ArrayList<String> suffixList) {
        this.suffixList = suffixList;
    }
}
