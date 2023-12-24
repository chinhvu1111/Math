package E1_heap_priority_queue;

import java.util.PriorityQueue;

public class E5_MinimumCostToConnectSticks {

    public static int connectSticks(int[] sticks) {
        PriorityQueue<Integer> minSticks=new PriorityQueue<>();
        int n=sticks.length;
        int rs=0;

        for (int stick : sticks) {
            minSticks.add(stick);
        }

        while(minSticks.size()>1){
            int curCost=minSticks.poll()+minSticks.poll();
            minSticks.add(curCost);
            rs+=curCost;
        }
        return rs;
    }

    public static void main(String[] args) {
        // Requirement
        //- You have some number of sticks with positive integer lengths.
        // These lengths are given as an array sticks, where sticks[i] is the length of the (ith stick).
        //- You can connect any two sticks of lengths (x and y) into one stick by paying a cost of (x + y).
        // You must connect all the sticks until there is (only one stick remaining).
        //* Return the (minimum cost) of (connecting all the given sticks) into (one stick) in this way.
        //
        // Idea
        //1.
        //1.0,
        //- Constraint:
        //1 <= sticks.length <= 104
        //1 <= sticks[i] <= 104
        //
        //- Brainstorm
        //Ex:
        //sticks = [2,4,3] ==> [2,3,4]
        //1. Combine sticks 2 and 3 for a cost of 2 + 3 = 5. Now you have sticks = [5,4].
        //2. Combine sticks 5 and 4 for a cost of 5 + 4 = 9. Now you have sticks = [9].
        //There is only one stick left, so you are done. The total cost is 5 + 9 = 14.
        //->
        //2+4=6
        //6+3=9 => 6+9 = 15
        //- Để có thể get MIN cost?
        //Ex:
        //a, b, c
        //==> Sum chung có thể giống nhau ==> Intermediate sum cần phải MIN
        //- Greedy
        //- Mỗi lần lấy 2 sticks ra ta sẽ lấy ra 2 sticks có MIN length
        //  + Tạo ra stick mới = sum(a,b) => add vào queue
        //==> Pop ra ==> + ==> add vào
        //
        //1.1, Optimization
        //1.2, Complexity
        //- Space : O(N)
        //- Time : O(N*Log(N))
        //#Reference:
        //1000. Minimum Cost to Merge Stones
    }
}
