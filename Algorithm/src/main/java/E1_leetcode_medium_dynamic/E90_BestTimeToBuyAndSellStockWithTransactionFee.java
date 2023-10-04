package E1_leetcode_medium_dynamic;

public class E90_BestTimeToBuyAndSellStockWithTransactionFee {

    public static int maxProfitWrong(int[] prices, int fee) {
        int n=prices.length;
        if(n<=1){
            return 0;
        }
        int rs=0;
        int min=prices[0];
        int max=prices[0];
        int prev=min;

        for(int i=1;i<n;i++){
//            System.out.printf("Prev: %s, current prices: %s, min: %s, subtraction: %s, current result: %s\n", prev, prices[i], min, prev-prices[i], rs);
            //1,4, fee=2
            if(prev-prices[i]>=fee&&i!=1){
                if(prev-min-fee>0){
                    rs+=prev-min-fee;
                }
                min=prices[i];
                max=prices[i];
                prev=prices[i];
                continue;
            }
            if(min>prices[i]){
                min=prices[i];
            }
            max=Math.max(prices[i], max);
            prev=prices[i];
        }
        //1,3,2,8,6,4,9
        //prev=9, min = 4, fee=2
//        System.out.printf("prev: %s, min: %s\n", prev, min);
        if(max-min-fee>0){
            rs+=max-min-fee;
        }
        return rs;
    }

    public static int maxProfit(int[] prices, int fee) {
        int n=prices.length;
        //Thể hiện maximum profit mà ta get nếu không hold stock in (i) previous days
        int[] free=new int[n];
        //Thể hiện maximum profit mà ta get nếu hold ở vị trí in (i) previous days
        int[] hold=new int[n];
        hold[0]=-prices[0];
        //- Do nothing được tính như thế nào
        //+ Do thing tại ith được tính bằng max(free[i-1], free[i-2]...)

        for(int i=1;i<n;i++){
            hold[i]=Math.max(hold[i-1], free[i-1]-prices[i]);
            free[i]=Math.max(free[i-1], hold[i-1]+prices[i]-fee);
        }
        return  free[n-1];
    }

    public static int maxProfitOptimization(int[] prices, int fee) {
        int n=prices.length;
        //Thể hiện maximum profit mà ta get nếu không hold stock in (i) previous days
        int free=0;
        //Thể hiện maximum profit mà ta get nếu hold ở vị trí in (i) previous days
        int hold;
        hold=-prices[0];
        //- Do nothing được tính như thế nào
        //+ Do thing tại ith được tính bằng max(free[i-1], free[i-2]...)

        for(int i=1;i<n;i++){
            int tmp=hold;
            hold=Math.max(hold, free-prices[i]);
            free=Math.max(free, tmp+prices[i]-fee);
        }
        return  free;
    }

