/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ai_hw1_m10509109;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author leo
 */
public abstract class State implements AbstractState{
    
    protected State parent;
    protected int depth;
    
    /**
     * 第一次state的父親為null
     * 呼叫State(state p)
     * @param null
     */
    public State() {
        this(null);
    }
    
    /**
     * null 時，深度為0
     @param parent state*/
    public State(State p) {
        parent = p;
        depth = (p == null) ? 0 : p.depth + 1;
    }
    
    public State getParent() {
        return parent;
    }
    public int getDepth() {
        return depth;
    }   
    /**
     * 以連結串列的方法來儲存資料的，也就是一個元素接著一個的串在一起
     @return 路徑*/
    public List getPath() {
        List list = new LinkedList();
        for (State node = this; node != null; node =  node.parent) {
            list.add(node); 
        }
        Collections.reverse(list);
        return list;
    }
}
