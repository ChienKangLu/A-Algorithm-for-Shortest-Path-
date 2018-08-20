/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ai_hw1_m10509109;

import java.util.Collection;

/**
 *
 * @author leo
 */
public interface AbstractState {
        /**
         * 取得子狀態
         * 
         @return 子狀態*/
        abstract Collection getSuccessors();
        /**
         * 判斷是否為目標
         * 
         @return true代表示目標*/
        abstract boolean isGoal();
}
