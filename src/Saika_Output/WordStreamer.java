package Saika_Output;

import org.apache.poi.hwpf.extractor.WordExtractor;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class WordStreamer {
    private String inputPath;
    public WordStreamer(String inputPath)
    {
        this.inputPath=inputPath;
    }
    public void setInputPath(String inputPath)
    {
        this.inputPath=inputPath;
    }

    public String read() throws IOException {
        try {
            FileInputStream fis = new FileInputStream(new File(inputPath));
            WordExtractor ex = new WordExtractor(fis);
            String doc = ex.getText();
            return doc;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    public void write(String str)
    {
        File file = new File(inputPath);
        try {
            FileOutputStream fos=new FileOutputStream(file);
            XWPFDocument doc = new XWPFDocument();
            XWPFParagraph para = doc.createParagraph();
            XWPFRun run = para.createRun();
            run.setText(str);
            doc.write(fos);
            fos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }




}
