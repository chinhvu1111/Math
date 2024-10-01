package contest;

import java.util.Arrays;
import java.util.Collections;

public class E191_MaximizeTheTotalHeightOfUniqueTowers {

    public static long maximumTotalSum(int[] maximumHeight) {
        Arrays.sort(maximumHeight);
        int n=maximumHeight.length;
        long rs=maximumHeight[n-1];
        int prevMax= (int) rs;

        for(int i=n-2;i>=0;i--){
            int curMax=Math.min(prevMax-1, maximumHeight[i]);
            if(curMax<=0){
                return -1;
            }
            rs+=curMax;
            prevMax=curMax;
        }
        return rs;
    }

    public static void main(String[] args) {
        //* Requirement
        //- You are given an array maximumHeight, where maximumHeight[i] denotes the maximum height the ith tower can be assigned.
        //- Your task is to assign a height to each tower so that:
        //  + The height of the ith tower is a positive integer and does (not exceed) maximumHeight[i].
        //  + No two towers have the same height.
        //* Return (the maximum possible total sum of the tower heights).
        // If it's not possible to assign heights, return -1.
        //
        // Idea
        //1.
        //1.0,
        //- Constraint:
        //1 <= maximumHeight.length<= 10^5
        //1 <= maximumHeight[i] <= 10^9
        //  + length big --> TIme: O(n)
        //
        //** Brainstorm
        //Ex:
        //Input: maximumHeight = [2,3,4,3]
        //4,3,3,2
        //4,3,2,1
        //
        //- Nếu không điền được thì sao.
//        int[] maximumHeight = {2,3,4,3};
        int[] maximumHeight = {2,2,1};
        //2,1,0
        System.out.println(maximumTotalSum(maximumHeight));
    }
}
