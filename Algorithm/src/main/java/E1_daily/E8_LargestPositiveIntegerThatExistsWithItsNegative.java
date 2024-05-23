package E1_daily;

import java.util.HashSet;

public class E8_LargestPositiveIntegerThatExistsWithItsNegative {

    public static int findMaxK(int[] nums) {
        HashSet<Integer> setNum=new HashSet<>();
        for(int e: nums){
            setNum.add(e);
        }
        int rs=Integer.MIN_VALUE;
        for(int e: nums){
            if(setNum.contains(e*-1)&&rs<e){
                rs=e;
            }
        }
        return rs==Integer.MIN_VALUE?-1:rs;
    }

    public static int findMaxKOptimization(int[] nums) {
        boolean[] visited=new boolean[2048];
        int rs=Integer.MIN_VALUE;
        for(int e: nums){
            //e=-3 -> check visited[3+1024]
            //  + set visited[-3+1024]
            //e=3 -> check visited[-3+1024]
            //  + set visited[3+1024]
            int absVal=Math.abs(e);
            if(rs<absVal&&visited[-e+1024]){
                rs=absVal;
            }
            visited[e+1024]=true;
        }
        return rs==Integer.MIN_VALUE?-1:rs;
    }

    public static void main(String[] args) {
        //** Requirement
        //Given an integer array nums that does not contain any zeros,
        // find the largest positive integer k such that -k also exists in the array.
        //Return the positive integer k. If there is no such integer, return -1.
        //
        //** Idea
        //1.
        //1.0,
        //* Constraint
        //1 <= nums.length <= 1000
        //-1000 <= nums[i] <= 1000
        //nums[i] != 0
        //
        //* Brainstorm
        //
        //- Chữa theo cách dùng bitset:
        //- Cần phải xác định 2 cases:
        //  + 3 <=> -3
        //  Ex:
        //  -3 ==> 3 điền chưa
        //  3 ==> -3 điền chưa
        //  ==> Loại trừ case 3 <=> 3
        //- Solution:
        //e=-3 -> check visited[3+1024]
        //  + set visited[-3+1024]
        //e=3 -> check visited[-3+1024]
        //  + set visited[3+1024]
        //
        //1.1, Optimization
        //1.2, Complexity:
        //- N is size of array
        //- Space: O(1)
        //- Time : O(n)
        //
        //2.
        //2.0, Sort cũng được.
        //
        //#Reference:
        //324. Wiggle Sort II
        //3027. Find the Number of Ways to Place People II
        //3016. Minimum Number of Pushes to Type Word II
//        int[] nums= {-1,2,-3,3};
        int[] nums= {-1,10,6,7,-7,1};
        System.out.println(findMaxK(nums));
        System.out.println(findMaxKOptimization(nums));
    }
}
