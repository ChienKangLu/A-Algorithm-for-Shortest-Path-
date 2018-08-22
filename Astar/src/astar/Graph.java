/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package astar;

/**
 *
 * @author leo
 */
public class Graph {
    Vertex[] graph;
    public Graph(Vertex[] graph){
        this.graph=graph;
    }

    @Override
    public String toString() {
        String s="";
        for(int i=0;i<graph.length;i++){
            s+=graph[i]+"\n";
        }
        return s;
    }
    
    
}
