package E1_daily;

import javafx.util.Pair;

import java.util.*;

public class E16_TheNumberOfBeautifulSubsets {

    public static int beautifulSubsets(int[] nums, int k) {
        int n = nums.length;
        HashSet<Integer> setVal=new HashSet<>();

        for(int i=0;i<n;i++){
            for(int j=i+1;j<n;j++){
                if(Math.abs(nums[i]-nums[j])==k){
                    setVal.add((1<<i) | (1<<j));
                }
            }
        }
        int size=(1<<n)-1;
        int rs=0;

        //Time : 2^n
        for(int i=1;i<=size;i++){
            boolean isValid=true;
            //Time : O(n^2) ==> O(1)
            for(int e: setVal){
                if((i & e) == e){
                    isValid=false;
                    break;
                }
            }
            if(isValid){
                rs++;
            }
        }
        return rs;
    }

    public static int beautifulSubsetsOptimization1(int[] nums, int k) {
        int n = nums.length;
        int size=(1<<n)-1;
        int rs=0;

        //Time : 2^n
        for(int i=1;i<=size;i++){
            HashSet<Integer> indexVal=new HashSet<>();
            int curVal=i;
            int pos=0;
            boolean isValid=true;

            //Time : O(n)
            while(curVal!=0){
                int curBit = curVal&1;

                if(curBit==1){
                    if(indexVal.contains(nums[pos]-k)||indexVal.contains(nums[pos]+k)){
                        isValid=false;
                        break;
                    }
                    indexVal.add(nums[pos]);
                }
                curVal>>=1;
                pos++;
            }
            if(isValid){
                rs++;
            }
        }
        return rs;
    }

    public static int beautifulSubsetsBitSet(int[] nums, int k) {
        return countBeautifulSubsets(nums, k, 0, 0);
    }

    private static int countBeautifulSubsets(int[] nums, int difference, int index, int mask) {
        // Base case: Return 1 if mask is greater than 0 (non-empty subset)
        if (index == nums.length)
            return mask > 0 ? 1 : 0;

        // Flag to check if the current subset is beautiful
        boolean isBeautiful = true;

        // Check if the current number forms a beautiful pair with any previous number
        // in the subset
        //Time : O(n)
        for (int j = 0; j < index && isBeautiful; ++j){
            isBeautiful = ((1 << j) & mask) == 0 ||
                    Math.abs(nums[j] - nums[index]) != difference;
        }

        //Time: O(2^n)
        // Recursively calculate beautiful subsets including and excluding the current
        // number
        int skip = countBeautifulSubsets(nums, difference, index + 1, mask);
        int take;
        if (isBeautiful) {
            take = countBeautifulSubsets(nums, difference,
                    index + 1, mask + (1 << index));
        } else {
            take = 0;
        }

        return skip + take;
    }

    public static int beautifulSubsetsBacktracking(int[] nums, int k) {
        // Frequency map to track elements
        Map<Integer, Integer> freqMap = new HashMap<>();
        // Sort nums array
        Arrays.sort(nums);
        return countBeautifulSubsets(nums, k, freqMap, 0) - 1;
    }

    private static int countBeautifulSubsets(int[] nums, int difference, Map<Integer, Integer> freqMap, int i) {
        // Base case: Return 1 for a subset of size 1
        if (i == nums.length) {
            return 1;
        }
        // Count subsets where nums[i] is not taken
        int totalCount = countBeautifulSubsets(nums, difference, freqMap, i + 1);

        // If nums[i] can be taken without violating the condition
        if (!freqMap.containsKey(nums[i] - difference)) {
            freqMap.put(nums[i], freqMap.getOrDefault(nums[i], 0) + 1); // Mark nums[i] as taken

            // Recursively count subsets where nums[i] is taken
            totalCount += countBeautifulSubsets(nums, difference, freqMap, i + 1);
            freqMap.put(nums[i], freqMap.get(nums[i]) - 1); // Backtrack: mark nums[i] as not taken

            // Remove nums[i] from freqMap if its count becomes 0
            if (freqMap.get(nums[i]) == 0) {
                freqMap.remove(nums[i]);
            }
        }
        return totalCount;
    }

