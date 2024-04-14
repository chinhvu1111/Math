package contest;

import javafx.util.Pair;

import java.util.HashMap;
import java.util.Objects;
import java.util.TreeSet;

public class E72_MostFrequentIDs {

    public static long[] mostFrequentIDs(int[] nums, int[] freq) {
        //Space : O(N)
        HashMap<Integer, Long> valToFreq = new HashMap<>();
        //Time : n*log(n)
        TreeSet<Pair<Long, Integer>> freqSet=new TreeSet<>((a, b) -> {
            if(!Objects.equals(a.getKey(), b.getKey())){
                return Math.toIntExact(a.getKey() - b.getKey());
            }
            return a.getValue()-b.getValue();
        });
        int n= nums.length;
        long[] ans=new long[n];

        for(int i=0;i<n;i++){
            if(freq[i]>=0){
                if(!valToFreq.containsKey(nums[i])){
                    valToFreq.put(nums[i], (long) freq[i]);
                    freqSet.add(new Pair<>((long)freq[i], nums[i]));
                }else{
                    long oldFreq = valToFreq.get(nums[i]);
                    long newFreq = valToFreq.get(nums[i]) + freq[i];
                    valToFreq.put(nums[i], newFreq);
                    freqSet.remove(new Pair<>(oldFreq, nums[i]));
                    freqSet.add(new Pair<>(newFreq, nums[i]));
                }
            }else{
                if(valToFreq.containsKey(nums[i])){
                    long oldFreq = valToFreq.get(nums[i]);
                    long newFreq = valToFreq.get(nums[i]) + freq[i];
                    if(newFreq>0){
                        valToFreq.put(nums[i], newFreq);
                        freqSet.add(new Pair<>(newFreq, nums[i]));
                    }else{
                        valToFreq.remove(nums[i]);
                    }
                    freqSet.remove(new Pair<>(oldFreq, nums[i]));
                }
            }
            if(!freqSet.isEmpty()){
                ans[i] = freqSet.last().getKey();
            }
            System.out.println(valToFreq);
        }
//        for(int i=0;i<n;i++){
//            System.out.printf("%s,", ans[i]);
//        }
        return ans;
    }

    public static void main(String[] args) {
        //* Requirement
        //- The problem involves tracking (the frequency of IDs) in a collection that changes over time.
        // You have two integer arrays, (nums) and (freq), of equal length n. (Each element) in nums represents (an ID),
        // and the corresponding element in freq indicates how many times that ID should be added to or removed from the collection at each step.
        //  + Addition of IDs: If freq[i] is positive, it means (freq[i]) IDs with the value nums[i] are (added) to the collection at (step i).
        //  + Removal of IDs: If freq[i] is negative, it means (-freq[i]) IDs with the value nums[i] are (removed) from the collection at (step i).
        //* Return an array ans of length n, where ans[i] represents the count of (the most frequent ID) in the collection after the (ith step).
        // If the collection is (empty) at any step, ans[i] should be (0) for that step.
        //
        // Idea
        //1.
        //1.0,
        //- Constraint:
        //1 <= nums.length == freq.length <= 10^5
        //1 <= nums[i] <= 10^5
        //-10^5 <= freq[i] <= 10^5
        //freq[i] != 0
        //
        //- Brainstorm
        //- Mỗi step ta add:
        //  + (value, freq[i])
        //- Điều kiện như sau:
        //+ Nếu cùng value : Ta cộng dồn
        //+ Nếu âm thì tìm trừ đi
        //- 1 treeSet sẽ lưu thông tin (freq[i], value)
        //  + Sort theo freq[i] và value.
        //
        //1.1, Optimization
        //1.2, Complexity
        //- Space: O(n)
        //- Time : n*log(n)
        int[] nums = {2,3,2,1};
        int[] freq = {3,2,-3,1};
        mostFrequentIDs(nums, freq);
    }
}
