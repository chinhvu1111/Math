package E1_leetcode_medium_dynamic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Stack;

public class E132_MinimumCostTreeFromLeafValues {

    //Space: O(n)
    public static int solution(int x, int y, int[][] memo, int[][] dp){
        if(memo[x][y]!=-1){
            return memo[x][y];
        }
        int rs=Integer.MAX_VALUE;

        for(int i=x;i<y;i++){
            int left = solution(x,i, memo, dp);
            int maxLeft = dp[x][i];
            int right = solution(i+1, y, memo, dp);
            int maxRight = dp[x+1][y];
            rs=Math.min(rs, left + right+ maxLeft*maxRight);
        }
        memo[x][y]=rs;
        return rs;
    }

    public static int mctFromLeafValuesTopDown(int[] arr) {
        int n= arr.length;
        //Space : O(n^2)
        //Time : O(n^2)
        int[][] dp=new int[n][n];
        int[][] memo=new int[n][n];
        for(int i=0;i<n;i++){
            Arrays.fill(memo[i], -1);
        }

        for(int i=0;i<n;i++){
            dp[i][i]=arr[i];
            memo[i][i]=0;
        }
        for(int i=2;i<=n;i++){
            for(int j=0;j+i-1<n;j++){
                int k=i+j-1;
                dp[j][k]=Math.max(dp[j+1][k-1], Math.max(arr[j], arr[k]));
            }
        }
        int rs = solution(0, n-1, memo, dp);
        return rs;
    }

    public static int mctFromLeafValuesBottomUp(int[] arr) {
        int n= arr.length;
        //Time : O(n^2)
        //Space : O(n^2)
        int[][] dp=new int[n][n];
        //Time : O(n^2)
        //Space : O(n^2)
        int[][] maxInRange=new int[n][n];

        //Time : O(n)
        for(int i=0;i<n;i++){
            Arrays.fill(dp[i], -1);
        }
        //Time : O(n^2)
        for(int i=0;i<n;i++){
            maxInRange[i][i]=arr[i];
            dp[i][i]=0;
        }
        //Time : O(n^3)
        for(int i=2;i<=n;i++){
            for(int j=0;j+i-1<n;j++){
                int k=i+j-1;
                maxInRange[j][k]=Math.max(maxInRange[j+1][k-1], Math.max(arr[j], arr[k]));
            }
        }
        for(int i=2;i<=n;i++){
            for(int j=0;j+i-1<n;j++){
                int k=i+j-1;
                int curRs=Integer.MAX_VALUE;

                for(int l=j;l<k;l++){
                    curRs=Math.min(dp[j][l]+dp[l+1][k]+maxInRange[j][l]*maxInRange[l+1][k], curRs);
                }
                //(0,2)
                //+ (0,0)(1,2)
                //+ (0,1)(2,2)
//                System.out.printf("Len=%s, %s %s, curRs: %s\n", i, j, k, curRs);
                dp[j][k]=curRs;
            }
        }
        return dp[0][n-1];
    }

    public static int mctFromLeafValuesOptimization(int[] arr) {
        ArrayList<Integer> list=new ArrayList<>();
        for(int e: arr){
            list.add(e);
        }
        int rs=0;

        //Time : O(n)
        while (list.size()>1){
//            System.out.println(list);
            int minVal=Integer.MAX_VALUE;
            int minIndex=0;

            //Time : O(n)
            for(int i=0;i<list.size();i++){
                if(minVal>list.get(i)){
                    minVal=list.get(i);
                    minIndex=i;
                }
            }
            int curMin = 0;

            if(minIndex>=1){
                curMin=list.get(minIndex-1);
            }
            if(minIndex+1<list.size()){
                curMin = Math.min(list.get(minIndex+1), curMin);
            }
            rs+=curMin*minVal;
            //Time : O(n)
            list.remove(minIndex);
        }
        return rs;
    }

    public static int mctFromLeafValuesStack(int[] arr) {
        Stack<Integer> stack=new Stack<>();
        int rs=0;
        stack.add(Integer.MAX_VALUE);

        for(int e: arr){
            while(stack.peek()<=e){
                int mid=stack.pop();
                rs+= Math.min(e, stack.peek()) * mid;
            }
            stack.add(e);
        }
        while(stack.size()>2){
            rs+=stack.pop()*stack.peek();
        }
        return rs;
    }

