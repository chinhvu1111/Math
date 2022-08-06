package leetcode_medium_dynamic;

public class E72_BestSightseeingPair {

    public static int maxScoreSightseeingPair(int[] values) {
//        int prev=0;
//
//        int maxFirst=0;
//        int maxFirstPrev=0;
//        int maxSecond=0;
//        int maxSeconPrev=0;

        int rs=0;
        int n=values.length;
//        int dp[]=new int[n];
        int init=values[0];

        for(int i=1;i<n;i++){
            rs=Math.max(rs, init+values[i]-i);
            init=Math.max(init, values[i]+i);
        }
        return rs;
    }

    public static void main(String[] args) {
//        int values[]=new int[]{8,1,5,2,6};
        int values[]=new int[]{1,2};
        //Công thức suy luận:
        //values[i] + values[j] + i - j
        //= values[i] + i + values[j] - j
        //Bài này khá dễ tư duy như sau:
        //Vì i<j
        //Ta for từ
        // (i=0) tính theo ct (values[i] + i)
        //Current: (i=1) tính theo cty (values[j]-j)
        //rs=Math.max(rs, init+values[i]-i); //Tính giá trị hiện tại
        //init=Math.max(init, values[i]+i); // Update tổng max nhất mới --> Để tính tiếp sau đó.
        System.out.println(maxScoreSightseeingPair(values));
    }
}
