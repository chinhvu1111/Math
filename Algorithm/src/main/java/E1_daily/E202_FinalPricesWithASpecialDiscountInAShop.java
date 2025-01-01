package E1_daily;

import java.util.Stack;

public class E202_FinalPricesWithASpecialDiscountInAShop {

    public static int[] finalPrices(int[] prices) {
        int n=prices.length;
        Stack<Integer> stack=new Stack<>();
        int[] rs=new int[n];

        for(int i=n-1;i>=0;i--){
            while(!stack.isEmpty()&&stack.peek()>prices[i]){
                stack.pop();
            }
            rs[i]=prices[i];
            if(!stack.isEmpty()){
                rs[i]=rs[i]-stack.peek();
            }
            stack.add(prices[i]);
        }
        return rs;
    }

    public static int[] finalPricesRefer(int[] prices) {
        // Create a copy of prices array to store discounted prices
        int[] result = prices.clone();

        Stack<Integer> stack = new Stack<>();

        for (int i = 0; i < prices.length; i++) {
            // Process items that can be discounted by current price
            while (!stack.isEmpty() && prices[stack.peek()] >= prices[i]) {
                // Apply discount to previous item using current price
                result[stack.pop()] -= prices[i];
            }
            // Add current index to stack
            stack.add(i);
        }

        return result;
    }

    public static void main(String[] args) {
        //** Requirement
        //- You are given an integer array prices where prices[i] is the price of (the ith item) in a shop.
        //- There is (a special discount) for items in the shop. If you buy (the ith item),
        // then you will receive (a discount) equivalent to prices[j] where j is the (minimum index)
        //  + such that (j > i) and (prices[j] <= prices[i]).
        //- Otherwise, you will not receive any discount at all.
        //* Return an integer array answer where answer[i] is the final price you will pay for the ith item of the shop,
        // considering the special discount.
        //
        //Example 1:
        //
        //Input: prices = [8,4,6,2,3]
        //Output: [4,2,4,2,3]
        //Explanation:
        //For item 0 with price[0]=8 you will receive a discount equivalent to prices[1]=4, therefore, the final price you will pay is 8 - 4 = 4.
        //For item 1 with price[1]=4 you will receive a discount equivalent to prices[3]=2, therefore, the final price you will pay is 4 - 2 = 2.
        //For item 2 with price[2]=6 you will receive a discount equivalent to prices[3]=2, therefore, the final price you will pay is 6 - 2 = 4.
        //For items 3 and 4 you will not receive any discount at all.
        //
        // Idea
        //1.
        //1.0,
        //- Method-1:
        //+ Constraints:
        //1 <= prices.length <= 500
        //1 <= prices[i] <= 1000
        //
        //- Brainstorm
        //- By using (i), we find j>i such that :
        //  + the price[j]<=price[i]
        //Ex:
        //prices = [8,4,6,2,3]
        //stack = [3]
        //  + add(2):
        //      + 2<3 ==> pop(3)
        //      stack = [2]
        //      + Because we prefer keeping the (minimal value) with (minimal index)
        //      + val=2(index=3), val=3(index=4)
        //stack=[2]
        //  + add(6) => stack[2,6]
        //stack=[2,6]
        //  + add(4) ==> We pop(6) because the role of element with (val=4) could be replaced by (val=6)
        //  ==> The next elements will use (val=4) rather than (val=6)
        //
        int[] prices = {8,4,6,2,3};
//        int[] rs=finalPrices(prices);
        int[] rs=finalPricesRefer(prices);
        for (int i = 0; i < prices.length; i++) {
            System.out.printf("%s, ", rs[i]);
        }
        System.out.println();
        //
        //1.1, Optimization
        //- We can use the index approach to solve this problem:
        //Ex:
        //prices = [8,4,6,2,3]
        //+ val=6 ==> has ability to update (val=8)
        //==> stack store index of elements
        //  + When update any element ==> We pop the index of the element out of stack
        //
        //1.2, Complexity
        //- Space: O(n)
        //- Time: O(n)
        //
        //#Reference:
        //999. Available Captures for Rook
        //1330. Reverse Subarray To Maximize Array Value
        //2866. Beautiful Towers II
        //
        //#More Good Stack Problems
        //Find the Most Competitive Subsequence
        //Minimum Number of Removals to Make Mountain Array
        //Final Prices With a Special Discount in a Shop
        //Constrained Subsequence Sum
        //Minimum Cost Tree From Leaf Values
        //Sum of Subarray Minimums
        //Online Stock Span
        //Score of Parentheses
        //Next Greater Element II
        //Next Greater Element I
        //Largest Rectangle in Histogram
        //Trapping Rain Water
    }
}
