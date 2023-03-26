/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package E1_leetcode_medium_dynamic;

import java.util.HashMap;

/**
 *
 * @author chinhvu
 */
public class E29_CanIWin {

    public static boolean solution(
            int low,
            int high,
            int desiredTotal) {
        int rs = 0;
        boolean isLeft = true;

        if (low >= desiredTotal) {
            return true;
        }
        if (low + high >= desiredTotal) {
            return false;
        } else {
            rs += low + high;
        }
        while (low < high) {
            if (isLeft) {
                low++;
                if (rs + low >= desiredTotal) {
                    break;
                }
                rs += low;
                isLeft = false;
            } else if (!isLeft) {
                high--;
                if (rs + high >= desiredTotal) {
                    break;
                }
                rs += high;
                isLeft = true;
            }
        }
        return isLeft;
    }

    public static HashMap<Integer, Boolean> dp;
    public static int[] dp1;

    public static boolean solution1(
            int min,
            int max,
            int remainingSum, int positions) {
//        int rangeChoose=remainingSum-max;
//        if (dp.get(remainingSum + "_" + positions) != null) {
//            return dp.get(remainingSum + "_" + positions);
//        }
//        if (dp.get(positions) != null) {
//            return dp.get(positions);
//        }
        if (dp1[positions] == 1) {
            return true;
        }
        if (dp1[positions] == -1) {
            return false;
        }
        if (remainingSum <= 0) {
            return false;
        }
        boolean rs = false;

        for (int i = min; i <= max; i++) {
            if ((positions & (1 << i - 1)) != 0) {
                continue;
            }
//            if (i == 1 && remainingSum == 6) {
//                System.out.println("");
//            }
            int nextMin = min;
            int nextMax = max;
            if (i == min) {
                nextMin++;
            }
            if (i == max) {
                nextMax--;
            }
            rs = !solution1(
                    nextMin,
                    nextMax,
                    remainingSum - i,
                    positions | (1 << i - 1));
//            if (i == 1 && remainingSum == 6) {
//                System.out.println("");
//            }
            //CHỗ này lưu ý tư duy
            //Câu hỏi là ta put vào map để làm gì? Dùng ở đâu?
            //Thì lúc đó ta mới quyết định value ta muốn truyền vào là gì?
            //VD: Ở đây là kết quả của việc trừ tiếp
            //--> Ta dùng ở chỗ phủ định --> Kết quả sẽ là !(dp[][])
            //--> Ta cần lưu kết quả nằm trong Recursion
//            dp.put(remainingSum - i + "_" + (positions | (1 << i - 1)), !rs);
//            dp.put(positions | (1 << i - 1), !rs);
//            System.out.println(remainingSum - i + "_" + (positions | (1 << i - 1)) + " " + !rs);
            if (rs) {
                break;
            } else {
//                dp1[positions | (1 << i - 1)]= !rs;
            }
            dp1[positions | (1 << i - 1)] = (!rs == true) ? 1 : -1;
        }
//        dp.put(remainingSum + "_" + (positions), rs);
//        dp.put(positions, rs);
//        System.out.println(remainingSum + "_" + (positions) + " " + rs);
        dp1[positions] = (rs == true) ? 1 : -1;
        return rs;
    }

    public static boolean canIWin(int maxChoosableInteger, int desiredTotal) {
//        int dp[]=new int[desiredTotal+1];
//        boolean isFirst=true;
//
//        for(int i=1;i<=desiredTotal;i++){
//
//        }
        if (desiredTotal == 0) {
            return true;
        }
        int total = (1 + maxChoosableInteger) * maxChoosableInteger / 2;
        if (total < desiredTotal) {
            return false;
        }
//        dp = new HashMap();
        dp1 = new int[1 << maxChoosableInteger + 1];
        boolean rs = solution1(1, maxChoosableInteger, desiredTotal, 0);
        if (rs) {
            return true;
        }
        return false;
//        return solution(1, maxChoosableInteger,desiredTotal);
    }