    public static void main(String[] args) {
        //** Requirement:
        //- Given an array arr of (positive integers), consider all (binary trees) such that:
        //  + Each node has either 0 or 2 children;
        //  + The values of arr correspond to the values of each leaf in an (in-order) traversal of the tree.
        //  + The value of each (non-leaf) node is equal to the product of the (largest leaf value) in its left and right subtree, respectively.
        //Among all possible binary trees considered,
        //* Return (the smallest possible sum of the values) of each (non-leaf) node.
        //  + Return sum (non-leaf) nodes.
        // It is guaranteed this sum fits into a 32-bit integer.
        //- A node is a leaf if and only if it has zero children.
        //
        //** Tư duy
        //1.
        //1.1, Idea
        //- Constraint
        //2 <= arr.length <= 40
        //1 <= arr[i] <= 15
        //  -> arr[i]>1
        //It is guaranteed that the answer fits into a 32-bit signed integer (i.e., it is less than 231).
        //
        //- Brainstorm
        //Ex:
        //Input: arr = [6,2,4]
        //Output: 32
        //Explanation: There are two possible trees shown.
        //The first has a (non-leaf) node sum 36, and the second has (non-leaf) node sum 32.
        //- Liệu có nên scan all binary tree với leaf có sẵn không?
        //  + Việc này khá khó
        //- Bài này biến thành bài toán chọn ra 2 node bất kỳ cạnh nhau ==> Nhân 2 (largest leaf nodes) của chúng với nhau
        //  + Sau đó cứ thế ==> Sum tất các num kết hợp chính là kết quả
        //- Nói chung là vẫn đi nhân mấy số trong array + kết hợp với nhau
        //
        //Ex:
        //Input: arr = [6,2,4]
        //Output: 32
        //
        //- Bài này có thể làm top-down được ==> n cách chia
        //
        //- Ta cần tìm max trong range [i][j] để trả lại thông tin
        //- Làm bottom up ntn?
        //
        //1.1, Optimization
        //1.2, Complexity
        //- Top down approach:
        //+ Space: O(n^2)
        //+ Time: ?
        //          (0,n-1)
        //        /             \             \
        //      (0,1)(2,n-1)  (0,2)(3,n-1)  (0,3)(4,n-1) : O(n)
        //      /     /
        //    x    (2,3)(4,n-1) : O(n-1) -> O(1)
        //--> Khá khó để xác định complexity
        //- Bottom up approach:
        //- Space : O(n^2)
        //- Time : O(n^3)
        //
        //2. No dynamic programming
        //2.0,
        //- Khi so sánh 2 giá trị (a,b) cạnh nhau ==> Ta chỉ lấy số max + không bao giờ sử dụng lại min
        //  + Ta remove đi để tránh mất thời gian check max(a,b)
        //- Biến đổi bài toán thành:
        //+ Given an array A, choose two neighbors in the array a and b,
        //we can remove the smaller one min(a,b) and the cost is a * b.
        //What is the minimum cost to remove the whole array until only one left?
        //To remove a number a, it needs a cost a * b, where b >= a.
        //+ The cost to remove a is a * min(left, right).
        //- Đại loại là remove object MIN nhất của cả array mỗi lần check
        //  + Mỗi min đó sẽ kết hợp với 2 node cạnh nó --> Ta sẽ ưu tiên chọn node nhỏ hơn
        //  + Cũng update rs luôn
        //
        //2.1, Optimization
        //2.2, Complexity
        //- Space : O(n)
        //- Time : O(n^2)
        //
        //3. Stack solution:
        //============= WRONG IDEA
        //- 1 điểm cần chú ý:
        //  + (Tất cả các điểm đều là leaf node ==> Max nhất chính là điểm ta cần xét cuối cùng) ==> WRONG
        //  --> Tức là đến cuối ta chỉ cần giữ max value của array cuối ==> WRONG
        //- Với mỗi arr[i]:
        //  + Ta sẽ có thể tìm được kết quả của tree trước đó.
        //- 1 phần tử sẽ kết hợp với element bên:
        //+ Left
        //+ Right
        //  của chính nó
        //- Giả sử last của stack chinh là element max nhất trong tất cả element trước đó.
        //- Ta tìm min nhất của tích các max leaf
        //- Nếu đứng ở last element, ta có 2 cases:
        //  + last element kết hợp với next element
        //      + rs+= previous * a (Do a là max)
        //  + last element kết hợp với previous element
        //      + rs+= mid * a (Do mid, a là max)
        //==> Lý thuyết thì theo công thức bên trên
        //  + Nhưng ta cần chọn sao cho việc value min nhất
        //- rs sẽ cộng thệm:
        //  + previous * a
        //  + mid * a
        //==> Ta cộng thêm min ==> a * min(previous*mid)
        //============= WRONG IDEA
        //* Stack với assumption bên trên không đúng
        //Ex:
        //arr=[6,2,4]
        //  + Nếu a>=lastValue:
        //   + Ta mới thực hiện việc pop
        //  + Nếu a<lastValue:
        //   + Thì ta add(a) vào luôn
        //--> Ta sẽ kết hợp cho đến khi gặp phần tử (lastElement>a)
        //arr=[6,2,3,5]
        //+ Stack: [6,2,3,5]
        //  + Với (2,3,5) thì MIN ta nên tính = 2* min(3,5) = 2*5 = 10
        //  Nếu tính min(2,3) * 5 = 15 ==> Tệ hơn.
        //  + Với (6,2,5):
        //    + rs+= 6*min(2,5)
        //    + rs+= min(6,2)*5
        //    ==> Ta chọn min(6,5) * 2 là được.
        //    --> Khi out loop thì stack.peek() sẽ là element max nhất.
        //3.1, Optimization
        //3.2, Complexity
        //- Space : O(n)
        //- Time : O(n^2)
        //
        //#Reference:
        //1870. Minimum Speed to Arrive on Time
        //2343. Query Kth Smallest Trimmed Number
        //722. Remove Comments
        //1019. Next Greater Node In Linked List
        //131. Palindrome Partitioning
        //641. Design Circular Deque
        //798. Smallest Rotation with Highest Score
        //2044. Count Number of Maximum Bitwise-OR Subsets
        //1887. Reduction Operations to Make the Array Elements Equal
        //
        //1395. Count Number of Teams
        //1665. Minimum Initial Energy to Finish Tasks
        //2590. Design a Todo List
//        int[] arr= {6,2,4};
        int[] arr= {15,13,5,3,15};
        System.out.println(mctFromLeafValuesTopDown(arr));
        System.out.println(mctFromLeafValuesBottomUp(arr));
        System.out.println(mctFromLeafValuesOptimization(arr));
        System.out.println(mctFromLeafValuesStack(arr));
    }
}
