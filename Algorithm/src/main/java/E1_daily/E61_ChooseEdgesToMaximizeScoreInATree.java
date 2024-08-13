package E1_daily;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class E61_ChooseEdgesToMaximizeScoreInATree {

    //Space: O(h)
    public static long solution(int node, long[][] memo, List<List<int[]>> tree, boolean isDelete){
        int delete=isDelete?0:1;
        //Mỗi node kết nối với parent node, đều có thể có 2 status:
        //  + Delete
        //  + Non delete
        //(10,5),(6,3),(3,5)
        //==> Chọn thằng nào có hiệu (deleted - non delete) ==> Max nhất là được.
        //
        //Time: O(n)
        if(memo[node][delete]!=-1){
            return memo[node][delete];
        }
        List<int[]> adjNodes=tree.get(node);
        long sum=0;
        long maxSubtraction=0;

        //Time: O(E)
        for(int[] e: adjNodes){
            long deleteVal= 0;
            //Current edge need to be deleted
            if(isDelete){
                deleteVal=solution(e[0], memo, tree, false);
            }else{
                //delete
                deleteVal=solution(e[0], memo, tree, true)+e[1];
            }
            //not delete
            long notDeleteVal= solution(e[0], memo, tree, false);
            if(maxSubtraction<deleteVal-notDeleteVal){
                maxSubtraction=deleteVal-notDeleteVal;
            }
            sum+=notDeleteVal;
        }
        return memo[node][delete]=sum+maxSubtraction;
    }

    public static long maxScore(int[][] edges) {
        int n=edges.length;
        List<List<int[]>> adj=new ArrayList<>();

        for(int i=0;i<n;i++){
            adj.add(new ArrayList<>());
        }
        for(int i=1;i<n;i++){
            adj.get(edges[i][0]).add(new int[]{i, edges[i][1]});
        }
        //Time: O(n)
        //Space: O(n)
        long[][] memo=new long[n][2];
        for (int i = 0; i < n; i++) {
            Arrays.fill(memo[i], -1);
        }
        long rs = solution(0, memo, adj, false);
        rs=Math.max(rs, solution(0, memo, adj, true));
        return rs;
    }

    public static long[] solutionPostOrder(int node, List<List<int[]>> tree){
        //curRs[0]: Thể hiện cho value khi (non delete next edge) từ current node
        //curRs[1]: Thể hiện cho value khi (delete next edge) từ current node
        long[] curRs=new long[2];

        for(int[] nextNode: tree.get(node)){
            long[] sub=solutionPostOrder(nextNode[0], tree);
            curRs[0]+=sub[1];
            //- Lấy current value edges as the deleted edge
            //- Trong các edge cùng layer chọn delete edge có hiệu giữa:
            //  + deleted và non deleted là lớn nhất
            //  ==> Lấy hiệu đó + non deleted sẽ ra là xoá ít nhất 1 edge
            curRs[1]=Math.max(curRs[1], nextNode[1]+sub[0]-sub[1]);
        }
        curRs[1]+=curRs[0];
        return curRs;
    }

    public static long maxScorePostOrder(int[][] edges) {
        int n=edges.length;
        //Space: O(N+E)
        List<List<int[]>> adj=new ArrayList<>();

        //Time: O(n)
        for(int i=0;i<n;i++){
            adj.add(new ArrayList<>());
        }
        //Time: O(n)
        for(int i=1;i<n;i++){
            adj.get(edges[i][0]).add(new int[]{i, edges[i][1]});
        }
        //Time: O(n)
        //Space: O(n)
        long[][] memo=new long[n][2];
        for (int i = 0; i < n; i++) {
            Arrays.fill(memo[i], -1);
        }
        long[] rs = solutionPostOrder(0, adj);
        return Math.max(rs[0], rs[1]);
    }

    public static void main(String[] args) {
        //** Requirement
        //- You are given (a weighted tree) consisting of n nodes numbered from (0 to n - 1).
        //- The tree is rooted at node 0 and represented with a 2D array edges of size n where
        //  + edges[i] = [pari, weighti]
        // indicates that (node pari) is the parent of (node i), and the edge between them has a weight equal to (weighti).
        //- Since the root does not have a parent, you have edges[0] = [-1, -1].
        //- Choose some edges from the tree such that (no) (two chosen edges) are adjacent and (the sum of the weights) of (the chosen edges) is maximized.
        //* Return (the maximum sum) of the (chosen edges).
        //Note:
        //- You are allowed to not choose any edges in the tree, the sum of weights in this case will be 0.
        //- Two edges Edge1 and Edge2 in the tree are adjacent if they have a common node.
        //- In other words, they are adjacent if Edge1 connects nodes a and b and Edge2 connects nodes b and c.
        //
        //* Tức là return (max sum) mà ta có thể choose:
        //  + Nhưng không được chọn (2 edges đứng cạnh nhau).
        //  + Được phép k chọn gì.
        //
        //** Idea
        //1.
        //1.0,
        //* Method-1:
        //- Constraint
        //n == edges.length
        //1 <= n <= 10^5
        //edges[i].length == 2
        //par0 == weight0 == -1
        //0 <= pari <= n - 1 for all i >= 1.
        //pari != i
        //-10^6 <= weighti <= 10^6 for all i >= 1.
        //edges represents a valid tree.
        //- Số lượng node=10^5 ==> Time: O(n)/ O(n*log(n))
        //
        //- Brainstorm
        //- Weighted tree:
        //  + Tức là tree có weight + có layers
        //- Bài toán giống như việc mỗi node:
        //  + Chỉ được phép chọn duy nhất 1 edges để delete
        //Ex:
        //      1
        //    /  \   \
        //   2    3   4
        // /  \        \
        //5    7        9
        //- Nếu delete 1 edge ==> Không thể xoá được các edges:
        //  + Nối với 2 vertices ở 2 đầu nữa.
        //- Bài này có thể làm recursion được không?
        //- Một đỉnh được phép chọn:
        //  + Có delete cạnh nối với parent hay không?
        //      + Delete: 0
        //      + Not deleted: 1
        //- Làm top down trước:
        //- memo[node][0]/[1]:
        //  + Max Sum của việc deleted/ non delete đường nối đến parent của node
        //
        //Ex:
        //      1
        //    /  \   \
        //   2    3   4
        // /  \        \
        //5    7        9
        //- Giả sử (2,1) bị deleted:
        //  + => (5,2),(7,2) sẽ không bị deleted
        //  ==> skip luôn 2 ==> (2.left/ 2.right)
        //  ==> Skip layers
        //- Nếu mà xét đến 1 node=x, tức là:
        //  + node=x có path đến parent sẽ không bị delete
        //- Còn nếu path từ node --> parent không deleted:
        //  + Chọn sao cũng được.
        //- Truyền biến isDelete:
        //  + Xác định next edges of current node có cần delete hay không
        //- Với root ==0:
        //  + Ta sẽ xét cả 2 case:
        //      + isDelete=true
        //      + isDelete=false
        //
        //1.1, Optimization
        //1.2, Complexity
        //- Space: O(n+h)
        //- Time: O(n+E)
        //
        //2.
        //2.0, Post order
        //- Ở đây ta có thể return lại 1 array bao gồm 2 element values đại diện cho:
        //  + rs[0]: Không xoá next edge
        //  + rs[1]: Xoá next edge
        //
        //- Lấy current value edges as the deleted edge
        //- Trong các edge cùng layer chọn delete edge có hiệu giữa:
        //  + deleted và non deleted là lớn nhất
        //  ==> Lấy hiệu đó + non deleted sẽ ra là xoá ít nhất 1 edge
        //========== CODE
        //curRs[1]=Math.max(curRs[1], nextNode[1]+sub[0]-sub[1]);
        //========== CODE
        //
        //2.1, Optimization
        //
        //2.2, Complexity
        //- Space: O(n+E)
        //- Time: O(n+E)
        //
//        int[][] edges = {{-1,-1},{0,5},{0,-6},{0,7}};
        int[][] edges = {{-1,-1},{0,5},{0,10},{2,6},{2,4}};
        System.out.println(maxScore(edges));
        System.out.println(maxScorePostOrder(edges));
        //#Reference:
        //968. Binary Tree Cameras
        //2246. Longest Path With Different Adjacent Characters
        //
    }
}
