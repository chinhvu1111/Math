package E1_daily;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class E282_PutMarblesInBags_hard {

    public static long putMarbles(int[] weights, int k) {
        int n=weights.length;
        PriorityQueue<Integer> maxHeap=new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2-o1;
            }
        });
        PriorityQueue<Integer> minHeap=new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1-o2;
            }
        });

        for(int i=0;i<n-1;i++){
            maxHeap.add(weights[i]+weights[i+1]);
            minHeap.add(weights[i]+weights[i+1]);
        }
        long maxSum=weights[0]+weights[n-1];
        long minSum=maxSum;

        for(int i=1;i<k;i++){
            if(!maxHeap.isEmpty()){
                maxSum+=maxHeap.poll();
            }
            if(!minHeap.isEmpty()){
                minSum+=minHeap.poll();
            }
        }
//        System.out.printf("max: %s\n", maxSum);
//        System.out.printf("min: %s\n", minSum);
        return maxSum-minSum;
    }

    public static long putMarblesRefer(int[] weights, int k) {
        int n=weights.length;
        int[] pairWeights=new int[n-1];

        for (int i = 0; i < n-1; i++) {
            pairWeights[i]=weights[i]+weights[i+1];
        }
        Arrays.sort(pairWeights);
        long rs=0;

        for (int i = 0; i < k-1; i++) {
            rs+=pairWeights[n-2-i]-pairWeights[i];
        }
        return rs;
    }

    public static void main(String[] args) {
        //** Requirement
        //- You have (k bags).
        //- You are given (a 0-indexed integer array weights) where weights[i] is the weight of (the ith marble).
        //- You are also given the integer k.
        //- Divide (the marbles) into (the k bags) according to the following rules:
        //  + (No bag) is empty.
        //  + If (the ith marble) and (jth marble) are in (a bag), then (all marbles) with an index
        //  between the (ith) and (jth) indices should also be in (that same bag).
        //  Ex: (i=0), (i=3) in the same bag ==> (i=1,2) are also in this bag
        //
        //  + If a bag consists of (all the marbles) with (an index) from (i to j inclusively),
        //  then (the cost of the bag) is (weights[i] + weights[j]).
        //
        //- The score after (distributing the marbles) is (the sum of the costs) of (all the k bags).
        //* Return the difference between the (maximum and minimum) scores among (marble distributions).
        //
        //Example 1:
        //
        //Input: weights = [1,3,5,1], k = 2
        //Output: 4
        //Explanation:
        //The distribution [1],[3,5,1] results in the minimal score of (1+1) + (3+1) = 6.
        //The distribution [1,3],[5,1], results in the maximal score of (1+3) + (5+1) = 10.
        //Thus, we return their difference 10 - 6 = 4.
        //
        //** Idea
        //1.
        //1.0,
        //- Method-1:
        //+ Constraints:
        //1 <= k <= weights.length <= 10^5
        //1 <= weights[i] <= 10^9
        //  + Length<=10^5 ==> Time: O(n*k)
        //  + weights[i]<=10^9 ==> Long
        //
        //* Brainstorm:
        //- How to partition (the weights) such that the score is:
        //  + Min
        //  + Max
        //
        //Example 1:
        //
        //Input: weights = [1,3,5,1], k = 2
        //Output: 4
        //[1,3],[5,1]
        //  + score = 3+5
        //==> k ranges
        //
        //Ex:
        //arr = [3,4,1,2,6,7]
        //[a,b],[c,d],[e,f]
        //=> Score = a+b+c+d+e+f
        //
        //==> [3,(4,1),(2,6),7]
        //  + Choose (k-1) pairs
        //  Such that:
        //  + (Max/ min) sum
        //
        //Ex:
        //[1,(3,4),(5,6),2,10]
        //[1,3,(4,5),(6,2),10]
        //- They are not sole
        //Ex:
        //[1,3,(4,5),(6,2),10,(2,3),5,8]
        //  + Not (10,2)
        //
        //Ex:
        //[(1,3,4,5),6,(2,10,2,3),5,8]
        //
        //Ex:
        //- weights = [10,3,5,1,1,1]
        //- k = 2
        //13+6 - 4
        //10+10+4 - 11-2
        //([10,10][3,1]),([10,1],[1,1])
        //==> Phải chia hết
        //
        //Ex:
        //[(1,3,4,5),6,(2,10,2,3),5,8]
        //==> Không chia cách ra ntn
        //
        //Ex:
        //[(1,3,4),(5,6,2,10),(2,3,5,8)]
        //<=>
        //[1,3,(4,5),6,2,(10,2),3,5,8]
        //
        //Ex:
        //[(1,3,4),(5),(6,2,10,2,3,5,8)]
        //<=>
        //[1,(4,5),(5,6),8]
        //==> From (4,5,6) we can get (4,5),(5,6)
        //
        //Ex:
        //[1,2,3]
        //[(1),(2,3)]
        //<=>
        //[(1,2),3]
        //
        //[(1,2),(3)]
        //<=>
        //[1,(2,3)]
        //- Use max, min heap
        //
        //1.1, Case
        //
        //
        //1.2, Optimization
        //- Use sort array:
        //  + Return arr[n-1]-arr[0]
        //      ==> The overlap nums[0]+nums[n-1] will be removed
        //
        //1.3, Complexity
        //- Space: O(n)
        //- Time: O(n*log(n)+k*log(n))
        //
        int[] weights = {1,3,5,1};
        int k = 2;
        System.out.println(putMarbles(weights, k));
        System.out.println(putMarblesRefer(weights, k));
        //
        //#Reference:
        //2589. Minimum Time to Complete All Tasks - hard
        //527. Word Abbreviation - hard
        //3311. Construct 2D Grid Matching Graph Layout - hard
        //
        //1311. Get Watched Videos by Your Friends - med
        //1187. Make Array Strictly Increasing - hard
        //295. Find Median from Data Stream - hard
    }
}
