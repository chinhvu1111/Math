package E1_daily;

public class E177_SwapAdjacentInLRString {

    public static boolean canTransform(String start, String result) {
        return true;
    }
    public static void main(String[] args) {
        //** Requirement
        //- In a string composed of 'L', 'R', and 'X' characters, like "RXXLRXRXL",
        // a move consists of either
        //  + replacing one occurrence of "XL" with "LX",
        // or
        //  + replacing one occurrence of "RX" with "XR".
        //- Given the starting (string start) and the ending (string result),
        //* Return True
        //  + if and only if there exists a sequence of moves to transform (start to result).
        //
        //Example 1:
        //Input: start = "RXXLRXRXL", result = "XRLXXRRLX"
        //Output: true
        //Explanation: We can transform start to result following these steps:
        //RXXLRXRXL ->
        //XRXLRXRXL ->
        //XRLXRXRXL ->
        //XRLXXRRXL ->
        //XRLXXRRLX
        //
        // Idea
        //1.
        //1.0,
        //- Method-1:
        //Constraints:
        //1 <= start.length <= 10^4
        //start.length == result.length
        //Both start and result will only consist of characters in 'L', 'R', and 'X'.
        //  + Length<=10^4 ==> Time: O(n*log(n))
        //
        //- Brainstorm
        //
        //Example 1:
        //Input: start = "RXXLRXRXL", result = "XRLXXRRLX"
        //Output: true
        //Explanation: We can transform start to result following these steps:
        //|RX|XLRXRXL ->
        //XR|XL|RXRXL ->
        //XRLX|RX|RXL ->
        //XRLXXRR|XL| ->
        //XRLXXRRLX
        //
        //- Replacement:
        //  + XL <=> LX
        //  + RX <=> XR
        //Ex:
        //|XL|RX
        //=> LXRX = LX|RX|
        //+ It is hard to find the case when
        //  + (XL -> LX) can combine with RX
        //
        //+ XL <=> LX
        //  + L => move to left
        //+ RX <=> XR
        //  + R => move to right
        //
        //- We can consider X as the _
        //  + Having the same idea as E176
        //==> Transfer the problem to the hard problem
        //
        //1.1, Optimization
        //1.2, Complexity
        //- Space: O(1)
        //- Time: O(n)
        //
        //#Reference:
        //12. Integer to Roman
        //1332. Remove Palindromic Subsequences
        //2227. Encrypt and Decrypt Strings
        //
    }
}
