package E1_rolling_hash;

public class E2_CountBeautifulSplitsInAnArray_classic {

    public static void reverseArr(int[] nums,  int n){
        for (int i = 0; i < n / 2; i++) {
            int t = nums[i];
            nums[i] = nums[n - 1 - i];
            nums[n - 1 - i] = t;
        }
    }

    public static int countNumberPrefix1(int[]nums, int n){
        int[] prefixSum=new int[n];
        int sum=0;

        for(int i=0;i<n;i++){
            sum+=nums[i];
            prefixSum[i]=sum;
        }
        int rs=0;
        //(a....b)(c....d)
        for(int i=0;i<n;i++){
            int firstSum = prefixSum[i];
            //i=0, j=1
            //i=1, j=2
            //  + (0,1),(2,3)
            //length = i+1
            if(2*i+1>=n){
                break;
            }
            int secondSum = prefixSum[2*i+1]-firstSum;
            int length=i+1;
            int nextIndex=i+1;
            int curIndex=i;
            //2,3
            if(i+length-1>=n){
                break;
            }
            int count=0;
            while(firstSum==secondSum&&count<length){
                firstSum-=nums[curIndex];
                secondSum-=nums[nextIndex];
                count++;
                curIndex=count;
                nextIndex=i+1+count;
            }
            if(count==length){
                //1,1,2,1
                //[1],[1],==> rs+2
                rs+=n-nextIndex;
            }
        }
        return rs;
    }

    public static int countNumberPrefix2(int[]nums, int n){
        int[] prefixSum=new int[n];
        int sum=0;

        for(int i=0;i<n;i++){
            sum+=nums[i];
            prefixSum[i]=sum;
        }
        int rs=0;
        //(a....b)(c....d)
        for(int i=0;i<n;i++){
            int firstSum = prefixSum[i];
            //i=0, j=1
            //i=1, j=2
            //  + (0,1),(2,3)
            //length = i+1
            if(2*i+1>=n){
                break;
            }
            int secondSum = prefixSum[2*i+1]-firstSum;
            int length=i+1;
            int nextIndex=i+1;
            int curIndex=i;
            //2,3
            if(i+length-1>=n){
                break;
            }
            int count=0;
            while(firstSum==secondSum&&count<length){
                firstSum-=nums[curIndex];
                secondSum-=nums[nextIndex];
                count++;
                curIndex=count;
                nextIndex=i+1+count;
            }
            if(count==length){
                //1,1,2,1
                //[1],[1],==> rs+2
                rs+=n-nextIndex;
            }
        }
        return rs;
    }

    public static int beautifulSplitsWrong(int[] nums) {
        int n=nums.length;
        //O(n^2)
        int rs= countNumberPrefix1(nums, n);
        return rs;
    }

    public static int[] zFunction(int[] nums, int offset) {
        int n = nums.length - offset;
        int[] z = new int[n];
        int l = 0, r = 0;
        //- The window [L, R] represents (the range of the string) where the (substring) matches (the prefix).
        //Ex:
        //aba(d)asdas
        //Each position ith
        for(int i=1;i<n;i++){
            if(i<=r){
                //0,...,...l.(i)..r
                z[i]=Math.min(r-i+1, z[i-l]);
            }
            //0...[i]....[j]
            while(i+z[i]<n&&nums[z[i]+offset]==nums[i+z[i]+offset]){
                z[i]++;
            }
            //Update [l,r] base on (current index = i)
            if(i+z[i]-1>r){
                l=i;
                r=i+z[i]-1;
            }
        }

        z[0] = n; // By definition, z[0] is the length of the entire array
        return z;
    }

