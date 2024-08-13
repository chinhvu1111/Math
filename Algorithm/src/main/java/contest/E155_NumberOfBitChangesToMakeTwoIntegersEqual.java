package contest;

public class E155_NumberOfBitChangesToMakeTwoIntegersEqual {

    public static int minChanges(int n, int k) {
        //n = 1101
        //k = 0100
        // xor
        // n có bit là 0 --> k có bit là 1
        //return -1
        if(Integer.bitCount(k)>Integer.bitCount(n)){
            return -1;
        }
//        int lengthN=0;
//        int tempN=n;
//        while (tempN>0){
//            tempN=tempN>>1;
//            lengthN++;
//        }
//        int lengthK=0;
//        int tempK=k;
//        while (tempK>0){
//            tempK=tempK>>1;
//            lengthK++;
//        }
//        if(lengthK!=lengthN){
//            return -1;
//        }
        int tempN=n;
        int tempK=k;
        while (tempK>0||tempN>0){
            int curBitK=tempK&1;
            int curBitN=tempN&1;
            if(curBitK==1&&curBitN==0){
                return -1;
            }
            tempK=tempK>>1;
            tempN=tempN>>1;
        }
        return Integer.bitCount(n^k);
    }

    public static void main(String[] args) {
//        int n=13;
//        int k=4;
        int n=14;
        int k=13;
        System.out.println(minChanges(n, k));
    }
}
