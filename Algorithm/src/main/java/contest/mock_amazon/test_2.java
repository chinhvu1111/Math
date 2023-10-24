package contest.mock_amazon;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class test_2 {

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
    public static List<Integer> treeNodes;
    public static List<Integer> subTreeNodes;

    public static void solution(TreeNode node, List<Integer> list){
        if(node==null){
            list.add(null);
            return;
        }
        list.add(node.val);
        solution(node.left, list);
        solution(node.right, list);
    }

    public static boolean isSubtree(TreeNode root, TreeNode subRoot) {
        if(root==null&&subRoot==null){
            return true;
        }
        if(root == null){
            return false;
        }
        treeNodes = new ArrayList<>();
        subTreeNodes = new ArrayList<>();
        solution(root, treeNodes);
        solution(subRoot, subTreeNodes);
        //n=5
        int n=treeNodes.size();
        //m=3
        int m=subTreeNodes.size();
        System.out.println(treeNodes);
        System.out.println(subTreeNodes);

        for(int i=0;i<n-m+1;i++){
            int index=0;
            int curIndex=i;

            while(curIndex<n&&index<m && Objects.equals(subTreeNodes.get(index), treeNodes.get(curIndex))){
                index++;
                curIndex++;
            }
            if(index==m){
                return true;
            }
        }
        return false;
    }


    public static void main(String[] args) {
        //** Requirement
        //- Subtree là 1 cái tree có node thuộc tree đó ==> Tree đó bao gồm node đó và tất cả các nodes dưới nó.
        //
        //** Idea
        //1.
        //1.0, Idea
        //- Constraint
        //The number of nodes in the root tree is in the range [1, 2000].
        //The number of nodes in the subRoot tree is in the range [1, 1000].
        //-104 <= root.val <= 104
        //-104 <= subRoot.val <= 104
        //
        //
        //- Brainstorm
        //- Cast tree --> Array
        //+ Preorder traverse
        //+ Inorder traverse
        //+ Postorder traverse
        //
        //- Ta sẽ thao tác tìm trùng trên array
        //==> Dùng tree thì ta sẽ phải traverse trùng lặp khá nhiều
        //
        //-             3
        //          /      \
        //        4         5
        //      /   \
        //    1      2
        //         /
        //       0
        //          4
        //        /   \
        //      1      2
        //
        //- Chọn cách traverse để ta có thể convert được thành tree --> Thể hiện sự trùng lặp
        //Ex:
        //inorder : 1 - 4 - 2 - 0 ==> Tức là cái số 0 đó có thể nằm left / right
        //==> Ta cần phải thể hiện cả null ra nữa
        //==> Luôn thể hiện null.
        //==> Kiểu nào cũng được
        TreeNode root=new TreeNode(3);
        TreeNode node=new TreeNode(4);
        TreeNode node1=new TreeNode(5);
        TreeNode node2=new TreeNode(1);
        TreeNode node3=new TreeNode(2);
        TreeNode node4=new TreeNode(0);

        root.left=node;
        root.right=node1;
        node.left=node2;
        node.right=node3;
        node3.left=node4;

        TreeNode node5=new TreeNode(4);
        TreeNode node6=new TreeNode(1);
        TreeNode node7=new TreeNode(2);
        node5.left=node6;
        node5.right=node7;
        System.out.println(isSubtree(root, node5));
    }
}
