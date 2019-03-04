package file_core;

import java.io.File;

/**
 * Created by Saika on 2019/1/8.
 */
public class CodeFile {
    private String packageName;
    private String fileName;
    private String name;
    private String[] codes;
    private StringBuffer code;
    private int index;
    private boolean removeComment=true;
//    private boolean removeSpace=true;
    private long size;
    private long lines;
    private String[] dictionary;
    //    private ArrayList<CodeFile> useTo;
//    private ArrayList<CodeFile> useFrom;
//    private ArrayList<CodeFile> createTo;
//    private ArrayList<CodeFile> createFrom;
    public String getFileName()
    {
        return fileName;
    }
//    public void resetIndex()
//    {
//        //    private int relateIndex;
//        //    private int useIndex;
//        //    private int createIndex;
//        ArrayList<CodeFile> relateTo = new ArrayList<>();
//        ArrayList<CodeFile> relateFrom = new ArrayList<>();
//
//    }
//    public void relate(CodeFile scanner2)
//    {
//
//    }
//    public void use(CodeFile scanner2)
//    {
//
//    }
//    public void create(CodeFile scanner2)
//    {
//
//    }

    public String getPackageName()
    {
        return packageName;
    }
    private void setPackageName(String packageName)
    {
        this.packageName=packageName;
    }



//    CodeFile(File file) { //    private double LOW_DOWN=1;
//        index=0;
//        size=0;
//        lines=0;
////        boolean findPackage=false;
//        code=new StringBuffer();
//        this.fileName=file.getName();
//
//      try {
//          this.setName(fileName.split(("\\."))[0]);
//      }
//      catch (Exception e)
//      {
//          this.setName(fileName);
//      }
//        //this.setName(fileName.replace(".java",""));
//
//        FileStreamer fs=new FileStreamer(file);
//        String s=fs.input();
//        if (removeComment)
//        s=removeComment(s);
//        if (removeSpace)
//        s=removeTab(s);
//
//
////        s=removeEmptyLine(s);
//
//        String[] codex=s.split("\r\n");
//        codes=codex;
//        for (String Scanner:codex) {
//       //System.out.println(Scanner);
//            if (removeSpace)
//                Scanner=removeSign(Scanner);
//            code.append(Scanner);
//            size+=Scanner.length();
//            lines++;
//            if (this.packageName==null)
//            if (Scanner.contains("package "))
//            {
//                this.setPackageName(Scanner.replace("package","").replace(" ","").replace(";",""));
//            }
//
//            //            if (Scanner.replace(" ","").replace("{","").replace("}","")
////                    .replace(";","").length()>0)
////                codes.add(Scanner);
//        }
//       // System.out.println("====================");
//        //for (String Scanner:codes)
//          //  System.out.println(Scanner);
//    }
    //
    CodeFile(File file,String[] dictionary) { //    private double LOW_DOWN=1;
        index=0;
        size=0;
        lines=0;
//        boolean findPackage=false;
        code=new StringBuffer();
        this.fileName=file.getName();

        try {
            this.setName(fileName.split(("\\."))[0]);
        }
        catch (Exception e)
        {
            this.setName(fileName);
        }
        //this.setName(fileName.replace(".java",""));

        FileStreamer fs=new FileStreamer(file);
        String s=fs.input();
        if (removeComment)
            s=removeComment(s);

        this.dictionary=dictionary;
//        s=removeSign(s);

        s=removeTab(s);
        s=deal(s);

//        s=removeEmptyLine(s);
        String[] codex=s.split("\r\n");
        codes=codex;
        for (String Scanner:codex) {
            //System.out.println(Scanner);

            code.append(Scanner);
            size+=Scanner.length();
            lines++;
            if (this.packageName==null)
                if (Scanner.contains("package "))
                {
                    this.setPackageName(Scanner.replace("package","").replace(" ","").replace(";",""));
                }

            //            if (Scanner.replace(" ","").replace("{","").replace("}","")
//                    .replace(";","").length()>0)
//                codes.add(Scanner);
        }
        // System.out.println("====================");
        //for (String Scanner:codes)
        //  System.out.println(Scanner);
    }
    private String deal(String s)
    {
       // if (dictionary==null) return s;
        for (String Scanner:dictionary)
        {
            s=s.replace(Scanner,"*");
        }
       // System.out.println(s);
        return s;

    }

    private String removeComment(String s)
    {
        s=s.replaceAll("/\\*{1,2}[\\s\\S]*?\\*/","*");
        s=s.replaceAll("//[\\s\\S]*?\\n","*");
        return s;
    }
//    private String removeSign(String s)
//    {
//        s=s.replace("  ","");
//        s=s.replace("'","");
//        s=s.replace("\"","");
//        s=s.replace("{","");
//        s=s.replace("}","");
//        //s=s.replace("\r\n ","\r\n");
//        return s;
//    }
    private String removeTab(String s)
    {
        s=s.replace("\t","");
        return s;
    }
//    public String removeEmptyLine(String s)
//    {
//        s=s.replaceAll("^\\s*\\n","");
//        return s;
//    }


    public String getCode() {
        return code.toString();
    }
    public String[] getCodes(){return codes; }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public void setRemoveComment(boolean removeComment) {
        this.removeComment = removeComment;
    }


    public long getSize() {
        return size;
    }

    public long getLines() {
        return lines;
    }




//    public int getRelateIndex() {
//        return relateIndex;
//    }
//
//    public void setRelateIndex(int relateIndex) {
//        this.relateIndex = relateIndex;
//    }
//
//    public int getUseIndex() {
//        return useIndex;
//    }
//
//    public void setUseIndex(int useIndex) {
//        this.useIndex = useIndex;
//    }
//
//    public int getCreateIndex() {
//        return createIndex;
//    }
//
//    public void setCreateIndex(int createIndex) {
//        this.createIndex = createIndex;
//    }
}
