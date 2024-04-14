package contest;

import java.util.Arrays;
import java.util.Collections;

public class E50_DistributeElementsIntoTwoArraysII {

    public static int minimumBoxes(int[] apple, int[] capacity) {
        Arrays.sort(capacity);
        int sum=0;
        int rs=0;

        for(int i=0;i<apple.length;i++){
            sum+=apple[i];
        }
        for(int i=capacity.length-1;i>=0;i--){
            sum-=capacity[i];
            rs++;
            if(sum<=0){
                break;
            }
        }
        if(sum>0){
            return 0;
        }
        return rs;
    }

    public static void main(String[] args) {
        // Requirement
        //- You are given an (array apple) of size n and (an array capacity) of size m.
        //- There are n packs where the (ith pack) contains (apple[i] apples).
        //- There are m boxes as well, and the (ith box) has a capacity of (capacity[i] apples).
        //* Return (the minimum number of boxes) you need to select to redistribute these n packs of apples into boxes.
        //Note that, apples from the same pack can be distributed into different boxes.
        //
        // Idea
        //1.
        //1.0,
        //- Constraint:
        //1 <= n == apple.length <= 50
        //1 <= m == capacity.length <= 50
        //1 <= apple[i], capacity[i] <= 50
        //The input is generated such that it's possible to redistribute packs of apples into boxes.
        //
        //- Brainstorm
        //- Bài này dùng greedy được
        //
        int[] apple = {1,3,2}, capacity = {4,3,1,5,2};
        System.out.println(minimumBoxes(apple, capacity));
    }
}
