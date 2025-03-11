package contest;

public class E295_MaximumPointsYouCanObtainFromCards {

    public static int maxScore(int[] cardPoints, int k) {
        int n=cardPoints.length;

        if(n<=k){
            int sum=0;

            for(int i=0;i<n;i++){
                sum+=cardPoints[i];
            }
            return sum;
        }
        int left=0, right=0;

        for(int i=0;i<k;i++){
            left+=cardPoints[i];
        }
        int p1=n-1;
        int rs=left;

        for(int i=k-1;i>=0;i--){
            left-=cardPoints[i];
            right+=cardPoints[p1--];
            rs=Math.max(left+right, rs);
        }
        return rs;
    }

    public static int maxScoreRefer(int[] cardPoints, int k) {
        int n=cardPoints.length;
        int left=0, right=0;

        for(int i=0;i<k;i++){
            left+=cardPoints[i];
        }
        if(n<=k){
            return left;
        }
        int p1=n-1;
        int rs=left;

        for(int i=k-1;i>=0;i--){
            left-=cardPoints[i];
            right+=cardPoints[p1--];
            rs=Math.max(left+right, rs);
        }
        return rs;
    }

    public static void main(String[] args) {
        //** Requirement
        //- There are (several cards) arranged in a row, and (each card) has (an associated number of points).
        //- The points are given in (the integer array cardPoints).
        //- In one step, you can take one card from (the beginning) or from (the end) of the row.
        //  + You have to take exactly k cards.
        //- Your score is the sum of the points of the cards you have taken.
        //- Given (the integer array cardPoints) and (the integer k),
        //* return (the maximum score) you can obtain.
        //
        //Example 1:
        //
        //Input: cardPoints = [1,2,3,4,5,6,1], k = 3
        //Output: 12
        //Explanation:
        // After the first step, your score will always be 1.
        // However, choosing the rightmost card first will maximize your total score.
        //The optimal strategy is to take the three cards on the right, giving a final score of 1 + 6 + 5 = 12.
        //
        // Idea
        //1
        //1.0,
        //- Method-1:
        //+ Constraints:
        //1 <= cardPoints.length <= 10^5
        //1 <= cardPoints[i] <= 10^4
        //1 <= k <= cardPoints.length
        //  + Length <=10^5 ==> Time: O(n)*k
        //
        //- Brainstorm
        //Ex:
        //arr = [1,100,2,3,1,8,9], k=3
        //  + rs = [1,100,9]
        //- left: right:
        //  + i: j:
        //      + (i+j == k)
        //
        //- left[i]:
        //  + How many card at (ith) from 0 to i?
        //+ rs = left[i] + right[k-i]
        //- Can we use two pointers?
        //  + Yes we can do
        //
        //1.1, Cases
        //1.2, Optimize
        //- Reduce the length of the code:
        //  + Remove the duplicated code
        //
        //1.3, Complexity
        //- Space: O(1)
        //- Time: O(k)
        //
        int[] cardPoints = {1,2,3,4,5,6,1};
        int k = 3;
        System.out.println(maxScore(cardPoints, k));
        System.out.println(maxScoreRefer(cardPoints, k));
        //
        //#Reference:
        //2091. Removing Minimum and Maximum From Array
        //2931. Maximum Spending After Buying Items
    }
}
