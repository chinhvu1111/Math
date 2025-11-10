package contest;

import java.util.HashSet;

public class E300_Unique3DigitEvenNumbers {

    public static int totalNumbers(int[] digits) {
        HashSet<Integer> uniqueNum=new HashSet<>();
        int n=digits.length;
        for (int i = 0; i < n; i++) {
            if(digits[i]==0){
                continue;
            }
            for (int j = 0; j < n; j++) {
                if(i==j){
                    continue;
                }
                for (int k = 0; k < n; k++) {
                    if(j==k||i==k){
                        continue;
                    }
                    int curNum=digits[i]*100+digits[j]*10+digits[k];
                    if(curNum%2==1){
                        continue;
                    }
                    uniqueNum.add(curNum);
                }
            }
        }
//        System.out.println(uniqueNum);
        return uniqueNum.size();
    }

    public static void main(String[] args) {
        int[] digits={1,2,3,4};
        System.out.println(totalNumbers(digits));
    }
}
