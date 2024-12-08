package E1_daily;

import java.util.*;

public class E179_MaximumNumberOfIntegersToChooseFromARangeII {

    public static int findValidIndex(List<Integer> bannedList, long[] prefixSum, long val){
        int low=0;
        int high=bannedList.size()-1;
        int rs=-1;

        while (low<=high){
            int mid=low+(high-low)/2;
            if(bannedList.get(mid)<=val){
                rs=mid;
                low=mid+1;
            }else{
                high=mid-1;
            }
        }
        return rs;
    }

    public static int maxCount(int[] banned, int n, long maxSum) {
        int m=banned.length;
        List<Integer> bannedList=new ArrayList<>();
        HashSet<Integer> bannedSet=new HashSet<>();

        for (int i = 0; i < m; i++) {
            if(!bannedSet.contains(banned[i])){
                bannedList.add(banned[i]);
            }
            bannedSet.add(banned[i]);
        }
        //Time: O(m*log(m))
        Collections.sort(bannedList);
        long low=1;
        long high=n;
        long sum=0;
        int rs=0;
        //1,2,(3,4,5,6)
        //+ high*(high+1)/2 - low*(low+1)/2
        //banned = [1,4,6]
        //==> prefix sum
        //banned_sum = [1,5,11]
        //- Base on banned array, we find the correct index such that:
        //- Find the index of banned such that:
        //  + banned[index]<= mid
        //  + maximize banned[index]
        //- For the current sum:
        //  + mid*(mid+1) - prefixSum[index] <= maxSum
        //      + low=mid+1
        //  <> high=mid-1
        m=bannedList.size();
        long[] prefixSum=new long[m];

        for(int i=0;i<m;i++){
            sum+=bannedList.get(i);
            prefixSum[i]=sum;
        }
//        System.out.printf("low: %s, high: %s, sum: %s\n", low, high, sum);

        //Time: O(log(n))
        while(low<=high){
            long mid=low+(high-low)/2;
            //Time: O(log(m))
            int validIndex = findValidIndex(bannedList, prefixSum, mid);
//            System.out.printf("mid:%s, index:%s\n", mid, validIndex);
            long bannedVal = 0L;
            if(validIndex!=-1){
                bannedVal=prefixSum[validIndex];
            }
            if(mid*(mid+1)/2-bannedVal<=maxSum){
                rs= (int) (mid - validIndex-1);
                low=mid+1;
            }else{
                high=mid-1;
            }
        }
        return rs;
    }

    public static void main(String[] args) {
        //** Requirement
        //- You are given (an integer array banned) and two integers (n and maxSum).
        //- You are choosing some number of integers following the below rules:
        //  + The chosen integers have to be in the range [1, n].
        //  + Each integer can be chosen (at most once).
        //  + The chosen integers should not be in the (array banned).
        //  + The sum of the chosen integers should not exceed maxSum.
        //* Return the maximum number of integers you can choose following the mentioned rules.
        //
        // Idea
        //1.
        //1.0,
        //- Method-1:
        //+ Constraints:
        //1 <= banned.length <= 10^5
        //1 <= banned[i] <= n <= 10^9
        //1 <= maxSum <= 10^15
        //
        //- Brainstorm
        //
//        int[] banned = {1,4,6};
//        int n = 6, maxSum = 4;
//        int[] banned = {4,3,5,6};
        //3,4,5,6
//        int n = 7, maxSum = 18;
        //- Special cases:
        //- Duplicated elements
        //  ==> distinct
        int[] banned = {1,1};
        //3,4,5,6
        int n = 2, maxSum = 2;
        //
        //* Solution:
        //1,2,(3,4,5,6)
        //+ high*(high+1)/2 - low*(low+1)/2
        //banned = [1,4,6]
        //==> prefix sum
        //banned_sum = [1,5,11]
        //- Base on banned array, we find the correct index such that:
        //- Find the index of banned such that:
        //  + banned[index]<= mid
        //  + maximize banned[index]
        //- For the current sum:
        //  + mid*(mid+1) - prefixSum[index] <= maxSum
        //      + low=mid+1
        //  <> high=mid-1
        //
        //1.1, Optimization
        //1.2, Complexity
        //- Space: O(m)
        //- Time: O((log(n)+m)*log(m))
        //
        //#Reference:
        //41. First Missing Positive
        System.out.println(maxCount(banned, n, maxSum));
    }
}
