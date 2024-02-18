package E1_Array;

public class E7_CountAndSay {
    public static String getStringCountAndSay(String s){
        int low=0, high=low;
        int n=s.length();
        StringBuilder rs=new StringBuilder();

        while(high<n){
            while(high<n&&s.charAt(high)==s.charAt(low)){
                high++;
            }
            rs.append(high-low);
            rs.append(s.charAt(low));
            low=high;
        }
        return rs.toString();
    }

    public static String countAndSay(int n) {
        String s="1";
        for(int i=2;i<=n;i++){
//            System.out.println(s.length());
            System.out.println(s);
            s=getStringCountAndSay(s);
        }
        return s;
    }
    public static void main(String[] args) {
        //** Requirement
        //- Given n
        //- Calculate countAndSay(n)
        //Ex
        //countAndSay(1) = "1"
        //countAndSay(2) = say "1" = one 1 = "11"
        //countAndSay(3) = say "11" = two 1's = "21"
        //countAndSay(4) = say "21" = one 2 + one 1 = "12" + "11" = "1211"
        //--> Lần lượt.
        //- Tức n là số lần biến đổi
        //Ex:
        //1211 --> 11(1 số 1)|12 (1 số 2) |21 (2 số 1)
        //
        //** Idea
        //1.
        //1.0,
        //- Constraint
        //1 <= n <= 30
        //
        //- Brainstorm
        //- Dùng low, high làm lần lượt thôi.
        //
        //1.1, Optimization
        //-
        //
        //1.2, Complexity
        //+ Given n
        //- Space : O(n) (string)
        //- Time : n * (2^n)
        //String length increase each time:
        // 1 --> 2 --> 4 --> ... 2^n
        //
        //#Reference:
        //271. Encode and Decode Strings
        System.out.println(countAndSay(30));
    }
}