    public static int beautifulSplitsWeel(int[] nums) {
        int n = nums.length;
        int[] z = zFunction(nums, 0);
        int total = 0;

        // n1 prefix n2
        for (int i = 1; i < n; i++) {
            if (z[i] >= i) {
                total += n - i - i;
            }
        }

        // n2 prefix n3
        //nums1 start from (0 -> n-2)
        //nums2 starts at character ith (1 -> n-1)
        //Time: O(n^2)
        for (int i = 1; i < n; i++) {
            int[] zn = zFunction(nums, i);

            //j: size of the n3 from 1 -> zn.length
            for (int j = 1; j < zn.length; j++) {
                //n2 is the prefix of n3
                // AND
                //n1 is not the prefix of n2
                //- zn[j]>=j:
                //  + count_val is valid
                //- z[i]<i:
                //  + n1 is not prefix of n2
                //  Explain:
                //  0...[i](count=z[i])...
                //  => z[i]<i (1->n-1)
                //
                //- j < Math.min(z[i], i)
                //Ex:
                //[1,1,1,1,1,1],  [1,1]   [1,1,1,1,1,1,1,3]
                //- If we use global hash function:
                //If we choose:
                //  + nums1 = [1,1,1,1,1,1]
                //  + nums2 = [1,1,1,1,1,1]
                //  + nums3 = [....]
                //  ==> nums1 is prefix of nums2
                //  ==> rs++
                //- But in the same case:
                //If we choose:
                //  + nums1 = [1,1,1,1,1,1]
                //  + nums2 = [1,1]
                //  + nums3 = [1,1,1,1,3]
                //- nums1 is not prefix of nums2
                //- nums2 is prefix of nums3
                //  ==> rs++
                if (zn[j] >= j && (z[i] < i || j < Math.min(z[i], i))) {
                    total++;
                }
            }
        }

        return total;
    }

