package contest;

public class E63_ApplyOperationsToMakeSumOfArrayGreaterThanOrEqualTok {

    public static int[] dp;

    public static int solution(int max, int sum, int k){
        if(k<=sum){
            return 0;
        }
        if(dp[max]!=0){
            return dp[max];
        }
        int numPlusOne = solution(max+1, sum+1, k)+1;
        int dup = solution(max, sum+max, k)+1;
        return dp[max] = Math.min(numPlusOne, dup);
    }

    public static int minOperations(int k) {
        if(k==1){
            return 0;
        }
        dp=new int[k+1];
        return solution(1, 1, k);
    }

    public static void main(String[] args) {
        //* Requirement
        //- You are given a positive integer k. Initially, you have an array nums = [1].
        //You can perform any of the following operations on the array any number of times (possibly zero):
        //  - Không giới hạn số lượng or ==0
        // + Choose any element in the array and increase its value by 1 (tăng value của bất cứ element nào lên 1)
        // + Duplicate any element in the array and add it to the end of the array (Duplicate bất cứ element nào + add vào last)
        //* Return the (minimum number of operations) required to make the sum of elements of the final array (greater than or equal to k).
        //
        // Idea
        //1.
        //1.0,
        //- Constraint:
        //1 <= k <= 10^5
        //
        //- Brainstorm
        //- Init array = [1]
        //+ Làm ntn đấy để >= k
        //* Bài này khả năng dynamic programming:
        //- Ta có 2 hướng đi ở init:
        //+ [1+x]
        //+ [1,1,1...]
        //- Ta thẩy rằng:
        //+ Nếu thêm x thì thêm ở element nào cũng được
        //  + Ta sẽ ưu tiên thêm x vào max element cũng được ==> Để có giá trị max nhất khi duplicate
        //+ Còn nếu duplicate thì cần chọn element để có thể tối ưu nhất
        //  + Ở đây ta có thể chọn max element để duplicate ==> Lúc nào cũng tốt nhất
        //+ Nếu cả 2 thằng cho kết quả operation giống nhau
        //  - Thì ta ưu tiên thằng có add thêm vào ==> Nó sẽ tằng value của max element ==> Duplicate về sau nhanh --> K hơn
        //Ex:
        //k = 11
        //k=1
        //  + Max[1]=2
        //  + sum = k = 1
        //k=2
        //  + 2= 1 + 1 (duplicate)
        //  + 2= 1 + 1 (add thêm) ==> choose
        //  + sum = k = 2
        //  + count = 1
        //  + Max[2] = 2
        //k=3:
        //  + duplication:
        //      + [1] + ceil( (3-1)/ max[1] ) = 0 + 2
        //      + [2] + ceil( (3-2)/ max[2] ) = 1 + 1
        //  + Add:
        //      + [1] + (3 - 1) = 0 + 2
        //      + [2] + (3 - 2) = 1 + 1
        //  + count = 2
        //  + Max[3]=3
        //  + sum = 3
        //k=4
        //  + duplication:
        //      + [1] + ceil( (4-1)/ max[1] ) = 0 + 3
        //      + [2] + ceil( (4-2)/ max[2] ) = 1 + 1
        //      + [3] + ceil( (4-3)/ max[3] ) = 2 + 1
        //  + Add:
        //      + [1] + (4 - 1) = 0 + 3
        //      + [2] + (4 - 2) = 1 + 2
        //      + [3] + (4 - 3) = 2 + 1
        //  + count = 2
        //  + Max[4] = 2
        //  + sum = k = 4
        //k=5
        //k=6
        //k=7
        //Ex
        //k = 11
        //1 -> 4 : count=3
        //4,4,4 : count=5
        //- Ta có thể giải quyết bằng recursion với:
        //  + max
        //  + sum
        //dp[i]: số lượng operation tiếp theo ta cần thực hiện
        //  + i là max element mà ta đã lấy được.
        //
        //1.1, Optimization
        //1.2, Complexity (Upper bound):
        //- Space : O(k)
        //- Time : O(k)
        int k=11;
        System.out.println(minOperations(k));
    }
}
