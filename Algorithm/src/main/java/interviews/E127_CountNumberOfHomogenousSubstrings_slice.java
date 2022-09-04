package interviews;

public class E127_CountNumberOfHomogenousSubstrings_slice {
    public static int countHomogenous(String s) {
        int n=s.length();
        int start=-1;
        char previousChar=s.charAt(0);
        int rs=0;
        int i;

        for(i=0;i<n;i++){
            char currentChar=s.charAt(i);

            if(currentChar!=previousChar){
                long value =  ((long)i - start - 1) *((long)i-start)/2 % 1_000_000_007;
//                System.out.printf("%s,", value);
                previousChar=currentChar;
                rs= (int) ((rs+value)% 1_000_000_007);
                start=i-1;
            }
        }
        long value=((long)i - start - 1) *((long)i-start)/2 % 1_000_000_007;
        rs= (int) ((rs+value)% 1_000_000_007);
        return rs;
    }
    public static void main(String[] args) {
//        String s="abbcccaa";
        String s="ab";
        System.out.println(countHomogenous(s));
    }
}
