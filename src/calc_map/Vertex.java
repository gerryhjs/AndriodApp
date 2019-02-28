package calc_map;

import core.CodeCompare;
import file_core.CodeFile;

import java.util.HashMap;
import java.util.Set;

public class Vertex {
    private CodeFile cf;
    private HashMap<Vertex,Object> Edges=new HashMap<>();
    public Vertex(CodeFile cf)
    {
        this.setCf(cf);
    }

    public CodeFile getCf() {
        return cf;
    }
    public String info()
    {
        return cf.getFileName()+"[valid lines:"+cf.getLines()+";valid size:"+cf.getSize()+"]";
    }

    public void setCf(CodeFile cf) {
        this.cf = cf;
    }

    public void Relate(Vertex another, double value)
    {
        Edges.put(another,value);
    }

    public Set<Vertex> getTo()
    {
        return Edges.keySet();
    }
    public double  getDistance(Vertex another)
    {
        Object distance=Edges.get(another);
        if (distance!=null) return (double) distance;
        //return Double.MAX_VALUE;
        return -1;
    }

    public double similar(Vertex another)
    {
        return new CodeCompare(this.getCf(),another.getCf()).work();
    }

}
