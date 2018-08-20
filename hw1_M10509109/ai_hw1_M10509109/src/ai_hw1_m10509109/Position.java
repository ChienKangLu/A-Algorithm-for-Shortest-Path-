/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ai_hw1_m10509109;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author leo
 */
public class Position extends State {

    /**
     * 整張地圖用二維陣列來表示
     */
    public static double map[][];
    public static double mapHeuristic[];
    /**
     * 目前的位置
     */
    protected int now_Position;
    /**
     * 目標位置
     */    
    public static Position goalPosition;
    
    public Position(int Position){
        super(null);
        setPosition(Position);
    }
    public Position(Position parants,int Position){
        super(parants);
        setPosition(Position);        
    }
    
    private void setPosition(int posNum){
        this.now_Position=posNum;
    }
    public static void initial(double map[][],double mapH[],int goalPos){
        setMap(map);
        setMapHeuristic(mapH);
        setGoalPosition(goalPos); 
    }

    public static void setMap(double[][] map){
        Position.map=map;
    }
    public static void setMapHeuristic(double[] mapH){
        Position.mapHeuristic=mapH;
    }
    public static void setGoalPosition(int goal){
        Position.goalPosition=new Position(goal);
    }


    @Override
    public Collection getSuccessors() {
        List successors = (List) new LinkedList();
        for(int i=0;i<map[now_Position].length;i++){
            if(map[now_Position][i]!=0){
                successors.add(new Position(this,i));
            }
        }
        return successors;
    }

    @Override
    public String toString() {
        return translateToLetter(now_Position)+":"+f();
    }
    public String translateToLetter(int pos){
        return ""+(char) ('A'+pos);
    }
    
    @Override
    public int hashCode() {
        int hash = -1;//我隨便給的
        hash = Integer.hashCode(now_Position);
        return hash;
    }
     @Override
    public boolean equals(Object obj) {
        Position another = (Position) obj;
        if(now_Position==another.now_Position){
            return true;
        }
        return false;
    }
    public double findMapToGetG(int from ,int to){
        return map[from][to];
    }
    public double findMapHeuristicToGetH(int pos){
        return mapHeuristic[pos];
    }
    public double f(){
        return g()+h();
    }
    public double g(){
        int score=0;
        List<Position> path = this.getPath();
        Position start=null;
        Position end=null;
        int count=0;
        for (Position state : path) {
            if(count==0){
                start=state;
            }else if(count>=1){
                end=state;
                score+=state.findMapToGetG(start.now_Position, end.now_Position);
                start=end;
            }
            count++;
        }
        return score;
    }
    public double h(){
        return findMapHeuristicToGetH(now_Position);
    }
    @Override
    public boolean isGoal() {
        return this.equals(goalPosition);
    }
    
}
