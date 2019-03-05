package compare_core;

import file_core.CodeFile;

public class CodeCompare {
    private  String[] codes1;
    private String[] codes2;
    public CodeCompare(CodeFile cf1, CodeFile cf2)
    {
        codes1=cf1.getCodes();
        codes2=cf2.getCodes();
    }

    public double work()
    {
        return compare(codes1,codes2);
       // return Math.max(compare(codes1,codes2),compare(codes2,codes1));

//     if (mode==1)
//    {
//        double[][] diff = new double[codes1.length + 1][codes2.length + 1];
//        for (int i = 0; i < codes1.length; i++)
//            diff[i][0] = i;
//        for (int j = 0; j < codes2.length; j++)
//            diff[0][j] = j;
//        for (int i = 1; i <= codes1.length; i++)
//            for (int j = 1; j <= codes2.length; j++) {
//                double similar = new StringCompare(codes1[i - 1], codes2[j - 1])
//                        .work(1);
//                double temp = 1 - Math.pow(similar, 2);
//                diff[i][j] = min(diff[i - 1][j] + 1, diff[i][j - 1] + 1, diff[i - 1][j - 1] + temp);
//            }
//        return diff[codes1.length][codes2.length];
//    }

    }
    public double min(double... input)
    {
        double ans=Double.MAX_VALUE;
        for (double Scanner:input)
            ans=Math.min(ans,Scanner);
        return ans;
    }
    public double max(double... input)
    {
        double ans=Double.MIN_VALUE;
        for (double Scanner:input)
            ans=Math.max(ans,Scanner);
        return ans;
    }

    public double compare(String[] codes1,String[] codes2)
    {
        double sum=0;
        double index=0;
        for (String Scanner:codes1)
        {

            if (Scanner.length()>0)
            {
                index++;
                double similar=0;
                String ans = null;
                for(String Scanner2:codes2)
                {
                    if (Scanner2.length()>0) {
                        double temp = new StringCompare(Scanner, Scanner2).work(1);
                        ////System.out.println(Scanner + "<->" + Scanner2 + ":"+ temp);
                        if (temp > 1) temp = 1;
                        temp = Math.pow(temp, 1);
                      //  similar = Math.max(similar, temp);
                        if (temp>similar)
                        {
                            similar=temp;
                            ans = Scanner + "=>" + Scanner2;
                        }
                    }
                }
                ////System.out.println(ans+":"+similar);
                ////System.out.println(similar);
                sum+=similar;
            }
        }

        if (index==0) index=1;
        ////System.out.println("S_"+sum+"_"+index);
        return sum/index;
    }


}
