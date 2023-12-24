package E1_Tree;

import java.util.HashMap;

public class E29_LargestBSTSubtree {

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

    public static class Node{
        boolean isBST;
        int numNode;
        int min;
        int max;
        public Node(boolean isBST, int numNode, int min, int max){
            this.isBST=isBST;
            this.numNode=numNode;
            this.min=min;
            this.max=max;
        }
    }

//    public static Node solution(HashMap<TreeNode, Node> memo, TreeNode node, int[] rs){
//        if(node==null){
//            return null;
//        }
//        if(memo.containsKey(node)){
//            return memo.get(node);
//        }
//        Node left=solution(memo, node.left, rs);
//        Node right=solution(memo, node.right, rs);
//        int min=Integer.MIN_VALUE;
//        int max=Integer.MAX_VALUE;
//        int numNode=0;
//
//        System.out.printf("Before cur_val: %s, rs: %s\n", node.val, rs[0]);
//        int leftNum=0;
//        int rightNum=0;
//        if(left!=null&&left.isBST){
//            leftNum=left.numNode;
//            numNode+=left.numNode;
//            min=left.min;
//            System.out.printf("Cur node: %s, Left num node: %s\n", node.val, left.numNode);
//        }
//        if(right!=null&&right.isBST){
//            rightNum=right.numNode;
//            numNode+=right.numNode;
//            max=right.max;
//            System.out.printf("Cur node: %s, Right num node: %s\n", node.val, right.numNode);
//        }
//        System.out.printf("Cur node: %s, rs: %s, numNode: %s\n",  node.val, rs[0], numNode);
//        if((left!=null&&!left.isBST)||(right!=null&&!right.isBST)){
//            System.out.printf("Cur_val-1: %s, rs: %s\n", node.val, false);
//            return new Node(false, -1, -1, -1);
//        }
//        if(!((node.left!=null&&node.val>node.left.val||node.left==null)&&(node.right!=null&&node.val<node.right.val||node.right==null))){
//            System.out.printf("Cur_val-2: %s, rs: %s\n", node.val, false);
//            return new Node(false, -1, -1, -1);
//        }
//        if(node.val>min&&node.val<max){
//            System.out.printf("Cur node: %s, min: %s, max: %s\n", node.val, min, max);
//            rs[0]=Math.max(rs[0], Math.max(leftNum, rightNum)+1);
//            return new Node(true, numNode+1, min, max);
//        }
//        if((left!=null&&left.isBST)||(right!=null&&right.isBST)){
//            rs[0]=Math.max(rs[0], Math.max(leftNum, rightNum)+1);
//            return new Node(true, numNode+1, min, max);
//        }
//        System.out.printf("After cur_val: %s, rs: %s\n", node.val, rs[0]);
//        return new Node(false, numNode+1, min, max);
//    }

//    public static Node solution(HashMap<TreeNode, Node> memo, TreeNode node, int[] rs){
//        if(node==null){
//            return null;
//        }
//        if(memo.containsKey(node)){
//            return memo.get(node);
//        }
//        Node left=solution(memo, node.left, rs);
//        Node right=solution(memo, node.right, rs);
//        int maxLeft=Integer.MIN_VALUE;
//        int minRight=Integer.MAX_VALUE;
//        int numNode=0;
//
//        System.out.printf("Before cur_val: %s, rs: %s\n", node.val, rs[0]);
//        if(left!=null&&left.isBST){
//            numNode+=left.numNode;
//            maxLeft=left.max;
//            System.out.printf("Cur node: %s, Left num node: %s\n", node.val, left.numNode);
//        }
//        if(right!=null&&right.isBST){
//            numNode+=right.numNode;
//            minRight=right.min;
//            System.out.printf("Cur node: %s, Right num node: %s\n", node.val, right.numNode);
//        }
//        System.out.printf("Cur node: %s, rs: %s, numNode: %s, maxLeft: %s,minRight: %s\n",  node.val, rs[0], numNode, maxLeft, minRight);
//        if((left!=null&&!left.isBST)||(right!=null&&!right.isBST)){
//            System.out.printf("Cur_val-1: %s, rs: %s\n", node.val, false);
//            return new Node(false, 0, -1, -1);
//        }
//        if(node.val>=minRight||node.val<=maxLeft){
//            return new Node(false, 0, -1, -1);
//        }
//        minRight=minRight==Integer.MAX_VALUE?node.val:minRight;
//        maxLeft=maxLeft==Integer.MIN_VALUE?node.val:maxLeft;
//
//        rs[0] = Math.max(rs[0], numNode+1);
//        System.out.printf("Valid Cur node: %s, rs: %s, numNode: %s, maxLeft: %s,minRight: %s\n",  node.val, rs[0], numNode+1, maxLeft, minRight);
//        return new Node(true, numNode+1, minRight, maxLeft);
//    }

