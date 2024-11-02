package E1_daily;

import java.util.*;

public class E144_MinimumTotalDistanceTraveled {

    public static long minimumTotalDistanceWrong(List<Integer> robot, int[][] factory) {
        int n=robot.size();
        int m=factory.length;
        HashMap<Integer, Integer> factoryLimit=new HashMap<>();
        HashSet<Integer> visitedRobot=new HashSet<>();
        PriorityQueue<int[]> minDistance=new PriorityQueue<>(new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                if(o1[0]!=o2[0]){
                    return o1[0]-o2[0];
                }
//                return o2[2]-o1[2];
                return o1[2]-o2[2];
            }
        });

        for(int i=0;i<m;i++){
            factoryLimit.put(factory[i][0], factory[i][1]);
        }

        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++){
                //-2,1
                //-2-1= -3
                //1+2 = 3
                minDistance.add(new int[]{Math.abs(robot.get(i)-factory[j][0]), factory[j][0], robot.get(i)});
            }
        }
        long rs=0;
        while(!minDistance.isEmpty()){
            int[] e=minDistance.poll();
            if(visitedRobot.contains(e[2])||!factoryLimit.containsKey(e[1])){
                continue;
            }
            if(factoryLimit.get(e[1])==1){
                factoryLimit.remove(e[1]);
            }else{
                factoryLimit.put(e[1], factoryLimit.getOrDefault(e[1], 0)+1);
            }
            rs+=e[0];
            visitedRobot.add(e[2]);
        }
        return rs;
    }

    public static long[][] memo;

    public static long solution(int robotIndex, int factoryIndex, List<Integer> robot, List<Integer> factoryPos){
        if(robotIndex==robot.size()){
            return 0;
        }
        if(factoryIndex==factoryPos.size()){
            return 1_000_000_000_000L;
        }
//        System.out.println(factoryIndex);
        if(memo[robotIndex][factoryIndex]!=Long.MAX_VALUE){
            return memo[robotIndex][factoryIndex];
        }
        //Assign current robot to the current factory
        long addValue = solution(robotIndex+1, factoryIndex+1, robot, factoryPos)
                +Math.abs(factoryPos.get(factoryIndex) - robot.get(robotIndex));
        long skipValue = solution(robotIndex, factoryIndex+1, robot, factoryPos);
        return memo[robotIndex][factoryIndex] = Math.min(addValue, skipValue);
    }

    public static long minimumTotalDistance(List<Integer> robot, int[][] factory) {
        List<Integer> factoryPos=new ArrayList<>();
        Collections.sort(robot);
        Arrays.sort(factory, (o1, o2) -> o1[0] - o2[0]);
        for(int[] e: factory){
            for(int j=0;j<e[1];j++){
                factoryPos.add(e[0]);
            }
        }
        memo=new long[robot.size()][factoryPos.size()];
        for (int i = 0; i < robot.size(); i++) {
            Arrays.fill(memo[i], Long.MAX_VALUE);
        }
        return solution(0, 0, robot, factoryPos);
    }

    public static void main(String[] args) {
        //** Requirement:
        //- There are some robots and factories on the (X-axis).
        //- You are given an integer array robot where robot[i] is the position of the (ith robot).
        //- You are also given a 2D integer array factory where factory[j] = [position-j, limit-j] indicates
        //  that (position-j) is the position of the (jth factory) and
        //  that (the jth factory) can repair (at most) (limit-j robots).
        //- The positions of each robot are unique. The positions of each factory are also unique.
        //* Note that (a robot) can be in the (same position) as (a factory) initially.
        //
        //- All (the robots) are (initially broken);
        //- They keep moving in (one direction).
        // The direction could be the (negative or the positive) direction of (the X-axis).
        //- When a robot reaches a factory that (did not reach) (its limit),
        //  the factory (repairs the robot), and it (stops moving).
        //- At any moment, you can set (the initial direction of moving) for some robot.
        //- Your target is to minimize (the total distance) traveled by (all the robots).
        //
        //* Return (the minimum total distance) traveled by all the robots.
        //  + Minimize distance mà robot đi (Đi sai)
        //- The test cases are generated such that all the robots can be repaired.
        //* Note that
        //- All robots move at the same speed.
        //- If two robots move in the same direction, they will never collide.
        //  + Nếu 2 robots move the same direction --> Never collide.
        //- If two robots move in (opposite directions) and they meet at some point, they do not collide. They cross each other.
        //  + Tức là nếu di chuyển ngược chiều --> Vẫn k collide (Không va chạm nhau)
        //- If a robot passes by a factory that reached its limits, it crosses it as if it does not exist.
        //  + Nếu 1 robot pass qua 1 factory --> Nó sẽ pass factory coi như không tồn tại.
        //- If the robot moved from a position x to a position y, the distance it moved is |y - x|.
        //  + Nếu robot move từ (x -> y), distance move sẽ là |y-x|
        //
        // Idea
        //1.
        //1.0,
        // Method-1:
        //- Constraint
        //1 <= robot.length, factory.length <= 100
        //factory[j].length == 2
        //-10^9 <= robot[i], position-j <= 10^9
        //0 <= limit-j <= robot.length
        //The input will be generated such that it is always possible to repair every robot.
        //  + Length của robot và factory không lớn
        //  => Time: O(n*m)
        //  + robot[i] và pos <= 10^9:
        //      + Khá lớn.
        //
        //- Brainstorm
        //
        //Input: robot = [0,4,6], factory = [[2,2],[6,2]]
        //Output: 4
        //Explanation: As shown in the figure:
        //- The first robot at position 0 moves in the positive direction. It will be repaired at the first factory.
        //- The second robot at position 4 moves in the negative direction. It will be repaired at the first factory.
        //- The third robot at position 6 will be repaired at the second factory. It does not need to move.
        //The limit of the first factory is 2, and it fixed 2 robots.
        //The limit of the second factory is 2, and it fixed 1 robot.
        //The total distance is |2 - 0| + |2 - 4| + |6 - 6| = 4. It can be shown that we cannot achieve a better total distance than 4.
        //
        //y               y       y
        //        x               x
        //0 - 1 - 2 - 3 - 4 - 5 - 6
        //The total distance is |2 - 0| + |2 - 4| + |6 - 6| = 4.
        //It can be shown that we cannot achieve a better total distance than 4.
        //- Tìm distance nhỏ nhất nếu robot moves
        //- Each robot sẽ chọn được factory tối ưu để repair
        //- Vấn đề khi 1 robot repair tại 1 factory:
        //  + Factory đó sẽ giảm limit đi ==> Các robot có thể đến đây để repair sẽ giảm đi
        //Ex:
        //            y       y
        //x                       x
        //0 - 1 - 2 - 3 - 4 - 5 - 6
        //- giả sử factory[1] = [6,1]
        //  + Chỉ repair được 1 robot
        //  + Ở đây sẽ ưu tiên sửa robot[5] thay vì robot[3] vì nếu sửa robot[3]
        //  ==> robot[5] sẽ cần đi xa hơn đến factory[0]=[0,1] để repair
        //Ex:
        //            y       y
        //                        x   x
        //0 - 1 - 2 - 3 - 4 - 5 - 6 - 7 - 8
        //- Case mà robot[3],robot[5] move cùng chiều đến:
        //  + factory[6], factory[7]
        //  ==> Kiểu gì move cũng duplicate [5,6] ==> Move kiểu gì cũng cho cùng kết quả
        //Ex:
        //    y                       y
        //                    x           x
        //0 - 1 - 2 - 3 - 4 - 5 - 6 - 7 - 8
        //+ Với case này:
        //rs = [1,5] + [7,8]
        //
        //- Bài này có khả năng dùng min heap để làm:
        //  + Với element là khoảng cách giữa các robot với factory
        //      + Chọn khoảng cách ngắn nhất trước (Vì robot đó sẽ đến trước do smaller distance)
        //- Cách giải tìm tất cả khoảng cách từ:
        //  + Factory[i] -> robot[j]
        //- Add all vào list
        //- Sort theo khoảng cách
        //- Lấy từ thằng nhỏ nhất ==> loại trừ dần các factory nếu đã sửa xong.
        //
        //- Có case nào mà factory[i] lấy robot gần nhất --> Kết quả sai hay không?
        //Ex:
        //    y               y
        //        x                                          x
        //0 - 1 - 2 - 3 - 4 - 5 - 6 - 7 - 8 - 9 - 10 - 11 - 12
        //+ Ta thấy rằng nếu không chọn [1,2], [5,12] thì sẽ không tối ưu
        //
        //- Ta sẽ add vào priority queue:
        //  + Mỗi lần ta sẽ (lấy min distance) + (pop ra) cho đến khi (gặp robot chưa xét)
        //
        //Ex:
        //    y       y
        //        x                   x
        //0 - 1 - 2 - 3 - 4 - 5 - 6 - 7 - 8 - 9 - 10 - 11 - 12
        //
        //Ex:
        //                    y       y
        //            x           x
        //0 - 1 - 2 - 3 - 4 - 5 - 6 - 7 - 8 - 9 - 10 - 11 - 12
        // [3,5] + [6,7] = 2 + 1 = 3
        // [3,7] + [5,7] = 4 + 2 = 6
        //  ==> case này khá khó
        //
        Integer[] robot = {0,4,6};
        int[][] factory = {{2,2},{6,2}};
//        Integer[] robot = {1,-1};
//        int[][] factory = {{-2,1},{2,1}};
//        Integer[] robot = {1,5};
//        int[][] factory = {{2,1},{12,1}};
        //Special cases:
//        Integer[] robot = {1,3};
//        int[][] factory = {{2,1},{7,1}};
//        Integer[] robot = {9,11,99,101};
//        int[][] factory = {{10,1},{7,1},{14,1},{100,1},{96,1},{103,1}};
        //
        //- Nếu nhìn về hình vẽ thì ta thiết kế làm sao cho khi nối (robot vs factory):
        //  + Nó sẽ ít nhất đường cut nhau nhất có thể (Không có thì càng tốt)
        //  ==> kể cả suy luận ntn thì sẽ bị case dist bằng nhau
        //
        //- Length nhỏ nên làm backtrack được không + memo?
        //* Exp:
        //- Mỗi factory + count sẽ có các cách biểu diễn như sau:
        //  + Add count số lượng factory
        //  + Sử dụng hashmap để biểu diễn số lượng factory còn lại
        //- Recursive như sau:
        //  + Assign (current robot) to (current factory)
        //  + Skip (current factory) for (the current robot)
        //- Ta sẽ add multiple the position of the factory để:
        //  + Ta có thể next factoryIndex dễ dàng hơn.
        //
        //1.1, Optimization
        //1.2, Complexity
        //- Count <= n:
        //- Time: O(n^2*m)
        //- Space: O(n*m*n)
        //
//        System.out.println(minimumTotalDistanceWrong(Arrays.asList(robot), factory));
        System.out.println(minimumTotalDistance(Arrays.asList(robot), factory));
        //
        //#Reference:
        //2585. Number of Ways to Earn Points
    }
}
