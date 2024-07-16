package E1_daily;

import java.util.*;

public class E49_RobotCollisions {

    public static List<Integer> survivedRobotsHealths(int[] positions, int[] healths, String directions) {
        int n=positions.length;
        //Time: O(n)
        //Space: O(n)
        int[][] healthWithPos=new int[n][3];
        HashMap<Integer, Integer> posToIndex=new HashMap<>();

        //Time: O(n)
        for(int i=0;i<n;i++){
            healthWithPos[i][0]=positions[i];
            healthWithPos[i][1]=healths[i];
            //L:0
            //R:1
            healthWithPos[i][2]=directions.charAt(i)=='L'?0:1;
            posToIndex.put(positions[i], i);
        }
        //Time: O(n*log(n))
        //Space: O(log(n))
        Arrays.sort(healthWithPos, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[0]-o2[0];
            }
        });
        List<int[]> info=new ArrayList<>();
        Stack<int[]> stack=new Stack<>();

        //Time: O(n^2)
        for(int i=0;i<n;i++){
            if(healthWithPos[i][2]==0){
                System.out.println(healthWithPos[i][1]);
//                System.out.printf("Index: %s, val: %s\n", stack.peek()[0], stack.peek()[1]);
//                System.out.println(stack.size());
                if(!stack.isEmpty()){
                    boolean isAdd=true;
                    while (!stack.isEmpty()&&stack.peek()[2]==1){
                        int[] prev=stack.peek();
//                    System.out.println(prev[1]);

                        if(healthWithPos[i][1]>prev[1]){
                            healthWithPos[i][1]--;
                            stack.pop();
                        }else if(healthWithPos[i][1]==prev[1]){
                            stack.pop();
                            isAdd=false;
                            break;
//                        System.out.println(healthWithPos[i][1]);
                        }else{
                            prev[1]--;
                            isAdd=false;
                            break;
                        }
                    }
                    if(isAdd){
                        stack.add(healthWithPos[i]);
                    }
                }else{
                    stack.add(healthWithPos[i]);
                }
            }else{
                stack.add(healthWithPos[i]);
            }
        }
//        System.out.println(stack.peek()[1]);
        while (!stack.isEmpty()){
            info.add(stack.pop());
        }
        //Time: O(n*log(n))
        Collections.sort(info, Comparator.comparingInt(o -> posToIndex.get(o[0])));
        List<Integer> rs= new ArrayList<>();

        for(int i=0;i<info.size();i++){
            rs.add(info.get(i)[1]);
        }
        return rs;
    }

    public static void main(String[] args) {
        //** Requirement
        //- There are n 1-indexed robots, each having a position on a line, health, and movement direction.
        //- You are given 0-indexed integer arrays positions, healths, and a string directions (directions[i] is either 'L' for left or 'R' for right).
        //- All integers in positions are unique.
        //- All robots start moving on the line simultaneously at the (same speed) in their given directions.
        //  + If two robots ever share the same position while moving, they will collide.
        //  + If two robots collide, the robot with (lower health) is removed from the line, and the health of (the other robot) decreases (by one).
        //  + The surviving robot continues in the (same direction) it was going. If both robots have the (same health),
        //  they are both removed from the line.
        //- Your task is to determine the health of the robots that survive the collisions,
        // in the same order that the robots were given, i.e. final heath of robot 1 (if survived), final health of robot 2 (if survived), and so on.
        //- If there are no survivors, return an empty array.
        //* Return an array containing (the health of the remaining robots)
        //  + (In the order they were given in the input), after (no further collisions) can occur.
        //- Note:
        //  + The positions may be unsorted.
        //
        //** Idea
        //1.
        //1.0,
        //* Method-1:
        //- Constraint
        //1 <= positions.length == healths.length == directions.length == n <= 10^5
        //1 <= positions[i], healths[i] <= 10^9
        //directions[i] == 'L' or directions[i] == 'R'
        //All values in positions are distinct
        //
        //- Brainstorm
        //- Sort theo positions trước --> Get order
        //- Ta có 2 directions:
        //  + L/R
        //- Đi từ left -> right:
        //  + Gặp L --> Bỏ qua luôn được
        //  + Gặp R --> So sánh với L ==> Nếu có thì triệt tiêu đi.
        //      + Vì nếu triệt tiêu 1 trong 2 đi:
        //          Các health của robot khác sẽ (health-=1)
        //      ==> Ta sẽ cộng dồn phần giảm đi
        //Ex:
        //positions = [1,2,3,4,5], healths = [2,5,6,10,15], directions = "RLRLL"
        //pos=
        //+ [1,2]:
        //  + 5-1=4
        //+ [3,4]
        //  + 10-1=9
        //+ 15
        //  + 15
        //
        //- Ta chạy lần lượt + dùng stack
        //  ==> R --> check L là được.
        //
//        int[] positions = {5,4,3,2,1}, healths = {2,17,9,15,10};
//        String directions = "RRRRR";
//        int[] positions = {3,5,2,6}, healths = {10,10,15,12};
//        String directions = "RLRL";
        //2,3,5,6
        //15,10,10,12
        //R,R,L,L
//        int[] positions = {1,40}, healths = {10,11};
//        String directions = "RL";
        int[] positions = {11,44,16}, healths = {1,20,17};
        String directions = "RLR";
        //11,16,44
        //1,17,20
        //"RRL"
        //- Special case:
        //  + L --> Clear nhiều R cùng lúc
        //
        //1.1, Optimization
        //1.2, Complexity
        //- Time: O(n*log(n) + n^2)
        //- Space: O(n+log(n))
        //
        System.out.println(survivedRobotsHealths(positions, healths, directions));
        //#Reference:
        //1575. Count All Possible Routes
        //1764. Form Array by Concatenating Subarrays of Another Array
        //1413. Minimum Value to Get Positive Step by Step Sum
    }
}
