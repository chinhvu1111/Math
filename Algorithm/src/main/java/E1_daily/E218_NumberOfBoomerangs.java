package E1_daily;

import java.util.HashMap;

public class E218_NumberOfBoomerangs {

    public static int numberOfBoomerangs(int[][] points) {
        int n=points.length;
        int rs=0;

        for(int i=0;i<n;i++){
            HashMap<Integer, Integer> mapCount=new HashMap<>();

            for(int j=0;j<n;j++){
                if(i==j){
                    continue;
                }
                Integer distance = (points[i][0]-points[j][0])*(points[i][0]-points[j][0])+(points[i][1]-points[j][1])*(points[i][1]-points[j][1]);
                if(mapCount.containsKey(distance)){
//                    System.out.printf("i:%s, j:%s, dist: %s\n", i, j, distance);
                    rs+=mapCount.get(distance)*2;
                }
                mapCount.put(distance, mapCount.getOrDefault(distance, 0)+1);
            }
        }
        return rs;
    }

    public static void main(String[] args) {
        //** Requirement
        //- You are (given n points in the plane) that are (all distinct), where points[i] = [xi, yi].
        //- A boomerang is a tuple of points (i, j, k) such that the distance between (i and j) equals the distance between (i and k)
        // (the order of the tuple matters).
        //* Return (the number of boomerangs).
        //
        //Example 1:
        //
        //Input: points = [[0,0],[1,0],[2,0]]
        //Output: 2
        //Explanation: The two boomerangs are [[1,0],[0,0],[2,0]] and [[1,0],[2,0],[0,0]].
        //
        // Idea
        //1.
        //1.0,
        //- Method-1:
        //+ Constraints:
        //n == points.length
        //1 <= n <= 500
        //points[i].length == 2
        //-10^4 <= xi, yi <= 10^4
        //All the points are unique.
        //
        //- Brainstorm
        //Ex:
        //points = [a,b,c,d,e]
        //(a,b) = x
        //(c,d) = x
        //  + They will be combined to (one tuple) if they share the same (x or y)
        //  Ex:
        //  (a,b),(a,c),(a,d)
        //  ==> We need to choose (the pivot) to traverse
        //
        //Example 1:
        //
        //Input: points = [[0,0],[1,0],[2,0]]
        //Output: 2
        //Explanation: The two boomerangs are [[1,0],[0,0],[2,0]] and [[1,0],[2,0],[0,0]].
        //[(1,0),(0,0),(2,0)]
        //a,b,c,d
        //  + (a,b),(a,c),(a,d)
        //(a,b,c,d) + e
        //  + (a,e) = (a,b),(a,c),(a,d)
        //  + [(a,b),x], [(a,c),x], [(a,d),x]
        //  + [(a,e)...]
        //      + rs+=map[count]
        //
//        int[][] points = {{0,0},{1,0},{2,0}};
        int[][] points = {{1,1},{2,2},{3,3}};
        //2*2+2*2
        //[0,1,2],[0,2,1]
        //[1,2,0],[1,0,2]
        //[2,1,0],[2,0,1]
        System.out.println(numberOfBoomerangs(points));
    }
}