    public static void main(String[] args) {
        //** Requirement
        //- You are given an array nums.
        //- A split of an array nums is beautiful if:
        //  + The array nums is split into (three non-empty subarrays):
        //      + nums1, nums2, and nums3,
        //  such that nums can be formed by concatenating nums1, nums2, and nums3 in that order.
        //  + The subarray (nums1) is (a prefix of (nums2)) OR nums2 is (a prefix of (nums3)).
        //* Return (the number of ways) you can (make this split).
        //
        //- A subarray is a contiguous non-empty sequence of elements within an array.
        //- A prefix of an array is a subarray that starts from the beginning of the array and extends to any point within it.
        //
        // Idea
        //1. Rolling hash
        //1.0,
        // Method-1:
        //- Constraint
        //1 <= nums.length <= 5000
        //0 <= nums[i] <= 50
        //  + Length<=5000 ==> O(n^2)
        //
        //- Brainstorm
        //- We need to loop only 1 time from first to last:
        //  + To check (nums1, nums2)
        //- We need to loop only 1 time from last to first:
        //  + To check (nums2, nums3)
        //- If we use the sum to reduce the complexity:
        //  + For the nums1 and nums2 ==> We can reduce the complexity
        //  + But for the nums2 is the prefix of the nums3
        //      ==> It is difficult to loop (cut off the nums1 to check the relationship between nums2 and nums3)
        //
        //- Có same idea nhưng function để tính hash chuẩn hơn
        //
        //- The obvious part that can be optimized here is checking for prefixes.
        //- Instead of traversing in O(n) we can make it in O(1) using z-function.
        //  + For each entry 𝑍[𝑖] it represents the length of the longest prefix of the array (starting at i)
        //  that matches the prefix of the entire array
        //Ex:
        //[1,1,1,2,3,1,1]
        //[7,2,1,0,0,2,1]
        //
        //.....(i).....(j)
        //
        //- First, we compute z-array for all the array nums.
        //- This allows us to fast-check if (first cut) is a prefix of (second cut):
        //  + zall[i] >= i is our condition for it.
        //- Now we go over each (2nd cut) possible:
        //- First, we compute z-array for subarray starting at (i - zpart):
        //  + If length of the (first cut) is less than length of (second)
        //==> our only possibility of a beautiful cut is if
        //  + 2nd is that 2nd cut is a prefix of 3rd.
        //      + zpart[j - i] >= j - i
        //
        //- If length of 2nd cut is the same as of 1st cut we have 2 options:
        //  + 1st cut is a prefix of 2nd
        //  ==> we can (fast compute all the other possible cuts) because for each 2nd cut 1st cut stays a prefix
        //  + 1st cut is not a prefix of 2nd
        //  ==> need to apply (same logic) with z-array checks
        //
        //** To solve the struggle idea above:
        //- For the relationship between (nums1 and nums2):
        //  + Z function for entire array
        //- For the relationship between (nums2 and nums3)
        //  + Calculate z for the subarray start with i-index
        //
        //n2 is the prefix of n3
        // AND
        //n1 is not the prefix of n2
        //- zn[j]>=j:
        //  + count_val is valid
        //- z[i]<i:
        //  + n1 is not prefix of n2
        //  Explain:
        //  0...[i](count=z[i])...
        //  => z[i]<i (1->n-1)
        //
        //- j < Math.min(z[i], i)
        //Ex:
        //[1,1,1,1,1,1],  [1,1]   [1,1,1,1,1,1,1,3]
        //- If we use global hash function:
        //If we choose:
        //  + nums1 = [1,1,1,1,1,1]
        //  + nums2 = [1,1,1,1,1,1]
        //  + nums3 = [....]
        //  ==> nums1 is prefix of nums2
        //  ==> rs++
        //- But in the same case:
        //If we choose:
        //  + nums1 = [1,1,1,1,1,1]
        //  + nums2 = [1,1]
        //  + nums3 = [1,1,1,1,3]
        //- nums1 is not prefix of nums2
        //- nums2 is prefix of nums3
        //  ==> rs++
        //
        //1.1, Optimization
        //
        //1.2, Complexity
        //- Space: O(1)
        //- Time: O(n^2)
        //
        //- Z-FUNCTION:
        //- The Z-function is a string-processing algorithm that computes the length of the longest substring starting at each position
        // in a string that matches the prefix of the string.
        //- It is commonly used in pattern matching and string problems due to its efficiency.
        //
        //---
        //
        //### **What is the Z-Array?**
        //For a string \( s \) of length \( n \), the Z-array \( Z[i] \) represents the length of the longest substring starting at index \( i \) in \( s \) that is also a prefix of \( s \).
        //
        //#### **Key Points**:
        //- \( Z[0] \) is always 0 because a substring starting at index 0 is trivially the entire prefix.
        //- For \( Z[i] \), the algorithm checks how much the substring starting at \( i \) matches the prefix of \( s \).
        //
        //---
        //
        //### **How the Z-Function Works**
        //
        //The Z-function uses a sliding window approach to efficiently compute the Z-array:
        //
        //1. **Maintain a Window (`[L, R]`)**:
        //   - The window `[L, R]` represents the range of the string where the substring matches the prefix.
        //   - Initially, \( L = R = 0 \).
        //
        //2. **Two Cases for Each Position \( i \)**:
        //   - **Case 1: \( i > R \):**
        //     - If \( i \) is outside the window, compute \( Z[i] \) directly by comparing characters starting at \( i \) with the prefix of the string.
        //     - Update the window `[L, R]` to reflect the new match range.
        //   - **Case 2: \( i \leq R \):**
        //     - If \( i \) is inside the window, use the previously computed values to avoid redundant comparisons:
        //       - \( k = i - L \): The relative position of \( i \) in the current match window.
        //       - If \( Z[k] < R - i + 1 \), then \( Z[i] = Z[k] \) (i.e., the match continues within the window).
        //       - Otherwise, start a new comparison beyond \( R \) and update the window.
        //
        //3. **Continue until the end of the string**.
        //
        //---
        //
        //### **Algorithm**
        //
        //Here’s the Z-function algorithm:
        //
        //```python
        //def z_function(s):
        //    n = len(s)
        //    z = [0] * n  # Initialize Z-array
        //    l, r = 0, 0  # Initialize window [L, R]
        //
        //    for i in range(1, n):
        //        if i <= r:  # Case 2: Inside the window
        //            z[i] = min(r - i + 1, z[i - l])
        //        while i + z[i] < n and s[z[i]] == s[i + z[i]]:  # Case 1: Direct comparison
        //            z[i] += 1
        //        if i + z[i] - 1 > r:  # Update the window
        //            l, r = i, i + z[i] - 1
        //
        //    return z
        //```
        //
        //---
        //
        //### Example Walkthrough
        //
        //Let’s compute the Z-array for \( s = \text{"aabcaabxaaaz"} \):
        //
        //1. **Initialization**:
        //   - \( z = [0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0] \)
        //   - \( L = R = 0 \)
        //
        //2. **Steps**:
        //
        //| \( i \) | \( L, R \)       | Matching Prefix | \( Z[i] \) | Updated \( L, R \)       |
        //|--------|------------------|-----------------|-----------|-------------------------|
        //| 1      | (0, 0)           | "a"             | 1         | (1, 1)                  |
        //| 2      | (1, 1)           | "ab"            | 0         | (1, 1)                  |
        //| 3      | (1, 1)           | "a"             | 1         | (3, 3)                  |
        //| 4      | (3, 3)           | "abc"           | 0         | (3, 3)                  |
        //| 5      | (3, 3)           | "aab"           | 3         | (5, 7)                  |
        //| 6      | (5, 7)           | "ab"            | 0         | (5, 7)                  |
        //| 7      | (5, 7)           | "a"             | 1         | (7, 7)                  |
        //| 8      | (7, 7)           | "xaa"           | 0         | (7, 7)                  |
        //| 9      | (7, 7)           | "aaa"           | 3         | (9, 11)                 |
        //| 10     | (9, 11)          | "aa"            | 0         | (9, 11)                 |
        //| 11     | (9, 11)          | "a"             | 1         | (11, 11)                |
        //
        //Final \( Z \)-array: \([0, 1, 0, 1, 0, 3, 0, 1, 0, 3, 0, 1]\).
        //
        //---
        //
        //### **Applications**
        //1. **Pattern Matching**:
        //   - To find all occurrences of a pattern in a text, concatenate the pattern and text with a delimiter and compute the Z-array.
        //   - Any \( Z[i] \) equal to the pattern length indicates a match.
        //
        //2. **Prefix Checks**:
        //   - Quickly check if a substring is a prefix of another string.
        //
        //3. **String Comparisons**:
        //   - Compute the longest common prefix of two strings in \( O(n + m) \).
        //
        //Ex:
        //A A B Z |A (A) B Z| C A A
        //0 1 0 0  4
        //* Main point:
        //- So sánh:
        //A A B Z
        //A B Z
        //- Vì [A, B, Z] đã tính trước đấy rồi (Đoạn đầu)
        //  + Mà đoạn [l,r] là đoạn does match ==> Ta không cần tính lại
        //- Xét đến prefix với index = 5
        //- Index nằm trong [r,l]
        //A [A] A Z |A (A) [A] Z|
        //0  2 1 0
        //So sánh:
        //A A A Z
        //A A Z
        //- [A] == [A] (2):
        //  + Lấy luôn khỏi tính
        //
        //- [l,r] đóng vai trò khoảng cache:
        //  <> Nếu không match thì không update [l,r]
        //* Rule:
        //- Zp< |S(k...r)| = r-k+1
        //  + We can get the value
        //  ==> Reuse value
        //- Zp >= |S(k...r)| = r-k+1
        //Ex:
        //A A B Z A A B Z C |A A B Z [A] A B Z| A
        //0 1 0 0 4 1 0 0 0  8 1 0 0
        //- For index = 13 [A]
        //  + We cannot use [index=4] because the current cache does have (A)
        //      + We get 5 instead of 4
        //  ==> We traverse in naive way + update [l,r]
        //* Refer:
        //https://www.youtube.com/watch?v=Tg1w-0a1xew
        //Time: O(n)
        //
        //========== CODE
        ////Each position ith
        //        for(int i=1;i<n;i++){
        //            if(i<=r){
        //                //0,...,...l.(i)..r
        //                z[i]=Math.min(r-i+1, z[i-l]);
        //            }
        //            //0...[i]....[j]
        //            while(i+z[i]<n&&nums[z[i]+offset]==nums[i+z[i]+offset]){
        //                z[i]++;
        //            }
        //            //Update [l,r] base on (current index = i)
        //            if(i+z[i]-1>r){
        //                l=i;
        //                r=i+z[i]-1;
        //            }
        //        }
        //========
        //
//        int[] nums = {1,1,2,1};
        int[] nums = {2,3,2,2,1};
        //output=0
        //rs=1
//        System.out.println(beautifulSplitsWrong(nums));
        System.out.println(beautifulSplitsWeel(nums));
        //
        //#Reference:
        //171. Excel Sheet Column Number
        //1524. Number of Sub-arrays With Odd Sum
        //1088. Confusing Number II
    }
}
