package E1_heap_priority_queue;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.PriorityQueue;

public class E13_MiceAndCheese {

    public static int miceAndCheese(int[] reward1, int[] reward2, int k) {
        int sumSecond=0;
        int n=reward1.length;
        PriorityQueue<int[]> subtraction=new PriorityQueue<>(new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                if(o1[0]!=o2[0]){
                    return o2[0]-o1[0];
                }
                return o1[1]-o2[1];
            }
        });

        for(int i=0;i<n;i++){
            sumSecond+=reward2[i];
            subtraction.add(new int[]{reward1[i]-reward2[i], i});
        }
        int rs=0;
        int count=0;

        while (!subtraction.isEmpty()&&count<k){
            int[] curVal=subtraction.poll();
            rs+=reward1[curVal[1]];
            sumSecond-=reward2[curVal[1]];
            count++;
        }
        rs+=sumSecond;
        return rs;
    }

    public static int miceAndCheeseOptimization(int[] reward1, int[] reward2, int k) {
        int sumSecond=0;
        int n=reward1.length;
        int[][] firstVal=new int[n][2];

        for(int i=0;i<n;i++){
            sumSecond+=reward2[i];
            firstVal[i][0]=reward1[i]-reward2[i];
            firstVal[i][1]=i;
        }
        Arrays.sort(firstVal, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                if(o1[0]!=o2[0]){
                    return o2[0]-o1[0];
                }
                return o1[1]-o2[1];
            }
        });
        int rs=0;
        int count=0;

        while (count<k){
            int[] curVal=firstVal[count];
            rs+=reward1[curVal[1]];
            sumSecond-=reward2[curVal[1]];
            count++;
        }
        rs+=sumSecond;
        return rs;
    }

    public static void main(String[] args) {
        //** Requirement
        //- There are (two mice) and (n different types of cheese),
        // (each type of cheese) "should" be eaten by (exactly) (one mouse).
        //- (A point of the cheese) with index i (0-indexed) is:
        //  + reward1[i] if the first mouse eats it.
        //  + reward2[i] if the second mouse eats it.
        //- You are given
        //  + (a positive integer array reward1),
        //  + (a positive integer array reward2), and
        //  + (a non-negative) integer k.
        //* Return (the maximum points) the mice can achieve if the ("first mouse") eats exactly (k types of cheese).
        //
        //** Idea
        //1.
        //1.0,
        //* Method-1:
        //- Constraint:
        //1 <= n == reward1.length == reward2.length <= 10^5
        //1 <= reward1[i], reward2[i] <= 1000
        //0 <= k <= n
        //  + n khá lớn ==> Time: O(n)
        //
        //- Brainstorm
        //
        //Example 1:
        //
        //Input: reward1 = [1,1,3,4], reward2 = [4,4,1,1], k = 2
        //Output: 15
        //Explanation: In this example, the first mouse eats the 2nd (0-indexed) and the 3rd types of cheese,
        // and the second mouse eats the (0th) and the (1st) types of cheese.
        //The total points are 4 + 4 + 3 + 4 = 15.
        //It can be proven that 15 is the maximum total points that the mice can achieve.
        //
        //- Tức là first mouse luôn eat k thằng
        //- Second mouse sẽ eat cái còn lại
        //- Lấy sao tổng cả 2 mices ăn max
        //Ex
        //[1,1,3,4]
        //[4,4,1,1]
        //+ first mice sẽ chọn ntn:
        //  + Chọn sao cho first mice cho kết quả max
        //- Ta sẽ xét hiệu của 2 thằng:
        //  + sort giảm dần
        //      ==> Chọn là được.
        //
        //1.1, Optimization
        //1.2, Complexity
        //- Space: O(n+log(n))
        //- Time: O(n*log(n))
        //
        int[] reward1 = {1,1,3,4};
        int[] reward2 = {4,4,1,1};
        int k = 2;
        System.out.println(miceAndCheese(reward1, reward2, k));
        System.out.println(miceAndCheeseOptimization(reward1, reward2, k));
        //
        //#Reference:
        //2141. Maximum Running Time of N Computers
        //1502. Can Make Arithmetic Progression From Sequence
        //3049. Earliest Second to Mark Indices II
//        1736. Latest Time by Replacing Hidden Digits
//        3025. Find the Number of Ways to Place People I
//        1899. Merge Triplets to Form Target Triplet
    }
}