    public static int beautifulSubsetsRepeatDuplication(int[] nums, int k) {
        int totalCount = 1;
        Map<Integer, Map<Integer, Integer>> freqMap = new TreeMap<>();

        // Calculate frequencies based on remainder
        //Space: O(n)
        //Time: n*log(n)
        for (int num : nums) {
            //Map(2,(5,1)), k=3
            Map<Integer, Integer> fr = freqMap.getOrDefault(num % k, new TreeMap<>());
            fr.put(num, fr.getOrDefault(num, 0) + 1);
            freqMap.put(num % k, fr);
        }

        //Time : 2^n
        // Calculate subsets for each remainder group
        for (Map.Entry<Integer, Map<Integer, Integer>> entry : freqMap.entrySet()) {
            ArrayList<Pair<Integer, Integer>> subsets = new ArrayList<Pair<Integer, Integer>>(
                    entry.getValue().entrySet().size());
            for (Map.Entry<Integer, Integer> subsetEntry : entry.getValue().entrySet()) {
                subsets.add(
                        new Pair<>(subsetEntry.getKey(), subsetEntry.getValue()));
            }
            totalCount *= countBeautifulSubsets(subsets, subsets.size(), k, 0);
        }

        return totalCount - 1;
    }

    private static int countBeautifulSubsets(ArrayList<Pair<Integer, Integer>> subsets,
                                      int numSubsets, int difference, int i) {
        // Base case: Return 1 for a subset of size 1
        if (i == numSubsets) {
            return 1;
        }

        // Calculate subsets where the current subset is not taken
        int skip = countBeautifulSubsets(subsets, numSubsets, difference, i + 1);
        // Calculate subsets where the current subset is taken
        int take = (1 << subsets.get(i).getValue()) - 1;

        // If next number has a 'difference', calculate subsets; otherwise, move
        // to next
        if (i + 1 < numSubsets &&
                subsets.get(i + 1).getKey() - subsets.get(i).getKey() == difference) {
            take *= countBeautifulSubsets(subsets, numSubsets, difference, i + 2);
        } else {
            take *= countBeautifulSubsets(subsets, numSubsets, difference, i + 1);
        }

        return skip + take; // Return total count of subsets
    }

    public static int beautifulSubsetsDynamicProgramming(int[] nums, int k) {
        int totalCount = 1;

        Map<Integer, Map<Integer, Integer>> freqMap = new TreeMap<>();

        // Calculate frequencies based on remainder
        for (int num : nums) {
            int remainder = num % k;
            freqMap.computeIfAbsent(remainder, x -> new TreeMap<>())
                    .merge(num, 1, Integer::sum);
        }

        // Iterate through each remainder group
        for (Map.Entry<Integer, Map<Integer, Integer>> entry : freqMap.entrySet()) {
            int n = entry.getValue().size(); // Number of elements in the current group

            List<Map.Entry<Integer, Integer>> subsets = new ArrayList<>(
                    entry.getValue().entrySet());

            int[] counts = new int[n + 1]; // Array to store counts of subsets

            counts[n] = 1; // Initialize count for the last subset

            // Calculate counts for each subset starting from the second last
            for (int i = n - 1; i >= 0; i--) {

                // Count of subsets skipping the current subset
                int skip = counts[i + 1];

                // Count of subsets including the current subset
                int take = (1 << subsets.get(i).getValue()) - 1;

                // If next number has a 'difference',
                // calculate subsets; otherwise, move to next
                if (i + 1 < n && subsets.get(i + 1).getKey()
                        - subsets.get(i).getKey() == k) {
                    take *= counts[i + 2];
                } else {
                    take *= counts[i + 1];
                }

                // Store the total count for the current subset
                counts[i] = skip + take;
            }

            totalCount *= counts[0];
        }

        return totalCount - 1;
    }

