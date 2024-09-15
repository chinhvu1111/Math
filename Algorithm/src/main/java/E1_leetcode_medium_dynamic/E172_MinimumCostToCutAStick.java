package E1_leetcode_medium_dynamic;

import java.util.Arrays;

public class E172_MinimumCostToCutAStick {

    public static int minCost(int n, int[] cuts) {
        int m=cuts.length;
        //Space: O(m)
        int[] newCuts=new int[m+2];
        //max_index = m+1
        newCuts[m+1]=n;
        //Space: O(m^2)
        int[][] dp=new int[m+2][m+2];

        for(int i=0;i<m;i++){
            newCuts[i+1]=cuts[i];
        }
        //Space: O(m+log(m))
        //Time: O(m*log(m))
        Arrays.sort(newCuts);

        //Time: O(m^3)
        for(int len=2;len<=m+2;len++){
            //0,1,2
            for(int i=0;i+len<=m+1;i++){
                int j=i+len;
                int curRs=Integer.MAX_VALUE;
                for(int h=i+1;h<j;h++){
                    curRs=Math.min(curRs, dp[i][h]+dp[h][j]+newCuts[j]-newCuts[i]);
                }
                dp[i][j]=curRs;
            }
        }
        return dp[0][m+1];
    }

    public static int[][] memo;

    public static int solution(int[] cuts, int i, int j){
        if(j-i<2){
            return 0;
        }
        if(memo[i][j]!=Integer.MAX_VALUE){
            return memo[i][j];
        }
        int curRs=Integer.MAX_VALUE;
        for(int h=i+1;h<j;h++){
            curRs=Math.min(solution(cuts, i, h)+solution(cuts, h, j)+cuts[j]-cuts[i], curRs);
        }
        return memo[i][j]=curRs;
    }

    public static int minCostTopDown(int n, int[] cuts) {
        int m=cuts.length;
        //Space: O(m)
        int[] newCuts=new int[m+2];
        //max_index = m+1
        newCuts[m+1]=n;
        //Space: O(m^2)
        memo=new int[m+2][m+2];

        for(int i=0;i<=m+1;i++){
            Arrays.fill(memo[i], Integer.MAX_VALUE);
        }
        for(int i=0;i<m;i++){
            newCuts[i+1]=cuts[i];
        }
        //Space: O(m+log(m))
        //Time: O(m*log(m))
        Arrays.sort(newCuts);
        return solution(newCuts, 0, m+1);
    }

