package E1_leetcode_medium_dynamic;

public class E92_BestTimeToBuyAndSellStockIII {

    public static int maxProfit(int[] prices) {
        int n=prices.length;
        int[] left=new int[n];
        int[] right=new int[n];
        int min=prices[0];
        int max;
        int rs=0;

        for(int i=1;i<n;i++){
            if(min>prices[i]){
                min=prices[i];
            }
            left[i]=Math.max(prices[i]-min, left[i-1]);
            rs=Math.max(rs, left[i]);
        }
        max=prices[n-1];

        for(int i=n-2;i>=0;i--){
            if(max<prices[i]){
                max=prices[i];
            }
            right[i]=Math.max(max-prices[i], right[i+1]);
            if(i>=1){
                rs=Math.max(right[i]+left[i-1], rs);
            }
            rs=Math.max(rs, right[i]);
        }
        return rs;
    }

    public static int maxProfitOptimization(int[] prices) {
        int n=prices.length;
        int[] left=new int[n];
        int[] right=new int[n];
        int min=prices[0];
        int max;
        int rs=0;

        for(int i=1;i<n;i++){
            if(min>prices[i]){
                min=prices[i];
            }
            left[i]=Math.max(prices[i]-min, left[i-1]);
        }
        rs=Math.max(rs, left[n-1]);
        max=prices[n-1];

        for(int i=n-2;i>=0;i--){
            if(max<prices[i]){
                max=prices[i];
            }
            right[i]=Math.max(max-prices[i], right[i+1]);
            if(i>=1){
                rs=Math.max(right[i]+left[i-1], rs);
            }
        }
        rs=Math.max(rs, right[0]);
        return rs;
    }

    public static void main(String[] args) {
        //** Requirement
        //- Given prices, prices[i] is the price of given stock on the ith day.
        //- Một lúc chỉ có thể giữ nhiều nhất 1 stock ==> Nhưng có thể buy and sell on the same day.
        //- Ta chỉ được thực hiện tối đa 2 transactions.
        //
        //** Idea
        //1.
        //1.0,
        //- Constraint
        //1 <= prices.length <= 10^5
        //0 <= prices[i] <= 10^5
        //--> Chỉ có thể xử lý trong O(n)/ O(n*log(n))
        //
        //- Brainstorm
        //- Chỉ thực hiện nhiều nhất <=2 transactions
        //- Liệu thực hiện k transaction có thể tính theo thực hiện k-1 transactions được không
        //Ex:
        //prices = [3,3,5,0,0,3,1,4]
        //- Bài giống kiểu tìm sum subtraction của 2 ranges (send-start) và (end1-start1) lớn nhất
        // (3,5), có thể kết hợp với (0,3),(0,1),(0,4)
        //
        //- Như thế này thì ta sẽ tính max nhất của từng ith --> sang bên trái xem nên chọn giá trị nào
        //==> Sau đó ta tính lần 2 để tính ra max khi thực hiện 2 transaction
        //- Dp là gì?
        //+ dp[i] là max profit khi ta thực hiện 1 transaction của (i) days before
        //+ Idea vẫn còn unclear --> Vậy nếu ta suy luận tiếp thì ta sẽ tính như thế nào?
        //Ex:
        //prices = [1,3,5,2,6,0,3,1,4]
        //+ Result = (6-1) + (4-0) = 9
        //+ Giả sử dp[i] là max profit khi sell ở vị trí (i)th
        //dp=[0,2,4,4,5,5,5,5,5] ==> lấy max dần
        //+ Không thể hiện được element ith được chọn hay không để loại bỏ
        //==> Nó thể hiện được 5 valid khi ta xét từ right -> left ==> Chạy từ biên vào dùng được rồi.
        //Nên dp ta sẽ tính theo từng vị trí:
        //dp=[0,2,4,1,5,0,3,1,4]
        //==> Cái này ta có thể dùng two pointers khi dùng 2 arrays để tính.
        //- Ghép nối array với nhau (Traverse sẽ được thực hiện như thế nào?)
        //Ex:
        //prices = [1,3,5,2,6,0,3,1,4]
        //dp=[0,2,4,1,5,0,3,1,4] ==> Nên xét max dần
        //
        //1.1, Optimization
        //- Vì left[i]=Math.max(left[i-1], max-prices[i]) + i (0 --> n-1)
        //  + rs chỉ cần xét max(rs, left[n-1]) vì theo loop thì left[n-1] chính là giá trị max nhất.ss
        //- Tương tự right[0] cũng chính là giá trị max nhất.
        //  + rs=Math.max(rs, right[0])
        //
        //1.2, Complexity
        //- Space : O(n)
        //- Time : O(n)
        //
        //Method 2:
        //2.
        //2.0, Idea
        //-
//        int[] arr={3,3,5,0,0,3,1,4};
//        int[] arr={1,2,3,4,5};
//        int[] arr={1};
//        int[] arr={1,2};
//        int[] arr={2,1};
        int[] arr={2,2};
        System.out.println(maxProfit(arr));
        System.out.println(maxProfitOptimization(arr));
        //#Reference:
        //121. Best Time to Buy and Sell Stock
        //188. Best Time to Buy and Sell Stock IV
        //689. Maximum Sum of 3 Non-Overlapping Subarrays
    }
}
