package E1_leetcode_medium_dynamic;

public class E93_1_BestTimeToBuyAndSellStock {

    public static int maxProfit(int[] prices) {
        int n=prices.length;
        int[] minLeft=new int[n];
        int[] maxRight=new int[n];
        int min=Integer.MAX_VALUE;
        int max=Integer.MIN_VALUE;

        for(int i=0;i<n;i++){
            min=Math.min(min, prices[i]);
            max=Math.max(max, prices[n-i-1]);
            minLeft[i]=min;
            maxRight[n-i-1]=max;
        }
        int rs=0;

        for(int i=0;i<n;i++){
            rs=Math.max(rs, maxRight[i]-minLeft[i]);
        }
        return rs;
    }

    public static int maxProfitRefactor(int[] prices) {
        int n=prices.length;
        int min=Integer.MAX_VALUE;
        int max=Integer.MIN_VALUE;
        int rs=0;

        //2,3,5,3,1
        for(int i=0;i<n;i++){
            if(max<=prices[i]){
                max=prices[i];
                rs=Math.max(rs, max-min);
                // System.out.printf("Max: %s, min: %s\n", max, min);
            }
            if(min>prices[i]){
                min=prices[i];
                if(max>min){
                    max=min;
                }
            }
        }
        return Math.max(0, rs);
    }


    public static int maxProfitOptimization(int[] prices) {
        int min=Integer.MAX_VALUE;
        int rs=0;

        //2,3,5,3,1
        for (int price : prices) {
            if (min > price) {
                min = price;
            } else if (price - min > rs) {
                rs = price - min;
            }
        }
        return rs;
    }

    public static void main(String[] args) {
//        int[] prices = {7,1,5,3,6,4};
        int[] prices = {2,3,5,3,1};
        System.out.println(maxProfitRefactor(prices));
        //+ prices[i]<min : min = prices[i]==> Prices[i] như ta thấy không dùng cho max được.
        //+ Check prices[i]-min (Tức là min từ 0 --> i-1) có lớn hơn giả trị rs cũ hay không
        //> --> Update
        System.out.println(maxProfitOptimization(prices));
    }
}
