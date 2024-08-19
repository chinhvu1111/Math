package E1_daily;

public class E76_BrokenCalculator {

    public static double log2(double val){
        return Math.log(val)/Math.log(2);
    }

    public static int brokenCalcWrong(int startValue, int target) {
        if(startValue>=target){
            return startValue-target;
        }
        int rs=0;
        if(target%2==1){
            rs=1;
            target=target+1;
        }
        double x=log2((double) target/startValue);
        if(x!=(int)x){
            x=(int)x+1;
        }
        rs+=x;
//        System.out.printf("x: %s\n", x);
        double subtractionNumber = startValue*Math.pow(2,x)-target;
        System.out.println((startValue*Math.pow(2,x)));
        while (subtractionNumber>=2){
            int p = (int) log2(subtractionNumber);
            subtractionNumber-= Math.pow(2, p);
            rs++;
        }
        rs+=subtractionNumber;
//        rs+=(startValue*Math.pow(x,2)-target)/2;
        return rs;
    }

    public static int brokenCalc(int startValue, int target) {
        int rs=0;

        while (target>startValue){
            rs++;
            if(target%2==1){
                target++;
            }else{
                target=target/2;
            }
        }
        return rs+startValue-target;
    }

    public static void main(String[] args) {
        // Requirement
        //- There is (a broken calculator) that has the (integer startValue) on its display initially.
        //- In one operation, you can:
        //  + multiply (the number) on display (by 2), or
        //  + (subtract 1) from (the number) on display.
        //- Given two integers (startValue) and (target),
        //* Return (the minimum number of operations) needed to display (target) on the calculator.
        //
        // Idea
        //1.
        //1.0,
        // Method-1:
        //- Constraint
        //1 <= startValue, target <= 10^9
        //+ Số khá lớn
        //
        //- Brainstorm
        //Ex:
        //Input: startValue = 2, target = 3
        //Output: 2
        //Explanation: Use double operation and then decrement operation {2 -> 4 -> 3}.
        //
        //
        //- Có thể giảm 1 đi ở giữa ==> Nhân 2 lên ra số kia
        //- 1 số a có thể:
        //  + a*2
        //  + a--
        //
        //Example 3:
        //Input: startValue = 3, target = 10
        //Output: 3
        //Explanation: Use double, decrement and double {3 -> 6 -> 5 -> 10}.
        //3 -> 10
        //  + Chỉ có thể tăng: 3*2*2 = 12
        //  + 12 - 10 = 2
        //      + Ta có thể giảm ở 1 gần nhất
        //- Giả sử:
        //- Hiệu chẵn:
        //  + target - startValue = 4
        //Ex:
        //startValue = 3
        //target = 8
        //+ Để 3*k>=8 ==> k >= 3
        //  ==> k=4
        //+ 3 -> 6 -> 5 -> 4 -> 8 ==> (4/2 sẽ ra số cần cộng thêm ào)
        //- Hiệu lẻ:
        //  + target - startValue = 5
        //Ex:
        //startValue = 3
        //target = 7
        //+ Để 3*k>=7 ==> k >= 3
        //  ==> k=4
        //+ 3 -> 6 -> 5 -> 4 -> 8 -> 7
        //  ==> Lấy số chẵn gần nhất (lớn hơn).
        //  + Vì số nhỏ hơn <7 ==> Không cộng lên được 7
        //
        //- startVal * 2^k = target
        //  ==> Tìm k
        //
        //- start = 3, target = 10
        //+ 3 -> 6 -> 12 => 10
        //
//        int startValue = 3, target = 10;
//        int startValue = 3, target = 7;
        //3 -> 6 -> 12 => 8 => 7
        //  + Hiệu = 4
        //3 -> 6(-1) -> 5(-1) -> 4 -> 8
        //x -> x-1 -> 2*(x-1) -> 2*(x-1) - 1 -> 2*(2*(x-1)-1) = 4*(x-1) - 2
        //==> Không cần phức tạp ntn
        //- Qua mỗi bước nếu ta chọn giảm đi 1 unit ==> còn N steps thì khi đến cuối sẽ giảm đi (2^N) unit
        //- Xét lại:
        //3 -> 6 -> 12 => 8(-4) => 7
        // + Length = 3
        //  1 -> 2 -> 4 ==>
        //+ 3 -> 6 -> 12 => 10
        //
        //3 -> 6(-1) -> 5(-2-2) -> 4 -> 8
        //3(-2) -> 2 -> 4 -> 8(-1) -> 7
        //  + Mỗi số giảm được 2 thôi không phải nhân lên ==> HIỂU SAI
        //- Giảm số càng xa ==> Số trừ dồn càng lớn
        //
        //+ 3 -> 6 -> 12 => 10
        //==> Size = 3*(2^2)
        //  + 2 lần nhân dồn
        //- Nếu giảm ở 3 => 2:
        //  + Trừ ở cuối đi 1*2*2
        //==> Hiệu = 2^a + 2^b
//        int startValue = 5, target = 8;
//        int startValue = 1024, target = 1;
        //5*2 = 10 => 8
        int startValue = 68, target = 71;
        //68 -> 136 -> 71
        //
        //- Nếu cứ giải như cách trên sẽ bị dính case:
        //  + 68 --> Giảm -> *2 ==> x -> Giảm thành 71
        //  ==> 68*2 ==> giảm về 71 không tối ưu
        //
        //* Solution:
        //- Instead of (multiplying by 2) or (subtracting 1) from startValue, we could divide by 2 (when target is even) or (add 1) to target.
        //- The motivation for this is that it turns out we always greedily (divide by 2):
        //- If say target is (even), then if we perform (2 additions) and (one division),
        // we could instead perform
        //  + [(one division) and (one addition)] for (less operations) [(target + 2) / 2 vs target / 2 + 1].
        //  + Nếu ((start-1)*2) <> (start*2-1)
        //      + ((start-1)*2) ==> Giảm nhiều hơn
        //  + (target+1)/2 <> (target/2+1)
        //- If say target is (odd), then if we perform (3 additions) and (one division),
        // we could instead perform
        //  + [(1 addition, 1 division, and 1 addition)] for (less operations) [(target + 3) / 2 vs (target + 1) / 2 + 1].
//        System.out.println(brokenCalc(startValue, target));
        //* Main point:
        //- Ở đây là việc backward approach
        //- Chia thành 2 case:
        //  + Odd
        //  + Even
        //- Việc tính:
        //- Target is even:
        //  + [(target + 2) / 2 vs target / 2 + 1] ==> Cùng đến 1 value
        //      3 steps vs 2 steps
        //  ==> Ưu tiên (target/2)
        //- Target is odd:
        //  + [(target + 3) / 2 vs (target + 1) / 2 + 1] ==> ==> Cùng đến 1 value
        //      4 steps vs 3 steps
        //  ==> Ưu tiên (target + 1)
        //==> CT:
        //- Target is even:
        //  + target = target /2
        //- Target is odd:
        //  + target = target+1
        //
        //- In details:
        //
        //Explanation:
        //Obviously,
        //If Y <= X, we won't do Y / 2 anymore.
        //We will increase Y until it equals to X
        //
        //So before that, while Y > X, we'll keep reducing Y, until it's smaller than X.
        //- If Y is odd, we can do only Y = Y + 1
        //  ==> Số lẻ không /2 được.
        //- If Y is even, if we plus 1 to Y, then Y is odd, we need to plus another 1.
        //And because (Y + 1 + 1) / 2 = (Y / 2) + 1, 3 operations are more than 2.
        //==> We always choose Y / 2 if Y is even.
        //
        //Why it's right
        //Actually, what we do is:
        //  - If Y is even, Y = Y / 2
        //  - If Y is odd, Y = (Y + 1) / 2
        //
        //- We reduce Y with least possible operations, until it's smaller than X.
        //And we know that, we won't do Y + 1 twice in a row.
        //Ex:
        // y+1 -> y+2 -> (y+2)/2 = y/2+1 (2 operations)
        // <=>
        // y+1 -> (y+1)/2 = y/2+1 (2 operations)
        //- Because we will always end with an operation Y / 2.
        //  ==> y+1 (Ưu tiên +1)
        //- 2N times Y + 1 and (once) Y / 2 needs 2N + 1 operations.
        //Ex:
        //  (Y+2N)/2 = Y/2+N (2*N+1 operations)
        //- Once Y / 2 first and N times Y + 1 will end up with same result, but needs only N + 1 operations.
        //  Y/2 + N ((N+1 operations)
        //  ==> Y/2 (Ưu tiên chia trước)
        //
        //*NOTE:
        //- Greedy --> Cần chứng mình CT tối ưu
        //- Thay đổi cách approach:
        //  + Backward --> forward
        //- Dùng công thức toán ==> Cần chứng minh (out of scope)
        //
        //1.1, Optimization
        //
        //1.2, Complexity
        //- Space: O(1)
        //- Time : O(log(target))
        //
        //#Reference:
        //2749. Minimum Operations to Make the Integer Zero
        System.out.println(brokenCalcWrong(startValue, target));
        System.out.println(brokenCalc(startValue, target));
    }
}
