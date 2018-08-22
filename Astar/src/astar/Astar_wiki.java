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
 *
 * @author leo
 */
public class Astar_wiki<N extends AbstractNode> {

    PriorityQueue<N> openList;//The OPEN list keeps track of those nodes that need to be examined
    LinkedList<N> closedList;//The the CLOSED list keeps track of nodes that have already been examined
    N goal;
    N start;

    public Astar_wiki(Graph g) {
    }

    public boolean astar(Vertex s, Vertex g) {
        this.start = (N) s;
        this.goal = (N) g;

        closedList = new LinkedList<N>();
        openList = new PriorityQueue<N>();
        openList.add(start);
        start.setG(0);
        start.setF(start.getH());
        System.out.println("add start point" + "[" + ((Vertex) start).name + "] to openlist");
        while (!openList.isEmpty()) {
            System.out.print("openList: ");
            for(Iterator iter=openList.iterator();iter.hasNext();){
                Vertex v=((Vertex)iter.next());
                System.out.print(v.name+"["+v.getF()+"] ");
            }
            System.out.println("");
            N n = openList.poll();
            if (n.equals(goal)) {
                return true;
            }
            System.out.println("remove" + "[" + ((Vertex) n).name + "] from openlist");
            closedList.add(n);
            System.out.println(((Vertex) n).name + "" + n.getF());

            List<N> successor = (List<N>) n.getSuccessor();
            for (N S : successor) {
                /**
                 * it assume that the heuristic function is monotonic[ h(x) smaller or equal to g(x)+h(x) ]
                 * 
                 * the heuristic must be “admissible.” To be 
                 * admissible, a heuristic must never over-estimate the cost of getting from a state to the goal state
                 */
                if(closedList.contains(S)){
                    continue;
                }
                boolean addToOpenList=false;
                if(!openList.contains(S)){
                    /**
                     * my addressed:
                     * Remove [ openList.add(S); ] after set S's F, beacuase at here we use a priority queue 
                     * and it will sort decreasely when adding, so we have to set F value first and then add to this queue
                     * Otherwise, the order will be sorted by a infinite value
                     * 
                     * wiki:
                     *      addToOpenList=true; --->  openList.add(S);
                     * because wiki use array to save all node's f and it will be sorted every time
                     * so it not be effected by the order of setting f value
                    */
                    addToOpenList=true;
                    System.out.println("        " + "add ["+((Vertex) S).name+"]to openlist");
                }
                
                //check the tentative one is smaller than the one in openList
                double tentativeG = n.getG() + S.getCost(n);
                if(tentativeG>=S.getG()){
                    continue;
                }

                S.setParent(n);
                S.setH(S.getH());
                S.setG(tentativeG);
                S.setF(S.getG() + S.getH());
                
                if(addToOpenList){
                    openList.add(S);
                }

                
                System.out.println("        " + ((Vertex) n).name + ":" + ((Vertex) S).name + "(" + S.getF() + ")");



            }

        }
        return false;//we've searched all reachable nodes and still haven't found the path
    }

    /**
     * print path from the goal node to start node
     *
     * @param node goal node
     */
    public void printPath(AbstractNode node) {
        if (node.getParent() != null) {
            printPath(node.getParent());
            System.out.print("  to  ");
        }
        System.out.print(((Vertex) node).name);
    }

}
