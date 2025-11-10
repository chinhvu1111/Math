package E1_daily;

public class E342_MaximumDifferenceByRemappingADigit {

    public static int minMaxDifference(int num) {
        int temp=num;
        int firstDigitMax=0;
        int firstDigitTemp=0;

        while (temp!=0){
            if(temp%10!=9){
                firstDigitMax=temp%10;
            }
            //Find the first digit
            if(temp/10==0){
                firstDigitTemp=temp%10;
            }
            temp=temp/10;
        }
//        System.out.println(firstDigitTemp);
        //Reset
        temp=num;
        int firstDigitMin=-1;
        boolean firstDigit=false;

        while (temp!=0){
            //12345
            //82349
            if(temp%10>1||temp/10!=0){
                //If the current digit == first digit
                //==> Ignore
                //&& first digit must be 1
                //- current digit ==0
                //==> Ignore
                if(temp%10==0||(firstDigitMin!=-1&&temp%10==firstDigitTemp&&firstDigitTemp==1)) {
                    temp = temp / 10;
                    continue;
                }
                firstDigitMin=temp%10;
                //11111 ==> We can not use 1 replace by 0
                firstDigit=(temp/10==0)||(firstDigitMin==firstDigitTemp);
            }
            temp=temp/10;
        }
        System.out.printf("%s %s\n", firstDigitMax, firstDigitMin);
        int replaceDigit=0;
        if(firstDigit){
            replaceDigit=1;
        }
//        System.out.println(replaceDigit);
        //Reset
        temp=num;
        int max=0;
        int min=0;
        int mul=1;

        while (temp!=0){
            int minDigit=temp%10;
            int maxDigit=temp%10;
            if(temp%10==firstDigitMax){
                maxDigit=9;
            }
            if(temp%10==firstDigitMin){
                minDigit=replaceDigit;
            }
            max+=maxDigit*mul;
            min+=minDigit*mul;
            mul*=10;
            temp=temp/10;
        }
        return max-min;
    }

    public static void main(String[] args) {
        //** Requirement
        //- You are (given an integer num).
        //- You know that Bob will sneakily remap (one of the 10 possible digits) (0 to 9) to another digit.
        //* Return the difference between (the maximum and minimum values) Bob can make
        // by remapping (exactly one digit) in num.
        //
        //- Notes:
        //  + When Bob remaps (a digit d1) to another (digit d2), Bob (replaces all occurrences) of d1 in num with d2.
        //  + Bob can remap a digit to itself, in which case num (does not change).
        //  + Bob can remap (different digits) for obtaining (minimum and maximum values) respectively.
        //  + The resulting number after remapping (can contain) (leading zeroes).
        //Ex:
        //Example 1:
        //
        //Input: num = 11891
        //Output: 99009
        //Explanation:
        //To achieve the maximum value, Bob can remap the digit 1 to the digit 9 to yield 99899.
        //To achieve the minimum value, Bob can remap the digit 1 to the digit 0, yielding 890.
        //The difference between these two numbers is 99009.
        //
        //** Idea
        //1.
        //1.0,
        //- Method-1:
        //+ Constraints:
        //1 <= num <= 10^8
        //  + Time: O(log(num))
        //
        //* Brainstorm:
        //- First digit != 9 for max
        //- First digit for min
        //
        //1.1, Case
        //
        //
        //
        //1.2, Optimization
        //
        //
        //1.3, Complexity
        //- Time: O(n)
        //- Space: O(1)
//        System.out.println(minMaxDifference(11891));
//        System.out.println(minMaxDifference(90));
//        System.out.println(minMaxDifference(22222));
//        System.out.println(minMaxDifference(199));
        //Wrong val: 100
        //Expected: 900
//        System.out.println(minMaxDifference(123456));
        //923456 - 113456
//        System.out.println(minMaxDifference(111));
        //999 - 111 ==> Don't replace for min
//        System.out.println(minMaxDifference(1101057));
        //1101057
        //9909057 - 1000057
//        System.out.println(minMaxDifference(9));
        System.out.println(minMaxDifference(9288));
        //
        //#Reference:
        //1282. Group the People Given the Group Size They Belong To
        //659. Split Array into Consecutive Subsequences
        //1232. Check If It Is a Straight Line
    }
}
