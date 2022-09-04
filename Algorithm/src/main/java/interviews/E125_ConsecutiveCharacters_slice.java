package interviews;

public class E125_ConsecutiveCharacters_slice {

    public static int maxPower(String s) {
        int i;
        char previousChar=s.charAt(0);
        System.out.println(previousChar);
        int n=s.length();
        int rs=1;
        int start=-1;

        for(i=1;i<n;i++){
            char currentChar=s.charAt(i);

            if(currentChar!=previousChar){
                rs=Math.max(rs, i-start-1);
                start=i-1;
                previousChar=currentChar;
                System.out.printf("%s %s %s\n", rs, previousChar, s.charAt(i));
            }
        }
        rs=Math.max(rs, i-start-1);
        return rs;
    }

    public static void main(String[] args) {
//        String s="leetcode";
//        String s="abbcccddddeeeeedcba";
        String s="tourist";
        System.out.println(maxPower(s));
        //Bài này tư duy như sau:
        //1,
        //1.1, Để tránh case đầu tiên : l(0)e(1) : nếu rs= i - start -1 =0 --> Sai case đầu
        //--> start =-1
        //1.2,
        //l(0)e(1)et(3)code
        //+ if(t!=e) --> start có 2 lựa chọn để lựa chọn Index (l(0), e(1))
        //--> Nếu start = i-1 --> (0) ==> rs= i - start -1 = (3-0-1 ==2)
        //--> Là kết quả bài toán.
    }
}
