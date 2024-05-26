package contest;

import java.util.*;

public class E114_FindTheNumberOfGoodPairsII {

    public static long numberOfPairs(int[] nums1, int[] nums2, int k) {
        int n=nums1.length;
        int m=nums2.length;
        HashMap<Integer, Integer> mapCount=new HashMap<>();
//        Arrays.sort(nums1);
//        Arrays.sort(nums2);
        int maxVal1=0;

        for(int i=0;i<n;i++){
            if(nums1[i]%k==0){
                mapCount.put(nums1[i], mapCount.getOrDefault(nums1[i], 0)+1);
            }
            maxVal1=Math.max(maxVal1, nums1[i]);
        }
        List<Integer> listKeys=new ArrayList<>();
        listKeys.addAll(mapCount.keySet());
        Collections.sort(listKeys, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2-o1;
            }
        });

        long rs=0;
        //- 1 nums2[j]*k có thể chia hết bởi nhiều nums1[i]
        //  Ex:
        //  3 ==> (12%3==0) or (6%3==0)
        //- nums1[i] có thể chia hết cho nhiều nums2[j]
        //  Ex:
        //  12%4 ==0 or 12%3==0
        //
        //- Lặp lại khí num1[i] và num1[j] cùng chia hết cho 1 (num2[l]*k)
        if(listKeys.isEmpty()){
            return 0;
        }
        Arrays.sort(nums2);
        for(int i=0;i<m;i++){
            int currentVal=nums2[i]*k;
            int maxMul=maxVal1/currentVal;

            while (maxMul*currentVal>=listKeys.get(listKeys.size()-1)){
                if(mapCount.containsKey(maxMul*currentVal)){
                    rs+=mapCount.get(maxMul*currentVal);
                }
                maxMul--;
            }
        }
        return rs;
    }

    public static void main(String[] args) {
        //* Requirement
        //- You are given 2 integer arrays nums1 and nums2 of lengths n and m respectively. You are also given a positive integer k.
        //A pair (i, j) is called good if nums1[i] is divisible by nums2[j] * k (0 <= i <= n - 1, 0 <= j <= m - 1).
        //* Return the total number of good pairs.
        //
        // Idea
        //1.
        //1.0,
        //- Constraint:
        //1 <= n, m <= 10^5
        //1 <= nums1[i], nums2[j] <= 10^6
        //1 <= k <= 10^3
        //+ n,m lớn nên không xử lý trong O(n*m) được nữa.
        //+ k<= 1000 không quá lớn.
        //+ nums1[i] <= (10^6) = 1_000_000 (Khá lớn)
        //
        //- Brainstorm
        //- Các a%(b*k)==0
        //  + a = b*k*x
        //==>
        //  + a%k==0
        //  + a%b==0
        //- 2 pointers?
        //Ex:
        // nums1 = [8,10,15],
        // nums2 = [3,4,5], k = 1
        //8 % 3 = 2
        //
        //- % thường nhớ đến hiệu
        //
//        int[] nums1 = {1,3,4}, nums2 = {1,3,4};
//        int k = 1;
//        int[] nums1 = {1,2,4,12}, nums2 = {2,4};
//        int k = 3;
        int[] nums1 = {1}, nums2 = {9};
        int k = 2;
        System.out.println(numberOfPairs(nums1, nums2, k));
    }
}
