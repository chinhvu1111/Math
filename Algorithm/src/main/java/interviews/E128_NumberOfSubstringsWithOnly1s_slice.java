package interviews;

public class E128_NumberOfSubstringsWithOnly1s_slice {

    public static int numSub(String s) {
        int found = 0;
        long ct = 0;
        for(char ch : s.toCharArray()){
            if(ch == '1') {
                found++;
                ct += found;
            }
            else{
                found = 0;
            }
        }
        return (int)(ct % 1_000_000_007);
    }

    public static void main(String[] args) {
//        String s="111111";
//        String s="111001";
        String s="0110111";
        System.out.println(numSub(s));
    }
}
