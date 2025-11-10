package E1_daily;

public class E370_Largest3SameDigitNumberInString {

    public static String largestGoodInteger(String num) {
        Character maxDigit=null;
        char curDigit='0';
        int n=num.length();
        int count=0;

        for(int i=0;i<n;i++){
            if(num.charAt(i)==curDigit){
                count++;
            }else{
                count=1;
            }
            curDigit=num.charAt(i);
            if(count==3){
                if(maxDigit == null || maxDigit<curDigit){
                    maxDigit=curDigit;
                }
//                System.out.println(curDigit);
            }
//            System.out.println(curDigit);
//            System.out.println(maxDigit);
        }
//        System.out.println(maxDigit);
        if(maxDigit==null){
            return "";
        }
        return String.valueOf(maxDigit)+ maxDigit + maxDigit;
    }

    public static String largestGoodIntegerRefer(String num) {
        // Assign 'maxDigit' to the NUL character (smallest ASCII value character)
        char maxDigit = '\0';

        // Iterate on characters of the num string.
        for (int index = 0; index <= num.length() - 3; ++index) {
            // If 3 consecutive characters are the same,
            // store the character in 'maxDigit' if it's bigger than what it already stores.
            if (num.charAt(index) == num.charAt(index + 1) && num.charAt(index) == num.charAt(index + 2)) {
                maxDigit = (char) Math.max(maxDigit, num.charAt(index));
            }
        }

        // If 'maxDigit' is NUL, return an empty string; otherwise, return a string of size 3 with 'maxDigit' characters.
        return maxDigit == '\0' ? "" : new String(new char[]{maxDigit, maxDigit, maxDigit});
    }

    public static void main(String[] args) {
        //** Requirement
        //- You are given a string num representing a large integer.
        //- An integer is good if it meets the following conditions:
        //  + It is a substring of num with length 3.
        //  + It consists of only one unique digit.
        //* Return the maximum good integer as a string or an empty string "" if no such integer exists.
        //
        //Note:
        //
        //A substring is a contiguous sequence of characters within a string.
        //There may be leading zeroes in num or a good integer.
        //
        //** Idea
        //1.
        //1.0,
        //- Method-1:
        //+ Constraints:
        //3 <= num.length <= 1000
        //num only consists of digits.
        //
        //* Brainstorm:
        //
        //
        //1.1, Case
        //
        //1.2, Optimization
        //
        //
        //1.3, Complexity
        //- Time: O(n)
        //- Space: O(1)
        System.out.println('7'>'3');
        String num = "6777133339";
        System.out.println(largestGoodInteger(num));
        System.out.println(largestGoodIntegerRefer(num));
    }
}
