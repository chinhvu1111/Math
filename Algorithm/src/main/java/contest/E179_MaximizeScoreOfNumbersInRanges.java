package contest;

import java.util.Arrays;

public class E179_MaximizeScoreOfNumbersInRanges {

    public static int maxPossibleScore(int[] start, int d) {
        int n=start.length;
        Arrays.sort(start);
        int low=0;
        int high=Integer.MAX_VALUE;
        int rs=-1;

        while(low<=high){
            //Min absolute:
            //  + Hiệu ==> Ta luôn lấy số dương
            //  + Số???
            //- Tìm min absolute x xem có thoả mãn hay không
            //- Check xem nó có thể là min của all array hay không:
            //  + start[i]+d, start[i+1]+d,...
            //  + x>= start[i+1]-start[i]
            //
            //- Ta có thể chọn số dựa trên mid
            int mid=low+(high-low)/2;
            int count=1;
            int lastChoose=start[0];
            for(int i=0;i<n-1;i++){
                if(lastChoose+mid<=start[i+1]+d){
                    lastChoose=Math.max(start[i+1], lastChoose+mid);
                    count++;
                }else {
                    break;
                }
            }
//            System.out.printf("%s %s\n", mid, count);
            if(count>=n){
                rs=mid;
                low=mid+1;
            }else{
                high=mid-1;
            }
        }
        return rs;
    }

    public static void main(String[] args) {
        //* Requirement
        //- You are given an array of (integers start) and (an integer d), representing (n intervals) [start[i], start[i] + d].
        //- You are asked to choose (n integers) where the (ith integer) must belong to the (ith interval).
        //- The (score of the chosen integers) is defined as (the minimum absolute) difference between (any two integers) that have been chosen.
        //* Return (the maximum possible score) of (the chosen integers).
        //
        // Idea
        //1.
        //1.0,
        //- Constraint:
        //2 <= start.length <= 10^5
        //0 <= start[i] <= 10^9
        //0 <= d <= 10^9
        //  + length = 10^5 ==> Time: O(n)
        //  + start[i], d<=10^9 ==> long
        //      + binary search???
        //  + start[i]>=0
        //
        //** Brainstorm
        //- Mỗi số + d:
        //  + Chọn hiệu giữa các cặp để min nhất ntn?
        //- Bài toán con:
        //  + Có n số:
        //      + Hiệu |m| min của cặp của n số là gì?
        //==> Sort thôi ==> Sau đó xét từng cặp 1
        //
        //- Nếu chọn trong khoảng (start[i]+d):
        //  + Thì sẽ có case:
        //  choose[i+1] < choose[i] (start[i+1]>start[i])
        //  6,10, d=7
        //  choose=[13,11]
        //
        //Example 1:
        //Input: start = [6,0,3], d = 2
        //Output: 4
        //Explanation:
        //The maximum possible score can be obtained by choosing integers: 8, 0, and 4.
        // The score of these chosen integers is min(|8 - 0|, |8 - 4|, |0 - 4|) which equals 4.
        //Sort:
        //start=[0,3,6]
        //- Ta hướng đến việc maximize |a-b| bất kỳ:
        //
        //- Binary search được
        //
//        int[] start = {6,0,3};
//        int d = 2;
//        int[] start = {2,6,13,13};
        //2,8,14,20
//        int d = 5;
//        int[] start = {2,3};
//        int d = 0;
//        int[] start = {1000000000,1000000000};
//        int d = 0;
        //[1000000000,1000000000]
        //0
//        int[] start = {1000000000,1000000000};
//        int d = 1000000000;
//        int[] start = {1000000000,0};
//        int d = 1000000000;
        //[1000000000,0]
        //1000000000
        //[0,9,2,9]
        //2
        int[] start = {0,9,2,9};
        //0,2,9,9
        //0,3,9
        int d = 2;
        //output: 3
        //Expected: 2
        System.out.println(maxPossibleScore(start, d));
//        System.out.println(maxScore(start, d));
    }
}
