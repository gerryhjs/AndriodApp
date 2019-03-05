package Tester;

import MechineLearning.LearnProject;
import main.CompareFactory;
import org.junit.Test;

public class TestList {

    @Test
    public void Facotory_test()
    {
        CompareFactory.init();
        CompareFactory.createDiagram=false;
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
        LearnProject lp=new LearnProject();
        lp.addData(path0,path1,true);
        lp.addData(path1,path0,false);
        lp.addData(path0,path2,false);
        lp.addData(path2,path3,false);
        lp.addData(path3,path2,true);

        lp.training(2,10);


    }
}
