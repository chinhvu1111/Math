package E1_leetcode_medium_dynamic;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class E110_UniqueBinarySearchTreesII {

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }

        @Override
        public String toString() {
            return "TreeNode{" +
                    "val=" + val +
                    '}';
        }
    }

    public static List<TreeNode> generateTreesWrongBottomUp(int n) {
        List<TreeNode>[][] dpTree = new ArrayList[n + 1][n + 1];

        //Init for root node
        for (int i = 1; i <= n; i++) {
            //Init for size of tree
            for(int j=2;j<=n;j++){
                dpTree[j][i] = new ArrayList<>();
            }
        }
        for (int i = 1; i <= n; i++) {
            dpTree[1][i] = new ArrayList<>();
            dpTree[1][i].add(new TreeNode(i));
        }
        //Scan all size
        for (int i = 2; i <= n; i++) {
            //Scan all node root
            System.out.printf("Size tree: %s\n", i);
            for (int j = 1; j <= n; j++) {
                //Scan all of sizes of the left subtree
                for (int l = 0; l < i; l++) {
                    //Scan all left root node
                    for (int ln = 0; ln < j; ln++) {
                        List<TreeNode> listLeftRoot = null;
                        //Left node exists
                        if(ln!=0){
                            listLeftRoot=dpTree[l][ln];
                        }
                        int rSize = i - 1 - l;
                        //Scan all the root right nodes
                        //j==n
                        for (int rn = j + 1; rn <= n+1; rn++) {
                            System.out.printf("Size left= %s, left root= %s, Size right= %s, right root= %s\n", l, ln, rSize, rn);
                            List<TreeNode> listRightRoot = null;
                            if(rn!=n+1){
                                listRightRoot=dpTree[rSize][rn];
                            }
                            if(listLeftRoot==null&&listRightRoot==null){
                                continue;
                            }
                            List<TreeNode> listCurRoots=new ArrayList<>();
                            if(listLeftRoot!=null){
                                for(TreeNode left: listLeftRoot){
                                    TreeNode curRoot = new TreeNode(j);
                                    curRoot.left=left;
                                    listCurRoots.add(curRoot);
                                }
                            }
                            if(listRightRoot!=null){
                                for(TreeNode right: listRightRoot){
                                    if(!listCurRoots.isEmpty()){
                                        for(TreeNode root: listCurRoots){
                                            root.right=right;
                                            dpTree[i][j].add(root);
                                        }
                                        continue;
                                    }
                                    TreeNode curRoot = new TreeNode(j);
                                    curRoot.right=right;
                                    dpTree[i][j].add(curRoot);
                                }
                            }
                            //Generate the new BST
                            //List left nodes
//                            for (TreeNode left : listLeftRoot) {
//                                System.out.printf("Left node: %s\n", left);
//                                for (TreeNode right : listRightRoot) {
//                                    System.out.printf("Right node: %s\n", right);
//                                    TreeNode curRoot = new TreeNode(j);
//                                    curRoot.left = left;
//                                    curRoot.right = right;
//                                    dpTree[i][j].add(curRoot);
//                                }
//                            }

                        }
                    }
                }
            }
        }
        System.out.println("========");
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                List<TreeNode> listRoots = dpTree[i][j];
                System.out.printf("Length= %s, root val=%s, count= %s\n", i, j, listRoots.size());

                for (TreeNode root : listRoots) {
                    println(root);
                }
            }
        }
        return null;
    }

    //Để build BST cần base trên:
    //+ Limit (Chứ không phải root vì root có thể --> Sang right được > root)
    //+ Size của BST nữa
    //- Idea dựng 2 method để build left, right tree
    //  ==> Right thiếu phần max ==> Cần đủ cả min và max -> Về cơ bản 2 thằng có vẻ đểu quy về min max được
    //
    //* Kinh nghiệm:
    //+ Build cái BST cần chú ý vào từng node là được + update (min, max) mỗi lần
    //+ Không cần chú ý size qua nhiều. ==> Vì range(min, max) cũng thể hiện size rồi
    //+ Ngoài ra cũng không cần care previous node ==> Vì việc update range(min, max) cũng thể hiện rồi
    //- Ngoài ra việc left không có cases nào : Ta sẽ add null để tránh (list left ==null / empty)
    //  + for leftList
    //      for rightList : nested sẽ không run được.
    //
