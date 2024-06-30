package E1_daily;

import java.util.Arrays;

public class E30_HeightChecker {

    public static int heightChecker(int[] heights) {
        int rs=0;
        int n=heights.length;
        int maxHeight= Arrays.stream(heights).max().getAsInt();
        int[] count=new int[maxHeight+1];

        //Ex:
        //heights = [1,1,4,2,1,3]
        //sort    = [1,1,1,2,3,4]
        //count =   [1,2,3,4,5,6]
        //count =   [3,1,1,1,0,0]
        //
        for(int i=0;i<n;i++){
            count[heights[i]]++;
        }
        int j=0;
        for(int i=0;i<=maxHeight;i++){
            if(count[i]!=0){
                while (count[i]>0){
                    if(i!=heights[j]){
                        rs++;
//                        System.out.printf("i=%s, val=%s, j=%s, height[j]=%s\n", i, count[i], j, heights[j]);
                    }
                    count[i]--;
                    j++;
                }
            }
        }
        return rs;
    }

    public static void main(String[] args) {
        //** Requirement
        //- A school is trying to take an annual photo of all the students.
        // The students are asked to stand in a single file line in (non-decreasing order by height).
        // Let this ordering be represented by the integer array expected where expected[i] is the expected height of the ith student in line.
        //You are given an integer array heights representing the current order that the students are standing in.
        // Each heights[i] is the height of the ith student in line (0-indexed).
        //* Return the number of indices where heights[i] != expected[i].
        //
        //** Idea
        //1.
        //1.0,
        //* Method-1:
        //- Constraint:
        //+ 1 <= heights.length <= 100
        //+ 1 <= heights[i] <= 100
        //
        //- Brainstorm
        //
        //
        //1.1, Optimization
        //
        //1.2, Complexity
        //- Space: O(maxHeight)
        //- Time: O(maxHeight+n)
        //#Reference:
        //2402. Meeting Rooms III
        //1984. Minimum Difference Between Highest and Lowest of K Scores
        //2517. Maximum Tastiness of Candy Basket
        int[] heights = {1,1,4,2,1,3};
        System.out.println(heightChecker(heights));
    }
}