    public static void main(String[] args) {
        //** Requirement
        //- Given (a wooden stick) of (length n units).
        //  + The stick is labelled from (0 to n).
        //For example, (a stick of length 6) is labelled as follows:
        //- Given (an integer array cuts) where cuts[i] denotes (a position) you should perform a cut at.
        //- You should perform the cuts (in order), you can change (the order of the cuts) as you wish.
        //  + Cut theo order nào cũng được
        //- The cost of one cut is the length of the stick to be cut, the total cost is the sum of costs of all cuts.
        //- When you cut a stick, it will be split into two (smaller sticks)
        // (i.e. the sum of their lengths is the length of the stick before the cut).
        //- Please refer to (the first example) for (a better explanation).
        //* Return the minimum total cost of the cuts.
        //- Summary:
        //- Cho danh sách cuts và 1 rule có chiều dài n
        //* Reorder để việc cut tốn ít chi phí nhất
        //
        //** Idea
        //1.
        //1.0,
        //* Method-1: Dynamic programming
        //- Constraint:
        //2 <= n <= 10^6
        //1 <= cuts.length <= min(n - 1, 100)
        //1 <= cuts[i] <= n - 1
        //All the integers in cuts array are distinct.
        //  + Length của rule khá lớn ==> 10^6
        //  + Length của cuts thì small
        //      + Time: O(n)
        //
        //- Brainstorm
        //Example 1:
        //
        //Input: n = 7, cuts = [1,3,4,5]
        //Output: 16
        //Explanation: Using cuts order = [1, 3, 4, 5] as in the input leads to the following scenario:
        //The first cut is done to a rod of length 7 so the cost is 7.
        // The second cut is done to a rod of length 6 (i.e. the second part of the first cut),
        // the third is done to a rod of length 4 and the last cut is to a rod of length 3.
        // The total cost is 7 + 6 + 4 + 3 = 20.
        //Rearranging the cuts to be [3, 5, 1, 4] for example will lead to a scenario with total cost = 16 (as shown in the example photo 7 + 4 + 3 + 2 = 16).
        //
        //- Cut ở đâu sẽ gắn với length của rule mà nó cut lên:
        //  + Có thể có nhiều rule được cut ra.
        //Ex:
        // n cut 1 = n-1
        //  + rs+=n
        // n cut 2 = n-1
        //  + rs+=n-1
        //
        //- Kiểu gì ta cũng sẽ cut qua tất cả elements:
        //  + Mỗi index đều có thể chọn bất cứ cách cut nào
        //- Giả sử chiều dài còn lại là x, cut[i]=y:
        //  + cut thì sẽ tốn cost = x
        //  + cost dựa trên length của rule mà nó cut lên
        //
        //- x,y: cut ở (i):
        //  + x<=i<=y:
        //      + (x,i),(i,y)
        //  + i<x<=y:
        //      + (i,x),(x,y)
        //  + x<=y<i:
        //      + (x,y),(y,i)
        //
        //- Nếu muốn tính kết quả ==> Đi qua all cuts
        //- Thứ tự cut ảnh hưởng ntn đến cost:
        //Ex:
        //0-1-2-3-4-5-6-7
        //  + cut 5
        //  + rs+=7
        //0-1-2-3-4-5 5-6-7
        //  + cut 3
        //  + rs+=5
        //0-1-2-3 3-4-5 5-6-7
        //  + cut 2
        //  + rs+=3
        //==> rs = 15
        //
        //0-1-2-3-4-5-6-7
        //  + cut 3
        //  + rs+=7
        //0-1-2-3 3-4-5-6-7
        //  + cut 5
        //  + rs+=4
        //0-1-2-3 3-4-5 5-6-7
        //  + cut 2
        //  + rs+=3
        //==> rs = 14
        //- Việc tráo đổi cut 3 và 5 với nhau làm kết quả tốt hơn:
        //  + Do cut 5 cut trên rule ngắn hơn = 4 (Thay vì cut 3 trên rule 5)
        //
        //- Cut tại (i)
        //  + Sau đó cut tại (j)
        //  + j<i: thì rs+= left_rule_length
        //  + j>i: thì rs+= right_rule_length
        //
        //- Khó ở chỗ hoán vị của orders
        //- Làm sao để có thể lưu size của rule ==> Về sau có thể cut -> tìm được cost tương ứng
        //- Cut thế nào thì cut:
        //  + Kết quả của việc cut rule nó vẫn sẽ chỉ là 1 thôi
        //
        //Ex:
        //0-1-2-3-4-5-6-7
        //cuts = [1,3,4,5]
        //final rules = 0-1 1-2-3 3-4 4-5 5-6-7
        //- last cut có thể là:
        //  1: rs+=3
        //  3: rs+=3
        //  4: rs+=2
        //  5: rs+=3
        //- Value được cộng thêm khi cut[i] sẽ phụ thuộc vào:
        //  + j gần nhất cut[j] < cut[i]
        //  + k gần nhất cut[i] < cut[k]
        //      + rs+= (cuts[k]-cuts[j])
        //
        //* Solution:
        //- Việc order của cuts, dựa trên các idea:
        //  + Liên quan đến permutation:
        //      + Idea không được vì hoán vị lớn
        //cuts: 1,3,4,5
        //  + Việc chọn cut[i] là cut cuối tương ứng với việc:
        //      + Chia cái problem cuối thành 2 phần:
        //          + Phần rule có length <= cut[i] và phần rule có length >= cut[i]
        //  ==> Ta có thể chia ra thành 2 bài toán con đoạn này
        //- Với idea này thì làm top down được
        //
        //- Nếu làm bottom up thì ta cần phải tính những thằng con có :
        //  + j-i>=2 ==> j-i>=n-1
        //* Khi tính lần lượt thì (diff+1) sẽ được tính theo (diff)
        //
        //** Kinh nghiệm:
        //  + Loop theo (i) để điền lần lượt
        //      + Permutation + memo
        //  + Loop theo length để tính khoảng
        //
        //- Init value:
        //  + diff=1:
        //      + dp[i][i] = n
        //  + Sau khi cut thì length sẽ ăn theo cái gì:
        //  n=8 ==> Bên trên nhầm đấy
        //      + n=k ==> max(index)=k-1
        //  + cut[0]=2
        //      + (0-1-2)(2-3-4-5-6-7)
        //      + rs+=7
        //  + cut[1]=5
        //      + rs+=5
        //
        //- Vị trí mà có (diff=n-1)
        //==> CHÍNH LÀ LẦN CHỌN ĐẦU TIÊN
        //- Cần add theo 2 phần tử nữa vào cuts là:
        //  + 0, n-1
        //* Kinh nghiêm:
        //  + Không phải lần (diff/index) tính trước là lần chọn (first/last)
        //  ==> Còn phụ thuộc vào tính chất chiều của bài toán
        //  + Ở đây lần chọn đầu tính chất của nó ngược lại 1 chút:
        //      + Nó sẽ được suy ra bằng việc xen kẽ nhau tính trước của (các cuts[i])
        //- Tính từ length = 3 trở đi
        //  + Tính từ length = 2 thì sẽ sai
        //
        //1.1, Optimization
        //1.2, Complexity
        //- Space: O(m^2+m+log(m))
        //- Time: O(m^3)
        //
        int n = 7;
        int[] cuts = {1,3,4,5};
        System.out.println(minCost(n, cuts));
        System.out.println(minCostTopDown(n, cuts));
        //#Reference:
        //2147. Number of Ways to Divide a Long Corridor
        //3013. Divide an Array Into Subarrays With Minimum Cost II
        //
    }
}
