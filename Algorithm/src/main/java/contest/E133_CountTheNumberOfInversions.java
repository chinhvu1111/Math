package contest;

public class E133_CountTheNumberOfInversions {

    public static int numberOfPermutations(int n, int[][] requirements) {
        return 1;
    }

    public static void main(String[] args) {
        //* Requirement
        //- You are given an integer n and a 2D array requirements,
        // where requirements[i] = [endi, cnti] represents the end index and the inversion count of each requirement.
        //  + A pair of indices (i, j) from an integer array nums is called an (inversion) if:
        //  + i < j and nums[i] > nums[j]
        //* Return the number of permutations perm of [0, 1, 2, ..., n - 1] such that for all requirements[i], perm[0..endi] has exactly (cnti inversions).
        //Since the answer may be very large, return it modulo 10^9 + 7.
        //
        // Idea
        //1.
        //1.0,
        //- Constraint:
        //2 <= n <= 300
        //1 <= requirements.length <= n
        //requirements[i] = [endi, cnti]
        //0 <= endi <= n - 1
        //0 <= cnti <= 400
        //+ The input is generated such that there is at least one i such that endi == n - 1.
        //+ The input is generated such that (all endi are unique).
        //==> N không quá lớn
        //
        //- Brainstorm
        //Example 1:
        //Input: n = 3, requirements = [[2,2],[0,0]]
        //Output: 2
        //Explanation:
        //The two permutations are:
        //[2, 0, 1]
        //Prefix [2, 0, 1] has inversions (0, 1) and (0, 2).
        //Prefix [2] has 0 inversions.
        //[1, 2, 0]
        //Prefix [1, 2, 0] has inversions (0, 2) and (1, 2).
        //Prefix [1] has 0 inversions.
        //
        //- Mối quan hệ của các endIndexes với nhau ntn?
        //  + index1< index2: count1< count2
        //      + index2,count2 thoã mãn ==> Không cần xét index1, count1
        //  + index1< index2: count1> count2
        //      + ==> + thêm case với index2 ==> Chọn thoải mái
        //
        //- Tìm số cases sao cho số pair (i,j) là inversion = count trong 1 array có size = n ntn?
        // (i, j):
        //  + i<j
        //  + nums[i]>nums[j]
        //- Số unique count=1
        //+ j: có n-1 lựa chọn (Vì có đk i<j)
        //+ i: n-1 lựa chọn (Vì có đk i<j)
        //- Số unique count=2
        //Ex:
        //0,1,2,3
        //+ (2,1),(3,0)
        //+ (2,0),(3,1)
        //+ j: có n/2 lựa chọn
        //+ i: n/2 lựa chọn
        //
        //Ex:
        //0,1,2, count=1
        //+ (1,0)
        //+ (2,1)
        //+ (2,0)
        //Ex:
        //
        //- Tìm số lượng inversion cho đến i
        //  ==> Sau đó là với từng count?
        //  ==> Liên quan đến số case permutation + order nên không được.
        //-
        //
    }
}