    public static void main(String[] args) {
        //** Requirement
        //- You are given an array prices where prices[i] is the price of a given stock on the (ith day),
        // and an integer fee representing a transaction fee.
        //* Tìm lợi nhuận tối đa bạn có thể đạt được (max profit)
        //- Bạn có thể hoàn thành bao nhiêu giao dịch tùy thích, nhưng bạn phải trả phí giao dịch cho mỗi giao dịch.
        //- Fee được trả khi ta bán stock ==> Chứ không phải mua.
        //
        //** Idea
        //1.
        //1.0,
        //- Constraint
        //1 <= prices.length <= 5 * 10^4
        //1 <= prices[i] < 5 * 10^4
        //0 <= fee < 5 * 10^4
        //
        //- Brainstorm
        //Ex:
        //Input: prices = [1,3,2,8,4,9], fee = 2
        //Output: 8
        //
        //- Ta chọn như thế nào ==> Luôn chọn lowest price --> sell max price
        //Ex:
        //1,3,2,8,4,9
        //+ 1,3 : profit=0
        //+ 1,3,2 : profit=0
        //+ 1,3,2,8 : profit= 8-1-2=5
        //
        //a,b,c,d,e
        //Price có thể là :
        //+ b-a-2 : mua giá a và bán giá b
        //+ d-c-2 : mua giá c và bán giá d
        //Ex:
        //1,3,5,7,10
        //+ 1 sẽ chọn như thế nào để có kết quả tối ưu?
        //  + Ta có nên chọn 1 <-> 7 hay 10
        //  + Khá khó để tìm được rule của dãy số này.
        //
        //Ex:
        //1,3,2,8
        //+ Có lý do nào ta không chọn cặp (1,3),(2,8) mà lại đi chọn (1,8)?
        //  + Sum1= (3-1-fee)+ (8-2-fee) = 8-1 + 3-2 -(2*fee)
        //  + Sum2=(8-1-fee)
        //  + Nếu: Sum2 > sum1 <=> 3-2 < fee
        //  + Nếu: Sum2 < sum1 <=> 3-2 > fee
        //==> Ta thấy rằng 2,3 đều thuộc khoảng (1,8) ==> Xét (1,8) sẽ tốt hơn xét (1,3)
        //
        //Ex:
        //1,3,7,5,10,3, fee= 3
        //1 --> chọn 10 vì (7-5 <fee)
        //
        //Ex:
        //Input: prices = [1,3,2,8,6,4,9], fee = 2
        //rs= (8-1-2) + (9-4-2) = 5 + 3 = 8
        //
        //- Có thể xảy ra case liên quan đến:
        //+ tăng --> giảm => chọn ra trị min sau thì đoạn tăng lúc trước sẽ tính thiếu
        //Ex:
        //Input: prices = [1,5,2,8,6,4,9], fee = 2
        //+ Sum1= (5-1-2) + (8-2-2) + (9-4-2) = 2 + 4 + 3 = 9
        //+ Sum2= (8-1-2) + (9-4-2) = 5 + 3 = 8
        //
        //Ex:
        //{1,3,2,8,4,9}
        //
        //- Special cases:
        //1,2,5,9, fee=2
        //+ Expected result = 6
        //- Ta chỉ được phép hold 1 lần stock
        //Ex:
        //Buy 1 + sell 9 =6 thay vì:
        //+ buy 1, buy 2
        //  + Buy 2 sell 5 = 5-2-1 = 1
        //  + Buy 1 sell 9 = 9-1-2= 6
        //
//        int[] prices={1,3,7,5,10,3};
//        int fee=3;
//        int[] prices={1,3,2,8,6,4,9};
//        int fee=2;
//        int[] prices={1,3};
//        int fee=1;
//        int[] prices={2,1};
//        int fee=1;
//        int[] prices={2,2,1,1,5,5,3,1,5,4};
        //Sum = (5-1-2) + (5-1-2) = 4
//        int fee=2;
        int[] prices={1,5,5,3,1,5,4,6};
        //Sum = (5-1-2) + (5-1-2) = 4
        int fee=2;
        System.out.println(maxProfit(prices, fee));
        //- Case nếu ta không tìm được cặp giảm >= fee thì sao.
        //Ex:
        //1,6,5,4,5,3,2,5
        //fee=2
        //
        //Ex:
        //(1,6),5,4,5,3,(2,5),4,3,2,(1,8)
        //fee=2
        //- Ta thử lấy 1 ví dụ:
        //+ 1,6,5,3,6
        //fee=2
        //sum= (6-1-2) + (6-3-2) = 3 + 1 = 4
        //
        //(1,6),5,(3,9) : (1!=3)
        //fee=2
        //+ sum1= (6-1-2) + (9-3-2) = 3 + 4 = 7
        //+ sum2= 9-1-2 = 6
        //
        //(1,6),5,3,2,(1,2,9) : 1==1
        //fee=2
        //+ sum1= (6-1-2) + (9-1-2) = 3 + 6 = 9
        //+ sum2= 9-1-2 = 6
        //
        //***** Với tư duy ở trên thì rất khó có thể tìm được cách giải quyết triệt để hết cases:
        //- Mỗi vị trí ith ta có thể thực hiện 1 số thao tác như sau:
        //+ Buy the stock.
        //+ Sell the stock.
        //+ Do nothing.
        //- Ta sẽ tính sub problem như thế nào?
        //- Giả sử dp[i] là maximum profit mà ta có thể get trong (first i days)
        //+ Vì vị trí ith cũng có 2 states như những vị trí khác
        //---> Bài toán chuyển thành 2^n cases recursion, ta không thể giải quyết bình thường được.
        //
        //- Nếu dp[i] có 2 trạng thái:
        //+ Sell stock at (ith) position
        //+ Hold stock at (ith) position
        //- Nếu dùng dp[i] thì công thức truy hồi như sau:
        //- Nếu hold:
        //+ dp[i] = dp[i-1] - prices[i]
        //- Nếu sell:
        //+ dp[i] = dp[i-1] + prices[i]
        //- Nếu do nothing:
        //+ dp[i] = dp[i-1]
        //* WRONG formula vì dp[i] không thể phân biệt được là trước đó có (hold/ sell) hay không
        //--> Nên nếu kết hợp vào thì sẽ bị sai với 2 cases (sell, hold)
        //
        //- Ta chia dp[i] thành 2 array thể hiện trong 2 trường hợp khác nhau được không?
        //
        //+ free[i] : Thể hiện maximum profit mà ta get nếu không hold stock at (ith)
        //+ hold[i] : Thể hiện maximum profit mà ta get nếu hold ở vị trí (ith)
        //==> Tư duy này sai vì vị trí thứ ith không có tác dụng gì nếu đằng trước (i)th hold
        //- Arrays như sau:
        //+ free[i] : Thể hiện maximum profit mà ta get nếu không hold stock in (i) previous days
        //+ hold[i] : Thể hiện maximum profit mà ta get nếu hold ở vị trí in (i) previous days
        //
        //- Công thức truy hồi sẽ như sau:
        //- hold[i]: Hold trong i days before --> Vị trí (i-1) đằng sau chỉ có thể là :
        //  + Free (i-1)th : Không hold trong (i-1) days trước đó + hold thêm stock at (ith)
        //      + hold[i]=free[i-1]-prices[i]
        //  + Do nothing :
        //      + hold[i]=hold[i-1]
        //
        //- free[i] : Don't hold the stock in previous (ith) days --> Vị trí (i-1) đằng sau chỉ có thể là :
        //  + Don't hold in (i-1) days before => free[i] = free[i-1]
        //  + Do nothing : free[i] = hold[i-1] + prices[i] - fee
        //
        //1.1, Optimization
        //- Ta chỉ dùng 2 giá trị gần nhất thế nên không cần tạo array (hold[n], free[n])
        //
        //1.2, Complexity:
        //- Space : O(n)
        //- Time : O(n)
        //
        //#Reference:
        //122. Best Time to Buy and Sell Stock II
        //
        //Ex:
        //783,3146,2600,1939,694,4284,3881,554,167,372,4620,3037,1175,1075
        //fee=655
        int[] arr={1,3,2,8,4,9};
        fee=2;
        System.out.println(maxProfit(arr, fee));
        System.out.println(maxProfitOptimization(arr, fee));
    }
}
