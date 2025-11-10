package contest;

import java.util.ArrayList;
import java.util.List;

public class E315_MinimumPairRemovalToSortArrayI {

    static boolean isValid(List<Integer> nums, int n){
        for (int i = 1; i < n; i++) {
            if(nums.get(i)<nums.get(i-1)){
                return false;
            }
        }
        return true;
    }

    public static int minimumPairRemoval(int[] nums) {
        int rs=0;
        List<Integer> numsList=new ArrayList<>();

        for (int i = 0; i < nums.length; i++) {
            numsList.add(nums[i]);
        }
        while (!isValid(numsList, numsList.size())){
            int minAdj=Integer.MAX_VALUE;
            int index=-1;
            int size = numsList.size();
            List<Integer> newList=new ArrayList<>();
            for (int i = 0; i+1 < size; i++) {
                if(numsList.get(i)+numsList.get(i+1)<minAdj){
                    minAdj=numsList.get(i)+numsList.get(i+1);
                    index=i;
                }
            }
            for (int i = 0; i < size; i++) {
                if(i!=index&&i!=index+1){
                    newList.add(numsList.get(i));
                }else if(i==index&&i+1<size){
                    newList.add(numsList.get(i)+numsList.get(i+1));
                }
            }
            numsList=newList;
//            System.out.println(numsList);
            rs++;
        }
        return rs;
    }

    public static void main(String[] args) {
        //- Given an array nums, you can perform the following operation any number of times:
        //  + Select the adjacent pair with the minimum sum in nums.
        //  If multiple such pairs exist, choose the leftmost one.
        //  + Replace the pair with their sum.
        //* Return the minimum number of operations needed to make the array non-decreasing.
        //
        //An array is said to be non-decreasing if each element
        // is greater than or equal to its previous element (if it exists).
        //
        //Example 1:
        //
        //Input: nums = [5,2,3,1]
        //Output: 2
        //
        //Explanation:
        //
        //The pair (3,1) has the minimum sum of 4. After replacement, nums = [5,2,4].
        //The pair (2,4) has the minimum sum of 6. After replacement, nums = [5,6].
        //The array nums became non-decreasing in two operations.
//        int[] nums = {5,2,3,1};
//        int[] nums = {1,2,2};
//        int[] nums = {1};
//        int[] nums = {3,1};
        int[] nums = {3,6,4,-6,2,-4,5,-7,-3,6,3,-4};
        //Output: 11
        //Expected: 10
        System.out.println(minimumPairRemoval(nums));
    }
}
