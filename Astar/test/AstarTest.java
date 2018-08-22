
import astar.Astar;
import astar.Astar_wiki;
import astar.Graph;
import astar.Vertex;
import astar.Weight;
import java.util.LinkedList;
import java.util.List;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author leo
 */
public class AstarTest {
    public static void main(String args[]) {
        Vertex a=new Vertex("A",25);//b(10),c(2)
        Vertex b=new Vertex("B",8);//a(10),c(3),d(3)
        Vertex c=new Vertex("C",10);//a(2),b(3),e(3)
        Vertex d=new Vertex("D",6);//b(3),e(4),f(6)
        Vertex e=new Vertex("E",12);//c(3),d(4),f(12)
        Vertex f=new Vertex("F",0);//e(12),d(6)
        //b(10),c(2)
        List<Weight> a_adj=new LinkedList<>();
        a_adj.add(new Weight(b, 10));
        a_adj.add(new Weight(c, 2));
        a.setAdj(a_adj);        
        //a(10),c(3),d(3)
        List<Weight> b_adj=new LinkedList<>();
        b_adj.add(new Weight(a, 10));
        b_adj.add(new Weight(c, 3));
        b_adj.add(new Weight(d, 3));
        b.setAdj(b_adj);
        //a(2),b(3),e(3)
        List<Weight> c_adj=new LinkedList<>();
        c_adj.add(new Weight(a, 2));
        c_adj.add(new Weight(b, 3));
        c_adj.add(new Weight(e, 3));
        c.setAdj(c_adj);
        //b(3),e(4),f(6)
        List<Weight> d_adj=new LinkedList<>();
        d_adj.add(new Weight(b, 3));
        d_adj.add(new Weight(e, 4));
        d_adj.add(new Weight(f, 6));
        d.setAdj(d_adj);
        //c(3),d(4),f(12)
        List<Weight> e_adj=new LinkedList<>();
        e_adj.add(new Weight(c, 3));
        e_adj.add(new Weight(d, 4));
        e_adj.add(new Weight(f, 12));
        e.setAdj(e_adj);
        //e(12),d(6)
        List<Weight> f_adj=new LinkedList<>();
        f_adj.add(new Weight(e, 12));
        f_adj.add(new Weight(d, 6));
        f.setAdj(f_adj);
        //create graph
        Vertex total[]={a,b,c,d,e,f};
        Graph graph=new Graph(total);
        System.out.println(graph);
        
        //Astar
        Astar<Vertex> astar=new Astar<Vertex>(graph, a, f);
        astar.astar();
        System.out.println("\npath:");
        astar.printPath(f);
        System.out.println("");
        
        //Astar_wiki
//        Astar_wiki<Vertex> astar=new Astar_wiki<Vertex>(graph);
//        astar.astar(a,f);
//        System.out.println("\npath:");
//        astar.printPath(f);
//        System.out.println("");
    }
}
