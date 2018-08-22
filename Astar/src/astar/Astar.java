/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package astar;

import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;

/**
 * A* algorithm
 * @author leo
 */
public class Astar <N extends AbstractNode>{
    PriorityQueue<N> openList;//The OPEN list keeps track of those nodes that need to be examined
    LinkedList<N> closedList;//The the CLOSED list keeps track of nodes that have already been examined
    N goal;
    N start;
    public Astar(Graph g,Vertex start,Vertex goal){
        this.start=(N)start;
        this.goal=(N) goal;
    }
    public boolean astar(){
        openList =new PriorityQueue<N>();
        closedList = new LinkedList<N>();
        start.setG(0);
        start.setF(start.getH());
        System.out.println("add start point"+"["+((Vertex)start).name+"] to openlist");
        openList.add(start);
        while(!openList.isEmpty()){            
            System.out.print("openList: ");
            for(Iterator iter=openList.iterator();iter.hasNext();){
                Vertex v=((Vertex)iter.next());
                System.out.print(v.name+"["+v.getF()+"] ");
            }
            System.out.println("");
            
            N n=openList.poll();
            System.out.println("remove"+"["+((Vertex)n).name+"] from openlist");
            closedList.add(n);
            System.out.println(((Vertex)n).name+""+n.getF());
            
            if(n.equals(goal)){
                return true;
            }else{
                List<N> successor=(List<N>) n.getSuccessor();
                for(N S:successor){
                    
                    //the successor from n
                    double tentativeH=S.getH();
                    double tentativeG=n.getG()+S.getCost(n);
                    double tentativeF=tentativeG+tentativeH;
                    //check whether this successor'tentetive F is better than the old one in openList or closedList or not
                    if(openList.contains(S)&&goodOrbetter(S,tentativeF)){
                        System.out.println("        "+((Vertex)S).name+"("+tentativeF+")"+":"+"contains in openList! and old one ["+((Vertex)S).name+","+((Vertex)S).getF()+"] is better");
                        continue;
                    }
                    if(closedList.contains(S)&&goodOrbetter(S,tentativeF)){
                        System.out.println("        "+((Vertex)S).name+"("+tentativeF+")"+":"+"contains in closedList! and old one ["+((Vertex)S).name+","+((Vertex)S).getF()+"] is better");
                        continue;
                    }else{
                        System.out.println("        "+"!"+((Vertex)S).name+"("+tentativeF+")"+":"+"contains in closedList or the first time visited! and old one ["+((Vertex)S).name+","+((Vertex)S).getF()+"] is not better!");
                    }
                    //if the successor'tentetive F is better than the one exits in openList or closedList,,we remove the inferior one from the two set and add n as S's parent
                    System.out.println("        "+"add to openlist");
                    S.setParent(n);
                    S.setH(S.getH());
                    S.setG(n.getG()+S.getCost(n));
                    S.setF(S.getG()+S.getH());
                    
                    System.out.println("        "+((Vertex)n).name+":"+((Vertex)S).name+"("+S.getF()+")");
                    openList.remove(S);
                    closedList.remove(S);
                    openList.add(S);
                }
            }
            
        }
        return false;//we've searched all reachable nodes and still haven't found the path
    }
    /**
     * check whether the tentative one is better than old one in the openList or closedList
     * @param S the old one in the openList or closedList
     * @param tentativeF the tentative one 
     * @return goodOrbetter
     */
    public boolean goodOrbetter(AbstractNode S,double tentativeF){
        if(S.getF()<=tentativeF)
            return true;
        else
            return false;

    }
    /**
     * print path from the goal node to start node
     * @param node goal node
     */
    public void printPath(AbstractNode node){
        if(node.getParent()!=null){
            printPath(node.getParent());
            System.out.print("  to  ");
        }
        System.out.print(((Vertex)node).name);
    }

}