    public static Node solution(HashMap<TreeNode, Node> memo, TreeNode node, int[] rs){
        if(node==null){
            return null;
        }
        if(memo.containsKey(node)){
            return memo.get(node);
        }
        Node left=solution(memo, node.left, rs);
        Node right=solution(memo, node.right, rs);
        int maxLeft=Integer.MIN_VALUE;
        int minRight=Integer.MAX_VALUE;
        int numNode=0;

        // System.out.printf("Before cur_val: %s, rs: %s\n", node.val, rs[0]);
        if(left!=null&&left.isBST){
            numNode+=left.numNode;
            maxLeft=left.max;
            // System.out.printf("Cur node: %s, Left num node: %s\n", node.val, left.numNode);
        }
        if(right!=null&&right.isBST){
            numNode+=right.numNode;
            minRight=right.min;
            // System.out.printf("Cur node: %s, Right num node: %s\n", node.val, right.numNode);
        }
        // System.out.printf("Cur node: %s, rs: %s, numNode: %s, maxLeft: %s,minRight: %s\n",  node.val, rs[0], numNode, maxLeft, minRight);
        if((left!=null&&!left.isBST)||(right!=null&&!right.isBST)){
            // System.out.printf("Cur_val-1: %s, rs: %s\n", node.val, false);
            Node newNode=new Node(false, 0, -1, -1);
            memo.put(node, newNode);
            return newNode;
        }
        if(node.val>=minRight||node.val<=maxLeft){
            Node newNode=new Node(false, 0, -1, -1);
            memo.put(node, newNode);
            return newNode;
        }
        minRight=minRight==Integer.MAX_VALUE?node.val:minRight;
        maxLeft=maxLeft==Integer.MIN_VALUE?node.val:maxLeft;
        int min=Integer.MAX_VALUE;
        int max=Integer.MIN_VALUE;

        if(left!=null){
            min=Math.min(left.min, node.val);
        }else{
            min= maxLeft;
        }
        if(right!=null){
            max=Math.max(right.max, node.val);
        }else{
            max=minRight;
        }

        rs[0] = Math.max(rs[0], numNode+1);
        // System.out.printf("Valid Cur node: %s, rs: %s, numNode: %s, maxLeft: %s,minRight: %s\n",  node.val, rs[0], numNode+1, maxLeft, minRight);
        // System.out.printf("Valid Cur node: %s, rs: %s, numNode: %s, min: %s, max: %s\n",  node.val, rs[0], numNode+1, min, max);
        Node newNode=new Node(true, numNode+1, min, max);
        memo.put(node, newNode);
        return newNode;
    }

    public static int largestBSTSubtree(TreeNode root) {
        if(root==null){
            return 0;
        }
        HashMap<TreeNode, Node> memo=new HashMap<>();
        int[] rs=new int[]{1};

        solution(memo, root, rs);
        if(rs[0]!=0){
            return rs[0];
        }
        return 0;
    }

    public static void main(String[] args) {
        // Requirement
        // Find the largest subtree
        //- Given the root of a binary tree, find the largest subtree
        //, which is also a Binary Search Tree (BST), where the largest means subtree has the largest number of nodes.
        //
        // Idea
        //1.
        //1.0,
        //- Constraint:
        //The number of nodes in the tree is in the range [0, 10^4].
        //-10^4 <= Node.val <= 10^4
        //
        //- Brainstorm
        //- Cần check subtree nông nhất --> Theo height mà là:
        //  + BST
        //  + Cùng 1 layer thì số node lớn nhất
        //- Ta sẽ check BST và số node cùng lúc.
        //==> Không phải BST ==> Return -1
        //==> Cần lưu lại kết quả mỗi node là root của 1 subtree gồm:
        //  + Số nodes trong subtree đó
        //  + Subtree đó có phải bst hay không
        //
        //- Ở đây có 2 dạng tư duy:
        //+ Bottom up
        //+ Top down
        //
        //- Bottom up thì cần check cases:
        //+ left có phải BST hay không : true : update rs <> false
        //+ right có phải BST hay không : true : update rs <> false
        //+ Root value có thoả mãn điều kiện hay không
        //  + root < min(right)
        //  + root > max(left)
        //- Nếu 3 điều kiện trên thoả mãn:
        //  update: rs=max(rs, num_left+ num_right+1)
        //Ex:
        //        3 (false) vì 1<3
        //      /   \
        //     2     5 (true)
        //         /
        //       1 (true)
        //
        //- Ta cần update min, max nếu đi theo bottom up
        //Ex:
        //                3 (false) vì 1<3
        //          /                  \
        //     2 (min=2, max=2)     5 (true) (min=1, max=6)
        //                        /                         \
        //                  1 (true) (min=1, max=1)         6 (true) (min=6, max=6)
        //=> Tree trên valid
        //Ex:
        //                3 (false) vì 1<3
        //          /                  \
        //     2 (min=2, max=2)     5 (false) (min=1, max=6)
        //                        /                         \
        //                  1 (true) (min=1, max=1)         2 (true) (min=6, max=6)
        //=> Ở 5 mặc dù False --> Vẫn valid với 3 ==> (WRONG IDEA)
        //      3
        //    /   \
        //  2      5
        //--> Vì đề bài chỉ tính 1 node được valid nếu this node as root ==> Phải bao gồm (all descendants)
        //--> 3 ở trên bắt buộc phải includes 1 và 2 : Invalid BST
        //
        //- Idea:
        //- Khi qua 1 root node --> Luôn update rs[0]
        //  + Việc update rs bằng left và right là vô nghĩa ==> Remove
        //- Ta chỉ update khi new root node is valid
        //  + rs= max(rs, left + right + 1)
        //
        //#Reference:
        //2321. Maximum Score Of Spliced Array
        //968. Binary Tree Cameras
        //1038. Binary Search Tree to Greater Sum Tree
        //
    }
}
