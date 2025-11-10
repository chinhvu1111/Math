package E1_daily;

public class E294_CountSymmetricIntegers {

    public static boolean isSymmetric(int curVal){
        int numCount=0;
        int temp=curVal;

        while (temp!=0){
            numCount++;
            temp=temp/10;
        }
        if(numCount%2==1){
            return false;
        }
        int mul=1;
        int sumRight=0;
        int sumLeft=0;
        int curValTmp=curVal;

        for (int i = 0; i < numCount; i++) {
            int curDigit = curValTmp%10;
            if(i<numCount/2){
                sumRight+=curDigit;
            }else{
                sumLeft+=curDigit;
            }
            mul=mul*10;
            curValTmp=curValTmp/10;
        }
        return sumRight==sumLeft;
    }

    public static int countSymmetricIntegers(int low, int high) {
        int rs=0;
        for (int i = low; i <=high ; i++) {
            if(isSymmetric(i)){
                rs++;
            }
        }
        return rs;
    }

    public static int countSymmetricIntegersRefer(int low, int high) {
        int res = 0;
        for (int a = low; a <= high; ++a) {
            if (a < 100 && a % 11 == 0) {
                res++;
            } else if (1000 <= a && a < 10000) {
                int left = a / 1000 + (a % 1000) / 100;
                int right = (a % 100) / 10 + (a % 10);
                if (left == right) {
                    res++;
                }
            }
        }
        return res;
    }

    public static void main(String[] args) {
        //- You are given (two positive integers) (low and high).
        //- (An integer x) consisting of (2 * n digits) is symmetric
        // if (the sum of the (first) n digits) of x is equal to (the sum of the ("last") n digits) of x.
        //- Numbers with (an odd number of digits) are never symmetric.
        //* Return (the number of symmetric integers) in the range [low, high].
        //
        //Example 2:
        //
        //Input: low = 1200, high = 1230
        //Output: 4
        //Explanation: There are 4 symmetric integers between 1200 and 1230: 1203, 1212, 1221, and 1230.
        //
        //* Constraints:
        //1 <= low <= high <= 10^4
        //
        //* Optimization:
        //1 <= low <= high <= 10^4
        //  ==> Use only 1000
        //
//        int x=1221;
        int x=3214;
        System.out.println(isSymmetric(x));
        int low = 1200, high = 1230;
        System.out.println(countSymmetricIntegers(low, high));
        System.out.println(countSymmetricIntegersRefer(low, high));
    }
}
