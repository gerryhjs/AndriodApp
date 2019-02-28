package core;

/**
 * Created by Saika on 2019/1/2.
 */
public class StringFactory {
    public double min(double... input)
    {
        double ans=Double.MAX_VALUE;
        for (double Scanner:input)
            ans=Math.min(ans,Scanner);
        return ans;
    }
}
