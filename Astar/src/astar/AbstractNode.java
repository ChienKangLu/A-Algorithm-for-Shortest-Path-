/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.

 */
package astar;

import java.util.List;

/**
 *
 * @author leo
 */
public abstract class AbstractNode implements Comparable<AbstractNode>{
    private double g = Double.MAX_VALUE;//the cost of getting from the initial node to n.
    protected double h;//the estimate, according to the heuristic function, of the cost of getting from n to the goal node.
    private double f = Double.MAX_VALUE;//g(n)+h(n), intuitively, this is the estimate of the best solution that goes through n
    private AbstractNode parent;//previous Node
    
    public AbstractNode(double h){
        setH(h);
    }
    public double getG(){
        return g;
    }

    public abstract double getH();//this is heuristic function(at here we just give a number,but maybe can use the straight-line distance to replace)

    public double getF() {
        return f;
    }

    public AbstractNode getParent() {
        return parent;
    }

    public void setG(double g) {
        this.g = g;
    }

    public void setH(double h) {
        this.h = h;
    }

    public void setF(double f) {
        this.f = f;
    }

    public void setParent(AbstractNode parent) {
        this.parent = parent;
    }
    

    @Override
    public int compareTo(AbstractNode other){ //small to big
        //a.compareTo(b)
        //smaller than 0(a index smaller than b)
        //equal to 0(a index same as b)
        //bigger than 0(a index larger than b)
        if(this.getF()<other.getF()){
            return -1;
        }else if(this.getF()>other.getF()){
            return 1;
        }
        return 0;
        
    }

    /**
     * just use name to vertify
     * @param o
     * @return 
     */
    @Override
    public abstract boolean equals(Object o);
    public abstract List<AbstractNode> getSuccessor();
    /**
     * Cost between now and previous(e.g. distance between two vertexs)
     * @param previous
     * @return distance
     */
    public abstract double getCost(AbstractNode previous);
    
    

}
