package E1_daily;

import java.util.HashMap;
import java.util.Map;

public class E64_IntegerToEnglishWords {

    public static Map<Integer, String> numToWord;

    public static String convertNumToStr(int num){
        StringBuilder curRs=new StringBuilder();
        if(num>=100){
            curRs.append(numToWord.get(num/100)).append(" Hundred");
            num=num%100;
        }
        if(num>=10){
            if(num/10==1){
                curRs.append(" ");
                curRs.append(numToWord.get(num));
                num=0;
            }else{
                curRs.append(" ");
                curRs.append(numToWord.get(num-num%10));
                num=num%10;
            }
        }
        if(num!=0){
            curRs.append(" ");
            curRs.append(numToWord.get(num));
        }
        return curRs.toString().trim();
    }

    public static String numberToWords(int num) {
        if(num==0){
            return "Zero";
        }
        numToWord=new HashMap<>();
        numToWord.put(1, "One");
        numToWord.put(2, "Two");
        numToWord.put(3, "Three");
        numToWord.put(4, "Four");
        numToWord.put(5, "Five");
        numToWord.put(6, "Six");
        numToWord.put(7, "Seven");
        numToWord.put(8, "Eight");
        numToWord.put(9, "Nine");
        numToWord.put(10, "Ten");
        numToWord.put(11, "Eleven");
        numToWord.put(12, "Twelve");
        numToWord.put(13, "Thirteen");
        numToWord.put(14, "Fourteen");
        numToWord.put(15, "Fifteen");
        numToWord.put(16, "Sixteen");
        numToWord.put(17, "Seventeen");
        numToWord.put(18, "Eighteen");
        numToWord.put(19, "Nineteen");
        numToWord.put(20, "Twenty");
        numToWord.put(30, "Thirty");
        numToWord.put(40, "Forty");
        numToWord.put(50, "Fifty");
        numToWord.put(60, "Sixty");
        numToWord.put(70, "Seventy");
        numToWord.put(80, "Eighty");
        numToWord.put(90, "Ninety");
        int count=0;
        StringBuilder rs=new StringBuilder();
        int curNum=0;
        int multiply=1;

        while (num!=0){
            curNum+=multiply*(num%10);
            num/=10;
            count++;
            multiply=multiply*10;
            if(count%3==0||num==0){
                if(curNum!=0){
                    StringBuilder curRs=new StringBuilder();
                    curRs.append(" ");
                    curRs.append(convertNumToStr(curNum));
                    curRs.append(" ");
                    if(count>=4&&count<=6){
                        curRs.append("Thousand");
                    }else if(count>=7&&count<10){
                        curRs.append("Million");
                    }else if(count>=10){
                        curRs.append("Billion");
                    }
                    rs.insert(0, curRs);
                }
                curNum=0;
                multiply=1;
            }
        }
        return rs.toString().trim();
    }

    public static void main(String[] args) {
        //** Requirement
        //- Convert (a non-negative integer num) to its English words representation.
        //
        //** Idea
        //1.
        //1.0,
        //* Method-1:
        //- Constraint
        //0 <= num <= 2^31 - 1
        //+ 2_147_483_647
        //
        //- Brainstorm
        //- Cần giới hạn để chuyển số:
        //  + two billion one hundred forty-seven million four hundred eighty-three thousand six hundred forty-seven
        //      + 2_147_483_647
        //- Ta thấy rằng ta chỉ quan tâm tập 3 số thôi:
        //Ex:
        //1_000_000_000: billion
        //1_000_000: million
        //1_000: thousand
        //100 : Hundred
        //10 : ten
        //
        //1.1, Optimization
        //1.2, Complexity
        //- N is length of the number
        //- Space: O(Constant)
        //- Time: O(n)
        //
//        System.out.println(numberToWords(2_147_483_647));
//        System.out.println(numberToWords(0));
//        System.out.println(numberToWords(1));
//        System.out.println(numberToWords(12));
//        System.out.println(numberToWords(123));
//        System.out.println(numberToWords(12345));
//        System.out.println(numberToWords(1234567));
        System.out.println(numberToWords(1000000));
        //#Reference:
        //12. Integer to Roman
        //
    }
}
