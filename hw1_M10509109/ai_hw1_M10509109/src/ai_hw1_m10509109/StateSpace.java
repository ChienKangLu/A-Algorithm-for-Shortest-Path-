/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ai_hw1_m10509109;

import java.util.Collection;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 *
 * @author leo
 */
public class StateSpace<S extends AbstractState> {
    public static boolean mergeGoals = false;//沒有任何作用
    public static int maxNodeNum = Integer.MAX_VALUE;
    PriorityQueue<S> open;
    ClosedMap<S> closed;
    private int nodeCount;
    private int visitCount;
    private Comparator<S> comparator;
    
    public StateSpace(Comparator<S> c) {
        comparator = c;
        reset();//初始化
    }
    
    public void add(S state) {
        nodeCount++;
        open.add(state);
        if(Ai_hw1_M10509109.Mode==Ai_hw1_M10509109.MeOrTeacher.Teacher){ 
            closed.put(state, state); // spanned state added to closed set
        }
    }
    public void setComparator(Comparator c) {
        comparator = c;
        reset();
    }
    public void reset() {
        nodeCount = 0;
        open = new PriorityQueue(1000, comparator);
        closed = new ClosedMap<S>(comparator);
    }
    public S search() {
        while (true) {
            S current = open.poll();//Retrieves and removes the head of this queue
            if(Ai_hw1_M10509109.Mode==Ai_hw1_M10509109.MeOrTeacher.Me){
                closed.put(current,current); // spanned state added to closed set
            }
            if (current == null) {
                return null;
            }
            else if (current.isGoal()) {
                return current;
            }
            else if (nodeCount >= maxNodeNum) {
                return null;
            }
            visitCount++;
            Collection<S> successors = current.getSuccessors();
            for (S successor : successors) {
                if(!closed.accept(successor)) continue;//不是目標而且已經走過的不放入successor
                
                add(successor);//先經過檢查才加入
            }
        }
        
    }
    public int getNodeCount() {
        return nodeCount;
    }

    public int getVisitCount() {
        return visitCount;
    }
}
