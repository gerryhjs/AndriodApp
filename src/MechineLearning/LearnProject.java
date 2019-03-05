package MechineLearning;

import main.CompareFactory;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class LearnProject {
    private DecimalFormat df = new DecimalFormat("#.000");

    private ArrayList<LearnData> myDatas;
    private double edge_weight;
//    private double adj_dis;
    private double pow_dis;
//    private double threshold;
    private double check_threshold;

    private final double edge_weight_limit1=0;
    private final double edge_weight_limit2=2;
    private double edge_weight_step;
    private int edge_weight_direct=0;


    private final double pow_dis_limit1=0;
    private final double pow_dis_limit2=2;
    private double pow_dis_step;
    private int pow_dis_direct=0;

    private final double check_threshold_limit1=0.2;
    private final double check_threshold_limit2=0.8;
    private  double check_threshold_step;
    private int check_threshold_direct=0;

    private final double approachIndex=2;

    private double[][][][] learnPoints;

    private final double allowDiff=0.1;//为正限制 为负放宽

    private double threshold=0.5;
    private double threshold_step;
    private final double threshold_limit1=0.2;
    private final double threshold_limit2=0.8;
    private int threshold_direct=0;

//    private final double threshold_limit1=0.5;
//    private final double threshold_limit2=0.8;
//    private final double threshold_move=0.005;
//    private int threshold_direct=0;

//
//    private final double adj_dis_limit1=1;
//    private final double adj_dis_limit2=2;
//
//    private final double adj_dis_move=0.01;
//    private int adj_dis_direct=0;


    private double result;
    private boolean isSimilar;

    private double accurate;
    private final double expansion=10d;
    public double getEdge_weight() {
        return edge_weight;
    }

//    public double getAdj_dis() {
//        return adj_dis;
//    }

    public double getPow_dis() {
        return pow_dis;
    }

//    public double getThreshold() {
//        return threshold;
//    }

    public double getCheck_threshold() {
        return check_threshold;
    }


    public LearnProject()
    {
        CompareFactory.init();
        myDatas=new ArrayList<>();
    }

    public void training(double step, double times)
    {
        /*
    private final double edge_weight_limit1=0;
    private final double edge_weight_limit2=10;
    private int edge_weight_direct=0;
         */
        learnPoints=new double[(int) step][(int) step][(int) step][(int) step];
        edge_weight_step=(edge_weight_limit2-edge_weight_limit1)/step;
        pow_dis_step=(pow_dis_limit2-pow_dis_limit1)/step;
        check_threshold_step=(check_threshold_limit2-check_threshold_limit1)/step;
        threshold_step=(threshold_limit2-threshold_limit1)/step;
        int all= (int) Math.pow(step,4);
        double maxAcc=0;
        for (int i=1;i<=step;i++)
            for (int j=1;j<=step;j++)
                for (int k=1;k<=step;k++)
                    for (int l=1;l<=step;l++)
                    {
                        edge_weight=edge_weight_limit1+i*edge_weight_step;
                        pow_dis=pow_dis_limit1+j*pow_dis_step;
                        check_threshold=check_threshold_limit1+k*check_threshold_step;
                        threshold=threshold_limit1+l*check_threshold_step;
                        int now= (int) ((i-1)*Math.pow(step,3)+(j-1)*Math.pow(step,2)+(k-1)*Math.pow(step,1)+l);
                        System.out.println(now+"/"+all+":");
                        System.out.println(info());
                        double sumDiff=0;
                        for (LearnData Scanner:myDatas)
                        {
                            sumDiff+=getDiff(Scanner);
                        }

                        accurate=1-sumDiff/myDatas.size()*expansion;
                        learnPoints[i-1][j-1][k-1][l-1]=accurate;
                        System.out.println(df.format(accurate*100)+"%");
                    }
    }
//    public void training(double times)
//    {
//        for (int i=0;i<=times;i++) {
//            for (LearnData Scanner : myDatas) {
//
//            }
//        }
//    }

    private double getResult(LearnData Scanner) {
        CompareFactory.init();
        CompareFactory.pow_dis=pow_dis;
        CompareFactory.check_threshold=check_threshold;
        return CompareFactory.compare(Scanner.getPath0(), Scanner.getPath1(), edge_weight);
    }
    private double getDiff(LearnData Scanner)
    {
        result = getResult(Scanner);
        isSimilar =  Scanner.isSimilar();
        double diff;
        System.out.println(result);
        System.out.println(isSimilar);
        if (isSimilar)
        {
            if (result>threshold+allowDiff) diff=0;
            else diff=(threshold+allowDiff)-result;
        }
        else
        {
            if (result<threshold-allowDiff) diff=0;
            else diff=result-(threshold-allowDiff);
        }
        System.out.println(diff);
        return Math.pow(diff,approachIndex);
    }

    public void setMyDatas(ArrayList<LearnData> myDatas) {
        this.myDatas = myDatas;
    }
    public void addData(String path0,String path1,boolean similar)
    {
        this.myDatas.add(new LearnData(path0,path1,similar));
    }

    public String info() {
        /*
        this.edge_weight=edge_weight;
        this.adj_dis=adj_dis;
        this.pow_dis=pow_dis;
        this.threshold=threshold;
        this.check_threshold=check_threshold;
         */
        String s= "edge_weight:"+df.format(edge_weight)+" move:"+edge_weight_direct +"\n"+
                "pow_dis:"+df.format(pow_dis)+" move:"+pow_dis_direct +"\n"+
                "check_threshold:"+df.format(check_threshold)+" move:"+check_threshold_direct +"\n"+
                "threshold:"+df.format(threshold)+" move:"+threshold_direct +"\n";
        return s;
    }
}
