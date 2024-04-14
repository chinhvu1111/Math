package contest;

import java.util.*;

public class E83_KthSmallestAmountWithSingleDenominationCombination {

    public static long findKthSmallestSlow(int[] coins, int k) {
        TreeSet<Long> amounts=new TreeSet<>((a,b) -> Math.toIntExact(b - a));
        HashSet<Long> diffCoins = new HashSet<>();
        HashSet<Long> diffAmounts = new HashSet<>();

        for(int coin : coins){
            diffCoins.add((long) coin);
        }
        Long[] listCoins = diffCoins.toArray(new Long[0]);
        Arrays.sort(listCoins);
        int m=listCoins.length;
        int curK=k;

        for(int i=0;i<m;i++){
            if(diffAmounts.contains(listCoins[i])){
                continue;
            }
            if(!amounts.isEmpty()&&listCoins[i]>=amounts.first()){
                break;
            }
            if(i!=0){
                if(amounts.size()==k){
                    curK= (int) (amounts.first()/listCoins[i])+1;
                }else{
                    curK=k;
                }
                if(curK>k){
                    curK=k;
                }
            }
            for(int j=1;j<=curK;j++){
                if(diffAmounts.contains(listCoins[i]*j)){
                    continue;
                }
                diffAmounts.add(listCoins[i]*j);
                if(amounts.size()==k){
                    //Phải đủ k element ==> break
                    if(listCoins[i]*j>amounts.first()){
                        break;
                    }
                    amounts.remove(amounts.first());
                }
                amounts.add(listCoins[i]*j);
            }
            //Đến cuối mới thêm vào
//            amounts.addAll(curList);
        }
        return amounts.first();
    }

    public static long findKthSmallest(int[] coins, int k) {
        // Tìm kết quả trực tiếp
        //- Giả sử số cần tìm là x
        //
        long low = 1, high=1_000_000_000L*100;
        long rs=0;

        //Time : O(log(k))
        while(low<=high){
            long mid= low + (high-low)/2;
            //Time : O(1^n * n)
            long countNum = countNum(mid, coins);
//            System.out.printf("Mid: %s, count: %s\n",mid, countNum);

            if(countNum>=k){
                if(countNum==k){
                    rs=mid;
                }
                high=mid-1;
            }else{
                low=mid+1;
            }
        }
        return rs;
    }

    public static long countNum(long x, int[] coins){
        int m=coins.length;
        int combinationNum = 1<<m;
        long count=0;

        //Time : O(1^n)
        for(int i=1;i<combinationNum;i++){
            long lcm=1;
            int bits=0;

            //Time: O(n)
            for(int j=0;j<m;j++){
                if((i|(1<<j)) == i){
                    bits++;
                    lcm = leastCommonMultiple(lcm, coins[j]);
                }
            }
            if(bits%2==1){
                count+=x/lcm;
            }else{
                count-=x/lcm;
            }
        }
        return count;
    }

    public static long uscln(long a, long b){
        if(b>a){
            long temp=a;
            a=b;
            b=temp;
        }
        while (b!=0){
            long temp=b;
            b=a%b;
            a=temp;
        }
        return a;
    }

    public static long leastCommonMultiple(long a, long b){
        return a*b/uscln(a, b);
    }

