package interviews;

import java.util.*;

public class E198_CourseScheduleII {

    public static int[] findOrder(int numCourses, int[][] prerequisites) {
        int[] degree=new int[numCourses];
        Map<Integer, Set<Integer>> map=new HashMap<>();

        for(int i=0;i<numCourses;i++){
            map.put(i, new HashSet<>());
        }
        for(int[] edge: prerequisites){
            int u=edge[0];
            int v=edge[1];

            degree[v]++;
            map.get(u).add(v);
        }
        List<Integer> result=new ArrayList<>();
        Deque<Integer> queue=new LinkedList<>();

        for(int i=0;i<numCourses;i++){
            if(degree[i]==0){
                queue.addLast(i);
            }
        }
        return null;
    }

    public static void main(String[] args) {

    }
}
