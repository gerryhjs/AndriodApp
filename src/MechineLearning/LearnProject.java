package MechineLearning;

import main.CompareFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Vector;

public class LearnProject {

    private ArrayList<LearnData> myDatas;
    private double edge_weight;
//    private double adj_dis;
    private double pow_dis;
//    private double threshold;
    private double check_threshold;


    private double edge_weight_step;
    private int edge_weight_direct=0;

    private double edge_weight_limit1=0;
    private double edge_weight_limit2=2;
    private double pow_dis_limit1=0;
    private double pow_dis_limit2=2;
    private double check_threshold_limit1=0.3;
    private double check_threshold_limit2=0.8;
    private double threshold_limit1=0.3;
    private double threshold_limit2=0.8;

    private double pow_dis_step;
    private int pow_dis_direct=0;


    private  double check_threshold_step;
    private int check_threshold_direct=0;

    private final double approachIndex=2;
    private final double stepIndex=0.01;

    private HashMap<Vector<Double>, Double> map;
    ArrayList<Double>  edge_weight_list;
    ArrayList<Double> pow_dis_list;
    ArrayList<Double> check_threshold_list;
    ArrayList<Double> threshold_list;

    private final double allowDiff=0.5;//为正限制 为负放宽
    private double step;
    private double threshold=0.5;
    private double threshold_step;

    private int threshold_direct=0;

    private double edge_weight_key;
    private double pow_dis_key;
    private double check_threshold_key;
    private double threshold_key;

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
    private final double expansion=1d;

    public void init()
    {
        edge_weight_limit1=0;
        edge_weight_limit2=2;
        pow_dis_limit1=0;
        pow_dis_limit2=2;
        check_threshold_limit1=0.3;
        check_threshold_limit2=0.8;
        threshold_limit1=0.3;
        threshold_limit2=0.8;
    }
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

    public void training(double step, int times)
    {
        if (times==0)
        {
            System.out.println(infoD());
            return;
        }
        this.step=step;
        edge_weight_step=(edge_weight_limit2-edge_weight_limit1)/(step+1);
        pow_dis_step=(pow_dis_limit2-pow_dis_limit1)/(step+1);
        check_threshold_step=(check_threshold_limit2-check_threshold_limit1)/(step+1);
        threshold_step=(threshold_limit2-threshold_limit1)/(step+1);
        int all= (int) Math.pow(step,4);
        double maxAcc=0;
        map=new HashMap<>();

        edge_weight_list=new ArrayList<>();
        pow_dis_list=new ArrayList<>();
        check_threshold_list=new ArrayList<>();
        threshold_list=new ArrayList<>();
        for (int i=1;i<=step;i++)
            for (int j=1;j<=step;j++)
                for (int k=1;k<=step;k++)
                    for (int l=1;l<=step;l++)
                    {
                        edge_weight=deal(edge_weight_limit1+i*edge_weight_step);
                        pow_dis=deal(pow_dis_limit1+j*pow_dis_step);
                        check_threshold=deal(check_threshold_limit1+k*check_threshold_step);
                        threshold=deal(threshold_limit1+l*threshold_step);
                        edge_weight_list.add(edge_weight);
                        pow_dis_list.add(pow_dis);
                        check_threshold_list.add(check_threshold);
                        threshold_list.add(threshold);
                        int now= (int) ((i-1)*Math.pow(step,3)+(j-1)*Math.pow(step,2)+(k-1)*Math.pow(step,1)+l);
                        System.out.println("["+times+"]:"+now+"/"+all+":");
                        System.out.println(info());
                        double sumDiff=0;
                        for (LearnData Scanner:myDatas)
                        {
                            sumDiff+=getDiff(Scanner);
                        }
                        accurate=deal(1-sumDiff/myDatas.size()*expansion);
//                        Vector<Double> v= new Vector<>(4);
//                        v.addElement(edge_weight);
//                        v.addElement(pow_dis);
//                        v.addElement(check_threshold);
//                        v.addElement(threshold);
//                        map.put(v,result);
                        System.out.println(accurate*100+"%");
                        if (accurate>maxAcc)
                        {
                            maxAcc=accurate;
                            edge_weight_key=edge_weight;
                            pow_dis_key=pow_dis;
                            check_threshold_key=check_threshold;
                            threshold_key=threshold;
                        }
                    }
            edge_weight_limit1=edge_weight_key-edge_weight_step;
            edge_weight_limit2=edge_weight_key+edge_weight_step;
            pow_dis_limit1=pow_dis_key-pow_dis_step;
            pow_dis_limit2=pow_dis_key+pow_dis_step;
            check_threshold_limit1=check_threshold_key-check_threshold_step;
            check_threshold_limit2=check_threshold_key+check_threshold_step;
            threshold_limit1=threshold_key-threshold_step;
            threshold_limit2=threshold_key+threshold_step;
            training(step,times-1);
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
        CompareFactory.edge_weight =edge_weight;
        return CompareFactory.compare(Scanner.getPath0(), Scanner.getPath1());
    }
    private double getDiff(LearnData Scanner)
    {
        result = getResult(Scanner);
        isSimilar =  Scanner.isSimilar();
        double diff;
//        System.out.println("CalcSimilar:"+deal(result)+"/"+threshold);
//        System.out.println("Fact:"+isSimilar);
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
//        System.out.println("Diff:"+deal(diff));
        return Math.pow(diff,approachIndex);
    }
    public Double deal(double input)
    {
        return  input;
//        BigDecimal b = new BigDecimal(input);
//        return   b.setScale(5, BigDecimal.ROUND_HALF_UP).doubleValue();
    }
//    public double getIndex()
//    {
//        long grade=1000000;
//        return edge_weight*Math.pow(grade,4)+pow_dis*Math.pow(grade,3)+check_threshold*Math.pow(grade,4)+threshold;
//    }

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
        return "edge_weight:["+edge_weight_limit1+"]/"+edge_weight+"/["+edge_weight_limit2+"]"+
                "\npow_dis:["+pow_dis_limit1+"]/"+pow_dis+"/["+pow_dis_limit2+"]"+
                "\nthreshold:["+threshold_limit1+"]/"+threshold+"/["+threshold_limit2+"]"+
                "\ncheck_threshold:["+check_threshold_limit1+"]/"+check_threshold+"/["+check_threshold_limit2+"]";
    }

    public String infoD() {
        /*
        this.edge_weight=edge_weight;
        this.adj_dis=adj_dis;
        this.pow_dis=pow_dis;
        this.threshold=threshold;
        this.check_threshold=check_threshold;
         */
        return "edge_weight:"+edge_weight_key+
                "\npow_dis:"+pow_dis_key+
                "\nthreshold:"+threshold_key+
                "\ncheck_threshold:"+check_threshold_key;
    }
}
