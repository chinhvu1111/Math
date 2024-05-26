package contest;

public class E115_MaximumSumOfSubsequenceWithNonAdjacentElements {
    public static void main(String[] args) {
        //* Requirement
        //- You are given an array nums consisting of integers. You are also given a 2D array queries, where queries[i] = [posi, xi].
        //- For query i, we first set nums[pos-i] equal to xi, then we calculate the answer to (query i) which is (the maximum sum of ("a subsequence"))
        // of nums where (no two adjacent elements) are selected.
        //* Return the sum of the answers to all queries.
        //Since the final answer may be very large, return it modulo 10^9 + 7.
        //- A subsequence is an array that can be derived from another array by (deleting some or no elements) without changing the order of the remaining elements.
        //
        //Example 1:
        //Input: nums = [3,5,9], queries = [[1,-2],[0,-3]]
        //Output: 21
        //Explanation:
        //After the 1st query, nums = [3,-2,9] and the maximum sum of a subsequence with non-adjacent elements is 3 + 9 = 12.
        //After the 2nd query, nums = [-3,-2,9] and the maximum sum of a subsequence with non-adjacent elements is 9.
        //
        // Idea
        //1.
        //1.0,
        //- Constraint:
        //1 <= nums.length <= 5 * 10^4
        //-10^5 <= nums[i] <= 10^5
        //1 <= queries.length <= 5 * 10^4
        //queries[i] == [posi, xi]
        //0 <= posi <= nums.length - 1
        //-10^5 <= xi <= 10^5
        //==> Có thể xử lý trong O(k*n)
        //
        //- Brainstorm
        //- Khi set lại 1 value:
        //  + Nếu value <0 + value
        //- Không chọn 2 elements cạnh nhau
        //Ex:
        //nums = [1,2,6,-1]
        //max seq sum = 1+6 = 7
        //  + Không chọn 2 (mặc dù nếu scan left -> right thì ta sẽ phân vân với 1)
        //
        //- Tìm max sum seq cho 1 array bất kỳ + Không select cạnh nhau:
        //  Time: O(2^n)
        //      + N khá to --> Failed
        //
    }
}
