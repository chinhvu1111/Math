package E1_daily;

import java.util.ArrayList;
import java.util.List;

public class E107_LexicographicalNumbers_classic {

    public static void dfs(int index, int curVal, int len, int n, List<Integer> rs){
        if(index==len){
            return;
        }
        rs.add(curVal);
        for(int i=0;i<=9;i++){
            int nextVal=curVal*10+i;
            if(nextVal<=n){
                dfs(index+1, nextVal, len, n, rs);
            }
        }
    }

    public static List<Integer> lexicalOrder(int n) {
        int len=0;
        int temp=n;

        while(temp!=0){
            temp/=10;
            len++;
        }
        List<Integer> rs=new ArrayList<>();
        int max=Math.min(n, 9);
        for(int i=1;i<=max;i++){
            dfs(0, i, len, n, rs);
        }
        return rs;
    }

    public static List<Integer> lexicalOrderOptimization(int n) {
        int curNum=1;
        List<Integer> rs=new ArrayList<>();
        //
        for(int i=0;i<n;i++){
            rs.add(curNum);
            if(curNum*10<=n){
                curNum*=10;
            }else{
                while (curNum %10 == 9 || curNum >= n){
                    curNum/=10;
                }
                curNum++;
            }
        }
        return rs;
    }

    public static void main(String[] args) {
        //** Requirement
        //- Given an integer n,
        //* Return all the numbers in the range [1, n] sorted in lexicographical order.
        //- You must write an algorithm that runs in O(n) time and uses O(1) extra space.
        //- Tức là sort theo lexicographical dựa trên:
        //  Ex: 12 < 2 vì 1<2
        //
        // Idea
        //1.
        //1.0,
        // Method-1:
        //- Constraint
        //1 <= n <= 5 * 10^4
        //
        //- Brainstorm
        //- Ta sẽ sort theo prefix:
        //  + 1, 2, 3, 4, 5, 6, ..., 9
        //- Len sẽ tăng dần:
        //  + 1 ==> len(n)
        //- Mỗi lần ta sẽ add các phần tử dần dần:
        //  + thêm 1 -> n (Chắc chắn tăng dần)
        //Ex:
        //1,11,111,(111)1
        //  + 1111,1112,1113
        //
        //1.1, Optimization
        //- Tối ưu space --> O(1)
        //Ex:
        //1,11,111,(111)1
        //  + 1111,1112,1113
        //- Ta thấy index sẽ được tăng dần:
        //1,11,111,(111)1
        //i=0,i=1,i=2,i=3
        //  + Với mỗi (i) ta sẽ loop:
        //      + 0 -> 9 để add thêm giá trị mới.
        //- Index tăng dần nhưng nó sẽ theo chu kỳ
        //  + index = (index+1)%len
        //  + Khi ta back lại index cũ thì cache lại số trước đó tính lên ntn?
        //  Ex:
        //  121(3)9
        //  121(4)0
        //  ==> trước 4 phải là (max next index) = 9
        //
        //- Nếu ta đi theo chiều generate này:
        //  + Nó sẽ bị dạng top down ==> Sẽ bị recursion
        //  => Nếu ta gen ngược lại thì sao
        //1 -> n
        //n -> 1
        //Ex:
        //n = 13
        //13,12,11,10,9
        //13
        //12
        //==> Thấy logic cũng chưa rõ ràng cho lắm
        //Ex:
        //n=13
        //1111111
        //1111112
        //1111113
        //1111114
        //.......
        //1111119
        //1111120
        //...
        //1000000
        //==> Theo lexical ==> Bỏ hết số 0 cuối đi được.
        //==> Rule để tăng số
        //
        //* Solution:
        //- 1 -> 10 -> 100 -> 1000 -> 10000
        //  + 10000
        //  + 10001
        //  ...
        //  + 10010 * 10 > n
        //  + 10010 * 10 > n
        //  + 10011
        //  + 10012
        //  ...
        //  + 10019
        //  ==> Ra được kết quả
        //
        //1.2, Complexity
        //- Space: O(log10(n)) => O(1)
        //- Time: O(n)
        //
        int n = 13;
        System.out.println(lexicalOrder(n));
        System.out.println(lexicalOrderOptimization(n));
        //#Reference:
        //2479. Maximum XOR of Two Non-Overlapping Subtrees
        //3157. Find the Level of Tree with Minimum Sum
        //2003. Smallest Missing Genetic Value in Each Subtree
        //2307. Check for Contradictions in Equations
        //1302. Deepest Leaves Sum
        //2360. Longest Cycle in a Graph
    }
}
