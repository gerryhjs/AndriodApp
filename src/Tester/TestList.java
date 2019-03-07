package Tester;

import MechineLearning.LearnProject;
import main.CompareFactory;
import org.junit.Test;

import java.io.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class TestList {

    @Test
    public void draw_test()
    {
        CompareFactory.init();
        CompareFactory.draw("/home/hjs/code_compare");
    }
    @Test
    public void Factory_test()
    {
        CompareFactory.init();
        System.out.println(CompareFactory.test_compare2());
    }
    /*
        private final double edge_weight_limit1=0.2;
    private final double edge_weight_limit2=5;

    private final double adj_dis_limit1=0.5;
    private final double adj_dis_limit2=5;

    private final double pow_dis_limit1=0.1;
    private final double pow_dis_limit2=10;

    private final double threshold1=0.5;
    private final double threshold2=0.8;

    private final double check_threshold1=0.2;
    private final double check_threshold2=0.8;

     */
    @Test
    public void Learn_test()
    {
        String path0="/media/hjs/KINGSTON/check/jsp-server";
        String path1="/media/hjs/KINGSTON/check/jsp-lab";
        String path2="/media/hjs/KINGSTON/check/saikaDL24";
        String path3="/media/hjs/KINGSTON/check/Predict";
        LearnProject.init();
        LearnProject.addData(path0,path1,true);
        LearnProject.addData(path1,path0,false);
        LearnProject.addData(path0,path2,false);
        LearnProject.addData(path2,path3,false);
        LearnProject.addData(path3,path2,true);
        LearnProject.training(4,10);
    }
    private String zipFileName;      // 目的地Zip文件
    private String sourceFileName;   //源文件（带压缩的文件或文件夹）





}
