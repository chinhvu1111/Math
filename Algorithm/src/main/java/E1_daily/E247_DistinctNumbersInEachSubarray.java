package E1_daily;

import java.util.HashMap;

public class E247_DistinctNumbersInEachSubarray {

    public static int[] distinctNumbers(int[] nums, int k) {
        int n=nums.length;
        HashMap<Integer, Integer> mapCount=new HashMap<>();
        int[] rs=new int[n-k+1];

        for(int i=0;i<k;i++){
            mapCount.put(nums[i], mapCount.getOrDefault(nums[i], 0)+1);
        }
        int index=0;
        rs[index++]=mapCount.size();
        int start=0;

        for(int i=k;i<n;i++){
            int removedVal= nums[start++];
            int curRemovedValCount= mapCount.get(removedVal);
            if(curRemovedValCount==1){
                mapCount.remove(removedVal);
            }else{
                mapCount.put(removedVal, curRemovedValCount-1);
            }
            int addedVal= nums[i];
            int curAddedValCount= mapCount.getOrDefault(addedVal, 0);
            mapCount.put(addedVal, curAddedValCount+1);
            rs[index++]=mapCount.size();
        }
        return rs;
    }

    public static void main(String[] args) {
        //** Requirement
        //- Given (an integer array nums) and (an integer k),
        // you are asked to construct the array ans of size (n-k+1)
        // where ans[i] is (the number of distinct numbers) in
        // the subarray nums[i:i+k-1] = [nums[i], nums[i+1], ..., nums[i+k-1]].
        //
        //Return the array ans.
        //
        //** Idea
        //1.
        //1.0,
        //- Method-1:
        //+ Constraints:
        //1 <= k <= nums.length <= 10^5
        //1 <= nums[i] <= 10^5
        //  + n,k<=10^5 => Time: O(n*x)
        //
        //- Brainstorm
        //- Use hashmap as map count of each number in array
        //- We use slide window to remove the element out of the hashmap
        //
        //
        //1.1, Special cases
        //
        //1.2, Optimization
        //- While we typically think of hash map operations as taking constant time, this isn't always true.
        //- In reality, these operations are amortized O(1), meaning they usually take constant time but can sometimes slow down to O(n),
        // especially with large datasets. Hash maps also carry extra overhead due to their hash functions and pointer management.
        //- This is why sometimes it's better to use a frequency array instead of a hash map.
        //- A frequency array works similarly to a map, with the index of the array serving as the key
        // and the element stored at that index as the value.
        //- In this problem, using such an array is possible, as the largest value in nums is relatively small (nums[i]<=10
        //5
        // ).
        //- Overall, this approach is more time-efficient because array operations are guaranteed
        // to take constant time and don't have the extra overhead that hash maps do.
        //
        //- We create a frequency array called freq to track how often each number appears.
        // As we slide our window across the array nums, we update these frequencies just like we did with the hash map approach.
        //
        //- However, since we can't simply check the size of our data structure to count distinct elements anymore,
        // we need a new strategy. We'll use a variable called distinctCount to keep track of how many different numbers we have.
        // When we add a new number and its frequency becomes 1, we increase distinctCount because we've found a new unique number.
        // Similarly, when we remove a number and its frequency drops to 0, we decrease distinctCount because we've lost a unique number.
        //
        //- After each window moves, we store the current value of distinctCount in our answer array.
        //- Once we've checked all windows, we return this array as our final answer.
        //
        //1.3, Complexity
        //- Space: O(n)
        //- Time: O(n)
//        int[] nums = {1,2,3,2,2,1,3};
//        int k = 3;
//        int[] nums = {1,1,1,1,2,3,4};
//        int k = 4;
        int[] nums = {1,1,1,1};
        int k = 1;
        int[] rs= distinctNumbers(nums, k);
        for(int i=0;i<rs.length;i++){
            System.out.printf("%s, ", rs[i]);
        }
        //#Reference:
        //955. Delete Columns to Make Sorted II
        //1826. Faulty Sensor
        //2971. Find Polygon With the Largest Perimeter
    }
}