//    public static void buildLeftTree(int limit, int prevNode, int leftSize){
//        //Ex:
//        //       limit
//        //     /
//        //   1 -> limit-1
//        //
//    }
//
//    public static void buildRightTree(int limit, int prevNode, int rightSize){
//        //Ex:
//        //       limit
//        //          \
//        //          limit+1, n
//        //
//    }
    //Space: O(n+y)
    public static Map<Pair, List<TreeNode>> dp;
    public static List<TreeNode> buildTree(int min, int max){
        List<TreeNode> rs=new ArrayList<>();

        if(min>max){
            rs.add(null);
            return rs;
        }
        Pair p=new Pair(min, max);
        if(dp.containsKey(p)){
            return dp.get(p);
        }
        //Time : O(max-min)
        //1,n
        //n=4
        //              1,4 (O(n-2)) = 1
        //         /            \
        //   (1,2/2,4)       (1,3/3,4) = O(n-8) (time=2)
        //   /     \            \     \
        // (1,2)   (2,3/3,4)   (1,3)  (3,4) = O(n-16) (time=4)
        //  -> O(n-32) (time=8) 8*4
        //  -> O(n-4*2^k)
        //- K is the depth of the recursion
        //- Y is the number of tree for each case.
        //- Time Upper bound: O(n*k-x)
        //- Space: O(n+y)
        //Left size
        for(int i=min;i<=max;i++){
            List<TreeNode> leftSubTrees=buildTree(min, i-1);
            List<TreeNode> rightSubTrees=buildTree(i+1, max);
            for(TreeNode left: leftSubTrees){
                for(TreeNode right: rightSubTrees){
                    TreeNode root=new TreeNode(i, left, right);
                    rs.add(root);
                }
            }
        }
        dp.put(new Pair(min, max), rs);
        return rs;
    }

    public static List<TreeNode> generateTreesBottomUp(int n) {
        //Size for left
        dp=new HashMap<>();

//        for(TreeNode root: listRoots){
//            System.out.printf("Root node: %s\n", root.val);
//            println(root);
//        }
        return buildTree(1, n);
    }

    public static void println(TreeNode node) {
        if (node == null) {
            return;
        }
        System.out.println(node.val);
        println(node.left);
        println(node.right);
    }

    public static void main(String[] args) {
        // Đề bài:
        //- Given an integer n
        //* Return all the structurally unique BST's (binary search trees),
        // which has exactly (n nodes of unique values) from (1 to n).
        //- Return the answer in any order.
        //
        // Tư duy
        //1.
        //1.1, Idea
        //- Constraint
        //1 <= n <= 8
        //
        //- Brainstorm
        //- Bài này tương tự bài "Unique Binary Search Trees":
        //  + Khác là bài kia return số lượng
        //  --> Bài này return danh sách các unique trees tương ứng
        //
        //Ex:
        //n=5
        //+ numNode=1
        //rs = [1,2,3,4,5]
        //+ numNode=2
        //rs =
        //          1 ==> SAI vì là BST
        //      /
        // (2,3,4,5)
        //
        //rs =
        //          2 ==> SAI vì là BST
        //      /
        // (1,3,4,5)
        //- Khi mà gắn 1 subtree --> root
        //  + Ta cần xét all subtree mà không contain value của root đó
        //- Vấn đề là:
        //      root
        //     /   \
        //   left  right
        //==> Vì là binary search tree (BST) --> Nên sẽ không có chuyện left và right chung node với nhau được.
        //
        //- dpLeft[i] là list các tree có all value <=i.
        //- dpRight[i] là list các tree có all value >=i.
        //
        //- Wrong solution:
        //- Xét all size tree 1 --> n
        //- Size i --> được tính theo (0 --> i-1)
        //- Formula:
        //      root(val)
        //     /                                                \
        //  left(List all subtree having dp[val-1] --> dp[1]    right (thì ta xét bằng cách trừ đi left)
        //
        //Ex:
        //      5
        //    /   \
        //  2      6
        //    \
        //     4
        //
        //* Solution:
        //- Vì tree là BST nên:
        //  + Ta không có cách nào order để có thể 2 subtree thiếu node + bù lại được
        //  --> 2 subtrees left và right cần có số lượng nodes chính bằng (root.val-1)
        //  => WRONG IDEA
        //- Ta sẽ build tree từ (small root.val) -> (big root.val)
        //Ex:
        //Root=1
        //          1
        //            \
        //             2 --> Số lớn hơn 1 có n-1 số
        //==> Ta không limit bằng chỉ root value được ==> Mà cần cả size nữa.
        //
        //Ex:
        //n=3
        //- n=1
        //rs=
        // 1, 2, 3
        //- n=2
        //rs=
        //+ right
        // 1
        //  \
        //   2
        // 2
        //  \
        //   3
        //+ left
        //    2
        //  /
        // 1
        //    3
        //  /
        // 2
        //- n=3
        // 1
        //  \
        //   2
        //    \
        //     3
        //
        //   1
        //     \
        //       3
        //     /
        //   2
        //
        //    2
        //  /   \
        //1      3
        //
        //        3
        //      /
        //    2
        //  /
        //1
        //- Ta thấy rằng:
        //- 1 có thể connect với node với:
        //  + root.val >1
        //  + size = x
        //- Chỗ này có phải sẽ không chia ra left và right nữa mà để thành 1 dp luôn bao gồm?
        //  + root val
        //  + Size
        //===> WRONG
        //- Vì nếu không chia left, right
        //==> Làm sao biết
        // + (subtree đó >= root) : right
        // + (subtree đó <= root) : left
        //* ==> Rất khó để làm (bottom up)
        //- Thử top down (Recursion)
        //
        //Ex:
        //          1
        //   /                  \
        //+ size=(0 -> 2)
        //+ root =  i-1 -> 0
        //
        //** Solution:
        //Để build BST cần base trên:
        //+ Limit (Chứ không phải root vì root có thể --> Sang right được > root)
        //+ Size của BST nữa
        //- Idea dựng 2 method để build left, right tree
        //  ==> Right thiếu phần max ==> Cần đủ cả min và max -> Về cơ bản 2 thằng có vẻ đểu quy về min max được
        //
        //** Kinh nghiệm:
        //+ Build cái BST cần chú ý vào từng node là được + update (min, max) mỗi lần
        //+ Không cần chú ý size qua nhiều. ==> Vì range(min, max) cũng thể hiện size rồi
        //+ Ngoài ra cũng không cần care previous node ==> Vì việc update range(min, max) cũng thể hiện rồi
        //- Ngoài ra việc left không có cases nào : Ta sẽ add null để tránh (list left ==null / empty)
        //  + for leftList
        //      for rightList : nested sẽ không run được.
        //
//    public static void buildLeftTree(int limit, int prevNode, int leftSize){
//        //Ex:
//        //       limit
//        //     /
//        //   1 -> limit-1
//        //
//    }
//
//    public static void buildRightTree(int limit, int prevNode, int rightSize){
//        //Ex:
//        //       limit
//        //          \
//        //          limit+1, n
//        //
//    }
        //
        //1.2, Optimization
        //1.3, Complexity:
        //- Space:
        //- Time :
        //** Analysis:
        //Time : O(max-min)
        //1,n
        //n=4
        //              1,4 (O(n-2)) = 1
        //         /            \
        //   (1,2/2,4)       (1,3/3,4) = O(n-8) (time=2)
        //   /     \            \     \
        // (1,2)   (2,3/3,4)   (1,3)  (3,4) = O(n-16) (time=4)
        //  -> O(n-32) (time=8) 8*4
        //  -> O(n-4*2^k)
        //- K is the depth of the recursion
        //- Y is the number of tree for each case.
        //- Time Upper bound: O(n*k-x)
        //- Space: O(n+y)
        //
        int n = 3;
//        System.out.println(generateTreesWrongBottomUp(n));
        System.out.println(generateTreesBottomUp(n));
        //#Reference:
        //514. Freedom Trail
        //2209. Minimum White Tiles After Covering With Carpets
        //1676. Lowest Common Ancestor of a Binary Tree IV
    }
}
