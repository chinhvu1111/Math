package E1_daily;

import java.util.Comparator;
import java.util.TreeSet;

public class E310_PreviousPermutationWithOneSwap_classic_permutation {

    public static int[] prevPermOpt1(int[] arr) {
        int n=arr.length;
        TreeSet<int[]> set=new TreeSet<>(new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                if(o1[0]!=o2[0]){
                    return o1[0]-o2[0];
                }
                return o2[1]-o1[1];
//                return o1[0]-o2[0];
            }
        });
        int[] rs=new int[n];
        int i;
        int x=-1, y=-1;
        for(i=n-1;i>=0;i--){
            //1,2,3,4,5
            //search: (arr[i]-1, i+1)
            //<= (arr[i]-1, i+1)
            //- (i) need to be decrease
            //  + We need to search index>=i+1 (MAX)
            //
            int[] curVal=new int[]{arr[i]-1, i+1};
            int[] smallerElement = set.floor(curVal);
//            System.out.printf("Index: %s, search: %s\n", i, curVal[0]);
            if(smallerElement!=null){
//                System.out.printf("%s, \n", smallerElement[0]);
//                rs[i]=smallerElement[0];
                x=smallerElement[1];
                y=i;
//                set.remove(smallerElement);
                break;
            }
//            rs[i]=arr[i];
            set.add(new int[]{arr[i], i});
        }
//        System.out.printf("%s %s\n", x, y);
        for (int j = 0; j < n; j++) {
            if(j==x){
                rs[j]=arr[y];
            }else if(j==y){
                rs[j]=arr[x];
            }else{
                rs[j]=arr[j];
            }
        }
        return rs;
    }

    public static int[] prevPermOpt1Refer(int[] nums) {
        int idx = nums.length - 2, i = nums.length - 1;
        //find the first i, that A[i] > A[i + 1]
        while (idx >= 0 && nums[idx] <= nums[idx + 1]) idx--;
        if (idx < 0) return nums;
        //find the largest A[i] and smallest i,  that make A[i] < A[idx], where i > idx;
        while (nums[i] >= nums[idx] || (i > 0 && nums[i] == nums[i - 1])) i--;
        int tmp = nums[i];
        nums[i] = nums[idx];
        nums[idx] = tmp;
        return nums;
    }

    public static void main(String[] args) {
        //** Requirement
        //- Given (an array of positive integers arr) (not necessarily distinct),
        //* return (the lexicographically ("largest") permutation) that is (smaller than) arr,
        // that can be made with (exactly one) swap.
        //- If it (cannot be done), then return (the same array).
        //* Note that a swap exchanges the positions of two numbers arr[i] and arr[j]
        //
        //Example 1:
        //
        //Input: arr = [3,2,1]
        //Output: [3,1,2]
        //Explanation: Swapping 2 and 1.
        //
        //** Idea
        //1.
        //1.0,
        //- Method-1:
        //+ Constraints:
        //1 <= arr.length <= 10^4
        //1 <= arr[i] <= 10^4
        //  + arr.length <= 10^4 ==> Time: O(n*k)
        //
        //* Brainstorm:
        //
        //Example 3:
        //
        //Input: arr = [1,9,4,6,7]
        //Output: [1,7,4,6,9]
        //Explanation: Swapping 9 and 7.
        //
        //[1,9,4,6,7]
        //[1,7,4,6,9]
        //==>
        //[1,9,4,6,7]
        //==>
        //+ replace 1 by smaller
        //+ replace 9 by smaller but biggest
        //  + 7 ==> Valid
        //- TreeSet
        //==> WRONG
        //Example 1:
        //
        //Input: arr = [3,2,1]
        //Output: [3,1,2]
        //Explanation: Swapping 2 and 1.
        //
        //- Search from (right -> left):
        //  + At (index=i): We find the element rearest to (the current index=i)
        //  ==> Remove index out of treeSet
        //
        //1.1, Case
        //
        //
        //* Main point:
        //
        //
        //1.2, Optimization
        //* Main point:
        //- Find the first pair from (right to left) such that:
        //  + i>j, nums[i]>nums[j]
        //- find the largest nums[i] and (smallest i),  that make A[i] < A[idx], where i > idx;
        //Ex:
        //(3,1),1,3
        //==> choose (3,1) with (i=0,j=1)
        //Ex:
        //3,1,1,(3,1)
        //==> choose (3,1) with (i=3,j=4)
        //
        //1.3, Complexity
        //- Space: O(n) ==> O(1)
        //- Time: O(n*log(n)) ==> O(n)
        //
//        int[] arr = {1,9,4,6,7};
//        int[] arr = {3,2,1};
        //3,2,1
        //==> 3,1,2
        //Ex:
        int[] arr = {3,1,1,3};
        //3,1,1,3
        //=>
        //1,3,1,3
        //Expected: 1,3,1,3
//        int[] rs= prevPermOpt1(arr);
        int[] rs= prevPermOpt1Refer(arr);
        int n=rs.length;

        for (int i = 0; i < n; i++) {
            System.out.printf("%s, ", rs[i]);
        }
        //
        //#Reference:
        //2718. Sum of Matrix After Queries
        //2923. Find Champion I
        //1655. Distribute Repeating Integers
    }
}
