package E1_daily;

import java.util.Comparator;
import java.util.TreeSet;

public class E195_ContinuousSubarrays_classic {

    public static long continuousSubarrays(int[] nums) {
        int n=nums.length;
        TreeSet<int[]> set=new TreeSet<>(new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                if(o1[0]!=o2[0]){
                    return o1[0]-o2[0];
                }
                return o1[1]-o2[1];
            }
        });
        int minIndex=0;
        long rs=0;

        //Time: O(n)
        for(int i=0;i<n;i++){
            //Time: O(log(n))
            set.add(new int[]{nums[i], i});
            int[] maxElement = set.last();
            int[] minElement = set.first();

            if(maxElement[0]-minElement[0]<=2){
                rs+=Math.abs(i-minIndex+1);
                continue;
            }
            if(minElement[1]>maxElement[1]){
                //Time: O(log(n))
                while(!set.isEmpty()&&Math.abs(set.last()[0]-minElement[0])>2){
                    minIndex=Math.max(minIndex, set.pollLast()[1]);
                }
                minIndex++;
                rs+=Math.max(0, i-minIndex+1);
            }else{
                while(!set.isEmpty()&&Math.abs(set.first()[0]-maxElement[0])>2){
                    minIndex=Math.max(minIndex, set.pollFirst()[1]);
                }
                minIndex++;
                rs+=Math.max(0, i-minIndex+1);
            }
        }
        return rs;
    }

    public static void main(String[] args) {
        //** Requirement
        //- You are given a 0-indexed integer array nums.
        //- A subarray of nums is called continuous if:
        //  + Let (i, i + 1, ..., j) be the indices in the subarray.
        //Then, for each pair of indices (i <= i1, i2 <= j):
        //==> All of pair (i,j) in the subarray following the rule below:
        //  + 0 <= |nums[i1] - nums[i2]| <= 2.
        //* Return (the total number of continuous subarrays).
        //- A subarray is a contiguous non-empty sequence of elements within an array.
        //
        //Example 1:
        //
        //Input: nums = [5,4,2,4]
        //Output: 8
        //Explanation:
        //Continuous subarray of size 1: [5], [4], [2], [4].
        //Continuous subarray of size 2: [5,4], [4,2], [2,4].
        //Continuous subarray of size 3: [4,2,4].
        //Thereare no subarrys of size 4.
        //Total continuous subarrays = 4 + 3 + 1 = 8.
        //It can be shown that there are no more continuous subarrays.
        //
        // Idea
        //1.
        //1.0,
        //- Method-1:
        //+ Constraints:
        //1 <= nums.length <= 10^5
        //1 <= nums[i] <= 10^9
        //  + length<=10^5 ==> Time: O(n)
        //
        //- Brainstorm
        //(i,j) is valid:
        //  + Part of this array is valid
        //  ==> rs+=1+2+3 = n*(n+1)/2
        //- |a-b| >=2:
        //- We need to calculate max difference between (max and min) elements in the array
        //Ex:
        //[5,4,2]
        //  + |5-2|>2 ==> remove (5)
        //- Add index to value:
        //  + Remove (5,0)
        //  + min_index = 0
        //      + Remove (min_index, max_index)
        //- Add new element:
        //  + It could be (min or max)
        //- If current value is (max value):
        //  + Min value has index == i
        //      + remove all element from (min_index to i)
        //- If current value is (min value):
        //  + Max value has index == i
        //      + remove all element from (min_index to i)
        //
        //Ex:
        //nums = [5,1,4,2,4]
        //[5,4,2,1]
        //
        //x,y,z,a,b,c,...,min
        //- remove (a,b,c):
        //  + Because:
        //      + |a-min|>2
        //      + |b-min|>2
        //      + |c-min|>2
        //  ==> Get max index of (removed elements)
        //  ==> It means from [(max_index+1) -> current index)]
        //      + This range is valid
        //
        //1.1, Optimization
        //1.2, Complexity
        //- Space: O(n)
        //- Time: O(n*log(n))
        //
        //2. TreeMap
        //2.0,
        //-
        //
//        int[] nums = {5,4,2,4};
        int[] nums = {2,1,2,2,3,4,5};
        System.out.println(continuousSubarrays(nums));
        //#Reference:
        //2500. Delete Greatest Value in Each Row
        //2231. Largest Number After Digit Swaps by Parity
        //3277. Maximum XOR Score Subarray Queries
        //312. Burst Balloons
        //1599. Maximum Profit of Operating a Centennial Wheel
        //1642. Furthest Building You Can Reach
    }
}
