package E1_heap_priority_queue;

import java.util.TreeSet;

public class E18_PrimeSubtractionOperation_classic {

    public static TreeSet<Integer> primeSet=null;

    public static boolean isPrime(int number){
        if(number<=1){
            return false;
        }
        for(int i=2;i<=Math.sqrt(number);i++){
            if(number%i==0){
                return false;
            }
        }
        return true;
    }

    public static boolean isPrimeOptimization(int number){
        if(number<=1){
            return false;
        }
        if(number<=3){
            return true;
        }
        if(number%2==0||number%3==0){
            return false;
        }
        for(int i=5;i*i<=number;i+=6){
            if(number%i==0||number%(i+2)==0){
                return false;
            }
        }
        return true;
    }

    public static boolean primeSubOperation(int[] nums) {
        int n= nums.length;

        if(primeSet==null){
            primeSet=new TreeSet<>();
            //Time: O(const)
            for(int i=0;i<=1000;i++){
                if(isPrimeOptimization(i)){
                    primeSet.add(i);
                }
            }
        }
        int prevVal = 0;

        //Time: O(n)
        for(int i=0;i<n;i++){
            //Time: O(const)
            Integer maxPrime = primeSet.floor(nums[i]-prevVal-1);
            if(maxPrime==null){
                if(prevVal>=nums[i]){
                    return false;
                }
                maxPrime=0;
            }
            prevVal = nums[i]-maxPrime;
//            System.out.println(prevVal);
        }
        return true;
    }

