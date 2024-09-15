package E1_daily;

import java.util.Arrays;

public class E89_FindMissingObservations {

    public static int[] missingRolls(int[] rolls, int mean, int n) {
        int m=rolls.length;
        int sum=0;

        for(int i=0;i<m;i++){
            sum+=rolls[i];
        }
        int expectedSum = mean*(n+m)-sum;
        int[] rs=new int[n];
        int remainingNum=n;

        if(expectedSum/remainingNum>6||expectedSum/remainingNum<=0){
            return new int[]{};
        }
        for(int i=0;i<n;i++){
            int tmp=expectedSum/remainingNum;
            if(expectedSum>tmp*remainingNum){
                tmp=tmp+1;
            }
            if(tmp>6||tmp<=0){
                rs=new int[]{};
                break;
            }
            expectedSum-=tmp;
            rs[i]=tmp;
            remainingNum--;
        }
        return rs;
    }

    public static int[] missingRollsOptimization(int[] rolls, int mean, int n) {
        int m=rolls.length;
        int sum=0;

        for(int i=0;i<m;i++){
            sum+=rolls[i];
        }
        int expectedSum = mean*(n+m)-sum;
        int[] rs=new int[n];

        if(expectedSum>6*n||expectedSum/ n <=0){
            return new int[]{};
        }
        Arrays.fill(rs, expectedSum/ n);
        int mod=expectedSum%n;

        for(int i=0;i<mod;i++){
            rs[i]++;
        }
        return rs;
    }

    public static void main(String[] args) {
        //** Requirement
        //- You have observations of n + m (6-sided dice rolls) with (each face) numbered from (1 to 6).
        // n of the observations went missing, and you only have the observations of m rolls.
        //- Fortunately, you have also calculated the average value of (the n + m rolls).
        //  + Tức là roll (n+m) lần nhưng quên k observe (n times)
        //  + Nhưng đã có giá trị trung bình (n+m) lần rolls.
        //- You are given (an integer array rolls) of (length m) where rolls[i] is (the value of the ith observation).
        //- You are also given the (two integers) (mean and n).
        //* Return (an array of length n) containing (the missing observations) such that (the average value) of (the n + m rolls) is exactly (mean).
        //  + Tìm any result có thể từ n lần missing đó (return array)
        //- If there are multiple valid answers, return any of them.
        //- If no such array exists, return an (empty array).
        //- The average value of (a set of k numbers) is (the sum of the numbers) divided by (k).
        //* Note that mean is an integer, so the (sum of the n + m rolls) should be divisible by (n + m).
        //
        //- Mean:
        //  + Gía trị trung bình.
        //
        // Idea
        //1.
        //1.0,
        // Method-1:
        //- Constraint
        //m == rolls.length
        //1 <= n, m <= 10^5
        //1 <= rolls[i], mean <= 6
        //  + n,m khá lớn:
        //      + Time: O(n/m)
        //
        //- Brainstorm
        //Ex
        //Example 1:
        //Input: rolls = [3,2,4,3], mean = 4, n = 2
        //Output: [6,6]
        //Explanation: The mean of all n + m rolls is (3 + 2 + 4 + 3 + 6 + 6) / 6 = 4.
        //- (3+2+4+3 + x)/6 = 4
        //  => x = 12
        //- Tức là bài toán là:
        //  + Sum = x, có n số 1<=a<=6
        //  + return lại list các số đó
        //
        //- Liệu có thể suy ra chính xác 1 case luôn không?
        //Ex:
        // sum = 12, n=2
        //  + 12/2 = 6
        // sum = 9, n=4
        //  + 9/4 = 2
        //  + (9 - 2)
        //- 1 số khi nào thì không thể tạo thành:
        //  + 1 sum các số + có n số
        //Ex:
        //sum = 24, n = 3
        //  + 24/3 = 8
        //+ Mean của nó > 6
        //- Có case nào mean <6 mà không điển được không:
        //sum = 2, n = 3
        //  + mean = 2/3 <1 ==> Sẽ không được
        //- Các cases khác muốn valid thì cần chọn khéo:
        //  + Các số sẽ luôn bù cho nhau để đạt được:
        //      + x<=6*n
        //sum =7, n=3
        //  + Nếu (a,b,c) = (6...) : Invalid vì ta chọn số quá to
        //  + Nếu (a,b,c) = (1,6,0) : Invalid vì ta chọn số quá to và số quá nhỏ
        //+ 7/3 = 2.3
        //  + Ta sẽ chọn 1 số >2: 3
        //  + 7-3=4, n=2
        //  + 4/2=2
        //  + Ta sẽ chọn 1 số >=2: 2
        //  ==> Ra kết quả.
        //  + Ta chọn làm tròn là được.
        //
//        int[] rolls = {3,2,4,3};
//        int mean = 4, n = 2;
        //
        //* Main point:
        //- Ta có sum = x, x/n:
        //  + Nếu thoả mãn:
        //      + (x/n)>=1 and (x/n)<=6
        //      + Phần (x/n) dư ra (ex:2.5)
        //          + 0.5 luôn bù được vì (x/n<6) (cùng lắm == 6)
        //              + Mỗi số bù ít nhầt 1 unit
        //- Ở đây ta sẽ tính được số number cần bù:
        //  Ex:
        //  n=21,n=5
        //  x = 21/5 = 4.2
        //  4,4,4,4,4
        //      + Các phần 0.2 là các phần cần bù
        //  ==> num = x%n
        //  num = 21%5 = 1 (=0.2*5)
        //  5,4,4,4,4
        //==> Đoạn này sẽ giảm được time đi.
        //
        //1.1, Optimization
        //1.2, Complexity
        //- Space: O(n)
        //- Time: O(n+m)
        //
        int[] rolls = {6,3,4,3,5,3};
        int mean = 1, n = 6;
//        int[] rs = missingRolls(rolls, mean, n);
        int[] rs = missingRollsOptimization(rolls, mean, n);
        for (int i = 0; i < rs.length; i++) {
            System.out.printf("%s, ", rs[i]);
        }
        //#Reference:
        //1223. Dice Roll Simulation
    }
}
