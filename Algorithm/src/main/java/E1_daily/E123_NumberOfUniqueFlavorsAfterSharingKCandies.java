package E1_daily;

import java.util.HashMap;
import java.util.Map;

public class E123_NumberOfUniqueFlavorsAfterSharingKCandies {

    public static int shareCandies(int[] candies, int k) {
        int n=candies.length;
        Map<Integer, Integer> mapCount=new HashMap<>();

        for(int i=0;i<n;i++){
            mapCount.put(candies[i], mapCount.getOrDefault(candies[i], 0)+1);
        }
        if(k==0){
            return mapCount.size();
        }
        if(n<=k){
            return 0;
        }
        for(int i=0;i<k;i++){
            int countRight=mapCount.get(candies[i]);
            if(countRight!=1){
                mapCount.put(candies[i], countRight-1);
            }else{
                mapCount.remove(candies[i]);
            }
        }
        int rs=mapCount.size();
        int left=0;

        for(int i=k;i<n;i++){
            int countRight=mapCount.get(candies[i]);
            if(countRight!=1){
                mapCount.put(candies[i], countRight-1);
            }else{
                mapCount.remove(candies[i]);
            }
            mapCount.put(candies[left], mapCount.getOrDefault(candies[left], 0)+1);
            left++;
            rs=Math.max(rs, mapCount.size());
//            System.out.println(mapCount);
        }
        return rs;
    }

    public static void main(String[] args) {
        //** Requirement
        //- You are given (a 0-indexed integer array candies), where candies[i] represents (the flavor of the ith candy).
        //- Your mom wants you to share these candies with your little sister by giving her (k consecutive candies),
        // but you want to keep as (many flavors of candies) as possible.
        //* Return (the maximum number of unique flavors) of candy you can keep after sharing with your sister.
        //
        // Idea
        //1.
        //1.0,
        // Method-1:
        //- Constraint
        //1 <= candies.length <= 10^5
        //1 <= candies[i] <= 10^5
        //0 <= k <= candies.length
        //  + n khá lớn ==> Time: O(n)
        //
        //- Brainstorm
        //- Cần keep được (unique flavors) max
        //- Bài này dùng slide window
        //- Dùng mapCount là được
        //- Lưu count của toàn bộ number
        //- Sau đó run window từ (0-k) trước
        //  + Sau đó run tiếp từ k+1 ==> n-1
        //- trừ count nums[left] nếu:
        //  == 1: remove <> add
        //
        //1.1, Optimization
        //- Tính hash all --> Sau đó trừ dần đi là được
        //- (0->k-1),(k->n-1)
        //1.2, Complexity
        //- Space: O(n)
        //- Time: O(n)
        //
//        int[] candies = {1,2,2,3,4,3};
//        int k = 3;
//        int[] candies = {2,2,2,2,3,3};
//        int k = 2;
//        int[] candies = {2,4,5};
//        int k = 0;
        int[] candies = {1,2,3,2,2,2};
        int k = 3;
        System.out.println(shareCandies(candies, k));
        //
        //#Reference:
        //546. Remove Boxes
        //992. Subarrays with K Different Integers
    }
}
