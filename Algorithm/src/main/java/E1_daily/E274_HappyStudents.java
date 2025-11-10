package E1_daily;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class E274_HappyStudents {

    public static int countWays(List<Integer> nums) {
        Collections.sort(nums);
        int count=0;
        if(nums.get(0)>0)
            count++;
        for(int i=0;i<nums.size();i++){
            if(i==nums.size()-1){
                if(i+1>nums.get(i))
                    count++;
            }
            else{
                if(i+1>nums.get(i) && i+1<nums.get(i+1))
                    count++;
            }
        }
        return count;
    }

    public static void main(String[] args) {
        //** Requirement
        //- You are given (a 0-indexed integer array nums of length n) where n is (the total number of students) in the class.
        //- The class teacher tries to select (a group of students) so that (all the students) remain happy.
        //- The (ith student) will become ("happy") if one of these two conditions is met:
        //  + The student is selected and the (total number of selected students) is "strictly greater" than nums[i].
        //  + The student is not selected and the (total number of selected students) is "strictly less" than nums[i].
        //* Return (the number of ways) to select (a group of students) so that everyone remains happy.
        //- Đề bài khá đau đầu:
        //  + Nói chung là chọn 1,2,3,4 ==> 4>nums[1], nums[2], nums[3], nums[4] là được
        //==> Sai đề phải là meet (both of conditions) mới đúng
        //
        //Example 2:
        //
        //Input: nums = [6,0,3,3,6,7,2,7]
        //Output: 3
        //Explanation:
        //The three possible ways are:
        //The class teacher selects the student with index = 1 to form the group.
        //The class teacher selects the students with index = 1, 2, 3, 6 to form the group.
        //The class teacher selects all the students to form the group.
        //
        //** Idea
        //1.
        //1.0,
        //- Method-1:
        //+ Constraints:
        //1 <= nums.length <= 10^5
        //0 <= nums[i] < nums.length
        //  + length<= 10^5 ==> Time: O(n)
        //
        //* Brainstorm:
        //
        //Example 2:
        //
        //Input: nums = [6,0,3,3,6,7,2,7]
        //Output: 3
        //
        //- Number of selected > value
        //- Sort
        //nums = [6,0,3,3,6,7,2,7]
        //sort = [0,2,3,3,6,6,7,7]
        //+ 1>0: rs++
        //+ 2>2: wrong
        //  + Stop because (next val) compare to (2+1) = x+1 but (prev <x) ==> next val <2+1
        //      ==> Skip ==> Wrong
        //  + 3,3,3: duplication
        //  ==> loop all
        //
        //Ex:
        //sort = [0,2,3,3,6,6,7,7]
        //[0]
        //[0,2,3,3]: 4>3
        //[0,2,3,3,6,6,7,7]: 8>7
        //
        //1.1, Case
        //
        //
        //1.2, Optimization
        //
        //
        //1.3, Complexity
        //- Time: O(n*log(n))
        //- Space: O(1)
        //
//        Integer[] nums={1,1};
        Integer[] nums={6,0,3,3,6,7,2,7};
        System.out.println(countWays(Arrays.asList(nums)));
    }
}
