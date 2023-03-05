package contest;

public class E13_FindTheDivisibilityArrayOfAString {

    public static int[] divisibilityArray(String word, int m) {
        int n=word.length();
        long prevValue=0;
        int[] rs=new int[n];

        for(int i=0;i<n;i++){
            prevValue=(prevValue*10+word.charAt(i)-'0')%m;
            System.out.println(prevValue);
            rs[i]=prevValue==0?1:0;
        }
        return rs;
    }

    public static void println(int[] arr){
        for (int j : arr) {
            System.out.print(j + ",");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        //Bài này chỉ cần % là được
//        String word = "998244353";
//        int m = 3;
//        String word = "1010";
//        int m = 10;
        //==> Có vẻ công thức trên đúng
        String word = "100000000010000000003019999999961000000000";
        int m = 1000000000;
        int[] rs=divisibilityArray(word, m);
        println(rs);
    }
}
