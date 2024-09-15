package E1_daily;

public class E87_SumOfDigitsOfStringAfterConvert {

    public static int getLucky(String s, int k) {
        int n=s.length();
        StringBuilder num=new StringBuilder();

        for(int i=0;i<n;i++){
            num.append(s.charAt(i)-'a'+1);
        }
//        System.out.println(num);
        int rs=0;
        for(int i=0;i<k;i++){
//            System.out.println(num);
            int newNum=0;
            //9878
            //
            for(int j=0;j<num.length();j++){
                newNum+=num.charAt(j)-'0';
            }
            num=new StringBuilder();
            num.append(newNum);
            rs=newNum;
//            System.out.printf("new num: %s\n", num);
        }
        return rs;
    }

    public static int getLuckyOptimization(String s, int k) {
        int n=s.length();
        int num=0;

        for(int i=0;i<n;i++){
            int curNum = s.charAt(i)-'a'+1;
            while (curNum!=0){
                num+=curNum%10;
                curNum=curNum/10;
            }
        }
//        System.out.println(num);
        for(int i=1;i<k;i++){
            int curNum=num;
//            System.out.println(num);
            num=0;
            //9878
            //
            while(curNum!=0){
                num+=(curNum%10);
                curNum=curNum/10;
            }
//            System.out.printf("new num: %s\n", num);
        }
        return num;
    }

    public static void main(String[] args) {
        //** Requirement
        //- You are given a string s consisting of (lowercase English letters), and an integer k.
        //- First, convert (s) into (an integer) by replacing (each letter) with (its position) in the (alphabet)
        // (i.e., replace 'a' with 1, 'b' with 2, ..., 'z' with 26).
        //- Then, transform (the integer) by replacing it with (the sum of its digits).
        //  + Repeat the transform operation (k times) in total.
        //
        //For example, if s = "zbax" and k = 2, then the resulting integer would be 8 by the following operations:
        //Convert: "zbax" ➝ "(26)(2)(1)(24)" ➝ "262124" ➝ 262124
        //Transform #1: 262124 ➝ 2 + 6 + 2 + 1 + 2 + 4 ➝ 17
        //Transform #2: 17 ➝ 1 + 7 ➝ 8
        //* Return the resulting integer after performing the operations described above.
        //
        // Idea
        //1.
        //1.0,
        // Method-1:
        //- Constraint
        //1 <= s.length <= 100
        //1 <= k <= 10
        //s consists of lowercase English letters.
        //  + Length không quá lớn ==> O(n^3) được
        //
        //- Brainstorm
        //1.0,
        //- Dùng stringBuilder sau đó cast theo number
        //
        //1.1, Optimization
        //- Do k>1:
        //- Nếu cộng luôn thì có được không?
        //- Special cases khi cộng luôn:
        //  + Số có >= 2 digits
        //Ex:
        //"leetcode" ➝ "(12)(5)(5)(20)(3)(15)(4)(5)" ➝ "12552031545" ➝ 12552031545
        //  + (12) : Mà cộng luôn thì sẽ bị sai
        //  ==> (12) = 1+3
//        String s = "iiii";
//        int k = 1;
//        String s = "dbvmfhnttvr";
//        int k = 5;
        String s = "hvmhoasabaymnmsd";
        int k = 1;
        System.out.println(getLucky(s, k));
        System.out.println(getLuckyOptimization(s, k));
        //
        //#Reference:
        //2180. Count Integers With Even Digit Sum
    }
}
