/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package astar;

import java.util.AbstractList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * smallest entity in graph
 * extends AbstractNode to be used by A* algorithm
 * @author leo
 */
public class Vertex extends AbstractNode{
    public String name;// just form vertify differ Vertex in graph(read friendly)
    public List<Weight> adj;//the adjacent vertex
    
    
    /**
     * create a vertex
     * give a name and heuristic value(can be replace by a heuristic function better)
     * @param name 
     */
    public Vertex(String name,double h){
        super(h);
        this.name=name;
    }
    
    /**
     * set this vertex' adjacent
     * @param adj
     */
    public void setAdj(List<Weight> adj){
        this.adj=adj;
    }

    /**
     * print the vertex name and it adjacent certext name and weight
     * @return 
     */
    @Override
    public String toString() {
        String s="";
        s+=this.name+"["+this.h+"]"+"  ";
        for(Iterator iter=this.adj.iterator();iter.hasNext();){
            Weight adjNow=(Weight)iter.next();
            s+=adjNow.toString();
        }
        return s;
    }
    /**
     * just use name to vertify two vertex
     * @param o
     * @return 
     */
    @Override
    public boolean equals(Object o) {
        if(this.name.equals(((Vertex)o).name)){
            return true;
        }
        return false;
    }
    /**
     * 
     * @return all Successor vertex without weight(only vertex)
     */
    @Override
    public List<AbstractNode> getSuccessor() {
        List<AbstractNode> successor=new LinkedList<>();
        for(Iterator iter=this.adj.iterator();iter.hasNext();){
            Weight adjNow=(Weight)iter.next();
            successor.add(adjNow.getVertex());
        }
        return successor;
    }
    /**
     * Get distance between two vertex.
     * Because,the weight only remember by parent vertex with a linkList
     * At here,I use linear search to find the adjacent vertex in a linkList(maybe wast time I think).
     *
     * @param previous an adjacent vertex
     * @return 
     */
    @Override
    public double getCost(AbstractNode previous) {
        double cost=0;
        for(Iterator iter=this.adj.iterator();iter.hasNext();){
            Weight adjNow=(Weight)iter.next();
            if(adjNow.getVertex().equals(previous)){
                 cost=adjNow.getWeight();
                 break;
            }
        }
        return cost;
    }
    /**
     * 
     * @return heuristic value we have set
     */
    @Override
    public double getH() {
        return this.h;
    }



    


    
}
