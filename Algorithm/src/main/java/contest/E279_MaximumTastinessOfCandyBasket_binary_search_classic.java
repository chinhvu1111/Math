package contest;

import java.util.Arrays;

public class E279_MaximumTastinessOfCandyBasket_binary_search_classic {

    public static int searchUpperBound(int[] price, int keyPrice){
        int n=price.length;
        int low=0, high=n-1;
        int rs=-1;

        while(low<=high){
            int mid=low+(high-low)/2;
            if(price[mid]>=keyPrice){
                rs=price[mid];
                high=mid-1;
            }else{
                low=mid+1;
            }
        }
        return rs;
    }

    public static int countSelectedCandies(int diff, int[] price, int k){
        int curPrice = price[0];
        int count=1;
        int curDiff=Integer.MAX_VALUE;
//        System.out.printf("Cur diff: %s\n",diff);
//        System.out.println(curPrice);

        //Time: O(k)
        while(curPrice!=-1&&count<k){
            int nextPrice = curPrice+diff;
            int prevPrice = curPrice;
            //Time: O(log(n))
            curPrice = searchUpperBound(price, nextPrice);
//            System.out.println(curPrice);
            curDiff=Math.min(curPrice-prevPrice, curDiff);
            count++;
        }
        if(count==k&&curPrice!=-1){
            return curDiff;
        }
        return -1;
    }

    public static int maximumTastiness(int[] price, int k) {
        int n=price.length;
//        int max=0;
//        for(int i=0;i<n;i++){
//            max=Math.max(price[i], max);
//        }
        //Space: O(log(n))
        //Time: O(n*log(n))
        Arrays.sort(price);
        int low=0, high=price[n-1]-price[0];
        int rs=0;

        //Time: O(log(n))
        while(low<=high){
            int mid=low+(high-low)/2;
            //Time: O(k)
            int curMinDiff = countSelectedCandies(mid, price, k);
//            System.out.printf("curMinDiff: %s, mid: %s\n", curMinDiff, mid);
            //diff is too big
            if(curMinDiff==-1){
                high=mid-1;
            }else{
                rs=Math.max(curMinDiff, rs);
                low=mid+1;
            }
        }
        return rs;
    }

    public static boolean searchFunction(int diff, int[] price, int k){
        int prevPrice=price[0];
        int count=1;
        for(int i=1;i<price.length;i++){
            if(price[i]-prevPrice>=diff){
                count++;
                prevPrice=price[i];
            }
        }
        if(count==k){
            return true;
        }
        return false;
    }

    public static int maximumTastinessReference(int[] price, int k) {
        int n=price.length;
        Arrays.sort(price);
        int lo = 0;
        int hi =  price[n-1] - price[0];
        int rs=-1;

        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2; // + 1 to push mid to the right because the monotonicity is [T, T, T, F, F] and we want the middle to be chosen right this way we cut hi down
            if(searchFunction(mid, price, k)) {
                rs=mid;
                lo = mid+1;
            } else {
                hi = mid-1;
            }
        }

        return rs;
    }

    public static void main(String[] args) {
        //** Requirement
        //- You are given (an array of positive integers price) where price[i] denotes (the price of the ith candy) and (a positive integer k).
        //- The store sells baskets of (k distinct candies).
        //- (The tastiness of a candy basket) is (the smallest absolute difference) of (the prices of any two candies) in the basket.
        //* Return (the "maximum" tastiness of a candy basket).
        //- (Distinct candies) mean (different candies)
        //
        //Example 1:
        //
        //Input: price = [13,5,1,8,21,2], k = 3
        //Output: 8
        //Explanation: Choose the candies with the prices [13,5,21].
        //The tastiness of the candy basket is: min(|13 - 5|, |13 - 21|, |5 - 21|) = min(8, 8, 16) = 8.
        //It can be proven that 8 is the maximum tastiness that can be achieved.
        //
        // Idea
        //1.
        //1.0,
        //- Method-1:
        //+ Constraints:
        //2 <= k <= price.length <= 10^5
        //1 <= price[i] <= 10^9
        //  + Length <= 10^5 ==> Time: O(n)/ O(n*k)
        //
        //- Brainstorm
        //- Min absolute difference is the:
        //  + | second min - first min |
        //  ==> Wrong because [1,100,101] ==> 99 is not min value
        //
        //Example 1:
        //Input: price = [13,5,1,8,21,2], k = 3
        //Output: 8
        //- sort(price) = [1,2,5,8,13,21]
        //- Binary search?
        //  + diff = x
        //  + count (number of element price) we can get from (the array)
        //      + Possible: low=mid+1
        //      + Impossible: high=mid-1
        //
        //
        //1.2, Special case
        //
        //
        //1.3, Optimization
        //- We don't need to loop to find max
        //  + sort ==> max= price[n-1]-price[0]
        //
        //- Đoạn search ta không tính (min diff) do sợ sẽ có d(iff không khớp) + (max bên ngoài)
        //  + Chỉ cần (rs=mid), low=mid+1 là được
        //      + Vì giá trị cuối cùng rs=low chắc chắn sẽ khớp với 1 pair nào đó vì:
        //          + Ta sẽ luôn xét ở "biên" (Boundary)
        //          ==> Ở boundary diff sẽ trùng subtraction của pair nào đó.
        //
        //
        //1.4, Complexity
        //- Space: O(log(n))
        //- Time: O(log(n)^2*k) ==> O(n*log(n+max-min))
        //
//        int[] price = {13,5,1,8,21,2};
//        int k = 3;
        int[] price = {1,5};
        int k = 2;
        System.out.println(maximumTastiness(price, k));
        System.out.println(maximumTastinessReference(price, k));
        //
        //#Reference:
        //976. Largest Perimeter Triangle
        //3049. Earliest Second to Mark Indices II
        //2421. Number of Good Paths
    }
}
