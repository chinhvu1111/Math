package E1_Monotoic_Stack;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class E2_MinimumOperationsToConvertAllElementsToZero_monotonic_classic {

    public static int minOperationsWrong(int[] nums) {
        int n=nums.length;
        int i;
        int rs=0;
        HashMap<Integer, int[]> valToRangeIndex=new HashMap<>();

        for (int j = 0; j < n; j++) {
            int[] currentRange = valToRangeIndex.getOrDefault(nums[j], new int[]{Integer.MAX_VALUE, -1});
            currentRange[0]=Math.min(currentRange[0], j);
            currentRange[1]=Math.max(currentRange[1], j);
            valToRangeIndex.put(nums[j], currentRange);
        }

        for(i=0;i<n;i++){
            if(nums[i]==0){
                continue;
            }
            int j=i+1;
            int[] currentRange = valToRangeIndex.get(nums[i]);
//            System.out.printf("%s\n", nums[i]);
            //arr = [2,3,3,2,1,5,5,1]
            while(j<currentRange[1]&&nums[j]>=nums[i]){
                if(nums[j]!=nums[i]&&nums[j]!=nums[j-1]){
                    rs++;
                }
//                System.out.printf("%s, ", nums[j]);
                j++;
            }
//            System.out.println();
            //Fix for [3,4,5,3]
            //  + j = i if last element = 3 (min)
            if(j<n&&nums[j]==nums[i]){
                i=j;
            }else{
                i=j-1;
            }
            //nums[i]=2, nums[j(4)]=1
            rs++;
        }
        return rs;
    }

    public static int minOperationsWrong2(int[] nums) {
        int n=nums.length;
        Queue<Integer> cacheElements=new LinkedList<>();
        int rs=0;
        int j=1;

        for(int i=0;i<n;i++){
            while(!cacheElements.isEmpty()&&cacheElements.peek()>=nums[i]){
                if(cacheElements.peek()>nums[i]){
                    rs++;
                }
                cacheElements.poll();
            }
            rs++;
            for (; j <n && nums[j]>=nums[i]; j++) {
                cacheElements.add(nums[j]);
            }
        }
        return rs;
    }

    public static int minOperations(int[] nums) {
        int n=nums.length;
        Stack<Integer> stack=new Stack<>();
        int rs=0;

        for (int i = 0; i < n; i++) {
            while(!stack.isEmpty()&&stack.peek()>nums[i]){
                stack.pop();
            }
            //list=[1...0...2]
            //  ==> remove all element before 0
            if(nums[i]==0){
                continue;
            }
            if(stack.isEmpty()||stack.peek()<nums[i]){
                rs++;
                stack.add(nums[i]);
            }
        }
        return rs;
    }

    public static void main(String[] args) {
        //** Requirement
        //- You are given an array nums of size n, consisting of (non-negative integers).
        //- Your task is to apply some (possibly zero) operations on the array so that all elements become 0.
        //- In one operation, you can select (a subarray [i, j]) (where 0 <= i <= j < n)
        // and set all occurrences of the (minimum non-negative integer) in that subarray to 0.
        //* Return the minimum number of operations required to make all elements in the array 0.
        //
        //Example 2:
        //
        //Input: nums = [3,1,2,1]
        //Output: 3
        //Explanation:
        //
        //Select subarray [1,3] (which is [1,2,1]), where the minimum non-negative integer is 1.
        // Setting all occurrences of 1 to 0 results in [3,0,2,0].
        //Select subarray [2,2] (which is [2]), where the minimum non-negative integer is 2.
        // Setting all occurrences of 2 to 0 results in [3,0,0,0].
        //Select subarray [0,0] (which is [3]), where the minimum non-negative integer is 3.
        // Setting all occurrences of 3 to 0 results in [0,0,0,0].
        //Thus, the minimum number of operations required is 3.
        //
        //** Idea
        //1.
        //1.0,
        //- Method-1:
        //+ Constraints:
        //1 <= n == nums.length <= 10^5
        //0 <= nums[i] <= 10^5
        //  + Time: O(n)/ O(n*k)
        //
        //* Brainstorm:
        //- Choose [i,j](0,0,2,0)
        //  ==> Choose [2,2] rather than [0,3]
        //- Find the index of element that nearest + less than x
        //  + Left, right
        //- If x is min:
        //  + Delete as soon as possible
        //- Delete from (min -> max)
        //  + min heap
        //
        //Ex:
        //nums = [1,2,1,2,1,2]
        //- Delete(1) => [0,2,0,2,0,2]
        //- Delete(2) => [0,0,0,0,0,0]
        //- If we have (x,y,x)
        //  + x<y ==> we need at least (2 operations) to delete
        //  + One for (x) and one for (y)
        //
        //- 1: [0,4], count=3
        //- 2: [1,5], count=3
        //- HashMap + (HashSet of the indices) for each value
        //- For each value:
        //  + Loop from (min -> max index)
        //      + Remove the indices from other value
        //- For each value:
        //  + Scan too much ==> Not good
        //
        //- Always delete min first that is correct?
        //Ex:
        //arr = [1,2,1,1,5,5,5,5,1]
        //==> delete 5 first
        //==> arr = [1,2,1,1,0,0,0,0,(1)]
        //  ==> Can not delete (1) at the most right side
        //===> Delete min first that is correct
        //  + We need to delete (5,5,5,5) at one time
        //
        //- We don't need to care about range from (min -> max)
        //- (x,y,...,x)
        //- x<y ==> delete(y) until fix the (z<x)
        //  + delete x taking 1 operation
        //
        //Ex: (small to big)
        //arr = [2,3,3,2,1,5,5,1]
        //  + 2<3 ==> traverse all elements till getting (1<2)
        //- 2: traverse[0,3] ==> stop at index=3
        //Ex: (big to small)
        //arr = [3,1,1,3,2,1,5,5,1]
        //
        //1.1, Case
        //
        //        int[] nums = {1,2,1,2,1,2};
//        int[] nums = {0,2};
//        int[] nums = {2,4,5,4};
        int[] nums = {3,8,6,10,6,8,1,3};// Break algorithm
        //[3,8,6,10,6,8,1,3]
        //[0,8,6,10,6,8,1,0]
        //[0,8,6,10,6,8,1,0]
        //  ==> If we do this way so [6,10,6] takes 3 operations
        //  Actually, [6,10,6] only takes 2 operations to delete
        //- We need to do [6,10,6]
        //  + Increase(i) only --> WRONG
        //* If we do [6,10,6] first ==> Incorrect
        //  + [3,..0,10,0,...3]
        // - We still need to do [3,...,3] first
        //
        //- Each turn ==> Only delete (min value)
        //  ==> We need to cache (the list scanned elements) to check
        //* Rules:
        //- Pop element
        //  + element > current element: rs++
        //  + Only pop
        //
        //- Add only (the element < current element)
        //Ex:
        //[3,8,6,10,6,8,1,3]
        //3, queue = []
        // + add[8,6,10,6,8]
        //8, queue = [8,6,10,6,8]
        //  + add []
        //  + 8=8
        //  + 8>6 ==> stop, rs++
        //6, [6,10,6,8] ==> Always consider as the 1 operation
        //  + 6=6
        //  + 6<10 ==> pop rs++
        //  + 6=6 ==> pop
        //  + add[]
        //==> TƯ DUY TRÊN CÓ GÌ ĐÓ CHƯA ĐÚNG
        //  + Chưa clear được init queue + index tăng ntn khi add và pop
        //
        //** Main point
        //- Thay vì tư duy [3] so sánh với [8..10..8] in the right hand side
        //  + Sau so sánh với trước
        //  ==> [lef,..., right]: Mới quyết định được là ok hay k???
        //- Queue ==> Là cái cache lại để quyết định sau
        //* Phải xuất hiện [3,...,],1 (1<3)
        //  ==> Thì mới quyết định rs++
        //  + Nếu [3....,3]
        //      + 8 sẽ không chỉ so sánh với thẳng ở giữa ==> so sánh với thằng ở (first)
        //      ==> 8 sẽ so với (3)
        //
        //- Rules:
        //  + Nếu (top_stack/index=n-1)<y
        //      + list.add(y)
        //      + rs++ ==> Coi như th mới sẽ take 1 operation
        //
        //Ex:
        //[3,8,10,8,3]
        //- i=0, stack.add(3)
        //  + stack = [3]
        //  + rs=rs+1=1
        //- i=1, 3<8
        //  + stack.add(8)
        //  + stack = [3,8]
        //  + rs=rs+1=2
        //- i=2, 8<10
        //  + stack.add(10)
        //  + stack = [3,8,10]
        //  + rs=rs+1 = 3
        //- i=3, 10>8
        //  + stack.pop()
        //  + 8==8 ==> no add(8)
        //  + stack = [3,8]
        //- i=4, 8>3
        //  + stack.pop()
        //  + 3=3 ==> no add(3)
        //
        //** Monotonic:
        //- Phù hợp với những bài dạng thực hiện từ ngoài vào trong
        //  ==> Các kết quả được tính trước từ [i,j] ra dần [i-k,j+l]
        //  ==> MỞ RỘNG RA, và result có thể được tính trước như trên:
        //      + [3] min/ thêm element thì đã tính result sẵn rồi
        //      ==> Nếu gặp 3==3 thì không tính nữa
        //      ==> min=3 ==> Tức là "CÁC PHẦN TỬ Ở GIỮA CẦN TÍNH XONG HẾT"
        //
        //2 ==> need to go from [0,0]
        //  + It is allow to go further
        //- Special case:
        // list=[1...0...2]
        //  ==> remove all element before 0
        //  ==> Continue
        //
        //1.3, Complexity
        //- Time: O(n)
        //- Space: O(n)
        //
        //# Reference:
        //3434. Maximum Frequency After Subarray Operation
        //3603. Minimum Cost Path with Alternating Directions II
        //3636. Threshold Majority Queries
        //
//        System.out.println(minOperationsWrong(nums));
        System.out.println(minOperationsWrong2(nums));
        System.out.println(minOperations(nums));
    }
}