    public static void main(String[] args) {
        //** Requirement:
        //- You are given (a 0-indexed integer array nums) of length n.
        //- You can perform the following operation as many times as you want:
        //  + Pick (an index i) that you haven’t picked before, and pick (a prime p) strictly (less than) (nums[i]), then (subtract p) from (nums[i]).
        //* Return true if you can make nums (a strictly increasing array) using (the above operation) and (false otherwise).
        //- (A strictly increasing array) is an array whose (each element) is strictly greater than (its preceding element).
        //- Tức là chọn index=i + (prime number < nums[i])
        //  + nums[i] = nums[i] - p
        //* Return true if we can make the array strictly increasing.
        //
        // Idea
        //1.
        //1.0,
        // Method-1:
        //- Constraint
        //1 <= nums.length <= 1000
        //1 <= nums[i] <= 1000
        //nums.length == n
        //  + n <= 1000, Time: O(n^2)
        //  + nums[i] <= 1000:
        //      + We can list all of prime numbers <= 1000
        //
        //- Brainstorm
        //Example 1:
        //
        //Input: nums = [4,9,6,10]
        //Output: true
        //Explanation: In the first operation:
        //  + Pick i = 0 and p = 3, and then subtract 3 from nums[0], so that nums becomes [1,9,6,10].
        //  + In the second operation: i = 1, p = 7, subtract 7 from nums[1], so nums becomes equal to [1,2,6,10].
        //  + After the second operation, nums is sorted in strictly increasing order, so the answer is true.
        //
        //- If we subtract many times:
        //  + We need to find the optimal way to subtract.
        //
        //Example 2:
        //Input: nums = [6,8,11,12]
        //Output: true
        //Explanation: Initially nums is sorted in strictly increasing order, so we don't need to make any operations.
        //  + This array is in the increase order
        //  => We don't need to subtract anymore
        //Ex:
        //nums = [2,14,13,9,12]
        //  + 14 - 7 = 7
        //  + 13 - 5 = 8
        //nums = [2,7,8,9,12]
        //- We see that:
        //- For nums1 = [2,14,13]
        //  ==> We just substract such that nums1 is in increase order
        //+ a,b
        //  ==> b need to minimize such that b>a
        //nums1 = [2,14,13]
        //nums1 = [2,14,13]
        //  + 14 - 2 = 12 ==> (Max prime < 12)
        //      + Max prime = 11
        //=>
        //nums1 = [2,3,13]
        //- max prime < 13-3 = 10
        //  + max prime = 7
        //nums1 = [2,3,6]
        //
        //- We add all of prime to the treeSet:
        //  + We find the max prime < x:
        //      + Find lower bound of the treeset
        //
//        int[] nums = {4,9,6,10};
//        int[] nums = {6,8,11,12};
        int[] nums = {5,8,3};
//        int[] nums = {5};
//        int[] nums = {998,2};
        System.out.println(primeSubOperation(nums));
        //
        //*
        //- Thuật toán có thể được cải thiện hơn nữa bằng cách quan sát rằng tất cả các số nguyên tố đều có dạng (6k ± 1), ngoại trừ 2 và 3.
        // Điều này là do tất cả các số nguyên có thể được biểu thị dưới dạng (6k + i) đối với một số nguyên k và
        //  + i = -1, 0, 1, 2, 3 hoặc 4.
        //** Lưu ý:
        //  - 2 phép chia (6k+0), (6k+2), (6k+4):
        //      + Chia hết cho 2
        //  - 3 phép chia (6k + 3)
        //      + Chia hết cho 3
        //  - Các số còn lại không chia hết cho (2 and 3)
        //  ==> Tạo thành form: (6*k+1)
        //
        //** Use increments of 6 to check divisors as numbers of
        //  + the form 6k ± 1 (5, 7, 11, 13...) cover all potential primes beyond 3.
        //
        //- Vì vậy, một phương pháp hiệu quả hơn là kiểm tra xem n có chia hết cho 2 hay 3 không,
        // sau đó kiểm tra tất cả các số có dạng 6k ± 1 ≤ √n.
        //=> Phương pháp này nhanh hơn 3 lần so với việc kiểm tra tất cả các số lên đến √n.
        //
        //1.1, Optimization
        //1.2, Complexity
        //- Space: O(count)
        //- Time: O(n+sqrt(max_val))
        //
        //### 1. **Why Multiples of 6?**
        //When looking for prime numbers, we can start by considering the divisibility patterns of integers:
        //   - Any integer can be represented in the form \( 6k + r \), where \( k \) is an integer (representing multiples of 6) and
        //   \( r \) is the remainder when divided by 6.
        //   - The possible values of \( r \) are \( 0, 1, 2, 3, 4, \) and \( 5 \), which covers all integers.
        //   This gives us these forms:
        //   - \( 6k \) (divisible by 6)
        //   - \( 6k + 1 \)
        //   - \( 6k + 2 \)
        //   - \( 6k + 3 \)
        //   - \( 6k + 4 \)
        //   - \( 6k + 5 \)
        //### 2. **Filtering Out Non-Prime Forms**
        //   Let’s examine each of these forms to see which could represent prime numbers:
        //   - **\( 6k \)**: Any number of this form is divisible by 6, so it’s not prime (except for 6 itself, which isn’t prime either).
        //   - **\( 6k + 2 \)**: This is (even), so it’s divisible by 2 and therefore not prime (unless it’s the number 2 itself).
        //   - **\( 6k + 3 \)**: This is (divisible by 3), so it can’t be prime (unless it’s the number 3 itself).
        //   - **\( 6k + 4 \)**: This is (even and divisible by 2), so it can’t be prime.
        //   - **\( 6k + 5 \)**: This is the same as \( 6(k + 1) - 1 \), which we can rewrite as \( 6k - 1 \).
        //
        //   ** So, the only forms that are (potentially) prime are:
        //   - **\( 6k + 1 \)**
        //   - **\( 6k - 1 \)** (or equivalently \( 6k + 5 \))
        //
        //### 3. **Why Are \( 6k \pm 1 \) Forms Important for Prime Checking?**
        //   Since we’ve established that all prime numbers greater than 3 must take the form \( 6k \pm 1 \), we can limit our search when testing whether a number \( n \) is prime:
        //   - Instead of testing all numbers up to \( \sqrt{n} \), we only test numbers of the form \( 6k \pm 1 \), greatly reducing the number of checks.
        //   - This works because if a number \( n \) is composite (not prime), it will have a factor that does not fit the form \( 6k \pm 1 \).
        //
        //### 4. **Example of \( 6k \pm 1 \) with Prime Numbers**
        //   Let’s list a few prime numbers greater than 3 and check if they fit the form \( 6k \pm 1 \):
        //   - \( 5 = 6 \times 0 + 5 = 6 \times 1 - 1 \)
        //   - \( 7 = 6 \times 1 + 1 \)
        //   - \( 11 = 6 \times 1 + 5 = 6 \times 2 - 1 \)
        //   - \( 13 = 6 \times 2 + 1 \)
        //   - \( 17 = 6 \times 2 + 5 = 6 \times 3 - 1 \)
        //   - \( 19 = 6 \times 3 + 1 \)
        //   - \( 23 = 6 \times 3 + 5 = 6 \times 4 - 1 \)
        //
        //   As seen here, each of these primes fits the pattern \( 6k + 1 \) or \( 6k - 1 \).
        //
        //### 5. **Conclusion: The Efficiency of the \( 6k \pm 1 \) Formula**
        //   - Using the \( 6k \pm 1 \) formula helps us focus on a subset of numbers that are most likely to be prime.
        //   - This optimization reduces the number of checks, especially for larger numbers, because we skip numbers that are obviously composite based on divisibility by 2 or 3.
        //   - By implementing this in algorithms (as in the Java example), we make prime-checking more efficient, especially for large inputs.
        //
        //This is why \( 6k \pm 1 \) is widely used in prime-checking optimizations—it cuts down unnecessary checks and aligns with the mathematical properties of primes beyond 3.
        //
        //** WHY?
        //- num % i == 0 checks if num is divisible by i.
        //- num % (i + 2) == 0 checks if num is divisible by i + 2.
        //Here’s why:
        //	•	After 2 and 3, prime candidates take the form 6k ± 1.
        //	•	So instead of checking every single number, we can skip numbers that would fall between (two successive candidates) in this form.
        //	•	For example, if we check (i = 5), then (the next potential divisor) in this sequence is 7, which is (i + 2).
        //Ex:
        //- 5 = (6*1-1) ==> Xét tiếp (6*1+1) = 7
        //  + k+=6 ==> Và ta chỉ cần cộng từ 5 = (6*1-1) (k==1)
        //
        //#Reference:
        //2459. Sort Array by Moving Items to Empty Space
        //1970. Last Day Where You Can Still Cross
        //1561. Maximum Number of Coins You Can Get
    }
}
