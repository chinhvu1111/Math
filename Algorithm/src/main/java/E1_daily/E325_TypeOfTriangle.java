package E1_daily;

public class E325_TypeOfTriangle {

    public static String triangleType(int[] nums) {
        if(nums[0]+nums[1]<=nums[2]||nums[0]+nums[2]<=nums[1]||nums[1]+nums[2]<=nums[0]){
            return "none";
        }
        if(nums[0]==nums[1]&&nums[1]==nums[2]){
            return "equilateral";
        }
        if(nums[0]==nums[1]||nums[1]==nums[2]||nums[0]==nums[2]){
            return "isosceles";
        }
        return "scalene";
    }

    public static void main(String[] args) {
        //** Requirement
        //- You are given a 0-indexed integer array nums of size 3 which can form the sides of a triangle.
        //  + A triangle is called (equilateral) if it has all sides of equal length.
        //  + A triangle is called (isosceles) if it has exactly two sides of equal length.
        //  + A triangle is called (scalene) if all its sides are of different lengths.
        //* Return a string representing the type of triangle that can be formed or "none" if it cannot form a triangle.
        //
        //Example 2:
        //
        //Input: nums = [3,4,5]
        //Output: "scalene"
        //Explanation:
        //nums[0] + nums[1] = 3 + 4 = 7, which is greater than nums[2] = 5.
        //nums[0] + nums[2] = 3 + 5 = 8, which is greater than nums[1] = 4.
        //nums[1] + nums[2] = 4 + 5 = 9, which is greater than nums[0] = 3.
        //Since the sum of the two sides is greater than the third side for all three cases, therefore,
        // it can form a triangle.
        //As all the sides are of different lengths, it will form a scalene triangle.
        //
        //** Idea
        //1.
        //1.0,
        //- Method-1:
        //+ Constraints:
        //nums.length == 3
        //1 <= nums[i] <= 100
        //
        //* Brainstorm:
        //
        //
        //
        //1.1, Case
        //
        //
        //1.2, Optimization
        //
        //
        //1.3, Complexity
        //- Space: O(3^(2*m))
        //- Time: O(n*3*(2*m))
        //
    }
}