    public static void main(String[] args) {
        //* Requirement
        //- You are given an integer array coins representing coins of different denominations and an integer k.
        //You have an infinite number of coins of each denomination.
        // However, you are not allowed to combine coins of different denominations.
        //* Return the kth smallest amount that can be made using these coins.
        //
        // Idea
        //1.
        //1.0,
        //- Constraint:
        //1 <= coins.length <= 15
        //1 <= coins[i] <= 25
        //1 <= k <= 2 * 10^9
        //coins contains pairwise distinct integers.
        //- k khá lớn
        //- Các pair coin trong array sẽ khác nhau.
        //
        //- Brainstorm
        //- Có thể có case:
        //coins = [2,(3),(3),9], k = 3
        //Ex:
        //coins = [3,6,9], k = 3
        //
        //- Multiple pointers
        //Ex:
        //coins = [1,6,9], k = 3
        //+ 1 : 1,2,3,4,5,6
        //==> k = 6
        //+ 6 : 6,12,18
        //- Bài này dùng kỹ thuật liên quan đến giảm số K phải loop mỗi coins + min queue
        //  ==> Nhưng vẫn failed do k quá lớn (10^9)
        //- Idea binary search
        //  - Tìm result dựa trên binarySearch
        //- X phải thoã mãn các dk:
        //  + X chia hết cho ít nhất 1 số trong coins
        //- Count distinct( Numbers <=X ) == k
        //  + Numbers cần phải chia hết cho 1 trong các coins
        //- Ta sẽ define function pie:
        //  + Tính tổng số <= X + chia hết cho 1 số nào đó trong (coins) array
        //- Để count all các số < X:
        //  + Bình thường thì scan all (1 -> X-1)
        //  ==> Slow vì X khá lớn
        //- Count các số chia hết cho all combinations của coins thì sao?
        //  + List combination by using bit mask : 1 -> 1<<m
        //  + Ta sẽ tìm count số chia hết cho mỗi combination mà ta tìm được.
        //* Questions:
        //- Có thể có trường hợp trùng:
        //Ex:
        //{3,6,9}
        //+ Chọn {3,9} 101 tức là ta tìm số chia hết cho {3,9}
        //+ Chọn {3,6} 110 tức là ta tìm số chia hết cho {3,6} ==> Count các số trên có thể trùng nhau không
        //* CT:
        //- | A U B U C | = |A| + |B| + |C| - |A ^ B| - |A ^ C| - |B ^ C| + |A ^ B ^ C|
        //  + Ta thấy :
        //      + count += bit lẻ
        //      + count -= bit chẵn
        //- | A U B U C | : Tức là số chia hết cho 1 trong ba A or B or C.
        //- Ta tìm các tổ hợp việc chia hết cho:
        //  + | A ^ B ... ^ M | : Sau đó dựa trên số bit mà +/- đi.
        //- Tại sao count lại phải +/- phần chia:
        //  + Count += x/ LCM --> Vì (số lượng số) chia hết cho (LCM < x) chính là (x/LCM)
        //  + Count -= x/ LCM
        //- Tìm 1 (số chia hết cho cả A và B):
        //  + Ta sẽ tìm (BCNN của A và B) là được.
        //- BCNN function:
        //  x= BCNN(a,b)
        //  + x%a==0 : x=a*k (k nhỏ nhất)
        //  + x%b==0 : x=b*l (l nhỏ nhất)
        //  + (4,6): BCNN 12
        //  4*3 = 6*2
        //  4 = 2*k
        //  6 = 2*l
        //  + ucln(4,6) = 2
        //      4 = 2*k
        //      6 = 2*l
        //   a>b:
        //      + b=a%b
        //      + a=b
        // - BCNN = (a*b)/ uscln(a,b)
        //
//        int[] coins = {3,6,9};
//        int k = 3;
//        int[] coins = {5,2};
//        int k = 7;
//        int[] coins = {1,1};
//        int k = 7;
//        int[] coins = {1};
//        int k = 7;
//        int[] coins = {3,8,7};
//        int k = 6;
        //Mid: 11, count: 5
        //  + 3,6,9,7,8
        //Mid: 13, count: 6
        //  + 3,6,9,7,8,12
        //* Note:
        //- Số high cần lớn mới tìm được k ==> Ít nhất phải lớn hơn 10^9 100 lần
        //- Cách tìm UCLN, BCNN
        //  + BCNN = (a*b)/ UCLN(a,b)
        //
        //1.1, Optimization
        //
        //1.2, Complexity
        //- Time : O(log(k)*const)
        //- Space: O(1)
        //
        //#Reference:
        //668. Kth Smallest Number in Multiplication Table
        //
        int[] coins = {6,5};
        int k = 1435065516;
//        System.out.println(findKthSmallestSlow(coins, k));
        System.out.println(findKthSmallest(coins, k));
        System.out.println(uscln(4,6));
        System.out.println(leastCommonMultiple(4,6));
    }
}
