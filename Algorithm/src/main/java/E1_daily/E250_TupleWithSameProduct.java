package E1_daily;

import java.util.*;

public class E250_TupleWithSameProduct {

    public static int tupleSameProduct(int[] nums) {
        int n=nums.length;
        HashMap<Integer, Integer> mapCount=new HashMap<>();

        for(int i=0;i<n;i++){
            for(int j=i+1;j<n;j++){
                int curVal = nums[i]*nums[j];
                mapCount.put(curVal, mapCount.getOrDefault(curVal, 0)+1);
            }
        }
        int rs=0;
        for(Map.Entry<Integer, Integer> e: mapCount.entrySet()){
            rs+=e.getValue()*(e.getValue()-1)/2*8;
        }
        return rs;
    }

    public static int tupleSameProductRefer(int[] nums) {
        int numsLength = nums.length;

        List<Integer> pairProducts = new ArrayList<>();

        int totalNumberOfTuples = 0;

        // Iterate over nums to calculate all pairwise products
        for (int firstIndex = 0; firstIndex < numsLength; firstIndex++) {
            for (
                    int secondIndex = firstIndex + 1;
                    secondIndex < numsLength;
                    secondIndex++
            ) {
                pairProducts.add(nums[firstIndex] * nums[secondIndex]);
            }
        }

        Collections.sort(pairProducts);

        int lastProductSeen = -1;
        int sameProductCount = 0;

        // Iterate over pairProducts to count how many times each product occurs
        for (
                int productIndex = 0;
                productIndex < pairProducts.size();
                productIndex++
        ) {
            if (pairProducts.get(productIndex) == lastProductSeen) {
                // Increment the count of same products
                sameProductCount++;
            } else {
                // Calculate how many pairs had the previous product value
                int pairsOfEqualProduct =
                        ((sameProductCount - 1) * sameProductCount) / 2;

                totalNumberOfTuples += 8 * pairsOfEqualProduct;

                // Update lastProductSeen and reset sameProductCount
                lastProductSeen = pairProducts.get(productIndex);
                sameProductCount = 1;
            }
        }

        // Handle the last group of products (since the loop ends without adding
        // it)
        int pairsOfEqualProduct =
                ((sameProductCount - 1) * sameProductCount) / 2;
        totalNumberOfTuples += 8 * pairsOfEqualProduct;

        return totalNumberOfTuples;
    }

    public static void main(String[] args) {
        //** Requirement
        //- Given an array nums of (distinct positive integers),
        //* return the number of tuples (a, b, c, d) such that:
        //  + a * b = c * d where a, b, c, and d are elements of nums, and a != b != c != d.
        //
        //Example 2:
        //
        //Input: nums = [1,2,4,5,10]
        //Output: 16
        //Explanation: There are 16 valid tuples:
        //(1,10,2,5) , (1,10,5,2) , (10,1,2,5) , (10,1,5,2)
        //(2,5,1,10) , (2,5,10,1) , (5,2,1,10) , (5,2,10,1)
        //(2,10,4,5) , (2,10,5,4) , (10,2,4,5) , (10,2,5,4)
        //(4,5,2,10) , (4,5,10,2) , (5,4,2,10) , (5,4,10,2)
        //
        //** Idea
        //1.
        //1.0,
        //- Method-1:
        //+ Constraints:
        //1 <= nums.length <= 1000
        //1 <= nums[i] <= 10^4
        //All elements in nums are distinct.
        //  + Time: O(n^2)
        //
        //- Brainstorm
        //
        //Example 2:
        //
        //Input: nums = [1,2,4,5,10]
        //Output: 16
        //10 = 2*5 = 1*10
        //  ==> 8 ways
        //- 3 pairs
        //  + (1,2,3)
        //  => (1,2),(1,3),(2,3)
        //  ==> 2+1 = n*(n-1)/2
        //  => rs = count*(count-1)/2 * 8
        //
        //1.1, Special cases
        //
        //1.2, Optimization
        //+ We can sort the pairs
        //  ==> It is not efficient
        //
        //1.3, Complexity
        //- Space: O(n^2)
        //- Time: O(n^2)
        //
        //#Reference:
        //789. Escape The Ghosts
        //1725. Number Of Rectangles That Can Form The Largest Square
        //533. Lonely Pixel II
//        int[] nums = {2,3,4,6};
        int[] nums = {1,2,4,5,10};
        System.out.println(tupleSameProduct(nums));
        System.out.println(tupleSameProductRefer(nums));
    }
}
