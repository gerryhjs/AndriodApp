package Tester;

import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by Saika on 2019/1/9.
 */
public class Main_v1 {
//    public static void main() throws IOException {
////        String target1=selectFolder();
////        FolderScanner fs1=new FolderScanner(target1,10);
////        String target2=selectFolder();
////        FolderScanner fs2=new FolderScanner(target2,10);
//
//    }
@Test
    public void jsonTest()
    {
        String s="{\"table\": \"\",\"id\": \"1_mcu\",\"threshold_memory\": \"60\",\"threshold_cpu\": \"95\"}";
        System.out.println(getValue(s,"table"));
        System.out.println(getValue(s,"id"));
    }
    @Test
    public void arrayTest()
    {
        ArrayList<String> test=new ArrayList<>();
        test.add("a");
        test.add("b");
        //System.out.println(test.toString());
    }
    private String getValue(String json, String attr)
    {

        try {
            int index1 = json.indexOf("\""+attr+"\"");
            String newData = json.substring(index1 + attr.length()+2);
            int index2 = newData.indexOf("\"");
            int index3 = newData.substring(index2+1).indexOf("\"");
            return newData.substring(index2+1, index2+index3+1);
        }
        catch (Exception e)
        {
            return null;
        }
    }
}
