
package ai_hw1_m10509109;

import java.util.*;

/**
 *  
 * 最短路徑
 * @author leo
 */
public class Ai_hw1_M10509109{
    public static enum MeOrTeacher{Me,Teacher};
//    public static final MeOrTeacher Mode=MeOrTeacher.Teacher;
    public static final MeOrTeacher Mode=MeOrTeacher.Me;
    
    public static void main(String args[]) {
        double map[][]={{0,10,2,0,0,0},
                        {10,0,3,3,0,0},
                        {2,3,0,0,3,0},
                        {0,3,0,0,4,6},
                        {0,0,3,4,0,12},
                        {0,0,0,6,12,0}};
        double mapH[]={25,8,10,6,12,0};
        Position initial=new Position(0);
        StateSpace<Position> space=new StateSpace<Position>(new Comparator<Position>() {
            @Override
            public int compare(Position a, Position b) {
                return (int)(a.f() - b.f());
            }
        });
        Position.initial(map, mapH, 5);
        space.maxNodeNum = 10000;
        Position goal = null;
        space.reset();
        space.add(initial);
        goal = space.search();
        System.out.println("nodeCount=" + space.getNodeCount());
        List<Position> path = goal.getPath();
        System.out.println("steps=" + (path.size() - 1));

        for (Position state : path) {
//            System.out.println("------ " + state.getDepth() + ":" + state.getActionCode() + " -------");
            System.out.println(state);
        }
        
    }
}

