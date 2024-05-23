package E1_codechef;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class E3_InvertAndEqualize {

    public static int solution(String s){
        int numOne=0;
        int numZero=0;
        int n = s.length();

        //101111000
        //
        int index=-2;
        for(int i=0;i<n;i++){
            //index=i
            if (s.charAt(i)=='0') {
                if((i!=index+1)){
                    numZero++;
                }
                index=i;
            }
        }
        index=-2;
        for(int i=0;i<n;i++){
            if (s.charAt(i)=='1') {
                if(i!=index+1){
                    numOne++;
                }
                index=i;
            }
        }
//        System.out.printf("Num zero: %s, num one: %s\n", numZero, numOne);
        return Math.min(numZero, numOne);
    }

    public static void main(String[] args) throws IOException {
        //* Requirement
        //- Given binary string
        //- Ta có thể chọn các bit cùng value --> flip chúng
        //* Find (min number of such operations) needed to make all character of S equal.
        //Ex:
        //S=101111000.
        //==> 100000000
        //==> 000000000
        //
        // Idea
        //1.
        //1.0,
        //- Constraint:
        //
        //
        //- Brainstorm
        //- Ta hoàn toàn có thể flip đan xen nhau
        //  + Nhưng swap:
        //      + 1111000 --> 1111|111
        //      + 1111000 --> 0000|000 -> 1111|111
        //
        //10001
        //
        //Ex:
        //101111 ==> 1
        //
//        String s="101111000";
//        String s="111111000";
//        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
//        String input = bufferedReader.readLine();
//        int t = Integer.parseInt(input);
//        for(int i=0;i<t;i++) {
//            String len = bufferedReader.readLine();
//            String s = bufferedReader.readLine();
//            System.out.println(solution(s));
//        }
        String s="111111";
//        String s="0110";
//        String s="01";
//        String s="001";
//        String s="101";
//        String s="101111000";
        System.out.println(solution(s));
    }
}
