package MechineLearning;

import main.CompareFactory;
import org.junit.Test;

import java.util.ArrayList;

public class LearnProject {

    private ArrayList<LearnData> myDatas;
    private double edge_weight;
    private double adj_dis;
    private double pow_dis;
    private double threshold;
    private double check_threshold;

    public double getEdge_weight() {
        return edge_weight;
    }

    public double getAdj_dis() {
        return adj_dis;
    }

    public double getPow_dis() {
        return pow_dis;
    }

    public double getThreshold() {
        return threshold;
    }

    public double getCheck_threshold() {
        return check_threshold;
    }


    @Test
    public void test()
    {

    }
    public LearnProject()
    {
        CompareFactory.init();
        
    }
    public void setStartData(double edge_weight,double adj_dis,double pow_dis,double threshold,double check_threshold)
    {
        this.edge_weight=edge_weight;
        this.adj_dis=adj_dis;
        this.pow_dis=pow_dis;
        this.threshold=threshold;
        this.check_threshold=check_threshold;
    }

    public void training()
    {
        for(LearnData Scanner:myDatas)
        {
            double result=CompareFactory.compare(Scanner.getPath0(),Scanner.getPath1(),edge_weight);
            boolean similar=result>threshold;
            if (similar==Scanner.isSimilar())
            {
                //TODO adjust1
            }
            else
            {
                //TODO adjust2
            }
        }
    }

    public void setMyDatas(ArrayList<LearnData> myDatas) {
        this.myDatas = myDatas;
    }
    private void addData(String path0,String path1,boolean similar)
    {
        this.myDatas.add(new LearnData(path0,path1,similar));
    }
}
