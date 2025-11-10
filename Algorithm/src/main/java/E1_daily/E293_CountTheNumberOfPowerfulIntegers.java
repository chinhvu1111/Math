package E1_daily;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class E293_CountTheNumberOfPowerfulIntegers {

    public static long numberOfPowerfulInt(long start, long finish, int limit, String s) {
        for (int i = 0; i < s.length(); i++) {
            if(s.charAt(i)-'0'>limit){
                return 0;
            }
        }
        int maxNumDigit=0;
        int minNumDigit=0;
        long temp=finish;
        List<Integer> maxDigits=new ArrayList<>();
        List<Integer> minDigits=new ArrayList<>();

        while (temp!=0){
            maxDigits.add((int) (temp % 10));
            temp=temp/10;
            maxNumDigit++;
        }
        temp=start;
        while (temp!=0){
            minDigits.add((int) (temp % 10));
            temp=temp/10;
            minNumDigit++;
        }
        Collections.reverse(minDigits);
        Collections.reverse(maxDigits);
        int[] arrMinDigits=new int[minDigits.size()];
        int[] arrMaxDigits=new int[maxDigits.size()];
        //Min
        for (int i = 0; i < arrMinDigits.length; i++) {
            arrMinDigits[i]=minDigits.get(i);
            System.out.printf("%s, ", arrMinDigits[i]);
        }
        System.out.println();
        //Max
        for (int i = 0; i < arrMaxDigits.length; i++) {
            arrMaxDigits[i]=Math.min(limit, maxDigits.get(i));
            System.out.printf("%s, ", arrMaxDigits[i]);
        }
        System.out.println();
        //1(25)
//        long curNum=Long.valueOf(s);
        int curNumDigit=s.length();
        long rs=0;

        while (curNumDigit<=maxNumDigit){
            int[] minDigit=new int[curNumDigit];
            int[] maxDigit=new int[curNumDigit];
            if(curNumDigit==minNumDigit){
                minDigit=arrMinDigits;
            }else if(curNumDigit<minNumDigit){
                curNumDigit++;
                continue;
            }
            boolean isAlwaysLessThanMax=false;
            if(curNumDigit<maxNumDigit) {
                isAlwaysLessThanMax=true;
            }else{
                maxDigit=arrMaxDigits;
            }
            double maxMul = Math.pow(limit, curNumDigit-s.length()-1);
            //150, 215
            System.out.printf("Digit number: %s\n", curNumDigit);
            long curRs=0;
            for(int i=curNumDigit;i>s.length();i--){
                int index=i-curNumDigit;
                if(isAlwaysLessThanMax){
                    curRs+=(limit-minDigit[index])*maxMul;
                    System.out.printf("%s, ", (limit-minDigit[index])*maxMul);
                }else{
                    curRs+=(maxDigit[index]-minDigit[index]-1)*maxMul;
                    System.out.printf("%s, ", (maxDigit[index]-minDigit[index]-1)*maxMul);
                }
                maxMul=maxMul/limit;
            }
            System.out.println();
            rs+=curRs;
            curNumDigit++;
        }
        return rs;
    }

    public static void main(String[] args) {
        //** Requirement
        //- You are given three integers (start, finish, and limit).
        //- You are also (given a 0-indexed string s) representing (a positive integer).
        //- (A positive integer x) is called (powerful) if it (ends with s) (in other words, s is a suffix of x)
        // and (each digit in x) is (("at most") limit).
        //* Return (the total number of powerful integers) in the range [start..finish].
        //- A string x is (a suffix) of a string y if and only if x is (a substring) of y
        // that starts from some index (including 0) in y and extends to the index y.length - 1.
        //
        //- For example, 25 is (a suffix) of 5125 whereas 512 is not.
        //
        //Example 2:
        //
        //Input: start = 15, finish = 215, limit = 6, s = "10"
        //Output: 2
        //Explanation:
        // The powerful integers in the range [15..215] are 110 and 210.
        // All these integers have each digit <= 6, and "10" as a suffix.
        //It can be shown that there are only 2 powerful integers in this range.
        //
        //** Idea
        //1.
        //1.0,
        //- Method-1:
        //+ Constraints:
        //1 <= start <= finish <= 10^15
        //1 <= limit <= 9
        //1 <= s.length <= floor(log10(finish)) + 1
        //s only consists of numeric digits which are at most limit.
        //s does not have leading zeros.
        //  + 1 <= start <= finish <= 10^15 ==> Time: O(log(x))
        //  + s.length <= 16
        //
        //* Brainstorm:
        //
        //Input: start = 15, finish = 215, limit = 6, s = "10"
        //Output: 2
        //15 <= x <= 215
        // + xyz10
        //+ 145 < xyz
        //  + x>1 ==> rs+=10*10
        //  + x==1
        //      + y>4 => rs+=(9-4)*10
        //      + y==4 => rs+=9-5
        //
        //- We start from:
        //  + (min) (the number of digits) => (max) (the number of digits)
        //
        //1.1, Case
        //
        //
        //1.2, Optimization
        //
        //
        //1.3, Complexity
        //
        //
        int start = 15, finish = 215, limit = 6;
        String s = "10";
        //Digit numbers = 3:
        //
        //int start = 15, finish = 48597, limit = 6;
        //String s = "10";
        //1xyzt
        //  + 1[5-6][1-6][1-6][1-6] ==> Check specific
        //  + rs=(limit-5)*(limit-...)
        //2xyzt
        //  + 2[1-6][1-6][1-6][1-6]
        //3xyzt
        //  + 3[1-6][1-6][1-6][1-6]
        //4xyzt
        //  + 4[1-6][1-5][1-6][1-6] ==> Check specific
        //  max[i]-min[i]
        //
        System.out.println(numberOfPowerfulInt(start, finish, limit, s));
    }
}
