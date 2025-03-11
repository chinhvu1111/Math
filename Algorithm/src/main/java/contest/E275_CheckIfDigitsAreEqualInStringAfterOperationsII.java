package contest;

import javafx.util.Pair;

import java.util.HashMap;

public class E275_CheckIfDigitsAreEqualInStringAfterOperationsII {

    public static HashMap<Pair<Integer, Integer>, Integer> memo;

    public static int solution(int left, int right, String s){
//        if(left==right){
//            return (s.charAt(left)-'0')*10+s.charAt(right)-'0';
//        }
        Pair<Integer, Integer> pair=new Pair<>(left, right);
        if(memo.containsKey(pair)){
            return memo.get(pair);
        }
        if(left==right){
            memo.put(pair, s.charAt(left)-'0');
            return s.charAt(left)-'0';
        }
        int curVal = (solution(left, right-1, s)%10+solution(left+1, right, s)%10)%10;
        memo.put(pair, curVal);
        return curVal;
    }

    public static boolean hasSameDigits(String s) {
        memo=new HashMap<>();
        int n=s.length();
        int firstDigit = solution(0, n-2, s);
        int secondDigit = solution(1, n-1, s);
        return firstDigit==secondDigit;
    }

    public static void main(String[] args) {
        //** Requirement
        //- You are given a string s consisting of digits. Perform the following operation repeatedly until the string has exactly two digits:
        //- For each pair of consecutive digits in s, starting from the first digit, calculate a new digit as the sum of the two digits modulo 10.
        //- Replace s with the sequence of newly calculated digits, maintaining the order in which they are computed.
        //* Return true if the final two digits in s are the same; otherwise, return false.
        //
        //Ex:
        //Input: s = "34789"
        //Output: false
        //
        //Explanation:
        //
        //Initially, s = "34789".
        //After the first operation, s = "7157".
        //After the second operation, s = "862".
        //After the third operation, s = "48".
        //Since '4' != '8', the output is false.
        //
        //* Idea
        //1.
        //1.0,
        // Method-1:
        //- Constraint
        //3 <= s.length <= 10^5
        //s consists of only digits.
        //  + Length<= 10^5 => Time: O(n)
        //
        //- Brainstorm
        //
        //Ex:
        //Input: s = "34789"
        //- Depth = log(10^5)
        //Ex:
        //abcd
        //(a+b)%10,(b+c)%10,(c+d)%10
        //((a+b)%10+(b+c)%10)%10,(b+c)%10+(c+d)%10
        //s = "34789"
        //"34789" = 347+789
        //"34789" = 71 + 57
        //"34789" = 8 + 2
        //- (Split the string) is same as that we loop over (the all of the elements)
        //
        //- Ignore the idea that we calculate step by step:
        //  + ((a+b)%10+(c+d)%10)%10==(a+b+c+d)%10
        //Ex:
        //Input: s = "34789"
        //step1: 7157
        //step2: 862
        //step3: 48
        //  + 4 = ((3+4)%10 + (4+7)%10)%10 + ((4+7)%10+(7+8)%10)%10
        //  + 4 = ((7+1)%10 + (1+5)%10)%10 = (8+6)%10 = 14%10
        //  3  4  4   7  7    8 8    9
        //  \ /    \ /    \ /    \ /
        //   7      1      5      7
        //    \    /  \  /   \  /
        //      8      6      2
        //       \    / \    /
        //         4      8
        //- 4 <> 3+4+4+7+7+8
        //Ex:
        //Input: s = "34789"
        // s= 3478 +  4789
        // s = (347+478) +...
        // s = 34+47 + ...
        //
        //- Lucas' theorem
        //- Precompute Binomial Coefficients Modulo 10:
        //  + Use Lucas' theorem to compute (binomial coefficients) modulo 2 and 5 separately.
        //  + Combine the results using (the Chinese Remainder Theorem).
        //- Compute Two Sums (f0 and f1):
        //  + Iterate over (the first N characters of s), calculating (binomial coefficients) modulo 10.
        //  + Compute weighted sums (f0 and f1) using (the binomial coefficients).
        //- Compare the Results:
        //  + If f0 is equal to f1, return true; otherwise, return false.
        //
//        String s = "34789";
//        System.out.println(solution(0, 3, s));
//        System.out.println(solution(1, 4, s));
        String s = "20505193698946387143568781580528797610340417809762328620481110368360878771262170156038939940016017031038371530585577200983178273617771270809136209817815765420794235677247079452178552369563283490615776004184627392312679516835653829637805715371376457860731650818395335595960013034159476345846131616014776141088278761825793612526733081818687714162362175453231578794348130941262199158906964587750122312470813361840531649561316510177962291866186063895726765813526397523963794416283723729128529022801667152370070421167022545709011859775351265613339775098050297072606540628556983010320967355503936864719689299982272873076787884759078222701440375684698844136260630957366973770797753784481779677505348121638088812502467158332641090445600985138738944320191161243929400462340610842018714689365029805993575505603517682973164061718058595722185427";
        System.out.println(hasSameDigits(s));
        //
    }
}