    public static void main(String[] args) {
        //** Requirement
        //- You are given (an array nums of positive integers) and (a positive integer k).
        //- (A subset of nums is beautiful) if (it does not contain two integers with an (absolute difference) equal to k).
        //* Return (the number of non-empty beautiful subsets) of (the array nums).
        //- (A subset of nums) is an array that can be obtained by ("deleting") some (possibly none) elements from nums.
        //  + (Two subsets) are (different) if and only if the (chosen indices) to delete are "different".
        //- Đếm số lượng beautiful subset of array
        //
        //** Idea
        //1.
        //1.0,
        //* Method-1:
        //- Constraint
        //1 <= nums.length <= 20
        //1 <= nums[i], k <= 1000
        //==> Số không quá lớn ==> O(n^2) được.
        //
        //- Brainstorm
        //Example 1:
        //Input: nums = [2,4,6], k = 2
        //Output: 4
        //Explanation: The beautiful subsets of the array nums are: [2], [4], [6], [2, 6].
        //It can be proved that there are only 4 beautiful subsets in the array [2,4,6].
        //
        //- Nếu bỏ đi 2 nums mà (absolute của nó == 2)
        //Ex:
        //Input: nums = [2,3,4,5,6], k = 2
        //- Số lượng subsets được list ra ntn?
        //  + Ta sẽ dùng binary search để list ra all cases
        //- Ta list all cases:
        //  + Mỗi case ta check xem có (2 digits có absolute of subtraction) == 2 là được.
        //
        //- Implement function check xem sub-array có beautiful hay không?
        //  ==> Ta chỉ cần lấy số đó & với (00001000100) == 00001000100 hay không là được
        //  + 00001000100: 1 chính là vị trí điền 2 số có absolute(subtraction) == 2
        //
        //1.1, Optimization
        //
        //1.2, Complexity
        //- Time : O(2^n*n^2) -> O(2^n) ==> Tưởng optimize nhưng hoá ra làm bài toán tệ hơn
        //- Space : O(n^2)
        //
        //2. Bitset
        //- Chọn từng element một (index=i)
        //  + Index --> Sẽ phải check với all elements trước index
        //      + Nó có diff==k ==> break
        //          + Beautiful --> Xét được index
        //          <> skip
        //      + return take + skip
        //2.1, Optimization
        //2.2, Complexity
        //- Space: O(n)
        //- Time : O(n*2^n)
        //
        //3. Backtracking
        //3.0,
        //- Vẫn là idea chọn phần tử
        //==> Nhưng ở đây ta dùng hashmap count để đánh dấu phần tử đã có hay chưa
        //Ex:
        //3,3,5,6
        //==> 3 sẽ cần lưu count(3) = 2
        //- Mỗi index có thể chọn/ không chọn
        //  + Check hash.contains(x-diff) : ==> xét rs+=curRs
        //- Sort để giảm cái đoạn check (x-diff)
        //  + Luôn tăng dần ==> Xét 1 chiều thôi
        //3.1, Optimization
        //3.2, Complexity
        //- Space : O(n)
        //- Time : O(2^n)
        //
        //4.
        //4.0, Sinh ra quy luật để tính toán
        //- x-y=k
        //==> (x%k) - (y%k) == 0
        //==>  (x%k) = (y%k)
        //--> Ta sẽ group thành cách map<x%k,<x,count>>
        //- Xét từng group 1
        //  + Các điểm có quan hệ với nhau ==> Chỉ trong 1 group thôi
        //- Map --> Sẽ được sort tăng dần
        //  + Diff sẽ chỉ cần so sánh giữa nums[i+1] và num[i]
        //- Count để tính số lần cần
        //  Ex: count(3) == 3
        //  ==> Số cách điền số 3 vào n là : 111
        //  ==> Nếu đi kèm 3 số 3 ==> (111)*next_val
        //- Sau đó là nhân tất cả kết quả với nhau
        //4.1, Optimization
        //4.2, Complexity
        //- Space : O(n)
        //- Time : O(2^n+n*log(n))
        //
        //#Reference:
        //1718. Construct the Lexicographically Largest Valid Sequence
        //
        int[] nums = {2,4,6};
//        int[] nums = {4,2,5,9,10,3};
        int k = 2;
        System.out.println(beautifulSubsets(nums, k));
        System.out.println(beautifulSubsetsOptimization1(nums, k));
        System.out.println(beautifulSubsetsBitSet(nums, k));
        System.out.println(beautifulSubsetsBacktracking(nums, k));
        System.out.println(beautifulSubsetsRepeatDuplication(nums, k));
        System.out.println(beautifulSubsetsDynamicProgramming(nums, k));
    }
}
