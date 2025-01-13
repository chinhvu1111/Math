package E1_daily;

public class E222_MinimumNumberOfOperationsToMoveAllBallsToEachBox {

    public static int[] minOperations(String boxes) {
        int n=boxes.length();
        int[] prefixCount=new int[n];
        int[] suffixCount=new int[n];
        int[] leftMovementNum=new int[n];
        int[] rightMovementNum=new int[n];
        prefixCount[0]=boxes.charAt(0)-'0';
        suffixCount[n-1]=boxes.charAt(n-1)-'0';

        for(int i=1;i<n;i++){
            prefixCount[i]=prefixCount[i-1]+boxes.charAt(i)-'0';
            leftMovementNum[i]=prefixCount[i-1]+leftMovementNum[i-1];
        }
        for(int i=n-2;i>=0;i--){
            suffixCount[i]=suffixCount[i+1]+boxes.charAt(i)-'0';
            rightMovementNum[i]=suffixCount[i+1]+rightMovementNum[i+1];
        }
        int[] rs=new int[n];
        rs[0]=rightMovementNum[0];
        rs[n-1]=leftMovementNum[n-1];

        for(int i=1;i<n-1;i++){
            rs[i]=rightMovementNum[i]+leftMovementNum[i];
        }
        return rs;
    }

    public static int[] minOperationsReference(String boxes) {
        int n = boxes.length();
        int[] answer = new int[n];

        int ballsToLeft = 0, movesToLeft = 0;
        int ballsToRight = 0, movesToRight = 0;

        // Single pass: calculate moves from both left and right
        for (int i = 0; i < n; i++) {
            // Left pass
            answer[i] += movesToLeft;
            ballsToLeft += Character.getNumericValue(boxes.charAt(i));
            movesToLeft += ballsToLeft;

            // Right pass
            int j = n - 1 - i;
            answer[j] += movesToRight;
            ballsToRight += Character.getNumericValue(boxes.charAt(j));
            movesToRight += ballsToRight;
        }

        return answer;
    }

    public static void main(String[] args) {
        //** Requirement
        //- You have n boxes. You are given (a binary string boxes) of (length n),
        // where
        //  + boxes[i] is '0' if (the ith box) is empty,
        //  + and '1' if it contains (one ball).
        //- In (one operation), you can move one ball from (a box) to (an adjacent box).
        //- (Box i) is adjacent to (box j) if abs(i - j) == 1.
        //* Note that after doing so, there may be (more than one ball) in some boxes.
        //* Return (an array answer of size n), where (answer[i] is the minimum number of operations) needed to move (all the balls) to (the ith box).
        //- Each answer[i] is calculated considering (the initial state of the boxes).
        //
        //- Minimum (the number of movement) to move from (all of balls) to (ith box).
        //
        //Example 1:
        //
        //Input: boxes = "110"
        //Output: [1,1,3]
        //Explanation: The answer for each box is as follows:
        //1) First box: you will have to move one ball from the second box to the first box in one operation.
        //2) Second box: you will have to move one ball from the first box to the second box in one operation.
        //3) Third box: you will have to move one ball from the first box to the third box in two operations,
        // and move one ball from the second box to the third box in one operation.
        //
        // Idea
        //1.
        //1.0,
        //- Method-1:
        //+ Constraints:
        //n == boxes.length
        //1 <= n <= 2000
        //boxes[i] is either '0' or '1'.
        //  + n<=2000 ==> Time: O(n^2)
        //
        //- Brainstorm
        //Ex:
        //Input: boxes = "110"
        //Output: [1,1,3]
        //i=0: [0],1,0
        //i=1: 1,[0],0
        //i=2: 2,1,[0]
        //- prefix, suffix
        //  + We can use that.
        //1101[0]11
        //1303[0]11
        //- The meaning of dp[i]
        //- accumulate the number of movement:
        //1 -> 1
        //count = 1
        //0 -> 2
        //2 -> 1
        //count+=2
        //
        //- dp[i]: The number of movement we need to do move the (all balls) from the left to the current (index=i)?
        //- prefixCount[i]: The number of ball we have at (the ith position)
        //- Formula:
        //  + prefixCount[i]=prefixCount[i-1]+boxes.charAt(i)-'0';
        //  + leftMovementNum[i]=prefixCount[i-1]+leftMovementNum[i-1];
        //
//        String boxes = "110";
        //
        //1.1, Optimization
        //- We have same idea but we remove space for (the prefixCount and leftMove)
        //
        //1.2, Complexity
        //- Space: O(n)
        //- Time: O(n)
        //
        String boxes = "001011";
//        int[] rs=minOperations(boxes);
        int[] rs=minOperationsReference(boxes);

        for (int i = 0; i < rs.length; i++) {
            System.out.printf("%s,", rs[i]);
        }
        System.out.println();
        //#Reference:
        //1217. Minimum Cost to Move Chips to The Same Position
        //2850. Minimum Moves to Spread Stones Over Grid
    }
}
