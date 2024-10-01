package E1_daily;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class E108_MakeKSubarraySumsEqual {

    public static long makeSubKSumEqual(int[] arr, int k) {
        int n=arr.length;
        long rs=0;

        //Time: O(log(n)*n)
        for(int i=0;i<k;i++){
            //Space: O(n/k)
            List<Integer> cycle=new ArrayList<>();
            for(int j=i;arr[j]!=0;j=(j+k)%n){
                cycle.add(arr[j]);
                arr[j]=0;
            }
            if(cycle.isEmpty()){
                continue;
            }
            Collections.sort(cycle);
            int medVal = cycle.get(cycle.size()/2);
            for(int e: cycle){
                rs+=Math.abs(e-medVal);
            }
        }
        return rs;
    }

    public static void main(String[] args) {
        //** Requirement
        //- You are given (a 0-indexed integer array arr) and an integer k.
        //- (The array arr) is circular.
        //  + In other words, the first element of the array is the next element of the last element,
        // and the last element of the array is the previous element of the first element.
        //- You can do the following operation (any number of times):
        //  + Pick (any element) from arr and (increase) or (decrease) it by 1.
        //* Return (the minimum number of operations) such that (the sum of each subarray) of (length k) is equal.
        //- A subarray is a contiguous part of the array.
        //
        // Idea
        //1.
        //1.0,
        // Method-1:
        //- Constraint
        //1 <= k <= arr.length <= 10^5
        //1 <= arr[i] <= 10^9
        //  + N khá lớn ==> Time: O(n*k)
        //
        //- Brainstorm
        //
        //Example 2:
        //
        //Input: arr = [2,5,5,7], k = 3
        //Output: 5
        //Explanation: we can do three operations on index 0 to make its value equal to 5 and two operations on index 3 to make its value equal to 5.
        //The array after the operations is [5,5,5,5]
        //- Subarray starts at index 0 is [5, 5, 5], and its sum is 15
        //- Subarray starts at index 1 is [5, 5, 5], and its sum is 15
        //- Subarray starts at index 2 is [5, 5, 5], and its sum is 15
        //- Subarray starts at index 3 is [5, 5, 5], and its sum is 15
        //
        //- Để có thể chuyển thành array mà (all of subarrays có length = k) đều có (sum = k) thì cần ntn?
        //Ex:
        //(a,b,c,d,e,f,g)
        //+ k = 4
        //a+b+c+d = b+c+d+e
        //  => a == e
        //b+c+d+e = c+d+e+f
        //  => b == f
        //c+d+e+f = d+e+f+g
        //  => c == g
        //d+e+f+g = e+f+g+a
        //  => d == a
        //e+f+g+a = f+g+a+b
        //  => e == b
        //f+g+a+b = g+a+b+c
        //  => f == c
        //g+a+b+c = a+b+c+d
        //  => g == d
        //a+b+c+d = b+c+d+e
        //  => a == e
        //b+c+d+e = c+d+e+f
        //  => b == f
        //c+d+e+f = d+e+f+g
        //  => c == g
        //+ a == e == b == f == c == g == d == a
        //
        //Ex:
        //(a,b,c,d,e,f,g)
        //+ k = 3
        //a+b+c = b+c+d
        //  => a == d
        //  => d == g
        //  => g == c
        //  => c == f
        //  => f == b
        //  => b == e
        //  => e == a
        //
        //Ex:
        //(a,b,c,d,e,f,g)
        //+ k = 2
        //  => a == c
        //  => c == e
        //  => e == g
        //  => g == b
        //  => b == d
        //  => d == f
        //  => f == a
        //
        //Ex:
        //(a,b,c,d)
        //k=2
        //  => a == c
        //  => c == a
        //Ex:
        //(a,b,c,d,e)
        //k=2
        //  => a == c
        //  => c == e
        //  => e == b
        //  => b == d
        //  => d == a
        //Ex:
        //(a,b,c,d,e)
        //k=4
        //  => a == e
        //  => e == d
        //  => d == c
        //  => c == b
        //- Ta cần phải traverse cycle để tìm các số == nhau
        //
        //- Bài toán con:
        //Ex:
        //2,5,10
        //+ Chọn số ở giữa để (increase or decrease) ít nhất
        //2,5,10 => 5,5,5
        //+ diff = 3+5 = 8
        //2,5,10 => 6,6,6
        //+ diff = 4+1+4 = 8
        //==> Ta chứng minh được là median sẽ là số ta cần lấy
        //  + Vì nếu lấy x>5
        //      + x-2 sẽ tăng lên
        //      + 5 và x sẽ !=0
        //      + 10-x sẽ giảm xuống
        //      ==> Ta tăng 2 lần so với 5 (Do 5-5==0)
        //      ==> Med là value ta cần tìm.
        //  + median = 5
        //+ diff = a+b+c -3*med
        //
        //- Giả sử trong 1 window có size = k:
        //- nums có size = n
        //  + nums[i] = nums[i+k] = nums[i+2*k]
        //- Loop:
        //  + i = 0
        //  + i = 1
        //  ...
        //  + i=k
        //  ==> Không cần xét thêm ==> Vì nó sẽ back lại 0
        //  1 ==> k+1 rồi
        //
        //1.1, Optimization
        //1.2, Complexity
        //- Space: O(n) (Upper)
        //- Time: O(log(n)*n)
        //
//        int[] arr = {1,4,1,3};
//        int k = 2;
        int[] arr = {2,5,5,7};
        int k = 3;
        //
        //#Reference:
        //1648. Sell Diminishing-Valued Colored Balls
        //3113. Find the Number of Subarrays Where Boundary Elements Are Maximum
        //2144. Minimum Cost of Buying Candies With Discount
        //
        System.out.println(makeSubKSumEqual(arr, k));
    }
}
