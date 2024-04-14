package E1_leetcode_medium_dynamic;

import java.util.ArrayList;
import java.util.List;

public class E131_LastStoneWeightII {

    public static int lastStoneWeightII(int[] stones) {
        int sum=0;

        for (int stone : stones) {
            sum += stone;
        }
        //Space : O(sum)
        //Tìm sum lớn nhất <= sum/2
        boolean[] dp=new boolean[sum+1];
        dp[0]=true;
        int rsLess=0;
        int rsGreater=Integer.MAX_VALUE;

        //sum 1 2 3 4 5 6 ...
        //0
        //1
        //2
        //Time : O(n)
        for (int stone : stones) {
            //Space : O(sum)
            //Time : O(sum)
            List<Integer> validIndex = new ArrayList<>();
            for (int i = 1; i <= sum; i++) {
                if (i >= stone && dp[i - stone]) {
                    validIndex.add(i);
                }
            }
            for (int idx : validIndex) {
                dp[idx] = true;
                if (idx <= sum / 2) {
                    rsLess = Math.max(rsLess, idx);
//                    System.out.printf("%s, stone: %s\n",i, stones[j]);
                }
                if (idx > sum / 2) {
                    rsGreater = Math.min(rsGreater, idx);
                }
            }
        }
//        System.out.printf("rsLess: %s, rsGreater: %s\n", rsLess, rsGreater);
        return Math.min(sum-2*rsLess, 2*rsGreater-sum);
    }

    public static void main(String[] args) {
        //** Requirement:
        //- You are given an array of integers stones where stones[i] is (the weight of the ith stone).
        //We are playing a game with the stones.
        // On each turn, we choose any two stones and smash them together.
        // Suppose the stones have weights x and y with x <= y. The result of this smash is:
        //- If x == y, (both stones are destroyed), and
        //- If x != y, the (stone of weight x) is destroyed, and the stone of (weight y) has new weight (y - x).
        //At the end of the game, there is ("at most one" stone left).
        //* Return (the smallest possible weight of the left stone). If there are (no stones left), return 0.
        //- Đại loại là return lại smallest weight trong mọi cách có thể.
        //
        //** Tư duy
        //1.
        //1.1, Idea
        //- Constraint
        //1 <= stones.length <= 30
        //1 <= stones[i] <= 100
        //
        //- Brainstorm
        //Ex:
        //Input: stones = [2,7,4,1,8,1]
        //Output: 1
        //Explanation:
        //We can combine 2 and 4 to get 2, so the array converts to [2,7,1,8,1] then,
        //we can combine 7 and 8 to get 1, so the array converts to [2,1,1,1] then,
        //we can combine 2 and 1 to get 1, so the array converts to [1,1,1] then,
        //we can combine 1 and 1 to get 0, so the array converts to [1], then that's the optimal value.
        //  + 2,4
        //  + 7,8
        //  + 2,1
        //  + 1,1
        //
        //- Ta thấy rằng:
        //  + Combine (2 và 4) ==> Ta có thể thu được 2
        //     + Không phải là cứ combine là bên phải sẽ trừ đi
        //- Chuyển đổi bài toán thành dạng có thể xử lý được:
        //Ex:
        //[2,7,4,1]
        //+ [(2,7,4),1]
        //  + [1,1]
        //+ [2,(7,4,1)]
        //  + [2,2]
        //==> Choose any two stones ==> Không cần cạnh nhau.
        //- Khi nào thì return value là min
        //- | | |a - b| - c | - d |
        //Ex:
        //2,5,8
        //+ (2,5),8 = |3 - 8| = 5
        //+ 2,(5,8) = |2 - 3| = 1
        //+ 2,(5),8 = |6 - 5| = 1
        //Ex:
        //stones = [2,7,4,1,8,1]
        //[(+)2,(-)7,(+)4,(-)1,(+)8,(-)1]
        //- Để 1 stone mang dấu + ==> Nó phải trừ đi 1 thằng nhỏ hơn nó.
        //-> Sort
        //stones = [1,1,2,4,7,8]
        //- Số nhỏ thì có thể mang dấu + hay không
        //Ex:
        //stones = [1,6,2]
        //+ (-)1,(+)6,(-)2 = 3
        // ==> Có thể coi 1 mang (-) kết hợp với 6 mang (+)
        //+ (+)1,(+)6,(-)2 = 5
        // ==> Không thể xảy ra trường hợp này.
        //==> Vì không thể xảy ra nên ta cứ tính thì sao?
        //- Coi như nó là trường hợp tệ hơn ==> Kiểu gì cũng bỏ qua.
        //- stones = [2,7,4,1,8,1]
        //  + 2+4+8 - (7+1+1)
        //  + 1+4+7 - (8+2+1)
        //- Bài toán trở thành việc chia sao cho (sum 2 phần chênh lệch nhau ít nhất)
        //- Có 2 cases:
        //  + Sum - 2*x = min >=0
        //  + 2*x - sum = min >=0
        //
        //
        //* KINH NGHIỆM:
        //- Trường hợp không thể xảy ra + tệ ==> Cứ cho vào để có thể có cùng pattern xử lý
        //
        //1.1, Optimization
        //1.2, Complexity
        //- Space: O(sum)
        //- Time: O(n*sum)
        //
        //#Reference:
        //2035. Partition Array Into Two Arrays to Minimize Sum Difference
        //
//        int[] stones= new int[]{2,7,4,1,8,1};
//        int[] stones= new int[]{31,26,33,21,40};
//        int[] stones= new int[]{31};
        int[] stones= new int[]{31,32};
        //31,33,
        //21,33,21: 75
        System.out.println(lastStoneWeightII(stones));
    }
}
