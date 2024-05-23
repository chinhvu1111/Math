package E1_bitmanipulation;

import java.util.ArrayList;
import java.util.List;

public class E11_subsets {

//    public List<List<Integer>> subsets(int[] nums) {
//        return null;
//    }

    public static List<List<Integer>> subsets(int[] nums) {
        //Space: O(N*2^N)
        //Ex:
        //nums = [1,2,3]
        //[]
        //[]+[1]
        //[],[1]+([2],[1,2])
        //[],[1],[2],[1,2]+([3],[1,3],[2,3],[1,2,3])
        //==> có 2^N tập hợp
        //+ Mỗi tập hợp có Max=N phần tử.
        //
        List<List<Integer>> output = new ArrayList();
        output.add(new ArrayList<Integer>());

        //Time : O(N)
        for (int num : nums) {
            List<List<Integer>> newSubsets = new ArrayList();
            //Time : O(2^N)
            for (List<Integer> curr : output) {
                newSubsets.add(
                        new ArrayList<Integer>(curr) {
                            {
                                add(num);
                            }
                        }
                );
            }
//            System.out.printf("%s\n", newSubsets);
            for (List<Integer> curr : newSubsets) {
                output.add(curr);
            }
        }
        return output;
    }

    public static void main(String[] args) {
        //* Requirement
        //- Given (an integer array nums of "unique" elements),
        //* Return (all possible subsets) (the power set).
        //- The solution set must not contain duplicate subsets. Return the solution in any order.
        //
        // Idea
        //1.
        //1.0,
        //- Constraint:
        //1 <= nums.length <= 10
        //-10 <= nums[i] <= 10
        //All the numbers of nums are unique.
        //
        //- Brainstorm
        //- Ta có thể áp dụng quy luật để list các elements:
        //Ex:
        //nums = [1,2,3]
        //[]
        //[]+[1]
        //[],[1]+([2],[1,2])
        //[],[1],[2],[1,2]+([3],[1,3],[2,3],[1,2,3])
        //- Quy luật ở đây là
        //  + dp[i] thể hiện cho list các sub array kết thúc bởi (index=i)
        //  + dp[i+1] = (dp[0] + ... + dp[i]) + append(nums[i+1])
        //1.1, Optimization
        //1.2, Complexity
        //- Space : O(N*2^n)
        //- Time : O(N*2^n)
        //
        int[] nums=new int[]{1,2,3};
        System.out.println(subsets(nums));
    }
}
