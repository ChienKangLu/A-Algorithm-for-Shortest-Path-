/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ai_hw1_m10509109;

import java.util.Comparator;
import java.util.HashMap;

/**
 *
 * @author leo
 */
public class ClosedMap<S extends AbstractState> extends HashMap<S, S>{

    private Comparator<S> comparator;//用於定義比較的方式

    public ClosedMap(Comparator c) {
        comparator = c;
    }
    public boolean accept(S obj) {
        if(!containsKey(obj)) {
            return true;
        }
        System.out.println("@@"+obj);
        S existing = get(obj);
        if (comparator.compare(obj, existing) < 0) { // lower cost --> replace it
        System.out.println("XX"+obj);
           this.remove(existing);
           return true;
        }
        return false;
    }
    
}
