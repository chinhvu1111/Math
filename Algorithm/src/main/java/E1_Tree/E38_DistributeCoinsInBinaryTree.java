package E1_Tree;

public class E38_DistributeCoinsInBinaryTree {

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
    }

    public static int[] solution(TreeNode node){
        if(node==null){
            return new int[]{0, 0};
        }
        int[] left, right;
        left=solution(node.left);
        right=solution(node.right);
        int[] curRs= new int[]{left[0]+right[0]+node.val-1, Math.abs(left[1])+Math.abs(right[1]) + Math.abs(left[0]+right[0]+node.val-1)};
//        System.out.printf("Left: %s %s, Right: %s %s, cur: %s %s\n", left[0], left[1], right[0], right[1], curRs[0], curRs[1]);
        return curRs;
    }

    public static int distributeCoins(TreeNode root) {
        int[] rs = solution(root);
        return rs[1];
    }

    public static void main(String[] args) {
        //**Requirement
        //- You are given (the root of a binary tree) with (n nodes) where (each node) in the tree has ("node.val" coins).
        // There are (n coins) in (total throughout) (the whole tree).
        //- In one move, we may choose (two adjacent nodes) and move (one coin) from (one node) to (another).
        //- (A move) may be from (parent to child), or from (child to parent).
        //* Return (the minimum number of moves) required to make (every node) have "exactly" (one coin).
        //* Số lần move để mỗi node có ít nhất 1 coin.
        //
        //**Idea
        //1.
        //1.0,
        //- Constraint:
        //The number of nodes in the tree is n.
        //1 <= n <= 100
        //0 <= Node.val <= n
        //The sum of all Node.val is n.
        //==> Số lượng nodes không quá lớn
        //
        //- Brainstorm
        //Ex:
        //Input: root = [3,0,0]
        //Output: 2
        //Explanation: From the root of the tree, we move one coin to its left child, and one coin to its right child.
        //      3
        //    /   \
        //  0      0
        //- Từ 1 coin có (val=x) > 1 ==> Chắc chắn sẽ cần move sang coin khác (x-1)
        //- Từ 1 coin có (val=y) == 0 ==> Chắc chắn sẽ cần move từ coin khác (y>1)
        //- Trong quá trình move hoàn toàn có thể sẽ move các coin qua các intermediate node + move tiếp sang các node khác nữa
        //Ex:
        //        3 (nếu >1) ==> Chắc chắn là cần phải move đến node nào đó
        //      /   \
        //    0      1
        //   /        \
        //  2          0
        //   \
        //    0
        //+ 3 có thể move:
        //  + right(depth=3)
        //  + left(depth=2)
        //  + left(depth=4)
        //
        //Ex:
        //        0
        //      /   \
        //    0      1
        //   /        \
        //  2          0
        //   \
        //    3
        //Với mỗi node.val ==0:
        //  + Cần thêm 1 move từ đâu đó
        //  => Ưu tiên node gần nó nhất liệu có được không?
        //Ex:
        //        2
        //      /  \
        //  (*)0     0
        //   /
        //  1
        // /
        //2
        //+ Node * có 2 lựa chọn:
        //  + Move từ root =2 (depth=1)
        //      + right node=0 cũng cần bù ==> move từ node=2(depth=4)
        //      + rs= 1 + 4 = 5
        //  + Move từ node=2 (Depth=4)
        //      + right node=0 cũng cần bù ==> move từ node=2(depth=1)
        //      + rs= 1 + 2 = 3
        //==> 2 node==0 gần là 1 ==> Không biết chọn nodes nào
        //- Ưu tiên node gần nó nhất liệu có được không?
        //  + Không được.
        //- 1 node có thể:
        //  + ==0
        //  + ==1
        //  + >1 : Move (x-1) đi ra (node==0) khác
        //- Nếu >1 ta chọn fill node có node.val == 0 gần nhất thì sao?
        //Ex:
        //             1
        //           /  \
        //      (*)0     1
        //       /
        //      2
        //    /
        //   0
        //  /
        // 2
        //
        //+ node=2(depth=3) nên chọn 0 trên hay dưới
        //  + Dưới thì node=2(depth=5) sẽ cần bù xa hơn
        //  + Trên thì node=2(depth=5) sẽ bù gần
        //=> Nếu >1 ta chọn fill node có node.val == 0 gần nhất thì sao?
        // + Không
        //* Hint:
        //- Instead of thinking on what node to move my current coins or from what node to get them,
        // think on "how many coins do I need at this point". After thinking on that, think on "from where should I start making the nodes coins 1?".
        //- Losing coins or gaining coins on each node means the same thing: "I need to move n coins to have only 1"
        //
        //- Left coins và right coins có liên quan không?
        //Ex:
        //               1 (4,1)
        //             /         \
        //      (*)0 (4,0)     1 (0,0)
        //         /
        //       2 (2,0)
        //      /
        //     0 (2,0)
        //    /
        //   2 (0,0)
        //+ Mỗi node có thể:
        //
        //Ex:
        //               1 (4,1)
        //             /         \
        //      (*)0 (4,0)     1 (0,0)
        //         /
        //       2 (2,0)
        //      /
        //     0 (1,0) ==> Nếu bằng 0 thì ta chọn lấy (left, right) đều được.
        //    /
        //   2 (0,0)
        //- Đi từ dưới lên trên + điền dần là được.
        //
        //Ex:
        //              3
        //      /(-1)         \ (-1)
        //   0 (0,0)     0 (0,0)
        //- We need to traverse the tree by using a post-order traversal method that considers the leaf nodes as the starting nodes.
        //- The number of coins of each node needs to be modified from x to 1 coin so it could be increased or decreased by (x-1)
        //- For each node, this number of coins will be brought following the bottom-up method so it will take (x-1) moves
        // ==> This result needs to be stored for use in the next step.
        //- In the next step, the new node also needs to be checked again by using the result of the left and right branches.
        //
        //1.1, Optimization
        //1.2, Complexity
        //- Space : O(n)
        //- Time : O(n)
        //
        TreeNode root=new TreeNode(3);
        TreeNode node1=new TreeNode(0);
        TreeNode node2=new TreeNode(0);
        root.left=node1;
        root.right=node2;
        System.out.println(distributeCoins(root));
    }
}