    public static boolean canIWin1(int maxChoosableInteger, int desiredTotal) {
        if (desiredTotal <= maxChoosableInteger) {
            return true;
        }
        if (1.0 * maxChoosableInteger * (1 + maxChoosableInteger) / 2 < desiredTotal) {
            return false;
        }
        return canWin(0, new Boolean[1 << maxChoosableInteger], desiredTotal, maxChoosableInteger);
    }

    private static boolean canWin(int state,
            Boolean[] dp,
            int desiredTotal,
            int maxChoosableInteger) {
        // state is the bitmap representation of the current state of choosable integers left
        // dp[state] represents whether the current player can win the game at state
        if (dp[state] != null) {
            return dp[state];
        }
        for (int i = 1; i <= maxChoosableInteger; i++) {
            // current number to pick
            int cur = 1 << (i - 1);
            if ((cur & state) == 0) {
                // i is not used
                // set i as used in state
                if (i >= desiredTotal || !canWin(state | cur, dp, desiredTotal - i, maxChoosableInteger)) {
                    // i is greater than the desired total
                    // or the other player cannot win after the current player picks i
                    dp[state] = true;
                    return dp[state];
                }
            }
        }
        dp[state] = false;
        return dp[state];
    }

    public static void main(String[] args) {
//        int maxChoosableInteger=4;
//        int desiredTotal= 6;
        //true
        int maxChoosableInteger = 10;
        int desiredTotal = 11;
//        int maxChoosableInteger = 10;
//        int desiredTotal = 0;
//        int maxChoosableInteger = 5;
////        int desiredTotal = 50;
//        int maxChoosableInteger = 20;
//        int desiredTotal = 152;
        //false

        //1 số điều ta cần lưu ý như sau:
        //2, Cả 2 đều chọn tối ưu
        //Nên thành ra người đầu tiên luôn có (lợi thế nhất định)
        //VD: Khi người 1 chọn tôi ưu 1 
        //--> Người 2 chỉ có thể chọn dựa trên (Tôi ưu 1) * (Tối ưu 2)
        //2.1, Câu hỏi ở đây là thế nào là tối ưu?
        //- Là sự lựa chọn 1 dãy số tốt nhất cho "bản thân người chọn" --> Dãy số theo quy luật (Có thể tăng dần/ giảm dần)
        //- Là sự lựa chọn tôi ưu bằng cách vét cạn tất cả các khả năng có thể xảy ra ==> Lúc đó mới tìm được khả năng tối ưu
        //cho "bản thân người chọn nhất!"
        //3, Ta đã thử tìm cách thu gọn các khả năng theo công thức:
        //- Người trước sẽ chọn kết quả sao cho --> người sau không có khả năng win
        //---> Nhưng nếu làm thế này thì ta sẽ không ghi được kết quả ta thua --> Cũng không nhanh hơn được bao nhiêu (Nếu số desride
        // quá lớn --> Case như thế này chỉ xảy ra vào "vòng đệ quy cuối cùng")
        //Kết luận: Ở đây ta không tìm được quy luật phù họp: --> Sẽ là vét cạn
        //--> Mỗi người sẽ tìm phương án tối ưu bằng cách vét cạn các khả năng + chỉ cần tìm được 1 phương án trả "phù hợp" --> Break
        //Chú ý các đoạn sau đây:
        //1, Phần tư duy trái phải hơi "LOẠN"
//        rs = !solution1(
//                    nextMin,
//                    nextMax,
//                    remainingSum - i,
//                    positions | (1 << i - 1));
        //Phần này có nghĩa rằng (chỗ trong for/ trước khi đệ quy) --> Ta đã chọn được phần tư (i) rồi
        //--> Cái này có thể cả người (left/right) có thể sửa dụng
        //Sau khi chọn --> Cái ta mong muốn là kết quả "người sau" sẽ không thắng
        //--> Ta mong muốn solution1() sẽ là việc chọn của "người sau" sẽ thất bại tức là:
        //remainingTime >0 và việc chọn (i1) tiếp sẽ trả về false
        //** ở vòng for cuối cùng
//        rs = !solution1(
//                    nextMin,
//                    nextMax,
//                    remainingSum - i,
//                    positions | (1 << i - 1));
        //Phần này vẫn sẽ xuất hiện chỉ là sẽ không for nữa mà sẽ check:
        //remaining>0 : trả về false
        //CHÚ Ý (HAY SAI): Vì "remaing" ở đầu method --> Chính là cái "kết quả của method chả trước đó"
        //remaing<0 --> Kết quả cha thỏa mãn:
        //Mà cái ta muốn là: rs=! solution1() + rs==true
        //--> Kết quả predicate: (remainingSum<0) --> Phải trả về false
        //2, Kết quả được ghi lại sẽ như thế nào?
//            rs = !solution1(
//                    nextMin,
//                    nextMax,
//                    remainingSum - i,
//                    positions | (1 << i - 1));
        //Kết quả ta "LUÔN" ghi lại là kết quả của trong solution1()
        //Nó sẽ phản ánh kết quả "THẬT" của solution1()
        //--> "KHÔNG DÍNH DÁNG ĐẾN (rs) của for hiện tại"
        //--> dp1[position]=!rs; --> Vì ta đang gán (rs là phủ định- của kết quả hiện tại)
        //3, Cái ta đang nhầm lúc trước là việc:
//        dp.put(remainingSum + "_" + (positions), rs);
        //Tức là (tổng số mong muốn "Còn lại") + Vị trí ta chọn) ----> Sẽ được (lưu) và không tính lại
        //SAI: Vì postions ở đây đã được tính dựa trên các (method cha đầu tiên)
        //3.1, Và cái lúc return kết quả --> Đó chính là "kết quả được ghi" của "vô số" --> Các case chọn trung gian
        //11000 ==> VD: 1100(1), 110(1)0 --> Chọn 1 trong những case này
        //Câu hỏi rõ ràng : Việc lưu dp có ý nghĩa như thế nào?
        //Là kết quả của việc chọn 1 case tối ưu từ (00000 --> XXXXX) sao cho thão mãn (remaining<0)
        //--> Nó sẽ lưu kết quả trung gian gắn với (XYXYX + remaining) <===> Mình đã nghĩ thế
        //------> XYXYX --> Nó đã thể hiện case đã chọn trước đó rồi 
        //===> Dựa vào (XYXYX) --> Ta hoàn toàn có thể tính được reamingSum
        //---> Việc thêm reamingSum là "VÔ NGHIA" --> Chỉ cần:
//        dp1[positions | (1 << i - 1)] = (!rs == true) ? 1 : -1;
//      3.2, DẤU HIỆU NHẬN BIẾT THỪA:
        //- Kết quả hiện tại: Có thể tracing được tất cả kết quả trước đó
        //----> Dẫn đến có thể tính được remainingSum
        //====> Ta chỉ lưu lại các biến "ĐỘC LẬP VỚI NHAU" mà thôi!
        //4, Việc đổi (min--> minNext) về cơ bản là vô nghĩa --> Vì ta phải thêm câu lệnh if(i==min) min--
        //Mọi vòng for mà kết quả chỉ giam đi được (1 element)
        // ---> Vô nghĩa
        //5, HashMap vs Array --> Array nhanh hơn (Nên dùng array để lưu trong bài toán đệ quy có nhớ "Nhiều trường hợp")
        Long startTime = System.currentTimeMillis();
        System.out.println(canIWin1(maxChoosableInteger, desiredTotal));
        Long endTime = System.currentTimeMillis();
        System.out.println(endTime - startTime);
        startTime = System.currentTimeMillis();
        System.out.println(canIWin(maxChoosableInteger, desiredTotal));
        System.out.println(System.currentTimeMillis() - startTime);
        //        System.out.println(2&3);
        E29_CanIWin canIWin_29=new E29_CanIWin();
        E29_CanIWin canIWin_2=new E29_CanIWin();
        System.out.println(canIWin_2==canIWin_29);
        System.out.println(canIWin_2.equals(canIWin_29));
        System.out.println(canIWin_2.hashCode()==(canIWin_29.hashCode()));
        Integer a=new Integer(3);
        Integer b=new Integer(3);
        //true
        System.out.println(a.equals(b));
        //false
        System.out.println(a==b);
    }
}
