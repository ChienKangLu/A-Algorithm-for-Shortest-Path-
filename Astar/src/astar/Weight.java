/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package astar;

/**
 * this is a weight edge,which means connect to v with the weight
 * @author leo
 */
public class Weight {
    Vertex v;
    int weight;//weighted graph
    /**
     * 
     * @param v connect to v 
     * @param weight with weight
     */
    public Weight(Vertex v,int weight){
        this.v=v;
        this.weight=weight;
    }
    public Vertex getVertex(){
        return this.v;
    }
    public int getWeight(){
        return this.weight;
    }
    
    
    @Override
    public String toString() {
        return v.name+"("+weight+") ";
    }
    
    
}
