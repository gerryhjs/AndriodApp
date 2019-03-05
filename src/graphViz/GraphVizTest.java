package graphViz;

import main.CompareFactory;

import java.util.ArrayList;

public class GraphVizTest {
    private final String graphVizPath= CompareFactory.getGraphVizPath();
    private final String dotPath=CompareFactory.getDotPath();


    public void draw(ArrayList<String> list,String name) {
        GraphViz gViz=new GraphViz(graphVizPath,dotPath);
        gViz.setPicName(name);
        gViz.start_graph();
        /*
        带有中文的图需要加上以下设置
        graph [fontname="Microsoft Yahei"];
node [shape=plaintext, fontname="Microsoft Yahei"];
edge [shape=plaintext, fontname="Microsoft Yahei"];
         */
        for (String Scanner:list) {
            gViz.addln(Scanner);
        }
        gViz.end_graph();
        try {
            gViz.run();
        } catch (Exception e) {
            e.printStackTrace();
        }
        gViz.delete();
    }
}
